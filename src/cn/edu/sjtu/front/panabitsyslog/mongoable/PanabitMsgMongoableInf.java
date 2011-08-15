/**
 * 
 */
package cn.edu.sjtu.front.panabitsyslog.mongoable;

import com.mongodb.DBObject;

/**
 * @author jianwen
 *
 */
public interface PanabitMsgMongoableInf {

	DBObject toMongoDBObj();
	
}
