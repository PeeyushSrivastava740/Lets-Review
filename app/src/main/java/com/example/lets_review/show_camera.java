package com.example.lets_review;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;

import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;
import java.util.List;

public class show_camera extends SurfaceView implements SurfaceHolder.Callback {
    Camera camera;
    SurfaceHolder holder=null;
    SquareCameraPreview camerapreview;
    private static final int PICTURE_SIZE_MAX_WIDTH = 1280;

    private static final int PREVIEW_SIZE_MAX_WIDTH = 640;
    private static final double ASPECT_RATIO = 3.0 / 4.0;
    public show_camera(Context context,Camera camera) {
        super(context);
        this.camera=camera;
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Camera.Parameters parameters=camera.getParameters();


        //  Camera.Parameters parameters=camera.getParameters();
        // for changing the orientation of camera
        if(this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE){

            parameters.set("orientation","portrait");
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            parameters.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
            parameters.setRotation(90);
        }
        else
        {
            parameters.set("orientation","landscape");

            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            parameters.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
            parameters.setRotation(0);

        }
        camera.setParameters(parameters);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        refreshCamera(camera);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


    }
    public void refreshCamera(Camera camera) {
        if (holder.getSurface() == null) {
            // preview surface does not exist
            return;
        }
        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }
        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        setCamera(camera);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e) {
            Log.d(VIEW_LOG_TAG, "Error starting camera preview: " + e.getMessage());
        }
    }


    public void setCamera(Camera mcamera) {
        //method to set a camera instance

        camera = mcamera;
    }



    private Size determineBestPreviewSize(Camera.Parameters parameters) {

        return determineBestSize(parameters.getSupportedPreviewSizes(), PREVIEW_SIZE_MAX_WIDTH);

    }

    private Size determineBestSize(List<Size> supportedPreviewSizes, int previewSizeMaxWidth) {

        Size bestSize = null;

        Size size;

        int numOfSizes = supportedPreviewSizes.size();

        for (int i = 0; i < numOfSizes; i++) {

            size = supportedPreviewSizes.get(i);

            boolean isDesireRatio = (size.width / 4) == (size.height / 3);

            boolean isBetterSize = (bestSize == null) || size.width > bestSize.width;



            if (isDesireRatio && isBetterSize) {

                bestSize = size;

            }

        }
        if (bestSize == null) {

            Log.d("123", "cannot find the best camera size");

            return supportedPreviewSizes.get(supportedPreviewSizes.size() - 1);

        }



        return bestSize;
    }


    private Size determineBestPictureSize(Camera.Parameters parameters) {

        return determineBestSize(parameters.getSupportedPictureSizes(), PICTURE_SIZE_MAX_WIDTH);

    }

    public void setupCamera() {

        // Never keep a global parameters

        Camera.Parameters parameters = camera.getParameters();



        Size bestPreviewSize = determineBestPreviewSize(parameters);

        Size bestPictureSize = determineBestPictureSize(parameters);



        parameters.setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);

        parameters.setPictureSize(bestPictureSize.width, bestPictureSize.height);





        // Set continuous picture focus, if it's supported

        if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {

            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);

        }



       // final View changeCameraFlashModeBtn = getView().findViewById(R.id.flash);

        List<String> flashModes = parameters.getSupportedFlashModes();

        if (flashModes != null && flashModes.contains(Custom_Camera_Activity.mFlashMode)) {

            parameters.setFlashMode(Custom_Camera_Activity.mFlashMode);

            Custom_Camera_Activity.flashview.setVisibility(View.VISIBLE);

        } else {

            Custom_Camera_Activity.flashview.setVisibility(View.INVISIBLE);

        }



        // Lock in the changes

        camera.setParameters(parameters);

    }


    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = MeasureSpec.getSize(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);



        final boolean isPortrait =

                getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;



        if (isPortrait) {

            if (width > height * ASPECT_RATIO) {

                width = (int) (height * ASPECT_RATIO + 0.5);

            } else {

                height = (int) (width / ASPECT_RATIO + 0.5);

            }

        } else {

            if (height > width * ASPECT_RATIO) {

                height = (int) (width * ASPECT_RATIO + 0.5);

            } else {

                width = (int) (height / ASPECT_RATIO + 0.5);

            }

        }

        setMeasuredDimension(width, height);

    }


}
