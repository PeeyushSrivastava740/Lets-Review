package com.example.lets_review;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class Custom_Camera_Activity extends AppCompatActivity {

    private static final int Request_Camera = 100;
    private static final int PICK_IMAGE_REQUEST =100 ;
    public static final String CAMERA_FLASH_KEY = "flash_mode";
    private boolean safeToTakePicture = false;
    public static final String CAMERA_ID_KEY = "camera_id";
    private static final String IMAGE_DIRECTORY = "/CustomImage";
   public static Button flashview;
    public static  String mFlashMode;
    String imagePath = "";
    Camera camera;
    FrameLayout framelayout;
    SquareCameraPreview camerapreview;
    show_camera show;
    Camera.PictureCallback mPicture;
    Button captue_image, open_gallery, camera_switch;
    private boolean cameraFront = false;

    private static final String FLASH_MODE = "squarecamera__flash_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__camera_);
        flashview=(Button) findViewById(R.id.flash_icon);
        framelayout = (FrameLayout) findViewById(R.id.framelayout);

        camera = Camera.open();
        camera.setDisplayOrientation(90);
        mPicture = getPictureCallback();
       // mFlashMode = savedInstanceState.getString(CAMERA_FLASH_KEY);
        mFlashMode = getCameraFlashMode(getApplicationContext());
        Log.e("123","nkk"+mFlashMode);
        show = new show_camera(Custom_Camera_Activity.this, camera);
        framelayout.addView(show);

        captue_image = (Button) findViewById(R.id.forcapture);
        open_gallery = (Button) findViewById(R.id.forgallery);

        camera_switch = (Button) findViewById(R.id.switch_camera);
        //for capturing image
        captue_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("123456", "take picture");
                camera.takePicture(null, null, mPicture);

            }
        });
        flashview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlashMode.equalsIgnoreCase(Camera.Parameters.FLASH_MODE_AUTO)) {

                    mFlashMode = Camera.Parameters.FLASH_MODE_ON;

                } else if (mFlashMode.equalsIgnoreCase(Camera.Parameters.FLASH_MODE_ON)) {

                    mFlashMode = Camera.Parameters.FLASH_MODE_OFF;

                } else if (mFlashMode.equalsIgnoreCase(Camera.Parameters.FLASH_MODE_OFF)) {

                    mFlashMode = Camera.Parameters.FLASH_MODE_AUTO;

                }

                setupFlashMode();
                show.setCamera(camera);

            }
        });


        //to open  phone gallery
        open_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 0);
            }
        });

        //to switch camera
        camera_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the number of cameras
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    Log.e("123456", "switch camera");
                    //release the old camera instance
                    //switch camera, from the front and the back and vice versa
                    releaseCamera();
                    chooseCamera();
                }

            }
        });
        camera.startPreview();
    }


    private void releaseCamera() {
        // stop and release camera
        if (camera != null) {
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
        }
    }

    public void chooseCamera() {
        //if the camera preview is the front

        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                camera = Camera.open(cameraId);
                camera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                show.refreshCamera(camera);
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                camera = Camera.open(cameraId);
                camera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                show.refreshCamera(camera);
            }
        }

    }

    private void setupFlashMode() {
       final TextView autoFlashIcon = (TextView) findViewById(R.id.autoflashicon);

        if (Camera.Parameters.FLASH_MODE_AUTO.equalsIgnoreCase(mFlashMode)) {

            autoFlashIcon.setText("Auto");

        } else if (Camera.Parameters.FLASH_MODE_ON.equalsIgnoreCase(mFlashMode)) {

            autoFlashIcon.setText("On");

        }  else if (Camera.Parameters.FLASH_MODE_OFF.equalsIgnoreCase(mFlashMode)) {

            autoFlashIcon.setText("Off");

        }

    }



    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;
    }

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Context inContext =getApplicationContext();
             Bitmap bitmap=decodeSampledBitmapFromByte(Custom_Camera_Activity.this,data);
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                Bitmap resized = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.7), (int)(bitmap.getHeight()*0.7), true);
                Log.e("1234", "Picture" + bitmap);
                saveImage(bitmap);


                if (imagePath!=""){
                    Intent intent = new Intent(Custom_Camera_Activity.this,filters.class);
                    //intent.putExtra("image_uri",imageuri);
                    // intent.putExtra("bitmapImg",resized);
                    intent.putExtra("imagePath",imagePath);
                    startActivity(intent);
                }else {
                    Bitmap thumbImage = Bitmap.createScaledBitmap(resized, 640, 640, false);
                    imagePath = getBase64String(thumbImage);
                    Intent intent = new Intent(Custom_Camera_Activity.this,filters.class);
                    //intent.putExtra("image_uri",imageuri);
                    // intent.putExtra("bitmapImg",resized);
                    intent.putExtra("imageStr",imagePath);
                    startActivity(intent);
                }

            }
        };
        return picture;
    }

    public static Bitmap decodeSampledBitmapFromByte(Context context, byte[] bitmapBytes) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int reqWidth, reqHeight;
        Point point = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(point);
            reqWidth = point.x;
            reqHeight = point.y;
        } else {
            reqWidth = display.getWidth();
            reqHeight = display.getHeight();
        }


        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inMutable = true;
        options.inBitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Load & resize the image to be 1/inSampleSize dimensions
        // Use when you do not want to scale the image with a inSampleSize that is a power of 2
        options.inScaled = true;
        options.inDensity = options.outWidth;
        options.inTargetDensity = reqWidth * options.inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false; // If set to true, the decoder will return null (no bitmap), but the out... fields will still be set, allowing the caller to query the bitmap without having to allocate the memory for its pixels.
        options.inPurgeable = true;         // Tell to gc that whether it needs free memory, the Bitmap can be cleared
        options.inInputShareable = true;    // Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future

        return BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length, options);
    }


    public static String convertBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        return Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
    }

    public static byte[] convertBitmapStringToByteArray(String bitmapByteString) {
        return Base64.decode(bitmapByteString, Base64.DEFAULT);
    }
    public static Bitmap decodeSampledBitmapFromPath(String path, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inMutable = true;
        options.inBitmap = BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inScaled = true;
        options.inDensity = options.outWidth;
        options.inTargetDensity = reqWidth * options.inSampleSize;

        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;

        return BitmapFactory.decodeFile(path, options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int initialInSampleSize = computeInitialSampleSize(options, reqWidth, reqHeight);

        int roundedInSampleSize;
        if (initialInSampleSize <= 8) {
            roundedInSampleSize = 1;
            while (roundedInSampleSize < initialInSampleSize) {
                // Shift one bit to left
                roundedInSampleSize <<= 1;
            }
        } else {
            roundedInSampleSize = (initialInSampleSize + 7) / 8 * 8;
        }

        return roundedInSampleSize;
    }



    private static int computeInitialSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final double height = options.outHeight;
        final double width = options.outWidth;

        final long maxNumOfPixels = reqWidth * reqHeight;
        final int minSideLength = Math.min(reqHeight, reqWidth);

        int lowerBound = (maxNumOfPixels < 0) ? 1 :
                (int) Math.ceil(Math.sqrt(width * height / maxNumOfPixels));
        int upperBound = (minSideLength < 0) ? 128 :
                (int) Math.min(Math.floor(width / minSideLength),
                        Math.floor(height / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if (maxNumOfPixels < 0 && minSideLength < 0) {
            return 1;
        } else if (minSideLength < 0) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
    private int findBackFacingCamera() {
        int cameraId = -1;
        //Search for the back facing camera
        //get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        //for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;

            }

        }
        return cameraId;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imageuri=data.getData();
        String filePath = String.valueOf(imageuri);
       // Log.e("6565656565",filePath);
        String [] filepathcolumn={MediaStore.Images.Media.DATA};
        Cursor cursor = getApplicationContext().getContentResolver().query(imageuri, null, null, null, null);
        cursor.moveToFirst();
        int ColumnIndex=cursor.getColumnIndex(filepathcolumn[0]);
        String picturepath =cursor.getString(ColumnIndex);
        Log.e("656565655",picturepath);
        if(imageuri!=null) {
            Intent intent = new Intent(Custom_Camera_Activity.this, filters.class);
            intent.putExtra("imagePath", picturepath);
            startActivity(intent);
        }

    }


    public void onResume() {

        super.onResume();
        if (camera == null) {
            camera = Camera.open();
            camera.setDisplayOrientation(90);
            mPicture = getPictureCallback();
            show.refreshCamera(camera);
            Log.d("nu", "null");
        } else {
            Log.d("nu", "no null");
        }
    }

    private String saveImage(Bitmap bitmap) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.

        if (!wallpaperDirectory.exists()) {
            Log.e("dirrrrrr", "" + wallpaperDirectory.mkdirs());
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();   //give read write permission
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            imagePath = f.getAbsolutePath();
            Log.e("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";

    }
    protected static String getCameraFlashMode(@NonNull final Context context) {

        final SharedPreferences preferences = getCameraSettingPreferences(context);



        if (preferences != null) {

            return preferences.getString(FLASH_MODE, Camera.Parameters.FLASH_MODE_AUTO);

        }



        return Camera.Parameters.FLASH_MODE_AUTO;

    }
    private static SharedPreferences getCameraSettingPreferences(@NonNull final Context context) {

        return context.getSharedPreferences("com.desmond.squarecamera", Context.MODE_PRIVATE);

    }
    private String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();

        String base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        return base64String;
    }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
