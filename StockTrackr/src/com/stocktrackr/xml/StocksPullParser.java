package com.stocktrackr.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

import com.stocktrackr.R;
import com.stocktrackr.model.Stock;

public class StocksPullParser {
	
		private static final String LOGTAG = "STOCKSDB";
		//private constants of each value in the xml file. They are used to identify what value we are looking for and wanting to return
		private static final String COLUMN_ID = "StockID";
		private static final String COLUMN_NAME = "name";
		private static final String COLUMN_SYMBOL = "symbol";
		private static final String COLUMN_LPRICE = "lastPrice";
		private static final String COLUMN_CHANGE = "change";
	    private static final String COLUMN_VOLUME = "volume";
		
		private Stock currentStock  = null;
		private String currentTag = null;
		List<Stock> stocks = new ArrayList<Stock>();

		public List<Stock> parseXML(Context context) {

			try {
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				//create an instance of the PullParser Class so that the application may read the xml file included in the res/raw folder of the project
				XmlPullParser xpp = factory.newPullParser();
				//InputStream opens up the stocks.xml file located in the res/raw folder of the project.
				InputStream stream = context.getResources().openRawResource(R.raw.stocks);
				xpp.setInput(stream, null);
				//Here we will trigger the opening and reading of the file using the getEventType method. Using a while loop 
				//we will read through the document to the end. using the next() method, the document will continue to be read until the end is reached.
				int eventType = xpp.getEventType();
				while (eventType != XmlPullParser.END_DOCUMENT) {
					if (eventType == XmlPullParser.START_TAG) {
						handleStartTag(xpp.getName());
						//occurs when no tag is being read in the document
					} else if (eventType == XmlPullParser.END_TAG) {
						currentTag = null;
						//if a text event is being read, then we need to do something with it (handleText method)
					} else if (eventType == XmlPullParser.TEXT) {
						handleText(xpp.getText());
					}
					eventType = xpp.next();
				}

			} catch (NotFoundException e) {
				Log.d(LOGTAG, e.getMessage());
			} catch (XmlPullParserException e) {
				Log.d(LOGTAG, e.getMessage());
			} catch (IOException e) {
				Log.d(LOGTAG, e.getMessage());
			}
			//returns the objects read as a list
			return stocks;
		}
		//Each conditional statement in this method is looking for a particular value that has been created within the xml document.
		//An integer or double will be parsed first, and text will be taken and added to the "currentStock" object in the proper value column. For each stock
		//the method will be called 6 times(once for each value we are looking to find) to read each value and determine where it should be added. A null tag will be created 
		//if we read any text in the document that we have not created a constant for (such as COLUMN_NAME)
		
		private void handleText(String text) {
			String xmlText = text;
			if (currentStock != null && currentTag != null) {
				if (currentTag.equals(COLUMN_ID)) {
					Integer id = Integer.parseInt(xmlText);
					currentStock.setId(id);
				} 
				else if (currentTag.equals(COLUMN_NAME)) {
					currentStock.setName(xmlText);
				}
				else if (currentTag.equals(COLUMN_SYMBOL)) {
					currentStock.setSymbol(xmlText);
				}
				else if (currentTag.equals(COLUMN_LPRICE)) {
					double lastPrice = Double.parseDouble(xmlText);
					currentStock.setLastPrice(lastPrice);
				}
				else if (currentTag.equals(COLUMN_CHANGE)) {
					double change = Double.parseDouble(xmlText);
					currentStock.setChange(change);
	            }
	            else if (currentTag.equals(COLUMN_VOLUME)) {
	                    Integer volume = Integer.parseInt(xmlText);
	                    currentStock.setVolume(volume);
				}
			}
		}

		//handle the creation of a new instance of the class Stock, if the name of the tag is "stock", then save it to the "currentStock" variable 
		//which is declared above for the Stock class itself.
		private void handleStartTag(String name) {
			if (name.equals("stock")) {
				currentStock = new Stock();
				//add the stock to the list if the tag matches "stock"
				stocks.add(currentStock);
			}
			else {
				//if no match occurs for "name" save the current tag 
				currentTag = name;
			}
		}
	}



