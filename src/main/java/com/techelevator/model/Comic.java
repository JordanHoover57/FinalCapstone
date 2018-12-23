package com.techelevator.model;

import java.time.LocalDate;

public class Comic {
	
	private Long id;
	private String comicVineId;
	private String volumeId;
	private String volumeName;
	private String issueNumber;
	private String image;
	private String thumbImage;
	private int comicCollectionId;



	private String issueName;     //Nullable: Set by user
	private LocalDate issueDate; //Nullable: Set by user
	private String description; //Nullable: Set by user
	private String condition;//Nullable: Set by user
	
	public Comic(String comicVineId, String volumeId, String volumeName, String issueNumber, String image, String thumbImage, int collectionId) {
		this.volumeName = volumeName;
		this.issueNumber = issueNumber;
		this.image = image;
		this.thumbImage = thumbImage;
		this.comicVineId = comicVineId;
		this.volumeId = volumeId;
		this.comicCollectionId = collectionId;
	}
	
	public int getComicCollectionId() {
		return comicCollectionId;
	}
	public void setComicCollectionId(int collectionId) {
		this.comicCollectionId = collectionId;
	}

	public String getThumbImage() {
		return thumbImage;
	}
	public void setThumbImage(String thumbImage) {
		this.thumbImage = thumbImage;
	}
	public Comic() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComicVineId() {
		return comicVineId;
	}

	public void setComicVineId(String comicVineId) {
		this.comicVineId = comicVineId;
	}

	public String getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(String volumeId) {
		this.volumeId = volumeId;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
//	@Override
//	public String toString() {
//		return this.comicCollectionId.toString();
//	}
//	

}