package com.example.banhang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhang.adapter.userAdapter;
import com.example.banhang.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QLND extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<User> userArrayList;
    DatabaseReference reference;
    private userAdapter adapter;

    Button btnXemLSTC;
    ImageView imgback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qlnd);
        btnXemLSTC = findViewById(R.id.btnxemLSTC);
        recyclerView = findViewById(R.id.rv);
        imgback =  findViewById(R.id.imgBack);
        btnXemLSTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QLND.this,LSTC.class);
                startActivity(intent);
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        reference = FirebaseDatabase.getInstance().getReference().child("taikhoan");
        userArrayList = new ArrayList<>();
        ClearAll();
        GetDataFromFireBase();
    }
    private void ClearAll() {
        if(userArrayList!=null){
            userArrayList.clear();
            if(adapter != null){
                adapter.notifyDataSetChanged();
            }
        }
        userArrayList = new ArrayList<>();
    }
    private void GetDataFromFireBase(){
        Query query = reference.orderByChild("tenLoai").equalTo("client");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = new User();
                    user.setHoten(dataSnapshot.child("hoten").getValue().toString());
                    user.setSodienthoai(dataSnapshot.child("sodienthoai").getValue().toString());
                    user.setNgaythamgia(dataSnapshot.child("ngaythamgia").getValue().toString());
                    user.setDiachi(dataSnapshot.child("diachi").getValue().toString());
                    user.setNgaysinh(dataSnapshot.child("ngaysinh").getValue().toString());
                    user.setGioitinh(dataSnapshot.child("gioitinh").getValue().toString());
                    userArrayList.add(user);
                }
                adapter = new userAdapter(getApplicationContext(),userArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.aqua));
        }
    }
}
