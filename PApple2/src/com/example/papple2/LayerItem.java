package com.example.papple2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class LayerItem {
	
	
	private Bitmap img;
	private Matrix transformatie;
	private boolean isVisible;
	private int soort;
	private String text;
	private Paint paint;
	private Context context;
	private int color;
	private int x = 0, y = 50;
	private boolean isBold = false;
	private boolean isItalic =false;
	private boolean isUnderlined = false;
	
	public LayerItem(Bitmap img){
		this.img = img;
		this.isVisible = true;
		this.soort = 0;
		transformatie = new Matrix();
	}
	
	public LayerItem(String text, Context context, Paint paint)
	{
		this.paint = new Paint(paint);
		this.context = context;
		this.text = text;
		this.isVisible = true;
		this.soort = 1;
		transformatie = new Matrix();
		this.img = BitmapFactory.decodeResource(context.getResources(), R.drawable.text);
	}
	
	public LayerItem(int color, Context context)
	{
		this.context = context;
		this.soort = 2;
		this.color = color;
		this.isVisible = true;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inMutable = true;
		this.img = BitmapFactory.decodeResource(context.getResources(), R.drawable.blauw, options);
		this.img.eraseColor(color);
	}
	
	public LayerItem(int pattern, boolean isBackground, Context context) {
		this.context = context;
		this.soort = 2;
		this.img = BitmapFactory.decodeResource(context.getResources(), pattern);
		this.isVisible = true;
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
				if(img != null)
				{
					c.drawBitmap(this.img, this.transformatie, null);
				}
				else
				{
					Log.d("EDR", "WTF");
				}
				
			}
			else if(soort == 1)
			{
				c.drawText(text, x, y, paint);
			}
			else if(soort == 2)
			{
				WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
				Point size = new Point();
				Display display = wm.getDefaultDisplay();
				display.getSize(size);
				img = Bitmap.createScaledBitmap(img, size.x,
						size.y, true);
				c.drawBitmap(img, 0, 0, null);
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

	public boolean isBackgroundColor() {
		if(soort == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
