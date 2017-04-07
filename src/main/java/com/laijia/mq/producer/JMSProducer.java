package com.laijia.mq.producer;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarLocation;
import com.laijia.mongodb.util.PropertiesUtil;

/**
 * JMS消息生产者
 *
 */
public class JMSProducer implements ExceptionListener{
	
	private Logger logger =LoggerFactory.getLogger(JMSProducer.class);
	//设置连接的最大连接数
	public final static int DEFAULT_MAX_CONNECTIONS=50;
	private int maxConnections = DEFAULT_MAX_CONNECTIONS;
	//设置每个连接中使用的最大活动会话数
	private int maximumActiveSessionPerConnection = DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION;
	public final static int DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION=300;
	//线程池数量
	private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;
	public final static int DEFAULT_THREAD_POOL_SIZE=50;
	//强制使用同步返回数据的格式
	private boolean useAsyncSendForJMS = DEFAULT_USE_ASYNC_SEND_FOR_JMS;
	public final static boolean DEFAULT_USE_ASYNC_SEND_FOR_JMS=true;
	//是否持久化消息
	private boolean isPersistent = DEFAULT_IS_PERSISTENT;
	public final static boolean DEFAULT_IS_PERSISTENT=true; 
	
	
	//连接地址
	private String brokerUrl;

	private String userName;

	private String password;

	private ExecutorService threadPool;

	private PooledConnectionFactory connectionFactory;
	
	
	private static class Inner{
		static JMSProducer jmsProducer=new  JMSProducer(PropertiesUtil.getActivemqString("brokerURL"), 
				PropertiesUtil.getActivemqString("username"), 
				PropertiesUtil.getActivemqString("password"));
	}
	
	
	public static JMSProducer getInstance(){
		return Inner.jmsProducer;
	}

	private JMSProducer(String brokerUrl, String userName, String password) {
		this(brokerUrl, userName, password, DEFAULT_MAX_CONNECTIONS, DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION, DEFAULT_THREAD_POOL_SIZE, DEFAULT_USE_ASYNC_SEND_FOR_JMS, DEFAULT_IS_PERSISTENT);
	}
	
	public JMSProducer(String brokerUrl, String userName, String password, int maxConnections, int maximumActiveSessionPerConnection, int threadPoolSize,boolean useAsyncSendForJMS, boolean isPersistent) {
		this.useAsyncSendForJMS = useAsyncSendForJMS;
		this.isPersistent = isPersistent;
		this.brokerUrl = brokerUrl;
		this.userName = userName;
		this.password = password;
		this.maxConnections = maxConnections;
		this.maximumActiveSessionPerConnection = maximumActiveSessionPerConnection;
		this.threadPoolSize = threadPoolSize;
		init();
	}
	  
