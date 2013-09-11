package com.stocktrackr.db;

import java.util.ArrayList;
import java.util.List;

import com.stocktrackr.model.Stock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StocksDataSource {
	
	public static final String LOGTAG = "STOCKSDB";
	private static final String [] allColumns = {
		StocksDBOpenHelper.COLUMN_ID,
		StocksDBOpenHelper.COLUMN_NAME,
		StocksDBOpenHelper.COLUMN_SYMBOL,
		StocksDBOpenHelper.COLUMN_LPRICE,
		StocksDBOpenHelper.COLUMN_CHANGE,
		StocksDBOpenHelper.COLUMN_VOLUME};
	
	
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
	
	public List<Stock> findAll(){
		List<Stock> stocks = new ArrayList<Stock>();
		
		Cursor cursor = database.query(StocksDBOpenHelper.TABLE_STOCKS, allColumns,
				null, null, null, null, null);
		
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
		if(cursor.getCount() > 0){
			while(cursor.moveToNext()){
				Stock stock = new Stock();
				stock.setId(cursor.getLong(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_ID)));
				stock.setName(cursor.getString(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_NAME)));
				stock.setSymbol(cursor.getString(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_SYMBOL)));
				stock.setChange(cursor.getDouble(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_CHANGE)));
				stock.setVolume(cursor.getInt(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_VOLUME)));
			}
		}
		return stocks;
				
				
	}
	

}
