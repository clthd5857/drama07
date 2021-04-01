package com.spring5.drama07.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring5.drama07.member.vo.MemberVO;

public interface MemberController {

	public ModelAndView listMember(@ModelAttribute MemberVO memberVO) throws Exception;
	public ModelAndView flagMember(@ModelAttribute MemberVO memberVO) throws Exception;
	
	public ModelAndView joinMember(@ModelAttribute MemberVO memberVO) throws Exception;
	public ModelAndView modMember(@ModelAttribute MemberVO memberVO) throws Exception;
	public ModelAndView removeMember(@RequestParam String id,HttpServletRequest request) throws Exception;
	public ModelAndView restoreMember(@RequestParam String id) throws Exception;
	
	
	public ModelAndView delMember(@RequestParam String id, @RequestParam int nameflag) throws Exception;
	public void duplicateId(@RequestParam String id, HttpServletResponse response) throws Exception;
	public void duplicateEmail(@RequestParam String id, HttpServletResponse response) throws Exception;
	//로그인
	public ModelAndView loginMember(@ModelAttribute MemberVO memberVO,HttpServletRequest request) throws Exception;
	
	public String logoutMember(HttpServletRequest request) throws Exception;
	
	//form
	public ModelAndView modMemberForm(HttpServletRequest request) throws Exception;
	public String loginForm() throws Exception;
	public String memberForm() throws Exception;
	public String adminPage() throws Exception;
	public String index() throws Exception;
}
