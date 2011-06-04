/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;

/**
 * @author jianwen,zhangzhaohui
 *
 */
public class PanabitMsgParserApp implements PanabitMsgParserInf {

	@Override
	public PanabitMsg parse(String msg) {
		// TODO Auto-generated method stub
		//the udp parse,  operation of string
		String ss[]=msg.split(" ");
		PanabitMsgApp.setAppType(msg.substring(6,msg.indexOf(".")));
		PanabitMsgApp.setConnType(msg.substring(msg.indexOf(".")+1, msg.indexOf(" ")));
		String a=ss[1];
		PanabitMsgApp.setStartTime(Integer.parseInt(a.substring(0,a.indexOf("-") )));
		PanabitMsgApp.setEndTime(Integer.parseInt(a.substring(a.indexOf("-")+1)));
		String b=ss[2];
		String srcip=b.substring(0,b.indexOf(":"));
        PanabitMsgApp.setSrcIpv4(iptolong(srcip));
        
        PanabitMsgApp.setSrcPort(Integer.parseInt(b.substring(b.indexOf(":")+1,b.indexOf("-"))));
		String c[]=b.split("-");
		String d=c[1];
		String desip=d.substring(0,d.indexOf(":"));
		PanabitMsgApp.setDstIpv4(iptolong(desip));
		
		PanabitMsgApp.setDstPort(Integer.parseInt(d.substring(d.indexOf(":")+1)));
		PanabitMsgApp.setInByte(Integer.parseInt(ss[3]));
		PanabitMsgApp.setOutByte(Integer.parseInt(ss[4]));
		
		
		return null;
	}
	//ip convert to long 
    public long iptolong(String t){
    	String[] ips = t.split("[.]");  
        long num =   16777216L*Long.parseLong(ips[0]) + 65536L*Long.parseLong(ips[1]) + 256*Long.parseLong(ips[2]) + Long.parseLong(ips[3]);  
    	return num;
    }


}
