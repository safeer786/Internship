import java.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class State {

	List<State> link=new ArrayList<State>();
	String name;
	Boolean isfinal;
	List<Transition> transitions=new ArrayList<Transition>();
	List<String> transi = new ArrayList<String>();
	
	void set(String value,String change)
	{
		Transition temp=new Transition("","",0.0,"",0);
		Transition temp1=new Transition("","",0.0,"",0);
		Transition tran=new Transition("","",0.0,"",0);
		for(int i=0;i<transitions.size();i++)
		{
			temp=transitions.get(i);
			if(temp.value.equals(value))
			{
				transitions.get(i).change=change;
				transitions.get(i).up=1;
			}
		}
	}
	List<Transition> mixcost(String value)
	{
		Transition temp=new Transition("","",0.0,"",0);
		List <Transition> tran=new ArrayList<Transition>();
		List <Transition> listofTransition=new ArrayList<Transition>();
		for( int i=0;i<transitions.size();i++)
		{
			temp=transitions.get(i);
			if(temp.value.equals(value))
			{
				tran.add(temp);				
				
			}
		}
		 Collections.sort(tran,Transition.costing);
		 return tran;
	}
	Transition min(String value)
	{
		Transition temp=new Transition("","",0.0,"",0);
		Transition temp1=new Transition("","",0.0,"",0);
		Transition tran=new Transition("","",0.0,"",0);
		for(int i=0;i<transitions.size();i++)
		{
			temp=transitions.get(i);
			if(temp.value.equals(value))
			{
				tran.change=temp.change;
				tran.cost=temp.cost;
				tran.state=temp.state;
				tran.up=temp.up;
				tran.value=temp.value;
				return tran;
			}
		}
		return null;
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Transition> temp=new ArrayList<Transition>();
		double b=0.1;
		Transition test1=new Transition("a","a",50.0,"a",0);
		Transition test2=new Transition("a","a",49.0,"a",0);
		Transition test3=new Transition("a","a",53.0,"a",0);
		Transition test4=new Transition("a","a",52.0,"a",0);
		Transition test5=new Transition("a","a",1.0,"a",0);
		temp.add(test1);
		temp.add(test2);
		temp.add(test3);
		temp.add(test4);
		temp.add(test5);
		temp.get(0).Print();
		
		for(int i=0;i<5;i++)
		{
			temp.get(i).Print();
		}
		Collections.sort(temp,Transition.costing);
		for(int i=0;i<5;i++)
		{
			temp.get(i).Print();
		}
		temp.get(0).Print();
	}

}
