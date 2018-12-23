package com.techelevator.model;

import java.util.List;

public interface WishListDAO {

	
public WishList getWishList(int wishListId);	


public int getWishListIdByUserName(String userName);


public List<Comic> getComicsInWishList(int wishListId);

public WishList getWishListByUserName(String userName);

public void deleteComicFromWishList(int comicId);

public String getFirstComic(int wishListId);

public void addComicToWishList(Long comicId, int wishListId);
	
public void moveComicFromWishListToCollection(Long comicId, int collectionId);


}
