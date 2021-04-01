package com.spring5.drama07.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring5.drama07.member.vo.MemberVO;

public interface MemberService {

	public List<MemberVO> memberList(MemberVO memberVO) throws DataAccessException;
	public List<MemberVO> memberFlag(MemberVO memberVO) throws DataAccessException;
	
	
	public int memberAdd(MemberVO memberVO) throws DataAccessException;
	
	public int memberRemove(String id) throws DataAccessException;
	public int memberRestore(String id) throws DataAccessException;
	
	
	public int memberMod(MemberVO memberVO) throws DataAccessException;
	
	public int memberDel(String id) throws DataAccessException;
	
	public String idDuplicate(String id) throws DataAccessException;
	
	public String emailDuplicate(String email) throws DataAccessException;
	//로그인
	
	public boolean memberLogin(MemberVO memberVO) throws DataAccessException;
	
	public MemberVO formMod(String id) throws DataAccessException;
}
