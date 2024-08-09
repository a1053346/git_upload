package com.cathaybk.practice.nt50359.b;

public class NineNineTable {

	public static void main(String[] args) {
		/*
		 * (1) 由左至右顯示九九乘法乘積之格式為「被乘數 乘數 乘積」，被乘數固定不變，乘數為 1~9 之變動數值
		 * (2)由上至下顯示九九乘法之乘積，乘數固定不變，被乘數為 2 ~9 之變動數值 (3) 顯示九九乘法表由上至下每一列須以「被乘數」對齊
		 */

		for (int j = 1; j < 10; j++) { // 乘數 for迴圈用簡單變數就好
			for (int i = 2; i < 10; i++) { // 被乘數
				System.out.printf(i + "%s" + j + "%s %2d" + "\t", "*", "=", i * j);
			}
			System.out.println();
		}

	}

}
