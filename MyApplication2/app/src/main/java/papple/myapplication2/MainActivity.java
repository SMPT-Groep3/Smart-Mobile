package papple.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    int state = 0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    private static final int RESULT_LOAD_IMAGE = 1;
    FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button1 = (Button) findViewById(R.id.button1);
         button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
         button4 = (Button) findViewById(R.id.button4);
         button5 = (Button) findViewById(R.id.button5);
         fl = (FrameLayout) findViewById(R.id.framelayoutmain);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void button1clicked(View v) {
        switch (state) {
            case 0:
                state = 1;
                button1.setBackground(getResources().getDrawable(R.drawable.menu11));
                button2.setBackground(getResources().getDrawable(R.drawable.menu12));
                button3.setBackground(getResources().getDrawable(R.drawable.menu13));
                button4.setBackground(getResources().getDrawable(R.drawable.menu14));
                button5.setVisibility(View.VISIBLE);
                break;
            case 1:
                fl.setBackgroundColor(Color.RED);
                break;
            case 2:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            case 3:
                fl.setBackground(getResources().getDrawable(R.drawable.pattern1));
                break;
            case 4:

                break;
            case 5:

                break;
        }
    }

    public void button2clicked(View v) {
        switch (state) {
            case 0:
                state = 2;
                button1.setBackground(getResources().getDrawable(R.drawable.menu21));
                button2.setBackground(getResources().getDrawable(R.drawable.menu22));
                button3.setBackground(getResources().getDrawable(R.drawable.menu23));
                button4.setBackground(getResources().getDrawable(R.drawable.menu24));
                button5.setVisibility(View.VISIBLE);
                break;
            case 1:
                fl.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                ImageView imageview = new ImageView(this);
                imageview.setImageDrawable(getResources().getDrawable(R.drawable.defaultpic1));
                fl.addView(imageview);
                break;
            case 3:
                fl.setBackground(getResources().getDrawable(R.drawable.pattern2));
                break;
            case 4:

                break;
            case 5:

                break;
        }
    }

    public void button3clicked(View v) {
        switch (state) {
            case 0:
                state = 3;
                button1.setBackground(getResources().getDrawable(R.drawable.menu31));
                button2.setBackground(getResources().getDrawable(R.drawable.menu32));
                button3.setBackground(getResources().getDrawable(R.drawable.menu33));
                button4.setBackground(getResources().getDrawable(R.drawable.menu34));
                button5.setVisibility(View.VISIBLE);
                break;
            case 1:
                fl.setBackgroundColor(Color.MAGENTA);
                break;
            case 2:
                ImageView imageview = new ImageView(this);
                imageview.setImageDrawable(getResources().getDrawable(R.drawable.defaultpic2));
                fl.addView(imageview);
                break;
            case 3:
                fl.setBackground(getResources().getDrawable(R.drawable.pattern3));
                break;
            case 4:

                break;
            case 5:

                break;
        }
    }

    public void button4clicked(View v) {
        switch (state) {
            case 0:
                state = 4;
                button1.setBackground(getResources().getDrawable(R.drawable.menu41));
                button2.setBackground(getResources().getDrawable(R.drawable.menu42));
                button3.setBackground(getResources().getDrawable(R.drawable.menu43));
                button4.setBackground(getResources().getDrawable(R.drawable.menu44));
                button5.setVisibility(View.VISIBLE);
                break;
            case 1:
                fl.setBackgroundColor(Color.BLACK);
                break;
            case 2:
                ImageView imageview = new ImageView(this);
                imageview.setImageDrawable(getResources().getDrawable(R.drawable.defaultpic3));
                fl.addView(imageview);
                break;
            case 3:
                fl.setBackground(getResources().getDrawable(R.drawable.pattern4));
                break;
            case 4:

                break;
            case 5:

                break;
        }
    }

    public void button5clicked(View v) {
        switch (state) {
            default:
                state = 0;
                button1.setBackground(getResources().getDrawable(R.drawable.menu01));
                button2.setBackground(getResources().getDrawable(R.drawable.menu02));
                button3.setBackground(getResources().getDrawable(R.drawable.menu03));
                button4.setBackground(getResources().getDrawable(R.drawable.menu04));
                button5.setVisibility(View.GONE);
                break;
        }
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
            ImageView imgView = new ImageView(this);
            imgView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            fl.addView(imgView);
        }
    }
}