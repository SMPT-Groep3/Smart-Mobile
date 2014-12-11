package com.example.papple2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void newProject (View view)
    {
    	Intent intent = new Intent(this, NewProject.class);
    	startActivity(intent);
    }
    
    public void loadProject (View view)
    {
    	Intent intent = new Intent(this, LoadProject.class);
    	startActivity(intent);
    }
    
    public void shareProject (View view)
    {
    	Intent intent = new Intent(this, ShareProject.class);
    	startActivity(intent);
    }
    
    public void importProject (View view)
    {
    	Intent intent = new Intent(this, ImportProject.class);
    	startActivity(intent);
    }
    
    public void exportProject (View view)
    {
    	Intent intent = new Intent(this, ExportProject.class);
    	startActivity(intent);
    }
    
}
