package com.spring5.drama07.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring5.drama07.member.dao.MemberDAO;
import com.spring5.drama07.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public MemberServiceImpl() {
	}

	public MemberServiceImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List<MemberVO> memberList(MemberVO memberVO) throws DataAccessException {
		return memberDAO.selectMemberList(memberVO);
	}
	
	@Override
	public List<MemberVO> memberFlag(MemberVO memberVO) throws DataAccessException {
		return memberDAO.selectMemberFlag(memberVO);
	}	
	
	@Override
	public int memberAdd(MemberVO memberVO) throws DataAccessException{
		return memberDAO.insertAddMember(memberVO);
	}
	
	@Override
	public int memberDel(String id) throws DataAccessException{
		return memberDAO.deleteMember(id);
	}
	
	
	@Override
	public int memberRemove(String id) throws DataAccessException{
		return memberDAO.updateRemoveMember(id);
	}
	
	@Override
	public int memberRestore(String id) throws DataAccessException {
		return memberDAO.updateRestoreMember(id);
	}

	@Override
	public int memberMod(MemberVO memberVO) throws DataAccessException{
		return memberDAO.updateModMember(memberVO);
	}
	
	@Override
	public String idDuplicate(String id) throws DataAccessException{
		return memberDAO.selectDuplicateId(id);
	}
	
	@Override
	public String emailDuplicate(String email) throws DataAccessException {
		return memberDAO.selectDuplicateEmail(email);
	}

	@Override
	public boolean memberLogin(MemberVO memberVO) throws DataAccessException {
		return Boolean.parseBoolean(memberDAO.selectLoginMember(memberVO));
	}

	@Override
	public MemberVO formMod(String id) throws DataAccessException {
		return memberDAO.selectModMember(id);
	}
	
	
	
	
}
