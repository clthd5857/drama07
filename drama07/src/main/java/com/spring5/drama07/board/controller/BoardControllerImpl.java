package com.spring5.drama07.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring5.drama07.board.service.BoardService;
import com.spring5.drama07.board.vo.BoardVO;
import com.spring5.drama07.board.vo.CommentVO;

@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	
	@Autowired
	private BoardService boardService;
	private final static String ARTICLE_IMAGE_REPO = "C:\\board07\\article_images";
	
	public BoardControllerImpl() {
	}

	//게시글 목록보기
	@Override
	@RequestMapping(value="/board07/listArticles", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listArticles(@ModelAttribute BoardVO boardVO) throws Exception {
		ModelAndView mav = new ModelAndView("board07/listArticles");
		List<BoardVO> articlesList = boardService.articlesList(boardVO);
		mav.addObject("articlesList",articlesList);
		return mav;
	}
	
	//관리자
	@Override
	@RequestMapping(value="/admin07/listAdminArticles", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listAdminArticles(@ModelAttribute BoardVO boardVO) throws Exception {
		ModelAndView mav = new ModelAndView("admin07/listAdminArticles");
		List<BoardVO> articlesList = boardService.articlesList(boardVO);
		mav.addObject("articlesList",articlesList);
		mav.addObject("name","listAdminArticles");
		int nameflag=1;
		mav.addObject("nameflag", nameflag);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/admin07/listFlagArticles", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listFlagArticles(@ModelAttribute BoardVO boardVO) throws Exception {
		ModelAndView mav = new ModelAndView("admin07/listAdminArticles");
		List<BoardVO> articlesList = boardService.flagArticlesList(boardVO);
		mav.addObject("articlesList",articlesList);
		mav.addObject("name","listFlagArticles");
		int nameflag=2;
		mav.addObject("nameflag", nameflag);
		return mav;
	}	
	
	//게시글 자세히 보기
	@Override
	@RequestMapping(value="/board07/viewArticle", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView viewArticle(@RequestParam int articleNO,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("board07/viewArticle");
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.articleView(articleNO);
		mav.addObject("boardVO", boardVO);
		
		//덧글
		List<CommentVO> commentsList = boardService.commentsList(articleNO);
		mav.addObject("commentsList", commentsList);
		return mav;
	}
	
	//게시글 추가
	@Override
	@RequestMapping(value="/board07/addArticle", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView addArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> articleMap = upload(request,response);
		
		String headName = articleMap.get("headName");
		String title = articleMap.get("title");
		String content = articleMap.get("content");
		String fileName = articleMap.get("fileName");
		String id = articleMap.get("id");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setHeadName(headName);
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setFileName(fileName);
		boardVO.setId(id);
		
		
		//articleNO
		int articleNO = boardService.articleAdd(boardVO);
		ModelAndView mav = new ModelAndView("redirect:/board07/viewArticle?articleNO="+articleNO);
		if(fileName!=null && fileName.length()!=0) {
		    File srcFile = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+fileName);
			File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO);
			//destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		
		mav.addObject("articleNO",articleNO);
		
		return mav;
	}
	
	//게시글 수정
	@Override
	@RequestMapping(value="/board07/modArticle", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView modArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		Map<String, String> articleMap=upload(request,response);
		
		int articleNO = Integer.parseInt(articleMap.get("articleNO"));
		String title = articleMap.get("title");
		String content = articleMap.get("content");
		String fileName = articleMap.get("fileName");
		String originalFileName = articleMap.get("originalFileName");
		
		BoardVO boardVO = new BoardVO();
		
		boardVO.setArticleNO(articleNO);
		boardVO.setTitle(title);
		boardVO.setContent(content);
		
		if (fileName != null && fileName.length() != 0) {
			boardVO.setFileName(fileName);
		} else {
			if (originalFileName != null && originalFileName.length() != 0) {
				boardVO.setFileName(originalFileName);
			}
		}
		
		boardService.articleMod(boardVO);				
		ModelAndView mav = new ModelAndView("redirect:/board07/viewArticle?articleNO="+articleNO);
		
		if (fileName != null && fileName.length() != 0) {
			
			originalFileName = articleMap.get("originalFileName");
			File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
			oldFile.delete();
								
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
			File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
			//destDir.mkdirs();
			
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		} 		
		
		return mav;
	}
	
	//게시글 삭제
	@Override
	@RequestMapping(value="/board07/removeArticle", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView removeArticle(@RequestParam int articleNO) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/board07/listArticles");
		boardService.articleRemove(articleNO);
		
		return mav;
	}

	@Override
	@RequestMapping(value="/board07/restoreArticle", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView restoreArticle(@RequestParam int articleNO) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/admin07/listFlagArticles");
		boardService.articleRestore(articleNO);
		
		return mav;
	}
	
	
	
	@Override
	@RequestMapping(value="/board07/delArticle", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView delArticle(@RequestParam int articleNO, @RequestParam int nameflag) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		if(nameflag==1) {
			mav.setViewName("redirect:/admin07/listAdminArticles");
			//ModelAndView mav = new ModelAndView("redirect:/admin07/listAdminArticles");
		} else {
			mav.setViewName("redirect:/admin07/listFlagArticles");
			//ModelAndView mav = new ModelAndView("redirect:/admin07/listFlagArticles");
		}
		
		boardService.articleDel(articleNO);
		
		File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
		if (imgDir.exists()) {
			FileUtils.deleteDirectory(imgDir);
		}
		return mav;
	}
	
	//form으로 이동
	@RequestMapping(value="/board07/articleForm", method= {RequestMethod.POST, RequestMethod.GET})
	public String modForm() throws Exception{
		return "board07/articleForm";
	}
	
	//덧글 추가
	@Override
	@RequestMapping(value="/board07/addComment", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView addComment(@ModelAttribute CommentVO commentVO) throws Exception{
		int articleNO = commentVO.getArticleNO();
		boardService.commentAdd(commentVO);
		ModelAndView mav = new ModelAndView("redirect:/board07/viewArticle?articleNO="+articleNO);
		return mav;
	}
	
	//덧글 수정
	@Override
	@RequestMapping(value="/board07/modComment", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView modComment(@ModelAttribute CommentVO commentVO) throws Exception {
		
		int articleNO = commentVO.getArticleNO();
		ModelAndView mav= new ModelAndView("redirect:/board07/viewArticle?articleNO="+articleNO);
		boardService.commentMod(commentVO);
		
		return mav;
	}

	//덧글 삭제 flag
	@Override
	@RequestMapping(value="/board07/removeComment", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView removeComment(@RequestParam int commentArticleNO,@RequestParam int articleNO) throws Exception {
		
		ModelAndView mav = new ModelAndView("redirect:/board07/viewArticle?articleNO="+articleNO);
		boardService.commentRemove(commentArticleNO);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/board07/restoreComment", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView restoreComment(@RequestParam int commentArticleNO) throws Exception {
		
		ModelAndView mav = new ModelAndView("redirect:/admin07/listFlagComments");
		boardService.commentRestore(commentArticleNO);
		return mav;
	}
	
	//덧글 삭제
	@Override
	@RequestMapping(value="/board07/delComment", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView delComment(@RequestParam int commentArticleNO, @RequestParam int nameflag) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		if(nameflag==1) {
			mav.setViewName("redirect:/admin07/listComments");
		} else {
			mav.setViewName("redirect:/admin07/listFlagComments");
		}
		boardService.commentDel(commentArticleNO);
		return mav;
	}

	
	//덧글 관리
	@RequestMapping(value="/admin07/listComments", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listAdminComments(@ModelAttribute CommentVO commentVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin07/listComments");
		List<CommentVO> commentsList = boardService.adminCommentsList(commentVO);
		mav.addObject("commentsList", commentsList);
		mav.addObject("name", "listComments");
		int nameflag=1;
		mav.addObject("nameflag", nameflag);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/admin07/listFlagComments", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listFlagComments(@ModelAttribute CommentVO commentVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin07/listComments");
		List<CommentVO> commentsList = boardService.flagCommentsList(commentVO);
		mav.addObject("commentsList", commentsList);
		mav.addObject("name", "listFlagComments");
		int nameflag=2;
		mav.addObject("nameflag", nameflag);
		return mav;
	}	
	
	
	//encType upload 파일 Map으로 받기
	private Map<String, String> upload(HttpServletRequest request, 
									   HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		Map<String, String> articleMap = new HashMap<String, String>();
		
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setRepository(currentDirPath);		
		factory.setSizeThreshold(1024 * 1024);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			for (int i = 0; i < items.size(); i++) {
			FileItem fileItem = (FileItem) items.get(i);
			
				if (fileItem.isFormField()) {
				System.out.println(fileItem.getFieldName() + "=" + fileItem.getString("utf-8"));
				
				articleMap.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
				} else {
				System.out.println("파라미터명:" + fileItem.getFieldName());
				System.out.println("파일명:" + fileItem.getName());
				System.out.println("파일크기:" + fileItem.getSize() + "bytes");
				
					if (fileItem.getSize() > 0) {
					int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
					
					String fileName = fileItem.getName().substring(idx + 1);
					System.out.println("파일명:" + fileName);
					articleMap.put(fileItem.getFieldName(), fileName);
					
					File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
					fileItem.write(uploadFile);
					
					} // end if
				} // end else
			} // end for
		} catch (Exception e) {
		e.printStackTrace();
		}
		return articleMap;
	}	
	
	
}
