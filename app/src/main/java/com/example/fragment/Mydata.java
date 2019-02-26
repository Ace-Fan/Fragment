package com.example.fragment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Mydata{

	private Context context;
	private SQLiteDatabase db;
	private SQLiteStatement insertStmt;
	//定义一个数据库姓名
    private static final String DATABASE_NAME = "jiazheng.db";
	//定义一个表名
	private static final String TABLE_NAME = "use";
	private String CREATE_TABLE_SQL="create table use(_id integer primary key autoincrement , pass)";
	//插入语句
	private static final String INSERT = "insert into use(name,sex,tel,money) values (?,?,?,?)";

	//构造函数
    public Mydata(Context context) {
	 	this.context = context;
	 	//创建OpenHelper实例化
		OpenHelper openHelper = new OpenHelper(this.context);
		//以写的方式打开对应的这个对象，这个时候获取数据库
		this.db = openHelper.getWritableDatabase();
		//调用增加一条记录的方法：执行插入的这条SQL语句
		this.insertStmt = this.db.compileStatement(INSERT);
    }
/* Insert method */
public long insert(String name,String sex,String tel,String money) {
		this.insertStmt.bindString(1, name);
		this.insertStmt.bindString(2,sex);
		this.insertStmt.bindString(3, tel);
		this.insertStmt.bindString(4, money);
		return this.insertStmt.executeInsert();
	}

public List<String> selectById(String id) {
		// TODO 自动生成的方法存根
		
			  //创建一个LIST集合
			  List<String> list = new ArrayList<String>();
			  //执行查询db.query，根据ID查询，返回一个游标cursor
			  Cursor cursor = this.db.query(TABLE_NAME, new String[] {"name","sex","tel","money"}, "id="+id, null, null, null, null);
			  //移到第一条记录：cursor.moveToFirst()
			  if (cursor.moveToFirst()) {
			    //取出游标中一条记录中的第一个字段：cursor.getString(0)，依次类推，取出所有的字段值，中间用;分割，并存入list集合
			    list.add("姓名："+cursor.getString(0)+"; 性别："+cursor.getString(1)
			    		+"; 电话："+cursor.getString(2)+"; 工资："+cursor.getString(3));
			  }
			  //关闭游标
			  if (cursor != null && !cursor.isClosed()) {
			     cursor.close();
			  }
			  //返回集合
			  return list;
		
	}

	public List<String> selectName() {
		// TODO 自动生成的方法存根
		//创建一个LIST集合
		 List<String> list = new ArrayList<String>();
	      //返回值为游标，它可以获取表的字段name
	      Cursor cursor = this.db.query(TABLE_NAME, new String[] {"name"}, 
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
	public List<String> selectId() {
		// TODO 自动生成的方法存根
		//创建一个LIST集合
		 List<String> list = new ArrayList<String>();
	      //返回值为游标，它可以获取表的字段name
	      Cursor cursor = this.db.query(TABLE_NAME, new String[] {"id"}, 
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
	     super(context, DATABASE_NAME, null, 1);
	  }

	  //第一次创建的时候执行，只执行一次
	  public void onCreate(SQLiteDatabase db) {
		  //创建表
	     db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, name TEXT, sex TEXT, tel TEXT, money TEXT)");
	  }												
	  //更新数据库
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	     Log.w("Example", "Upgrading database, this will drop tables and recreate.");
	     db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	     onCreate(db);
	  }
	}

}
