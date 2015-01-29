package com.example.papple2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuItemProvider extends ArrayAdapter<Drawable> {

	private final Context context;
	private final Drawable[] values;

	public MenuItemProvider(Context context, Drawable[] objects) {
		super(context, R.layout.menulistitem, objects);
		this.context = context;
		this.values = objects;

	}

	@SuppressLint("NewApi") @Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View myView = convertView;
		Drawable d = values[position];
		final int pos = position;
		int breedte = d.getIntrinsicWidth();
		int hoogte = d.getIntrinsicHeight();
		if (myView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			myView = inflater.inflate(R.layout.menulistitem, null, false);
		}
		
		if(hoogte <= 80)
		{
			CheckBox checkbox = (CheckBox) myView.findViewById(R.id.checkboxLayer);
			checkbox.setBackground(d);
			checkbox.setVisibility(View.VISIBLE);
			checkbox.getLayoutParams().height = hoogte -26;
			checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(pos == 1)
					{
						EditorActivity.setBold(isChecked);
					}
					else if(pos == 2)
					{
						EditorActivity.setItalic(isChecked);
					}
					else if(pos == 3)
					{
						EditorActivity.setUnderline(isChecked);
					}
					
				}
			});
			
			ImageView image = (ImageView) myView.findViewById(R.id.PlayerPicture);
			image.setVisibility(View.GONE);
			
		}
		else
		{
			ImageView image = (ImageView) myView.findViewById(R.id.PlayerPicture);
			image.setImageDrawable(d);
			image.setAdjustViewBounds(true);
			image.requestLayout();
			image.getLayoutParams().height = 128;
			image.setVisibility(View.VISIBLE);
			CheckBox checkbox = (CheckBox) myView.findViewById(R.id.checkboxLayer);
			checkbox.setVisibility(View.GONE);
		}
		
		return myView;
	}
}
