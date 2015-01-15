package papple.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureArrayAdapter extends ArrayAdapter<Drawable> {
    private final Context context;
    private final Drawable[] values;

    public PictureArrayAdapter(Context context, Drawable[] values) {
        super(context, R.layout.menulistitem, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View myView = convertView;

        if(myView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myView  = inflater.inflate(R.layout.menulistitem, null);
        }

        ImageView image = (ImageView) myView.findViewById(R.id.PlayerPicture);
        image.setImageDrawable(values[position]);

        return myView;
    }
}