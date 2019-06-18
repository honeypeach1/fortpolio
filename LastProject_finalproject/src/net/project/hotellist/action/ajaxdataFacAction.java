package net.project.hotellist.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import net.project.hotellist.db.HotelListDAO;

public class ajaxdataFacAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ajaxdataFacAction_execute");
		
		HotelListDAO hldao = new HotelListDAO();
		int min = Integer.parseInt(request.getParameter("min"));
		int max = Integer.parseInt(request.getParameter("max"));
		String input_text = request.getParameter("input_text");
		String star = request.getParameter("star");
		String review_score = request.getParameter("review_score");
		String fac_checkNum = request.getParameter("fac_checkNum");
		int people_num = Integer.parseInt(request.getParameter("people_num"));
		System.out.println(input_text);
		System.out.println(min);
		System.out.println(max);
		System.out.println(star);
		System.out.println(review_score);
		System.out.println(fac_checkNum);
		System.out.println(people_num);
		JSONArray hotelfacList = hldao.getAjaxHotelFacList(input_text,min,max,star,review_score,fac_checkNum,people_num);
		System.out.println(hotelfacList);
		response.setContentType("application/x-json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(hotelfacList);
		out.flush();
		out.close();
		
		ActionForward forward = new ActionForward();
		forward.setPath("./listPage.jsp");
		forward.setRedirect(false);		
		return forward;
	}

}
