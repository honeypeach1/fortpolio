package project.user.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserDAO;

public class UserAccountFindAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		
		System.out.println("유저 이름 : " + user_name);
		System.out.println("유저 이메일 : " + user_email);
		
		//유저 디비 처리하기 객체 생성
		UserDAO udao = new UserDAO();
				
		//아이디값 가져오기
		String user_id = udao.FindAcc(user_name,user_email);
		
		if(user_id == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('아이디가 없습니다. 다시 확인 해주세요.'); ");
			out.println(" history.back(); ");
			out.println("</script>");

			out.close();
			return null;
		}else {
		
		System.out.println("유저 아이디 : " + user_id);
		
		//TODO
		//메일 보내기 기능 구현
		String mailProtocol = "smtp";
		String mailHost = "smtp.gmail.com";
		String mailPort = "587";
		String mailId = "kbjae1991@gmail.com"; // 구글계정
		String mailPassword = "oapqmfwewpwicyci"; // 구글계정 비밀번호 
		String fromName = "Milestone";
		String fromEmail = "kbjae1991@gmail.com"; // 보내는 사람 메일
		String toName = user_name;	//받는 사람 이름
		String toEmail = user_email; // 받는 사람 메일
		String mailTitle = "The Third Milestone의 아이디 찾기 입니다.";
		String mailContents = "아이디를 보내드립니다.<br>" + user_id +"<br> 아이디 분실에 주의하세요.";
		String debugMode = "false";
		String authMode = "true";

		try {
			boolean debug = Boolean.valueOf(debugMode).booleanValue();
			
			Properties mailProps = new Properties();
			
			mailProps.put("mail.smtp.starttls.enable", "true");
			mailProps.setProperty("mail.transport.protocol", mailProtocol); 
			mailProps.put("mail.debug", debugMode);
			mailProps.put("mail.smtp.host", mailHost);
			mailProps.put("mail.smtp.port", mailPort);
			mailProps.put("mail.smtp.connectiontimeout", "5000");
			mailProps.put("mail.smtp.timeout", "5000");  
			mailProps.put("mail.smtp.auth", authMode);

			Session msgSession = null;
			if(authMode.equals("true")) {
		        Authenticator auth = new MyAuthentication(mailId, mailPassword);
				msgSession = Session.getInstance(mailProps, auth);
			} else {
				msgSession = Session.getInstance(mailProps, null); 
			}
			msgSession.setDebug(debug);

			MimeMessage msg = new MimeMessage(msgSession);

			msg.setFrom(new InternetAddress(fromEmail, fromName));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toName));
			msg.setSubject(mailTitle);
			msg.setContent(mailContents, "text/html; charset=euc-kr");

			
			// 스태틱함수로 직접 보내지 않고 객체를 이용해서 보내고 객체를 닫아준다. 
			Transport t = msgSession.getTransport(mailProtocol);
			
			System.out.println("메일 보내기 성공");
			try {
				t.connect();
				t.sendMessage(msg, msg.getAllRecipients());
			} finally {
			  t.close();
			}
	  
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	response.setContentType("text/html; charset=UTF-8");

	PrintWriter out = response.getWriter();
	out.println("<script>");
	out.println(" alert('해당 이메일로 아이디를 전송하였습니다.'); ");
	out.println(" location.href='./goToLoginPage.do' ");
	out.println("</script>");
	out.close();
	
	return null;
	}
}
