package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component
public class JDBCWishListDAO implements WishListDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCWishListDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public WishList getWishList(int wishListId) {
		WishList wishList = new WishList();
		
		String wishListStuff = "SELECT * FROM wishlist "
				+ "WHERE wishlist_id = ?";
		
		SqlRowSet wishLists = jdbcTemplate.queryForRowSet(wishListStuff, wishListId);
		
		while (wishLists.next()) {
		wishList = mapWishListToRow(wishLists);
		}

		return wishList;
		
	}
	
	
public WishList mapWishListToRow(SqlRowSet result) {
		
		WishList w = new WishList();
		
		w.setWishListId(result.getInt("wishlist_id"));
		w.setUsername(result.getString("user_name"));
		w.setWishListName(result.getString("wishlist_name"));
		
		return w;
	}

@Override
public int getWishListIdByUserName(String userName) {
	int wishListId = 0;
	
	String wishListStuff = "SELECT wishlist_id FROM wishlist "
			+ "WHERE user_name = ?";
	
	SqlRowSet wishLists = jdbcTemplate.queryForRowSet(wishListStuff, userName);
	
	while (wishLists.next()) {
	wishListId = wishLists.getInt("wishlist_id");
	}

	return wishListId;

}

@Override
public List<Comic> getComicsInWishList(int wishListId) {

	List<Comic> wishListComics = new ArrayList<Comic>();
	
	String sqlForComicList =  "SELECT * FROM comic " + 
			"JOIN wishlist_comics ON comic.id = wishlist_comics.comic_id " + 
			"WHERE wishlist_id = ?";
	
	SqlRowSet comicIssues = jdbcTemplate.queryForRowSet(sqlForComicList, wishListId);
	
	while (comicIssues.next()) {
		Comic c = new Comic (comicIssues.getString("comic_vine_id"), comicIssues.getString("volume_id"), 
							comicIssues.getString("volume_name"), comicIssues.getString("issue_number"), 
							comicIssues.getString("image"),comicIssues.getString("thumb_image"), comicIssues.getInt("comic_collection_id"));
		
		c.setId(comicIssues.getLong("id"));
		
		wishListComics.add(c);
	}
	
	return wishListComics;
}

@Override
public String getFirstComic (int wishListId) {
	String firstComic = "";
	
	String sqlForFirstComic = "SELECT image FROM comic " + 
			"JOIN wishlist_comics ON comic.id = wishlist_comics.comic_id " + 
			"WHERE wishlist_id = ? " + 							  
			"LIMIT 1;";
	
	SqlRowSet comicImage = jdbcTemplate.queryForRowSet(sqlForFirstComic, wishListId);
	
	if (comicImage.next()) {
		firstComic = comicImage.getString("image");
	}
	
	return firstComic;
}
@Override
public void addComicToWishList(Long comicId, int wishListId) {
	String sqlAddComicToWishList = "INSERT INTO wishlist_comics (comic_id, wishlist_id) "
			+ "VALUES (?, ?);";
	jdbcTemplate.update(sqlAddComicToWishList, comicId, wishListId);
	
}

@Override
public WishList getWishListByUserName(String userName) {
WishList wishList = new WishList();
	
	String wishListStuff = "SELECT * FROM wishlist "
			+ "WHERE user_name = ?";
	
	SqlRowSet wishLists = jdbcTemplate.queryForRowSet(wishListStuff, userName);
	
	while (wishLists.next()) {
	wishList = mapWishListToRow(wishLists);
	}

	return wishList;

}
@Override
public void deleteComicFromWishList(int comicId) {
	
	String sqlDeleteCollection = "DELETE FROM wishlist_comics " + 
			"WHERE comic_id = ? ";
	
	jdbcTemplate.update(sqlDeleteCollection, comicId);
}

@Override
public void moveComicFromWishListToCollection(Long comicId, int collectionId) { 
	
	
	String sqlDeleteAndInsertFromWishList = "DELETE FROM wishlist_comics " 
									+ "WHERE comic_id = ?; " 
									+ "INSERT INTO collection_comics (collection_id, comic_id) "
									+ "VALUES (?, ?) ";
	jdbcTemplate.update(sqlDeleteAndInsertFromWishList, comicId, collectionId, comicId);			
}


}



	

