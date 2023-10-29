package model;

import java.util.Vector;

public abstract class Confectionary {

	private String name, softness, paymentType;
	private Payment payment;

	private Vector<String> toppings;
	private double price;
	
	public Confectionary(String name, String softness, Vector<String> toppings, double price, String paymentType, Payment payment) {
		this.name = name;
		this.softness = softness;
		this.toppings = toppings;
		this.price = price;
		this.payment = payment;
		this.paymentType = paymentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSoftness() {
		return softness;
	}

	public void setSoftness(String softness) {
		this.softness = softness;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Vector<String> getToppings() {
		return toppings;
	}

	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setToppings(Vector<String> toppings) {
		this.toppings = toppings;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}
