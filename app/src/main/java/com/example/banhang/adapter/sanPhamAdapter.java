package com.example.banhang.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.banhang.MainActivity;
import com.example.banhang.R;
import com.example.banhang.models.SanPham;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class sanPhamAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public ArrayList<SanPham> sanPham;
    public static final  String SHARED_PREFS = "sharedPrefs";
    public sanPhamAdapter(Context context, ArrayList<SanPham> sanpham) {
        this.context = context;
        this.sanPham = sanpham;
    }

    @Override
    public int getCount() {
        return this.sanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return this.sanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SanPham currentItem = (SanPham) getItem(position);
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_sanpham, null);
        }
        ImageView imageView = convertView.findViewById(R.id.image_sanpham);
        TextView textView = convertView.findViewById(R.id.txttensp);
        TextView giaTienText = convertView.findViewById(R.id.txtgiatien);
        TextView tenst = convertView.findViewById(R.id.txttenst);
        textView.setText(currentItem.getTenSP());
        giaTienText.setText(String.valueOf(currentItem.getGiaSP()+"đồng"));
        tenst.setText(currentItem.getTenST());
        ImageButton imageButton = convertView.findViewById(R.id.imgyeuthich);
        if(currentItem.isYeuThich() == true){
            imageButton.setImageResource(R.drawable.favorite_icon);
        }
        else{
            imageButton.setImageResource(R.drawable.favorite_border_icon);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentItem.isYeuThich() == false) {
                    imageButton.setImageResource(R.drawable.favorite_icon);
                    currentItem.setYeuThich(true);
                    MainActivity.listYT.add(currentItem);
                    saveData(context);
                    Toast.makeText(v.getContext(),"Đã Thêm Vào Yêu Thích",Toast.LENGTH_SHORT).show();
                }
                else {
                    currentItem.setYeuThich(false);
                    /// khi nó là false -> remove nó khỏi listYT
                    MainActivity.listYT.remove(currentItem);
                    saveData(context);
                    notifyDataSetChanged();
                    imageButton.setImageResource(R.drawable.favorite_border_icon);
                    Toast.makeText(v.getContext(),"Xóa Khỏi Yêu Thích",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /// url của firebase
        String url = sanPham.get(position).getHinhSP();
        Picasso.get().load(url).into(imageView);
        return convertView;
    }
    private void saveData(Context mcontext){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // creating a new variable for gson.
        Gson gson = new Gson();
        // getting data from gson and storing it in a string.
        String json2 = gson.toJson(MainActivity.listYT);
        //below line is to save data in shared
        //prefs in the form of string.
        editor.putString("listYT", json2);
        editor.apply();
    }
}
