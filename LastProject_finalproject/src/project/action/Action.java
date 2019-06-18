package project.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Action {
	
	// 추상메서드 -> 처리 메서드의 틀 생성
	public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception;
	
}
