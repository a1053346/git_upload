package com.cathaybk.practice.nt50359.b;

import java.util.Calendar;
import java.util.Scanner;

public class SelectCalendar {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("請輸入1-12的數字查詢今年的月份：");
			int month = sc.nextInt();
			if (month < 1 || month > 12) { // 月份檢核
				System.err.println("輸入有誤，請重新輸入");
			} else {
				Calendar calendar = Calendar.getInstance(); // 目前時間
				int year = calendar.get(Calendar.YEAR);

				StringBuilder sb = new StringBuilder();
				sb.append(String.format("%10s", year)).append("年").append(month).append("月");
				System.out.println(sb.toString());
				System.out.println("---------------------");
				System.out.println("日  一  二  三  四 五 六");
				System.out.println("=====================");

				calendar.set(year, month - 1, 1); // 月份從0開始，所以輸入的月份要-1 

				int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 該月第一天是星期幾(星期日是1)
				int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 該月有幾天

				for (int i = 0; i < firstDayOfWeek; i++) {
					System.out.print("   "); // 印出月曆第一行的空格
				}

				for (int i = 1; i <= daysOfMonth; i++) {
					System.out.printf("%2d ", i);

					if ((firstDayOfWeek + i) % 7 == 0) {
						System.out.println();
					}
				}
			}

		}

	}

}
