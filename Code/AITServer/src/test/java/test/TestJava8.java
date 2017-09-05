package test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TestJava8 {
	@Test
	public void testData(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();  
	    c.setTime(new Date());
	    Calendar nextDate = (Calendar) c.clone();  
	    nextDate.add(Calendar.MONTH, +1);  
	    System.out.println("今天"+sdf.format(new Date()));
	    System.out.println("下个月"+sdf.format(nextDate.getTime()));
		
	}
	@Test
	public void aa(){
		String[] atp = {"Rafael Nadal", "Novak Djokovic",  
			       "Stanislas Wawrinka",  
			       "David Ferrer","Roger Federer",  
			       "Andy Murray","Tomas Berdych",  
			       "Juan Martin Del Potro"};  
			List<String> players =  Arrays.asList(atp);  
			  
			// 以前的循环方式  
			for (String player : players) {  
			     System.out.print(player + "; ");  
			}  
			System.out.println();
			System.out.println("===============lambda==============");
			// 使用 lambda 表达式以及函数操作(functional operation)  
			players.forEach((player) -> System.out.print(player + "; "));  
			System.out.println();
			System.out.println("===============java8==============");
//			 在 Java 8 中使用双冒号操作符(double colon operator)  
			players.forEach(System.out::print); 
	}
	
	@Test
	public void bb(){
		String a="aa";
		add(a);
		System.out.println(a);
		
	}
	@Test
	public void cc(){
		int a=7;
		int b=19;
		System.out.println(a/(float)b);
		
	}
	private void add(String a){
		a=a+"bb";
		System.out.println(""+a);
	}
	
}
