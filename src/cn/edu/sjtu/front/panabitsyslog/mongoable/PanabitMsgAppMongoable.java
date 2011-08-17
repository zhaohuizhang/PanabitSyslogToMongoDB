/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog.mongoable;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import cn.edu.sjtu.front.panabitsyslog.PanabitMsgApp;

/**
 * @author jianwen
 *
 */
public class PanabitMsgAppMongoable extends PanabitMsgApp implements InfPanabitMsgMongoable {

	public List<TrafficItem> trafficList;	// Traffic Item List
	
	class TrafficItem {
		int time;	// start of the record time, in 10min
		int inByte;
		int outByte;
		
		public TrafficItem(int _time, int _inByte, int _outByte) {
			time = _time;
			inByte = _inByte;
			outByte = _outByte;
		}
		
	}
	
	public PanabitMsgAppMongoable() {
		super();
		trafficList = new ArrayList<PanabitMsgAppMongoable.TrafficItem>();
	}

	
	public PanabitMsgAppMongoable(PanabitMsgApp msgApp) {
		super(msgApp);
		trafficList = new ArrayList<PanabitMsgAppMongoable.TrafficItem>();
		
		/*
		 * Generating Traffic Items
		 */
		final int TENMINUTE = 60 * 10;
		int _starttime = (int)this.startTime / TENMINUTE * TENMINUTE;
		int _endtime = (int)this.endTime / TENMINUTE * TENMINUTE + TENMINUTE;
		int _duration = (_endtime - _starttime) / TENMINUTE; // duration time in 10 minute
		int _inAvg = (int)this.inByte / _duration;
		int _outAvg = (int)this.outByte / _duration;
		
		for (int i = 0; i < _duration; i++) {
			TrafficItem tItem = new TrafficItem(_starttime + i * TENMINUTE, _inAvg, _outAvg);
			this.trafficList.add(tItem);
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	
	public DBObject toMongoDBObj() {
		DBObject dbObj = new BasicDBObject();
		dbObj.put("app", this.getAppType());
		dbObj.put("protocol", this.getConnType());

		/* Start Time to 10min */
		// dbObj.put("starttime", (double)this.getStartTime());
		int _starttime = (int)this.startTime;
		_starttime = _starttime / 600 * 600;
		dbObj.put("starttime", (double)_starttime);
		
		dbObj.put("endtime", (double)this.getEndTime());
		dbObj.put("srcip", (double)this.getSrcIpv4());
		dbObj.put("srcgroup", (double)this.getSrcGroup());
		dbObj.put("srcport", (double)this.getSrcPort());
		dbObj.put("dstcip", (double)this.getDstIpv4());
		dbObj.put("dstgroup", (double)this.getDstGroup());
		dbObj.put("dstport", (double)this.getDstPort());
		dbObj.put("inbyte", (double)this.getInByte());
		dbObj.put("outbyte", (double)this.getOutByte());
		
		/* Generating Item Lists */
		ArrayList<BasicDBObject>tDbObjList = new ArrayList<BasicDBObject>();
		for (TrafficItem tItem : this.trafficList) {
			BasicDBObject trafficObj = new BasicDBObject();
			trafficObj.put("time", (double)tItem.time);
			trafficObj.put("inByte", (double)tItem.inByte);
			trafficObj.put("outByte", (double)tItem.outByte);
			tDbObjList.add(trafficObj);
		}
		
		dbObj.put("traffic", tDbObjList);
		
		return dbObj;
	}

}
