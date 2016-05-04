package com.example.scott.guessit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryActivity extends AppCompatActivity {

    private Cursor imagecursor, actualimagecursor;
    private int image_column_index, actual_image_column_index;
    GridView imageGrid;
    private int count;
    private String[] imagePaths;
    private int MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }



        //init_phone_image_grid();
    }

    private String[] getImagePaths(){
        Uri uri;
        Cursor cursor;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };

        cursor = this.getContentResolver().query(uri, projection, null, null, MediaStore.Images.Media._ID);

        int count = cursor.getCount();

        //Create an array to store path to all the images
        String[] arrPath = new String[count];

        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            //Store the path of the image
            arrPath[i]= cursor.getString(dataColumnIndex);
            Log.i("PATH", arrPath[i]);
        }
        return arrPath;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted

                    imageGrid = (GridView)findViewById(R.id.photo_grid);
                    imagePaths = getImagePaths();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_list_item_1, imagePaths);
                    imageGrid.setAdapter(adapter);

                } else {

                    // permission denied
                    Intent goToGallery = new Intent(GalleryActivity.this, MainActivity.class);
                    GalleryActivity.this.startActivity(goToGallery);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /*private void init_phone_image_grid() {
        String[] img = {MediaStore.Images.Thumbnails._ID};
        imagecursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, img, MediaStore.Images.Thumbnails.DATA + "='/sdcard/DCIM/Camera/*.*'", null, MediaStore.Images.Thumbnails.IMAGE_ID + "");

        image_column_index = imagecursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
        count = imagecursor.getCount();
        imagegrid = (GridView) findViewById(R.id.photo_grid);
        imagegrid.setAdapter(new ImageAdapter(getApplicationContext()));
        imagegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                System.gc();
                String[] proj = {MediaStore.Images.Media.DATA};
                actualimagecursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj, MediaStore.Images.Media.DATA + "='/sdcard/DCIM/Camera/*.*'", null, null);
                actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToPosition(position);
                String i = actualimagecursor.getString(actual_image_column_index);
                System.gc();

                Intent objI = getIntent();
                objI.putExtra("clickedFile", i);
                setResult(100, objI);
                finish();
            }
        });
    }*/

    public class ImageAdapter extends BaseAdapter
    {
        private Context mContext;

        public ImageAdapter(Context c)
        {
            mContext = c;
        }

        public int getCount()
        {
            return count;
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(int position,View convertView,ViewGroup parent)
        {
            System.gc();
            ImageView i = new ImageView(mContext.getApplicationContext());
            if (convertView == null)
            {
                imagecursor.moveToPosition(position);
                int id = imagecursor.getInt(image_column_index);

                i.setImageURI(Uri.withAppendedPath( MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + id));

                i.setScaleType(ImageView.ScaleType.CENTER_CROP);
                i.setLayoutParams(new GridView.LayoutParams(92, 92));
            }
            else{
                i = (ImageView) convertView;
            }
            return i;
        }
    }
}

