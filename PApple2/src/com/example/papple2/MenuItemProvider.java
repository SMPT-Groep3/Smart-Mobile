package com.example.papple2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class MenuItemProvider extends ArrayAdapter<Drawable> {

	private final Context context;
	private final Drawable[] values;

	public MenuItemProvider(Context context, Drawable[] objects) {
		super(context, R.layout.menulistitem, objects);
		this.context = context;
		this.values = objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View myView = convertView;
		Drawable d = values[position];
		int breedte = d.getIntrinsicWidth();
		int hoogte = d.getIntrinsicHeight();
		if (myView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			myView = inflater.inflate(R.layout.menulistitem, null, false);
		}
		ImageView image = (ImageView) myView.findViewById(R.id.PlayerPicture);
		image.setImageDrawable(d);
		image.setAdjustViewBounds(true);
		image.requestLayout();
		image.getLayoutParams().height = 128;
		if(hoogte <= 80)
		{
			image.getLayoutParams().height = hoogte - 26;
		}
		return myView;
	}
}
