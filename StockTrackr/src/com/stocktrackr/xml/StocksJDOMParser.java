package com.stocktrackr.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import android.content.Context;
import android.util.Log;

import com.stocktrackr.R;
import com.stocktrackr.model.Stock;

public class StocksJDOMParser {
	
	private static final String LOGTAG = "STOCKSDB";
	//create constants for each value, again used to identify what we are looking for and want to return
	private static final String COLUMN_TAG = "stock";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_SYMBOL = "symbol";
	private static final String COLUMN_LPRICE = "lastPrice";
	private static final String COLUMN_CHANGE = "change";
	private static final String COLUMN_VOLUME = "volume";
	

	public List<Stock> parseXML(Context context) {
		//Using an input stream use the current activity to open the stocks.xml file
		InputStream stream = context.getResources().openRawResource(R.raw.stocks);
		//Instead of using a DOM builder, we are using a SAX builder which is a 
		//simpler approach using an api for xml. Streaming is used for the xml document, similar to the parser class.
		SAXBuilder builder = new SAXBuilder();
		//create the list of objects
		List<Stock> stocks = new ArrayList<Stock>();

		try {
			//parse using a document object, returning an element and create a list of the instance of the class and pass the tags in
			Document document = (Document) builder.build(stream);
			org.jdom2.Element rootNode = document.getRootElement();
			List<org.jdom2.Element> list = rootNode.getChildren(COLUMN_TAG);
			//this for statement will loop through the list and parse the values for integer/doubles and take the text values
			for (Element node : list) {
				Stock stock = new Stock();
				stock.setName(node.getChildText(COLUMN_NAME));
				stock.setSymbol(node.getChildText(COLUMN_SYMBOL));
				stock.setLastPrice(Double.parseDouble(node.getChildText(COLUMN_LPRICE)));
				stock.setChange(Double.parseDouble(node.getChildText(COLUMN_CHANGE)));
                stock.setVolume(Integer.parseInt(node.getChildText(COLUMN_VOLUME)));
				stocks.add(stock);
			}

		} catch (IOException e) {
			Log.i(LOGTAG, e.getMessage());
		} catch (JDOMException e) {
			Log.i(LOGTAG, e.getMessage());
		}
		//return the objects here
		return stocks;
	}

}

