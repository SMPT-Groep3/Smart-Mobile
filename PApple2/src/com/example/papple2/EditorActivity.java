package com.example.papple2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

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

	private static Paint paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);

		paint = new Paint();
		paint.setTextSize(130f);

		canvasDraw = (CanvasDraw) findViewById(R.id.canvasDraw1);
		Log.d("EDR", "oncreate van EditorActivity aangeroepen");
		String part = getIntent().getStringExtra("part");
		Bitmap img = null;

		if (menu0 == null && menu1 == null & menu2 == null && menu3 == null
				&& menu4 == null) {
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
			menu1 = new Drawable[] {
					getResources().getDrawable(R.drawable.rood),
					getResources().getDrawable(R.drawable.blauw),
					getResources().getDrawable(R.drawable.donkergroen),
					getResources().getDrawable(R.drawable.geel),
					getResources().getDrawable(R.drawable.backselector) };

			/**
			 * Afbeeldingen 1. Import 2. Last used image 1 3. Last used image 2
			 * 4. Last used image 3 5. back
			 */
			menu2 = new Drawable[] {
					getResources().getDrawable(R.drawable.inportselector),
					getResources().getDrawable(R.drawable.imageoneselector),
					getResources().getDrawable(R.drawable.imagetwoselector),
					getResources().getDrawable(R.drawable.imagefourselector),
					getResources().getDrawable(R.drawable.backselector) };

			/**
			 * Textures 1. Texture 1 2. Texture 2 3. Texture 3 4. Texture 4 5.
			 * back
			 */
			menu3 = new Drawable[] {
					getResources().getDrawable(R.drawable.textureoneselector),
					getResources().getDrawable(R.drawable.texturetwoselector),
					getResources().getDrawable(R.drawable.texturethreeselector),
					getResources().getDrawable(R.drawable.texturefourselector),
					getResources().getDrawable(R.drawable.backselector) };

			/**
			 * Text Select font Bold Italic (Nog een selector/pressed?)
			 * Underline Size Tap screen, start typing Back
			 */
			menu4 = new Drawable[] {
					getResources().getDrawable(R.drawable.font),
					getResources().getDrawable(R.drawable.boldselector),
					getResources().getDrawable(R.drawable.italic),
					getResources().getDrawable(R.drawable.underlineselector),
					getResources().getDrawable(R.drawable.size),
					getResources().getDrawable(R.drawable.screentap),
					getResources().getDrawable(R.drawable.backselector) };
			listViewMenu = (ListView) findViewById(R.id.listViewMenu);
			menuItemProvider = new MenuItemProvider(this, menu0);
			listViewMenu.setAdapter(menuItemProvider);

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
				img = BitmapFactory.decodeResource(getResources(),
						R.drawable.sided);
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

			Button buttonSave = (Button) findViewById(R.id.buttonSave);
			buttonSave.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast t = Toast.makeText(context,
							"Project Save: Your laptop just got pimp'ed",
							Toast.LENGTH_SHORT);
					t.show();
				}
			});

			canvasDraw.invalidate();

			adapter = new layerAdapter(this);

			ListView list = (ListView) findViewById(R.id.listViewItems);
			list.setAdapter(adapter);
		}

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
								addTexture(R.drawable.rood);
								break;
							case 1:
								// wat er gebeurt als je in het colors menu op
								// 2e knop drukt
								addTexture(R.drawable.blauw);
								break;
							case 2:
								// wat er gebeurt als je in het colors menu op
								// 3e knop drukt
								addTexture(R.drawable.donkergroen);
								break;
							case 3:
								// wat er gebeurt als je in het colors menu op
								// 4e knop drukt
								addTexture(R.drawable.geel);
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
								drawPicture(R.drawable.imageonebig);
								break;
							case 2:
								// wat er gebeurt als je in het pictures menu op
								// 3e knop drukt
								drawPicture(R.drawable.imagetwobig);
								break;
							case 3:
								// wat er gebeurt als je in het pictures menu op
								// 4e knop drukt
								drawPicture(R.drawable.imagefourbig);
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
								addTexture(R.drawable.patternonebig);
								break;
							case 1:
								// wat er gebeurt als je in het patterns menu op
								// 2e knop drukt
								addTexture(R.drawable.patterntwobig);
								break;
							case 2:
								// wat er gebeurt als je in het patterns menu op
								// 3e knop drukt
								addTexture(R.drawable.patternthreebig);
								break;
							case 3:
								// wat er gebeurt als je in het patterns menu op
								// 4e knop drukt
								addTexture(R.drawable.patternfourbig);
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
								Toast t = Toast.makeText(context,
										"Italic much1?", Toast.LENGTH_SHORT);
								t.show();
								CheckBox cb = (CheckBox) view;
								if (cb.isChecked()) {
									paint.setTextSkewX(-0.25f);
									Log.d("EDR", "Italic much?");
								} else {
									paint.setTextSkewX(0f);
								}

								break;
							case 3:
								// underline
								break;
							case 4:
								// Size
								numberpicker();
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
	}

	protected void drawPicture(int picture) {
		// TODO Auto-generated method stub
		LayerItemProvider.getInstance().addItem(
				new LayerItem(BitmapFactory.decodeResource(getResources(),
						picture)));
		adapter.notifyDataSetChanged();
		canvasDraw.invalidate();
	}

	private void addTexture(int pattern) {
		if (LayerItemProvider.getInstance().hasBackgroundColor() == false) {
			LayerItemProvider.getInstance().addItem(
					new LayerItem(pattern, true, context));
			adapter.notifyDataSetChanged();
			canvasDraw.invalidate();
		} else {
			Toast t = Toast.makeText(context,
					"Only 1 texture/background color allowed",
					Toast.LENGTH_SHORT);
			t.show();
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
										getApplicationContext(), paint));
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
	
	private void numberpicker()
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				EditorActivity.this);
		alertDialog.setTitle("Font size");
		alertDialog.setMessage("Select your font size");
		
		final NumberPicker np = new NumberPicker(EditorActivity.this);
		np.setMinValue(1);
		np.setMaxValue(25);
		np.setWrapSelectorWheel(false);
		np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		np.setLayoutParams(lp);
		alertDialog.setView(np);

		alertDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						int value = np.getValue() * 10;
						paint.setTextSize((float)value);
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

	public void previewClick(View view) {
		Intent intent = new Intent(this, DrieDRender.class);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		LayerItemProvider.getInstance().clearItems();
		finish();
	}

	static View getCanvas() {
		return canvasDraw;
	}

	static void setItalic(boolean checked) {
		if (checked) {
			paint.setTextSkewX(-0.25f);
		} else {
			paint.setTextSkewX(0);
		}

	}

	static void setBold(boolean checked) {
		if (checked) {
			paint.setTypeface(Typeface.DEFAULT_BOLD);
		} else {
			paint.setTypeface(null);
		}

	}

	static void setUnderline(boolean checked) {
		if (checked) {
			paint.setUnderlineText(true);
		} else {
			paint.setUnderlineText(false);
		}

	}

}
