package org.o7planning.yiji2.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.o7planning.yiji2.R;
import org.o7planning.yiji2.adapter.AdapterLesson;
import org.o7planning.yiji2.adapter.ApdapterBook;
import org.o7planning.yiji2.model.Book;
import org.o7planning.yiji2.model.Lesson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class LessonFragment extends Fragment {
    ArrayList<Lesson> arrayList;
    AdapterLesson adapterLesson;
    RecyclerView recyclerView;
    SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =   getView().findViewById(R.id.recycle);
        arrayList = new ArrayList<>();
        adapterLesson = new AdapterLesson(getContext(), arrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterLesson);
        lesson();
        adapterLesson.notifyDataSetChanged();
    }
    void lesson(){
        database = getContext().openOrCreateDatabase("yiji2.db",MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("SELECT * FROM lesson ",new String[]{});
        arrayList.clear();
        while (cursor.moveToNext()){
            Lesson lesson = new Lesson(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getBlob(3),cursor.getInt(4),cursor.getInt(5));
            arrayList.add(lesson);
        }
    }
}