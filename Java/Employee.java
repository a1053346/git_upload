package com.cathaybk.practice.nt50359.b;

public class Employee implements IWork {

	private String name;
	private String department;
	private int salary;

	public Employee(String name, String department, int salary) {
		setName(name);
		setDepartment(department);
		setSalary(salary);
	}

	@Override
	public void printInfo() {
		StringBuilder sb = new StringBuilder();
		System.out.println("薪資單");
		sb.append("姓名：").append(getName()).append(" 工作部門：").append(getDepartment());
		System.out.println(sb); // 改stringbuilder
		System.out.println("月薪：" + getSalary());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
