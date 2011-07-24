/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;



/**
 * @author jianwen
 *
 */
public class PanabitMsgParser {

	// Parser List
	public static PanabitMsgParserApp parserApp;
	public static PanabitMsgParserNat parserNat;
	// The undetremine parser
	public PanabitMsgParserInf parser;
	
	// TODO: Other parsers, like PanabitMsgParserDNS, ...

	public PanabitMsgParser() {
		// The App Parser
		if (parserApp == null) 
			parserApp = new PanabitMsgParserApp();
		
		// The NAT Parser
		if (parserNat == null) 
			parserNat = new PanabitMsgParserNat();
	}
	
	
	public PanabitMsg parseMsg(String msg) {

		PanabitMsgParserInf msgParser = null;

		// judge the type and select Parser

		// MsgType: NAT
		if (msg.startsWith("<PNB1>natip")) {
			msgParser = PanabitMsgParser.parserNat;
		} else {
			// MsgType: Connection
			String msgHead = msg.substring(6, msg.indexOf("."));
			String excludeType = "qqlogin,qqlogoff,msnlogin,pop3login,www,usrauth,ipnode,natip";
			if (excludeType.indexOf(msgHead) == -1) {
				msgParser = PanabitMsgParser.parserApp;
			} else {
				// Unhandled Msg Type
				msgParser = null;
			}

		}
		
		// Call the parser, and return.
		if (msgParser != null) {
			return msgParser.parse(msg);
		} else {
			return null;
		}
		
		
	}
	
}
