
public class Quiz5_3 {

	public static void main(String[] args) {
		for(int i = 1; i < 100; i++)
		{
			if(((i % 7) == 0) || ((i % 9) == 0)  )
			{
				if(i % 7 == 0)
				{
					if(i % 9 == 0)
					{
						System.out.println(i +"는 7의 배수이면서 9의 배수");
						continue;
					}
					System.out.println(i + "는 7의 배수");
				}
				if(i % 9 == 0)
				{
					System.out.println(i + "는 9의 배수");
				}
			}
		}

	}

}
