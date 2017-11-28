package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TestJava8 {
	@Test
	public void testData(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();  
	    c.setTime(new Date());
	    Calendar nextDate = (Calendar) c.clone();  
	    nextDate.add(Calendar.DATE, -6); 
	    System.out.println(sdf.format(nextDate.getTime()));
	    nextDate.add(Calendar.DATE, +7); 
	    System.out.println(sdf.format(nextDate.getTime()));
		
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
		String name="hjhjj";
		String[] split = name.split(",");
		for (int i = 0; i < split.length; i++) {
			System.out.println(i);
		}
	}
	@Test
	public void cc(){
		int a=7;
		int b=19;
		System.out.println(a/(float)b);
		
	}
	@Test
	public void dd(){
		Object[] cc={280,""};
		Object[] aa={95,"互联网"};
		Object[] bb={6,"互联网,文化创意"};
		List<Object[]> list = new ArrayList<Object[]>();  
		list.add(aa);
		list.add(bb);
		list.add(cc);
		for (Object[] objects : list) {
			System.out.println(objects[0]+"----"+objects[1]);
		}
		Map<String,Float> map = convertData(list);
		Set<String> set = map.keySet();
		for (String string : set) {
			System.out.println(string+map.get(string));
		}
	}
	private Map<String,Float> convertData(List<Object[]> ratio){
		Map<String,Float> map= new HashMap<String,Float>();
		int sum=0;
		for (int i = 0; i < ratio.size(); i++) {
			Object[] objects=ratio.get(i);
			if(objects[1]==null||objects[1].equals("")){
				break;
			}
			String name=(String)objects[1];
			String[] split = name.split(",");
			if(split.length!=1){
				for (int j = 0; j < split.length; j++) {
					Object[] data={objects[0],split[j]};
					if(map.containsKey(data[1])){
						Float value=map.get(data[1]);
		                map.put((String)data[1], Integer.parseInt(String.valueOf(data[0]))+value);
		            }else{
		                map.put((String)data[1], Float.parseFloat(String.valueOf(data[0])));
		            }
					sum+=Integer.parseInt(String.valueOf(objects[0]));
				}
			}else{
				if(map.containsKey(ratio.get(i)[1])){
					Float value=map.get(ratio.get(i)[1]);
	                map.put((String)ratio.get(i)[1], Integer.parseInt(String.valueOf(ratio.get(i)[0]))+value);
	            }else{
	                map.put((String)ratio.get(i)[1], Float.parseFloat(String.valueOf(ratio.get(i)[0])));
	            }
				sum+=Integer.parseInt(String.valueOf(objects[0]));
			}
		}
		Set<String> set = map.keySet();
		for (String key : set) {
			map.put(key, (float)map.get(key)/sum);
		}
		return map;
	}
	@Test
	public void testss(){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
		Date date=null;
		try {
			date = sdf1.parse("2017");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(sdf2.format(date));
		System.out.println(sdf3.format(date));
	}
	private ThreadLocal<Integer> thead = new ThreadLocal<>();
	private int i = 0;
	
	public TestJava8() {
		thead.set(i);
	}
	/*@Test
	public void testBack(){
		RunnerTask r = new RunnerTask();
		Thread t1 = new Thread(r, "线程1");
		Thread t2 = new Thread(r, "线程2");
		Thread t3 = new Thread(r, "线程3");
		Thread t4 = new Thread(r, "线程4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}*/
}
