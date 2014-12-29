package com.example.papple2;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.papple2.R.color;

public class NewProject extends Activity {
	
	MyGridAdapter adapter;
	ItemProvider itemProvider;
	GridView grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_project);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		itemProvider = new ItemProvider(this);
		List<ItemPart> items = itemProvider.getItems();
		if(items.size() == 0)
		{
			ItemPart item1 = new ItemPart("Cover", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_blue_dark));
			ItemPart item2 = new ItemPart("Shell", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_red_dark));
			ItemPart item3 = new ItemPart("Screen", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_orange_dark));
			ItemPart item4 = new ItemPart("Side", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_orange_dark));
			ItemPart item5 = new ItemPart("Keyboard", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_orange_dark));
			ItemPart item6 = new ItemPart("Trackpad", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_green_dark));
			ItemPart item7 = new ItemPart("Adapter", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_orange_dark));
			
			itemProvider.addItemPart(item1);
			itemProvider.addItemPart(item2);
			itemProvider.addItemPart(item3);
			itemProvider.addItemPart(item4);
			itemProvider.addItemPart(item5);
			itemProvider.addItemPart(item6);
			itemProvider.addItemPart(item7);
		}
		
		grid = (GridView) findViewById(R.id.itemCollection);
		adapter = new MyGridAdapter(this, items);
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> a, View view, int position, long id)
			{
				//Nieuw intent om dat onderdeel te customizen
				Log.d("EDR", "Yesh!!");
			}
		});
	}
}
