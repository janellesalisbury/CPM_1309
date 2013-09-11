package com.stocktrackr.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StocksDataSource {
	
	public static final String LOGTAG = "STOCKSDB";
	
	SQLiteOpenHelper dbHelper;
	SQLiteDatabase database;
	
	public StocksDataSource(Context context){
		dbHelper = new StocksDBOpenHelper(context);
	}
	
	public void open(){
		Log.i(LOGTAG, "Database Opened");
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		Log.i(LOGTAG, "Database Closed");
		dbHelper.close();
	}

}
