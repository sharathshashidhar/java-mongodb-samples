
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
  *@sshashid
**/

@WebServlet("/GetAlertServlet")
public class GetAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DB db = null;
	DBCollection coll = null;

	public GetAlertServlet() {
		super();
	}

	public void init(ServletConfig config)
	{
		try{   
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			db = mongoClient.getDB( "test" );
			System.out.println("Connect to database successfully");
			//Set<String> coll = db.getCollectionNames();
			//System.out.println("Collections: "+coll);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO - Send the response to jQuery/Angular to render the same in the UI.
	}

	private void getLatestDocument(){
		try{   
			DBCollection coll = db.getCollection("alerts");
			DBCursor latestAlert = coll.find().sort(new BasicDBObject("$natural", -1)).limit(1);
			while (latestAlert.hasNext()) {
				System.out.println(latestAlert.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws ServletException{
		GetAlertServlet a = new GetAlertServlet();
		ServletConfig config =null;
		a.init(config );
		a.getLatestDocument();
	}

}
