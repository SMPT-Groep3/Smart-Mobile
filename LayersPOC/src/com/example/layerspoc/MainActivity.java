package com.example.layerspoc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends Activity {
	
	CanvasView cv;
	CheckBox cb1;
	CheckBox cb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cv = (CanvasView)findViewById(R.id.customView);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        
        cb1.setChecked(true);
        cb2.setChecked(true);
    }
    
    public void btnRedraw(View view)
    {
    	cv.addPicture(R.drawable.ic_launcher);
    	
    	if(cv.getItemsSize() == 1)
    	{
    		cb1.setEnabled(true);
    	}
    	else if(cv.getItemsSize() >= 2)
    	{
    		cb2.setEnabled(true);
    		Button btn = (Button) findViewById(R.id.btnRedraw);
    		btn.setVisibility(View.GONE);
    	}
    }
    
    public void cb1Click(View view)
    {
    	if(cb1.isChecked())
    	{
    		//zichtbaarheid bm1 aan
    		cv.getItemById(0).setVisible(true);
    	}
    	else
    	{
    		//zichtbaarheid bm1 uit
    		cv.getItemById(0).setVisible(false);
    	}
    	cv.invalidate();
    }
    
    public void cb2Click(View view)
    {
    	if(cb2.isChecked())
    	{
    		//zichtbaarheid bm2 aan
    		cv.getItemById(1).setVisible(true);
    	}
    	else
    	{
    		//zichtbaarheid bm2 uit
    		cv.getItemById(1).setVisible(false);
    	}
    	cv.invalidate();
    }
}
