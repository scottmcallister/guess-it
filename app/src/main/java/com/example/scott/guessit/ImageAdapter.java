package com.example.scott.guessit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by scott on 16-04-10.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mImagePaths;

    // Constructor
    public ImageAdapter(Context c, ArrayList<String> imagePaths) {
        mContext = c;
        mImagePaths = imagePaths;
    }

    public int getCount() {
        return mImagePaths.size();
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
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        File imgFile = new  File(mImagePaths.get(position));
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            imageView.setImageBitmap(myBitmap);

        }
        return imageView;
    }
}
