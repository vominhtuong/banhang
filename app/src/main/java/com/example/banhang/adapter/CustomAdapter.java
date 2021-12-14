package com.example.banhang.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.banhang.R;
import com.example.banhang.models.ThuongHieu;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<ThuongHieu> thuongHieuArrayList;
    @Override
    public int getCount() {
        return this.thuongHieuArrayList.size();
    }
    public CustomAdapter(Context context, ArrayList<ThuongHieu> RecipeImageUrl) {
        this.context = context;
        this.thuongHieuArrayList = RecipeImageUrl;
    }
    @Override
    public Object getItem(int position) {
        return thuongHieuArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Context context;
    private LayoutInflater inflater;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);
        String url = thuongHieuArrayList.get(position).getHinhTH();
        Picasso.get().load(url).into(imageView);
        return convertView;
    }
}
