package com.example.demo_ps08611_asm_androidcoban.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.demo_ps08611_asm_androidcoban.Adapter.NguoidungAdapter;
import com.example.demo_ps08611_asm_androidcoban.R;
import com.example.demo_ps08611_asm_androidcoban.SQL_ASM.UsersDAO;
import com.example.demo_ps08611_asm_androidcoban.model.Usermodel;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {
    public static List<Usermodel> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoidungAdapter adapter = null;
    UsersDAO nguoiDungDAO;
    Toolbar toolbar;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listnguoidung);
        lvNguoiDung = findViewById(R.id.lvNguoiDung);


        nguoiDungDAO = new UsersDAO(ListNguoiDungActivity.this);
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter = new NguoidungAdapter(this, dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);


        toolbar = findViewById(R.id.tool1);

        back = findViewById(R.id.back1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListNguoiDungActivity.this, NguoiDungDetailActivity.class);

                Bundle b = new Bundle();

                b.putString("PHONE", dsNguoiDung.get(position).getPhone());
                b.putString("FULLNAME", dsNguoiDung.get(position).getHoTen());

                intent.putExtra("dulieu", b);
                startActivity(intent);
            }
        });
        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter.changeDataset(nguoiDungDAO.getAllNguoiDung());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuuser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add:
                intent = new Intent(ListNguoiDungActivity.this, ThemNguoiDungActivity.class);
                startActivity(intent);
                return (true);
            case R.id.btnChangePass:
                intent = new Intent(ListNguoiDungActivity.this, ChangePassActivity.class);
                startActivity(intent);
                return (true);
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
            case R.id.btnLogout:
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.clear();
                edit.commit();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                alertDialogBuilder.setTitle("Xác nhận");

                alertDialogBuilder.setIcon(R.drawable.ic_close);
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("Bạn có muốn đăng xuất?");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(ListNguoiDungActivity.this, Login.class);
                        startActivity(intent);
                    }
                });

                alertDialogBuilder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ListNguoiDungActivity.this, "Bạn đã click vào nút không đồng ý", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

