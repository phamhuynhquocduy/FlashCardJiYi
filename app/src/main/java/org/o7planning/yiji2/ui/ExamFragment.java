package org.o7planning.yiji2.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;

import org.o7planning.yiji2.R;
import org.o7planning.yiji2.model.Exam;
import org.o7planning.yiji2.model.Lesson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ExamFragment extends Fragment {
    Button btnA, btnB,btnC, btnNext;
    ImageButton imgBtnQuestion;
    TextView tv_request,tv_question;
    ImageView imgQuestion;
    SQLiteDatabase database;
    ArrayList<Exam> arrayList= new ArrayList<Exam>();
    SoundPool soundPool;
    int count=1,id;
    int[] sm; ;
    public static int total,correct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exam, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnA = view.findViewById(R.id.button_answer_a);
        btnB = view.findViewById(R.id.button_answer_b);
        btnC = view.findViewById(R.id.button_answer_c);
        btnNext = view.findViewById(R.id.button_next);
        imgBtnQuestion = view.findViewById(R.id.img_btn_question);
        imgQuestion = view.findViewById(R.id.img_question);
        tv_question = view.findViewById(R.id.tv_question);
        tv_request = view.findViewById(R.id.tv_request);
        //Hiệu ứng âm thanh
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attrs = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .setAudioAttributes(attrs)
                    .build();
        } else {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        }
        // người dùng chỉnh độ to nhỏ hiệu ứng âm thanh
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        sm = new int[10];
        // Anh xa am thanh
        sm[0] = soundPool.load(getActivity(), R.raw.correct, 1);
        sm[1] = soundPool.load(getActivity(), R.raw.wrong, 1);

        //Animation
        Animation animationLefttoright = AnimationUtils.loadAnimation(getActivity(),R.anim.lefttoright);
        btnA.startAnimation(animationLefttoright);
        btnB.startAnimation(animationLefttoright);
        btnC.startAnimation(animationLefttoright);
        
        exam();
        //Load data dau tien
        loadCard(arrayList.get(0));

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<arrayList.size()){
                    loadCard(arrayList.get(count++));
                    btnA.setBackgroundResource(R.drawable.custom_button_answer);
                    btnB.setBackgroundResource(R.drawable.custom_button_answer);
                    btnC.setBackgroundResource(R.drawable.custom_button_answer);
                    btnA.startAnimation(animationLefttoright);
                    btnB.startAnimation(animationLefttoright);
                    btnC.startAnimation(animationLefttoright);
                }else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
                    transaction.replace(R.id.content_main, new ResultFragment());
                    transaction.commit();
                }
            }
        });
    }
    private void loadCard(Exam exam) {
        if(exam.getType()==1){
            //An di hinh va nut am thanh
            imgQuestion.setVisibility(View.GONE);
            imgBtnQuestion.setVisibility(View.GONE);
            tv_question.setVisibility(View.VISIBLE);
            //Load data
            tv_request.setText(exam.getRequest());
            tv_question.setText(exam.getQuestion());
            btnA.setText(exam.getAnswerA());
            btnB.setText(exam.getAnswerB());
            btnC.setText(exam.getAnswerC());
            //Nhan nut tra loi cau hoi
            btnA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==1){
                        btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        total++;
                        correct++;
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnA.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==2){
                            btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
            btnB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==2){
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        correct++;
                        btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnB.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==3){
                            btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
            btnC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==3){
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        correct++;
                        btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnC.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==2){
                            btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
        }else if(exam.getType()==2){
            //An di hinh va nut am thanh
            imgQuestion.setVisibility(View.VISIBLE);
            imgBtnQuestion.setVisibility(View.GONE);
            tv_question.setVisibility(View.GONE);
            //Load data
            tv_request.setText(exam.getRequest());
            tv_question.setText(exam.getQuestion());
            btnA.setText(exam.getAnswerA());
            btnB.setText(exam.getAnswerB());
            btnC.setText(exam.getAnswerC());
            //Nhan nut tra loi cau hoi
            btnA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==1){
                        btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        total++;
                        correct++;
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnA.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==2){
                            btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
            btnB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==2){
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        correct++;
                        btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnB.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==3){
                            btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
            btnC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==3){
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        correct++;
                        btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnC.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==2){
                            btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
            //Hinh anh
            Bitmap image = BitmapFactory.decodeByteArray(exam.getImage(),0,exam.getImage().length);
            imgQuestion.setImageBitmap(image);
        }else{
            //An di hinh va nut am thanh
            imgQuestion.setVisibility(View.GONE);
            imgBtnQuestion.setVisibility(View.VISIBLE);
            tv_question.setVisibility(View.GONE);
            //Load data
            tv_request.setText(exam.getRequest());
            tv_question.setText(exam.getQuestion());
            btnA.setText(exam.getAnswerA());
            btnB.setText(exam.getAnswerB());
            btnC.setText(exam.getAnswerC());
            //Gif
            imgBtnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),getActivity().getResources().getIdentifier(exam.getMusic(), "raw", getActivity().getPackageName()));
                mediaPlayer.start();
            }
            });
            //Nhan nut tra loi cau hoi
            btnA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==1){
                        btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        total++;
                        correct++;
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnA.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==2){
                            btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
            btnB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==2){
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        correct++;
                        btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnB.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==3){
                            btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
            btnC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(exam.getCorrect()==3){
                        soundPool.play(sm[0], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        correct++;
                        btnC.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }else{
                        soundPool.play(sm[1], 1, 1, 1, 0, (float) 0.7);
                        total++;
                        btnC.setBackgroundResource(R.drawable.custom_button_answer_wrong);
                        if(exam.getCorrect()==2){
                            btnB.setBackgroundResource(R.drawable.custom_button_answer_correct);
                        }else btnA.setBackgroundResource(R.drawable.custom_button_answer_correct);
                    }
                }
            });
        }
    }

    void exam(){
        Bundle bundle = this.getArguments();
        if(bundle != null){
            id = getArguments().getInt("idlesson");
        }
        database = getContext().openOrCreateDatabase("yiji2.db",MODE_PRIVATE,null);
        Log.d("idddddddddd",String.valueOf(DetailLessonFragment.lesson.getId()));
        Cursor cursor = database.rawQuery("SELECT * FROM exam WHERE idlesson=? ",new String[]{String.valueOf(DetailLessonFragment.lesson.getId())});
        while (cursor.moveToNext()){
            Exam exam = new Exam(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getInt(7),cursor.getInt(8),cursor.getString(9),cursor.getInt(10));
            arrayList.add(exam);
        }
    }
}