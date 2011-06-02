/**
 * 
 */
package cn.edu.sjtu.front.drivers;

import cn.edu.sjtu.front.panabitsyslog.PanabitMsg;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsgParser;

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
		// TODO: Open UDP Socket
		// LOOP
		// TODO: Read a Message(String) from UDP
		// TODO: Parse the Message
		msgPanabit = PanabitMsgParser.parseMsg(strMsg);
		// LOOP END
	}

}
