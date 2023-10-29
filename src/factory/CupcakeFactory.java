package factory;

import java.util.Vector;

import model.Confectionary;
import model.Cupcake;
import model.Payment;

public class CupcakeFactory implements ConfectionaryFactory {
	@Override
	public Confectionary bakeConfectionary(String name, String softness, Vector<String> toppings, double price,
			String paymentType, Payment payment) {
		return new Cupcake(name, softness, toppings, price, paymentType, payment);
	}

}
