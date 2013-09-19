package com.stocktrackr;

import java.security.acl.LastOwnerException;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.stocktrackr.db.StocksDBOpenHelper;
import com.stocktrackr.model.Stock;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddStockActivity extends Activity {
	
	EditText Name;
	EditText Symbol;
	EditText Price;
	EditText Change;
	EditText Volume;
	String nameInfo;
	String symbolInfo;
	String priceInfo;
	String changeInfo;
	String volumeInfo;
	
	SQLiteDatabase database;
	SQLiteOpenHelper stocksDBOpenHelper;
	
	private static final String LOGTAG = "STOCKSDB";
	

  //THIS IS TO ADD A NEW STOCK TO THE LOCAL DB, NOT WORKING FOR SOME REASON
	//Creating a database name (look for this string name when locating the database on your device) and database version
	private static final String DATABASE_NAME = "stocks.db";
	private static final int DATABASE_VERSION = 1;

	//Table and column names
	
	public static final String TABLE_STOCKS = "stocks";
	public static final String COLUMN_ID = "stockID";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_SYMBOL = "symbol";
	public static final String COLUMN_LPRICE = "lastPrice";
	public static final String COLUMN_CHANGE = "change";
	public static final String COLUMN_VOLUME = "volume";
	
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//initialize Parse
	    Parse.initialize(this, "KiyYVr2obq6sxKvo5nUOG8woHJZ7buip6Dvu29aJ", "z1wDwWrz3kOwP95hdORDl4Zfgt1RzZXTRupwPOph"); 
	    ParseAnalytics.trackAppOpened(getIntent());
	    setContentView(R.layout.add_stock_form);
	    
	    textInfo();
	    /*
	    Button localAdd = (Button) findViewById(R.id.button2);
	    localAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addToLocal(null);
				
			}
		});
	    */
	    //save stock button onclick listener
	    Button addNew = (Button) findViewById(R.id.button1);
		addNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				addToParseDB(nameInfo, symbolInfo, priceInfo, changeInfo, volumeInfo);
				textInfo();
				Intent back = new Intent(AddStockActivity.this, MainActivity.class);
				startActivity(back);
			
			}
	});
}
	private void textInfo(){
		//find the edit text fields and button
		
	    Name = (EditText) findViewById(R.id.name_et);
		nameInfo = Name.getText().toString();
	    Symbol = (EditText) findViewById(R.id.symbol_et);
		symbolInfo = Symbol.getText().toString();
		Price = (EditText) findViewById(R.id.price_et);
		priceInfo = Price.getText().toString();
		//int lastPrice = Integer.parseInt(priceInfo);
		Change = (EditText) findViewById(R.id.change_et);
		changeInfo = Change.getText().toString();
		//int changes = Integer.parseInt(changeInfo);
		Volume = (EditText) findViewById(R.id.volume_et);
		volumeInfo = Volume.getText().toString();
		//int vol = Integer.parseInt(volumeInfo);
		
			
	}

	//add stock to parse db
	public void addToParseDB(String name, String symbol, String price, String change, String volume){
		ParseObject newStock = new ParseObject("StockObject");
		Log.i("LOGTAG", "Stock" + nameInfo + "saved to Parse.com");
		newStock.put("name", nameInfo);
		newStock.put("symbol", symbolInfo);
		newStock.put("price", priceInfo);
		newStock.put("change", changeInfo);
		newStock.put("volume", volumeInfo);
		newStock.saveInBackground();
		AddStockActivity.this.finish();
	}
	
	
	
	//ADD A NEW STOCK TO LOCAL DB HERE, NOT WORKING
	//create and add the stock to the database
	/*
	public Stock addToLocal(Stock newStock){
		database = stocksDBOpenHelper.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_NAME, nameInfo);
		cv.put(COLUMN_SYMBOL, symbolInfo);
		cv.put(COLUMN_LPRICE, priceInfo);
		cv.put(COLUMN_CHANGE, changeInfo);
		cv.put(COLUMN_VOLUME, volumeInfo);
		
		long insertNew = database.insert(StocksDBOpenHelper.TABLE_STOCKS, null, cv);
		newStock.setId(insertNew);
		return newStock;
		
		
			
	}
	
	*/
	}
		


