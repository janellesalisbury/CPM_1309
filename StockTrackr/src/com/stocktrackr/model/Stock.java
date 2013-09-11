package com.stocktrackr.model;

public class Stock {
	
	private long id;
	private String name;
	private String symbol;
	private float lastPrice;
	private float change;
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
	
	public float getLastPrice(){
		return lastPrice;
	}
	
	public void setLastPrice(float lastPrice){
		this.lastPrice = lastPrice;
	}
	
	public float getChange(){
		return change;
	}
	
	public void setChange(float change){
		this.change = change;
	}
	
	public int getVolume(){
		return volume;
	}
	
	public void setVolume(int volume){
		this.volume = volume;
	}
	
	

}
