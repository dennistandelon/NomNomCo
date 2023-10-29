package factory;

import java.util.Vector;

import model.Confectionary;
import model.Payment;

public interface ConfectionaryFactory {
	public Confectionary bakeConfectionary(String name, String softness, Vector<String> toppings, double price, String paymentType, Payment payment);
}
