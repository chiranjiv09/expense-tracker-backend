package com.chiranjiv.expense.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConstantData {

	 public static final Map<Integer, Integer> monthExpenseMap = createMap();

	    private static Map<Integer, Integer> createMap() {
	        Map<Integer, Integer> map = new HashMap<>();

	        map.put(23, 1);
	        map.put(24, 2);
	        map.put(25, 3);
	        map.put(26, 4);
	        map.put(27, 5);
	        map.put(28, 6);
	        map.put(29, 7);
	        map.put(30, 8);
	        map.put(31, 9);
	        map.put(32, 10);
	        map.put(33, 11);
	        map.put(34, 12);

	        return Collections.unmodifiableMap(map);
	    }
}
