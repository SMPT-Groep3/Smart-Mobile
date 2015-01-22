package papple.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;




public class MainActivity extends ActionBarActivity {

    int state = 0;
    PictureArrayAdapter picarray;
    Drawable[] menu0;
    Drawable[] menu1;
    Drawable[] menu2;
    Drawable[] menu3;
    Drawable[] menu4;
    ListView mainListView;
    Context main = this;
    FrameLayout fl;
    private static final int RESULT_LOAD_IMAGE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu0 = new Drawable[]{getResources().getDrawable(R.drawable.menu01), getResources().getDrawable(R.drawable.menu02), getResources().getDrawable(R.drawable.menu03), getResources().getDrawable(R.drawable.menu04)};
        menu1 = new Drawable[]{getResources().getDrawable(R.drawable.menu11), getResources().getDrawable(R.drawable.menu12), getResources().getDrawable(R.drawable.menu13), getResources().getDrawable(R.drawable.menu14), getResources().getDrawable(R.drawable.menu05)};
        menu2 = new Drawable[]{getResources().getDrawable(R.drawable.menu21), getResources().getDrawable(R.drawable.menu22), getResources().getDrawable(R.drawable.menu23), getResources().getDrawable(R.drawable.menu24), getResources().getDrawable(R.drawable.menu05)};
        menu3 = new Drawable[]{getResources().getDrawable(R.drawable.menu31), getResources().getDrawable(R.drawable.menu32), getResources().getDrawable(R.drawable.menu33), getResources().getDrawable(R.drawable.menu34), getResources().getDrawable(R.drawable.menu05)};
        menu4 = new Drawable[]{getResources().getDrawable(R.drawable.menu41), getResources().getDrawable(R.drawable.menu42), getResources().getDrawable(R.drawable.menu43), getResources().getDrawable(R.drawable.menu44), getResources().getDrawable(R.drawable.menu05)};

        fl = (FrameLayout) findViewById(R.id.mainframelayout);

        mainListView = (ListView) findViewById(R.id.mainListView);


        picarray = new PictureArrayAdapter(this, menu0);

        mainListView.setAdapter(picarray);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    switch (state) {
                        case 0:
                            state = 1;
                            mainListView.setAdapter(new PictureArrayAdapter(main, menu1));
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
                else if( position == 1)
                {
                    switch (state) {
                        case 0:
                            state = 2;
                            mainListView.setAdapter(new PictureArrayAdapter(main, menu2));
                            break;
                        case 1:
                            fl.setBackgroundColor(Color.BLUE);
                            break;
                        case 2:
                            ImageView imageview = new ImageView(main);
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
                else if (position == 2)
                {
                    switch (state) {
                        case 0:
                            state = 3;
                            mainListView.setAdapter(new PictureArrayAdapter(main, menu3));
                            break;
                        case 1:
                            fl.setBackgroundColor(Color.MAGENTA);
                            break;
                        case 2:
                            ImageView imageview = new ImageView(main);
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
                else if(position == 3)
                {
                    switch (state) {
                        case 0:
                            state = 4;
                            mainListView.setAdapter(new PictureArrayAdapter(main, menu4));
                            break;
                        case 1:
                            fl.setBackgroundColor(Color.BLACK);
                            break;
                        case 2:
                            ImageView imageview = new ImageView(main);
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
                else if(position == 4)
                {
                    switch (state) {
                        default:
                            state = 0;
                            mainListView.setAdapter(new PictureArrayAdapter(main, menu0));
                            break;
                    }
                }
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
            ImageView imgView = new ImageView(this);
            imgView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            fl.addView(imgView);
        }
    }
}
