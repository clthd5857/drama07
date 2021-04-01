package com.spring5.drama07.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring5.drama07.board.dao.BoardDAO;
import com.spring5.drama07.board.vo.BoardVO;
import com.spring5.drama07.board.vo.CommentVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;

	public BoardServiceImpl() {
	}
	
	@Override
	public List<BoardVO> articlesList(BoardVO boardVO) throws DataAccessException{
		return boardDAO.selectListArticles(boardVO);
	}

	@Override
	public List<BoardVO> flagArticlesList(BoardVO boardVO) throws DataAccessException {
		return boardDAO.selectListFlagArticles(boardVO);
	}

	@Override
	public BoardVO articleView(int articleNO) throws DataAccessException {
		return boardDAO.selectViewArticle(articleNO);
	}

	@Override
	public int articleAdd(BoardVO boardVO) throws DataAccessException {
		
		return boardDAO.insertAddArticle(boardVO);
	}

	@Override
	public int articleMod(BoardVO boardVO) throws DataAccessException {
		return boardDAO.updateModArticle(boardVO);
	}

	
	@Override
	public int articleRestore(int articleNO) throws DataAccessException {
		return boardDAO.updateRestoreArticle(articleNO);
	}

	@Override
	public int articleRemove(int articleNO) throws DataAccessException {
		return boardDAO.updateRemoveArticle(articleNO);
	}

	@Override
	public int articleDel(int articleNO) throws DataAccessException {
		return boardDAO.deleteArticle(articleNO);
	}


	//덧글
	
	@Override
	public List<CommentVO> commentsList(int articleNO) throws DataAccessException {
		return boardDAO.selectListComments(articleNO);
	}
	
	//관리자
	@Override
	public List<CommentVO> adminCommentsList(CommentVO commentVO) throws DataAccessException {
		return boardDAO.selectListAdminComments(commentVO);
	}

	@Override
	public List<CommentVO> flagCommentsList(CommentVO commentVO) throws DataAccessException {
		return boardDAO.selectListFlagComments(commentVO);
	}

	@Override
	public int commentAdd(CommentVO commentVO) throws DataAccessException {
		return boardDAO.insertAddComment(commentVO);
	}



	@Override
	public int commentMod(CommentVO commentVO) throws DataAccessException {
		return boardDAO.updateModComment(commentVO);
	}

	@Override
	public int commentRemove(int commentArticleNO) throws DataAccessException {
		return boardDAO.updateRemoveComment(commentArticleNO);
	}

	
	@Override
	public int commentRestore(int commentArticleNO) throws DataAccessException {
		return boardDAO.updateRestoreComment(commentArticleNO);
	}

	@Override
	public int commentDel(int commentArticleNO) throws DataAccessException {
		return boardDAO.deleteComment(commentArticleNO);
	}
	
	
	
}
