package com.example.demo_ps08611_asm_androidcoban.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_ps08611_asm_androidcoban.R;
import com.example.demo_ps08611_asm_androidcoban.SQL_ASM.BookDAO;
import com.example.demo_ps08611_asm_androidcoban.SQL_ASM.CategoryDAO;
import com.example.demo_ps08611_asm_androidcoban.model.Bookmodel;
import com.example.demo_ps08611_asm_androidcoban.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ThemSachActivity extends AppCompatActivity {
    BookDAO sachDAO;
    CategoryDAO theLoaiDAO;
    Spinner spnTheLoai;
    Button btnADDBOOK;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<CategoryModel> listTheLoai = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_themsach_fragment);
        setTitle("THÊM SÁCH");
        spnTheLoai = findViewById(R.id.spnTheLoai);
        getTheLoai();
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTenSach);
        edNXB = findViewById(R.id.edNXB);
        edTacGia = findViewById(R.id.edTacGia);
        edGiaBia = findViewById(R.id.edGiaBia);
        edSoLuong = findViewById(R.id.edSoLuong);
        btnADDBOOK = findViewById(R.id.btnAddBook);

        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheloai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaSach.setText(b.getString("MASACH"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenSach.setText(b.getString("TENSACH"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("TACGIA"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }

    }

    public void showSpinner(View view) {

    }

    public void getTheLoai() {
        theLoaiDAO = new CategoryDAO(ThemSachActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<CategoryModel> dataAdapter = new ArrayAdapter<CategoryModel>(this,
                android.R.layout.simple_spinner_item, listTheLoai);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }

    public void addBook(View view) {
        try {
            sachDAO = new BookDAO(ThemSachActivity.this);
            Bookmodel sach = new Bookmodel(edMaSach.getText().toString(), maTheLoai, edTenSach.getText().toString(), edTacGia.getText().toString(), edNXB.getText().toString(), parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText().toString()));
            if (sachDAO.inserSach(sach) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex) {
            String s = edGiaBia.getText().toString();
            String t = edSoLuong.getText().toString();
            if (edMaSach.getText().length() == 0 || edTenSach.getText().length() == 0
                    || edTacGia.getText().length() == 0 || edNXB.getText().length() == 0
                    || edGiaBia.getText().length() == 0 || edSoLuong.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Bạn chưa nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            }
            try {
                parseDouble(s);
                Integer.parseInt(t);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Kiểm tra định dạng giá bán và số lượng ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showBook(View view) {
        finish();
    }

    public void cancel(View view) {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheloai())) {
                return i;
            }
        }
        return 0;
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