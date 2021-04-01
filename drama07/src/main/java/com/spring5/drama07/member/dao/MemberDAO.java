package com.spring5.drama07.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring5.drama07.member.vo.MemberVO;

public interface MemberDAO {

	
	public List<MemberVO> selectMemberList(MemberVO memberVO) throws DataAccessException;
	public List<MemberVO> selectMemberFlag(MemberVO memberVO) throws DataAccessException;
	
	public int insertAddMember(MemberVO memberVO) throws DataAccessException;
	public int updateRemoveMember(String id) throws DataAccessException;
	public int updateRestoreMember(String id) throws DataAccessException;
	public int updateModMember(MemberVO memberVO) throws DataAccessException;
	public int deleteMember(String id) throws DataAccessException;
	public String selectDuplicateId(String id) throws DataAccessException;
	public String selectDuplicateEmail(String email) throws DataAccessException;
	//로그인
	
	public String selectLoginMember(MemberVO memberVO) throws DataAccessException;
	
	public MemberVO selectModMember(String id) throws DataAccessException;
}
