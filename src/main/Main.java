package main;

import java.util.Scanner;
import java.util.Vector;

import model.*;
import singleton.Database;
import adapter.*;
import factory.*;

public class Main {

	private Scanner scan = new Scanner(System.in);
	private Database store = Database.getStore();
	
	public int getInt() {
		int rand = -1;
		try {
			rand = Integer.parseInt(scan.nextLine());
		} catch (Exception e) {
			System.out.println("");
		}
		
		return rand;
	}
	
	public double getDouble() {
		double rand = -1;
		try {
			rand = Double.parseDouble(scan.nextLine());
		} catch (Exception e) {
			System.out.println("");
		}
		
		return rand;
	}
	
	public String generateRandom(String regex, int len) {
		String rand = "";
		
		int index = 0;
		for(int i =0; i < len; i++) {
			index = (int)(Math.random() * 100) % 10;
			rand = rand + regex.charAt(index);
		}
		
		return rand;
	}
	
	public void bakeMenu() {
		ConfectionaryFactory factory;
		Payment payment;
		String type, name, softness, additional, paymentType;
		Vector<String> toppings = new Vector<String>();
		double price;
		
		do {			
			System.out.print("Input confectionary type [Cupcake | Tart][case sensitive]: ");
			type = scan.nextLine();
		}while(!(type.equals("Cupcake") || type.equals("Tart")));
		
		factory = (type.equals("Cupcake"))? new CupcakeFactory() : new TartFactory();
		
		do {
			System.out.print("Input confectionary name [length between 5 - 15]: ");
			name = scan.nextLine();
		}while(name.length() < 5 || name.length() > 15);
		
		
		do {
			System.out.print("Input confectionary softness [Fluffy | Medium | Hard][case sensitive]: ");
			softness= scan.nextLine();
		}while(!(softness.equals("Fluffy") || softness.equals("Medium") || softness.equals("Hard")));

		do {
			System.out.print("Adding additional topping [Y | N][case sensitive]: ");
			additional= scan.nextLine();
		}while(!(additional.equals("Y") || additional.equals("N")));
		
		if(additional.equals("Y")) {
			for(int i=0;i<3;i++) {
				String top = "";
				do {
					System.out.print("Input topping " + (i+1) + " [length between 1 - 10]: ");
					top = scan.nextLine();
					toppings.add(top);
				} while(!(top.length() > 1 || top.length() < 10));
			}
		}
		
		do {
			System.out.print("Input confectionary price [10.0 - 50.0]: ");
			price = getDouble();
		}while(!(price > 10.0 && price < 50.0));
		
		do {
			System.out.print("What kind of payment [Cash | Transfer | Crypto][case sensitive]: ");
			paymentType = scan.nextLine();
		}while(!(paymentType.equals("Cash") || paymentType.equals("Transfer") || paymentType.equals("Crypto")));
		
		
		payment = new Cash(price);
		
		switch (paymentType) {
		case "Transfer": 
			payment = new Transfer(price, generateRandom("0123456789", 10));
			break;
		case "Crypto": 
			payment = new Crypto(price, "0x" + generateRandom("abcdefghijklmnopqrstuvwxyz0123456789", 10));
			break;
		}
		
		store.addHistory(factory.bakeConfectionary(name, softness, toppings, price, paymentType, payment));
		
		System.out.println("Confectionary Baked!....");
		System.out.println("Press enter to continue...");
		scan.nextLine();
	}
	
	public String getType(Confectionary c) {
		return (c instanceof Cupcake)? "Cupcake" : "Tart";
	}
	
	public void getPriceString(Payment payment, String type) {
		System.out.printf("Price\t\t: ");
		if(payment instanceof Cash) {
			System.out.printf("$%.2f\n", (new CashAdapter(payment)).getPrice());
		} else if(payment instanceof Transfer) {
			System.out.printf("$%.2f with Account Number : %s\n", (new TransferAdapter(payment)).getPrice(), ((Transfer)payment).getAccount());
		} else {
			System.out.printf("ADA %.2f with Address: %s\n", (new CryptoAdapter(payment)).getPrice(), ((Crypto)payment).getAddress());
		}
	}
	
	public void viewMenu() {
		Database db = Database.getStore();
		Vector<Confectionary> histories =  db.getHistory();
		
		if(histories.size() == 0) {
			System.out.println("No Confectionary Yet...");
		} else {
			for (Confectionary c : histories) {
				System.out.printf("Name\t\t: %s (%s)\n", c.getName(), getType(c));
				System.out.printf("Softness\t: %s\n", c.getSoftness());
				System.out.print("Topping\t\t:");
				if(c.getToppings().size() == 0) {
					System.out.println(" -");
				} else {
					for (String t : c.getToppings()) {
						System.out.print(" " + t);
					}
					System.out.println("");
				}
				System.out.printf("PaymentType\t: %s\n", c.getPaymentType());
				getPriceString(c.getPayment(), c.getPaymentType());
				System.out.println("==========================");
			}
		}
		
		System.out.println("Press enter to continue...");
		scan.nextLine();
	}
	
	public Main() {
		int menu = -1;
		do {
			System.out.println("Nom Nom Co.");
			System.out.println("===========");
			System.out.println("1. Bake Confectionary");
			System.out.println("2. View Confectionary Order");
			System.out.println("3. Exit");
			System.out.print(">> ");
			menu = getInt();
			
			switch (menu) {
			case 1: bakeMenu();
				break;
			case 2: viewMenu();
				break;
			default:
				break;
			}
		} while(menu != 3);

		System.out.println("Thank you for using nomnom service!");
	}

	public static void main(String[] args) {
		new Main();
	}

}
