package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Collection;
import com.techelevator.model.CollectionDAO;
import com.techelevator.model.Comic;
import com.techelevator.model.ComicDAO;
import com.techelevator.model.User;


@Controller
public class SearchController {
	private CollectionDAO collectionDAO;
	private ComicDAO comicDAO;
	
	@Autowired
	public SearchController(CollectionDAO collectionDao, ComicDAO comicDAO) {
		this.collectionDAO = collectionDao;
		this.comicDAO = comicDAO;
	}
	
	@RequestMapping(path="/search", method=RequestMethod.GET) 
		public String displaySearchPage(HttpSession session, ModelMap model) {
		if (session.getAttribute("currentUser") == null) {
			return "redirect:/error";
		}
			User user = (User)session.getAttribute("currentUser");
			String currentUser = user.getUserName();			
			//åmodel.addAttribute("currentUser", currentUser);
			List<Collection> userCollection = collectionDAO.getCollectionsByUserName(currentUser);
			model.addAttribute("userCollections", userCollection);
				
			return "search";
	}
	
	@GetMapping(path="/error")
	public String showErrorPage() {
		return "error";
	}
 
	@RequestMapping(path="/search", method=RequestMethod.POST)
	@ResponseBody
		public HttpStatus addComic(HttpSession session, @RequestBody Comic comic, RedirectAttributes redirAttr) {
		int comicCollectionId = comic.getComicCollectionId();
		Long comicId = comicDAO.saveComic(comic);
		Collection collection = collectionDAO.getCollection(comicCollectionId);
		comicDAO.addComicToCollection(comicId, collection);
		return HttpStatus.OK;

	}
	
}
