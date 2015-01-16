package com.poc.pocresizeimg;

import org.apache.http.conn.routing.RouteInfo.LayerType;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;

public class Item {
	
	
	private Bitmap img;
	private Matrix transformatie;
	private boolean isVisible;
	private int soort;
	private String text;
	private Paint paint;
	private Context context;
	private int x = 0, y = 50;
	
	public Item(Bitmap img){
		this.img = img;
		this.isVisible = true;
		this.soort = 0;
		transformatie = new Matrix();
	}
	
	public Item(String text, Context context)
	{
		this.context = context;
		this.text = text;
		this.isVisible = true;
		this.soort = 1;
		transformatie = new Matrix();
		paint = new Paint();
		paint.setTextSize(160f);
		this.img = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
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
			if(soort == 0)
			{
				c.drawBitmap(this.img, this.transformatie, null);
			}
			else if(soort == 1)
			{
				c.drawText(text, x, y, paint);
			}
			
		}
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public int getSoort()
	{
		return this.soort;
	}
	
	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
