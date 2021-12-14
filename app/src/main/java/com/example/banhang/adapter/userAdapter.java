package com.example.banhang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhang.R;
import com.example.banhang.chiTietUser;
import com.example.banhang.models.User;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<User> userList;

    public userAdapter(Context mContext, ArrayList<User> userList) {
        this.mContext = mContext;
        this.userList = userList;
    }

    @NonNull
    @Override
    public userAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txthoten.setText(userList.get(position).getHoten());
        holder.txtsodienthoai.setText(userList.get(position).getSodienthoai());
        holder.txtthoigian.setText(userList.get(position).getNgaythamgia());
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, chiTietUser.class);
                intent.putExtra("id",userList.get(position).getId());
                intent.putExtra("hoten",userList.get(position).getHoten());
                intent.putExtra("sodienthoai",userList.get(position).getSodienthoai());
                intent.putExtra("diachi",userList.get(position).getDiachi());
                intent.putExtra("ngaysinh",userList.get(position).getNgaysinh());
                intent.putExtra("hoatdong",userList.get(position).getHoatdong());
                intent.putExtra("gioitinh",userList.get(position).getGioitinh());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txthoten,txtsodienthoai,txtthoigian;
        RelativeLayout rlItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txthoten = itemView.findViewById(R.id.txthoten);
            txtsodienthoai = itemView.findViewById(R.id.txtsodienthoai);
            txtthoigian = itemView.findViewById(R.id.txtthoigianTG);
            rlItem = itemView.findViewById(R.id.rlImtem);
        }
    }
}
