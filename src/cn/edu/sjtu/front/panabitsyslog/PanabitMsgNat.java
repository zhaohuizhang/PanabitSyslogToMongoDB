package cn.edu.sjtu.front.panabitsyslog;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PanabitMsgNat extends PanabitMsg {
	
	
	private String nat;
	private long detTime;
	private int shareIp;
	private int shareIpGroup;
	private int interIp;
	

	public int getShareIpGroup() {
		return shareIpGroup;
	}

	public void setShareIpGroup(int shareIpGroup) {
		this.shareIpGroup = shareIpGroup;
	}

	public String getNat() {
		return nat;
	}

	public void setNat(String nat) {
		this.nat = nat;
	}

	public long getDetTime() {
		return detTime;
	}

	public void setDetTime(long detTime) {
		this.detTime = detTime;
	}

	public int getShareIp() {
		return shareIp;
	}

	public void setShareIp(int shareIp) {
		this.shareIp = shareIp;
	}

	public int getInterIp() {
		return interIp;
	}

	public void setInterIp(int interIp) {
		this.interIp = interIp;
	}


	public String toString() {
		// TODO Auto-generated method stub
		String s = "Nat："+this.getNat()+"DetectTime："+this.getDetTime()+"ShareIp："+this.getShareIp()+"ShareIpGroup："+this.getShareIpGroup()+"InterIp："+this.getInterIp();
		return s;
	}

	public DBObject toMongoDBObj() {
		// TODO Auto-generated method stub
		DBObject panabitMsgNat = new BasicDBObject();
		panabitMsgNat.put("Nat", this.getNat());
		panabitMsgNat.put("DetTime", this.getDetTime());
		panabitMsgNat.put("ShareIp", this.getShareIp());
		panabitMsgNat.put("ShareIpGroup", this.getShareIpGroup());
		panabitMsgNat.put("InterIp", this.getInterIp());
		
		return panabitMsgNat;
	}

	
	public long getEndTime() {
		// TODO Auto-generated method stub
		
		return this.getDetTime();
	}

}
