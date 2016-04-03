package com.example.scott.guessit;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity /*implements SurfaceHolder.Callback*/{

    /*protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    private SurfaceView SurView;
    private SurfaceHolder camHolder;
    private boolean previewRunning;
    final Context context = this;
    public static Camera camera = null;*/

    private CameraDevice mCameraDevice;
    private SurfaceView mSurfaceView;
    public CameraCaptureSession cameraCaptureSession;

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

        mSurfaceView = (SurfaceView) findViewById(R.id.surface);

        CameraManager manager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics characteristics
                        = manager.getCameraCharacteristics(cameraId);
                Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) {
                    // front camera
                    continue;
                }
                manager.openCamera(cameraId, new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(CameraDevice camera) {
                       mCameraDevice = camera;
                    }

                    @Override
                    public void onDisconnected(CameraDevice camera) {

                    }

                    @Override
                    public void onError(CameraDevice camera, int error) {

                    }
                }, null);
                List<Surface> outputs = Arrays.asList(
                        mSurfaceView.getHolder().getSurface(), null);
                mCameraDevice.createCaptureSession(outputs, new CameraCaptureSession.StateCallback() {
                    @Override
                    public void onConfigured(CameraCaptureSession session) {
                        cameraCaptureSession = session;
                    }

                    @Override
                    public void onConfigureFailed(CameraCaptureSession session) {

                    }
                }, null);
            }
        }catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            // Currently an NPE is thrown when the Camera2API is used but not supported on the
            // device this code runs.
            e.printStackTrace();
        }

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
