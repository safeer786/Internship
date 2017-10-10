import java.util.ArrayList;
import java.util.List;


public class Trans {
	int size=0;
	List<State> all=new ArrayList<State>();
	List<String> allnames=new ArrayList<String>();
	void Add(String istate,String value,String ostate,Double cost,String cha)
	{
		int up;
		if(value.equals(cha))
		{
			up=0;
		}
		else
		{
			up=1;
		}
	
			if(!allnames.contains(istate) && !allnames.contains(ostate))
			{
				State i=new State();
			
				State o=new State();			
				i.isfinal=false;
				Transition t=new Transition(value,ostate,cost,cha,up);
				i.transitions.add(t);
				i.name=istate;
				o.name=ostate;
				i.link.add(o);			
				allnames.add(istate);
				allnames.add(ostate);
				all.add(i);
				all.add(o);
				size++;
				size++;
			}
			else if (allnames.contains(istate) && allnames.contains(ostate))
			{
				int index1,index2;
				State i=new State();
				State o=new State();
				index1=allnames.indexOf(istate);
				index2=allnames.indexOf(ostate);
				i=all.get(index1);
				o=all.get(index2);
				Transition t=new Transition(value,ostate,cost,cha,up);
				i.transitions.add(t);
				i.link.add(o);
			}
			else if (allnames.contains(istate) && !allnames.contains(ostate))
			{
				int index;
				State i=new State();
				State o=new State();
				o.name=ostate;
				index=allnames.indexOf(istate);
				i=all.get(index);
				Transition t=new Transition(value,ostate,cost,cha,up);
				i.transitions.add(t);
				i.link.add(o);
				allnames.add(ostate);
				all.add(o);
				size++;
			}
			else
			{
				int index;
				State i=new State();
				State o=new State();
				index=allnames.indexOf(ostate);
				o=all.get(index);
				i.name=istate;
				Transition t=new Transition(value,ostate,cost,cha,up);
				i.transitions.add(t);
				i.link.add(o);
				allnames.add(istate);
				all.add(i);
				size++;
			}
		
	}

	String words()
	{
		return "a";
	}
	String possible_word()
	{
		return "a";
	}
	Boolean exists(String word)
	{
		
		State q1=new State();
		State current=new State();
		int index;
		String name;
		Transition temp=new Transition("","",0.0,"",0);
		index=allnames.indexOf("q1");
		q1=all.get(index);
		int size=word.length();
		List<Transition> for1=new ArrayList<Transition>();
		for1=q1.mixcost(Character.toString(word.charAt(0)));
		if(for1.isEmpty())
		{
			return false;
		}
		else
		{
			temp=for1.get(0);
			name=temp.state;
			index=allnames.indexOf(name);
			current=all.get(index);
			helper(current,word.substring(1, word.length()));
		}

		return true;
	}
	State helper(State current,String word)
	{
		List<Transition> for1=new ArrayList<Transition>();
		Transition temp=new Transition("","",0.0,"",0);
		String name;
		int index;
		State traverse=new State();
		for1=current.mixcost(Character.toString(word.charAt(0)));
		//agar yar traverse ki state koi ho jo ja rahi ho last char pa tu best hai easy true kar da
		if(for1.size()==0)
		{
			current=null;
			System.out.println("does not exists");
			return null;
		}
			
		for (int i=0;i<word.length();i++)
		{
			for(int j=0;j<for1.size();j++)
			{
				temp=for1.get(j);
				name=temp.state;
				index=allnames.indexOf(name);
				traverse=all.get(index);
				helper(traverse,word.substring(1,word.length()-1));
				
			}
		}
		return current;
	}
	State helper1(State current,String word)
	{
		List<Transition> for1=new ArrayList<Transition>();
		Transition temp=new Transition("","",0.0,"",0);
		String name;
		int index;
		State traverse=new State();
		for1=current.mixcost(Character.toString(word.charAt(0)));
		return current;
	}
//	void Print()
//	{
//		for (int i=0;i<size;i++)
//		{
//			for (int j=0;j<start.transitions.size();j++)
//			{
//				System.out.println(start.name);
//				System.out.println(start.isfinal);
//				start.transitions.get(0).Print();
//			}
//		}
//	}
	void Print ()
	{
		State temp=new State();
		Transition temp1=new Transition("","",0.0,"",0);
		for(int i=0;i<size;i++)
		{
			temp=all.get(i);
			System.out.println(temp.name);
			for(int j=0;j<temp.transitions.size();j++)
			{
				temp.transitions.get(j).Print();
			}
		}
	}
	void up(String word)
	{
	  //for
	}
	void down(String word)
	{
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans t=new Trans();
		t.Add("q1","a", "q2", 0.4,"");
		t.Add("q1", "a", "q2", 0.2,"");
		t.Add("q2", "b", "q1", 0.2,"");
		t.Add("q2", "b", "q2", 0.2,"");
	//	t.Print();
		for(int i=0;i<t.all.size();i++)
		{
			System.out.println(t.all.get(i).name);
		}
		//q1 at a go to q2 with cost 0.4
	}

}
