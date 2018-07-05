import java.util.Arrays;
import java.util.Scanner;

public class Puzzle1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
	
		int a = 0;
		String[][] arr = {
				{"1", "2", "3"},
				{"4", "5", "6"},
				{"7", "x", "8"}
		};
		String[][] arr2 = {
				{"1", "2", "3"},
				{"4", "5", "6"},
				{"7", "8", "x"}
		};

		for(int i = 0; i <arr.length; i++)
		{
			for(int j = 0; j < arr.length; j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}

		for(;;)
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
			
	
		}	
		
	}

}
