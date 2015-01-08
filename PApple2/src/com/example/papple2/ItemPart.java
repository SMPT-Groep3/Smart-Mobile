package com.example.papple2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;

public class ItemPart {
	
	private String naam;
	private Drawable afbeelding;
	private int kleur;
	private Drawable itemTitle;

	//Deze klasse is voor de menu items te kiezen, bijvoorbeeld de shell, toetsenbard etc.
	public ItemPart(String naam, Drawable drawable, int newandroidBlueDark, Drawable itemTitle)
	{
		this.naam = naam;
		this.afbeelding = drawable;
		this.kleur = newandroidBlueDark;
		this.itemTitle = itemTitle;
	}
	
	public String getNaam()
	{
		return this.naam;
	}
	
	public Drawable getAfbeelding()
	{
		return this.afbeelding;
	}
	
	public int getKleur()
	{
		return this.kleur;
	}

	public Drawable getTitleAfbeelding() {
		return this.itemTitle;
	}
}
