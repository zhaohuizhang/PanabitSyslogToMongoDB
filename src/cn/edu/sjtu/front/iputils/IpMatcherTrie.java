﻿package cn.edu.sjtu.front.iputils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class IpMatcherTrie implements IpMatcher{
	class TrieNode{
		Ipv4NetAddr leafNode;
		TrieNode leftNode;
		TrieNode rightNode;
		
		TrieNode(){
			leafNode = null;
			leftNode = null;
			rightNode = null;
		}
		
		TrieNode(Ipv4NetAddr in){
			leafNode = null;
			leftNode = null;
			leafNode = new Ipv4NetAddr(in);
		}
	}
	
	public static  TrieNode root = null;
	
	public void insert(Ipv4NetAddr v4NetAddr){
		TrieNode curNode = root; 
		int netMask;
		for (int i = 1; i < v4NetAddr.maskLen; i++){
			netMask = ((int)(1)) << ((int)32-i);
			if ( (v4NetAddr.netAddr & netMask) == 0 ) {
				if (curNode.leftNode == null) 
					curNode.leftNode = new TrieNode();
				curNode = curNode.leftNode;
			}
			else {
				if (curNode.rightNode == null) 
					curNode.rightNode = new TrieNode();
				curNode = curNode.rightNode;
			}			
		}			
		netMask = ((int)(1)) << ((int)32-v4NetAddr.maskLen);
		if ( (v4NetAddr.netAddr & netMask) == 0 ) {
			if (curNode.leftNode == null) 
				curNode.leftNode = new TrieNode(v4NetAddr);
			else
				curNode.leftNode.leafNode = new Ipv4NetAddr(v4NetAddr);
		}
		else {
			if (curNode.rightNode == null)
				curNode.rightNode = new TrieNode(v4NetAddr);
			else
				curNode.rightNode.leafNode = new Ipv4NetAddr(v4NetAddr);
		}
	}
	
	public IpMatcherTrie() throws NumberFormatException, IOException{
		if (root == null) {
			root = new TrieNode();
			File filename = new File(IpMatcherConf.NETFILE); 
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String readIn;
			String[] subReadIn;
			Ipv4NetAddr tempToAdd = new Ipv4NetAddr();
			while((readIn = in.readLine()) != null){
				subReadIn = readIn.split(" ");
				tempToAdd = new Ipv4NetAddr();
				tempToAdd.netAddr = (int) Long.parseLong(subReadIn[0].substring(2),16) & 0xFFFFFFFF;
				tempToAdd.maskLen = Integer.parseInt(subReadIn[1]);
				tempToAdd.netGroup = Integer.parseInt(subReadIn[2]);
				insert(tempToAdd);
			}
		}
	}
	
	public Ipv4NetAddr ipMatch(int ipv4Addr){
		Ipv4NetAddr result = null;
		int i = 1;
		int netMask;
		TrieNode curNode = root;
		while (curNode != null) {
			if (curNode.leafNode != null)
				result = new Ipv4NetAddr(curNode.leafNode);
			netMask = ((int)(1)) << ((int)32-i);
			if ( (ipv4Addr & netMask) == 0 )
				curNode = curNode.leftNode;
			else
				curNode = curNode.rightNode;
			i++;
		}
		return result;
	}
	
	@Override
	public Ipv4NetAddr ipMatch(Ipv4NetAddr v4NetAddr) {
		// TODO Auto-generated method stub
		return ipMatch(v4NetAddr.netAddr);
	}

	
	public static void main(String[] args) throws IOException{
		
		IpMatcher test1= new IpMatcherLst();
		IpMatcher  test2=new IpMatcherTrie();
		int i;
		for (i=0;i<100000;i++)
		{  
		   Random random=new Random();//创建Random对象
		   Long intNumber=random.nextLong()&0xFFFFFFFF;//获取一个整型数
	       int ipv4Addr=intNumber.intValue();//转换为32位
		   if(test1.ipMatch(ipv4Addr).equals(test2.ipMatch(ipv4Addr))!=true)
		   {	
			   System.out.println("false"); 
		   }  
		}
		if(i==100000)
        System.out.println("true") ;
	}
}
		/*System.out.println(Integer.toHexString(test.ipMatch(0x97491205).netAddr).toUpperCase());
		System.out.println(test.ipMatch(0x97491205).maskLen);
		System.out.println(test.ipMatch(0x97491205).netGroup);*/
	
 
		// TODO: IpMatchTrie & IpMatchLst veryfication
		// Generate a ramdom Long int (64bit) and cuts it to normal int(32-bit);
		// Match it with IpMatchLst 
		// Match it with IpMatchTrie
		// Check if they are "equal" with equals method
		// do it 100000 times, if fail to be equal, break it.
		// USE IpMather Interface