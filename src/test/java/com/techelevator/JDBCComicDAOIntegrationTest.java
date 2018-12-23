package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
import com.techelevator.model.JDBCComicDAO;

public class JDBCComicDAOIntegrationTest {

	private static SingleConnectionDataSource dataSource;

	private JDBCComicDAO dao;
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	// Test data for a comic
	private static final String TEST_COMIC_VINE_ID = "999999";
	private static final String TEST_VOLUME_ID = "999";
	private static final String TEST_VOLUME_NAME = "Test Volume Name";
	private static final String TEST_ISSUE_NUMBER = "999";
	private static final String TEST_IMAGE = "Http//www.url.com/test/test";
	private static final String TEST_THUMB_IMAGE = "Http//www.url.com/test/test";
	
	private static final Long TEST_COMIC_ID = 999999999L;
	private static final int TEST_COLLECTION_ID = 6;
	private static final Integer TEST_COLLECTION_ID_FOR_COMIC = 6;
	
	
	private static final String TEST_USER_NAME = "Test User Name";
	private static final String TEST_COLLECTION_NAME = "Test Colleciton";
	private static final String TEST_ACCESS_TYPE = "Premium";

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/capstone");
		// dataSource.setUsername("capstone_appuser");
		// dataSource.setPassword("capstone_appuser1");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/*
		 * The following line disables autocommit for connections returned by this
		 * DataSource. This allows us to rollback any changes after each test
		 */
		dataSource.setAutoCommit(false);
	}

	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dao = new JDBCComicDAO(dataSource);

	}

	/*
	 * After all tests have finished running, this method will close the DataSource
	 */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/*
	 * After each test, we rollback any changes that were made to the database so
	 * that everything is clean for the next test
	 */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/*
	 * This method provides access to the DataSource for subclasses so that they can
	 * instantiate a DAO for testing
	 */
	// protected DataSource getDataSource() {
	// return dataSource;
	// }

	// ****************************************************************
	// ************************TESTS***********************************
	// ****************************************************************
	
	@Test
	public void save_comic_test() {
		Comic c = new Comic(TEST_COMIC_VINE_ID, TEST_VOLUME_ID, TEST_VOLUME_NAME, TEST_ISSUE_NUMBER, TEST_IMAGE, TEST_THUMB_IMAGE, TEST_COLLECTION_ID_FOR_COMIC);

		dao.saveComic(c);

		int toCheck = 0;
		String sqlSelectAllComics = "SELECT * FROM comic";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllComics);
		while (results.next()) {
			toCheck++;
		}

		assertEquals(toCheck, 7);
	}

	@Test
	public void get_all_comics_test() {
		List<Comic> initialComicList = new ArrayList<Comic>();
		List<Comic> updatedComicList = new ArrayList<Comic>();

		Comic c = new Comic(TEST_COMIC_VINE_ID, TEST_VOLUME_ID, TEST_VOLUME_NAME, TEST_ISSUE_NUMBER, TEST_IMAGE,TEST_THUMB_IMAGE, TEST_COLLECTION_ID_FOR_COMIC);

		initialComicList = dao.getAllComics();
		int initialNumOfComics = initialComicList.size();

		dao.saveComic(c);

		updatedComicList = dao.getAllComics();
		int updatedNumOfComics = updatedComicList.size();

		assertEquals(initialNumOfComics + 1, updatedNumOfComics);

	}
	
	@Test
	public void add_comic_to_collection_test() {
		//Select count from collection_comics and set to variable
		
		String sqlCreateCollectionComic = "SELECT count(collection_id) AS num FROM collection_comics;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCreateCollectionComic);
		int initialNumOfComicCollection = 0;
		
		if(results.next()) {
			initialNumOfComicCollection = results.getInt("num");
		}
		
		Comic c = new Comic(TEST_COMIC_VINE_ID, TEST_VOLUME_ID, TEST_VOLUME_NAME, TEST_ISSUE_NUMBER, TEST_IMAGE, TEST_THUMB_IMAGE, TEST_COLLECTION_ID_FOR_COMIC);
		Collection col = new Collection();
			col.setCollectionId(TEST_COLLECTION_ID);
			col.setUsername(TEST_USER_NAME);
			col.setCollectionName(TEST_COLLECTION_NAME);
			col.setAccessType(TEST_ACCESS_TYPE);
			
		Long comicId = dao.saveComic(c);
	
		dao.addComicToCollection(comicId, col);
		
		String sqlCreateSecondCollectionComic = "SELECT count(collection_id) AS num FROM collection_comics;";
		SqlRowSet resultsTwo = jdbcTemplate.queryForRowSet(sqlCreateSecondCollectionComic);
		int numOfComicCollection = 0;
		
		if(resultsTwo.next()) {
			numOfComicCollection = resultsTwo.getInt("num");
		}
		
		assertEquals(initialNumOfComicCollection + 1, numOfComicCollection);
		
	}
	

	

}
