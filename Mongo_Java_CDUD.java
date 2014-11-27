
import java.util.Set;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Mongo_Java_CDUD {
	DB db = null;
	DBCollection coll = null;

	public static void main(String[] args) {
		Mongo_Java_CDUD tm = new Mongo_Java_CDUD();
		tm.connect();
		tm.selectCollection("new_table");
		tm.getAllDocuments();
		tm.insertDocument();
		tm.updateDocument();
		tm.deleteDocument();
	}

	public void connect(){
		try{   
			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			// Now connect to your databases
			db = mongoClient.getDB( "test" );
			System.out.println("Connect to database successfully");
			Set<String> coll = db.getCollectionNames();
			System.out.println("Collections: "+coll);
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public void selectCollection(String collectionName){
		coll = db.getCollection(collectionName);
		System.out.println("Collection "+ collectionName + " selected successfully");
	}

	public void getAllDocuments(){
		try{   
			DBCursor cursor = coll.find();
			int i=1;
			while (cursor.hasNext()) { 
				System.out.println("Inserted Document: "+i);
				DBObject doc = cursor.next();
				System.out.println(doc.get("Name"));
				System.out.println(doc); 
				i++;
			}
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public void updateDocument(){
		try{   
			    DBCursor cursor = coll.find();
		        // while (cursor.hasNext()) { 
			    BasicDBObject newDocument = new BasicDBObject();
				newDocument.append("votes", 110);
		            BasicDBObject searchQuery = new BasicDBObject().append("title", "MongoDB");
		            coll.update(searchQuery, newDocument);
		         //}
		         System.out.println("Document updated successfully");
		         cursor = coll.find();
		         int i=1;
		         while (cursor.hasNext()) { 
		            System.out.println("Updated Document: "+i); 
		            System.out.println(cursor.next()); 
		            i++;
		         }
		      }catch(Exception e){
			     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  }

	}
	
	public void insertDocument(){
		 try{   
			
		         BasicDBObject doc = new BasicDBObject("title", "MongoDB").
		            append("description", "No SQL database").
		            append("votes", 100).
		            append("url", "http://www.google.com");
		         
		         coll.insert(doc);
		         System.out.println("Document inserted successfully");
		      }catch(Exception e){
			     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			  }
	}
	
	public void deleteDocument(){
		try{
			BasicDBObject doc = new BasicDBObject("title", "MongoDB");
			coll.remove(doc);
			System.out.println("Document deleted successfully");
		}catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	}

}
