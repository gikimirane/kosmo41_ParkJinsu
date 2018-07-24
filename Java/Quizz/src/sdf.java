import java.util.Calendar;
import java.util.GregorianCalendar;

public class sdf {
	
	 static String solution(int a, int b) {

	    String answer = "";
	
	    Calendar cal = new GregorianCalendar();
	    cal.set(Calendar.YEAR, 2016);
	    try
	    {
	    	if(a <= 12)
	    	{
	    		if((a == 2)&&(b <=28))
	    		{
	    			cal.set(Calendar.MONTH,a-1);
		    		cal.set(Calendar.DATE,b);
	    		}
	    		else if((a <= 7) && (a % 2 == 1)&&(b <= 31))
	    		{
	    			cal.set(Calendar.MONTH,a-1);
	    			cal.set(Calendar.DATE,b);
	    		}
	    		else if((a > 2) && (a <= 7) && (a % 2 == 0)&&(b <= 30))
	    		{
	    			cal.set(Calendar.MONTH,a-1);
		    		cal.set(Calendar.DATE,b);
		    		int mon = cal.get(Calendar.MONTH);
	    		}
	    		else if((a > 7) && (a <= 12)&&(a % 2 == 0)&&(b <= 31))
	    		{
	    			cal.set(Calendar.MONTH,a-1);
		    		cal.set(Calendar.DATE,b);
	    		}
	    		else if((a > 7) && (a <= 12)&&(a % 2 == 1)&&(b <= 30))
	    		{
	    			cal.set(Calendar.MONTH,a-1);
		    		cal.set(Calendar.DATE,b);
	    		}
	    		else
	    			return answer;
    		
	    	}
	    	else
	    	{
	    		return answer;
	    	}
	    	
	    		    
	    }
	 
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	
	    }
	    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

	    switch(dayOfWeek)
	    {
	    case 1:
	    	answer = "SUN";
	    	break;
	    case 2:
	    	answer = "MON";
	    	break;
	    case 3:
	    	answer = "TUE";
	    	break;
	    case 4:
	    	answer = "WED";
	    	break;
	    case 5:
	    	answer = "THU";
	    	break;
	    case 6:
	    	answer = "FRI";
	    	break;
	    case 7:
	    	answer = "SAT";
	    	break;
	    default:
	    	
	    
	    }
	    
	    System.out.println();
	    return answer;
	}	
	public static void main(String[]args){

		int a = 5;
		int b = 24;
		System.out.println(solution(a,b));
		
	
	}
}