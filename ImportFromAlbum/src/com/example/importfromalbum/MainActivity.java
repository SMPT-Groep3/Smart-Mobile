package com.example.importfromalbum;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static final int RESULT_LOAD_IMAGE = 1;
	ImageView imgView;
	private String selectedImagePath;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void openFoto(View view)
    {
    	Intent intent = new Intent(Intent.ACTION_PICK,
    			android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    	startActivityForResult(intent, RESULT_LOAD_IMAGE);
    	
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
    		ImageView imgView = (ImageView) findViewById(R.id.imageview);
    		imgView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
    	}
    }
}
