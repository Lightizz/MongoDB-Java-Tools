package fr.lightiz.mongodbtoolsinjava;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DataTool {
	String mongoURI;
	MongoClient mongoClient;
	MongoDatabase mongoDataBase;
	MongoCollection<Document> collection;
	
	public DataTool(String mongoURI) {
		this.mongoURI = mongoURI;
		try { this.mongoClient = MongoClients.create(mongoURI);
		}catch(Exception e){ e.printStackTrace(); }
	}
	
	public MongoClient getMongoClient() { return mongoClient; }
	public void close() { mongoClient.close(); }
	
	public String getMongoURI() { return mongoURI; }
	public void setMongoURI(String mongoURI) {
		this.mongoURI = mongoURI;
	}
	
	public MongoDatabase getMongoDataBase(String name) { return mongoDataBase; }
	public void setMongoDataBase(String name) {
		this.mongoDataBase = mongoClient.getDatabase(name);
	}
	public void deleteMongoDataBase(String name) {
		try{ this.mongoClient.getDatabase(name).drop();
		}catch(Exception e) { e.printStackTrace(); }
	}
	
	public MongoCollection<Document> getCollection() { return this.collection; }
	public void setCollection(String name) {
		this.collection = mongoDataBase.getCollection(name);
	}
	public void deleteCollection(String name) {
		try{ this.mongoDataBase.getCollection(name).drop();
		}catch(Exception e) { e.printStackTrace(); }
	}
	
	public void addDocument(Document doc) {
		collection.insertOne(doc);
	}
	public void addDocuments(List<Document> docs) {
		collection.insertMany(docs);
	}
	
	public void createIndex(Document doc) { collection.createIndex(doc);}
	public void deleteOne(Document doc) { collection.deleteOne(doc); }
	public void deletemany(Document docs) { collection.deleteMany(docs); }
}
