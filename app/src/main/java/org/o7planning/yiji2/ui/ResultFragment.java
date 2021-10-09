package org.o7planning.yiji2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.o7planning.yiji2.R;
import org.o7planning.yiji2.model.Exam;

public class ResultFragment extends Fragment {
    Button bntBack;
    TextView textView, textViewReview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bntBack = view.findViewById(R.id.btnback);
        textView = view.findViewById(R.id.txt_result);
        textViewReview= view.findViewById(R.id.txt_review_result);

        // Hien thi ket qua
        if((ExamFragment.correct/ExamFragment.total)==1){
            textViewReview.setText("Thật tuyệt vời! Bạn đã nhớ toàn bộ từ đã học");
        }else if(Float.valueOf(ExamFragment.correct/ExamFragment.total)<=0.8 && Float.valueOf(ExamFragment.correct/ExamFragment.total)>=0.5){
            textViewReview.setText("Bạn đã nhớ được phần lớn từ đã học, hãy cố gắng phát huy hơn nữa nhé!");
        }else{
            textViewReview.setText("Ôi thật tiếc, có vẻ như bạn vẫn chưa nhớ hết các từ đã học, hãy chú ý hơn ở lần học sau nhé");
        }
        textView.setText("Số câu đúng "+ String.valueOf(ExamFragment.correct)+"/"+String.valueOf(ExamFragment.total));
        bntBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, new HomeFragment());
                transaction.commit();
            }
        });
    }
}