package com.lk.example;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<Integer>();
		for (int i = 0; i < 7; i++) {
			list1.add(i);
		}
		List<Integer> list2 = new ArrayList<Integer>();
		for (int i = 3; i < 10; i++) {
			list2.add(i);
		}
		
		List<Integer> result = new ArrayList<Integer>();
		for (Integer integer : list2) { // 遍历list1
			if (list1.contains(integer)) { // 如果存在这个数
				result.add(integer); // 放进一个list里面，这个list就是交集
			}
		}

		for (Integer i =0, integer = list1.get(i); i < list1.size(); ) {
			if (!result.contains(integer)) {
				list1.remove(integer);
				
			}else {
				i++;
				
			}
		}
		
		/*for (Integer i =0, integer = list2.get(i); i < list2.size(); i++) {
			if (!result.contains(integer)) {
				list1.add(integer);
			}
		}*/
		
		System.out.println("list1="+list1);
		
	}	
}
