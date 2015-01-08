package com.poc.pocresizeimg;

import java.util.ArrayList;
import java.util.List;

public class ItemProvider {

	private static ItemProvider INSTANCE;
	
	private List<Item> items;
	
	private ItemProvider(){
		items = new ArrayList<Item>();
	}
	
	public static ItemProvider getInstance(){
		if (INSTANCE == null)
		{
			INSTANCE = new ItemProvider();
		}
		return INSTANCE;
	}
	
	public List<Item> getItems()
	{
		return items;
	}
	
	public void addItem(Item i)
	{
		items.add(i);
	}
	
	
	
	
	
}
