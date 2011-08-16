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
import com.mongodb.Mongo;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsg;
import cn.edu.sjtu.front.panabitsyslog.PanabitProcessor;

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
		String dbCollectName = "panabit_" + today;
		System.out.println("Collection Name: " + dbCollectName);
		// MongoDB Connection, DB, Collections
		Mongo mongo = new Mongo(mongoIp, mongoPort);
		DB db = mongo.getDB(dbDatabaseName);
		DBCollection panabitsyslogs = db.getCollection(dbCollectName);
		
		// Prepare the Panabit Message Processor
		PanabitProcessor panabitProcessor = new PanabitProcessor();

		try {
			DatagramSocket receiveScoket = new DatagramSocket(Sysconf.SYSLOGPORT);
			byte buf[] = new byte[Sysconf.UDPBUFSIZE];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("Start to Receive Syslog Packet.");
			
			while (true) {
				receiveScoket.receive(receivePacket);
				String strPanabitMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());

				// 'Process the Panabit Message String'
				msgPanabit = panabitProcessor.parseMsg(strPanabitMsg);

				if (msgPanabit != null) {
					panabitsyslogs.insert(msgPanabit.toMongoDBObj());
				}

			}

		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

}
