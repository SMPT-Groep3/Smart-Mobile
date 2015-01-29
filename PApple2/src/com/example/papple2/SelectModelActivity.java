package com.example.papple2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SelectModelActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_model);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		setTitle("Choose your laptop");
	}
	
    public void dertien (View view)
    {
    	Intent intent = new Intent(this, NewProject.class);
    	intent.putExtra("model", "dertien");
    	startActivity(intent);
    }
    
    public void vijftien (View view)
    {
    	Intent intent = new Intent(this, NewProject.class);
    	intent.putExtra("model", "vijftien");
    	startActivity(intent);
    }
}
