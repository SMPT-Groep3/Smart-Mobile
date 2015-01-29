package com.example.papple2;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.papple2.R.color;

public class NewProject extends Activity {
	
	MyGridAdapter adapter;
	ItemProvider itemProvider;
	GridView grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_project);
		
		setTitle("Choose your part");
		
		itemProvider = new ItemProvider(this);
		List<ItemPart> items = itemProvider.getItems();
		if(items.size() == 0)
		{
			ItemPart item1 = new ItemPart("Keyboard", getResources().getDrawable(R.drawable.keyboard), getResources().getColor(color.rood), getResources().getDrawable(R.drawable.keyboardd));
			ItemPart item2 = new ItemPart("Trackpad", getResources().getDrawable(R.drawable.touchpad), getResources().getColor(color.paars), getResources().getDrawable(R.drawable.trackpadd));
			ItemPart item3 = new ItemPart("Adapter", getResources().getDrawable(R.drawable.adapter), getResources().getColor(color.grijs), getResources().getDrawable(R.drawable.adapterd));
			ItemPart item4 = new ItemPart("Cover", getResources().getDrawable(R.drawable.shell), getResources().getColor(color.blauw), getResources().getDrawable(R.drawable.coverd));
			
			itemProvider.addItemPart(item1);
			itemProvider.addItemPart(item2);
			itemProvider.addItemPart(item3);
			itemProvider.addItemPart(item4);
		}
		
		grid = (GridView) findViewById(R.id.itemCollection);
		adapter = new MyGridAdapter(this, items);
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> a, View view, int position, long id)
			{
				Intent editorIntent = new Intent(getApplicationContext(), EditorActivity.class);
				itemProvider.getItemPart(position).getNaam();
				String partName = itemProvider.getItemPart(position).getNaam();
				Log.d("EDR", partName);
				editorIntent.putExtra("part", partName);
				startActivity(editorIntent);
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
