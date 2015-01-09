package com.poc.pocresizeimg;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;


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
