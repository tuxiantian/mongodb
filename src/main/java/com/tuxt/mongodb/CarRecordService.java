package com.tuxt.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class CarRecordService {
	
	static 	MongoClient mongoClient;
	// ���ӵ����ݿ�
	static 	MongoDatabase mongoDatabase ;
	static {
		ServerAddress serverAddress=new ServerAddress("localhost" , 27017);
		final MongoClientOptions.Builder builder=new MongoClientOptions.Builder();
		builder.connectTimeout(PropertiesUtil.getMongodbInteger("mongo.connectTimeout"));
		MongoClientOptions clientOptions=builder.build();
		// ���ӵ� mongodb ����
		mongoClient = new MongoClient( serverAddress , clientOptions );
		mongoDatabase = mongoClient.getDatabase("test"); 
	}
	

	
		
	public void save() {
		
	}
}
