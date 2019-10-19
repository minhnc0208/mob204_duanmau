package com.example.demo_ps08611_asm_androidcoban.SQL_ASM;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "ASM.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(email text primary key, password text)");
        db.execSQL("CREATE TABLE loai_thu(id integer primary key autoincrement, ten_loai_thu text)");
        db.execSQL("CREATE TABLE khoan_thu(id_thu integer primary key autoincrement, ten_muc_thu text, so_tien decimal, don_vi text, ngay_thu date, gio_thu time, danh_gia integer, id_loaithu integer)");

        db.execSQL("CREATE TABLE loai_chi(id integer primary key autoincrement, ten_loai_chi text)");
        db.execSQL("CREATE TABLE khoan_chi(id_thu integer primary key autoincrement, ten_muc_chi text, so_tien decimal, don_vi text, ngay_chi date, gio_chi time, danh_gia integer, id_loaichi integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS loai_thu");
        db.execSQL("DROP TABLE IF EXISTS khoan_thu");

        db.execSQL("DROP TABLE IF EXISTS loai_chi");
        db.execSQL("DROP TABLE IF EXISTS khoan_chi");
    }

    public boolean insert(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;

    }

    public Boolean checkmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkmailpass(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=? AND password=?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
