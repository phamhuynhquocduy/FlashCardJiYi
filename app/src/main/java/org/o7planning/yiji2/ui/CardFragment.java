package org.o7planning.yiji2.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.o7planning.yiji2.R;
import org.o7planning.yiji2.adapter.AdapterLesson;
import org.o7planning.yiji2.model.Card;
import org.o7planning.yiji2.model.Lesson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;

import static android.content.Context.MODE_PRIVATE;

public class CardFragment extends Fragment {
    ImageView img,imggif,imginterpret;
    ImageButton imgbtn;
    SQLiteDatabase database;
    Button button;
    ArrayList<Card> arrayList = new ArrayList<>();
    int TAG =1, tmp =0, id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imggif = view.findViewById(R.id.imggif);
        imginterpret = view.findViewById(R.id.imginterpret);
        img = view.findViewById(R.id.img);
        imgbtn =view.findViewById(R.id.music);
        button = view.findViewById(R.id.button);

        // Load data vao array list
        card();
        // Hien thi data dau tien
        loadCard(arrayList.get(0));
        Log.d("Datadautien",arrayList.get(tmp).toString());

        //Su kien khi nhan button lat the va the tiep theo
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khi nhan lat the
                if(TAG==1){
                    TAG=0;
                    imggif.setVisibility(View.VISIBLE);
                    imgbtn.setVisibility(View.VISIBLE);
                    imginterpret.setVisibility(View.VISIBLE);
                    button.setText("Thẻ tiếp theo");
                }
                //Khi nhan the tiep theo
                else {
                    TAG=1;
                    imggif.setVisibility(View.VISIBLE);
                    imgbtn.setVisibility(View.VISIBLE);
                    imginterpret.setVisibility(View.GONE);
                    button.setText("Mặt sau");
                    tmp++;
                    if(tmp<arrayList.size()){
                        loadCard(arrayList.get(tmp));
                    }
                    //Khi het card thi chuyen qua man hinh on tap
                    else{
                        ReadyFragment fragment= new ReadyFragment();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
                        transaction.replace(R.id.content_main, fragment);
                        transaction.commit();
                    }
                }
            }
        });
    }
    //Bo data tu SQlite vao ArrayList
    void card()  {
            database = getContext().openOrCreateDatabase("yiji2.db",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM card WHERE idlesson =? ",new String[]{String.valueOf(DetailLessonFragment.lesson.getId())});
            while (cursor.moveToNext()){
                Card card = new Card(cursor.getInt(0),cursor.getBlob(1),cursor.getBlob(2),cursor.getString(3),cursor.getBlob(4),cursor.getInt(5),cursor.getInt(6));
                arrayList.add(card);
            }
    }

    void loadCard(Card card){
        Log.d("Loadcard", card.getMusic() + String.valueOf(card.getId())+ card.getIdbook());
        //Gif
        Glide.with(this).load(card.getGif()).into(imggif);
        //Hinh anh
        Bitmap image = BitmapFactory.decodeByteArray(card.getImage(),0,card.getImage().length);
        img.setImageBitmap(image);
        //Phat am thanh lan dau
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),getActivity().getResources().getIdentifier(card.getMusic(), "raw", getActivity().getPackageName()));
        mediaPlayer.start();
        //Hinh anh giai nghia
        Bitmap interpret = BitmapFactory.decodeByteArray(card.getInterpret(),0,card.getInterpret().length);
        imginterpret.setImageBitmap(interpret);
        //Music
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),getActivity().getResources().getIdentifier(card.getMusic(), "raw", getActivity().getPackageName()));
                mediaPlayer.start();
            }
        });
    }


}