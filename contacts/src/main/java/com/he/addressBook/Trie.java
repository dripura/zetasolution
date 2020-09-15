package com.he.addressBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;



public class Trie<E> {
	//possible characters used as keys
	final private String KEYS = "abcdefghijklmnopqrstuvwxyz ";
	
	//class describing each node of trie
	class TrieNode{
		HashMap<Character,TrieNode> child;
		List<E> contains;
		
		TrieNode(){
			this.child = new HashMap<>();
			this.contains = new ArrayList<>();
		}
		void addContact(E e) {
			this.contains.add(e);
		}	
	}
	
	//root element of the trie
	TrieNode root;
	
	public Trie() {
		this.root = new TrieNode();
	}
	
	// insert object E at node reacher from root using key
	public void insert(String key, E e) {
		key = key.toLowerCase();
		int len = key.length();
		
		TrieNode iterator = root;
		for(int i = 0;i < len; ++i) {
			
			TrieNode nextNode = iterator.child.get(key.charAt(i));
			if(nextNode == null) {
				
				nextNode = new TrieNode();
				iterator.child.put(key.charAt(i), nextNode);
			}
			
			iterator = nextNode;
			
			//isLast
			if(i == len - 1) {
				iterator.contains.add(e);
			}
		}
	}
	
	// returns list of contacts at node reached from root with key
	public List<E> look(String key){
		key = key.toLowerCase();
		List<E> list = new ArrayList<>();
		
		TrieNode iterator = root;
		for(char c: key.toCharArray()) {
			
			TrieNode nextNode = iterator.child.get(c);
			if(nextNode == null) {
				break;
			}
			iterator = nextNode;
		}
		list.addAll(iterator.contains);
		return list;
	}
	
	// search nodes with prefix key
	public List<E> search(String key){
		key = key.toLowerCase();
		List<E> list = new ArrayList<>();
		
		TrieNode iterator = root;
		for(char c: key.toCharArray()) {
			
			TrieNode nextNode = iterator.child.get(c);
			if(nextNode == null) {
				return list;
			}
			iterator = nextNode;
		}
		list.addAll(getAll(iterator));
		return list;
	}
	
	//get all objects in subtree of root
	List<E> getAll(TrieNode root){
		List<E> list = new ArrayList<>();
		Stack<TrieNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TrieNode node = stack.pop();
			list.addAll(node.contains);
			for(char c: KEYS.toCharArray()) {
				TrieNode temp = node.child.get(c);
				if(temp != null)
					stack.push(temp);
			}
		}
		return list;
	}
	
	public void delete(String key, E e) {
		key = key.toLowerCase();
		TrieNode iterator = root;
		for(char c: key.toCharArray()) {
			
			TrieNode nextNode = iterator.child.get(c);
			iterator = nextNode;
		}
		iterator.contains.remove(e);
	}
	
	
}

