package com.example.papple2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    	Intent intent = new Intent(this, SelectModelActivity.class);
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
    	Bitmap afbeelding = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.apple);
    	Log.d("EDR", "Afmeting afbeelding w:" + afbeelding.getWidth() + " h:" + afbeelding.getHeight());
    	
    	ImageView img = (ImageView) findViewById(R.id.imageView);
    	Log.d("EDR", "Afmeting view w:" + img.getWidth() + " h:" + img.getHeight());
    	
    	Intent intent = new Intent(this, ImportProject.class);
    	startActivity(intent);
    }
    
}
