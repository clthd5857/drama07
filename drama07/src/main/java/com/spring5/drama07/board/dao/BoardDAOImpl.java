package com.spring5.drama07.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring5.drama07.board.vo.BoardVO;
import com.spring5.drama07.board.vo.CommentVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlSession;

	public BoardDAOImpl() {
	}

	@Override
	public List<BoardVO> selectListArticles(BoardVO boardVO) throws DataAccessException {
		return sqlSession.selectList("BoardMapper.selectListArticles",boardVO);
	}
	
	@Override
	public List<BoardVO> selectListFlagArticles(BoardVO boardVO) throws DataAccessException {
		return sqlSession.selectList("BoardMapper.selectListFlagArticles",boardVO);
	}
	
	
	
	@Override
	public BoardVO selectViewArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("BoardMapper.selectViewArticle",articleNO);
	}

	@Override
	public int insertAddArticle(BoardVO boardVO) throws DataAccessException {
		sqlSession.insert("BoardMapper.insertAddArticle",boardVO);
		int articleNO = sqlSession.selectOne("BoardMapper.selectArticleNO");
		return articleNO;
	}

	@Override
	public int updateModArticle(BoardVO boardVO) throws DataAccessException {
		return sqlSession.update("BoardMapper.updateModArticle",boardVO);
	}

	@Override
	public int updateRemoveArticle(int articleNO) throws DataAccessException {
		return sqlSession.update("BoardMapper.updateRemoveArticle",articleNO);
	}

	@Override
	public int updateRestoreArticle(int articleNO) throws DataAccessException {
		return sqlSession.update("BoardMapper.updateRestoreArticle",articleNO);
	}

	@Override
	public int deleteArticle(int articleNO) throws DataAccessException {
		return sqlSession.delete("BoardMapper.deleteArticle", articleNO);
	}

	//덧글
	@Override
	public List<CommentVO> selectListComments(int articleNO) throws DataAccessException {
		return sqlSession.selectList("CommentMapper.selectListComments",articleNO);
	}
	
	//관리자
	@Override
	public List<CommentVO> selectListAdminComments(CommentVO commentVO) throws DataAccessException {
		return sqlSession.selectList("CommentMapper.selectListAdminComments",commentVO);
	}

	@Override
	public List<CommentVO> selectListFlagComments(CommentVO commentVO) throws DataAccessException {
		return sqlSession.selectList("CommentMapper.selectListFlagComments",commentVO);
	}

	@Override
	public int insertAddComment(CommentVO commentVO) throws DataAccessException {
		return sqlSession.insert("CommentMapper.insertAddComment",commentVO);
	}

	@Override
	public int updateModComment(CommentVO commentVO) throws DataAccessException {
		return sqlSession.update("CommentMapper.updateModComment", commentVO);
	}

	@Override
	public int updateRemoveComment(int commentArticleNO) throws DataAccessException {
		return sqlSession.update("CommentMapper.updateRemoveComment",commentArticleNO);
	}

	@Override
	public int updateRestoreComment(int commentArticleNO) throws DataAccessException {
		return sqlSession.update("CommentMapper.updateRestoreComment",commentArticleNO);
	}

	@Override
	public int deleteComment(int commentArticleNO) throws DataAccessException {
		return sqlSession.delete("CommentMapper.deleteComment",commentArticleNO);
	}
	
	
	
	
}
