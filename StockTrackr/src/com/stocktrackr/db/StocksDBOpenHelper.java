package com.stocktrackr.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//database management class
public class StocksDBOpenHelper extends SQLiteOpenHelper {
	
	private static final String LOGTAG = "STOCKSDB";

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

	//Create the stocks table (setting the column names and their data type)
	private static final String TABLE_CREATE = 
	"CREATE TABLE " + TABLE_STOCKS + " (" +
	COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
	COLUMN_NAME + " TEXT, " + 
	COLUMN_SYMBOL + " TEXT, " +
	COLUMN_LPRICE + " NUMERIC, " +
	COLUMN_CHANGE + " NUMERIC, " +
	COLUMN_VOLUME + " NUMERIC " + 
	")";

	//constructor method for the openHelper class. This will construct and open up the class and its methods.
	public StocksDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	
	}

	//Calling the Table_Create function to create the database table
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
		Log.i(LOGTAG, "Table has been created");

	}

	//drop the table if it already exists and recreate it using the onCreate method above
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_STOCKS);
		onCreate(db);

	}

}
