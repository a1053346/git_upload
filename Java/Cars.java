package com.cathaybk.practice.nt50359.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cars {

	public static void main(String[] args) {
		// 換行符號
		String lineSeparator = System.lineSeparator();
		List<String> keyList = null; // 欄位名的list
		List<Map<String, String>> resultList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Admin\\Desktop\\Java班\\git_upload\\Java\\cars.csv"))) {

			String line;
			boolean isFirst = true; // 第一次要執行，第二次後改為false

			while ((line = br.readLine()) != null) { // 把每一行寫入string
				Map<String, String> map = new HashMap<>();
				if (isFirst) { // 第一行
					keyList = Arrays.asList(line.split(",")); // 第一行是欄位名，另存起來，進下一行
					isFirst = false; // 註記第一行結束
				} else { // 第二行起，每一行放進map {欄位名,值}
					String[] valueArr = line.split(","); // 取得值
					// 遍歷key,建置map
					for (int i = 0; i < keyList.size(); i++) {
						map.put(keyList.get(i), valueArr[i]);
					}
//					System.out.println(map);
					resultList.add(map); // map放進list
				}

			}

			// 用comparator排序price降冪
			Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> m1, Map<String, String> m2) {
					return -(m1.get("Price").compareTo(m2.get("Price"))); // 用1 0 -1去比前後
				}
			};
			// 完成排序
			Collections.sort(resultList, mapComparator);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// trycatch 寫檔
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:\\Users\\Admin\\Desktop\\Java班\\git_upload\\Java\\cars2.csv")))) {
			// 把list的值取出來，先寫第一行欄位，再寫後面每一行
			StringBuilder sb = new StringBuilder();

			// keyA,keyB,keyC + nextLine
			sb.append(String.join(",", keyList)); // 逗號插入
			sb.append(lineSeparator); // nextLine

			// valueA,valueB,valueC + nextLine
			for (Map<String, String> thisData : resultList) {
				// 每筆資料 for get value
				StringBuilder lineSb = new StringBuilder();
				// lineSb = ,valueA,valueB,valueC
				for (String thisKey : keyList) { // 依照ketList順序，寫入value
					String thisValue = thisData.get(thisKey);
					lineSb.append(",").append(thisValue);
				}
				lineSb = lineSb.deleteCharAt(0); // remove first ","
				// 放入sb
				sb.append(lineSb.toString());
				sb.append(lineSeparator);

			}
//			System.out.println(sb);
			// 寫檔匯出
			bw.write(sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Manufacturer欄位分組
		StringBuilder sbTotal = new StringBuilder();
		// 用comparator排序
		Comparator<Map<String, String>> manuComparator = new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> m1, Map<String, String> m2) {
				return m1.get("Manufacturer").compareTo(m2.get("Manufacturer"));
			}
		};

		// 完成排序
		Collections.sort(resultList, manuComparator);

		// 印出第一行欄位
		for (String strKey : keyList) {
			sbTotal.append(strKey + " ");
		}
		sbTotal.append(lineSeparator);
		// 印出每一筆資料
		String preManufacturer = null; // 跟前一個檢查
		BigDecimal minPriceSum = BigDecimal.ZERO; // 現在Min.Price小計
		BigDecimal priceSum = BigDecimal.ZERO; // 現在Price小計
		BigDecimal minPriceTotal = BigDecimal.ZERO; // 現在Min.Price總和
		BigDecimal priceTotal = BigDecimal.ZERO; // 現在Price總和
		for (Map<String, String> thisData : resultList) {
			String manufacturer = thisData.get("Manufacturer");
			BigDecimal minPrice = new BigDecimal(thisData.get("Min.Price"));
			BigDecimal price = new BigDecimal(thisData.get("Price"));

			// 檢查上一筆 preManufacturer 是否不同
			if (preManufacturer != null && !preManufacturer.equals(manufacturer)) {
				// 如果 Manufacturer 值變了，印出上個 Manufacturer 小計
				sbTotal.append("小計         ").append(minPriceSum).append(" ").append(priceSum).append(lineSeparator);
				// 歸零
				minPriceSum = BigDecimal.ZERO;
				priceSum = BigDecimal.ZERO;
			}
			// 放入這次 Manufacturer
			preManufacturer = manufacturer;

			// 加入小計
			minPriceSum = minPriceSum.add(minPrice);
			priceSum = priceSum.add(price);
			// 加總和
			minPriceTotal = minPriceTotal.add(minPrice);
			priceTotal = priceTotal.add(price);

			// 每筆資料 for get value
			for (String thisKey : keyList) { // 依照keyList順序，寫入value
				String thisValue = thisData.get(thisKey);
				sbTotal.append(thisValue).append(" ");
			}
			sbTotal.append(lineSeparator);
		}

		// 印出最後一筆小計
		sbTotal.append("小計         ").append(minPriceSum).append(" ").append(priceSum).append(lineSeparator);
		// 印出總和
		sbTotal.append("總計         ").append(minPriceTotal).append(" ").append(priceTotal).append(lineSeparator);

		System.out.print(sbTotal.toString());

	}

}