package com.cathaybk.practice.nt50359.b;

public class NineNineTable {

	public static void main(String[] args) {
		/*
		 * (1) 由左至右顯示九九乘法乘積之格式為「被乘數 乘數 乘積」，被乘數固定不變，乘數為 1~9 之變動數值 
		 * (2)由上至下顯示九九乘法之乘積，乘數固定不變，被乘數為 2 ~9 之變動數值 
		 * (3) 顯示九九乘法表由上至下每一列須以「被乘數」對齊
		 */

		for (int multer = 1; multer < 10; multer++) { // 乘數
			for (int mult = 2; mult < 10; mult++) { // 被乘數
				System.out.print(mult + "*" + multer + "=" + String.format("%2d", mult * multer) + " ");
			}
			System.out.println();
		}

	}

}
