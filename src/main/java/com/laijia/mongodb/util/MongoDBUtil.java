package com.laijia.mongodb.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

	// 连接到数据库
	static 	MongoDatabase mongoDatabase ;
	static {
		String[] address = PropertiesUtil.getMongodbString("mongo.hostport").split(":");
		ServerAddress serverAddress=new ServerAddress(address[0] ,Integer.parseInt(address[1]));
		final MongoClientOptions.Builder builder=new MongoClientOptions.Builder();
		builder.connectTimeout(PropertiesUtil.getMongodbInteger("mongo.connectTimeout"));
		builder.maxWaitTime(PropertiesUtil.getMongodbInteger("mongo.maxWaitTime"));
		builder.connectionsPerHost(PropertiesUtil.getMongodbInteger("mongo.connectionsPerHost"));
		builder.socketTimeout(PropertiesUtil.getMongodbInteger("mongo.socketTimeout"));
		builder.threadsAllowedToBlockForConnectionMultiplier(PropertiesUtil.getMongodbInteger("mongo.threadsAllowedToBlockForConnectionMultiplier"));
		MongoClientOptions clientOptions=builder.build();
		// 连接到 mongodb 服务
		MongoClient mongoClient = new MongoClient( serverAddress , clientOptions );
		mongoDatabase = mongoClient.getDatabase("test");
	}
	private  MongoDBUtil() {
		
	}
	public static MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}
	
}
