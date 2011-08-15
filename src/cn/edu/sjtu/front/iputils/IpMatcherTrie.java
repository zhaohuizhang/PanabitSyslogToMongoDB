package cn.edu.sjtu.front.iputils;

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
		return ipMatch(v4NetAddr.netAddr);
	}

	
	public static void main(String[] args) throws IOException{
		
		IpMatcher matcherTrie = new IpMatcherTrie();
		IpMatcher matcherLst = new IpMatcherLst();
		Random rand = new Random();
		
		for (int i=0;i<100000;i++) {  
		   Long intNumber = rand.nextLong() & 0xFFFFFFFF;
	       int ipv4Addr = intNumber.intValue();
	       Ipv4NetAddr addrLst = matcherLst.ipMatch(ipv4Addr);
	       Ipv4NetAddr addrTrie = matcherTrie.ipMatch(ipv4Addr);
		   if(!addrLst.equals(addrTrie)) {	
			   System.out.println(ipv4Addr + "\nTrie Method:" + addrTrie + "\n" + "Lst Method:" + addrLst);
			   return;
		   }  
		}
		System.out.println("Trie and Lst match successfully.");	
	}

}