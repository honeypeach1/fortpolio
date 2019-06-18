package net.project.hotellist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// ì¶”ìƒë©”ì„œ?“œ -> ì²˜ë¦¬ ë©”ì„œ?“œ?˜ ?? ?ƒ?„±
	public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception;

}
