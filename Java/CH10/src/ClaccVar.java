class InstCnt
{
	//static int instnum = 0; //static 변수
	static int instnum = 0;
	InstCnt() 
	{
		instnum++;
		System.out.println("인스턴스 생성: "+instnum);
	}
}
public class ClaccVar {

	public static void main(String[] args) {
		InstCnt cnt1 = new InstCnt();
		InstCnt cnt2 = new InstCnt();
		InstCnt cnt3 = new InstCnt();
	}

}
