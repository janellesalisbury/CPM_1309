package com.stocktrackr.model;

public class Stock {
	
	private long id;
	private String name;
	private String symbol;
	private double lastPrice;
	private double change;
	private int volume;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
	
	public double getLastPrice(){
		return lastPrice;
	}
	
	public void setLastPrice(double lastPrice){
		this.lastPrice = lastPrice;
	}
	
	public double getChange(){
		return change;
	}
	
	public void setChange(double change){
		this.change = change;
	}
	
	public int getVolume(){
		return volume;
	}
	
	public void setVolume(int volume){
		this.volume = volume;
	}
	
	

}
