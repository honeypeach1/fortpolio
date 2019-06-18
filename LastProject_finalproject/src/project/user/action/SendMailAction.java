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

import project.action.Action;
import project.action.ActionForward;

public class SendMailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		//해당 폼에서 가져온 파라미터값 생성및 출력
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		String user_comment = request.getParameter("user_comment");
		
		System.out.println("ABOUT페이지 문의 메일 기능");
		System.out.println("유저 이름 : " + user_name);
		System.out.println("유저 메일 : " + user_email);
		System.out.println("유저 코멘트 : " + user_comment);

		//메일 보내기 기능 구현
		String mailProtocol = "smtp";
		String mailHost = "smtp.gmail.com";
		String mailPort = "587";
		String mailId = "kbjae1991@gmail.com"; // 구글계정
		String mailPassword = "oapqmfwewpwicyci"; // 구글계정 비밀번호 
		String fromName = user_name;   // 보내는 사람 이름
		String fromEmail = user_email; // 보내는 사람 메일
		String toName = "Milestone";	//받는 사람 이름
		String toEmail = "sir91@naver.com"; // 받는 사람 메일
		String mailTitle = user_name+"님의 문의 메일 입니다.";
		String mailContents = user_comment + "<br> 유저 이메일 : " + user_email;
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
		
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('성공적으로 문의 메일을 보내셨습니다.'); ");
			out.println(" history.back() ");
			out.println("</script>");
	
			return null;
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
