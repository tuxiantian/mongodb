package com.laijia.mq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarLocation;
import com.laijia.mongodb.service.CarRecordService;


/*当consumer端消息消费的速率很高(相对于producer生产消息)，而且消息的数量也很大时(比如消息源源不断的生产)，我们使用optimizeACK + prefetch将会极大的提升consumer的性能。
 * 不过反过来：
1) 如果consumer端消费速度很慢(对消息的处理是耗时的)，过大的prefetchSize，并不能有效的提升性能，反而不利于consumer端的负载均衡(只针对queue)；按照良好的设计准则，
当consumer消费速度很慢时，我们通常会部署多个consumer客户端，并使用较小的prefetch，同时关闭optimizeACK，可以让消息在多个consumer间“负载均衡”(即均匀的发送给每个
consumer)；如果较大的prefetchSize，将会导致broker一次性push给client大量的消息，但是这些消息需要很久才能ACK(消息积压)，而且在client故障时，还会导致这些消息的重发。

2) 如果consumer端消费速度很快，但是producer端生成消息的速率较慢，比如生产者10秒钟生成10条消息，但是consumer一秒就能消费完毕，而且我们还部署了多个consumer！！这种场景下，
建议开启optimizeACK，但是需要设置较小的prefetchSize；这样可以保证每个consumer都能有"活干"，否则将会出现一个consumer非常忙碌，但是其他consumer几乎收不到消息。

3) 如果消息很重要，特别是不原因接收到”redelivery“的消息，那么我们需要将optimizeACK=false，prefetchSize=1

既然optimizeACK是”延迟“确认，那么就引入一种潜在的风险：在消息被消费之后还没有来得及确认时，client端发生故障，那么这些消息就有可能会被重新发送给其他consumer，
那么这种风险就需要client端能够容忍“重复”消息。

prefetch值默认为1000，当然这个值可能在很多场景下是偏大的；我们暂且不考虑ACK_MODE(参见下文)，通常情况下，我们只需要简单的统计出单个consumer每秒的最大消费消息数即可，
比如一个consumer每秒可以处理100个消息，我们期望consumer端每2秒确认一次，那么我们的prefetchSize可以设置为100 * 2 /0.65大概为300。无论如何设定此值，
client持有的消息条数最大为：prefetch + “DELIVERED_ACK_TYPE消息条数”(DELIVERED_ACK_TYPE参见下文)

 即使当optimizeACK为true，也只会当session的ACK_MODE为AUTO_ACKNOWLEDGE时才会生效，即在其他类型的ACK_MODE时consumer端仍然不会“延迟确认”，即:
 consumer.optimizeAck = connection.optimizeACK && session.isAutoAcknowledge()
*/
public class JmsMessageListener implements MessageListener {
	private final Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);
	@Autowired
	private CarRecordService service;	
    
	public void onMessage(Message message) {
		logger.info("method  onMessage", "receive message  start");
		//BytesMessage 是最快的，ObjectMessage （序列化对象） 是最慢的。
		if (message instanceof ObjectMessage) {
			ObjectMessage objMessage = (ObjectMessage) message;
			try {
				System.out.println("-------------"+objMessage.toString());
				Object obj = objMessage.getObject();
				if (obj instanceof CarInfo) {
					service.saveCarInfo((CarInfo) obj);
				}else if (obj instanceof CarLocation) {
					service.saveCarLocation((CarLocation) obj);
				}else {
					logger.info("invalid message , cannot process this message");
				}
				logger.info("method  onMessage", "receive message  end");
			} catch (Exception e) {
				logger.error("JmsMessageListener.onMessage", "message:"+objMessage.toString(), e);
			}

		}

	}
}