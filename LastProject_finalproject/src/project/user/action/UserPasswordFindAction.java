package project.user.action;

import java.io.PrintWriter;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

import project.action.Action;
import project.action.ActionForward;
import project.user.db.UserDAO;

public class UserPasswordFindAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * 
		 * 가져온 정보를 기준으로 일치할시
		 * 
		 * 해당 비밀번호를 입력된, DB상의 이메일로 랜덤값 6자리수 보내기
		 * 
		 *
		 * */
		
		//1단계
		//가져온 파라미터값 DB와 비교
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		
		//받아온 값 확인하기
		System.out.println("유저 비밀번호 찾기 모듈");
		System.out.println("유저 아이디 :" + user_id);
		System.out.println("유저 이름 :" + user_name);
		System.out.println("유저 이메일 :" + user_email);
		System.out.println();
		
		//2단계
		//일치된 정보 있을시 랜덤값 6자리 생성
		UserDAO udao = new UserDAO();
		
		boolean ckPass = udao.ckPass(user_id,user_name,user_email);
		
		//일치된 정보가 있을시
		if(ckPass == true) {
			System.out.println("비밀번호 찾기 성공");
			
			/*
			 * 
			 * 랜덤값 생성 하기
			 * 
			 * */
			
			//랜덤값 저장하기 전역변수
			String temPass = null;
			
			// 대문자 A-Z 랜덤 알파벳 생성
			for (int i = 1; i <= 10; i++) {
		 	   char ch = (char) ((Math.random() * 26) + 65);
		 	   /*System.out.print(ch + " ");*/
			}
			// 소문자 a-z 랜덤 알파벳 생성
			for (int i = 1; i <= 10; i++) {
				char ch = (char) ((Math.random() * 26) + 97);
				/*System.out.print(ch + " ");*/
			}
			// 숫자 1~0 랜덤  생성
			for (int i = 1; i <= 10; i++) {
				char ch = (char) ((Math.random() * 10) + 48);
				/*System.out.print(ch + " ");*/
			}

			StringBuilder newPass = new StringBuilder("");

		//대문자 A-Z 랜덤 알파벳 생성
			for (int i = 1; i <= 3; i++) {
			   char ch = (char) ((Math.random() * 26) + 65);
			   newPass.append(ch);
			}
			// 소문자 a-z 랜덤 알파벳 생성
			for (int i = 1; i <= 3; i++) {
				char ch = (char) ((Math.random() * 26) + 97);
				newPass.append(ch);
			}
			// 숫자 1~0 랜덤  생성
			for (int i = 1; i <= 4; i++) {
				char ch = (char) ((Math.random() * 10) + 48);
				newPass.append(ch);
			}
			
			
			//임시 비밀번호로 DB값 바꾸기

			//Stringbuilder를 String으로 캐스팅
			temPass = newPass.toString();
			
			System.out.println("랜덤 값 생성 및 확인하기");
			System.out.println(temPass);
			
			//3단계
			//랜덤값 6자리를 DB에 새로운 비밀번호로 UPDATE하기.
			boolean UpdateTempPass = udao.UpdatePass(temPass,user_id,user_email);
			
			if(UpdateTempPass == true) {
				System.out.println("회원 임시 비밀번호 업데이트 성공");
				
				//4단계
				//이메일로 랜덤값 6자리 보내기
				
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
				String mailTitle = "The Third Milestone의 비밀번호 찾기 입니다.";
				String mailContents = "임시 비밀번호를 발송 해드립니다." + "<br>" + "임시 비밀번호 : " + temPass;
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
			}else {
				System.out.println("회원 임시 비밀번호 업데이트 실패");
			}
			
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('해당 이메일로 임시 비밀번호를 전송하였습니다.'); ");
			out.println(" location.href='./goToLoginPage.do' ");
			out.println("</script>");
			out.close();
		}else {
			System.out.println("일치된 정보가 없음.");
			
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('일치된 정보가 없습니다. 다시 한번 확인 해주세요.'); ");
			out.println("</script>");
			out.close();
			
			return null;
		}
		
		//forward를 안하는 이유 : 성공했을시 해당 팝업창을 종료하면 됨. 페이지 이동이 없음.
		return null;
	}

}
