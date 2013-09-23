package com.stocktrackr;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.stocktrackr.db.StocksDBOpenHelper;
import com.stocktrackr.model.Stock;
import com.stocktrackr.db.*;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
	int price2;
	int change2;
	int volume2;
	
	Context context;
	Button save;

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
		context = this;
		setContentView(R.layout.add_stock_form);
		/*
			    Button localAdd = (Button) findViewById(R.id.button2);
			    localAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						addToLocal(newStock);
					
				}
		});
		*/	
		textInfo();
		//save stock button onclick listener
		//	    Button addNew = (Button) findViewById(R.id.button1);
		//		addNew.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				
		//				addToParseDB(nameInfo, symbolInfo, priceInfo, changeInfo, volumeInfo);
		//				//addToParseDB(textInfo());
		//				Intent back = new Intent(AddStockActivity.this, MainActivity.class);
		//				startActivity(back);
		//			
		//			}
		//	});
	}
	private void textInfo(){
		//find the edit text fields and button

		Name = (EditText) findViewById(R.id.name_et);
		nameInfo = Name.getText().toString();
		Log.i("your txt", nameInfo);
		Symbol = (EditText) findViewById(R.id.symbol_et);
		symbolInfo = Symbol.getText().toString();
		/*
		Price = (EditText) findViewById(R.id.price_et);
		priceInfo = Price.getText().toString();
		price2 = Integer.parseInt(priceInfo);
		Change = (EditText) findViewById(R.id.change_et);
		changeInfo = Change.getText().toString();
		change2 = Integer.parseInt(changeInfo);
		Volume = (EditText) findViewById(R.id.volume_et);
		volumeInfo = Volume.getText().toString();
		volume2 = Integer.parseInt(volumeInfo);
*/
		Button save = (Button) findViewById(R.id.button1);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//addToParseDB(nameInfo, symbolInfo, price2, change2, volume2);
				//addToParseDB(textInfo());
				Log.i("log text", "Button Clicked");
				validateAndConvert();

			}
		});

	}
	//keep getting invalid int: "" error for some reason!
	public boolean validateAndConvert(){
		if(Name.getText().length() <= 0){
			myToast("Please Enter a Valid Name");
			return false;
		}
			nameInfo = Name.getText().toString();
			
		if(Symbol.getText().length() <= 0 ){
			myToast("Please Enter a Valid Symbol");
			return false;
		}
			symbolInfo = Symbol.getText().toString();
		/*	
		 * this is where the invalid integer error comes into play.
		if(Price.getText().length() <= 0){
			myToast("Please Enter a Valid Price");
			return false;			
		}
			//priceInfo = Price.getText().toString();
			price2 = Integer.parseInt(priceInfo);
		
		if(Change.getText().length() <= 0){
			myToast("Please Enter a Valid Change");
			return false;			
		}
		//changeInfo = Change.getText().toString();
		change2 = Integer.parseInt(changeInfo);
		
		if(Volume.getText().length() <= 0){
			myToast("Please Enter a Valid Volume");
			return false;			
		}
			//volumeInfo = Volume.getText().toString();
			volume2 = Integer.parseInt(volumeInfo);
		*/			
		addToParseDB(nameInfo, symbolInfo, priceInfo, changeInfo, volumeInfo);
		//addToLocal(newStock);
		return true;
		
}

	//add stock to parse db
		public void addToParseDB(String name, String symbol, String price, String change, String volume ){
			ParseObject newStock = new ParseObject("StockObject");
			newStock.put("name", nameInfo);
			newStock.put("symbol", symbolInfo);
			//newStock.put("price", price2);
			//newStock.put("change", change2);
			//newStock.put("volume", volume2);
			newStock.saveEventually();
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
			//cv.put(COLUMN_LPRICE, priceInfo);
			//cv.put(COLUMN_CHANGE, changeInfo);
			//cv.put(COLUMN_VOLUME, volumeInfo);
			
			long insertNew = database.insert(StocksDBOpenHelper.TABLE_STOCKS, null, cv);
			newStock.setId(insertNew);
			return newStock;
//			
//			
//				
	}
	*/
		
		
		public void myToast(String text){  
			CharSequence textIN = text;
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(AddStockActivity.this, textIN, duration);
			toast.setGravity(Gravity.BOTTOM, 0, 0);
			toast.show();
		};
		
		}
			


