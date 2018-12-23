package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

//needs to implement comicDao
@Component
public class JDBCComicDAO implements ComicDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCComicDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int getTotalComics() {
		
		int comicCount = 0;
		
		String sqlForComicCount = "SELECT COUNT(*) FROM comic;";
		
		SqlRowSet count = jdbcTemplate.queryForRowSet(sqlForComicCount);
		
		if(count.next()) {
			comicCount = count.getInt("count");
		}
		
		return comicCount;
	}
	
	
	@Override
	public List<Comic> getAllComics() {
		List<Comic> c = new ArrayList<Comic>();

		String sqlGetComics = "SELECT * FROM comic ; ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetComics);

		while (result.next()) {
			c.add(mapComicToRow(result));
		}

		return c;
	}

	public void addComicToCollection(Long comicId, Collection co) {
		String sqlAddComicToCollection = "INSERT INTO collection_comics (collection_id, comic_id) "
				+ "VALUES (?,?);";
		jdbcTemplate.update(sqlAddComicToCollection, co.getCollectionId(), comicId);
	}
	

	
	public List<Comic> getPopularComics() {
		List<Comic> mostPopular = new ArrayList<Comic>();
		
		String sqlGetMostPopular = "SELECT DISTINCT id, comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, comic_collection_id FROM comic " +
									"JOIN collection_comics ON comic.id = collection_comics.comic_id " +
									"GROUP BY comic.id " +
									"LIMIT 5;";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetMostPopular);
		
		while (result.next()) {
			mostPopular.add(mapComicToRow(result));
		}
		
		return mostPopular;
		
	}
	
	public List<Comic> getRecentComics() {
		List<Comic> mostRecent = new ArrayList<Comic>();
		
		String sqlGetMostRecent = "SELECT DISTINCT id, comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, comic_collection_id FROM comic " +
								  	"JOIN collection_comics ON comic.id = collection_comics.comic_id " +
								  	"ORDER BY id DESC " +
								  	"LIMIT 5;";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetMostRecent);
		
		while (result.next()) {
			mostRecent.add(mapComicToRow(result));
		}
		
		return mostRecent;
	}
	
	public Long saveComic(Comic c) {

		String sqlCreateComic = "INSERT INTO comic (comic_vine_id, volume_id, volume_name, issue_number, image, thumb_image, comic_collection_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?) " + "RETURNING id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCreateComic, c.getComicVineId(), c.getVolumeId(), c.getVolumeName(), c.getIssueNumber(),
				c.getImage(), c.getThumbImage(), c.getComicCollectionId());
		
		Long newComicId = null;
		if(results.next()) {
			newComicId = results.getLong("id");
		} else {
			throw new RuntimeException("Something went wrong while getting the ID for the new comic");
		}
		return newComicId;
	}

	public Comic mapComicToRow(SqlRowSet result) {
		Comic c = new Comic(null, null, null, null, null, null, 0);

		c.setId(result.getLong("id"));
		c.setComicVineId(result.getString("comic_vine_id"));
		c.setVolumeId(result.getString("volume_id"));
		c.setVolumeName(result.getString("volume_name"));
		c.setIssueNumber(result.getString("issue_number"));
		c.setImage(result.getString("image"));
		c.setThumbImage(result.getString("thumb_image"));
		c.setComicCollectionId(result.getInt("comic_collection_id"));
		return c;
	}
	
	

}
