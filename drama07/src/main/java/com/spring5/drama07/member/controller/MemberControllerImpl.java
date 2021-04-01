package com.spring5.drama07.member.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring5.drama07.member.service.MemberService;
import com.spring5.drama07.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	
	@Autowired
	private MemberService memberService;

	public MemberControllerImpl() {
	}

	
	//관리자
	//MemberList, 검색
	@Override
	@RequestMapping(value="/admin07/listMembers", method= {RequestMethod.GET})
	public ModelAndView listMember(@ModelAttribute MemberVO memberVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin07/listMembers");
		List<MemberVO> membersList = memberService.memberList(memberVO);
		mav.addObject("membersList",membersList);
		mav.addObject("name","listMembers");
		int nameflag = 1;
		mav.addObject("nameflag", nameflag);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/admin07/flagMembers", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView flagMember(@ModelAttribute MemberVO memberVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin07/listMembers");
		List<MemberVO> membersList = memberService.memberFlag(memberVO);
		mav.addObject("membersList",membersList);
		mav.addObject("name","flagMembers");
		int nameflag = 2;
		mav.addObject("nameflag", nameflag);
		return mav;
	}
	
	
	//회원 정보 삭제(DB)
	//회원 정보 삭제 flag
	@Override
	@RequestMapping(value="/member07/removeMember", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView removeMember(@RequestParam String id,HttpServletRequest request) throws Exception {
		memberService.memberRemove(id);
		ModelAndView mav = new ModelAndView("member07/index");
		
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("isLogon");
		
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member07/restoreMember", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView restoreMember(@RequestParam String id) throws Exception {
		memberService.memberRestore(id);
		ModelAndView mav = new ModelAndView("redirect:/admin07/flagMembers");
		return mav;
	}	
	
	@Override
	@RequestMapping(value="/member07/delMember", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView delMember(@RequestParam String id, @RequestParam int nameflag) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		if(nameflag==1) {
			mav.setViewName("redirect:/admin07/listMembers");
		} else {
			mav.setViewName("redirect:/admin07/flagMembers");
		}
		
		memberService.memberDel(id);
		return mav;
	}
	
	//일반
	//회원 가입
	@Override
	@RequestMapping(value="/member07/joinMember", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView joinMember(@ModelAttribute MemberVO memberVO) throws Exception {
		memberService.memberAdd(memberVO);
		ModelAndView mav = new ModelAndView("member07/index");
		return mav;
	}
	

	//회원 정보 수정 update/mod
	@Override
	@RequestMapping(value="/member07/modMember", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView modMember(@ModelAttribute MemberVO memberVO) throws Exception {
		memberService.memberMod(memberVO);
		ModelAndView mav = new ModelAndView("member07/index");
		return mav;
	}


	
	
	//아이디, 이메일 존재 확인//중복확인
	@Override
	@RequestMapping(value="/duplicateId", method= {RequestMethod.POST})
	public void duplicateId(@RequestParam String id, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();		
		pw.print(memberService.idDuplicate(id));
	}
	
	@Override
	@RequestMapping(value="/duplicateEmail", method= {RequestMethod.POST})
	public void duplicateEmail(@RequestParam String email, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();		
		pw.print(memberService.emailDuplicate(email));
	}
	
	
	
	//로그인
	@Override
	@RequestMapping(value="/member07/loginMember", method= {RequestMethod.POST})
	public ModelAndView loginMember(@ModelAttribute MemberVO memberVO, HttpServletRequest request) throws Exception {
	
		HttpSession session = request.getSession();
		
		boolean result = memberService.memberLogin(memberVO);
		ModelAndView mav = new ModelAndView();
		
		if(result) {
			String id = memberVO.getId();
			session.setAttribute("isLogon",result);
			session.setAttribute("id", id);
			if(id.equals("hong")) {
				mav.setViewName("admin07/adminPage");
			} else {
				mav.setViewName("member07/index"); 
			}
		} else {
			mav.setViewName("member07/loginForm");
			mav.addObject("isLogon", result);
		}
		return mav;
	}

	//로그아웃
	@Override
	@RequestMapping(value="/member07/logoutMember", method= {RequestMethod.POST, RequestMethod.GET})
	public String logoutMember(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("isLogon");
		return "member07/index";
	}


	//form으로 이동
	@Override
	@RequestMapping(value="/member07/modMemberForm", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView modMemberForm(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		
		ModelAndView mav = new ModelAndView("member07/modMemberForm");
		MemberVO memberVO = memberService.formMod((String)session.getAttribute("id"));
		mav.addObject("memberVO",memberVO);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member07/loginForm", method= {RequestMethod.POST, RequestMethod.GET})
	public String loginForm() throws Exception{
		return "member07/loginForm";
	}
	
	@Override
	@RequestMapping(value="/member07/memberForm", method= {RequestMethod.POST, RequestMethod.GET})
	public String memberForm() throws Exception{
		return "member07/memberForm";
	}
	
	@Override
	@RequestMapping(value="/admin07/adminPage", method= {RequestMethod.POST, RequestMethod.GET})
	public String adminPage() throws Exception{
		return "admin07/adminPage";
	}
	
	@Override
	@RequestMapping(value="/member07/index", method= {RequestMethod.POST, RequestMethod.GET})
	public String index() throws Exception{
		return "member07/index";
	}
	
}
