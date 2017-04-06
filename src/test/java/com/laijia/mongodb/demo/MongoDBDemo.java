package com.laijia.mongodb.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;

public class MongoDBDemo{
	// 连接到 mongodb 服务
	static 	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

	// 连接到数据库
	static 	MongoDatabase mongoDatabase = mongoClient.getDatabase("test"); 
	
	public static void find() {
		try {
			MongoCollection<Document> collection=mongoDatabase.getCollection("col");
			BasicDBObject condition=new BasicDBObject();
			//gt查询
			condition.append("likes", new BasicDBObject("$gt", 100));		
			printResult(collection, condition);
			System.out.println("-----------------------$gt-------------------------");
			//where查询
			String function = "function (){return parseFloat(this.likes) > 100 && parseFloat(this.likes) < 200};";
			condition = new BasicDBObject();
			condition.put("$where",function);
			printResult(collection, condition);
			System.out.println("----------------------$where--------------------------");
			//模糊匹配 正则表达式
			FindIterable<Document> findIterable = collection.find(Filters.regex("title", "^Mongo.*$"));
			MongoCursor<Document> mongoCursor=findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(JSON.serialize(mongoCursor.next()));
			}
			System.out.println("-------------------^Mongo.*$-----------------------------");
			//eq
			condition = new BasicDBObject();
			condition.append("likes", new BasicDBObject("$eq", 100));
			printResult(collection, condition);
			System.out.println("---------------------$eq---------------------------");
		} catch (Exception e) {
		}
	}
	private static void printResult(MongoCollection<Document> collection,
			BasicDBObject condition) {
		FindIterable<Document> findIterable = collection.find(condition);
		MongoCursor<Document> mongoCursor=findIterable.iterator();
		while (mongoCursor.hasNext()) {
			System.out.println(JSON.serialize(mongoCursor.next()));
		}
	}
	public static void page() {
		try {
			MongoCollection<Document> collection=mongoDatabase.getCollection("col");
			BasicDBObject sort=new BasicDBObject();
			// 1,表示正序； －1,表示倒序
			sort.put("title", 1);
			int start=0,pageSize=2;
			FindIterable<Document> findIterable =collection.find().sort(sort).skip(start).limit(pageSize);
			MongoCursor<Document> mongoCursor=findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(JSON.serialize(mongoCursor.next()));
			}
		} catch (Exception e) {
		}
	}
	public static void findAll(String collectionName){
		try{   
			MongoCollection<Document> collection=mongoDatabase.getCollection(collectionName);
			for (Document document : collection.find()) {
				System.out.println(JSON.serialize(document));
			}
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	public static void insert() {
		try {

			MongoCollection<Document> collection=mongoDatabase.getCollection("col");
			//插入文档  
			/** 
			 * 1. 创建文档 org.bson.Document 参数为key-value的格式 
			 * 2. 创建文档集合List<Document> 
			 * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
			 * */
			Document document = new Document("title", "MongoDB").  
					append("description", "database").  
					append("likes", 100).  
					append("by", "Fly");  
			List<Document> documents = new ArrayList<Document>();  
			documents.add(document);  
			collection.insertMany(documents);  
			System.out.println("文档插入成功");
		} catch (Exception e) {
		}
	}
	public static void update() {
		try {
			MongoCollection<Document> collection = mongoDatabase.getCollection("col");
			System.out.println("集合 test 选择成功");

			//更新文档   将文档中likes=100的文档修改为likes=200   
			collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
			//检索查看结果  
			FindIterable<Document> findIterable = collection.find();  
			MongoCursor<Document> mongoCursor = findIterable.iterator();  
			while(mongoCursor.hasNext()){  
				System.out.println(mongoCursor.next());  
			}  
		} catch (Exception e) {
		}
	}
	public static void convertJson() {
		Map<String, String> bean=new HashMap<String, String>();
		bean.put("name", "zhangsan");
		System.out.println(JSON.serialize(bean));
	}
	public static void showAllCollectionName() {
		MongoIterable<String> listCollectionNames = mongoDatabase.listCollectionNames();
		MongoCursor<String> iterator = listCollectionNames.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	public static void main( String args[] ){
//			page();
//		find();
//		insert();
		showAllCollectionName();
//		findAll("carLocation239");
	}
}
