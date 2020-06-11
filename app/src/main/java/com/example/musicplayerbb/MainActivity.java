package com.example.musicplayerbb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.target.CustomTarget;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity
   extends AppCompatActivity {

        ArrayList<ModelAudio> audioArrayList;
        RecyclerView recyclerView;
        MediaPlayer mediaPlayer;
        double current_pos, total_duration;
        TextView current, total;
        TextView audio_name;
        TextView getAudio_name;
        ImageView prev, next, pause, albumcover;
        SeekBar seekBar;
        int audio_index = 0;
        public static final int PERMISSION_READ = 0;
        Button button;
        Button details;
       // private JSONArrayRequest request;
        //RequestQueue requestQueue;

//ImageView songAlbumArt;
        @Override
        protected void onCreate (Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if (checkPermission()) {
                //jsonrequest();
                setAudio();
            }
            //details = (Button) findViewById(R.id.details);

          /*  details.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    openSongActivity();
                }});
*/

        }

//reqiest = new JSONArrayRequest ()
        public void setAudio() {
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            current = (TextView) findViewById(R.id.current);
            total = (TextView) findViewById(R.id.total);
            prev = (ImageView) findViewById(R.id.prev);
            next = (ImageView) findViewById(R.id.next);
            pause = (ImageView) findViewById(R.id.pause);
            seekBar = (SeekBar) findViewById(R.id.seekbar);
         //   albumcover = (ImageView) findViewById(R.id.image);

            audioArrayList = new ArrayList<>();
            mediaPlayer = new MediaPlayer();
            //details = findViewById(R.id.details);
            getAudioFiles();
            // setDetails(current);
            //seekbar change listener
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    current_pos = seekBar.getProgress();
                    mediaPlayer.seekTo((int) current_pos);
                }
            });
        //when song ends, play next one
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    audio_index++;
                    if (audio_index < (audioArrayList.size())) {
                        playAudio(audio_index);
                    } else {
                        audio_index = 0;
                        playAudio(audio_index);
                    }

                }
            });
