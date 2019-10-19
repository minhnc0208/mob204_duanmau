package com.example.demo_ps08611_asm_androidcoban.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.demo_ps08611_asm_androidcoban.R;
import com.example.demo_ps08611_asm_androidcoban.fragment.ThongKeFragment;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ThongKeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_thongke);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_chi:
                Intent i = new Intent(Home.this, ListSachActivity.class);
                startActivity(i);
                break;
            case R.id.nav_thongke:
                Intent a = new Intent(Home.this, ListTheLoaiActivity.class);
                startActivity(a);
                break;
            case R.id.nav_user:
                Intent m = new Intent(Home.this, ListNguoiDungActivity.class);
                startActivity(m);
                break;
            case R.id.nav_chitiet:
                Intent q = new Intent(Home.this, ListThongKeActivity.class);
                startActivity(q);
                break;
            case R.id.nav_info:
                Toast.makeText(this,"DeSign By Nguyễn Cao Minh PS08611 LT14304- Dự Án Mẫu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_close:
                Toast.makeText(this,"Error 4 : Ứng Dụng Không Thể Thoát Bằng Cách Này Do Máy Bạn Quá Cùi .Vui Lòng Mua IPhone 11 Pro Max Thể Sử Dụng Tính Năng VIP này !!!", Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
