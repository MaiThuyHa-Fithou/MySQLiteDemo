package com.mtha.mysqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbOpenHelper extends SQLiteOpenHelper {
    //khai bao cac hang su dung
    final static int DB_VERSION = 1;
    final static String DB_NAME="dbUser.db";
    //dinh nghia ten bang, ten cot
    final static String TB_NAME = "taikhoan";
    final static String _ID="id";
    final static String _TEN ="ten";
    final static String _DT="dt";
    final static String _EMAIL ="email";

    public DbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //dinh nghia cau lenh tao bang
        String sql ="CREATE TABLE " + TB_NAME + "( " + _ID +" integer PRIMARY KEY, "
                + _TEN +" text, " + _DT +" text, " + _EMAIL +" text );" ;
        //thuc thi cau lenh
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xoa csdl cu neu ton tai
        db.execSQL("DROP TABLE IF EXISTS " +TB_NAME +";");
        //goi lai ham tao csdl
        onCreate(db);
    }

    public long insTaiKhoan(TaiKhoan tk){
        //tao doi tuong ContentValue de chua cac gia tri can insert db
        ContentValues contentValues = new ContentValues();
        contentValues.put(_TEN,tk.getTen());
        contentValues.put(_DT, tk.getDienThoai());
        contentValues.put(_EMAIL, tk.getEmail());
        //lay ra duoc doi tuong sqlitedatabase de thuc hien ghi du lieu
        SQLiteDatabase db = getWritableDatabase();

        return db.insert(TB_NAME,null,contentValues);
    }

    public int updTaiKhoan (TaiKhoan tk, String ten){
        ContentValues values = new ContentValues();
        values.put(_DT, tk.getDienThoai());
        values.put(_EMAIL, tk.getEmail());
        String[] dk ={ten};
        SQLiteDatabase db=getWritableDatabase();
        return  db.update(TB_NAME,values,"ten like '%?%' ", dk);
    }

    public int delTaiKhoan(String id){
        return getWritableDatabase().delete(TB_NAME,"id=?",new String[]{id});
    }

    public ArrayList<String> getAllTaiKhoan(){
        ArrayList<String> lsTK = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + TB_NAME, null);
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String ten = cs.getString(1);
            String dt = cs.getString(2);
            String email = cs.getString(3);
            lsTK.add(new TaiKhoan(id,ten,dt,email).toString());
        }
        return  lsTK;
    }
}
