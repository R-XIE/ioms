package ioms_web;

public class Test {
	public static void main(String[] args) {
		String url="/business/businessTravelActionList.htm";
		url=url.substring(url.indexOf('/') + 1, url.indexOf("Action") + 6);
//		(\\.*/((login)|(loginout)|(login_ajax)).htm) \\|
		System.out.println(url);
	}
}