	private void init() {
		//设置JAVA线程池
		this.threadPool = Executors.newFixedThreadPool(this.threadPoolSize);
		//创建ActiveMQ的连接工厂
		ActiveMQConnectionFactory actualConnectionFactory = new ActiveMQConnectionFactory(this.userName, this.password, this.brokerUrl);
		//设置异步传输
		actualConnectionFactory.setUseAsyncSend(this.useAsyncSendForJMS);
		//Active中的连接池工厂
		this.connectionFactory = new PooledConnectionFactory(actualConnectionFactory);
		//初始化链接
		this.connectionFactory.setCreateConnectionOnStartup(true);
		//池中借出的对象的最大数目 
		this.connectionFactory.setMaxConnections(this.maxConnections);
		this.connectionFactory.setMaximumActiveSessionPerConnection(this.maximumActiveSessionPerConnection);
	}
	
	
	/**
	 * 执行发送消息的具体方法
	 * @param queue 队列名
	 * @param map	参数
	 * @param priority 优先级
	 */
	public void send(final String queue, final Map<String, Object> map,final int priority) {
		//直接使用线程池来执行具体的调用
		this.threadPool.execute(new Runnable(){
			public void run() {
				try {
					sendMsg(queue,map,priority);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 真正的执行消息发送
	 * @param queue 队列名字
	 * @param map
	 * @throws Exception
	 */
	private void sendMsg(String queue, Map<String, Object> map,int priority) throws Exception {
		Connection connection = null;
		Session session = null;
		try {
			//从连接池工厂中获取一个连接
			connection = this.connectionFactory.createConnection();
			/*
			 *  false 参数表示 为非事务型消息，后面的参数表示消息的确认类型
					Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。jms服务器自动删除消息
					Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。即消费者 message.acknowledge();
					DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
						此选项指示 Session 不必确保对传送消息的签收。它可能引起消息的重复，但Session 的开销，所以只有客户端能容忍重复的消息，才可使用
				true 表示为事务型消息  设置为true时，消息的确认类型被jms服务器设置为SESSION_TRANSACTED 。
				在带事务的 Session 中，签收自动发生在事务提交时。如果事务回滚，所有已经接收的消息将会被再次传送。
				在不带事务的Session 中，一条消息何时和如何被签收取决于Session 的设置
				*/
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//PTP消息方式 ，指定消息队列    
			Destination destination = session.createQueue(queue);
			//创建生产者
			MessageProducer producer = session.createProducer(destination);
			//消息传送模式设置为持久化（默认）
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			//map convert to javax message
			Message message = getMessage(session, map);
			if(priority<0||priority>9)
				priority=4;
			producer.setPriority(priority);
			producer.send(message);
		} finally {
//			closeSession(session);
//			closeConnection(connection);
		}
	}
	
	
	private Message getMessage(Session session, Map<String, Object> map) throws JMSException {
		MapMessage message = session.createMapMessage();
		if (map != null && !map.isEmpty()) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				message.setObject(key, map.get(key));
			}
		}
		return message;
	}
	
	
	/**
	 * 执行发送消息的具体方法
	 * @param queue 队列名
	 * @param carInfo	参数
	 * @param priority 优先级
	 */
	public void send(final String queue, final CarInfo carInfo,final int priority) {
		//直接使用线程池来执行具体的调用
		this.threadPool.execute(new Runnable(){
			public void run() {
				try {
					logger.info("method sendMsg START!", carInfo.toString());
					sendMsg(queue,carInfo,priority);
					logger.info("method sendMsg END!", "SUCCESS");
				} catch (Exception e) {
					//线程都不允许抛出未捕获的checked exception
					logger.error("method sendMsg ERROR!",carInfo.toString() , e);
				}
			}
		});
	}
	
	
	/**
	 * 真正的执行消息发送
	 * @param queue 队列名字
	 * @param carInfo
	 * @throws Exception
	 */
	private void sendMsg(String queue, CarInfo carInfo,int priority) throws Exception {
		Connection connection = null;
		Session session = null;
		//从连接池工厂中获取一个连接
		connection = this.connectionFactory.createConnection();
		/*
		 *  false 参数表示 为非事务型消息，后面的参数表示消息的确认类型
				Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。jms服务器自动删除消息
				Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。即消费者 message.acknowledge();
				DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
					此选项指示 Session 不必确保对传送消息的签收。它可能引起消息的重复，但Session 的开销，所以只有客户端能容忍重复的消息，才可使用
			true 表示为事务型消息  设置为true时，消息的确认类型被jms服务器设置为SESSION_TRANSACTED 。
			在带事务的 Session 中，签收自动发生在事务提交时。如果事务回滚，所有已经接收的消息将会被再次传送。
			在不带事务的Session 中，一条消息何时和如何被签收取决于Session 的设置
			*/
		session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		//PTP消息方式 ，指定消息队列    
		Destination destination = session.createQueue(queue);
		//创建生产者
		MessageProducer producer = session.createProducer(destination);
		//消息传送模式设置为持久化（默认）
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		if(carInfo==null)
			carInfo=new CarInfo();
		Message message = getObjectMessage(session, carInfo);
		if(priority<0||priority>9)
			priority=4;
		producer.setPriority(priority);
		producer.send(message);
	}
	
	/**
	 * 执行发送消息的具体方法
	 * @param queue 队列名
	 * @param carLocation	参数
	 * @param priority 优先级
	 */
	public void send(final String queue, final CarLocation carLocation,final int priority) {
		//直接使用线程池来执行具体的调用
		this.threadPool.execute(new Runnable(){
			public void run() {
				try {
					logger.info("method sendMsg START!", carLocation.toString());
					sendMsg(queue,carLocation,priority);
					logger.info("method sendMsg END!", "SUCCESS");
				} catch (Exception e) {
					//线程都不允许抛出未捕获的checked exception
					logger.error("method sendMsg ERROR!",carLocation.toString() , e);
				}
			}
		});
	}
	
	
	/**
	 * 真正的执行消息发送
	 * @param queue 队列名字
	 * @param carLocation
	 * @throws Exception
	 */
	private void sendMsg(String queue, CarLocation carLocation,int priority) throws Exception {
		Connection connection = null;
		Session session = null;
		//从连接池工厂中获取一个连接
		connection = this.connectionFactory.createConnection();
		/*
		 *  false 参数表示 为非事务型消息，后面的参数表示消息的确认类型
				Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。jms服务器自动删除消息
				Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。即消费者 message.acknowledge();
				DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
					此选项指示 Session 不必确保对传送消息的签收。它可能引起消息的重复，但Session 的开销，所以只有客户端能容忍重复的消息，才可使用
			true 表示为事务型消息  设置为true时，消息的确认类型被jms服务器设置为SESSION_TRANSACTED 。
			在带事务的 Session 中，签收自动发生在事务提交时。如果事务回滚，所有已经接收的消息将会被再次传送。
			在不带事务的Session 中，一条消息何时和如何被签收取决于Session 的设置
			*/
		session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		//PTP消息方式 ，指定消息队列    
		Destination destination = session.createQueue(queue);
		//创建生产者
		MessageProducer producer = session.createProducer(destination);
		//消息传送模式设置为持久化（默认）
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		if(carLocation==null)
			carLocation=new CarLocation();
		Message message = getObjectMessage(session, carLocation);
		if(priority<0||priority>9)
			priority=4;
		producer.setPriority(priority);
		producer.send(message);
	}
	
	public Message getObjectMessage(Session session,CarInfo carInfo) throws JMSException {
		ObjectMessage objMessage=session.createObjectMessage(carInfo);
		return objMessage;
	}
	
	public Message getObjectMessage(Session session,CarLocation carLocation) throws JMSException {
		ObjectMessage objMessage=session.createObjectMessage(carLocation);
		return objMessage;
	}
	
	public void onException(JMSException e) {
		e.printStackTrace();
	}

}
