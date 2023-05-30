package com.example.lets_review;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Detailed_Review extends Fragment {
    public static Dialog dialog;
    public static SeekBar seekBar1, seekBar2, seekBar3;
    public static TextView category_text, text1, text2, text3;
    public static ImageView category_images1, category_images2, category_images3;
    ArrayList<String> categorylist = new ArrayList<>();
    public ArrayList<String> placesNearby;
    public static LinearLayout linearLayout;
    TextView searchLocation;
    Context context;
    String mParam1;
    CircleImageView instaimg, fbimg, whatsaapimg;

    public static Detailed_Review newInstance(ArrayList<String> placesNearby) {
        Bundle args = new Bundle();
        args.putStringArrayList("PLACES_NEARBY", placesNearby);
        Detailed_Review fragment = new Detailed_Review();
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detailed_review, viewGroup, false);
        initViews(v);
        searchLocation = v.findViewById(R.id.locationsearch1);
        final NextScreen nextScreen = new NextScreen();
        placesNearby = new ArrayList<>();
        placesNearby = getArguments().getStringArrayList("PLACES_NEARBY");

        searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (placesNearby != null && placesNearby.size() > 0) {

                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.categoryrecyclelist);
                    dialog.getWindow().setLayout(700, 800);
                    dialog.show();

                    RecyclerView recyclev = dialog.findViewById(R.id.cateogory_list);

              /*  for(int i =0;i<nextScreen.nearbyPlaces.size();i++){

                    placesNearby.add(nextScreen.nearbyPlaces.get(i));
                }*/
                    LinearLayoutManager layout = new LinearLayoutManager(getContext());
                    recyclev.setLayoutManager(layout);
                    Category_Adapter adapter = new Category_Adapter(placesNearby, getContext());
                    recyclev.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Please Wait.....", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;


    }

    private void initViews(View v) {
        text1 = (TextView) v.findViewById(R.id.text1);
        text2 = (TextView) v.findViewById(R.id.category_text2);
        text3 = (TextView) v.findViewById(R.id.text3);
        seekBar1 = v.findViewById(R.id.seekbar11);
        seekBar2 = v.findViewById(R.id.seekbar12);
        seekBar3 = v.findViewById(R.id.seekbar13);
        linearLayout = v.findViewById(R.id.layout4);
        instaimg = v.findViewById(R.id.sharingdetail_insta);
        fbimg = v.findViewById(R.id.sharingdetail_fb);
        whatsaapimg = v.findViewById(R.id.sharingdetail_whatsapp);
        mParam1 = NextScreen.imagePath;
        byte[] decodedString = Base64.decode(mParam1, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        final Uri uri_image =getImageUri(this,decodedByte);
        category_images1 = (ImageView) v.findViewById(R.id.categoryimage1);
        category_images2 = (ImageView) v.findViewById(R.id.categoryimage2);
        category_images3 = (ImageView) v.findViewById(R.id.categoryimage3);
        category_text = (TextView) v.findViewById(R.id.category_textview);
        category_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.categoryrecyclelist);
                dialog.getWindow().setLayout(700, 800);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();

                RecyclerView recyclev = dialog.findViewById(R.id.cateogory_list);
                categorylist.add("AirBNB");
                categorylist.add("Bed and Breakfast");
                categorylist.add("Cafe");
                categorylist.add("Campsite");
                categorylist.add("Cinema");
                categorylist.add("Club/Gig");
                categorylist.add("Concert");
                categorylist.add("Festival");
                categorylist.add("Flight");
                categorylist.add("Food festival");
                categorylist.add("Gallery/Exhibition");
                categorylist.add("Gym");
                categorylist.add("Hotel");
                categorylist.add("Museum");
                categorylist.add("Party");
                categorylist.add("Pub/Bar");
                categorylist.add("Restaurant");
                categorylist.add("Shop");
                categorylist.add("Sport event");
                categorylist.add("Talk");
                categorylist.add("Threater");
                categorylist.add("Themepark");
                categorylist.add("Tour");
                LinearLayoutManager layout = new LinearLayoutManager(getContext());
                recyclev.setLayoutManager(layout);
                Category_Adapter adapter = new Category_Adapter(categorylist, getContext());
                recyclev.setAdapter(adapter);
            }
        });
        context = getContext();
        fbimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        whatsaapimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, uri_image);
                share.putExtra(Intent.EXTRA_TEXT, "Let's Review");
                context.startActivity(Intent.createChooser(share, "(Share your Experience)"));
            }
        });

        instaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.instagram.android");
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
                    Uri uri = getImageUri(Detailed_Review.this, bt_image);
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



seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

    {
        @Override
        public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
        NextScreen.ratingBar1.setRating(progress);
    }

        @Override
        public void onStartTrackingTouch (SeekBar seekBar){

    }

        @Override
        public void onStopTrackingTouch (SeekBar seekBar){

    }
    });
                seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

    {
        @Override
        public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
        NextScreen.ratingBar2.setRating(progress);
    }

        @Override
        public void onStartTrackingTouch (SeekBar seekBar){

    }

        @Override
        public void onStopTrackingTouch (SeekBar seekBar){

    }
    });
                seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

    {
        @Override
        public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
        NextScreen.ratingBar3.setRating(progress);
    }

        @Override
        public void onStartTrackingTouch (SeekBar seekBar){

    }

        @Override
        public void onStopTrackingTouch (SeekBar seekBar){

    }
    });

}

    private Uri getImageUri(Detailed_Review detailed_review, Bitmap bt_image) {
        ByteArrayOutputStream bytes=new ByteArrayOutputStream();
        bt_image.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path= MediaStore.Images.Media.insertImage(getContext().getContentResolver(),bt_image,"#LR",null);

        return Uri.parse(path);
    }


}
