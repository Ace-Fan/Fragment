package com.example.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
 final String CREATE_TABLE_SQL=
 "create table dict(_id integer primary " +
         "key autoincrement , word,detail)";
 public MyDatabaseHelper(Context context, String name, int version)
 {
     super(context,name,null,version);
 }
 public void onCreate(SQLiteDatabase db){
     //第一次使用数据库时自动见表
     db.execSQL(CREATE_TABLE_SQL);
 }
 public void onUpgrade(SQLiteDatabase db,
                       int oldVersion,int newVersion)
 {
     System.out.println("-----onUpdate called-----"+
     oldVersion+"---->"+newVersion);
 }
}