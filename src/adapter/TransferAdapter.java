package adapter;

import model.Payment;

public class TransferAdapter extends Payment {

	private Payment payment;
	
	public TransferAdapter(Payment payment) {
		this.payment = payment;
	}
	
	@Override
	public double getPrice() {
		return this.payment.getPrice() * 1.1;
	}

}
