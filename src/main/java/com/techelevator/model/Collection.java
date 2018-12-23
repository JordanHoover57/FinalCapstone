package com.techelevator.model;

public class Collection {

	private Integer collectionId;
	private String username;
	private String collectionName;
	private String accessType;
	private String comicImage;
	
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	
	public String getAccessType() {
		return accessType;
	}
	
	public String getCollectionName() {
		return collectionName;
	}
	
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	
	public String getComicImage() {
		return comicImage;
	}
	public void setComicImage(String comicImage) {
		this.comicImage = comicImage;
	}
	
}
