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

		// judge the type and select Parser
		String b=msg.substring(6,msg.indexOf("."));
		String a="qqlogin,qqlogoff,msnlogin,dnsquery,pop3login,www,usrauth,ipnode,natip";
		if(a.indexOf(b)==-1){
			 PanabitMsgParserApp ip = new PanabitMsgParserApp(); 
			 return  ip.parse(msg);
			
		}else{
			// IF(msg_is_a_app_msg)
			
		}
		
		return null;
		// Call the parser.parse
		
		
	}
	
//	public PanabitMsgParser() {
//		// TODO: Initialize all the parsers if null
//		if (parserApp == null)
//			parserApp = new PanabitMsgParserApp();
//	}
//	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
