package com.laijia.mongodb.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

	// ���ӵ����ݿ�
	static 	MongoDatabase mongoDatabase ;
	static {
		ServerAddress serverAddress=new ServerAddress("localhost" , 27017);
		final MongoClientOptions.Builder builder=new MongoClientOptions.Builder();
		builder.connectTimeout(PropertiesUtil.getMongodbInteger("mongo.connectTimeout"));
		builder.maxWaitTime(PropertiesUtil.getMongodbInteger("mongo.maxWaitTime"));
		builder.connectionsPerHost(PropertiesUtil.getMongodbInteger("mongo.connectionsPerHost"));
		builder.socketTimeout(PropertiesUtil.getMongodbInteger("mongo.socketTimeout"));
		builder.threadsAllowedToBlockForConnectionMultiplier(PropertiesUtil.getMongodbInteger("mongo.threadsAllowedToBlockForConnectionMultiplier"));
		MongoClientOptions clientOptions=builder.build();
		// ���ӵ� mongodb ����
		MongoClient mongoClient = new MongoClient( serverAddress , clientOptions );
		mongoDatabase = mongoClient.getDatabase("test");
	}
	private  MongoDBUtil() {
		
	}
	public static MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}
	
}
