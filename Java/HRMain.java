package com.cathaybk.practice.nt50359.b;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HRMain {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));
		for (Employee employee : employeeList) {
			employee.printInfo();
		}

//		Employee emp1 = new Sales("aaa", "bbb", 35000, 6000);
//		emp1.printInfo();

		try {

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("C:\\Users\\Admin\\Desktop\\Java班\\git_upload\\Java\\output.csv"),
					StandardCharsets.UTF_8)); // 創BufferedWriter
			bufferedWriter.write('\ufeff');
			bufferedWriter.flush();

			for (Employee emp : employeeList) {
				if (emp instanceof Sales) { // 判斷資料是不是屬於Sales
					Sales empSales = (Sales) emp; // 把emp轉型為Sales去拿取
//					System.out.println(empSales.getPayment());
					bufferedWriter.write(emp.getName() + "," + empSales.getPayment() + "\n"); // 寫入文件
				} else {
					Supervisor empSupervisor = (Supervisor) emp;
					bufferedWriter.write(emp.getName() + "," + empSupervisor.getPayment() + "\n"); // 寫入文件
				}

			}
			bufferedWriter.close();// 關閉writer
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
