/**
 * 
 */
package cn.edu.sjtu.front.drivers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import cn.edu.sjtu.front.panabitsyslog.PanabitMsg;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsgApp;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsgParser;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsgParserApp;

// Phase 0: Receive UDP, write to a file
// Phase 1: Parese Msg
// Phase 2: Print OR Write msgPanabit to DB

/**
 * @author jianwen,zhangzhaohui
 * 
 */
public class StartRecSyslog {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// create new file to store the udp packet
		// File test=new File("syslog.txt");
		// if(test.exists()){
		// System.out.print("this file does not exist");
		// }else{
		// System.out.print("this file  exist");
		// test.createNewFile();//create new file
		// }

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String strMsg = null;
		PanabitMsg msgPanabit = null;
		PanabitMsgParser panabitParser = new PanabitMsgParser();
		Mongo mongo = new Mongo("10.50.15.206");
		DB db = mongo.getDB("DBsyslog");
		DBCollection panabitsyslogs = db.getCollection("panabit_"
				+ df.format(new Date()));
		// DBCollection panabitCollection =
		// db.getCollection("panabit_20110706");
		// System.out.println(panabitCollection.find().count());
		try {
			DatagramSocket receiveScoket = new DatagramSocket(30514);
			byte buf[] = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("Start to Receive Syslog Packet.");
			// TOOD: Remove the file writing statements
			// BufferedWriter output = new BufferedWriter(new FileWriter(test));
			while (true) {
				receiveScoket.receive(receivePacket);
				char rawMsg[] = new String(receivePacket.getData())
						.toCharArray();
				// TOOD: Remove the console output the RAW UDP Message (for
				// Debug Only)
				// System.out.println(rawMsg);
				int udpSize = receivePacket.getLength();
				String strUDP = new String(rawMsg, 0, udpSize);
				// System.out.println(strUDP);
				msgPanabit = panabitParser.parseMsg(strUDP);

				if (msgPanabit != null) {
					System.out.println(msgPanabit);
					// System.out.println(msgPanabit.toMongoDBObj());
					panabitsyslogs.insert(msgPanabit.toMongoDBObj());
				}

			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// LOOP
		// TODO: Read a Message(String) from UDP
		// TODO: Parse the Message
		// msgPanabit = parser.parseMsg(strMsg);
		// TODO
		// LOOP END
	}

}
