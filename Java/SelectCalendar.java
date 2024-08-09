package com.cathaybk.practice.nt50359.b;

import java.util.Calendar;
import java.util.Scanner;

public class SelectCalendar {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("請輸入1-12的數字查詢今年的月份：");

		int month = sc.nextInt();
		System.out.println("       2024年" + month + "月");
		System.out.println("---------------------");
		System.out.println("日  一  二  三  四 五 六");
		System.out.println("=====================");

		Calendar c = Calendar.getInstance(); // 目前時間
		c.clear(); // 清除全部
		c.set(2024, month - 1, 1); // 月份從0開始，所以輸入的月份要-1

		int firstDayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1; // 該月第一天是星期幾(星期日是1)
		int daysOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH); // 該月有幾天

		for (int i = 0; i < firstDayOfWeek; i++) {
			System.out.print("   "); // 印出月曆第一行的空格8
		}

		for (int i = 1; i <= daysOfMonth; i++) {
			System.out.printf("%2d ",i);

			if ((firstDayOfWeek + i) % 7 == 0) {
				System.out.println();
			}
		}
		sc.close();

	}

}
