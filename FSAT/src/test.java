import java.util.ArrayList;
import java.util.List;


public class test {
 
	List <String> l=new ArrayList<String>();
	List <String> o=new ArrayList<String>();
	List<String> a=new ArrayList<String>();
	List<String> in=new ArrayList<String>();
	test()
	{
		l.add("54");
		l.add("46");
		l.add("41");
		l.add("16");
		l.add("64");
		o.add("05");
		o.add("11");
		o.add("23");
		o.add("45");
		o.add("52");
		o.add("53");
		
	}
	void go_to(int x,int y)
	{
		
		if(a.contains(Integer.toString(x)+Integer.toString(y)) || o.contains(Integer.toString(x)+Integer.toString(y))|| x>7 || y>7 || x<0 || y<0)
		{
			return;
		}
		if(l.contains(Integer.toString(x)+Integer.toString(y)))
				{
			System.out.print(x);
			System.out.println(y);
				}
		
		a.add(Integer.toString(x)+Integer.toString(y));
		go_to(x+1,y);
		go_to(x,y+1);
		go_to(x-1,y);
		go_to(x,y-1);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test t=new test();
		t.go_to(0, 6);

	}

}
