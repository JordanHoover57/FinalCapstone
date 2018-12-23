package com.techelevator.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Collection;
import com.techelevator.model.CollectionDAO;
import com.techelevator.model.Comic;
import com.techelevator.model.ComicDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class CollectionsController {
	
	private CollectionDAO collectionDao;

	@Autowired
	public CollectionsController(CollectionDAO collectionDao) {
		this.collectionDao = collectionDao;
	}
	
	@RequestMapping(value="/collections", params = { "destination", "CSRF_TOKEN", "collectionId" }, method=RequestMethod.POST)
	public String deleteCollection (HttpSession session,  @RequestParam int collectionId) {
		
		collectionDao.deleteCollection(collectionId);
		
		return "redirect:/collections";
	}
	
	
	@RequestMapping(value="/collections", params = { "destination", "CSRF_TOKEN", "collectionName", "accessType" }, method=RequestMethod.POST)
	public String addCollection(HttpSession session, @RequestParam String collectionName, @RequestParam String accessType, RedirectAttributes redirAttr) {
		User user = (User)session.getAttribute("currentUser");
		String userName = user.getUserName();
		
		if (collectionDao.getCollectionsByUserName(userName).size() >= 25 && user.getRole().equals("Standard")) {
			redirAttr.addFlashAttribute("route", "collection");
			return "redirect:/upgrade";
		}
		
		
		
		Collection collection = new Collection();
		collection.setUsername(userName);
		collection.setCollectionName(collectionName);
		collection.setAccessType(accessType);
		collectionDao.saveCollection(collection.getUsername(), collection.getCollectionName(), collection.getAccessType());
		
		
		
		return "redirect:/collections";
	}
	
	
	@RequestMapping(path="/collections", method=RequestMethod.GET) 
		public String displayUserCollections(HttpSession session, ModelMap model) {
		
		if (session.getAttribute("currentUser") == null) {
			return "redirect:/error";
		}
		
		User user = (User)session.getAttribute("currentUser");
		String userName = user.getUserName();
		List<Collection> userCollections = new ArrayList<Collection>();
		userCollections = collectionDao.getCollectionsByUserName(userName);
		for(Collection c: userCollections) {
			c.setComicImage(collectionDao.getFirstComic(c.getCollectionId()));
		}
		List<Integer>numOfBooks = collectionDao.getNumOfBooksByCollectionId(userName);
		
		model.addAttribute("userCollections", userCollections);
		model.addAttribute("numOfBooks", numOfBooks);
			return "collections";
		}
	
	
	@RequestMapping(path="/collectionDetail", method=RequestMethod.GET)
		public String displayCollectionDetail(@RequestParam int collectionId, ModelMap model) {
		List<Comic> collectionComics = collectionDao.getComics(collectionId);
		
		model.addAttribute("collectionId", collectionId);
		model.addAttribute("collectionComics", collectionComics);
		
		return "collectionDetail";
		}
	
	@RequestMapping(value="/collectionDetail", params = { "destination", "CSRF_TOKEN", "collectionId", "comicId" }, method=RequestMethod.POST)
		public String deleteComic(@RequestParam long comicId, @RequestParam int collectionId) {
		
		collectionDao.deleteComic(comicId, collectionId);
		
		return "redirect:/collectionDetail?collectionId=" + collectionId;
		
		}
	
	@RequestMapping(value="/collectionDetail", params = {"collectionId", "comicId", "issueName",
														"description", "condition" }, method=RequestMethod.POST)
	public String editComic(@RequestParam String issueName, @RequestParam String description, 
							@RequestParam String condition, @RequestParam long comicId, @RequestParam int collectionId) {
	
	collectionDao.editComic(issueName, description, condition, comicId);
	
	return "redirect:/collectionDetail?collectionId=" + collectionId;
	
	}
	
	@RequestMapping(path="publicCollections", method=RequestMethod.GET)
		public String showPublicCollections(ModelMap model) {
		
		List<Collection> publicCollections = collectionDao.viewPublicCollections();
		
		for(Collection c: publicCollections) {
			c.setComicImage(collectionDao.getFirstComic(c.getCollectionId()));
		}
		
		model.addAttribute("publicCollections", publicCollections);
		
		return "publicCollections";
	}
	
	}
	
	
	
	

