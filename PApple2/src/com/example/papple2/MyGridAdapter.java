package com.example.papple2;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/** Deze klasse zorgt voor de invulling van de gridView */
public class MyGridAdapter extends BaseAdapter {

	private List<ItemPart> items = new ArrayList<ItemPart>();
	private LayoutInflater inflater;

	public MyGridAdapter(Context context, List<ItemPart> itemParts) {
		inflater = LayoutInflater.from(context);
		this.items = itemParts;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	/** suppresslint (newApi) voor de setBackground */
	@SuppressLint("NewApi")
	@Override
	public View getView(int i, View convertView, ViewGroup viewGroup) {
		View v = convertView;
		//De benodigde views
		
		TextView item1Name;
		ImageView item1Image;

		/** Als convertview(v) null is dan worden er nieuwe tags toegevoegd */
		if (v == null) {
			v = inflater.inflate(R.layout.row_grid, viewGroup, false);
			v.setTag(R.id.itemName, v.findViewById(R.id.itemName));
			v.setTag(R.id.itemImage, v.findViewById(R.id.itemImage));
		}

		/** alle Views worden geinstantieerd */
		item1Name = (TextView) v.getTag(R.id.itemName);
		item1Image = (ImageView) v.findViewById(R.id.itemImage);

		/** card wordt opgehaald uit de CardProvider */
		ItemPart item = (ItemPart) getItem(i);
		
		/** achtergrond van de grid item */
		View root = v.findViewById(R.id.rootItem);
		ColorDrawable cDrawable = (ColorDrawable) root.getBackground().mutate();
		
		cDrawable.setColor(item.getKleur());
		

		/** Informatie van de kaart wordt in de Views gezet */
//		if(!item.getAfbeeldingString().equals(""))
//		{
//			img.setImageBitmap(item.decodeBase64(item.getAfbeeldingString()));
//		}
//		else if(item.getHeeftAfbeelding() == true)
//		{
//			/** alpha (zichtbaarheid) wordt veranderd anders wordt er bij elk kaartje een photo weergegeven */
//			img.setAlpha(1f);
//			img.setImageDrawable(v.getResources().getDrawable(
//					item.getAfbeelding()));
//		}
//		else if(item.getHeeftAfbeelding() == false)
//		{
//			img.setAlpha(0f);
//		}
		
		item1Image.setImageDrawable(item.getAfbeelding());

		item1Name.setText(item.getNaam());

		return v;
	}

}
