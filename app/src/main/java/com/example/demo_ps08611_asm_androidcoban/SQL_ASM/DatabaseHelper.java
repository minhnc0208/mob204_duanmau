package com.example.demo_ps08611_asm_androidcoban.SQL_ASM;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";
    public static final int VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsersDAO.SQL_NGUOI_DUNG);
        db.execSQL(CategoryDAO.SQL_THE_LOAI);
        db.execSQL(BookDAO.SQL_SACH);
        db.execSQL(BillDAO.SQL_HOA_DON);
        db.execSQL(BillInfoDAO.SQL_HOA_DON_CHI_TIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + UsersDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + CategoryDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + BookDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + BillDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + BillInfoDAO.TABLE_NAME);
        onCreate(db);
    }
}