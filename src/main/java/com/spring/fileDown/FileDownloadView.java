package com.spring.fileDown;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView{
	
	//생성자
	public FileDownloadView(){
		setContentType("application/download; charset=utf-8"); //클래스에서 사용할 contenType 지정
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("renderMergedOutputModel() 호출");
		
		File file = new File("C:\\JavaStudy\\uploadpractice\\정승환 - 비가온다 Official MV.mp4"); //여기서는 다운받을 파일 따로 지정해서 다운 받을 것임.
		
		response.setContentType(getContentType()); //MIME 타입을 지정, 캐릭터 인코딩 지정
		response.setContentLength(100); //파일의 크기
		
		String userAgent = request.getHeader("User-Agent"); //브라우저의 종류를 알아낼 수 있음.(예를 들면 MSIE 8.0, MSIE 9.0, Chrome 등)
		String fileName = null;
		
		if(userAgent.indexOf("MSIE") > -1){ //indexOf는 해당 문자의 시작 인덱스 번호를 알려준다.
		//MSIE(MicroSoft Internet Explorer) .... if문에서 확인해주는 사항은 해당 userAgent가 explorer 라면 
			fileName = URLEncoder.encode(file.getName(), "utf-8"); //explorer는 파일 이름이 깨지는 현상이 발생하므로, 인코딩을 할 때 파일이름을 utf-8로 인코딩해줌
		}else{
			fileName = new String(file.getName().getBytes("utf-8"), "iso-8859-1");
		}
		//이상 여기까지 한글이 깨지지 않게, 업로드 되어있는 파일 이름을 가져오기 위한 작업(현재 파일이름은 null)
		
		//
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fin = null;
		
		try{
			fin = new FileInputStream(file); //file을 읽어옴.
			FileCopyUtils.copy(fin, out); //inputStream을 그대로 복사해서 outputStream에 넣어주기.
		}finally{
			if(fin !=null){
				try{
					fin.close();
				}catch(IOException e){
					System.out.println("exception : " + e.toString());
				}
			}
		}//finally
		
		out.flush();
	}
}
