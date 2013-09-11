package com.stocktrackr;

import java.util.List;

import com.stocktrackr.db.StocksDataSource;
import com.stocktrackr.model.Stock;
import com.stocktrackr.xml.StocksPullParser;

import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	
	public static final String LOGTAG = "STOCKSDB";

	
	StocksDataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
		datasource = new StocksDataSource(this);
		datasource.open();
		
		List<Stock> stocks = datasource.findAll();
		if(stocks.size() == 0){
			createData();
			stocks = datasource.findAll();
		}
		
		ArrayAdapter<Stock> adapter = new ArrayAdapter<Stock>(this, android.R.layout.simple_list_item_1, stocks);
		setListAdapter(adapter);
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

	private void createData(){
		StocksPullParser parser = new StocksPullParser();
		List<Stock> stocks = parser.parseXML(this);
		
		for(Stock stock : stocks){
			datasource.create(stock);
		}
		
	}

}
