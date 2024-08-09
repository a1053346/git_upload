package com.cathaybk.practice.nt50359.b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cars {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Admin\\Desktop\\Java班\\git_upload\\Java\\cars.csv"))) {

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
