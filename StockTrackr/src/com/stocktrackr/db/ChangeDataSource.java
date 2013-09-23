package com.stocktrackr.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.stocktrackr.model.Stock;

public class ChangeDataSource {

	public static final String LOGTAG = "STOCKSDB";
	//retrieve all rows and columns of the database using the helper class
	private static final String [] allColumns = {
		StocksDBOpenHelper.COLUMN_ID,
		StocksDBOpenHelper.COLUMN_NAME,
		StocksDBOpenHelper.COLUMN_SYMBOL,
		StocksDBOpenHelper.COLUMN_LPRICE,
		StocksDBOpenHelper.COLUMN_CHANGE,
		StocksDBOpenHelper.COLUMN_VOLUME};
	
	//declaring database and database helper 
	SQLiteOpenHelper dbHelper;
	SQLiteDatabase database;
	
	//constructor method for the class and declare the instance of the helper class
	public ChangeDataSource(Context context){
		dbHelper = new StocksDBOpenHelper(context);
	}
	
	//open the writeable database by referencing the helper class 
	public void open(){
		Log.i(LOGTAG, "Database Opened");
		database = dbHelper.getWritableDatabase();
	}
	//close the database here using the close method
	public void close(){
		Log.i(LOGTAG, "Database Closed");
		dbHelper.close();
	}
	//create each data record and insert into the table and retrieve the information
	public Stock create(Stock stock){
		ContentValues values = new ContentValues();
		//using key, value pairs insert the data into the proper column created in the database
		values.put(StocksDBOpenHelper.COLUMN_NAME, stock.getName());
		values.put(StocksDBOpenHelper.COLUMN_SYMBOL, stock.getSymbol());
		values.put(StocksDBOpenHelper.COLUMN_LPRICE, stock.getLastPrice());
		values.put(StocksDBOpenHelper.COLUMN_CHANGE, stock.getChange());
		values.put(StocksDBOpenHelper.COLUMN_VOLUME, stock.getVolume());
		
		//you must set the type of the primary key to be a long value to match the api
		long insertid = database.insert(StocksDBOpenHelper.TABLE_STOCKS, null, values);
		//insert into database
		stock.setId(insertid);
		//return object
		return stock;
	}
	//Find all of the values inserted into the table and return them
	public List<Stock> findAll(){
		//reference to the data returned, here we find all of the data
		Cursor cursor = database.query(StocksDBOpenHelper.TABLE_STOCKS, allColumns,
				null, null, null, null, null);
		
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
		List<Stock> stocks = cursorToList(cursor);
		return stocks;			
	}
	
	public List<Stock> findFiltered(String selection, String orderBy){
		//reference to the data returned by the query used to retrieve the data here we are finding filtered data
		Cursor cursor = database.query(StocksDBOpenHelper.TABLE_STOCKS, allColumns,
				selection, null, null, null, orderBy);
		
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
		List<Stock> stocks = cursorToList(cursor);
		return stocks;
				
				
	}

	private List<Stock> cursorToList(Cursor cursor) {
		List<Stock> stocks = new ArrayList<Stock>();
		if(cursor.getCount() > 0){
			//
			while(cursor.moveToNext()){
				//instance to the Stock class
				Stock stock = new Stock();
				//loop through and retrieve the item and pass it into an object
				//stock.setId(cursor.getLong(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_ID)));
				stock.setName(cursor.getString(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_NAME)));
				stock.setSymbol(cursor.getString(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_SYMBOL)));
				stock.setLastPrice(cursor.getDouble(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_LPRICE)));
				stock.setChange(cursor.getDouble(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_CHANGE)));
				stock.setVolume(cursor.getInt(cursor.getColumnIndex(StocksDBOpenHelper.COLUMN_VOLUME)));
				//add the object to the list
				stocks.add(stock);
			}
		}
		//return the object
		return stocks;
	}
	public void deleteObjects(){
		database.execSQL("DROP TABLE IF EXISTS" + StocksDBOpenHelper.TABLE_STOCKS);
		database.execSQL(StocksDBOpenHelper.TABLE_CREATE);
		
	}
	
	
	

}


