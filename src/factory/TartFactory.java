package factory;

import java.util.Vector;

import model.Confectionary;
import model.Payment;
import model.Tart;

public class TartFactory implements ConfectionaryFactory {

	@Override
	public Confectionary bakeConfectionary(String name, String softness, Vector<String> toppings, double price,
			String paymentType, Payment payment) {
		return new Tart(name, softness, toppings, price, paymentType, payment);
	}

}
