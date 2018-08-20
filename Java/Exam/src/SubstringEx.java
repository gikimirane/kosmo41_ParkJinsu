
public class SubstringEx {

	public static void main(String[] args) {
		//1. 마지막 3글자 자르기
		String str1 = "ABCDEFG";
		String result1 = str1.substring(str1.length()-3, str1.length());
		System.out.println(result1);
		
		//2. 특정문자 포함, 이후의 문자열 제거
		String str2 = "AB/CD/DEFGH";
		int num2 = str2.indexOf("/");
		String result2 = str2.substring(num2+1);
		System.out.println(result2);
		
		//2. 특정문자 포함, 이전의 문자열 제거
		String str3 = "AB/CD/DEFGH";
		int num3 = str3.lastIndexOf("/");
		String result3 = str3.substring(num3+1);
		System.out.println(result3);
				

	}

}
