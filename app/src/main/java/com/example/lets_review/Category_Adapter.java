package com.example.lets_review;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Category_Adapter  extends RecyclerView.Adapter<Category_Adapter.myviewholder>
{

    String jsonstr="{\n" +
            "\"Manual input\":{\n" +
            "\"data\":[\n" +
            "\"Atmosphere\",\n" +
            "\"Company\",\n" +
            "\"Conclusion\",\n" +
            "\"Experience\",\n" +
            "\"Exhibition\",\n" +
            "\"Facilities\",\n" +
            "\"Film\",\n" +
            "\"Flight\",\n" +
            "\"Food\",\n" +
            "\"Layout\",\n" +
            "\"Price\",\n" +
            "\"Result\",\n" +
            "\"Rides\",\n" +
            "\"Service\",\n" +
            "\"Show\",\n" +
            "\"Taste\",\n" +
            "\"Team\",\n" +
            "\"Ux\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"atmosphere\",\n" +
            "\"company\",\n" +
            "\"conclusion\",\n" +
            "\"experience\",\n" +
            "\"exhibition\",\n" +
            "\"facilities\",\n" +
            "\"film\",\n" +
            "\"flight\",\n" +
            "\"food\",\n" +
            "\"layout\",\n" +
            "\"price\",\n" +
            "\"result\",\n" +
            "\"rides\",\n" +
            "\"service\",\n" +
            "\"show\",\n" +
            "\"taste\",\n" +
            "\"team\",\n" +
            "\"ux\"\n" +
            "]\n" +
            "},\n" +
            "\"AirBNB\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Location\",\n" +
            "\"Facilities\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"guide\",\n" +
            "\"facilities\"\n" +
            "]\n" +
            "},\n" +
            "\"Bed and Breakfast\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Service\",\n" +
            "\"Facilities\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"service\",\n" +
            "\"facilities\"\n" +
            "]\n" +
            "},\n" +
            "\"Café\":{\n" +
            "\"data\":[\n" +
            "\"Taste\",\n" +
            "\"Price\",\n" +
            "\"Service\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"taste\",\n" +
            "\"price\",\n" +
            "\"service\"\n" +
            "]\n" +
            "},\n" +
            "\"Campsite\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Facilities\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"facilities\"\n" +
            "]\n" +
            "},\n" +
            "\"Cinema\":{\n" +
            "\"data\":[\n" +
            "\"Film\",\n" +
            "\"Service\",\n" +
            "\"Price\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"film\",\n" +
            "\"service\",\n" +
            "\"price\"\n" +
            "]\n" +
            "},\n" +
            "\"Club\":{\n" +
            "\"data\":[\n" +
            "\"Atmosphere\",\n" +
            "\"Price\",\n" +
            "\"Music\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"atmosphere\",\n" +
            "\"price\",\n" +
            "\"music\"\n" +
            "]\n" +
            "},\n" +
            "\"Concert/Gig\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Music\",\n" +
            "\"Experience\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"music\",\n" +
            "\"experience\"\n" +
            "]\n" +
            "},\n" +
            "\"Festival\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Atmosphere\",\n" +
            "\"Experience\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"atmosphere\",\n" +
            "\"experience\"\n" +
            "]\n" +
            "},\n" +
            "\"Flight\":{\n" +
            "\"data\":[\n" +
            "\"Service\",\n" +
            "\"Price\",\n" +
            "\"Flight\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"service\",\n" +
            "\"price\",\n" +
            "\"flight\"\n" +
            "]\n" +
            "},\n" +
            "\"Food festival\":{\n" +
            "\"data\":[\n" +
            "\"Food\",\n" +
            "\"Price\",\n" +
            "\"Atmosphere\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"food\",\n" +
            "\"price\",\n" +
            "\"atmosphere\"\n" +
            "]\n" +
            "},\n" +
            "\"Gallery/Exhibition\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Exhibition\",\n" +
            "\"Gallery\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"exhibition\",\n" +
            "\"gallery\"\n" +
            "]\n" +
            "},\n" +
            "\"Gym\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Service\",\n" +
            "\"Facilities\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"service\",\n" +
            "\"facilities\"\n" +
            "]\n" +
            "},\n" +
            "\"Hotel\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Service\",\n" +
            "\"Facilities\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"service\",\n" +
            "\"facilities\"\n" +
            "]\n" +
            "},\n" +
            "\"Museum\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Exhibits\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"exhibition\"\n" +
            "]\n" +
            "},\n" +
            "\"Party\":{\n" +
            "\"data\":[\n" +
            "\"Atmosphere\",\n" +
            "\"Music\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"atmosphere\",\n" +
            "\"music\"\n" +
            "]\n" +
            "},\n" +
            "\"Pub/Bar\":{\n" +
            "\"data\":[\n" +
            "\"Taste\",\n" +
            "\"Price\",\n" +
            "\"Service\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"taste\",\n" +
            "\"price\",\n" +
            "\"service\"\n" +
            "]\n" +
            "},\n" +
            "\"Restaurant\":{\n" +
            "\"data\":[\n" +
            "\"Taste\",\n" +
            "\"Price\",\n" +
            "\"Service\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"taste\",\n" +
            "\"price\",\n" +
            "\"service\"\n" +
            "]\n" +
            "},\n" +
            "\"Shop\":{\n" +
            "\"data\":[\n" +
            "\"Service\",\n" +
            "\"Layout\",\n" +
            "\"Experience\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"service\",\n" +
            "\"layout\",\n" +
            "\"experience\"\n" +
            "]\n" +
            "},\n" +
            "\"Sport event\":{\n" +
            "\"data\":[\n" +
            "\"Team\",\n" +
            "\"Price\",\n" +
            "\"Result\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"team\",\n" +
            "\"price\",\n" +
            "\"result\"\n" +
            "]\n" +
            "},\n" +
            "\"Talk\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Conclusion\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"conclusion\"\n" +
            "]\n" +
            "},\n" +
            "\"Theatre\":{\n" +
            "\"data\":[\n" +
            "\"Show\",\n" +
            "\"Price\",\n" +
            "\"Atmosphere\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"show\",\n" +
            "\"price\",\n" +
            "\"atmosphere\"\n" +
            "]\n" +
            "},\n" +
            "\"Themepark\":{\n" +
            "\"data\":[\n" +
            "\"Price\",\n" +
            "\"Service\",\n" +
            "\"Rides\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"price\",\n" +
            "\"service\",\n" +
            "\"rides\"\n" +
            "]\n" +
            "},\n" +
            "\"Tour\":{\n" +
            "\"data\":[\n" +
            "\"Service\",\n" +
            "\"Price\",\n" +
            "\"Location\"\n" +
            "],\n" +
            "\"image\":[\n" +
            "\"service\",\n" +
            "\"price\",\n" +
            "\"guide\"\n" +
            "]\n" +
            "}\n" +
            "}";

    ArrayList<String> categorylist=new ArrayList<>();
    Context context;
    Category_Adapter(ArrayList<String> categorylist, Context context)
    {

        this.categorylist=categorylist;
        this.context=context;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item,viewGroup,false);


        return new myviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder myviewholder, int i) {

        if(categorylist!=null && categorylist.size()>0) {
            String s1 = categorylist.get(i);
            myviewholder.items.setText(s1);
        }


    }

    @Override
    public int getItemCount() {
        if ( categorylist!=null && categorylist.size() > 0)
            return categorylist.size();
        else
            return  5;


    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView items;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            items=(TextView)itemView.findViewById(R.id.category_items);
            items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        JSONObject on    = (new JSONObject(jsonstr)).getJSONObject("Manual input");
                        JSONArray jsonArray = on.getJSONArray("data");

                        if(items.getText().equals("AirBNB")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("AirBNB");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            String name1=jsonArray1.getString(0);
                            String name2=jsonArray1.getString(1);
                            String name3=jsonArray1.getString(2);
                            Detailed_Review.category_text.setText("AirBNB");
                            Detailed_Review.text1.setText(name1);
                            Detailed_Review.text2.setText(name2);
                            Detailed_Review.text3.setText(name3);
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.location);
                            Detailed_Review.category_images3.setImageResource(R.drawable.facility);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.location);
                            NextScreen.imagerating3.setImageResource(R.drawable.facility);

                            Detailed_Review.dialog.cancel();
