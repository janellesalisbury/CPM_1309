package com.stocktrackr;

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

import com.stocktrackr.model.Stock;

public class StocksPullParser {
	
		private static final String LOGTAG = "STOCKSDB";
		
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
				XmlPullParser xpp = factory.newPullParser();
				
				InputStream stream = context.getResources().openRawResource(R.raw.stocks);
				xpp.setInput(stream, null);

				int eventType = xpp.getEventType();
				while (eventType != XmlPullParser.END_DOCUMENT) {
					if (eventType == XmlPullParser.START_TAG) {
						handleStartTag(xpp.getName());
					} else if (eventType == XmlPullParser.END_TAG) {
						currentTag = null;
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

			return stocks;
		}

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

		private void handleStartTag(String name) {
			if (name.equals("stock")) {
				currentStock = new Stock();
				stocks.add(currentStock);
			}
			else {
				currentTag = name;
			}
		}
	}



