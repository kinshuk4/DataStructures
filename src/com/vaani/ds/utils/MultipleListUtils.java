package com.vaani.ds.utils;

import com.vaani.ds.list.LinkedList;
import com.vaani.ds.list.ListNode;

public class MultipleListUtils {
	public static boolean compareLists(ListNode list1, ListNode list2)
	{
		if((list1==null ) && (list2==null)) { 
			return true;
		}else { 
			if(list1==null || list2==null) {
				return false; 
			}
			if(list1.data!=list2.data) {
				return false; 
			} else {
				return compareLists(list1.next,list2.next);
			} 
		}
	}
}


