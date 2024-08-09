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
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Admin\\Desktop\\Java班\\git_upload\\Java\\cars.csv"))) {

			List<Map<String, String>> resultList = new ArrayList<>();

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				Map<String, String> map = new HashMap<>();
				String[] keyValuePairs = line.split(",");
				for (String keyValuePair : keyValuePairs) {
					String[] pair = keyValuePair.split(":");
					if (pair.length == 2) {
						String key = pair[0].trim();
						String value = pair[1].trim();
						map.put(key, value);
					}
				}
				resultList.add(map);
			}
			System.out.println(resultList);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
