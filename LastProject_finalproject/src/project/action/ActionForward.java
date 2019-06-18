package project.action;

public class ActionForward {

		// 이동 정보를 저장하는 객체 
	
		// 이동할 페이지 주소
		private String path;
		// 이동할 방식 
		private boolean isRedirect;
		// true - sendRedirect 방식 이동
		// false - forward 방식 이동
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public boolean isRedirect() {
			return isRedirect;
		}
		public void setRedirect(boolean isRedirect) {
			this.isRedirect = isRedirect;
		}
	
}
