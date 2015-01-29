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
	private static CanvasDraw canvasDraw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);

		canvasDraw = (CanvasDraw) findViewById(R.id.canvasDraw1);

		String part = getIntent().getStringExtra("part");
		Bitmap img = null;

		/**
		 * Hoofdmenu 1. Kleuren 2. Afbeeldingen 3. Textures 4. Text 5. clear
		 */

		menu0 = new Drawable[] {
				getResources().getDrawable(R.drawable.colorsselector),
				getResources().getDrawable(R.drawable.imageselector),
				getResources().getDrawable(R.drawable.textureselector),
				getResources().getDrawable(R.drawable.textselector),
				getResources().getDrawable(R.drawable.clearselector) };

		/**
		 * Kleuren paar kleurtjes back
		 */
		menu1 = new Drawable[] { getResources().getDrawable(R.drawable.rood),
				getResources().getDrawable(R.drawable.blauw),
				getResources().getDrawable(R.drawable.donkergroen),
				getResources().getDrawable(R.drawable.geel),
				getResources().getDrawable(R.drawable.backselector) };

		/**
		 * Afbeeldingen 1. Import 2. Last used image 1 3. Last used image 2 4.
		 * Last used image 3 5. back
		 */
		menu2 = new Drawable[] {
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.backselector) };

		/**
		 * Textures 1. Texture 1 2. Texture 2 3. Texture 3 4. Texture 4 5. back
		 */
		menu3 = new Drawable[] {
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.importpressed),
				getResources().getDrawable(R.drawable.backselector) };

		/**
		 * Text Select font Bold Italic (Nog een selector/pressed?) Underline
		 * Size Tap screen, start typing Back
		 */
		menu4 = new Drawable[] { getResources().getDrawable(R.drawable.font),
				getResources().getDrawable(R.drawable.boldselector),
				getResources().getDrawable(R.drawable.italic),
				getResources().getDrawable(R.drawable.underlineselector),
				getResources().getDrawable(R.drawable.size),
				getResources().getDrawable(R.drawable.screentap),
				getResources().getDrawable(R.drawable.backselector) };

		listViewMenu = (ListView) findViewById(R.id.listViewMenu);
		menuItemProvider = new MenuItemProvider(this, menu0);
		listViewMenu.setAdapter(menuItemProvider);

		listViewMenu
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						switch (state) {
						case 0:

							switch (position) {
							case 0:
								// wat er gebeurt als je in het hoofdmenu op 1e
								// knop drukt -> colors
								state = 1;
								listViewMenu.setAdapter(new MenuItemProvider(
										context, menu1));
								break;
							case 1:
								// wat er gebeurt als je in het hoofdmenu op 2e
								// knop drukt -> pictures
								state = 2;
								listViewMenu.setAdapter(new MenuItemProvider(
										context, menu2));
								break;
							case 2:
								// wat er gebeurt als je in het hoofdmenu op 3e
								// knop drukt -> patterns
								state = 3;
								listViewMenu.setAdapter(new MenuItemProvider(
										context, menu3));
								break;
							case 3:
								// wat er gebeurt als je in het hoofdmenu op 4e
								// knop drukt -> text
								state = 4;
								listViewMenu.setAdapter(new MenuItemProvider(
										context, menu4));
								break;
							case 4:
								// unused
								clear();
								break;
							}

							break;
						case 1:

							switch (position) {
							case 0:
								// wat er gebeurt als je in het colors menu op
								// 1e knop drukt
								// set background
								LayerItemProvider.getInstance().addItem(new LayerItem(color.rood, context));
								adapter.notifyDataSetChanged();
								canvasDraw.invalidate();
								break;
							case 1:
								// wat er gebeurt als je in het colors menu op
								// 2e knop drukt
								drawColor(color.blauw);
								break;
							case 2:
								// wat er gebeurt als je in het colors menu op
								// 3e knop drukt
								canvasDraw.setBackgroundColor(color.rood);
								break;
							case 3:
								// wat er gebeurt als je in het colors menu op
								// 4e knop drukt
								canvasDraw.setBackgroundColor(color.geel);
								break;
							case 4:
								// nu nog back button
								state = 0;
								listViewMenu.setAdapter(new MenuItemProvider(
										context, menu0));
								break;
							}

							break;
						case 2:

							switch (position) {
							case 0:
								importPicture();
								break;
							case 1:
								// wat er gebeurt als je in het pictures menu op
								// 2e knop drukt
								break;
							case 2:
								// wat er gebeurt als je in het pictures menu op
								// 3e knop drukt
								break;
							case 3:
								// wat er gebeurt als je in het pictures menu op
								// 4e knop drukt
								break;
							case 4:
								// nu nog back button
								state = 0;
								listViewMenu.setAdapter(new MenuItemProvider(
										context, menu0));
								break;
							}

							break;
						case 3:

							switch (position) {
							case 0:
								// wat er gebeurt als je in het patterns menu op
								// 1e knop drukt
								break;
							case 1:
								// wat er gebeurt als je in het patterns menu op
								// 2e knop drukt
								break;
							case 2:
								// wat er gebeurt als je in het patterns menu op
								// 3e knop drukt
								break;
							case 3:
								// wat er gebeurt als je in het patterns menu op
								// 4e knop drukt
								break;
							case 4:
								// nu nog back button
								state = 0;
								listViewMenu.setAdapter(new MenuItemProvider(
										context, menu0));
								break;
							}

							break;
						case 4:

							switch (position) {
							case 0:
								// select font
								break;
							case 1:
								// bold
								break;
							case 2:
								// italic
								break;
							case 3:
								// underline
								break;
							case 4:
								// Size
								break;
							case 5:
								// Tap the screen to start typing??
								createText();
								break;
							case 6:
								state = 0;
								listViewMenu.setAdapter(new MenuItemProvider(
										context, menu0));
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
				importPicture();
			}
		});
	}
	
	private void drawColor(int color) {
		if(LayerItemProvider.getInstance().hasBackgroundColor() == false)
		{
			LayerItemProvider.getInstance().addItem(new LayerItem(color, context));
			adapter.notifyDataSetChanged();
			canvasDraw.invalidate();
		}
		else
		{
			//Anders out of memory
			Log.d("EDR", "Geen extra achtergrond");
		}
		
	}

	private void importPicture() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, RESULT_LOAD_IMAGE);
	}

	public void btnTextClick(View view) {
		createText();
	}

	private void createText() {
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
		clear();
	}

	private void clear() {
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
	
	static View getCanvas()
	{
		return canvasDraw;
	}

}
