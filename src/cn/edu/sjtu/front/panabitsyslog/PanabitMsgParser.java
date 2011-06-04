/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;

/**
 * @author jianwen
 *
 */
public class PanabitMsgParser {

	public PanabitMsgParserInf parser;
	
	// Parser List
	public static PanabitMsgParserApp parserApp;
	// TODO: Other parsers, like PanabitMsgParserDNS, ...
	
	public PanabitMsg parseMsg(String msg){

		// 判断消息类型，调用响应的Parser
		
		// IF(msg_is_a_app_msg)
		parser = parserApp;
		
		// Call the parser.parse
		return parser.parse(msg);
		
	}
	
	public PanabitMsgParser() {
		// TODO: Initialize all the parsers if null
		if (parserApp == null)
			parserApp = new PanabitMsgParserApp();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
