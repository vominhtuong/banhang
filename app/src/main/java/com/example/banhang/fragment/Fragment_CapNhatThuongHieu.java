package com.example.banhang.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhang.Activity.Activity_ThemTH;
import com.example.banhang.R;
import com.example.banhang.Recyclerview_CapnhatThuongHieu;
import com.example.banhang.models.ThuongHieu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Fragment_CapNhatThuongHieu extends Fragment {
    RecyclerView recyclerView;
    Button btnthemT;
    ArrayList<ThuongHieu> list;
    DatabaseReference reference;
    Recyclerview_CapnhatThuongHieu adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuonghieu,container,false);
        recyclerView = view.findViewById(R.id.recyclerThuongHieu);
        btnthemT = view.findViewById(R.id.btnthemT);
        btnthemT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_ThemTH.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void loadData() {
        list = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("thuonghieu");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    String key = ds.getKey();
                    String hinhth = ds.child("hinh").getValue(String.class);
                    String tenth = ds.child("tenTH").getValue(String.class);
                    AtomicBoolean isThuongHieu = new AtomicBoolean();

                    list.forEach(thuongHieu -> {
                        if(thuongHieu.getID().equals(key)){
                            isThuongHieu.set(true);
                        }
                    });

                    if(!isThuongHieu.get()){
                        ThuongHieu th = new ThuongHieu(key, tenth, hinhth);
                        list.add(th);
                    }
                }
                //adapter = new Recyclerview_CapnhatThuongHieu(getActivity(),list);
                //recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        loadData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new Recyclerview_CapnhatThuongHieu(getActivity(),list);
        recyclerView.setAdapter(adapter);

    }
}
