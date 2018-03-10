package com.lk.example;

import java.util.ArrayList;
import java.util.List;

public class ListSameItem {

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<Integer>();
		for (int i = 0; i < 7; i++) {
			list1.add(i);
		}
		List<Integer> list2 = new ArrayList<Integer>();
		for (int i = 3; i < 10; i++) {
			list2.add(i);
		}
		System.out.println("List1：" + list1);
		System.out.println("List2：" + list2);
		System.out.println("新的集合" + getIntersection(list1, list2));

	}

	public static List<Integer> getIntersection(List<Integer> list1, List<Integer> list2) {
		List<Integer> result = new ArrayList<Integer>();
		for (Integer integer : list2) { // 遍历list1
			if (list1.contains(integer)) { // 如果存在这个数
				result.add(integer); // 放进一个list里面，这个list就是交集
			}
		}

		for (Integer integer = list1.get(0); integer < list1.size(); integer++) {
			if (!result.contains(integer)) {
				list1.remove(integer);
			}
		}
		for (Integer integer = 0; integer < result.size(); integer++) {
			if (!list2.contains(integer)) {
				list1.add(integer);
			}
		}
		return list1;
	}

}
