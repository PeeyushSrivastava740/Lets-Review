package com.example.lets_review;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class filters extends AppCompatActivity implements ThumbnailCallback {
    static {
        System.loadLibrary("NativeImageProcessor");
    }

    private Activity activity;

    private RecyclerView thumbListView;
    Bitmap bitmap;
    private ImageView placeHolderImageView;
    private static final String IMAGE_DIRECTORY = "/CustomImage";
    String imagePath;
    String imageStr;
    Bitmap imagebitmap;
    Bitmap imvBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        activity = this;
        initUIWidgets();
    }

    private void initUIWidgets() {
         imageStr = getIntent().getStringExtra("imageStr");

        if (imageStr!=null){
            byte[] bytes = Base64.decode(imageStr, Base64.DEFAULT);
            imvBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        }else {
            Bundle intent_camera = getIntent().getExtras();
            Uri image=  Uri.fromFile(new File(intent_camera.getString("imagePath")));

            try{
                imagebitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),image);

            }
            catch (FileNotFoundException e){

                e.printStackTrace();
                Log.e("54",e.getMessage());
            }
            catch (Exception e){
                e.printStackTrace();
                Log.e("58",e.getMessage());
            }
            Log.e("145263", "path" + image+"");
        }



        TextView next=findViewById(R.id.nexttext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagebitmap!=null){
                    //saveImage(imagebitmap);

                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    File file = new File(directory, "UniqueFileName" + ".jpg");
                    if (!file.exists()) {
                        Log.d("path", file.toString());
                        FileOutputStream fos = null;

                        try {
                            fos = new FileOutputStream(file);
                            imagebitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            imagePath = file.getAbsolutePath();
                            fos.flush();
                            fos.close();
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                    imagePath =  getBase64String(imagebitmap);
                    Intent intent = new Intent(filters.this,NextScreen.class);
                    intent.putExtra("imagePath",imagePath);
                    startActivity(intent);
                }else {
//                    saveImage(imvBitmap);

                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    File file = new File(directory, "UniqueFileName" + ".jpg");
                    if (!file.exists()) {
                        Log.d("path", file.toString());
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(file);
                            imvBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            imagePath = file.getAbsolutePath();
                            fos.flush();
                            fos.close();
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                    imagePath =  getBase64String(imvBitmap);
                    Intent intent = new Intent(filters.this,NextScreen.class);
                    intent.putExtra("imagePath",imagePath);
                    startActivity(intent);
                }


            }
        });

        thumbListView = (RecyclerView) findViewById(R.id.thumbnails);

        placeHolderImageView = (ImageView) findViewById(R.id.place_holder_imageview);
        if (imagebitmap!=null){
            placeHolderImageView.setImageBitmap(imagebitmap);
        }else {
            placeHolderImageView.setImageBitmap(imvBitmap);
        }

       // placeHolderImageView.setImageBitmap(Bitmap.createScaledBitmap(imagebitmap, 640, 640, false));

        // placeHolderImageView.setImageURI(uri);
        // placeHolderImageView.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.photo), 640, 640, false));

        final Context context = this.getApplication();

        Handler handler = new Handler();
        final ArrayList<String> texts=new ArrayList<>();
        texts.add("Fresh");
        texts.add("Memory");
        texts.add("Candy");
        texts.add("Mono");
        texts.add("Sweet");
        texts.add("Dawn");
        texts.add("Shine");

        Runnable r = new Runnable() {

            public void run() {
                if (imagebitmap!=null){
                    Bitmap thumbImage = Bitmap.createScaledBitmap(imagebitmap, 640, 640, false);

                    ThumbnailItem t1 = new ThumbnailItem();

                    ThumbnailItem t2 = new ThumbnailItem();

                    ThumbnailItem t3 = new ThumbnailItem();

                    ThumbnailItem t4 = new ThumbnailItem();

                    ThumbnailItem t5 = new ThumbnailItem();

                    ThumbnailItem t6 = new ThumbnailItem();
                    ThumbnailItem t7 = new ThumbnailItem();


                    t1.image = thumbImage;


                    t2.image = thumbImage;

                    t3.image = thumbImage;

                    t4.image = thumbImage;

                    t5.image = thumbImage;

                    t6.image = thumbImage;
                    t7.image=thumbImage;


                    ThumbnailsManager.clearThumbs();

                    ThumbnailsManager.addThumb(t1); // Original Image


                    t2.filter = SampleFilters.getStarLitFilter();

                    ThumbnailsManager.addThumb(t2);


                    t3.filter = SampleFilters.getBlueMessFilter();

                    ThumbnailsManager.addThumb(t3);


                    t4.filter = SampleFilters.getAweStruckVibeFilter();

                    ThumbnailsManager.addThumb(t4);


                    t5.filter = SampleFilters.getLimeStutterFilter();

                    ThumbnailsManager.addThumb(t5);


                    t6.filter = SampleFilters.getNightWhisperFilter();

                    ThumbnailsManager.addThumb(t6);

                    t7.filter=SampleFilters.getStarLitFilter();
                    ThumbnailsManager.addThumb(t7);


                    List<ThumbnailItem> thumbs = ThumbnailsManager.processThumbs(context);
                    Log.e("qhswdwji","list"+thumbs);


                    ThumbnailsAdapter adapter = new ThumbnailsAdapter(thumbs, (ThumbnailCallback) activity,texts);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                    layoutManager.scrollToPosition(0);

                    thumbListView.setLayoutManager(layoutManager);

                    thumbListView.setHasFixedSize(true);
                    thumbListView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                }else {
                    Bitmap thumbImage = Bitmap.createScaledBitmap(imvBitmap, 640, 640, false);

                    ThumbnailItem t1 = new ThumbnailItem();

                    ThumbnailItem t2 = new ThumbnailItem();

                    ThumbnailItem t3 = new ThumbnailItem();

                    ThumbnailItem t4 = new ThumbnailItem();

                    ThumbnailItem t5 = new ThumbnailItem();

                    ThumbnailItem t6 = new ThumbnailItem();
                    ThumbnailItem t7 = new ThumbnailItem();


                    t1.image = thumbImage;


                    t2.image = thumbImage;

                    t3.image = thumbImage;

                    t4.image = thumbImage;

                    t5.image = thumbImage;

                    t6.image = thumbImage;
                    t7.image=thumbImage;


                    ThumbnailsManager.clearThumbs();

                    ThumbnailsManager.addThumb(t1); // Original Image


                    t2.filter = SampleFilters.getStarLitFilter();

                    ThumbnailsManager.addThumb(t2);


                    t3.filter = SampleFilters.getBlueMessFilter();

                    ThumbnailsManager.addThumb(t3);


                    t4.filter = SampleFilters.getAweStruckVibeFilter();

                    ThumbnailsManager.addThumb(t4);


                    t5.filter = SampleFilters.getLimeStutterFilter();

                    ThumbnailsManager.addThumb(t5);


                    t6.filter = SampleFilters.getNightWhisperFilter();

                    ThumbnailsManager.addThumb(t6);

                    t7.filter=SampleFilters.getStarLitFilter();
                    ThumbnailsManager.addThumb(t7);


                    List<ThumbnailItem> thumbs = ThumbnailsManager.processThumbs(context);
                    Log.e("qhswdwji","list"+thumbs);


                    ThumbnailsAdapter adapter = new ThumbnailsAdapter(thumbs, (ThumbnailCallback) activity,texts);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                    layoutManager.scrollToPosition(0);

                    thumbListView.setLayoutManager(layoutManager);

                    thumbListView.setHasFixedSize(true);
                    thumbListView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                }


            }

        };

        handler.post(r);

    }

    @Override
    public void onThumbnailClick(Filter filter) {
//        placeHolderImageView.setImageBitmap(filter.processFilter(bitmap));
        // placeHolderImageView.setImageURI(filter.processFilter(imagePath));
        if (imagebitmap!=null){
            placeHolderImageView.setImageBitmap
                    (filter.processFilter
                            (Bitmap.createScaledBitmap
                                    (imagebitmap, 640, 640, false)));
            imagebitmap=filter.processFilter
                    (Bitmap.createScaledBitmap
                            (imagebitmap, 640, 640, false));
        }else {
            placeHolderImageView.setImageBitmap
                    (filter.processFilter
                            (Bitmap.createScaledBitmap
                                    (imvBitmap, 540, 540, false)));
            imvBitmap=filter.processFilter
                    (Bitmap.createScaledBitmap
                            (imvBitmap, 540, 540, false));
        }

      //  saveImage(imagebitmap);

    }


    private String saveImage(Bitmap bitmap) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
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
            Toast.makeText(activity, "File saved successfully!", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";

    }

    private String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();

        String base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        return base64String;
    }
}
