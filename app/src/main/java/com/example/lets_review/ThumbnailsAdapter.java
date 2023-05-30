package com.example.lets_review;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailsAdapter extends RecyclerView.Adapter<ThumbnailsAdapter.MyviewHolder> {
    private static final String TAG = "THUMBNAILS_ADAPTER";

    private static int lastPosition = -1;

    private ThumbnailCallback thumbnailCallback;

    private List<ThumbnailItem> dataSet=new ArrayList<>();
    ArrayList<String>texts=new ArrayList<>();



    public ThumbnailsAdapter(List<ThumbnailItem> dataSet, ThumbnailCallback thumbnailCallback, ArrayList<String> texts) {

        Log.v(TAG, "Thumbnails Adapter has " + dataSet.size() + " items");

        this.dataSet = dataSet;

        this.thumbnailCallback = thumbnailCallback;
        this.texts=texts;

    }


    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.

                from(viewGroup.getContext()).

                inflate(R.layout.item_layout, viewGroup, false);

        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, @SuppressLint("RecyclerView") final  int i) {

            final ThumbnailItem thumbnailItem = dataSet.get(i);
            String name=texts.get(i);
            Log.v(TAG, "On Bind View Called");

            myviewHolder.imageView.setImageBitmap(thumbnailItem.image);
            myviewHolder.textView.setText(name);
          myviewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_START);

           // setAnimation(thumbnailsViewHolder.thumbnail, i);
            myviewHolder.imageView.setOnClickListener(v -> {
                if (lastPosition != i) {
                    thumbnailCallback.onThumbnailClick(thumbnailItem.filter);
                    lastPosition = i;
                }
            });


        }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public  class MyviewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagefiltered);
            textView=itemView.findViewById(R.id.textfilter);
        }
    }

}
