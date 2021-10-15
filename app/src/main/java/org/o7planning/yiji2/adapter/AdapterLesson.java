package org.o7planning.yiji2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.o7planning.yiji2.R;
import org.o7planning.yiji2.model.Book;
import org.o7planning.yiji2.model.Lesson;
import org.o7planning.yiji2.ui.CardFragment;
import org.o7planning.yiji2.ui.DetailLessonFragment;
import org.o7planning.yiji2.ui.LessonFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterLesson extends RecyclerView.Adapter<AdapterLesson.ItemHolder> {

    public AdapterLesson(Context context, ArrayList<Lesson> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public Context context;
    public ArrayList<Lesson> arrayList;


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lesson,null);
        AdapterLesson.ItemHolder itemHolder=new AdapterLesson.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLesson.ItemHolder itemHolder, int i) {
        Lesson lesson =arrayList.get(i);
        itemHolder.txtName.setText(lesson.getName());
        itemHolder.txtCount.setText("Số thẻ "+ String.valueOf(lesson.getSothe()));
        Bitmap bmhinh = BitmapFactory.decodeByteArray(lesson.getImage(),0,lesson.getImage().length);
        itemHolder.image.setImageBitmap(bmhinh);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putSerializable("lesson",lesson);
                DetailLessonFragment fragment= new DetailLessonFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main,fragment).addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public  class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView txtName,txtCount;

        public ItemHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageview);
            txtName=itemView.findViewById(R.id.textview_name);
            txtCount=itemView.findViewById(R.id.textview_count);
        }
    }
}