//
                        }
                        else if(items.getText().equals("Bed and Breakfast")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Bed and Breakfast");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Bed and Breakfast");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.services);
                            Detailed_Review.category_images3.setImageResource(R.drawable.facility);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.services);
                            NextScreen.imagerating3.setImageResource(R.drawable.facility);
                            Detailed_Review.dialog.cancel();

                        }
                        else if(items.getText().equals("Café")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Café");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Café");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.tastes);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.services);
                            NextScreen.imagerating1.setImageResource(R.drawable.tastes);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.services);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Campsite")){
                            Detailed_Review.linearLayout.setVisibility(View.INVISIBLE);
                            NextScreen.imagerating3.setVisibility(View.INVISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.INVISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Campsite");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Campsite");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.facility);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.facility);
                            Detailed_Review.dialog.cancel();

//

                        }
                        else if(items.getText().equals("Cinema")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Cinema");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Cinema");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.film);
                            Detailed_Review.category_images2.setImageResource(R.drawable.services);
                            Detailed_Review.category_images3.setImageResource(R.drawable.price);
                            NextScreen.imagerating1.setImageResource(R.drawable.film);
                            NextScreen.imagerating2.setImageResource(R.drawable.services);
                            NextScreen.imagerating3.setImageResource(R.drawable.price);

                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Club")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Club");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Club");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.atmosphere);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.music);
                            NextScreen.imagerating1.setImageResource(R.drawable.atmosphere);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.music);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Concert/Gig")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Concert/Gig");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Concert/Gig");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.music);
                            Detailed_Review.category_images3.setImageResource(R.drawable.experiance);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.music);
                            NextScreen.imagerating3.setImageResource(R.drawable.experiance);
                            Detailed_Review.dialog.cancel();
