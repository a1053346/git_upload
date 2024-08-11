package com.cathaybk.practice.nt50359.b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cars {

	public static void main(String[] args) {

		//讀檔
		//整理資料
			//讀每一行存成string
				//第一行是欄位名，另存起來，進下一行
				//第二行起，每一行放進map {欄位名,值}
				//map放進list
		//寫檔
		//匯出

		
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Admin\\Desktop\\Java班\\git_upload\\Java\\cars.csv"))) {

			List<Map<String, String>> resultList = new ArrayList<>();

			String line;
			boolean isFirst = true;	//第一次要執行，第二次後改為false
			List<String> keyList;	//欄位名的list
			while ((line = br.readLine()) != null) {	//把每一行寫入string
				System.out.println(line);
				
				if (isFirst) { // 第一行
					keyList = Arrays.asList(line.split(",")); //第一行是欄位名，另存起來，進下一行
					isFirst = false; // 註記第一行結束
				} else { // 第二行起，每一行放進map {欄位名,值}
					String[] valueArr = line.split(","); // 取得值

					// 遍歷key,建置map
					Map<String, String> map = new HashMap<>();
					for (int i=0;i<keyList.length;i++) {
						map.put(keyList.get(i), valueArr[i]);
					}					
				}
				resultList.add(map);	//map放進list
			}
			System.out.println(resultList);

			// 用comparator排序price
			public Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
    				@Override
				public int compare(Map<String, String> m1, Map<String, String> m2) {
        				return m1.get("Price").compareTo(m2.get("Price"));
    				}
			}
			//完成排序
			Collections.sort(list, mapComparator);	

			//寫檔
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:\\Users\\Admin\\Desktop\\Java班\\git_upload\\Java\\cars2.csv")));
			//把list的值取出來，先寫第一行欄位，再寫後面每一行
			StringBuilder sb = new StringBuilder();
			String lineSeparator = System.lineSeparator();
			
			// keyA,keyB,keyC + nextLine
			sb.append(String.join(",", keyList);
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
				
				lineSb = lineSb.subString(1); // remove first ","

				// 放入sb
				sb.append(lineSb.toString());
				sb.append(lineSeparator);
			}
			
			// 寫檔匯出
			br.write(sb.toString());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
