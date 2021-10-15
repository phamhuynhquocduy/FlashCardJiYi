package org.o7planning.yiji2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.o7planning.yiji2.R;
import org.o7planning.yiji2.model.Lesson;

import java.security.PublicKey;

public class DetailLessonFragment extends Fragment {

    public TextView txtCountCard, txtCountLearn;
    public Button btnReady, btnNotReady;
    public static Lesson lesson;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtCountCard = view.findViewById(R.id.txt_count_card);
        txtCountLearn = view.findViewById(R.id.txt_count_learn);
        btnReady = view.findViewById(R.id.btn_ready);
        btnNotReady = view.findViewById(R.id.btn_not_ready);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            lesson = (Lesson) getArguments().getSerializable("lesson");
            txtCountCard.setText("Số thẻ "+ String.valueOf(lesson.getSothe()));
            txtCountLearn.setText("Số lần truy cập " + String.valueOf(lesson.getCountLearn()));
        }
        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardFragment fragment= new CardFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragment);
                transaction.commit();
            }
        });
        btnNotReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, new LessonFragment());
                transaction.commit();
            }
        });
    }
}