package com.spring5.drama07.board.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring5.drama07.board.vo.BoardVO;
import com.spring5.drama07.board.vo.CommentVO;

public interface BoardService {

	public List<BoardVO> articlesList(BoardVO boardVO) throws DataAccessException;
	
	public List<BoardVO> flagArticlesList(BoardVO boardVO) throws DataAccessException;
	
	
	public BoardVO articleView(int articleNO) throws DataAccessException;
	
	public int articleAdd(BoardVO boardVO) throws DataAccessException;
	
	public int articleMod(BoardVO boardVO) throws DataAccessException;
	
	
	public int articleRemove(int articleNO) throws DataAccessException;
	public int articleRestore(int articleNO) throws DataAccessException;
	public int articleDel(int articleNO) throws DataAccessException;
	
	
	//덧글
	
	public List<CommentVO> commentsList(int articleNO) throws DataAccessException;
	
	//관리자
	public List<CommentVO> adminCommentsList(CommentVO commentVO) throws DataAccessException;
	public List<CommentVO> flagCommentsList(CommentVO commentVO) throws DataAccessException;
	
	public int commentAdd(CommentVO commentVO) throws DataAccessException;
	
	public int commentMod(CommentVO commentVO) throws DataAccessException;
	
	public int commentRemove(int commentArticleNO) throws DataAccessException;
	public int commentRestore(int commentArticleNO) throws DataAccessException;
	public int commentDel(int commentArticleNO) throws DataAccessException;
}
