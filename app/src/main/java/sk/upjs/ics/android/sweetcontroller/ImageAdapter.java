package sk.upjs.ics.android.sweetcontroller;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

public class ImageAdapter extends BaseAdapter {//BaseAdapter
    private Context mContext;
    private int meal;

    public ImageAdapter(Context c, int meal) {

        mContext = c;
        this.meal=meal;
    }

    public int getCount() {
        if (meal==1) {
            return mThumbIds.length;
        }
        if (meal==2) {
            return mThumbIds1.length;
        }
        return mThumbIds2.length;

    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(185, 185));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
    if (meal==1) {
        imageView.setImageResource(mThumbIds[position]);
    }
        if (meal==2) {
            imageView.setImageResource(mThumbIds1[position]);
        }
        if (meal==3) {
            imageView.setImageResource(mThumbIds2[position]);
        }
        return imageView;
    }

    // references to our images
    Integer[] mThumbIds= {
             R.drawable.apple1,
           R.drawable.banana1, R.drawable.grapes1
//            R.drawable.roll, R.drawable.chocolate

    };

    Integer[] mThumbIds1= {
            R.drawable.tomatosoup, R.drawable.chickenmeat, R.drawable.potatoes, R.drawable.rice, R.drawable.dumpling, R.drawable.pasta, R.drawable.beef, R.drawable.pork,
            R.drawable.spaghetti, R.drawable.spinach, R.drawable.beans, R.drawable.lentil, R.drawable.vegetables, R.drawable.juice, R.drawable.fish


    };

    Integer[] mThumbIds2= {
            R.drawable.sausages, R.drawable.roll, R.drawable.bread, R.drawable.milk, R.drawable.ham, R.drawable.pineapple, R.drawable.vegetables,
            R.drawable.marmelade, R.drawable.cheese, R.drawable.beer, R.drawable.carrot, R.drawable.peas, R.drawable.wine, R.drawable.pear, R.drawable.chocolate1


    };
}