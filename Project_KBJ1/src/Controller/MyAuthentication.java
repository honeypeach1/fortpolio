package Controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
 
class MyAuthentication extends Authenticator {
	
	//회원가입을 했을시에 가입을 축하드립니다 메일 보내기
    PasswordAuthentication pa;

    public MyAuthentication(String mailId, String mailPass) {

        pa = new PasswordAuthentication(mailId, mailPass);  

    }

    public PasswordAuthentication getPasswordAuthentication() {

        return pa;

    }

}
