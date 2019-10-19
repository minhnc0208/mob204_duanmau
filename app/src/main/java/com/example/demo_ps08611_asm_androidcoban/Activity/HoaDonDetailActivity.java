package com.example.demo_ps08611_asm_androidcoban.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_ps08611_asm_androidcoban.Adapter.HoadonchitietAdapter;
import com.example.demo_ps08611_asm_androidcoban.R;
import com.example.demo_ps08611_asm_androidcoban.SQL_ASM.BillInfoDAO;
import com.example.demo_ps08611_asm_androidcoban.SQL_ASM.BookDAO;
import com.example.demo_ps08611_asm_androidcoban.model.Bookmodel;
import com.example.demo_ps08611_asm_androidcoban.model.HDCTmodel;
import com.example.demo_ps08611_asm_androidcoban.model.HoaDonModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDetailActivity extends AppCompatActivity {
    public List<HDCTmodel> dsHDCT = new ArrayList<>();
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    BillInfoDAO hoaDonChiTietDAO;
    BookDAO sachDAO;
    ListView lvCart;
    HoadonchitietAdapter adapter = null;
    double thanhTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THANH TOÁN");
        setContentView(R.layout.activity_hoadondetail);
        edMaSach = findViewById(R.id.edMaSach);
        edMaHoaDon = findViewById(R.id.edMaHoaDon);
        edSoLuong = findViewById(R.id.edSoLuongMua);
        lvCart = findViewById(R.id.lvCart);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        adapter = new HoadonchitietAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
    }

    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new BillInfoDAO(HoaDonDetailActivity.this);
        sachDAO = new BookDAO(HoaDonDetailActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Bookmodel sach = sachDAO.getSachByID(edMaSach.getText().toString());
                if (sach != null) {
                    int pos = checkMaSach(dsHDCT, edMaSach.getText().toString());
                    HoaDonModel hoaDon = new HoaDonModel(edMaHoaDon.getText().toString(), new
                            Date());
                    HDCTmodel hoaDonChiTiet = new
                            HDCTmodel(1, hoaDon, sach, Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos >= 0) {
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong +
                                Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos, hoaDonChiTiet);
                    } else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                } else {
                    Toast.makeText(getApplicationContext(), "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new BillInfoDAO(HoaDonDetailActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HDCTmodel hd : dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaSach(List<HDCTmodel> lsHD, String maSach) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            HDCTmodel hd = lsHD.get(i);
            if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation() {
        if (edMaSach.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty() ||
                edMaHoaDon.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}