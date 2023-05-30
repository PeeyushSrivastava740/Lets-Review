package com.example.lets_review;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextScreen extends AppCompatActivity {
    TabLayout tab;
    public  static   String imagePath;
   public static ImageView captured_image;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public  RatingBar imagerating;
    private static final String IMAGE_DIRECTORY = "/CustomImage";
    PlacesClient placesClient;
    List<Place.Field> placeFields;
    public ArrayList<String> nearbyPlaces;
    LocationManager manager;
    Bitmap imgBitmap;
    LinearLayout nextlayout1,nextlayout2;
    public static NextScreen nextScreen;
    public static RatingBar ratingBar1,ratingBar2,ratingBar3;
    public static  ImageView imagerating1,imagerating2,imagerating3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_screen);
        nextScreen=this;
        captured_image=(ImageView)findViewById(R.id.imageviewcaptured);
nextlayout1=findViewById(R.id.starlayout);
nextlayout2=findViewById(R.id.detaillayout);
        Intent intent_camera = getIntent();
         imagePath = intent_camera.getStringExtra("imagePath");
        byte[] bytes = Base64.decode(imagePath, Base64.DEFAULT);
        imgBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

         Log.e("145263","path"+imagePath);
        imagerating1=findViewById(R.id.ratingimage1);
        imagerating2=findViewById(R.id.ratingimage2);
        imagerating3=findViewById(R.id.ratingimage3);
        ratingBar1=findViewById(R.id.rate1);
        ratingBar2=findViewById(R.id.rate2);
        ratingBar3=findViewById(R.id.rate3);
        if (imagePath!=null){
           // final Uri uri = Uri.parse(imagePath);
            captured_image.setImageBitmap(imgBitmap);
        }

        nearbyPlaces = new ArrayList<>();
        imagerating=(RatingBar)findViewById(R.id.acivity_ratingbar);
        frameLayout=(FrameLayout)findViewById(R.id.framelayout);
        tab=findViewById(R.id.tablayout1);
        tab.addTab(tab.newTab().setText("STAR REVIEW"));
        tab.addTab(tab.newTab().setText("DETAILED REVIEW"));
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
      /*  Drawable drawable = imagerating.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#D81B60"), PorterDuff.Mode.SRC_ATOP);*/
        fragment = Star_review.newInstance(nearbyPlaces);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragment = Star_review.newInstance(nearbyPlaces);
                        nextlayout1.setVisibility(View.VISIBLE);
                        nextlayout2.setVisibility(View.GONE);
                        break;
                    case 1:
                        fragment = Detailed_Review.newInstance(nearbyPlaces);
                        nextlayout1.setVisibility(View.GONE);
                        nextlayout2.setVisibility(View.VISIBLE);
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.framelayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }
    @Override
    protected void onResume() {
        super.onResume();

        manager = (LocationManager)getSystemService (Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ){
            GpsAlertBuilder();
        }
        //what you want to do
        else{
            getNearbyLocations();
        }

    }
    public void GpsAlertBuilder(){
        AlertDialog.Builder builder = new AlertDialog.Builder(NextScreen.this);
        builder.setTitle("Enable Gps ")
                .setMessage("Open Location Settings")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                GpsAlertBuilder();
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public  void setRating(float rating)
    {
        imagerating.setRating(rating);
    }


    public void getNearbyLocations(){

        Places.initialize(getApplicationContext(), "AIzaSyDoTU2N0idPfozaz9ILG2Db3pANgfDyq1k ");
        // Create a new Places client instance.
        placesClient = Places.createClient(this);
        placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
        FindCurrentPlaceRequest request =
                FindCurrentPlaceRequest.builder(placeFields).build();
        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
            placeResponse.addOnCompleteListener(new OnCompleteListener<FindCurrentPlaceResponse>() {
                @Override
                public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
                    if (task.isSuccessful()) {
                        FindCurrentPlaceResponse response = task.getResult();
                        for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                            Log.e("TAG", String.format("Place '%s' has likelihood: %f",
                                    placeLikelihood.getPlace().getName(),
                                    placeLikelihood.getLikelihood()));
                            nearbyPlaces.add(placeLikelihood.getPlace().getName());

                        }
                    } else {
                        Exception exception = task.getException();
                        if (exception instanceof ApiException) {
                            ApiException apiException = (ApiException) exception;
                            Log.e("TAG", "Place not found: " + apiException.getStatusCode());
                        }
                    }

                   // Log.e("SizeOfList", String.valueOf(nearbyPlaces.size()));




                }
            });
        } else {
            // A local method to request required permissions;
            // See https://developer.android.com/training/permissions/requesting
            // getLocationPermission();
        }



    }
    public ArrayList<String> getNearbyPlaces() {
        return nearbyPlaces;
    }

}

