package project.user.action;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
 
class MyAuthentication extends Authenticator {
	
	//비밀번호 찾기시 일치된 이메일로 임시 비밀번호 보내기
    PasswordAuthentication pa;

    public MyAuthentication(String mailId, String mailPass) {

        pa = new PasswordAuthentication(mailId, mailPass);  

    }

    public PasswordAuthentication getPasswordAuthentication() {

        return pa;

    }

}
