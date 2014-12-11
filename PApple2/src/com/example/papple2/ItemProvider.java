package com.example.papple2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class ItemProvider {

	private static List<ItemPart> items;
	private Context context;
	
	public ItemProvider(Context context)
	{
		this.context = context;
		if(items == null)
		{
			items = new ArrayList<ItemPart>();
		}
	}
	
	public List<ItemPart> getItems()
	{
		return this.items;
	}
	
	public ItemPart getItemPart(int index)
	{
		if(index >= items.size())
		{
			return null;
		}
		return items.get(index);
	}
	
	public void addItemPart(ItemPart itemPart)
	{
		items.add(itemPart);
	}
	
	public ItemPart getItemPartByName(String naam)
	{
		for(ItemPart item : items)
		{
			if(item.getNaam().equals(naam))
			{
				return item;
			}
		}
		return null;
	}
}
