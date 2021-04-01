package com.spring5.drama07.board.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring5.drama07.board.vo.BoardVO;
import com.spring5.drama07.board.vo.CommentVO;

public interface BoardDAO {

	public List<BoardVO> selectListArticles(BoardVO boardVO) throws DataAccessException;
	public List<BoardVO> selectListFlagArticles(BoardVO boardVO) throws DataAccessException;
	
	
	public BoardVO selectViewArticle(int articleNO) throws DataAccessException;
	
	public int insertAddArticle(BoardVO boardVO) throws DataAccessException;
	
	public int updateModArticle(BoardVO boardVO) throws DataAccessException;
	
	public int updateRemoveArticle(int articleNO) throws DataAccessException;
	public int updateRestoreArticle(int articleNO) throws DataAccessException;
	public int deleteArticle(int articleNO) throws DataAccessException;
	
	//덧글
	
	public List<CommentVO> selectListComments(int articleNO) throws DataAccessException;
	public List<CommentVO> selectListAdminComments(CommentVO commentVO) throws DataAccessException;
	public List<CommentVO> selectListFlagComments(CommentVO commentVO) throws DataAccessException;
	
	public int insertAddComment(CommentVO commentVO) throws DataAccessException;
	
	public int updateModComment(CommentVO commentVO) throws DataAccessException;
	
	public int updateRemoveComment(int commentArticleNO) throws DataAccessException;
	public int updateRestoreComment(int commentArticleNO) throws DataAccessException;
	public int deleteComment(int commentArticleNO) throws DataAccessException;
}
