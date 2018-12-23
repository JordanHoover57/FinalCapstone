package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface CollectionDAO {

	public void saveCollection(String userName, String collectionName, String accessType);
	
	public Collection getCollection(int collectionId);
	
	public List<Collection> getCollectionsByUserName(String username);
	
	public List<Comic> getComics (int collectionId);
	
	public String getFirstComic (int collectionId);
	
	public void deleteComic(long comicId, int collectionId);
	
	public void deleteCollection(int collectionId);
	
	public List<Collection> viewPublicCollections();
	
	public int getTotalCollections();
	
	public List<Integer> getNumOfBooksByCollectionId(String userName);
	
	public String getRoleByCollectionId(int collectionId);
	
	public void editComic(String issueName, String description, String condition, long comicId);
	
}
