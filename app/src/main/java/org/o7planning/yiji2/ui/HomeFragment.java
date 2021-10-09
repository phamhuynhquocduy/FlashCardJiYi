package org.o7planning.yiji2.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.o7planning.yiji2.LoginActivity;
import org.o7planning.yiji2.MainActivity;
import org.o7planning.yiji2.adapter.ApdapterBook;
import org.o7planning.yiji2.R;
import org.o7planning.yiji2.model.Book;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    String DATABASE_NAME ="yiji2.db";
    ApdapterBook arrayAdapter;
    RecyclerView recyclerView;
    ArrayList<Book> arrayList;
    SQLiteDatabase database;
    Button btnHSK, btnTopic;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnHSK = view.findViewById(R.id.btn_hsk);
        btnTopic = view.findViewById(R.id.btn_topic);
        recyclerView =   getView().findViewById(R.id.recycle);
        arrayList = new ArrayList<>();
        arrayAdapter = new ApdapterBook(getContext(), arrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(arrayAdapter);
        // Load danh sach loai sach HKS
        book(1);
        arrayAdapter.notifyDataSetChanged();

        btnTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                book(2);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        btnHSK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                book(1);
                arrayAdapter.notifyDataSetChanged();
                Log.d("Sodanhsach", String.valueOf(arrayList.size()));
            }
        });
    }
    void book(int t){
        database = getContext().openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("SELECT * FROM book WHERE type=? ",new String[]{String.valueOf(t)});
        while (cursor.moveToNext()){
            Book book = new Book(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(5),null);
            book.setImage(cursor.getBlob(3));
            arrayList.add(book);
        }
    }
}