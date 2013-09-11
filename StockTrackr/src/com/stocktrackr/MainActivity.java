package com.stocktrackr;

import com.stocktrackr.db.StocksDBOpenHelper;
import com.stocktrackr.db.StocksDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;

public class MainActivity extends Activity {
	
	public static final String LOGTAG = "STOCKSDB";
	
	StocksDataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//database stuff
		datasource = new StocksDataSource(this);
		
		//ArrayAdapter<Stock> adapter = new ArrayAdapter<Stock>(this, android.R.layout.simple_list_item_1, stocks);
		//setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}

}
