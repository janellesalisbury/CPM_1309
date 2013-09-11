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

	private static final String COLUMN_TAG = "stock";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_SYMBOL = "symbol";
	private static final String COLUMN_LPRICE = "lastPrice";
	private static final String COLUMN_CHANGE = "change";
	private static final String COLUMN_VOLUME = "volume";
	

	public List<Stock> parseXML(Context context) {

		InputStream stream = context.getResources().openRawResource(R.raw.stocks);
		SAXBuilder builder = new SAXBuilder();
		List<Stock> stocks = new ArrayList<Stock>();

		try {

			Document document = (Document) builder.build(stream);
			org.jdom2.Element rootNode = document.getRootElement();
			List<org.jdom2.Element> list = rootNode.getChildren(COLUMN_TAG);

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
		return stocks;
	}

}

