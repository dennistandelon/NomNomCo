package adapter;

import model.Payment;

public class CashAdapter extends Payment{

	private Payment payment;
	
	public CashAdapter(Payment payment) {
		this.payment = payment;
	}
	
	@Override
	public double getPrice() {
		return this.payment.getPrice() * 1;
	}

}
