
public class UserService {
	EmailService emailService = new EmailService();
	IndexService indexService = new IndexService();
	PointService pointService = new PointService();

	public void userregister(){
			System.out.println("ÓÃ»§×¢²á");
			emailService.Sendconfirmmail();
			pointService.addPoint();
			indexService.indexuser();
			
	}
	
	public static void main(String[] args) {
		UserService userService = new UserService();
		userService.userregister();
	}
}
