package singleton;

import java.util.Vector;

import model.Confectionary;

public class Database {

	private static Database store;
	private Vector<Confectionary> histories;
	
	private Database() {
		histories = new Vector<Confectionary>();
	}
	
	public static Database getStore() {
		return (store == null)? store = new Database(): store;
	}

	public void addHistory(Confectionary history) {
		histories.add(history);
	}
	
	public Vector<Confectionary> getHistory(){
		return this.histories;
	}
	
}
