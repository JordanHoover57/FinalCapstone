package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.techelevator.security.PasswordHasher;

@Component
public class JDBCCollectionDAO implements CollectionDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCCollectionDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveCollection(String userName, String collectionName, String accessType) {
		
		jdbcTemplate.update("INSERT INTO collection (user_name, collection_name, access_type) " + 
							"VALUES (?, ?, ?);", userName, collectionName, accessType);
	}	

	@Override
	public int getTotalCollections() {
		
		int collectionCount = 0;
		
		String sqlForCollectionCount = "SELECT COUNT(*) FROM collection;";
		
		SqlRowSet count = jdbcTemplate.queryForRowSet(sqlForCollectionCount);
		
		if(count.next()) {
			collectionCount = count.getInt("count");
		}
		
		return collectionCount;
	}
	

	@Override
	public Collection getCollection(int collectionId) {
		Collection c = new Collection();
		String sqlForCollectionRowSet = "SELECT * FROM collection " +
										"WHERE collection_id = ?";
		
		SqlRowSet collection = jdbcTemplate.queryForRowSet(sqlForCollectionRowSet, collectionId);
		
		while (collection.next()) {
			c = createCollection(collection);
		}
		
		return c;
	}	
	
	@Override
	public List<Collection> getCollectionsByUserName(String username) {
		List<Collection> userCollections = new ArrayList<Collection>();
		
		String sqlForCollectionSet = "SELECT * FROM collection " +
									"WHERE user_name = ?;";
		
		SqlRowSet collections = jdbcTemplate.queryForRowSet(sqlForCollectionSet, username);
		
		while (collections.next()) {
			userCollections.add(createCollection(collections));
		}
		
		return userCollections;
		
	}
	public void deleteCollection(int collectionId) {
		
		String sqlDeleteCollection = "DELETE FROM collection_comics " +
									"WHERE collection_id = ?;" +
									"DELETE FROM collection " +
									"WHERE collection_id = ?;";
		
		jdbcTemplate.update(sqlDeleteCollection, collectionId, collectionId);
	}
	
	public Collection createCollection(SqlRowSet result) {
		
		Collection c = new Collection();
		
		c.setAccessType(result.getString("access_type"));
		c.setCollectionId(result.getInt("collection_id"));
		c.setCollectionName(result.getString("collection_name"));
		c.setUsername(result.getString("user_name"));
		return c;
	}

	@Override
	public List<Comic> getComics(int collectionId) {
		List<Comic> collectionComics = new ArrayList<Comic>();
		
		String sqlForComicList = "SELECT * FROM comic " +
								"JOIN collection_comics ON comic.id = collection_comics.comic_id " +
								"WHERE collection_id = ?;";
		
		SqlRowSet comicIssues = jdbcTemplate.queryForRowSet(sqlForComicList, collectionId);
		
		while (comicIssues.next()) {
			Comic c = new Comic (comicIssues.getString("comic_vine_id"), comicIssues.getString("volume_id"), 
								comicIssues.getString("volume_name"), comicIssues.getString("issue_number"), 
								comicIssues.getString("image"),comicIssues.getString("thumb_image"), comicIssues.getInt("comic_collection_id"));
			
			c.setId(comicIssues.getLong("id"));
			c.setIssueName(comicIssues.getString("issue_name"));
			if (c.getIssueName() == null) {
				c.setIssueName("");
			}
			c.setCondition(comicIssues.getString("condition"));
			if (c.getCondition() == null) {
				c.setCondition("");
			}
			c.setIssueDate(LocalDate.now());
			c.setDescription(comicIssues.getString("description"));
			if (c.getDescription() == null) {
				c.setDescription("");
			}
			collectionComics.add(c);
		}
		
		return collectionComics;
	}
	
	@Override
	public String getFirstComic (int collectionId) {
		String firstComic = "";
		
		String sqlForFirstComic = "SELECT image FROM comic " +
								  "JOIN collection_comics ON comic.id = collection_comics.comic_id " +
								  "WHERE collection_id = ? " +
								  "LIMIT 1;";
		
		SqlRowSet comicImage = jdbcTemplate.queryForRowSet(sqlForFirstComic, collectionId);
		
		if (comicImage.next()) {
			firstComic = comicImage.getString("image");
		}
		
		return firstComic;
	}
	
	@Override
	public void deleteComic(long comicId, int collectionId) {
		
		String sqlDelete = "DELETE FROM collection_comics " +
							"WHERE collection_id = ? AND comic_id = ?;";
		
		jdbcTemplate.update(sqlDelete, collectionId, comicId);
	}
	
	@Override
	public List<Collection> viewPublicCollections() {
		
		List<Collection> publicCollections = new ArrayList<Collection>();
		
		String sqlForPublicCollections = "SELECT * FROM collection " +
										"WHERE access_type = 'Public';";

		SqlRowSet collections = jdbcTemplate.queryForRowSet(sqlForPublicCollections);

		while (collections.next()) {
			publicCollections.add(createCollection(collections));
		}

		return publicCollections;

	}
	
	@Override
	public List<Integer> getNumOfBooksByCollectionId(String userName) {
		
		List<Integer> numOfBooks = new ArrayList<Integer>();
		
		String sqlForNumOfBooks = "SELECT count(cc.comic_id) " + 
				"FROM collection_comics AS cc " + 
				"JOIN collection AS c ON cc.collection_id = c.collection_id " + 
				"WHERE c.user_name = ? " + 
				"GROUP BY cc.collection_id " + 
				"ORDER BY cc.collection_id ASC;";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlForNumOfBooks,userName);
		
		while (result.next()) {
			numOfBooks.add(result.getInt("count"));
		}
		return numOfBooks;
		
	}
	
	@Override
	public String getRoleByCollectionId(int collectionId) {
		
		String role = "";
		
		String sqlGetRole = "SELECT a.role FROM collection AS c " + 
				"JOIN app_user AS a ON c.user_name = a.user_name " + 
				"WHERE c.collection_id = ?;";
		
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetRole, collectionId);
		
		if(result.next()) {
			role = result.getString("role");
		}
		return role;
	}
	
	@Override
	public void editComic(String issueName, String description, String condition, long comicId) {
		
		String sqlEditComic = "UPDATE comic " +
							  "SET issue_name = ?, description = ?, condition = ? " +
							  "WHERE id = ?;";
		
		jdbcTemplate.update(sqlEditComic, issueName, description, condition, comicId);
		
	}
	
}
