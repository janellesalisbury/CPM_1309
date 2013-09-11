package com.stocktrackr.db;

import com.stocktrackr.model.Stock;

import android.content.ContentValues;
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
	
	public Stock create(Stock stock){
		ContentValues values = new ContentValues();
		values.put(StocksDBOpenHelper.COLUMN_NAME, stock.getName());
		values.put(StocksDBOpenHelper.COLUMN_SYMBOL, stock.getSymbol());
		values.put(StocksDBOpenHelper.COLUMN_LPRICE, stock.getLastPrice());
		values.put(StocksDBOpenHelper.COLUMN_CHANGE, stock.getChange());
		values.put(StocksDBOpenHelper.COLUMN_VOLUME, stock.getVolume());
		
		long insertid = database.insert(StocksDBOpenHelper.TABLE_STOCKS, null, values);
		stock.setId(insertid);
		return stock;
	}
	

}
