package net.project.hotelregist.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.project.hotelregist.db.HotelRegisBean;
import net.project.hotelregist.db.HotelRegisDAO;
import net.project.roomregist.db.RoomRegisBean;
import net.project.roomregist.db.RoomRegisDAO;
import project.action.Action;
import project.action.ActionForward;

public class regisAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction_execute()");
		
		request.setCharacterEncoding("UTF-8");
		HotelRegisBean hb = new HotelRegisBean();
		
		System.out.println("FileBoardWriteAction_execute()");
		
		// ./upload 폴더 생성
		
		String realPath = request.getRealPath("/images");
		System.out.println(realPath);
		
		int maxSize = 100 * 1024 * 1024;
		
		// MultipartRequest 객체 생성
		MultipartRequest multi = 
				new MultipartRequest(
						request,
						realPath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
						);
		
		String address = multi.getParameter("hotel_address");
		String address1 = multi.getParameter("hotel_address1");
		System.out.println(address+address1);
		
		
		
		
		
		
		String str = multi.getParameter("str");
		System.out.println("str값은 : "+str);
		
		String[] array = str.split(",");
		
		
		
		
		
		int[] hotel_option_no=new int[array.length];
		
		
		for(int i=0;i<array.length;i++) {
		System.out.println(array[i]);
		
		switch(array[i]){
        case "와이파이": 
        	hotel_option_no[i] = 1;
            break;
        case "수영장":
        	hotel_option_no[i] = 2;
            break;
        case "주차장" :
        	hotel_option_no[i] = 3;
            break;
        case "금연" :
        	hotel_option_no[i] = 4;
            break;
        case "환전소" :
        	hotel_option_no[i] = 5;
            break;
        case "레스토랑" :
        	hotel_option_no[i] = 6;
            break;
        case "피트니스" :
        	hotel_option_no[i] = 7;
            break;
        case "안내데스크" :
        	hotel_option_no[i] = 8;
            break;
        case "칵테일바" :
        	hotel_option_no[i] = 9;
            break;
            
        default :
            System.out.println("그 외의 숫자");
    }
		
		}
		
		
		for(int i=0;i<hotel_option_no.length;i++) {
			System.out.println(hotel_option_no[i]);
		
		}
		
		
	
		
		
		
		hb.setHotel_name(multi.getParameter("hotel_name"));
		hb.setHotel_address(address+" "+address1);
		hb.setHotel_option_no(hotel_option_no);
		hb.setHotel_tel(multi.getParameter("hotel_tel"));
		hb.setHotel_class(Integer.parseInt(multi.getParameter("hotel_class")));
		hb.setHotel_instroduct(multi.getParameter("hotel_instroduct"));
		hb.setHotel_pic1(multi.getFilesystemName("hotel_pic1"));
		hb.setHotel_pic2(multi.getFilesystemName("hotel_pic2"));
		hb.setHotel_pic3(multi.getFilesystemName("hotel_pic3"));
		hb.setHotel_pic4(multi.getFilesystemName("hotel_pic4"));
		hb.setHotel_pic5(multi.getFilesystemName("hotel_pic5"));
		HotelRegisDAO htao = new HotelRegisDAO();
		
		
		
		int hotel_num = htao.insertHotel(hb);
		
		
		System.out.println("ACTION"+hotel_num);
		
		request.setAttribute("hotel_num", hotel_num);

		System.out.println(" DAO 에서 Action 페이지로 돌아옴!");

		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./regRoom.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
