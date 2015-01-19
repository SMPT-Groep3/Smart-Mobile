package com.example.papple2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

public class layerAdapter extends ArrayAdapter<LayerItem> {

	private Context context;
	private static LayerItem selectedItem;
	private int selectedIndex = -1;
	private static List<LayerItem> items;
	
	public layerAdapter(Context context) {
		super(context, R.layout.layer_item, LayerItemProvider.getInstance().getItems());
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final LayerItem item = super.getItem(position);
		final int positionlist = position;
		final ViewGroup parentView = parent;
		View myView = convertView;
		
		if(myView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			myView = inflater.inflate(R.layout.layer_item, null);
		}
		
		ImageView image = (ImageView)myView.findViewById(R.id.imageView1);
		image.setImageBitmap(item.getImg());
		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectedItem = item;
				selectedIndex = positionlist;
				notifyDataSetChanged();
			}
			
		});
		
		CheckBox cb = (CheckBox)myView.findViewById(R.id.checkBox1);
		cb.setChecked(item.isVisible());
		
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				item.setVisible(isChecked);	
				CanvasDraw canvas = (CanvasDraw) parentView.findViewById(R.id.canvasDraw1);
				canvas.invalidate();
			}
		});
		
		if (selectedIndex == position)
		{
			myView.setBackgroundColor(Color.BLUE);
		}
		else
		{
			myView.setBackgroundColor(Color.WHITE);
		}
		
		return myView;
	}
	
	public static LayerItem getSelectedItem()
	{
		return selectedItem;
	}
	

	
	

}
