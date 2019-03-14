package com.system.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandCodeGenerator {	
	public static String generate(int count, Boolean withOand0){
		String[] beforeShuffle = new String[] {"1", "2", "3", "4", "5",
				"6", "7", "8", "9", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z"};
		String[] beforeShuffleWithOand0 = new String[] {"0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z"};
		List list = null;
		if(withOand0!= null && withOand0){
			list = Arrays.asList(beforeShuffleWithOand0);
		}else{
			list = Arrays.asList(beforeShuffle);
		}
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(0, count);
		return result;
	}
}
