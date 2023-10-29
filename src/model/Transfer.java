package model;

public class Transfer extends Payment {

	private String account;
	

	public Transfer(double price, String account) {
		setAccount(account);
		setPrice(price);
	}

	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
}
