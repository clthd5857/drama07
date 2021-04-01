package com.spring5.drama07.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DownloadController {

	
	private static String ARTICLE_IMAGE_REPO = "C:\\board07\\article_images";
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void getImage(@RequestParam int articleNO, @RequestParam String fileName, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("fileName=" + fileName);
		OutputStream out = response.getOutputStream();
		
		String filePath = ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + fileName;
		
		File imageFile = new File(filePath);
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;fileName=" + fileName);
		
		FileInputStream in = new FileInputStream(imageFile);
		
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer);
			if (count == -1)
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
		
	}
	
}
