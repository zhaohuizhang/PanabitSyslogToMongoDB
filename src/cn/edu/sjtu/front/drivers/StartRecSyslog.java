/**
 * 
 */
package cn.edu.sjtu.front.drivers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;

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
        //create new file to store the udp packet		
//		File test=new File("syslog.txt");
//		if(test.exists()){
//		    System.out.print("this file does not exist");
//		   }else{
//		    System.out.print("this file  exist");
//		    test.createNewFile();//create new file
//		   }
		
		String strMsg = null;
		PanabitMsg msgPanabit = null;
		PanabitMsgParser panabitParser = new PanabitMsgParser();

		try {
			DatagramSocket receiveScoket = new DatagramSocket(30514);
			byte buf[] = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("Start to Receive Syslog Packet.");
			// TOOD: Remove the file writing statements
			// BufferedWriter output = new BufferedWriter(new FileWriter(test));
			while(true){
				receiveScoket.receive(receivePacket);
				char rawMsg[] = new String(receivePacket.getData()).toCharArray();
				// TOOD: Remove the console output the RAW UDP Message (for Debug Only)
				// System.out.println(rawMsg);
				int udpSize = receivePacket.getLength();
				String strUDP = new String(rawMsg, 0, udpSize);
				// TODO: Remove the console output
				// System.out.println(strUDP+"\r\n");
			    panabitParser.parseMsg(strUDP);
			    
			    // TODO: DELETE the DEMO following code (by Jianwen)
			    // msgPanabit = panabitParser.parseMsg(strUDP);
			    // System.out.println(msgPanabit);
			    
			    //TODO delete
//			    PanabitMsgParserApp ip = new PanabitMsgParserApp(); 
//			    ip.parse(strUDP);
			    //the solution of parse write into txtfloder

			    // TODO: Change to a 'type-independent' style 
			    PanabitMsgApp panbitmsgapp = new PanabitMsgApp();
//			    output.write(panbitmsgapp.toString()+"\r\n");
			    
			    // TODO: NOT to call toString Explicitly
			    System.out.println(panbitmsgapp.toString() + "\r\n");
			    
			    // TODO: Parse the PanabitMsg Object into MongoDB 'String'
			    // TODO: Write into MongoDB
			    
			}
				
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// LOOP
		// TODO: Read a Message(String) from UDP
		// TODO: Parse the Message
//		msgPanabit = parser.parseMsg(strMsg);
		// TODO
		// LOOP END 
	}

}
