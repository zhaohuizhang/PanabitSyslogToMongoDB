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
        // Read MongoDB Database Name From Command Line
        String dbName = args[2];
        // Read MongoDB collection Name From Command Line
        String collectionName = args[3];
        // User name
        String username = args[4];
        // User pwd
        String password = args[5];
        // TODO: DELETE
        // MongoDB Connection Name: panabit_today
		// SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
		// String dbCollectName = "panabit_" + today.format(new Date());
		// MongoDB Connection, DB, Collections
		Mongo mongo = new Mongo(mongoIp, mongoPort);
		DB dbPanabit = mongo.getDB(dbName);
		boolean loginSuccess=dbPanabit.authenticate(username, password.toCharArray());  
        if(!loginSuccess){  
            try {
				System.out.println("登录"+dbName+"验证失败,请确认用户名和密码");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }  
		DBCollection mongoConn = dbPanabit.getCollection(collectionName);
		
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
				PanabitMsg msgPanabit = panabitProcessor.parseMsg(strPanabitMsg);

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
