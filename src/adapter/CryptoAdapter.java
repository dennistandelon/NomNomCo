package adapter;

import model.Payment;

public class CryptoAdapter extends Payment{

	private Payment payment;
	
	public CryptoAdapter(Payment payment) {
		this.payment = payment;
	}
	
	@Override
	public double getPrice() {
		return this.payment.getPrice() / 2;
	}

}