//when refreshing page, play first song
            if (!audioArrayList.isEmpty()) {
              //  playAudio(audio_index);
                prevAudio();
                nextAudio();
                setPause();

            }

        }

        //play music file
        public void playAudio(int pos) {
            try  {
                mediaPlayer.reset();
                //set file path
                mediaPlayer.setDataSource(this, audioArrayList.get(pos).getaudioUri());
                mediaPlayer.prepare();
                mediaPlayer.start();
                pause.setImageResource(R.drawable.ic_pause);
                audio_name.setText(audioArrayList.get(pos).getaudioTitle());
                audio_index=pos;
            } catch (Exception e) {
                e.printStackTrace();
            }
            setAudioProgress();
        }

        //set music file progress
        public void setAudioProgress() {
            //get the audio duration
            current_pos = mediaPlayer.getCurrentPosition();
            total_duration = mediaPlayer.getDuration();

            //display the audio duration
            total.setText(timerConversion((long) total_duration));
            current.setText(timerConversion((long) current_pos));
            seekBar.setMax((int) total_duration);
            final Handler handler = new Handler();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        current_pos = mediaPlayer.getCurrentPosition();
                        current.setText(timerConversion((long) current_pos));
                        seekBar.setProgress((int) current_pos);
                        handler.postDelayed(this, 1000);
                    } catch (IllegalStateException ed){
                        ed.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 1000);
        }


        //play previous audio
        public void prevAudio() {
            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (audio_index > 0) {


                        playAudio(audio_index);
                        audio_index--;

                    } else {
                        //play last song

                        playAudio(audioArrayList.size() - 1);
                        audio_index = audioArrayList.size() - 1;
                    }
                }
            });
        }

        //play next audio
        public void nextAudio() {
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (audio_index < (audioArrayList.size()-1)) {
                       // audio_index++;
                        playAudio(audio_index+1);
                        audio_index++;
                    } else {
                        //play first song
                        audio_index = 0;
                        playAudio(audio_index);
                    }
                }
            });
        }

        //start/pause buttons
        public void setPause() {
            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        //pausing
                        mediaPlayer.pause();
                        pause.setImageResource(R.drawable.ic_play);
                    } else {
                        mediaPlayer.start();
                        pause.setImageResource(R.drawable.ic_pause);
                    }
                }
            });
        }



    public  void playMainBtnClick(View view)
    {
        openSongActivity();
    }


    //button to open the main activity uses this method
    public  void openSongActivity() {
       // mediaPlayer.reset();
        Intent intent = new Intent(this, SongActivity.class);

        startActivity(intent);
    }

        //time conversion
        public String timerConversion(long time) {
            String audioTime;
            int dur = (int) time;
            int hrs = (dur / 3600000);
            int mns = (dur / 60000) % 60000;
            int scs = dur % 60000 / 1000;

            if (hrs > 0) {
                audioTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
            } else {
                audioTime = String.format("%02d:%02d", mns, scs);
            }
            return audioTime;
        }


        //fetch the audio files from storage
        public void getAudioFiles() {
//  provides applications access to the content mode
            ContentResolver contentResolver = getContentResolver();

//Getting audio file path or URI from mediastore/URI for the "primary" external storage volume
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
           Uri uri2 = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            Cursor cursor2 = contentResolver.query(uri2, null, null, null, null);

            //looping through all rows and adding to list
            if (cursor != null  && cursor2 !=null && cursor.moveToFirst()) {
                int songAlbumArt = cursor2.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);

                do {
                  //  ImageView songAlbumArt  = cursor.get(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART));
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    //String AlbumIds = cursor2.getString(songAlbumArt);
                    //Drawable img = Drawable.createFromPath(AlbumIds);


                  // String songId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));

                    ModelAudio modelAudio = new ModelAudio();
                    modelAudio.setaudioTitle(title);
                    modelAudio.setaudioArtist(artist);
                    modelAudio.setaudioUri(Uri.parse(url));
                    modelAudio.setaudioDuration(duration);
                   // modelAudio.setSongAlbumArt(AlbumIds);
                    audioArrayList.add(modelAudio);

                } while (cursor.moveToNext());
            }
            AudioAdapter adapter;
            //sorting songs by name
Collections.sort(audioArrayList,new Comparator<ModelAudio>(){

    @Override
    public int compare(ModelAudio o1, ModelAudio o2) {

        return o1.audioTitle.compareTo(o2.audioTitle);


    }

});

            adapter = new AudioAdapter(this, audioArrayList);
            recyclerView.setAdapter(adapter);

         //   assert recyclerView != null;
            Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();



            adapter.setOnItemClickListener(new AudioAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int pos, View v) {
                    audio_index=pos;
                    playAudio(audio_index);

                }
            });
        }

        //checks storage permission
        public boolean checkPermission() {
            int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ);
                return false;
            }
            return true;
        }

        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case  PERMISSION_READ: {
                    if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                            Toast.makeText(getApplicationContext(), "Please allow storage permission", Toast.LENGTH_LONG).show();
                        } else {
                            setAudio();
                        }
                    }
                }
            }
        }

    /*
    public void onItemClick(View view, int position) {
        // Toast.makeText(this, "You clicked " + adp.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
       public void  startActivity(new Intent(getApplicationContext(), SongActivity.class).);



     /*   }    /*
    private  final Context context;
    public MyViewHolder(View itemView){

        super(itemView);
        contex = itemView.getContext();
    }

    public void onClick(View view)
    {
        final Intent intent;
        intent = new Intent(context, SongActivity.class);
        context.startActivity(intent);

    }*/
        //stop song after leaving page
        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (mediaPlayer!=null){
                mediaPlayer.release();
            }
        }

}