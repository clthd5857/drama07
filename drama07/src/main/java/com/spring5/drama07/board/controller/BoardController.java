package com.spring5.drama07.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring5.drama07.board.vo.BoardVO;
import com.spring5.drama07.board.vo.CommentVO;

public interface BoardController {

	public ModelAndView listArticles(@ModelAttribute BoardVO boardVO) throws Exception;
	
	//관리자
	public ModelAndView listAdminArticles(@ModelAttribute BoardVO boardVO) throws Exception;
	public ModelAndView listFlagArticles(@ModelAttribute BoardVO boardVO) throws Exception;
	
	
	public ModelAndView viewArticle(@RequestParam int articleNO,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView addArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView modArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView removeArticle(@RequestParam int ArticleNO) throws Exception;
	public ModelAndView restoreArticle(@RequestParam int articleNO) throws Exception;
	public ModelAndView delArticle(@RequestParam int articleNO, @RequestParam int nameflag) throws Exception;
	
	//덧글
	
		//관리자
	public ModelAndView listAdminComments(@ModelAttribute CommentVO commentVO) throws Exception;
	public ModelAndView listFlagComments(@ModelAttribute CommentVO commentVO) throws Exception;
	
	
	public ModelAndView addComment(@ModelAttribute CommentVO commentVO) throws Exception;
	
	public ModelAndView modComment(@ModelAttribute CommentVO commentVO) throws Exception;
	
	public ModelAndView removeComment(@RequestParam int commentArticleNO, @RequestParam int articleNO) throws Exception;
	public ModelAndView restoreComment(@RequestParam int commentArticleNO) throws Exception;
	public ModelAndView delComment(@RequestParam int commentArticleNO, @RequestParam int nameflag) throws Exception;
	
}
