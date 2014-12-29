package com.example.papple2;

import java.util.ArrayList;
import java.util.List;

import com.example.papple2.R.color;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class LoadProject extends Activity {
	
	GridView grid;
	MyGridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_project);	
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		List<ItemPart> projects = new ArrayList();
		
		ItemPart item1 = new ItemPart("Project1", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_blue_dark));
		ItemPart item2 = new ItemPart("Project2", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_red_dark));
		ItemPart item3 = new ItemPart("Project3", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_orange_dark));
		ItemPart item4 = new ItemPart("Project4", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_orange_dark));
		ItemPart item5 = new ItemPart("Project5", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_orange_dark));
		ItemPart item6 = new ItemPart("Project6", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_green_dark));
		ItemPart item7 = new ItemPart("Project7", getResources().getDrawable(R.drawable.ic_launcher), getResources().getColor(color.newAndroid_orange_dark));
		
		projects.add(item1);
		projects.add(item2);
		projects.add(item3);
		projects.add(item4);
		projects.add(item5);
		projects.add(item6);
		projects.add(item7);
		
		
		grid = (GridView) findViewById(R.id.projectCollection);
		adapter = new MyGridAdapter(this, projects);
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> a, View view, int position, long id)
			{
				//Nieuw intent om dat onderdeel te customizen
				Log.d("EDR", "Project clicked");
			}
		});
	}
}
