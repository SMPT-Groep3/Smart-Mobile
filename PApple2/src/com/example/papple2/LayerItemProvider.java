package com.example.papple2;

import java.util.ArrayList;
import java.util.List;

public class LayerItemProvider {

	private static LayerItemProvider INSTANCE;
	
	private List<LayerItem> items;
	
	private LayerItemProvider(){
		items = new ArrayList<LayerItem>();
	}
	
	public static LayerItemProvider getInstance(){
		if (INSTANCE == null)
		{
			INSTANCE = new LayerItemProvider();
		}
		return INSTANCE;
	}
	
	public List<LayerItem> getItems()
	{
		return items;
	}
	
	public void addItem(LayerItem i)
	{
		items.add(i);
	}
	
	public void clearItems()
	{
		items.clear();
	}
	
	
	
	
	
}
