package com.poc.pocresizeimg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private static final int RESULT_LOAD_IMAGE = 1;
	private layerAdapter adapter;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        CanvasDraw canvasDraw = (CanvasDraw)findViewById(R.id.canvasDraw1);
        canvasDraw.invalidate();
        
        adapter = new layerAdapter(this);
        
        ListView list = (ListView)findViewById(R.id.listViewItems);
        list.setAdapter(adapter);
        
        Button importBtn = (Button)findViewById(R.id.buttonImport);
        importBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, RESULT_LOAD_IMAGE);	
			}	
        });
    }
    
    public void btnTextClick(View view)
    {
    	
    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
    	alertDialog.setTitle("Text");
    	alertDialog.setMessage("Add your Text");
    	
    	final EditText input = new EditText(MainActivity.this);
    	 LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
    	     LinearLayout.LayoutParams.MATCH_PARENT,
    	     LinearLayout.LayoutParams.MATCH_PARENT);
    	 input.setLayoutParams(lp);
    	 alertDialog.setView(input);
    	 
    	 alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
    		 
				@Override
				public void onClick(DialogInterface dialog, int id) {
					ItemProvider.getInstance().addItem(new Item(input.getText().toString(), getApplicationContext()));
			    	adapter.notifyDataSetChanged();
			    	CanvasDraw cd = (CanvasDraw)findViewById(R.id.canvasDraw1);
					cd.invalidate();
					cd.resetMatrix();
				}
			});
			
			alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int id) {
					
					
				}
			});
    	
    	alertDialog.show();
    	
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
    	{
    		Uri selectedImage = data.getData();
    		String[] filePathColumn = {MediaStore.Images.Media.DATA};
    		Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
    		cursor.moveToFirst();
    		
    		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
    		String picturePath = cursor.getString(columnIndex);
    		cursor.close();
    		ItemProvider.getInstance().addItem(new Item(BitmapFactory.decodeFile(picturePath)));
    		adapter.notifyDataSetChanged();
    		CanvasDraw cd = (CanvasDraw)findViewById(R.id.canvasDraw1);
    		cd.invalidate();
    		cd.resetMatrix();
    	}
    }
}
