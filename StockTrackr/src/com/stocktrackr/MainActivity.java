package com.stocktrackr;

import java.util.List;

import com.stocktrackr.db.StocksDataSource;
import com.stocktrackr.model.Stock;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	
	public static final String LOGTAG = "STOCKSDB";
	
	StocksDataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StocksPullParser parser = new StocksPullParser();
		List<Stock> stocks = parser.parseXML(this);
		
		ArrayAdapter<Stock> adapter = new ArrayAdapter<Stock>(this, android.R.layout.simple_list_item_1, stocks);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	

	/*private void createData(){
		Stock stock = new Stock();
		stock.setName("Apple Inc");
		stock.setSymbol("AAPL");
		stock.setLastPrice(487.115);
		stock.setChange(-0.100999999999999);
		stock.setVolume(568074);
		stock = datasource.create(stock);
		Log.i(LOGTAG, "Stock created with id " + stock.getId());
		
	    stock = new Stock();
		stock.setName("Bank of America Corp");
		stock.setSymbol("BAC");
		stock.setLastPrice(14.115);
		stock.setChange(-0.00499999999999901);
		stock.setVolume(4378773);
		stock = datasource.create(stock);
		Log.i(LOGTAG, "Stock created with id " + stock.getId());
		
		stock = new Stock();
		stock.setName("Facebook, Inc");
		stock.setSymbol("FB");
		stock.setLastPrice(41.31);
		stock.setChange(0.0160000000000053);
		stock.setVolume(3425912);
		stock = datasource.create(stock);
		Log.i(LOGTAG, "Stock created with id " + stock.getId());
		
		stock = new Stock();
		stock.setName("General Motors Co");
		stock.setSymbol("GM");
		stock.setLastPrice(23.1);
		stock.setChange(-0.0399999999999991);
		stock.setVolume(2786454);
		stock = datasource.create(stock);
		Log.i(LOGTAG, "Stock created with id " + stock.getId());
		
		stock = new Stock();
		stock.setName("Sirius SM Radio Inc");
		stock.setSymbol("SIRI");
		stock.setLastPrice(3.58);
		stock.setChange(-0.0499999999999998);
		stock.setVolume(1816606);
		stock = datasource.create(stock);
		Log.i(LOGTAG, "Stock created with id " + stock.getId());
		
	}*/

}
