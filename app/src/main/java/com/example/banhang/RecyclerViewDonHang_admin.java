package com.example.banhang;

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

import com.example.banhang.models.HoaDon;

import java.util.ArrayList;

public class RecyclerViewDonHang_admin extends RecyclerView.Adapter<RecyclerViewDonHang_admin.ViewHolder> {
    private Context mContext;
    private ArrayList<HoaDon> hoaDonArrayList;
    public RecyclerViewDonHang_admin(Context mContext, ArrayList<HoaDon> hoaDonArrayList) {
        this.mContext = mContext;
        this.hoaDonArrayList = hoaDonArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdonhang,parent,false);
        return new ViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtmadonhang.setText("Mã đơn hàng: "+hoaDonArrayList.get(position).getId());
        holder.txtngaydukien.setText("Hàng sẽ được xác nhận trước: "+hoaDonArrayList.get(position).getNgaydukien());
        holder.txttongtien.setText(String.valueOf(hoaDonArrayList.get(position).getTongtien())+"đ");
        holder.rlItemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,CTHoaDon_admin.class);
                intent.putExtra("idHD",hoaDonArrayList.get(position).getId());
                intent.putExtra("tongtien",hoaDonArrayList.get(position).getTongtien());
                intent.putExtra("hoten",hoaDonArrayList.get(position).getHoten());
                intent.putExtra("diachi",hoaDonArrayList.get(position).getDiachi());
                intent.putExtra("sodienthoai",hoaDonArrayList.get(position).getSodienthoai());
                intent.putExtra("trangthai",hoaDonArrayList.get(position).getTrangthai());
                intent.putExtra("lido",hoaDonArrayList.get(position).getLido());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return hoaDonArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtmadonhang,txttongtien,txtngaydukien;
        RelativeLayout rlItemHD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmadonhang = itemView.findViewById(R.id.txtmadonhang);
            txttongtien = itemView.findViewById(R.id.txttongtiendonhang);
            txtngaydukien = itemView.findViewById(R.id.txtngaydk);
            rlItemHD = itemView.findViewById(R.id.rlItemHD);
        }
    }
}
