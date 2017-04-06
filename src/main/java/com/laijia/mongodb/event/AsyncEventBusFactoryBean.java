package com.laijia.mongodb.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.laijia.mongodb.listener.EventListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Collection;
import java.util.concurrent.Executors;

public class AsyncEventBusFactoryBean implements FactoryBean<EventBus>, ApplicationListener<ContextRefreshedEvent>, InitializingBean {

    private static final Logger logger = Logger.getLogger(AsyncEventBusFactoryBean.class);

    private Collection<EventListener> listeners;

    private AsyncEventBus eventBus;

    public void onApplicationEvent(ContextRefreshedEvent event) {
//		if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
        listeners = event.getApplicationContext().getBeansOfType(EventListener.class).values();
        for (EventListener listener : listeners) {
            logger.info("event bus register listener:" + listener.getClass().getName());
            eventBus.register(listener);
        }
//		}
    }

    public void afterPropertiesSet() throws Exception {
        eventBus = new AsyncEventBus("service", Executors.newFixedThreadPool(20));
    }

    public EventBus getObject() throws Exception {
        return eventBus;
    }

    public Class<?> getObjectType() {
        return EventBus.class;
    }

    public boolean isSingleton() {
        return true;
    }
}