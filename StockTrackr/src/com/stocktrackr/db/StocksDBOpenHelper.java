package com.stocktrackr.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StocksDBOpenHelper extends SQLiteOpenHelper {
	
	private static final String LOGTAG = "STOCKSDB";

	private static final String DATABASE_NAME = "stocks.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_STOCKS = "stocks";
	public static final String COLUMN_ID = "stockID";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_SYMBOL = "symbol";
	public static final String COLUMN_LPRICE = "lastPrice";
	public static final String COLUMN_CHANGE = "change";
	public static final String COLUMN_VOLUME = "volume";

	private static final String TABLE_CREATE = 
	"CREATE TABLE " + TABLE_STOCKS + " (" +
	COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
	COLUMN_NAME + " TEXT, " + 
	COLUMN_SYMBOL + " TEXT, " +
	COLUMN_LPRICE + " NUMERIC, " +
	COLUMN_CHANGE + " NUMERIC, " +
	COLUMN_VOLUME + " NUMERIC " + 
	")";

	public StocksDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
		Log.i(LOGTAG, "Table has been created");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_STOCKS);
		onCreate(db);

	}

}
