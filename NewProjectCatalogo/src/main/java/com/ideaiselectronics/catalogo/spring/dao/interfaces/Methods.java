package com.ideaiselectronics.catalogo.spring.dao.interfaces;

import javax.ws.rs.core.GenericType;

public interface Methods {
	
	public <T> Object get(String url, GenericType<T> type);
	
	public boolean post(String url, String json);
	
	public void put(String url, String json);
	
	public void options();

}
