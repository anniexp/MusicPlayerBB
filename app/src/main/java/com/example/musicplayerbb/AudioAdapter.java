package com.example.musicplayerbb;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.viewHolder> {

    Context context;
    ArrayList<ModelAudio> audioArrayList;
    public OnItemClickListener onItemClickListener;
   // RequestOptions option;
//public ImageView songAlbumArt;

    public AudioAdapter(Context context, ArrayList<ModelAudio> audioArrayList) {
        //super(audioArrayList);
        this.context = context;
        this.audioArrayList = audioArrayList;
      //  this.songAlbumArt = audioArrayList.find
        //Request option for Glide
     //   option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_audiotrack).error(R.drawable.ic_audiotrack);

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
        if (i%2==0)
            holder.itemView.setBackgroundColor(Color.parseColor("black"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#3B232325"));

        // holder.songAlbumArt = (ImageView)convertView.findViewById(R.id.imageView);

        //holder.songAlbumArt.set(audioArrayList.get(i).getSongAlbumArt());
       // holder.songAlbumArt.setImageDrawable(audioArrayList.get(i).getsongId());
        //holder.songAlbumArt.setImage
        //get image form song and set it into imageView with Glide

        //Glide.with(context).load(audioArrayList.get(i).getaudioUri()).apply(option).into(holder.songAlbumArt);
    }

//how many list items are in the list
    @Override
    public int getItemCount() {

        return audioArrayList.size();
    }

//holds the widgets in memory ,each individual entry, and it gets recycled
    public class viewHolder extends RecyclerView.ViewHolder {
    TextView title, artist, parentLayout;
        ImageView  songAlbumArt;

        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            artist = (TextView) itemView.findViewById(R.id.artist);
            //songAlbumArt = (ImageView) itemView.findViewById(R.id.image);


            //parentLayout = itemView.findViewById(R.id.parent_layout);

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