package com.example.papple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		// Button newproject = (Button) findViewById(R.id.newproject);
		// newproject.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v)
		// {
		// Intent intent = new Intent(getApplicationContext(), Project.class);
		// startActivity(intent);
		// }
		// });

		// Button loadproject = (Button) findViewById(R.id.loadproject);
		// loadproject.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v)
		// {
		// Intent intent = new Intent(getApplicationContext(), Gallery.class);
		// startActivity(intent);
		// }
		// });

		Button dertien = (Button) findViewById(R.id.btnDertien);
		dertien.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		
//		Button vijftien = (Button) findViewById(R.id.btnVijftien);
//		vijftien.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//		Button openProject = (Button) findViewById(R.id.btnOpenProject);
//		openProject.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//		Button newProject = (Button) findViewById(R.id.btnNewProject);
//		newProject.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//			}
//		});
	}
}
