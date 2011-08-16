package cn.edu.sjtu.front.panabitsyslog.mongoable;

import cn.edu.sjtu.front.panabitsyslog.EnumPanabitMsg;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsg;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsgApp;

public class PanabitMsgMongoDbFactory {
	
	public static InfPanabitMsgMongoable MsgToMongo(PanabitMsg panabitMsg) {
		
		if (panabitMsg.msgType == EnumPanabitMsg.MSGAPP) {
			return new PanabitMsgAppMongoable((PanabitMsgApp)panabitMsg);
		} else {
			return null;
		}
		
		
	}
	
}
