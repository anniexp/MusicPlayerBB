package com.example.musicplayerbb;


import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.viewHolder> {

    Context context;
    ArrayList<ModelAudio> audioArrayList;
    public OnItemClickListener onItemClickListener;


    public AudioAdapter(Context context, ArrayList<ModelAudio> audioArrayList) {
        this.context = context;
        this.audioArrayList = audioArrayList;

    }

    //recycling the view holder and putting them in positions
    @Override
    public AudioAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.audio_list, viewGroup, false);
        //RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
        return new viewHolder(view);
    }



    @Override
    public void onBindViewHolder(final AudioAdapter.viewHolder holder, final int i) {
        holder.title.setText(audioArrayList.get(i).getaudioTitle());
        holder.artist.setText(audioArrayList.get(i).getaudioArtist());
    }
   /* @Override
    public void onBindViewHolder(final AudioAdapter.viewHolder holder, final int i) {
        holder.title.setText(audioArrayList.get(i).getaudioTitle());
        holder.artist.setText(audioArrayList.get(i).getaudioArtist());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SongActivity.class);
                intent.putExtra("title", audioArrayList.get(i).audioTitle);
                intent.putExtra("artist", audioArrayList.get(i).audioArtist);
             //   intent.putExtra("dur", audioArrayList.get(i).audioDuration);
             //   intent.putExtra("audioUri", audioArrayList.get(i).audioUri);
             //   intent.putExtra("art", audioArrayList.get(i).songAlbumArt);
                context.startActivity(intent);



            }
        });*/
       // holder.songAlbumArt.getDrawable(audioArrayList.get(i).getsongAlbumArt());

       /* if (context !=null) {

            Glide.with(context)
                    .asDrawable()
                    .load(audioArrayList.get(i).songAlbumArt)
                    .into(holder.songAlbumArt);
        }
        holder.*/

//how many list items are in the list
    @Override
    public int getItemCount() {
        return audioArrayList.size();
    }
//holds the widgets in memory ,each individual entry, and it gets recycled
    public class viewHolder extends RecyclerView.ViewHolder {
    TextView title, artist, parentLayout;
        ImageView delete, edit, songAlbumArt;
        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            artist = (TextView) itemView.findViewById(R.id.artist);
            songAlbumArt = (ImageView) itemView.findViewById(R.id.songAlbumArt);
parentLayout = itemView.findViewById(R.id.parent_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(), v);
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, View v);
    }
}