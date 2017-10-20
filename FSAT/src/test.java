import java.util.ArrayList;
import java.util.List;


public class test {
 
	List <String> l=new ArrayList<String>();
	List <String> o=new ArrayList<String>();
	List<String> a=new ArrayList<String>();
	List<String> in=new ArrayList<String>();
	test()
	{
		l.add("15");
		l.add("51");
		l.add("22");
		l.add("31");
		l.add("32");
		o.add("03");  //(2, 0); (2, 2); (3, 6); (4, 0); (4, 1); (6, 2);
		o.add("16");
		o.add("34");
		o.add("40");
		o.add("54");
		o.add("55");
		
	}
//	Start position Oedipus: (0, 2); 
	//Start position Oedipus: (3, 6); 
//	Letters: L (1, 5); A (5,1); I (2, 2); O (3,1); S (3, 2); 
//	Obstacles: (0, 3); (1, 6); (3, 4); (4, 0); (5, 4); (5, 5);
//	Letters: L   (2, 5); A (5, 6); I (3, 3); O (2, 2); S (1, 0);   
//	Obstacles:  (0, 3); (0, 6); (1, 6); (4, 2); (4, 4); (6, 4);
	void go_to(int x,int y)
	{
		
		if(a.contains(Integer.toString(x)+Integer.toString(y)) || o.contains(Integer.toString(x)+Integer.toString(y))|| x>7 || y>7 || x<0 || y<0)
		{
			if(l.contains(Integer.toString(x)+Integer.toString(y)))
			{
		System.out.print(x);
		System.out.println(y);
			}

			return;
		}
		
		a.add(Integer.toString(x)+Integer.toString(y));
		go_to(x+1,y);
		go_to(x,y+1);
		go_to(x-1,y);
		go_to(x,y-1);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "004-034556";
		String[] parts = string.split("-");
		
		test t=new test();
		//t.go_to(0,2);

	}

}
