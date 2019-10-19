package com.example.demo_ps08611_asm_androidcoban.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.demo_ps08611_asm_androidcoban.Activity.Chitiet;
import com.example.demo_ps08611_asm_androidcoban.Activity.ListBanChayActivity;
import com.example.demo_ps08611_asm_androidcoban.Activity.ListHoaDonActivity;
import com.example.demo_ps08611_asm_androidcoban.Activity.ListNguoiDungActivity;
import com.example.demo_ps08611_asm_androidcoban.R;

public class ThongKeFragment extends Fragment {

    public CardView them_user, hoadon2, sachbanchay2, thongke2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container,false);
        them_user = view.findViewById(R.id.them_user);
        hoadon2 = view.findViewById(R.id.hoadon2);
        sachbanchay2 = view.findViewById(R.id.sachbanchay2);
        thongke2 = view.findViewById(R.id.thongke2);

        them_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ListNguoiDungActivity.class);
                startActivity(i);
            }
        });

        hoadon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ListHoaDonActivity.class);
                startActivity(i);
            }
        });
        sachbanchay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ListBanChayActivity.class);
                startActivity(i);
            }
        });
        thongke2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Chitiet.class);
                startActivity(i);
            }
        });

        return view;
    }
}
