package com.poc.pocresizeimg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Item {
	
	
	private Bitmap img;
	private Matrix transformatie;
	private boolean isVisible;
	
	public Item(Bitmap img){
		this.img = img;
		this.isVisible = true;
		transformatie = new Matrix();
	}
	
	public void setMatrix(float xPos, float yPos, float scaleFactor)
	{
		transformatie.setScale(scaleFactor, scaleFactor);
		transformatie.postTranslate(xPos, yPos);
	}
	
	public Matrix getMatrix(){
		return this.transformatie;
	}
	
	public Bitmap getImg()
	{
		return this.img;
	}
	
	public void setVisible(boolean b)
	{
		this.isVisible = b;
	}
	
	public boolean isVisible()
	{
		return this.isVisible;
	}

	
	public void Draw(Canvas c)
	{
		if(isVisible)
		{
			c.drawBitmap(this.img, this.transformatie, null);
		}
	}
}
