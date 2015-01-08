package com.example.papple2;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
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
		
		itemProvider = new ItemProvider(this);
		List<ItemPart> items = itemProvider.getItems();
		if(items.size() == 0)
		{
			ItemPart item1 = new ItemPart("Cover", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.rood), getResources().getDrawable(R.drawable.coverd));
			ItemPart item2 = new ItemPart("Shell", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.paars), getResources().getDrawable(R.drawable.shelld));
			ItemPart item3 = new ItemPart("Screen", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.grijs), getResources().getDrawable(R.drawable.screend));
			ItemPart item4 = new ItemPart("Side", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.blauw), getResources().getDrawable(R.drawable.sided));
			ItemPart item5 = new ItemPart("Keyboard", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.rood), getResources().getDrawable(R.drawable.keyboardd));
			ItemPart item6 = new ItemPart("Trackpad", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.paars), getResources().getDrawable(R.drawable.trackpadd));
			ItemPart item7 = new ItemPart("Adapter", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.grijs), getResources().getDrawable(R.drawable.adapterd));
			ItemPart item8 = new ItemPart("Onderkant", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.blauw), getResources().getDrawable(R.drawable.bottomd));
			
			itemProvider.addItemPart(item1);
			itemProvider.addItemPart(item2);
			itemProvider.addItemPart(item3);
			itemProvider.addItemPart(item4);
			itemProvider.addItemPart(item5);
			itemProvider.addItemPart(item6);
			itemProvider.addItemPart(item7);
			itemProvider.addItemPart(item8);
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
	
	 @Override
		public void onBackPressed() {

			dialogClick();
		}
	    
	    public void dialogClick()
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int id) {
					Toast tToast = Toast.makeText(getApplication().getApplicationContext(), "Project Saved", Toast.LENGTH_SHORT);
					tToast.show();
					finish();
				}
			});
			
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int id) {
					Toast tToast = Toast.makeText(getApplication().getApplicationContext(), "Project deleted", Toast.LENGTH_SHORT);
					tToast.show();
					finish();
					
				}
			});
			
			builder.setMessage(R.string.dialog_message)
			.setTitle(R.string.dialog_title);
			
			AlertDialog dialog = builder.create();
			dialog.show();
		}
}
