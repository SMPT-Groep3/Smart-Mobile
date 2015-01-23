package com.example.papple2;

import com.example.papple2.R.color;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class EditorActivity extends Activity {

	private static final int RESULT_LOAD_IMAGE = 1;
	private layerAdapter adapter;

	private int state = 0;
	private MenuItemProvider menuItemProvider;
	private Drawable[] menu0;
	private Drawable[] menu1;
	private Drawable[] menu2;
	private Drawable[] menu3;
	private Drawable[] menu4;
	private ListView listViewMenu;
	private Context context = this;
	private CanvasDraw canvasDraw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);

		canvasDraw = (CanvasDraw) findViewById(R.id.canvasDraw1);

		String part = getIntent().getStringExtra("part");
		Bitmap img = null;

		menu0 = new Drawable[] { 
				getResources().getDrawable(R.drawable.colorsselector),
				getResources().getDrawable(R.drawable.imageselector),
				getResources().getDrawable(R.drawable.textureselector),
				getResources().getDrawable(R.drawable.textselector) };
		menu1 = new Drawable[] { 
				getResources().getDrawable(R.drawable.rood),
				getResources().getDrawable(R.drawable.blauw),
				getResources().getDrawable(R.drawable.donkergroen),
				getResources().getDrawable(R.drawable.geel),
				getResources().getDrawable(R.drawable.colorpicker) };
		menu2 = new Drawable[] { 
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.colorpicker) };
		menu3 = new Drawable[] { 
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.colorpicker) };
		menu4 = new Drawable[] { 
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.colorpicker) };

		listViewMenu = (ListView) findViewById(R.id.listViewMenu);
		menuItemProvider = new MenuItemProvider(this, menu0);
		listViewMenu.setAdapter(menuItemProvider);

