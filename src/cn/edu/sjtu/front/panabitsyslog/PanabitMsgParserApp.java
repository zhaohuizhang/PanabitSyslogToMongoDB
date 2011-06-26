/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;

import java.io.IOException;

import cn.edu.sjtu.front.iputils.IpConvert;
import cn.edu.sjtu.front.iputils.IpMatcher;
import cn.edu.sjtu.front.iputils.IpMatcherLst;
//import cn.edu.sjtu.front.iputils.IpMatcherLst;

/**
 * @author jianwen,zhangzhaohui
 *
 */
public class PanabitMsgParserApp implements PanabitMsgParserInf {

	public IpMatcher ipMatcher;
	
	    
	public PanabitMsgApp parse(String msg) {
		PanabitMsgApp panabitMsgApp = new PanabitMsgApp();
		
		// TODO Auto-generated method stub
		//the udp parse,  operation of string
		String ss[]=msg.split(" ");
		panabitMsgApp.setAppType(msg.substring(6,msg.indexOf(".")));
		panabitMsgApp.setConnType(msg.substring(msg.indexOf(".")+1, msg.indexOf(" ")));
		String a=ss[1];
		panabitMsgApp.setStartTime(Integer.parseInt(a.substring(0,a.indexOf("-") )));
		panabitMsgApp.setEndTime(Integer.parseInt(a.substring(a.indexOf("-")+1)));
		String b=ss[2];
		String srcip=b.substring(0,b.indexOf(":"));
		long longSrcIp = IpConvert.iptolong(srcip);
        panabitMsgApp.setSrcIpv4(longSrcIp);
//        insert srcgroup
//        panabitMsgApp.setSrcGroup(ipMatcher.ipMatch(longSrcIp).netGroup);
  
        panabitMsgApp.setSrcPort(Integer.parseInt(b.substring(b.indexOf(":")+1,b.indexOf("-"))));
		String c[]=b.split("-");
		String d=c[1];
		String desip=d.substring(0,d.indexOf(":"));
		long longDetIp = IpConvert.iptolong(desip);
		panabitMsgApp.setDstIpv4(longDetIp);
//		insert dstgroup
//		panabitMsgApp.setDstGroup(ipMatcher.ipMatch(longDetIp).netGroup);
		panabitMsgApp.setDstPort(Integer.parseInt(d.substring(d.indexOf(":")+1)));
		panabitMsgApp.setInByte(Integer.parseInt(ss[3]));
		panabitMsgApp.setOutByte(Integer.parseInt(ss[4]));
		
		return panabitMsgApp;
	}
	
	

    public PanabitMsgParserApp(){
    	try {
			ipMatcher = new IpMatcherLst();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
