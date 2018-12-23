package com.techelevator.model;

import java.util.List;

public interface ComicDAO {
	
	public List<Comic> getPopularComics();
	
	public List<Comic> getRecentComics();
	
	public Long saveComic(Comic c);
	
	public List<Comic> getAllComics();
	
	public void addComicToCollection(Long comicId, Collection co);
	
	public int getTotalComics();
	
}
