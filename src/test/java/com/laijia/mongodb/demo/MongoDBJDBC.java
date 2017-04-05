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
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;

public class MongoDBJDBC{
	// ���ӵ� mongodb ����
	static 	MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

	// ���ӵ����ݿ�
	static 	MongoDatabase mongoDatabase = mongoClient.getDatabase("test"); 
	
	public static void find() {
		try {
			MongoCollection<Document> collection=mongoDatabase.getCollection("col");
			BasicDBObject condition=new BasicDBObject();
			//gt��ѯ
			condition.append("likes", new BasicDBObject("$gt", 100));		
			printResult(collection, condition);
			System.out.println("-----------------------$gt-------------------------");
			//where��ѯ
			String function = "function (){return parseFloat(this.likes) > 100 && parseFloat(this.likes) < 200};";
			condition = new BasicDBObject();
			condition.put("$where",function);
			printResult(collection, condition);
			System.out.println("----------------------$where--------------------------");
			//ģ��ƥ�� ������ʽ
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
			// 1,��ʾ���� ��1,��ʾ����
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
	public static void findAll(){
		try{   
			MongoCollection<Document> collection=mongoDatabase.getCollection("col");
			for (Document document : collection.find()) {
				System.out.println(document.getString("title"));
			}
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	public static void insert() {
		try {

			MongoCollection<Document> collection=mongoDatabase.getCollection("col");
			//�����ĵ�  
			/** 
			 * 1. �����ĵ� org.bson.Document ����Ϊkey-value�ĸ�ʽ 
			 * 2. �����ĵ�����List<Document> 
			 * 3. ���ĵ����ϲ������ݿ⼯���� mongoCollection.insertMany(List<Document>) ���뵥���ĵ������� mongoCollection.insertOne(Document) 
			 * */
			Document document = new Document("title", "MongoDB").  
					append("description", "database").  
					append("likes", 100).  
					append("by", "Fly");  
			List<Document> documents = new ArrayList<Document>();  
			documents.add(document);  
			collection.insertMany(documents);  
			System.out.println("�ĵ�����ɹ�");
		} catch (Exception e) {
		}
	}
	public static void update() {
		try {
			MongoCollection<Document> collection = mongoDatabase.getCollection("col");
			System.out.println("���� test ѡ��ɹ�");

			//�����ĵ�   ���ĵ���likes=100���ĵ��޸�Ϊlikes=200   
			collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
			//�����鿴���  
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
	public static void main( String args[] ){
			page();
//		find();
//		insert();
	}
}