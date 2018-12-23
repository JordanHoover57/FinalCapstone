package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Collection;
import com.techelevator.model.CollectionDAO;
import com.techelevator.model.Comic;
import com.techelevator.model.ComicDAO;
import com.techelevator.model.User;
import com.techelevator.model.WishList;
import com.techelevator.model.WishListDAO;

@Controller
public class WishListController {

	private WishListDAO wishListDAO;
	private ComicDAO comicDAO;
	private CollectionDAO collectionDAO;

	@Autowired
	public WishListController(WishListDAO wishListDAO, ComicDAO comicDAO, CollectionDAO collectionDAO) {
		this.wishListDAO = wishListDAO;
		this.comicDAO = comicDAO;
		this.collectionDAO = collectionDAO;
	}

	@GetMapping(path = "/wishList")
	public String displayWishListPage(HttpSession session, ModelMap model) {
		User user = (User) session.getAttribute("currentUser");
		String userName = user.getUserName();
		WishList WishList = wishListDAO.getWishListByUserName(userName);
		String comicImage = WishList.getComicImage();
		String comicDAOImage = wishListDAO.getFirstComic(WishList.getWishListId());

		model.addAttribute("comicImage", comicDAOImage);
		model.addAttribute("wishList", WishList);

		return "wishList";
	}

	@RequestMapping(path = "/wishListDetail", method = RequestMethod.GET)
	public String displayWishListDetail(HttpSession session, @RequestParam int wishListId, ModelMap model) {
		User user = (User) session.getAttribute("currentUser");
		String userName = user.getUserName();
		List<Comic> wishListComics = wishListDAO.getComicsInWishList(wishListId);
		List<Collection> userCollection = collectionDAO.getCollectionsByUserName(userName);

		model.addAttribute("userCollections", userCollection);
		model.addAttribute("wishListId", wishListId);
		model.addAttribute("wishListComics", wishListComics);

		return "wishListDetail";
	}

	@RequestMapping(path = "/wishList", method = RequestMethod.POST)
	@ResponseBody
	public String addComicToWishList(@RequestBody Comic comic, RedirectAttributes redirAttr, HttpSession session) {
		// int comicCollectionId = comic.getComicCollectionId();
		User user = (User) session.getAttribute("currentUser");
		String userName = user.getUserName();
		int wishListId = wishListDAO.getWishListIdByUserName(userName);

		Long comicId = comicDAO.saveComic(comic);
		// System.out.println(comicId);
		// int comicCollectionId = comic.getComicCollectionId();
		// System.out.println(comicCollectionId);
		// JK IT DOESN'T LOL -- Adam
		wishListDAO.addComicToWishList(comicId, wishListId);
		;
		// return HttpStatus.OK;
		return "redirect:/search";
	}

	@RequestMapping(path = "/wishListDetailDelete", method = RequestMethod.POST)
	public String deleteComicFromWishList(@RequestParam int comicId, @RequestParam int wishListId) {
		wishListDAO.deleteComicFromWishList(comicId);

		return "redirect:/wishListDetail?wishListId=" + wishListId;
	}

	@RequestMapping(path = "/wishListDetail", method = RequestMethod.POST)
	@ResponseBody

	public String moveComicFromWishListToCollection(@RequestBody Comic comic, HttpSession session,
			RedirectAttributes redirAttr) {
		User user = (User) session.getAttribute("currentUser");
		String userName = user.getUserName();
		int wishListId = wishListDAO.getWishListIdByUserName(userName);
		Long comicId = comic.getId();
		int collectionId = comic.getComicCollectionId();
		wishListDAO.moveComicFromWishListToCollection(comicId, collectionId);

		return "redirect:/wishListDetail?wishListId=" + wishListId;

	}

}
