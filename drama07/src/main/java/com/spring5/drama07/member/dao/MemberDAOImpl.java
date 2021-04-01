package com.spring5.drama07.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring5.drama07.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	public MemberDAOImpl() {
	}
	
	public MemberDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	//리스트
	@Override
	public List<MemberVO> selectMemberList(MemberVO memberVO) throws DataAccessException {
		return sqlSession.selectList("MemberMapper.selectMemberList",memberVO);
	}
	
	@Override
	public List<MemberVO> selectMemberFlag(MemberVO memberVO) throws DataAccessException {
		return sqlSession.selectList("MemberMapper.selectMemberFlag",memberVO);
	}

	//추가
	@Override
	public int insertAddMember(MemberVO memberVO) throws DataAccessException{
		return sqlSession.insert("MemberMapper.insertAddMember",memberVO);
	}
	
	
	//삭제
	@Override
	public int deleteMember(String id) throws DataAccessException{
		return sqlSession.delete("MemberMapper.deleteMember",id);
	}
	
	@Override
	public int updateRemoveMember(String id) throws DataAccessException{
		return sqlSession.update("MemberMapper.updateRemoveMember",id);
	}
	
	@Override
	public int updateRestoreMember(String id) throws DataAccessException {
		return sqlSession.update("MemberMapper.updateRestoreMember",id);
	}

	//수정
	@Override
	public int updateModMember(MemberVO memberVO) throws DataAccessException{
		return sqlSession.update("MemberMapper.updateModMember",memberVO);
	}
	
	//중복검사
	@Override
	public String selectDuplicateId(String id) throws DataAccessException{
		return sqlSession.selectOne("MemberMapper.selectDuplicateId",id);
	}

	@Override
	public String selectDuplicateEmail(String email) throws DataAccessException {
		return sqlSession.selectOne("MemberMapper.selectDuplicateEmail",email);
	}

	@Override
	public String selectLoginMember(MemberVO memberVO) throws DataAccessException {
		return sqlSession.selectOne("MemberMapper.selectLoginMember",memberVO);
	}

	@Override
	public MemberVO selectModMember(String id) throws DataAccessException {
		return sqlSession.selectOne("MemberMapper.selectModMember",id);
	}
	
	
	
}
