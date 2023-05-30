package com.example.lets_review;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Star_review extends Fragment {
    CardView cardview;
    TextView searchLocation;
    RatingBar star_rating;
    Context context;
    ProgressDialog progressDialog;
    public ArrayList<String> placesNearby;
    private CallbackManager callbackManager;
   CircleImageView instashare,fbshare,whatsapp_share;
    ShareDialog shareDialog;
    String mParam1;
    public static Star_review newInstance(ArrayList<String> placesNearby) {

        Bundle args = new Bundle();
        args.putStringArrayList("PLACES_NEARBY",placesNearby);
        Star_review fragment = new Star_review();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.star_review , viewGroup , false);
        initViews(v);
        return v;

    }

    private void initViews(View v) {
        star_rating=(RatingBar)v.findViewById(R.id.star_rating);

        cardview=(CardView)v.findViewById(R.id.cardview);
        context=getContext();
        instashare=(CircleImageView) v.findViewById(R.id.sharing_insta);
       mParam1=NextScreen.imagePath;
        byte[] decodedString = Base64.decode(mParam1, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

      final Uri uri_image =getImageUri(this,decodedByte);
        Log.e("123456","sdfreg"+NextScreen.imagePath);
       fbshare=(CircleImageView) v.findViewById(R.id.sharing_fb);
       whatsapp_share=(CircleImageView)v.findViewById(R.id.sharing_whatsapp);

        star_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // star_rating.getRating();

                Log.e("97",rating+"");

                NextScreen.nextScreen.setRating(rating);
            }
        });
       whatsapp_share.setOnClickListener(v12 -> {
           Intent share=new Intent(Intent.ACTION_SEND);
           share.setType("image/*");
           share.putExtra(Intent.EXTRA_STREAM,uri_image);
           share.putExtra(Intent.EXTRA_TEXT,"Let's Review");
           context.startActivity(Intent.createChooser(share,"(Share your Experience)"));
       });
       instashare.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =getActivity().getPackageManager().getLaunchIntentForPackage("com.instagram.android");
               if (intent != null) {
                   String str_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Lets-Review/";
                   Intent shareIntent = new Intent();
                   shareIntent.setAction(Intent.ACTION_SEND);
                   shareIntent.setPackage("com.instagram.android");
                   NextScreen.captured_image.setDrawingCacheEnabled(true);
                   // creates immutable clone of image
                   //  Bitmap  bt_image = Bitmap.createBitmap(iv_capture.getDrawingCache());
                   Bitmap bt_image = Bitmap.createBitmap(NextScreen.captured_image.getDrawingCache());
                   NextScreen.captured_image.setDrawingCacheEnabled(false);
                   Uri uri = getImageUri(Star_review.this, bt_image);
                   shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

                   shareIntent.setType("image/*");
                   shareIntent.putExtra(Intent.EXTRA_SUBJECT, "#LR");
                   shareIntent.putExtra(
                           Intent.EXTRA_TEXT,
                           "#Let'sReview");
                   startActivity(shareIntent);
               } else {
                   // bring user to the market to download the app.
                   // or let them choose an app?
                   intent = new Intent(Intent.ACTION_VIEW);
                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   intent.setData(Uri.parse("market://details?id=" + "com.instagram.android"));
                   startActivity(intent);
               }

           }


       });

       fbshare.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               postPicture();

           }
       });
     /*   Drawable drawable = star_rating.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#D81B60"), PorterDuff.Mode.SRC_ATOP);*/

/*
        star_rating.setOnRatingBarChangeListener(new android.support.v7.widget.AppCompatRatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(android.support.v7.widget.AppCompatRatingBar ratingBar, float rating, boolean fromUser) {



            }
        });
*/

        searchLocation = v.findViewById(R.id.locationsearch);
        placesNearby = new ArrayList<>();
        placesNearby = getArguments().getStringArrayList("PLACES_NEARBY");

        searchLocation.setOnClickListener(v1 -> {
           if (placesNearby!=null && placesNearby.size()>0) {

                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.categoryrecyclelist);
                dialog.getWindow().setLayout(700, 800);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();
                RecyclerView recyclev = dialog.findViewById(R.id.cateogory_list);
                LinearLayoutManager layout = new LinearLayoutManager(getContext());
                recyclev.setLayoutManager(layout);
                Category_Adapter adapter = new Category_Adapter(placesNearby, getContext());
                recyclev.setAdapter(adapter);
            }
            else
            {
                Toast.makeText(getContext(),"Please Wait.....",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private Uri getImageUri(Star_review star_review, Bitmap bt_image) {
        ByteArrayOutputStream bytes=new ByteArrayOutputStream();
        bt_image.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path= MediaStore.Images.Media.insertImage(getContext().getContentResolver(),bt_image,"#LR",null);

        return Uri.parse(path);
    }


    private void postPicture() {
        //check counter
        NextScreen.captured_image.setDrawingCacheEnabled(true);
        // creates immutable clone of image
        //  Bitmap  bt_image = Bitmap.createBitmap(iv_capture.getDrawingCache());
        Bitmap bt_image = Bitmap.createBitmap(NextScreen.captured_image.getDrawingCache());
        // Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);
        NextScreen.captured_image.setDrawingCacheEnabled(false);
        Log.e("4455","outside the if block but inside post picture");
        // destroy
        //  iv_capture.destroyDrawingCache();
        CallbackManager callbackManager = CallbackManager.Factory.create();

        shareDialog=new ShareDialog(this);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.e("TAGgggg","onSuccesssssss");

            }
            @Override
            public void onCancel() {
                Log.e("TAGgggg","onCAncellllll");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("TAGgggg","onErrorrrr"+error.getMessage());
            }
        });

        if (shareDialog.canShow(SharePhotoContent.class)) {
            Log.e("567","inside share Dialog ka if block");
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(bt_image)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            shareDialog.show(content);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}



