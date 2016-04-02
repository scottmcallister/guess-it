package com.example.scott.guessit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity /*implements SurfaceHolder.Callback*/{

    /*protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    private SurfaceView SurView;
    private SurfaceHolder camHolder;
    private boolean previewRunning;
    final Context context = this;
    public static Camera camera = null;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button galleryBtn = (Button) findViewById(R.id.gallery_btn);
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent goToGallery = new Intent(MainActivity.this, GalleryActivity.class);
                MainActivity.this.startActivity(goToGallery);
            }
        });

        final Button takePhotoBtn = (Button) findViewById(R.id.take_photo_btn);
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent goToGallery = new Intent(MainActivity.this, TakePhotoActivity.class);
                MainActivity.this.startActivity(goToGallery);
            }
        });

        /*SurView = (SurfaceView)findViewById(R.id.sview);
        camHolder = SurView.getHolder();
        camHolder.addCallback(this);
        camHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);*/
    }

    /*@Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        if(previewRunning){
            camera.stopPreview();
        }
        Camera.Parameters camParams = camera.getParameters();
        Camera.Size size = camParams.getSupportedPreviewSizes().get(0);
        camParams.setPreviewSize(size.width, size.height);
        camera.setParameters(camParams);
        try{
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            previewRunning=true;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try{
            camera=Camera.open();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera=null;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
