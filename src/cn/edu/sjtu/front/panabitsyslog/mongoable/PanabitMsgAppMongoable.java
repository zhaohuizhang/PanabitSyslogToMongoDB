/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog.mongoable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import cn.edu.sjtu.front.panabitsyslog.PanabitMsg;
import cn.edu.sjtu.front.panabitsyslog.PanabitMsgApp;

/**
 * @author jianwen
 *
 */
public class PanabitMsgAppMongoable extends PanabitMsgApp implements InfPanabitMsgMongoable {

	/**
	 * 
	 */
	public PanabitMsgAppMongoable() {
		super();
	}

	public PanabitMsgAppMongoable(PanabitMsgApp msgApp) {
		super(msgApp);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public DBObject toMongoDBObj() {
		DBObject dbObj = new BasicDBObject();
		dbObj.put("app", this.getAppType());
		dbObj.put("protocol", this.getConnType());
		dbObj.put("starttime", (double)this.getStartTime());
		dbObj.put("endtime", (double)this.getEndTime());
		dbObj.put("srcip", (double)this.getSrcIpv4());
		dbObj.put("srcgroup", (double)this.getSrcGroup());
		dbObj.put("srcport", (double)this.getSrcPort());
		dbObj.put("dstcip", (double)this.getDstIpv4());
		dbObj.put("dstgroup", (double)this.getDstGroup());
		dbObj.put("dstport", (double)this.getDstPort());
		dbObj.put("inbyte", (double)this.getInByte());
		dbObj.put("outbyte", (double)this.getOutByte());
		return dbObj;
	}

}
