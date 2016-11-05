package com.vaani.ds.misc;

import java.util.*;

abstract class Element {
}

final class ListElement extends Element {
	private List<Element> list = new ArrayList<Element>();;

	public Element get(int index) {
		return list.get(index);
	}

	public void add(Element val) {
		list.add(val);
	}

	public int size() {
		return list.size();
	}
}

final class Int extends Element {
	private int val;

	public Int(int val) {
		this.val = val;
	}

	public int val() {
		return val;
	}
}


/**
 *	Calculate the weighted sum of the nested list.
 *
 *	Space complexity O(1), time complexity O(n).
 *
 */
public class NestedIntWeightedSum {

	public int nestedIntWeightedSum(Element elem) {
		int weight = 1;
		return weightedSum(elem, weight);	
	}

	private int weightedSum(Element elem, int weight) {
		if (elem instanceof Int) {
			return (weight - 1) * ((Int)elem).val();
		} else {
			ListElement list = (ListElement)elem;
			int res = 0;
			for (int i = 0; i < list.size(); ++i) {
				res += weightedSum(list.get(i), weight + 1); 
			}
			return res;
		}
	}

}
