/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;

import java.io.IOException;

import cn.edu.sjtu.front.iputils.IpConvert;
import cn.edu.sjtu.front.iputils.IpMatcher;
import cn.edu.sjtu.front.iputils.IpMatcherTrie;
//import cn.edu.sjtu.front.iputils.IpMatcherLst;

/**
 * @author jianwen,zhangzhaohui
 *
 */
public class PanabitMsgParserApp implements InfPanabitParser {

	// Longgest Prefix IP Matcher
	public IpMatcher ipMatcher;
	    
	public PanabitMsgApp parse(String msg) {
		PanabitMsgApp panabitMsgApp = new PanabitMsgApp();
		
		// TODO Auto-generated method stub
		String ssApp[]=msg.split(" ");
		panabitMsgApp.setAppType(msg.substring(6,msg.indexOf(".")));
		panabitMsgApp.setConnType(msg.substring(msg.indexOf(".")+1, msg.indexOf(" ")));
		String a=ssApp[1];
		// Added By wei.jianwen@gmail.com
		int _starttime = Integer.parseInt(a.substring(0,a.indexOf("-") ));
		_starttime = _starttime / 600 * 600;
		panabitMsgApp.setStartTime(_starttime);		
		panabitMsgApp.setEndTime(Integer.parseInt(a.substring(a.indexOf("-")+1)));
		String b=ssApp[2];
		String srcip=b.substring(0,b.indexOf(":"));
		int intSrcIp = IpConvert.iptoInt(srcip);
        panabitMsgApp.setSrcIpv4(intSrcIp);
        panabitMsgApp.setSrcGroup(ipMatcher.ipMatch(intSrcIp).netGroup);
  
        panabitMsgApp.setSrcPort(Integer.parseInt(b.substring(b.indexOf(":")+1,b.indexOf("-"))));
		String c[]=b.split("-");
		String d=c[1];
		String desip=d.substring(0,d.indexOf(":"));
		int intDetIp = IpConvert.iptoInt(desip);
		panabitMsgApp.setDstIpv4(intDetIp);

		panabitMsgApp.setDstGroup(ipMatcher.ipMatch(intDetIp).netGroup);
		
		panabitMsgApp.setDstPort(Integer.parseInt(d.substring(d.indexOf(":")+1)));
		panabitMsgApp.setInByte(Long.parseLong(ssApp[3]));
		panabitMsgApp.setOutByte(Long.parseLong(ssApp[4]));
		
		return panabitMsgApp;
	}
	
	

    public PanabitMsgParserApp(){
     	try {

    		ipMatcher = new IpMatcherTrie();
		} catch (IOException e) {
 			e.printStackTrace();
		}
    }

}
