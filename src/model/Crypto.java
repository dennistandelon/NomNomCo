package model;

public class Crypto extends Payment {

	private String address;
	
	public Crypto(double price, String address) {
		setAddress(address);
		setPrice(price);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
