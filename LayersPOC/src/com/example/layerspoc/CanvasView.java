package com.example.layerspoc;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CanvasView extends View {

	Context context;
	Bitmap pic = makeBitmap(R.drawable.ic_launcher);
	Bitmap pic2 = makeBitmap(R.drawable.ic_launcher);

	int id = 0;
	ArrayList<Item> items = new ArrayList();
	private float x = 0;
	private float y = 0;
	private float x2 = 0;
	private float y2 = 100;

	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (Item item : items) {
			if (item.getVisible()) {
				if (item.getSoort() == 0) {
					drawBitmap(canvas, item.getId());
				}
			}
		}
	}

	private void drawBitmap(Canvas canvas, int id) {
		if(id == 0)
		{
			canvas.drawBitmap(pic, x, y, null);
		}
		else if(id == 1)
		{
			canvas.drawBitmap(pic, x2, y2, null);
		}
		
	}

	private Bitmap makeBitmap(int bitmapId) {
		return (BitmapFactory.decodeResource(getResources(), bitmapId));
	}

	public void addPicture(int bitmapId) {
		Bitmap bitmap = makeBitmap(bitmapId);
		Item item = new Item(id, bitmap, 0, true);
		id++;
		items.add(item);
		invalidate();
	}
	
	public Item getItemById(int id)
	{
		return items.get(id);
	}
	
	public int getItemsSize()
	{
		return this.items.size();
	}

}
