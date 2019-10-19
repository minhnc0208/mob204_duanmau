package com.example.demo_ps08611_asm_androidcoban.SQL_ASM;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demo_ps08611_asm_androidcoban.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "CREATE TABLE TheLoai (matheloai text primary key, tentheloai text, mota text, vitri int);";
    public static final String TAG = "TheLoaiDAO";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public CategoryDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserTheLoai(CategoryModel theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheloai());
        values.put("tentheloai", theLoai.getTenTheloai());
        values.put("mota", theLoai.getMoTa());
        values.put("vitri", theLoai.getViTri());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //getAllTheLoai
    public List<CategoryModel> getAllTheLoai() {
        List<CategoryModel> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            CategoryModel ee = new CategoryModel();
            ee.setMaTheloai(c.getString(0));
            ee.setTenTheloai(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setViTri(c.getString(3));
            dsTheLoai.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }

    public List<CategoryModel> getAllTheLoai1() {
        List<CategoryModel> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                CategoryModel ee = new CategoryModel();
                ee.setMaTheloai(c.getString(0));
                ee.setTenTheloai(c.getString(1));
                ee.setMoTa(c.getString(2));
                ee.setViTri(c.getString(3));
                dsTheLoai.add(ee);
                Log.d("//=====", ee.toString());

            }
            while (c.moveToNext());

        }
        c.close();
        return dsTheLoai;
    }

    //update
    public int updateTheLoai(CategoryModel theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheloai());
        values.put("tentheloai", theLoai.getTenTheloai());
        values.put("mota", theLoai.getMoTa());
        values.put("vitri", theLoai.getViTri());
        int result = db.update(TABLE_NAME, values, "matheloai=?", new
                String[]{theLoai.getMaTheloai()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int updateInfoTheLoai(String matheloai, String s, String s1, String s2, String s3) {
        ContentValues values = new ContentValues();
        values.put("matheloai", s);
        values.put("tentheloai", s1);
        values.put("mota", s2);
        values.put("vitri", s3);
        int result = db.update(TABLE_NAME, values, "matheloai=?", new
                String[]{matheloai});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteTheLoaiByID(String matheloai) {
        int result = db.delete(TABLE_NAME, "matheloai=?", new String[]{matheloai});
        if (result == 0)
            return -1;
        return 1;
    }
}
