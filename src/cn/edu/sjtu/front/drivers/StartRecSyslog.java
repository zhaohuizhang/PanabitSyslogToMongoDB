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
// Phase 2: Write msgPanabit to DB

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
		PanabitMsgParser parser = new PanabitMsgParser();
		// TODO: Open UDP Socket
		try {
			DatagramSocket receiveScoket=new DatagramSocket(30514);
			byte buf[]=new byte[100];
			DatagramPacket receivePacket=new DatagramPacket(buf,buf.length);
			System.out.println("startinig to receive packet");
//			BufferedWriter output = new BufferedWriter(new FileWriter(test));
			while(true){
				receiveScoket.receive(receivePacket);
				//check out the udppacket
				char s[]=new String(receivePacket.getData()).toCharArray();
				System.out.println(s);
				int udpSize=receivePacket.getLength();
				String strUDP=new String(s, 0, udpSize);
				//write into txtfloder
//			    output.write(strUDP+"\r\n");
				System.out.println(strUDP+"\r\n");
			    //udp parse
			    PanabitMsgParser PMP=new  PanabitMsgParser();
			    PMP.parseMsg(strUDP);
			    //TODO delete
//			    PanabitMsgParserApp ip = new PanabitMsgParserApp(); 
//			    ip.parse(strUDP);
			    //the solution of parse write into txtfloder
			    PanabitMsgApp panbitmsgapp = new PanabitMsgApp();
//			    output.write(panbitmsgapp.toString()+"\r\n");
			    System.out.println(panbitmsgapp.toString()+"\r\n");
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