//		listViewMenu
//				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//					@Override
//					public void onItemClick(AdapterView<?> parent, View view,
//							int position, long id) {
//						if (position == 0) {
//							switch (state) {
//							case 0:
//								state = 1;
//								listViewMenu.setAdapter(new MenuItemProvider(
//										context, menu1));
//								break;
//							case 1:
//								// fl.setBackgroundColor(Color.RED);
//								// Set background color
//								break;
//							case 2:
//								// Default image 1
//								Intent intent = new Intent(
//										Intent.ACTION_PICK,
//										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//								startActivityForResult(intent,
//										RESULT_LOAD_IMAGE);
//								break;
//							case 3:
//								// fl.setBackground(getResources().getDrawable(
//								// R.drawable.pattern1));
//								// Set texture (pattern1) as background
//								break;
//							case 4:
//								// Select font
//								break;
//							case 5:
//								// free space for something nice
//								break;
//							}
//						} else if (position == 1) {
//							switch (state) {
//							case 0:
//								state = 2;
//								listViewMenu.setAdapter(new MenuItemProvider(
//										context, menu2));
//								break;
//							case 1:
//								// fl.setBackgroundColor(Color.BLUE);
//								// Set background color
//								break;
//							case 2:
//								// Default image 2
//								Intent intent = new Intent(
//										Intent.ACTION_PICK,
//										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//								startActivityForResult(intent,
//										RESULT_LOAD_IMAGE);
//								break;
//							case 3:
//								// fl.setBackground(getResources().getDrawable(
//								// R.drawable.pattern2));
//								// Set texture (pattern2) as background
//								break;
//							case 4:
//								// Bold italic en underline
//								break;
//							case 5:
//
//								break;
//							}
//						} else if (position == 2) {
//							switch (state) {
//							case 0:
//								state = 3;
//								listViewMenu.setAdapter(new MenuItemProvider(
//										context, menu3));
//								break;
//							case 1:
//								// fl.setBackgroundColor(Color.MAGENTA);
//								// Set background color
//								break;
//							case 2:
//								// Default image 2
//								Intent intent = new Intent(
//										Intent.ACTION_PICK,
//										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//								startActivityForResult(intent,
//										RESULT_LOAD_IMAGE);
//								break;
//							case 3:
//								// fl.setBackground(getResources().getDrawable(
//								// R.drawable.pattern2));
//								// Set texture (pattern2) as background
//								break;
//							case 4:
//								// Size
//								break;
//							}
//						} else if (position == 3) {
//							switch (state) {
//							case 0:
//								state = 4;
//								listViewMenu.setAdapter(new MenuItemProvider(
//												context, menu4));
//								break;
//							case 1:
//								// fl.setBackgroundColor(Color.BLACK);
//								// Set background color
//								break;
//							case 2:
//								// Default image 3
//								Intent intent = new Intent(
//										Intent.ACTION_PICK,
//										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//								startActivityForResult(intent,
//										RESULT_LOAD_IMAGE);
//								break;
//							case 3:
//								// fl.setBackground(getResources().getDrawable(
//								// R.drawable.pattern2));
//								// Set texture (pattern2) as background
//								break;
//							case 4:
//								// Tap screen to start typing
//								break;
//							}
//						} else if (position == 4) {
//
//						} else if (position == (listViewMenu.getCount() - 1)) {
//							state = 0;
//							listViewMenu.setAdapter(new MenuItemProvider(
//									context, menu0));
//						}
//					}
//				});
		
		listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (state) {
                    case 0:

                        switch (position) {
                            case 0:
                                // wat er gebeurt als je in het hoofdmenu op 1e knop drukt -> colors
                                state = 1;
                                listViewMenu.setAdapter(new MenuItemProvider(context, menu1));
                                break;
                            case 1:
                                // wat er gebeurt als je in het hoofdmenu op 2e knop drukt -> pictures
                                state = 2;
                                listViewMenu.setAdapter(new MenuItemProvider(context, menu2));
                                break;
                            case 2:
                                // wat er gebeurt als je in het hoofdmenu op 3e knop drukt -> patterns
                                state = 3;
                                listViewMenu.setAdapter(new MenuItemProvider(context, menu3));
                                break;
                            case 3:
                                // wat er gebeurt als je in het hoofdmenu op 4e knop drukt -> text
                                state = 4;
                                listViewMenu.setAdapter(new MenuItemProvider(context, menu4));
                                break;
                            case 4:
                                // unused
                                break;
                        }

                        break;
                    case 1:

                        switch (position) {
                            case 0:
                                // wat er gebeurt als je in het colors menu op 1e knop drukt
                            	//set background
                                break;
                            case 1:
                                // wat er gebeurt als je in het colors menu op 2e knop drukt
                            	canvasDraw.setBackgroundColor(color.blauw);
                                break;
                            case 2:
                                // wat er gebeurt als je in het colors menu op 3e knop drukt
                            	canvasDraw.setBackgroundColor(color.rood);
                                break;
                            case 3:
                                // wat er gebeurt als je in het colors menu op 4e knop drukt
                            	canvasDraw.setBackgroundColor(color.geel);
                                break;
                            case 4:
                                // nu nog back button
                                state = 0;
                                listViewMenu.setAdapter(new MenuItemProvider(context, menu0));
                                break;
                        }

                        break;
                    case 2:

                        switch (position) {
                            case 0:
                                // wat er gebeurt als je in het pictures menu op 1e knop drukt
                                break;
                            case 1:
                                // wat er gebeurt als je in het pictures menu op 2e knop drukt
                                break;
                            case 2:
                                // wat er gebeurt als je in het pictures menu op 3e knop drukt
                                break;
                            case 3:
                                // wat er gebeurt als je in het pictures menu op 4e knop drukt
                                break;
                            case 4:
                                // nu nog back button
                                state = 0;
                                listViewMenu.setAdapter(new MenuItemProvider(context, menu0));
                                break;
                        }

                        break;
                    case 3:

                        switch (position) {
                            case 0:
                                // wat er gebeurt als je in het patterns menu op 1e knop drukt
                                break;
                            case 1:
                                // wat er gebeurt als je in het patterns menu op 2e knop drukt
                                break;
                            case 2:
                                // wat er gebeurt als je in het patterns menu op 3e knop drukt
                                break;
                            case 3:
                                // wat er gebeurt als je in het patterns menu op 4e knop drukt
                                break;
                            case 4:
                                // nu nog back button
                                state = 0;
                                listViewMenu.setAdapter(new MenuItemProvider(context, menu0));
                                break;
                        }

                        break;
                    case 4:

                        switch (position) {
                            case 0:
                                // wat er gebeurt als je in het tekst menu op 1e knop drukt
                                break;
                            case 1:
                                // wat er gebeurt als je in het tekst menu op 2e knop drukt
                                break;
                            case 2:
                                // wat er gebeurt als je in het tekst menu op 3e knop drukt
                                break;
                            case 3:
                                // wat er gebeurt als je in het tekst menu op 4e knop drukt
                                break;
                            case 4:
                                // nu nog back button
                                state = 0;
                                listViewMenu.setAdapter(new MenuItemProvider(context, menu0));
                                break;
                        }

                        break;
                }


            }

        });

		if (part.equals("Cover")) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.shelloutline);
		} else if (part.equals("Shell")) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.shelld);
		} else if (part.equals("Screen")) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.screend);
		} else if (part.equals("Side")) {
			img = BitmapFactory
					.decodeResource(getResources(), R.drawable.sided);
		} else if (part.equals("Keyboard")) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.keyboardd);
		} else if (part.equals("Trackpad")) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.trackpadd);
		} else if (part.equals("Adapter")) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.adapterd);
		} else if (part.equals("Bottom")) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.bottomd);
		}

		if (img != null) {
			canvasDraw.setPartImage(img);
		}

		canvasDraw.invalidate();

		adapter = new layerAdapter(this);

		ListView list = (ListView) findViewById(R.id.listViewItems);
		list.setAdapter(adapter);

		Button importBtn = (Button) findViewById(R.id.buttonImport);
		importBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, RESULT_LOAD_IMAGE);
			}
		});
	}

	public void btnTextClick(View view) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				EditorActivity.this);
		alertDialog.setTitle("Text");
		alertDialog.setMessage("Add your Text");

		final EditText input = new EditText(EditorActivity.this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		input.setLayoutParams(lp);
		alertDialog.setView(input);

		alertDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						LayerItemProvider.getInstance().addItem(
								new LayerItem(input.getText().toString(),
										getApplicationContext()));
						adapter.notifyDataSetChanged();
						CanvasDraw cd = (CanvasDraw) findViewById(R.id.canvasDraw1);
						cd.invalidate();
						cd.resetMatrix();
					}
				});

		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		alertDialog.show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& data != null) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			Bitmap img = BitmapFactory.decodeFile(picturePath);
			Log.d("EDR", String.valueOf(img.getHeight()));
			LayerItemProvider.getInstance().addItem(new LayerItem(img));
			adapter.notifyDataSetChanged();
			CanvasDraw cd = (CanvasDraw) findViewById(R.id.canvasDraw1);
			cd.invalidate();
			cd.resetMatrix();
		}
	}

	public void btnClearClick(View view) {
		LayerItemProvider.getInstance().clearItems();
		adapter.notifyDataSetChanged();
		CanvasDraw cd = (CanvasDraw) findViewById(R.id.canvasDraw1);
		cd.invalidate();
		cd.resetMatrix();
	}

	public void btn3DPreviewClick(View view) {
		Intent intent = new Intent(this, DrieDRender.class);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		LayerItemProvider.getInstance().clearItems();
		finish();
	}

}
