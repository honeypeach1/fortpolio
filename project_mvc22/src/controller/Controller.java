package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CourseDAO;
import model.LeavePeople;
import model.Reservation;
import model.StayPeople;

import model.Course;

	/**
	 * Servlet implementation class Controller
	 */
	//@WebServlet("/Controller")
	public class Controller extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public Controller() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		String subPath = request.getPathInfo();
		String viewPage = null;
		System.out.println("[Controller.java] Get Data has been arrive");
		System.out.println("[Controller.java] Arrived SubPath : " + subPath);
		System.out.println();
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//메인페이지 비자 버튼별
		if (subPath.equals("/getCourse"))
		{
			String type = request.getParameter("type");
			
			CourseDAO dao = new CourseDAO();
			ArrayList<Course> dtos = null;
			
			if (type.equals("Short"))
				dtos = dao.shortList();
			else if (type.equals("Long"))
				dtos = dao.longList();
			else if (type.equals("Holiday"))
				dtos = dao.holidayList();
			else if (type.equals("Stay"))
				dtos = dao.stayList();
			else if (type.equals("Student"))
				dtos = dao.studentList();
			else if (type.equals("Work"))
				dtos = dao.workList();
			
			if (dtos != null)
			{
				request.setAttribute("list", dtos);
			}
			
			viewPage = "/mainpage.jsp";
			System.out.print("연결완료");
		}
		
		// 메인페이지 초기화
		else if (subPath.equals("/initMainPage"))
		{
			CourseDAO dao = new CourseDAO();
			
			ArrayList<Course> dtos = dao.AllList();
			request.setAttribute("list",dtos);
			
			viewPage = "/mainpage.jsp";
		}
		
		//스테이 마이페이지
		else if (subPath.equals("/StayMypage"))
		{			
			//상품제공자 번호
			String stay_num = session.getAttribute("STAY_NUM").toString();
			
			System.out.println("뭐지? : " + stay_num);
			CourseDAO db = new CourseDAO();
			
			/*Course course = db.getStaycourse(stay_num);
			
			if (course != null)
			{
				request.setAttribute("course", course);
			}
			else
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아직 등록이 된 상품이 없습니다.');");
				out.println("</script>");
				viewPage = "/StayMypage.jsp";
			}
			*/
			viewPage = "/StayMypage.jsp";
		}

		//상세 페이지 넘어가기(이민, 워킹, 워크, 학생)
		else if (subPath.equals("/DetailInfor"))
		{
			String course_num = request.getParameter("course_num");
			
			CourseDAO db = new CourseDAO();
			
			Course course = db.GetDetailInfor(course_num);
			
			if (course != null)
			{
				request.setAttribute("course", course);		
				
				ArrayList<String> reserdate = db.GetReserDate(course_num);
				request.setAttribute("reserdate", reserdate);
				
				
				viewPage = "/information/DetailInfor.jsp";			
			}	
		
						
			else
			{
				System.out.println("course가 null임");
				//TODO : 해당 패키지가 존재하지 않는다는 예외처리
			}
		}
		
		//상세 페이지 넘어가기(장기)
		else if (subPath.equals("/LongTravel"))
		{
			String course_num = request.getParameter("course_num");
			
			CourseDAO db = new CourseDAO();
			
			Course course = db.GetLongTravel(course_num);
			
			if (course != null)
			{
				request.setAttribute("course", course);
				
				ArrayList<String> long_date = db.GetTravelReserDate(course_num);
				ArrayList<String> reserdate = db.GetReserDate(course_num);
				request.setAttribute("long_date", long_date);
				request.setAttribute("reserdate", reserdate);
				
				viewPage = "/information/TravelInfor.jsp";
				System.out.println("페이지 이동");
			}
			else
			{
				System.out.println("course가 null임");
				//TODO : 해당 패키지가 존재하지 않는다는 예외처리
			}
		} 
		//상세 페이지 넘어가기(단기)
		else if (subPath.equals("/ShortTravel"))
		{
			String course_num = request.getParameter("course_num");
			
			CourseDAO db = new CourseDAO();
			
			Course course = db.GetShortTravel(course_num);
			
			if (course != null)
			{
				request.setAttribute("course", course);
				
				ArrayList<String> long_date = db.GetTravelReserDate(course_num);
				ArrayList<String> reserdate = db.GetReserDate(course_num);
				request.setAttribute("long_date", long_date);
				request.setAttribute("reserdate", reserdate);
				
				viewPage = "/information/TravelInfor.jsp";
				System.out.println("페이지 이동");
			}
			else
			{
				System.out.println("course가 null임");
				//TODO : 해당 패키지가 존재하지 않는다는 예외처리
			}
		} 
		
		else if (subPath.equals("/MakeNewReserv"))
		{
			this.doPost(request, response);
		}
		
		
		else if (subPath.equals("/deleteAccount"))
		{
			this.doPost(request, response);
		}
		
		//메인 페이지 검색
		else if (subPath.equals("/Search"))
			{
					String visa = request.getParameter("visa");        //검색 할때 받아오는 visa 값
					String nation = request.getParameter("nation");    //검색 할때 받아오는 nation 값
					
					CourseDAO db = new CourseDAO();
					
					System.out.println("visa = "+ visa);
					System.out.println("nation = " + nation);
					
					
					ArrayList<Course> search = db.SearchPack(visa,nation); //courseDAO에 검색해서 가져오는 리스트값 함수
					
					if(search != null) 
					{
						request.setAttribute("list", search);
						viewPage = "/mainpage.jsp";                           //search값이 있을시 start페이지로 
					}	
					else
					{
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('일치된 정보가 없습니다! 다시 검색을 해주세요.');");
						out.println("location.href='/project_mvc22/mainpage.jsp'");
						out.println("</script>");
					}
				}	
		
		//예약리스트 보기
		else if(subPath.equals("/checkreser")) 
		{
			CourseDAO db = new CourseDAO();
			String leave_num = session.getAttribute("LEAVE_NUM").toString();
			
			System.out.println("출국자 넘버 " + leave_num);
			ArrayList<Course> reservation = db.CheckList(leave_num);

			if(reservation != null) {			
				request.setAttribute("list",reservation);
				viewPage = "/reservation.jsp";
			}
		}
		//예약 상세정보
		else if(subPath.equals("/DetailReser"))
		{
			CourseDAO db = new CourseDAO();
			String reser_num = request.getParameter("reser_num");
			String course_num = request.getParameter("course_num");
			System.out.println("예약번호 " + reser_num);
			System.out.println("코스번호 " + course_num);

			Course detail = db.GetDetailInfor(course_num);

			if (detail != null)
			{
				request.setAttribute("list",detail);
				viewPage = "/DetailReservation.jsp";
			}
		}
		//예약취소
		else if(subPath.equals("/CancelReser"))
		{
			String reser_num = request.getParameter("reser_num");
			
			CourseDAO db = new CourseDAO();
			String leave_num = session.getAttribute("LEAVE_NUM").toString();
			
			System.out.println("예약 취소 controller 예약번호 " + reser_num);
			
			boolean reserdelete = db.CancelReser(reser_num);
			
			if(reserdelete)
			{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('선택하신 예약이 취소되었습니다..');");
			out.println("location.href='/project_mvc22/view/checkreser?leave_num="+leave_num+"'");
			out.println("</script>");
			System.out.println("테스트");
			}
			else {
				System.out.println("예약 취소 실패");
			}
		}
		//판매자 코스 리스트 불러오기
		else if(subPath.equals("/Courselist"))
		{
			CourseDAO db = new CourseDAO();
			String stay_num = session.getAttribute("STAY_NUM").toString();
			
			System.out.println("코스 리스트 불러오기 판매자 번호 : " + stay_num);
			
			ArrayList<Course> courselist = db.CourseList(stay_num);

			if(courselist != null) {			
				request.setAttribute("lists",courselist);
				viewPage = "/StayMyPageList.jsp";
			}
		}
		
		//추가 정보 삭제하기
		else if(subPath.equals("/deleteInfor"))
		{
			CourseDAO db = new CourseDAO();
			String category_num = request.getParameter("category_num");
			
			System.out.println("추가 정보 삭제하기에서 카테고리 넘버 : " + category_num);
			
			boolean infordelete = db.CancelInfor(category_num);
			
			if(infordelete)
			{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('선택하신 추가 정보가 삭제되었습니다..');");
			out.println("window.close();");
/*			out.println("location.href='/project_mvc22/view/Courselist");*/
			out.println("</script>");
			}
			else {
				System.out.println("추가 정보 삭제 실패");
			}
		}
		
		//추가 정보 수정하기
		else if(subPath.equals("/CorrectInfor"))
		{
			CourseDAO db = new CourseDAO();
			Course course = new Course();
			course.setCategory_kind(request.getParameter("inforKind"));	
			course.setCategory_name(request.getParameter("inforName"));
			course.setCategory_comments(request.getParameter("inforComments"));
			course.setCategory_num(request.getParameter("category_num"));
			
			System.out.println("[/CorrectInfor] 파라미터값 입력완료");
			System.out.println("[/CorrectInfor] category_kind : " + course.getCategory_kind());
			System.out.println("[/CorrectInfor] category_name : " + course.getCategory_name());
			System.out.println("[/CorrectInfor] category_comments : " + course.getCategory_comments());
			System.out.println("[/CorrectInfor] course_num : " + course.getCourse_num());					
			System.out.println();
			
			boolean inforcorrect = db.correctInfor(course);
			
			if(inforcorrect) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('추가 정보 수정이 성공하였습니다...');");
				out.println("window.close()");
				out.println("</script>");

			}else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('추가 정보 수정이 실패하였습니다...');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		//판매자 추가 정보 불러오기 및 등록
				else if(subPath.equals("/addLists"))
				{
					CourseDAO db = new CourseDAO(); 
					
					String course_num = request.getParameter("course_num");
							
					System.out.println("코스 리스트 불러오기 코스 번호 : " + course_num);
					
					ArrayList<Course> addlists = db.addList(course_num);
					if(addlists != null) {			
						request.setAttribute("addlists",addlists);
						viewPage = "/AddList.jsp";
					}else {
						System.out.println("값이 없습니다.");
					}
				}
			
		//판매자 추가 정보 추가 등록하기
				else if(subPath.equals("/addinfor"))
				{
					CourseDAO db = new CourseDAO();
					Course course = new Course();
					course.setCategory_kind(request.getParameter("inforKind"));	
					course.setCategory_name(request.getParameter("inforName"));
					course.setCategory_comments(request.getParameter("inforComments"));
					course.setCourse_num(request.getParameter("course_num"));
					
					System.out.println("[/addinfor] 파라미터값 입력완료");
					System.out.println("[/addinfor] category_kind : " + course.getCategory_kind());
					System.out.println("[/addinfor] category_name : " + course.getCategory_name());
					System.out.println("[/addinfor] category_comments : " + course.getCategory_comments());
					System.out.println("[/addinfor] course_num : " + course.getCourse_num());						
					System.out.println();
					
					boolean insertInfor = db.insertInfor(course);
					
					if(insertInfor) {
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('추가 정보 등록이 성공하였습니다...');");
						out.println("window.close()");
						out.println("</script>");

					}else {
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('추가 정보 등록이 실패하였습니다...');");
						out.println("history.back();");
						out.println("</script>");
					}
				}
		
		//구매자 예약 리스트 불러오기
		else if(subPath.equals("/StayMypageReserList"))
		{
			CourseDAO db = new CourseDAO();
			String stay_num = session.getAttribute("STAY_NUM").toString();
			
			System.out.println("구매자 예약 리스트의 판매자 번호 : " + stay_num);
			
			ArrayList<Course> reserlist = db.ReserList(stay_num);

			if(reserlist != null) {			
				
				request.setAttribute("reserlists",reserlist);
				viewPage = "/StayMypageReserList.jsp";
			}
		}
		
		//구매자 마이페이지
		else if(subPath.equals("/LeaveMypage"))
		{
			LeavePeopleDAO db = new LeavePeopleDAO();
			
			LeavePeople LeavePeople = null;
			try {
				String leave_num = session.getAttribute("LEAVE_NUM").toString();
				
				System.out.println("구매자 마이페이지의 구매자 번호 : " + leave_num);
				
				LeavePeople = db.LeaveInfor(leave_num);

				if (LeavePeople != null)
				{
					request.setAttribute("leaveinfor", LeavePeople);
					viewPage = "/LeaveMypage.jsp";
				}
				else {
					System.out.println("LeavePeople이 null입니다.");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//판매자 페이지에서 코스 삭제
		else if(subPath.equals("/deleteCourse"))
		{
			CourseDAO db = new CourseDAO();
			String course_num =request.getParameter("course_num");
			
			System.out.println("판매자 리스트에서 코스 삭제, 코스 번호 : " + course_num);
			
			boolean coursedelete = db.deleteCourse(course_num);
			
			if(coursedelete) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('선택하신 판매자의 코스가 삭제 되었습니다..');");
			out.println("location.href='/project_mvc22/view/Courselist'");
			out.println("</script>");
			}
			else {
				System.out.println("코스 삭제가 실패하였습니다.");
			}
		}
		
		if (viewPage != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			return;
		}
		else
		{
			System.out.println("viewPage값이 NULL 입니다. 다시 확인하세요.");
			return;
		}
	}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subPath = request.getPathInfo();
		String viewName = null;
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//leave login
		if (subPath.equals("/LoginLeave"))
		{
			
			LeavePeopleDAO db = new LeavePeopleDAO();

			String leave_id = null;
			String leave_pw = null;
			
			LeavePeople LeavePeople = null;
			try
			{
				leave_id = request.getParameter("id"); 
				leave_pw = request.getParameter("pw");
				
				System.out.println("테스트 : " + leave_id + ", " + leave_pw);
				
			}catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			if (leave_id != null && leave_pw != null)
			{
				try {
					LeavePeople = db.loginleave(leave_id, leave_pw);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (LeavePeople != null)
			{
				session.setAttribute("LEAVE_NUM", LeavePeople.getLeave_num());
				session.setAttribute("LEAVE_ID", LeavePeople.getLeave_id());
				session.setAttribute("LEAVE_NAME", LeavePeople.getLeave_name());
				session.setAttribute("GRADE", LeavePeople.getGrade());
				
				if (request.getParameter("id").equals(null))
				{
					System.out.println("[/loginLeave] Redirect URL : " + request.getParameter("requestURL").replace("/project_mvc22", ""));
					viewName = request.getParameter("requestURL").replace("/project_mvc22", "");
				}
				else
				{
					System.out.println("[/loginLeave] Redirect URL : /mainpage.jsp" + " 아이디 값을 가져온다");
					System.out.println(LeavePeople.getGrade());
					
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					
					out.print("<script>");
					out.println("location.href='/project_mvc22/mainpage.jsp'");
					out.println("</script>");
				}
			}
			else
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.println("alert('아이디와 비밀번호를 다시 확인해 주세요.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		//LeavePeople sign up
		else if(subPath.equals("/CreateLeave")) {
			LeavePeopleDAO db = new LeavePeopleDAO();
			
			String leave_id = request.getParameter("leave_id");	
			String leave_pw = request.getParameter("leave_pw");
			String leave_nm = request.getParameter("leave_nm");
			String leave_tel = request.getParameter("leave_tel");
			String leave_email = request.getParameter("leave_email");
			String leave_image = request.getParameter("leave_image");
			
			System.out.println("[/CreateLeave] LeavePeople sign up");
			System.out.println("[/CreateLeave] leave_id : " + leave_id);
			System.out.println("[/CreateLeave] leave_pw : " + leave_pw);
			System.out.println("[/CreateLeave] leave_nm : " + leave_nm);
			System.out.println("[/CreateLeave] leave_tel : " + leave_tel);
			System.out.println("[/CreateLeave] leave_email : " + leave_email);
			System.out.println("[/CreateLeave] leave_image : " + leave_image);
			System.out.println();
			
				boolean InsertLeave = false;
				try {
					InsertLeave = db.CreateLeave(leave_id, leave_pw, leave_nm, leave_tel, leave_email, leave_image);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				if (InsertLeave = true)
				{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					
					out.print("<script>");
					out.println("alert('출국자 회원가입이 완료되었습니다.');");
					out.println("location.href='/project_mvc22/mainpage.jsp'");
					out.println("</script>");
				}
				else
				{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					
					out.print("<script>");
					out.println("alert('출국자 회원가입이 실패 하였습니다.');");
					out.println("history.back();");
					out.println("</script>");
				}
			}
	
		//stay login
		else if(subPath.equals("/LoginStay")) {
			StayPeopleDAO db = new StayPeopleDAO();

			String stay_id = null;
			String stay_pw = null;
			
			StayPeople StayPeople = null;
			
			try
			{
				stay_id = request.getParameter("id");
				stay_pw = request.getParameter("pw");				
				
				System.out.println("테스트 : " + stay_id + ", " + stay_pw);
				
			}catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			if (stay_id != null && stay_pw != null)
			{
				try {
					StayPeople = db.loginStay(stay_id, stay_pw);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			if (StayPeople != null)
			{
				session.setAttribute("STAY_NUM", StayPeople.getStay_num());
				session.setAttribute("STAY_ID", StayPeople.getStay_id());
				session.setAttribute("STAY_NAME", StayPeople.getStay_name());
				session.setAttribute("GRADE", StayPeople.getGrade());
				
				if (request.getParameter("id").equals(null))
				{
					System.out.println("[/loginStay] Redirect URL : " + request.getParameter("requestURL").replace("/project_mvc22", ""));
					viewName = request.getParameter("requestURL").replace("/project_mvc22", "");
				}
				
				else
				{
					System.out.println("[/loginStay] Redirect URL : /mainpage.jsp" + " 아이디 값을 가져온다");
					System.out.println(StayPeople.getGrade());
					viewName = "/mainpage.jsp";
				}
		
			}
			else
			{
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.println("alert('아이디가 없습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		else if (subPath.equals("/initMainPage"))
		{
			this.doGet(request, response);
		}
		
		//예약하기
		else if (subPath.equals("/MakeNewReserv"))
		{
			CourseDAO db = new CourseDAO();
			
			String leave_num = session.getAttribute("LEAVE_NUM").toString();
			String course_num = request.getParameter("course_num");
			String peoples = request.getParameter("peoples");
			String reser_date = request.getParameter("reser_date");
			
			System.out.println("leave_num : " + leave_num);
			System.out.println("course_num : " + course_num);
			System.out.println("peoples : " + peoples);
			System.out.println("reser_date : " + reser_date);
			
			boolean isSuccessed = db.makeNewReservation(course_num, leave_num, reser_date, peoples);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (isSuccessed == true)
			{
				out.print("<script>");
				out.println("alert('예약에 성공하였습니다!');");
				out.println("location.href='/project_mvc22/mainpage.jsp'");
				out.println("</script>");
			}
			else
			{
				out.print("<script>");
				out.println("alert('예약에 실패하였습니다...');");
				out.println("location.href='/project_mvc22/mainpage.jsp'");
				out.println("</script>");
			}
		}
		
		//StayPeople sign up
		else if(subPath.equals("/CreateStay")) {
			StayPeopleDAO db = new StayPeopleDAO();
			
			String stay_id = request.getParameter("stay_id");	
			String stay_pw = request.getParameter("stay_pw");	
			String stay_email = request.getParameter("stay_email");
			String stay_tel = request.getParameter("stay_tel");
			String stay_nm = request.getParameter("stay_nm");
			
			System.out.println("[/CreateStay] CreateStay sign up");
			System.out.println("[/CreateStay] stay_id : " + stay_id);
			System.out.println("[/CreateStay] stay_pw : " + stay_pw);
			System.out.println("[/CreateStay] stay_email : " + stay_email);
			System.out.println("[/CreateStay] stay_tel : " + stay_tel);
			System.out.println("[/CreateStay] stay_nm : " + stay_nm);	
			System.out.println();
			
				boolean InsertStay = false;
				try {
					InsertStay = db.CreateStay(stay_id, stay_pw, stay_email, stay_tel,stay_nm);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				if (InsertStay = true)
				{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					
					out.print("<script>");
					out.println("alert('체류자 회원가입에 성공 하였습니다.');");
					out.println("location.href='/project_mvc22/mainpage.jsp'");
					out.println("</script>");
				}
				else
				{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					
					out.print("<script>");
					out.println("alert('출국자 회원가입이 실패 하였습니다.');");
					out.println("history.back();");
					out.println("</script>");
				}
		}
		
		// 메인코스 등록
		else if (subPath.equals("/CourseRegist")) {
			CourseDAO db = new CourseDAO();
			Course course = new Course();
			course.setStay_num(session.getAttribute("STAY_NUM").toString());
			course.setCourse_name(request.getParameter("CourseName"));
			course.setCourse_price(request.getParameter("CoursePrice"));
			course.setComments(request.getParameter("CourseComment"));
			course.setCourse_image1(request.getParameter("CourseImg1"));
			course.setCourse_image2(request.getParameter("CourseImg2"));
			course.setCourse_image3(request.getParameter("CourseImg3"));
			course.setCourse_image4(request.getParameter("CourseImg4"));
			course.setNation(request.getParameter("CourseNation"));
			course.setStart_date(request.getParameter("Start_date"));
			course.setLong_date(request.getParameter("LongDate"));
			course.setEnd_date(request.getParameter("End_date"));
			course.setPeople_num(request.getParameter("CoursePeoples"));
			course.setVisa(request.getParameter("visa"));
						
			// 패키지 등록이 되었는지 콘솔창에 출력
			System.out.println("[/CourseRegist] 파라미터값 입력완료");
			System.out.println("[/CourseRegist] CourseName : " + course.getCourse_name() + "]");
			System.out.println("[/CourseRegist] CoursePrice : " + course.getCourse_price() + "]");
			System.out.println("[/CourseRegist] CourseComment : " + course.getComments() + "]");
			System.out.println("[/CourseRegist] CourseImage1 : " + course.getCourse_image1() + "]");
			System.out.println("[/CourseRegist] CourseImage2 : " + course.getCourse_image2() + "]");
			System.out.println("[/CourseRegist] CourseImage3 : " + course.getCourse_image3() + "]");
			System.out.println("[/CourseRegist] CourseImage4 : " + course.getCourse_image4() + "]");
			System.out.println("[/CourseRegist] CourseNation : " + course.getNation() + "]");
			System.out.println("[/CourseRegist] Start_date : " + course.getStart_date() + "]");
			System.out.println("[/CourseRegist] End_date : " + course.getEnd_date() + "]");
			System.out.println("[/CourseRegist] CoursePeoples : " + course.getPeople_num() + "]");
			System.out.println("[/CourseRegist] Visa : " + course.getVisa() + "]");

			boolean result = db.registCourse(course);

			if (result != false) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('코스등록 되었습니다. 상세 코스 등록은 MyPage에서 코스 관리를 참조하세요.');");
				out.println("location.href='/project_mvc22/StayMypage.jsp'");
				out.println("</script>");

				session.setAttribute("course", course);
			} else {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('코스등록이 실패하였습니다.');");
				out.println("location.href='/project_mvc22/StayMypage.jsp'");
				out.println("</script>");
			}
		}
		
		
		// 세부 정보 등록
				else if (subPath.equals("/InforRegist")) {
					CourseDAO db = new CourseDAO();
					Course course = new Course();
					course.setCategory_kind(request.getParameter("CategoryKind"));
					course.setCategory_name(request.getParameter("CategoryName"));
					course.setCategory_comments(request.getParameter("CategoryComments"));
					
					// 패키지 등록이 되었는지 콘솔창에 출력
					System.out.println("[/InforRegist] 파라미터값 입력완료");
					System.out.println("[/InforRegist] CategoryKind : " + course.getCategory_kind() + "]");
					System.out.println("[/InforRegist] CategoryName : " + course.getCategory_name() + "]");
					System.out.println("[/InforRegist] CategoryComments : " + course.getCategory_comments() + "]");

					boolean result = db.registCourse(course);

					if (result != false) {
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('세부 정보 등록이 완료 되었습니다.');");
						out.println("location.href='/project_mvc22/StayMypage.jsp'");
						out.println("</script>");
						session.setAttribute("course", course);
					} else {
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('코스등록이 실패하였습니다.');");
						out.println("location.href='/project_mvc22/StayDetailInfor.jsp'");
						out.println("</script>");
					}
				}
		
				//판매자 코스 수정하기
				else if(subPath.equals("/CorrectCourse"))
				{
					CourseDAO db = new CourseDAO();
					String course_num = request.getParameter("course_num"); 
					
					System.out.println("코스 수정하기 코스 번호 : " + course_num);
					
					Course course = new Course();
					course.setCourse_num(course_num);
					course.setCourse_name(request.getParameter("CourseName"));
					course.setNation(request.getParameter("CourseNation"));
					course.setVisa(request.getParameter("visa"));
					course.setCourse_price(request.getParameter("CoursePrice"));
					course.setPeople_num(request.getParameter("CoursePeoples"));
					course.setLong_date(request.getParameter("LongDate"));
					course.setStart_date(request.getParameter("Start_date"));
					course.setEnd_date(request.getParameter("End_date"));
					course.setComments(request.getParameter("CourseComment"));
					course.setCourse_image1(request.getParameter("CourseImg1"));
					course.setCourse_image2(request.getParameter("CourseImg2"));
					course.setCourse_image3(request.getParameter("CourseImg3"));
					course.setCourse_image4(request.getParameter("CourseImg4"));
								
					// JSP페이지에서 입력된 값이 올바르게 오는지 콘솔창에 출력
					System.out.println("[/correctCourse] 수정되는 파라미터값 입력완료");
					System.out.println("[/correctCourse] CourseName : " + course.getCourse_name() + "]");
					System.out.println("[/correctCourse] CourseNation : " + course.getNation() + "]");
					System.out.println("[/correctCourse] Visa : " + course.getVisa() + "]");
					System.out.println("[/correctCourse] CoursePrice : " + course.getCourse_price() + "]");
					System.out.println("[/correctCourse] CoursePeoples : " + course.getPeople_num() + "]");
					System.out.println("[/correctCourse] LongDate : " + course.getLong_date() + "]");			
					System.out.println("[/correctCourse] Start_date : " + course.getStart_date() + "]");		
					System.out.println("[/correctCourse] End_date : " + course.getEnd_date() + "]");
					System.out.println("[/correctCourse] CourseComment : " + course.getComments() + "]");
					System.out.println("[/correctCourse] CourseImage1 : " + course.getCourse_image1() + "]");
					System.out.println("[/correctCourse] CourseImage2 : " + course.getCourse_image2() + "]");
					System.out.println("[/correctCourse] CourseImage3 : " + course.getCourse_image3() + "]");
					System.out.println("[/correctCourse] CourseImage4 : " + course.getCourse_image4() + "]");

					boolean result = db.correctCourse(course);

					if (result != false) {
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('코스 수정이 완료 되었습니다.');");
						out.println("window.close()");
						out.println("</script>");

						session.setAttribute("course", course);
					} else {
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('코스 수정이 실패하였습니다.');");
						out.println("window.close()");
						out.println("</script>");
					}
				}
				
				//예약자 삭제
				else if(subPath.equals("/deleteReser"))
				{
					CourseDAO db = new CourseDAO();
					String reser_num = request.getParameter("reser_num"); 
					
					System.out.println("예약자 삭제 : " + reser_num);
					
					boolean reserdelete = db.deleteReserNum(reser_num);
					
					if(reserdelete)
					{
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('선택하신 구매자의 예약이 취소되었습니다..');");
					out.println("location.href='/project_mvc22/view/StayMypageReserList'");
					out.println("</script>");
					}
					else {
						System.out.println("예약 취소 실패");
					}
				}
		
				else if(subPath.equals("/correctLeave"))
				{
					LeavePeopleDAO db = new LeavePeopleDAO();

					String leave_num = session.getAttribute("LEAVE_NUM").toString();
					System.out.println("코스 구매자 마이페이지의 수정 : " + leave_num);

					try {
					LeavePeople leavepeople = new LeavePeople();
					leavepeople.setLeave_password(request.getParameter("leave_pw"));
					leavepeople.setLeave_name(request.getParameter("leave_nm"));
					leavepeople.setLeave_tel(request.getParameter("leave_tel"));
					leavepeople.setLeave_email(request.getParameter("leave_email"));
					leavepeople.setLeave_image(request.getParameter("leave_image"));
					leavepeople.setLeave_num(leave_num);
					
					System.out.println("[/correctLeave] LeavePeople correct information");
					System.out.println("[/correctLeave] leave_pw : " + leavepeople.getLeave_password());
					System.out.println("[/correctLeave] leave_nm : " + leavepeople.getLeave_name());
					System.out.println("[/correctLeave] leave_tel : " + leavepeople.getLeave_tel());
					System.out.println("[/correctLeave] leave_email : " + leavepeople.getLeave_email());
					System.out.println("[/correctLeave] leave_image : " + leavepeople.getLeave_image());
					System.out.println("[/correctLeave] leave_num : " + leavepeople.getLeave_num());
					System.out.println();
											
						boolean correctLeave = db.correctLeav(leavepeople);
							
							if (correctLeave)
							{
								response.setContentType("text/html;charset=utf-8");
								PrintWriter out = response.getWriter();
								
								out.print("<script>");
								out.println("alert('출국자 회원 수정이 완료 되었습니다. 수정된 정보는 로그아웃 하고 재로그인시 반영 됩니다.');");
								out.println("location.href='/project_mvc22/view/LeaveMypage'");
								out.println("</script>");
							}
							else
							{
								response.setContentType("text/html;charset=utf-8");
								PrintWriter out = response.getWriter();
								
								out.print("<script>");
								out.println("alert('출국자 회원 수정이 실패 하였습니다.');");
								out.println("history.back();");
								out.println("</script>");
							}
							
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
				}
		
				else if(subPath.equals("/deleteAccount"))
				{
					LeavePeopleDAO db = new LeavePeopleDAO();

					String leave_num = session.getAttribute("LEAVE_NUM").toString();
					System.out.println("회원 탈퇴 : " + leave_num);

					boolean accountDelete = false;
					
					try {
						accountDelete = db.acccountDelete(leave_num);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					if(accountDelete != false) {
						System.out.println("회원탈퇴 성공");
						
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						
						out.print("<script>");
						out.println("location.href='/project_mvc22/sessionDestroy.jsp'");
						out.println("</script>");
					}else
					{
						System.out.println("회원탈퇴 실패");
					}
				}
						
		if (viewName != null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
			dispatcher.forward(request, response);
			return;
		}
	}
}
