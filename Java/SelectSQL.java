package com.cathaybk.practice.nt50359.b;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SelectSQL {

	private static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";

	private static final String USER_NAME = "student";

	private static final String PASSWORD = "student123456";

	private static final String SELECT_CARS_SQL = "select * from STUDENT.CARS";

	static String lineSeparator = System.lineSeparator();

	public static void main(String[] args) {
		doQuery();

		try (// 執行scanner
				Scanner sc = new Scanner(System.in)) {
			System.out.println("請選擇以下指令輸入select、insert、update、delete：");
			String order = sc.next();

			switch (order) {
			// 選擇select時
			case "select":
				System.out.println("請輸入製造商：");
				String manu = sc.next();
				System.out.println("請輸入類別：");
				String type = sc.next();
				query(manu, type);
				break;

			// 選擇insert時
			case "insert":
				System.out.println("請輸入製造商：");
				String manuInsert = sc.next();
				System.out.println("請輸入類別：");
				String typeInsert = sc.next();
				System.out.println("請輸入底價：");
				String minPriceInsert = sc.next();
				System.out.println("請輸入售價：");
				String priceInsert = sc.next();
				Map<String, String> insertMap = new HashMap<>();
				insertMap.put("MANUFACTURER", manuInsert);
				insertMap.put("TYPE", typeInsert);
				insertMap.put("MIN_PRICE", minPriceInsert);
				insertMap.put("PRICE", priceInsert);
				insert(insertMap);
				break;

			// 選擇update時
			case "update":
				System.out.println("請輸入製造商：");
				String manuUpdate = sc.next();
				System.out.println("請輸入類別：");
				String typeUpdate = sc.next();
				System.out.println("請輸入更新後底價：");
				String minPriceUpdate = sc.next();
				System.out.println("請輸入更新後售價：");
				String priceUpdate = sc.next();
				Map<String, String> updateMap = new HashMap<>();
				updateMap.put("MANUFACTURER", manuUpdate);
				updateMap.put("TYPE", typeUpdate);
				updateMap.put("MIN_PRICE", minPriceUpdate);
				updateMap.put("PRICE", priceUpdate);
				update(updateMap);
				break;

			// 選擇delete時
			case "delete":
				System.out.println("請輸入製造商：");
				String manuDelete = sc.next();
				System.out.println("請輸入類別：");
				String typeDelete = sc.next();
				delete(manuDelete, typeDelete);
				break;

			default:
				System.out.println("輸入有誤");
				break;
			}
		}
	}

	// 查詢
	public static void doQuery() {
		// 取得連線
		// 寫入 SQL 指令
		try (Connection conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(SELECT_CARS_SQL);) {

			// ResultSet物件儲存查詢結果
			ResultSet rs = pstmt.executeQuery();

			// 使用StringBuilder做字串串接
			StringBuilder sb = new StringBuilder();

			// 將每筆資料放進map後，放入List
			List<Map<String, String>> carMapList = new ArrayList<>();
			while (rs.next()) {
				Map<String, String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", rs.getString("MANUFACTURER")); // 取得欄位資料
				carMap.put("TYPE", rs.getString("TYPE"));
				carMap.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				carMap.put("PRICE", rs.getString("PRICE"));
				carMapList.add(carMap);
			}
			sb.append("查詢結果： ").append(lineSeparator);
			for (Map<String, String> map : carMapList) {
				BigDecimal price = new BigDecimal(map.get("PRICE")); // map.get("自己儲存的KEY值")
				BigDecimal minPrice = new BigDecimal(map.get("MIN_PRICE"));
				sb.append("製造商：").append(map.get("MANUFACTURER")).append("，型號：").append(map.get("TYPE")).append("，售價：")
						.append(price.toPlainString()).append("，底價：").append(minPrice.toPlainString());
				System.out.println(sb.toString());
				sb.setLength(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	甲、提供 method query(製造商、類別) 查詢條件為製造商、類別。
	public static void query(String manu, String type) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?");) {
			// setString 輸入的製造商、類別
			pstmt.setString(1, manu);
			pstmt.setString(2, type);

			// ResultSet物件儲存查詢結果
			ResultSet rs = pstmt.executeQuery();
			StringBuilder sb = new StringBuilder();
			sb.append("查詢結果： ").append(lineSeparator);
			;
			while (rs.next()) {
				sb.append("製造商： ").append(rs.getString("MANUFACTURER")).append("，型號：").append(rs.getString("TYPE"))
						.append("，售價：").append(rs.getString("PRICE")).append("，底價：").append(rs.getString("MIN_PRICE"));
			}
			System.out.println(sb.toString());

		} catch (SQLException sqle) {
			System.out.println("查詢失敗，原因：" + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("查詢失敗，原因：" + e.getMessage());
		}
	}

//	乙、提供 method insert(Map)：設定所有欄位 。
	public static void insert(Map<String, String> map) {

		try (Connection conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);) {
			try {
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(
						"insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)");

				pstmt.setString(1, map.get("MANUFACTURER"));
				pstmt.setString(2, map.get("TYPE"));
				pstmt.setString(3, map.get("MIN_PRICE"));
				pstmt.setString(4, map.get("PRICE"));
				pstmt.executeUpdate();

				conn.commit();
				System.out.println("新增成功");

			} catch (Exception e) {
				System.out.println("新增失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

//	丙、提供 method update(Map)：by PK (製造商 類別)。
	public static void update(Map<String, String> map) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);) {
			try {
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(
						"update STUDENT.CARS set MIN_PRICE = ? , PRICE = ? where MANUFACTURER = ? and TYPE = ?");
				pstmt.setString(1, map.get("MIN_PRICE"));
				pstmt.setString(2, map.get("PRICE"));
				pstmt.setString(3, map.get("MANUFACTURER"));
				pstmt.setString(4, map.get("TYPE"));
				pstmt.executeUpdate();

				conn.commit();
				System.out.println("更新成功");

			} catch (Exception e) {
				System.out.println("更新失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

//	丁、提供 method delete(製造商，類別) by PK (製造商 類別)。
	public static void delete(String manu, String type) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, USER_NAME, PASSWORD);) {
			try {
				// TODO
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn
						.prepareStatement("delete from STUDENT.CARS where MANUFACTURER = ? and TYPE = ? ");
				pstmt.setString(1, manu);
				pstmt.setString(2, type);
				pstmt.executeUpdate();

				conn.commit();
				System.out.println("刪除成功");

			} catch (Exception e) {
				System.out.println("刪除失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
