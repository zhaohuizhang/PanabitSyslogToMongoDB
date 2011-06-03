/**
 * 
 */
package cn.edu.sjtu.front.drivers;

import cn.edu.sjtu.front.panabitsyslog.PanabitMsg;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsgParser;

// Phase 0: Receive UDP, write to a file
// Phase 1: Parese Msg
// Phase 2: Write msgPanabit to DB

/**
 * @author jianwen
 *
 */
public class StartRecSyslog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String strMsg = null;
		PanabitMsg msgPanabit = null;
		PanabitMsgParser parser = new PanabitMsgParser();
		// TODO: Open UDP Socket
		
		// LOOP
		// TODO: Read a Message(String) from UDP
		// TODO: Parse the Message
		msgPanabit = parser.parseMsg(strMsg);
		// TODO
		// LOOP END 
	}

}
