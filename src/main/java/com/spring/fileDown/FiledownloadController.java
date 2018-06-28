package com.spring.fileDown;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FiledownloadController {
	
	@RequestMapping("/filedown")
	public ModelAndView fileDownload(){
		System.out.println("파일다운로드 실행......");
		ModelAndView mv = new ModelAndView("filedownload");
		
		return mv;
	}
}
