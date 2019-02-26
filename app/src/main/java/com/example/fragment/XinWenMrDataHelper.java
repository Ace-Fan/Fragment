package com.example.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class XinWenMrDataHelper {
    //定义一个数据库姓名
    private static final String DATABASE_NAME = "MovieRatings.db";
    //定义一个数据库版本
    private static final int DATABASE_VERSION = 1;
    //定义一个表名
    private static final String TABLE_NAME = "Review";
    //定义一个上下文
    private Context context;
    //定义一个数据库对象
    private SQLiteDatabase db;
    //执行SQL语句的一个SQLiteStatement 对象
    private SQLiteStatement insertStmt;
    //插入语句
    private static final String INSERT = "insert into "
            + TABLE_NAME + "(title,style,date,personname,content) values (?,?,?,?,?)";

    //构造函数
    public XinWenMrDataHelper(Context context) {
        this.context = context;
        //创建OpenHelper实例化
        OpenHelper openHelper = new OpenHelper(this.context);
        //以写的方式打开对应的这个对象，这个时候获取数据库
        this.db = openHelper.getWritableDatabase();
        //调用增加一条记录的方法：执行插入的这条SQL语句
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    //增加一条记录的方法
    public long insert(String title, String style, String date, String personname,
                       String content) {
        //设置SQL语句中？的值，从1开始，依次绑定
        this.insertStmt.bindString(1, title);
        this.insertStmt.bindString(2, style);
        this.insertStmt.bindString(3, date);
        this.insertStmt.bindString(4, personname);
        this.insertStmt.bindString(5, content);
        //执行SQL语句
        return this.insertStmt.executeInsert();
    }

    //根据ID查找到记录并返回到LIST对象
    public List<String> selectById(int id) {
        //创建一个LIST集合
        List<String> list = new ArrayList<String>();
        //执行查询db.query，根据ID查询，返回一个游标cursor
        Cursor cursor = this.db.query(TABLE_NAME, new String[] {"title","style","date","personname","content"},
                "id="+id, null, null, null, null);
        //移到第一条记录：cursor.moveToFirst()
        if (cursor.moveToFirst()) {
            //取出游标中一条记录中的第一个字段：cursor.getString(0)，依次类推，取出所有的字段值，中间用;分割，并存入list集合
            list.add(cursor.getString(0)+";"+cursor.getString(1)+";"+cursor.getString(2)+";"+cursor.getString(3)+";"+cursor.getString(4));
        }
        //关闭游标
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        //返回集合
        return list;
    }

    //返回所有电影的名字
    public List<String> selectName() {
        //创建一个LIST集合
        List<String> list = new ArrayList<String>();
        //返回值为游标，它可以获取表的字段name
        Cursor cursor = this.db.query(TABLE_NAME, new String[] {"title"},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //添加NAME字段（第一个字段0）到集合
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());//移动到下一条
        }
        if (cursor != null && !cursor.isClosed()) {
            //关闭
            cursor.close();
        }
        return list; //返回有NAME值的集合
    }
    //通过创建子类OpenHelper继承SQLiteOpenHelper类
    private static class OpenHelper extends SQLiteOpenHelper {

        //构造函数
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //第一次创建的时候执行，只执行一次
        public void onCreate(SQLiteDatabase db) {
            //创建表
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, title TEXT, style TEXT, date TEXT, personname TEXT,content TEXT)");
        }
        //更新数据库
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Example", "Upgrading database, this will drop tables and recreate.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
