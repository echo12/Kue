package com.kue.mobile;

public class Item {

	private String itemName;
	private int itemID;
	private boolean itemRange;

	// one constructor
	public Item (int ID, String name, boolean range) {
		itemID = ID;
		itemName = name;
		itemRange = range;
	}

	public void setID(int newID) {
		itemID = newID;
	}

	public void setName(String newName) {
		itemName = newName;
	}

	public void setID(boolean newRange) {
		itemRange = newRange;
	}

	public int getID(){
		return itemID;
	}

	public String getName(){
		return itemName;
	}

	public boolean getRange(){
		return itemRange;
	}

}
