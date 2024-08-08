package com.cathaybk.practice.nt50359.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLotto {

	public static void main(String[] args) {
		Random rand = new Random();
		List<Integer> randList = new ArrayList<>(); // List存set的數
		Set<Integer> setList = new HashSet<>(); // 用set放不重複六個數，沒有順序性
		while (setList.size() < 6) {
			int num = rand.nextInt(49) + 1;
			setList.add(num);
		}
		randList.addAll(setList); // 把setList的數字放進List中
		System.out.print("排序前：");

		for (int i = 0; i < 6; i++) {
			System.out.print(randList.get(i) + " ");
		}
		System.out.println();
		System.out.print("排序後：");
		Collections.sort(randList);
		for (int i = 0; i < 6; i++) {
			System.out.print(randList.get(i) + " ");
		}
	}

}
