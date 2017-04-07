package br.com.emersonluiz.service;

import java.util.UUID;

import javax.inject.Named;

@Named
public class CacheService {
	
	private String information;
	
	public CacheService() {
		this.information = "Hello World!!!";
	}

	public String getInformation() {
		return this.information + UUID.randomUUID().toString();
	}

	public void setInformation(String information) {
		this.information = information;		
	}

}
