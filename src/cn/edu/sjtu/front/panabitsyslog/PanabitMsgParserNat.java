package cn.edu.sjtu.front.panabitsyslog;

import java.io.IOException;

import cn.edu.sjtu.front.iputils.IpConvert;
import cn.edu.sjtu.front.iputils.IpMatcher;
import cn.edu.sjtu.front.iputils.IpMatcherLst;

public class PanabitMsgParserNat  implements PanabitMsgParserInf{

	public IpMatcher ipMatcher;
	
	public PanabitMsg parse(String msg) {
		// TODO Auto-generated method stub
		PanabitMsgNat panabitMsgNat = new PanabitMsgNat();
		String natMsg = msg.substring(0, msg.indexOf("\n"));
		String ssNat[] = natMsg.split(" ");
		panabitMsgNat.setNat(ssNat[0].substring(6));
		panabitMsgNat.setDetTime(Long.parseLong(ssNat[1]));
		panabitMsgNat.setShareIp(IpConvert.iptoInt(ssNat[2]));
		panabitMsgNat.setShareIpGroup(ipMatcher.ipMatch(IpConvert.iptoInt(ssNat[2])).netGroup);
		panabitMsgNat.setInterIp(IpConvert.iptoInt(ssNat[3]));
		return panabitMsgNat;
	}
	 public PanabitMsgParserNat(){
	    	try {
				ipMatcher = new IpMatcherLst();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
