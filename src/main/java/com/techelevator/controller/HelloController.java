package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.CollectionDAO;
import com.techelevator.model.Comic;
import com.techelevator.model.ComicDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class HelloController {

	private ComicDAO comicDAO;
	private UserDAO userDAO;
	private CollectionDAO collectionDAO;
	
	@Autowired
	public HelloController (ComicDAO comicDAO, UserDAO userDAO, CollectionDAO collectionDAO) {
		this.comicDAO = comicDAO;
		this.userDAO = userDAO;
		this.collectionDAO = collectionDAO;
	}
	
	@RequestMapping(path= {"/", "/homepage"}, method=RequestMethod.GET)
	public String displayHomePage(ModelMap model) {
		
		int totalCollections = collectionDAO.getTotalCollections();
		int totalComics = comicDAO.getTotalComics();
		
		List<Comic> mostPopular = comicDAO.getPopularComics();
		List<Comic> mostRecent = comicDAO.getRecentComics();
		
		model.addAttribute("totalCollections", totalCollections);
		model.addAttribute("totalComics", totalComics);
		model.addAttribute("mostPopular", mostPopular);
		model.addAttribute("mostRecent", mostRecent);
		
		return "homePage";
	}
	
	@RequestMapping(path="/upgrade", method=RequestMethod.GET)
	public String displayUpgradePage(ModelMap model, @ModelAttribute("route") String route) {
		String routing = "routing";
		if (route.equals("collection")) {
			routing = "collection";
		} else if (route.equals("comic")) {
			routing = "comic";
		} else {
			routing = "upgrade";
		}
		
		model.addAttribute("routing", routing);
		
		return "upgrade";
	}
	
	@RequestMapping(path="/upgrade", method=RequestMethod.POST)
	public String redirectToHomePage(@RequestParam String userName, HttpSession session) {
		
		userDAO.updateUserRole(userName);
		
		session.setAttribute("currentUser", userDAO.getUserByUserName(userName));
		

		 return "redirect:/homepage";
	}
	
	
	
	
}
