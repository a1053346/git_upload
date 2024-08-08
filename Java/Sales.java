package com.cathaybk.practice.nt50359.b;

public class Sales extends Employee {

	private int bonus;
	private int payment;

	public Sales(String name, String department, int salary, int kpi) {
		super(name, department, salary);
		this.bonus = (int) (kpi * 0.05);
		this.payment = salary + this.bonus;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public void printInfo() {
		super.printInfo();
		System.out.println("業績獎金：" + bonus);
		System.out.println("總計：" + payment);
	}
}
