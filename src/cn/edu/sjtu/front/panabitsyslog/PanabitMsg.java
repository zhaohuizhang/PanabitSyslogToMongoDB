/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog;

import com.mongodb.DBObject;

/**
 * @author jianwen
 *
 */
public abstract class PanabitMsg {

	public PanabitMsgEnum msgType;
	
	public abstract String toString();
	public abstract DBObject toMongoDBObj();
	public abstract long getEndTime();
}
