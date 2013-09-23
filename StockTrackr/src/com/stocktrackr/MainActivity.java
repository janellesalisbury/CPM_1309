package com.stocktrackr;

import java.util.List;

import com.stocktrackr.db.ChangeDataSource;
import com.stocktrackr.db.StocksDBOpenHelper;
import com.stocktrackr.db.StocksDataSource;
import com.stocktrackr.model.Stock;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	
	public static final String LOGTAG = "STOCKSDB";
	//listview declaration
	 private List<Stock> stocks;
	
	
	//instance of the datasource class which hides the openHelper dealings within
	StocksDataSource datasource;
	StocksDBOpenHelper database;
	ChangeDataSource changeDS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//initialize Parse
	    Parse.initialize(this, "KiyYVr2obq6sxKvo5nUOG8woHJZ7buip6Dvu29aJ", "z1wDwWrz3kOwP95hdORDl4Zfgt1RzZXTRupwPOph"); 
	    ParseAnalytics.trackAppOpened(getIntent());
	    
	    //TEST PARSE HERE... this works
	    //ParseObject testObject = new ParseObject("TestObject");
	    //testObject.put("foo", "bar");
	    //testObject.saveInBackground();
	    
	    
		//instantiate the datasource class here
		datasource = new StocksDataSource(this);
		datasource.open();
	
		
		//create and retrieve the data here, if the app has already been run, there is no need to have it be recreated, it will just be read
		stocks = datasource.findAll();
		if(stocks.size() == 0){
			createData();
			stocks = datasource.findAll();
			
		}
		//refresh the list
		refreshDisplay();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		//retrieve all of the data from the database and display it in the list when the all button is clicked
		case R.id.menu_all:
			stocks = datasource.findAll();
			refreshDisplay();
		break;
		//retrieve data that is less that 100 dollars from the database and display it when the -100 button is clicked
		case R.id.menu_less:
			stocks = datasource.findFiltered("lastPrice <= 100", "lastPrice ASC");
			refreshDisplay();
		
		break;
		//retrieve data that is more than 100 dollars from the database and display it when the +100 button is clicked
		case R.id.menu_more:
			stocks = datasource.findFiltered("lastPrice >= 100", "lastPrice DESC");
			refreshDisplay();
		
		break;
		case R.id.menu_stock:
			Intent addIntent = new Intent(MainActivity.this, AddStockActivity.class);
			startActivity(addIntent);
			break;
		case R.id.menu_delete:
			datasource.deleteObjects();
			createData();
		default:
			
			break;
		}
		//return user selection
		return super.onOptionsItemSelected(item);
		
	}
	//Use an arrayAdapter to bind the data objects to the list. The Stock class provides the data type here, it will be displayed
	//as a simple list and will return the stocks objects read from the xml file and parser class, it will be displayed using the 
	//toString method from the Stocks class.
	public void refreshDisplay(){
		ArrayAdapter<Stock> adapter = new ArrayAdapter<Stock>(this, android.R.layout.simple_list_item_1, stocks);
		setListAdapter(adapter);

	
	}
	
	//open the database when the activity comes to the screen.
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}
	//close the database when the activity is cancelled by pressing the back button or home button
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}

	@SuppressWarnings("unchecked")
	private void createData(){
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery("StockObject");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				if(e == null){
					int s = objects.size();
					for (int i = 0; i < s; i++){
						String name = (String) objects.get(i).get("name");
						String symbol = (String) objects.get(i).get("symbol");
						int price = (Integer) objects.get(i).get("lastPrice");
						int change = (Integer) objects.get(i).get("change");
						int volume = (Integer) objects.get(i).get("volume");
						
						Stock stock = new Stock();
						stock.setName(name);
						stock.setSymbol(symbol);
						stock.setLastPrice(price);
						stock.setChange(change);
						stock.setVolume(volume);
						
					}
					}else{
						String error = e.toString();
					}
					
				
			}
		
		//read the xml source and return the objects, 
		//StocksPullParser parser = new StocksPullParser();
		//List<Stock> stocks = parser.parseXML(this);
		//loop through and create an object 
		//for(Stock stock : stocks){
			//datasource.create(stock);
		
	
		});
	}
}
