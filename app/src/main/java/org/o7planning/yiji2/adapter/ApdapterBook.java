package org.o7planning.yiji2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.o7planning.yiji2.R;
import org.o7planning.yiji2.model.Book;
import org.o7planning.yiji2.ui.LessonFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ApdapterBook extends RecyclerView.Adapter<ApdapterBook.ItemHolder> {
    public Context context;
    public ArrayList<Book> arrayList;

    public ApdapterBook(Context context, ArrayList<Book> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book,null);
        ApdapterBook.ItemHolder itemHolder=new ApdapterBook.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        Book product=arrayList.get(i);
        itemHolder.txtName.setText(product.getName());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        if(product.getPrice()==0){
            itemHolder.txtPrice.setTextColor(Color.parseColor("#2FFA3D"));
            itemHolder.txtPrice.setText("Miễn phí");
        }else{
            itemHolder.txtPrice.setText(decimalFormat.format(product.getPrice())+" đ");
        }
        Bitmap bmhinh = BitmapFactory.decodeByteArray(product.getImage(),0,product.getImage().length);
        itemHolder.image.setImageBitmap(bmhinh);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().addToBackStack(null);
                transaction.replace(R.id.content_main, new LessonFragment());
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
        public TextView txtName,txtPrice;

        public ItemHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageview);
            txtName=itemView.findViewById(R.id.textview_name);
            txtPrice=itemView.findViewById(R.id.textview_price);
        }
    }
    private  void replaceFragment(Fragment fragment){

    }
}