//
                        }
                        else if(items.getText().equals("Festival")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Festival");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Festival");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.atmosphere);
                            Detailed_Review.category_images3.setImageResource(R.drawable.experiance);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.atmosphere);
                            NextScreen.imagerating3.setImageResource(R.drawable.experiance);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Flight")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Flight");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Flight");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.services);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.flight);
                            NextScreen.imagerating1.setImageResource(R.drawable.services);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.flight);
                            Detailed_Review.dialog.cancel();
//

                        } else if(items.getText().equals("Food festival")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Food festival");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Food festival");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.food);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.atmosphere);
                            NextScreen.imagerating1.setImageResource(R.drawable.food);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.atmosphere);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Gallery/Exhibition")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Gallery/Exhibition");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Gallery/Exhibition");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.exhibition);
                            Detailed_Review.category_images3.setImageResource(R.drawable.gallery);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.experiance);
                            NextScreen.imagerating3.setImageResource(R.drawable.gallery);
                            Detailed_Review.dialog.cancel();
//
                        }
                        else if(items.getText().equals("Gym")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Gym");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Gym");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.services);
                            Detailed_Review.category_images3.setImageResource(R.drawable.facility);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.experiance);
                            NextScreen.imagerating3.setImageResource(R.drawable.gallery);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Hotel")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Hotel");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Hotel");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.services);
                            Detailed_Review.category_images3.setImageResource(R.drawable.facility);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.services);
                            NextScreen.imagerating3.setImageResource(R.drawable.facility);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Museum")){
                            Detailed_Review.linearLayout.setVisibility(View.INVISIBLE);
                            NextScreen.imagerating3.setVisibility(View.INVISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.INVISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Museum");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Museum");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.exhibition);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.exhibition);

                            Detailed_Review.dialog.cancel();

                        }
                        else if(items.getText().equals("Party")){
                            Detailed_Review.linearLayout.setVisibility(View.INVISIBLE);
                            NextScreen.imagerating3.setVisibility(View.INVISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.INVISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Party");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Party");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.category_images1.setImageResource(R.drawable.atmosphere);
                            Detailed_Review.category_images2.setImageResource(R.drawable.music);
                            NextScreen.imagerating1.setImageResource(R.drawable.atmosphere);
                            NextScreen.imagerating2.setImageResource(R.drawable.music);
                            Detailed_Review.dialog.cancel();

                        }
                        else if(items.getText().equals("Pub/Bar")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Pub/Bar");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Pub/Bar");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.tastes);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.services);
                            NextScreen.imagerating1.setImageResource(R.drawable.tastes);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.services);
                            Detailed_Review.dialog.cancel();


                        }
                        else if(items.getText().equals("Restaurant")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Restaurant");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Restaurant");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.tastes);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.services);
                            NextScreen.imagerating1.setImageResource(R.drawable.tastes);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.services);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Shop")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Shop");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Shop");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.services);
                            Detailed_Review.category_images2.setImageResource(R.drawable.layout);
                            Detailed_Review.category_images3.setImageResource(R.drawable.experiance);
                            NextScreen.imagerating1.setImageResource(R.drawable.services);
                            NextScreen.imagerating2.setImageResource(R.drawable.layout);
                            NextScreen.imagerating3.setImageResource(R.drawable.exhibition);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Sport event")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Sport event");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Sport event");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.team);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.result);
                            NextScreen.imagerating1.setImageResource(R.drawable.team);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.result);
                            Detailed_Review.dialog.cancel();
