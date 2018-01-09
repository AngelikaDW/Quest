package nl.aleaf.quest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by angelika on 04/01/2018.
 */



public class StoneAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Stone> mDataSource;

    public StoneAdapter(Context context, ArrayList<Stone> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1 getCount() lets ListView know how many items to display, or in other words,
    // it returns the size of your data source.
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2 getItem() returns an item to be placed in a given position from the data source,
    // specifically, Recipe objects obtained from mDataSource.
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3 This implements the getItemId() method that defines a unique ID for each row in the list.
    // For simplicity, you just use the position of the item as its ID.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4 Finally, getView() creates a view to be used as a row in the list.
    // Here you define what information shows and where it sits within the ListView.
    // You also inflate a custom view from the XML layout defined in
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_stone, parent, false);

        // Get id element
        TextView idTextView =
                (TextView) rowView.findViewById(R.id.id);

        // Get subtitle element
        TextView nameTextView =
                (TextView) rowView.findViewById(R.id.name);

        // Get detail element
        TextView addressTextView =
                (TextView) rowView.findViewById(R.id.addres);

        // Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.stone_thumnail);


        // 1 Getting the corresponding stone for the current row.
        Stone stone = (Stone) getItem(position);

        // 2 Updating the row view's text views so they are displaying the stone.
        idTextView.setText(String.valueOf(stone.id));
        nameTextView.setText(stone.name);
        addressTextView.setText(stone.addres);
        int id;

        //TODO display images from drawable R.drawable.image+stone.id
//        String idImage = ("R.drawable.image"+stone.id);
//                //.getIdetifier("R.drawable/"+stone.imageUrl, null, null);
//        Log.i("StoneAdapter Image", idImage);
//
//        // Set image in the detail view from drawable folder, based on the running Number
//        // as extracted from the database
//        String uri = "@drawable/image" + stone.id;
//        int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());
//        thumbnailImageView.setImageResource(imageResource);

//        //Image of Checkbox, if the data is 0, no match yet --> unchecked box
//        // if data in db is 1, location of user and stone matched --> checked box
//        if (stoneMatch.equals("0")){
//            checkboxImageView.setImageResource(R.drawable.match_grey);
//
//        } else {
//            checkboxImageView.setImageResource(R.drawable.match_green);
//            // Extract how many matches there are
//            matches.add(stoneMatch);}


        // 3 Making use of the open-source Picasso library for asynchronous image loading --
        // it helps you download the thumbnail images on a separate thread instead of the main thread.
        // You're also assigning a temporary placeholder for the ImageView to handle slow loading of images.
        //Picasso.with(mContext).load(String.valueOf(thumbnailImageView)).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);


        return rowView;
    }
}
