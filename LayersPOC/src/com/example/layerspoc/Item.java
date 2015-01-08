package com.example.layerspoc;

import android.graphics.Bitmap;

public class Item {
	
	private int id;
	private Bitmap bitmap;
	
	/**
	 * 1 = bitmap
	 * 2 = rechthoek
	 * 3 = cirkel
	 * 4 = e.d.
	 */
	private int soort;
	private boolean isVisible;

	public Item(int id, Bitmap bitmap, int soort, boolean isVisible)
	{
		this.id = id;
		this.bitmap = bitmap;
		this.soort = soort;
		this.isVisible = isVisible;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public Bitmap getBitmap()
	{
		return this.bitmap;
	}
	
	public int getSoort()
	{
		return this.soort;
	}
	
	public boolean getVisible()
	{
		return this.isVisible;
	}
	
	public void setVisible(boolean visible)
	{
		if(visible)
		{
			this.isVisible = true;
		}
		else
		{
			this.isVisible = false;
		}
	}
}