//

                        }
                        else if(items.getText().equals("Talk")){
                            Detailed_Review.linearLayout.setVisibility(View.INVISIBLE);
                            NextScreen.imagerating3.setVisibility(View.INVISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.INVISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Talk");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Talk");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.conclusion);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.conclusion);

                            Detailed_Review.dialog.cancel();

                        }
                        else if(items.getText().equals("Theatre")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Theatre");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Theatre");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.show);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.atmosphere);
                            NextScreen.imagerating1.setImageResource(R.drawable.show);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.atmosphere);

                            Detailed_Review.dialog.cancel();


                        }
                        else if(items.getText().equals("Themepark")){
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Themepark");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Themepark");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.price);
                            Detailed_Review.category_images2.setImageResource(R.drawable.services);
                            Detailed_Review.category_images3.setImageResource(R.drawable.rides);
                            NextScreen.imagerating1.setImageResource(R.drawable.price);
                            NextScreen.imagerating2.setImageResource(R.drawable.services);
                            NextScreen.imagerating3.setImageResource(R.drawable.rides);

                            Detailed_Review.dialog.cancel();
                        }
                        else {
                            Detailed_Review.linearLayout.setVisibility(View.VISIBLE);
                            NextScreen.imagerating3.setVisibility(View.VISIBLE);
                            NextScreen.ratingBar3.setVisibility(View.VISIBLE);
                            JSONObject obj=(new JSONObject(jsonstr)).getJSONObject("Tour");
                            JSONArray jsonArray1=obj.getJSONArray("data");
                            Detailed_Review.category_text.setText("Tour");
                            Detailed_Review.text1.setText(jsonArray1.getString(0));
                            Detailed_Review.text2.setText(jsonArray1.getString(1));
                            Detailed_Review.text3.setText(jsonArray1.getString(2));
                            Detailed_Review.category_images1.setImageResource(R.drawable.services);
                            Detailed_Review.category_images2.setImageResource(R.drawable.price);
                            Detailed_Review.category_images3.setImageResource(R.drawable.location);
                            NextScreen.imagerating1.setImageResource(R.drawable.services);
                            NextScreen.imagerating2.setImageResource(R.drawable.price);
                            NextScreen.imagerating3.setImageResource(R.drawable.location);
                            Detailed_Review.dialog.cancel();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}




