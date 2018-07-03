
import java.util.Random;
import java.util.Scanner;

class Compare
{

	public boolean compare(String[][] arr, String[][] arr2)
	{
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j < arr.length; j++)
			{
				if(arr[i][j] == arr2[i][j])
				{
					continue;
				}
				else
				{
					return false;
				}
			}
		}
		return true;
		
	}
}

public class Puzzle {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Compare com = new Compare();
		Random ran = new Random();
		String str = new String();
		int[] ar1 = new int[9];
		String[][] arr2 = {
				{"1", "2", "3"},
				{"4", "5", "6"},
				{"7", "8", "x"}
		};
		int a = 0;
		int b = 0;
		for(;;)
		{
			for(int i = 0; i < ar1.length; i++)
			{
				ar1[i] = ran.nextInt(9)+1;	
			}
			for(int i = 0; i < 8; i++)
			{
				if(a != 0)
				{
					break;
				}
				for(int j = i; j < 8; j++)
				{
					if(ar1[i] != ar1[j+1])
					{
						b++;
						continue;
					}
					else
					{
						a++;
						break;
					}
				}
			}
			if(b > 0)
			{
				break;
			}		
		}
		for(int e : ar1)
		{
			System.out.print(e+" ");
		}
		
		/*for(;;)
		{
			String num = null;
			System.out.println("[ MOVE ] a:Left , s:Right , s:Up , z:Down");
			System.out.println("[ EXIT ] k:Exit");
			System.out.print("이동키를 입력하세요");
			String key = s.next();
			if(key.equals("a"))
			{
				for(int i = 0; i < arr.length; i++)
				{
					for(int j = 1; j < arr.length; j++)
					{					
						if(arr[i][j-1].equals("x"))
						{
							num = arr[i][j-1];
							arr[i][j-1] = arr[i][j];
							arr[i][j] = num;
							break;
						}
						
					}
				}
			}
			else if(key.equals("s"))
			{
				for(int i = 0; i < arr.length; i++)
				{
					for(int j = 0; j < arr.length-1; j++)
					{
						if(arr[i][j+1].equals("x"))
						{
							num = arr[i][j+1];
							arr[i][j+1] = arr[i][j];
							arr[i][j] = num;
							break;
						}
					}
				}
			}
			else if(key.equals("w"))
			{
				for(int i = 1; i < arr.length; i++)
				{
					for(int j = 0; j < arr[i].length; j++)
					{
						if(arr[i-1][j].equals("x"))
						{
							num = arr[i-1][j];
							arr[i-1][j] = arr[i][j];
							arr[i][j] = num;
							i++;
							break;
						}
					}
					
				}
			}
			else if(key.equals("z"))
			{
				for(int i = 0; i < arr.length-1; i++)
				{
					for(int j = 0; j < arr[i].length; j++)
					{
						if(arr[i+1][j].equals("x"))
						{
							num = arr[i+1][j];
							arr[i+1][j] = arr[i][j];
							arr[i][j] = num;
							break;
						}
					}
				}
			}
			else if(key.equals("k"))
			{
				System.out.println("게임을 종료합니다");
				break;
			}
			for(int i = 0; i <arr.length; i++)
			{
				for(int j = 0; j < arr.length; j++)
				{
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
			/*
			if(Arrays.deepEquals(arr, arr2))
			{
				System.out.println("정답입니다");
				System.out.println("게임을 종료합니다");
				break;
			}
			if(com.compare(arr, arr2))
			{
				System.out.println("게임을 종료합니다");
				break;
			}
					
		}	*/
		
	}

}
