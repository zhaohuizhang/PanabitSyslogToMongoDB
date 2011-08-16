/**
 * 
 */
package cn.edu.sjtu.front.drivers;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.BasicDBObject;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsg;
import cn.edu.sjtu.front.panabitsyslog.PanabitProcessor;
import cn.edu.sjtu.front.panabitsyslog.mongoable.InfPanabitMsgMongoable;
import cn.edu.sjtu.front.panabitsyslog.mongoable.PanabitMsgMongoDbFactory;

/**
 * @author jianwen,zhangzhaohui
 */
public class StartRecSyslog {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Read MongoDB IP from Command Line
        String mongoIp = args[0];
        // Read MongoDB Connecting Port From Command Line
        int mongoPort = Integer.parseInt(args[1]);
        
        /* Prepare MongoDB Connection */
        PanabitMsg msgPanabit = null;
        // MongoDB Database Name
        String dbDatabaseName = "dbpanabit";
        // MongoDB Connection Name: panabit_today
		SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		String dbCollectName = "panabit_" + today.format(new Date());
		// MongoDB Connection, DB, Collections
		Mongo mongo = new Mongo(mongoIp, mongoPort);
		DB dbPanabit = mongo.getDB(dbDatabaseName);
		DBCollection mongoConn = dbPanabit.getCollection(dbCollectName);
		
		// Prepare the Panabit Message Processor
		PanabitProcessor panabitProcessor = new PanabitProcessor();
		
		try {
			DatagramSocket receiveScoket = new DatagramSocket(Sysconf.SYSLOGPORT);
			byte buf[] = new byte[Sysconf.UDPBUFSIZE];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("Listening on port " + Sysconf.SYSLOGPORT + "..." + "Receiving Packets.");
			
			while (true) {
				receiveScoket.receive(receivePacket);
				String strPanabitMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());

				// 'Process the Panabit Message String'
				msgPanabit = panabitProcessor.parseMsg(strPanabitMsg);

				InfPanabitMsgMongoable msgMongoable = null;
				DBObject dbObj = null;
				
				// Convert a normal panabit message to 'MongoDBable' object
				if (msgPanabit != null) 
					msgMongoable = PanabitMsgMongoDbFactory.MsgToMongo(msgPanabit);
					
				// Generate a MongoDB Object from a 'MongoDBable' object
				if (msgMongoable != null)
					dbObj = msgMongoable.toMongoDBObj();
				
				// Insert MongoDB Object to MongoDB
				if (dbObj != null)
					mongoConn.insert(dbObj);
			}

		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

}
