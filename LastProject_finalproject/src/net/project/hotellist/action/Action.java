package net.project.hotellist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// μΆμλ©μ? -> μ²λ¦¬ λ©μ?? ?? ??±
	public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception;

}
