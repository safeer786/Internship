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
				o.isfinal=false;
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
				Transition t1=new Transition("","",0.0,"",0);
				t1=i.min(value);
				if(t1==null)
				{
					i.transitions.add(t);
				}
				else
				{
					t1.change=t1.change + " " + cha;
					t1.up=1;
				}
				i.link.add(o);
			}
			else if (allnames.contains(istate) && !allnames.contains(ostate))
			{
				int index;
				State i=new State();
				State o=new State();
				o.isfinal=false;
				o.name=ostate;
				index=allnames.indexOf(istate);
				i=all.get(index);
				Transition t=new Transition(value,ostate,cost,cha,up);
				Transition t1=new Transition("","",0.0,"",0);
				t1=i.min(value);
				if(t1==null)
				{
					i.transitions.add(t);
				}
				else
				{
					t1.change=t1.change + " " + cha;
					t1.up=1;
				}
				//i.transitions.add(t);
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
				i.isfinal=false;
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
	//	for1=q1.mixcost(Character.toString(word.charAt(0)));
	//	if(for1.isEmpty())
		//{
	//		System.out.println("Does not exist");
	//		return false;
	//	}
//		else
	//	{
			temp=q1.min(Character.toString(word.charAt(0)));
			if(temp==null)
			{
				System.out.println("Does not Exists");
				return false;
			}
			name=temp.state;
			index=allnames.indexOf(name);
			current=all.get(index);
			if(current==null)
			{
				System.out.println("Does not exist");
				return false;
			}
		//	helper(current,word.substring(1, word.length()));
			for(int i=1;i<word.length();i++)
			{
				
				current=helper1(current,word.substring(i, word.length()));
				if(current==null)
				{
					System.out.println("Does not exist");
					return false;
				}
			}
	//	}
			if(current.isfinal==true)
			{
				System.out.println("Exist");
				return true;
			}
			System.out.println("Does not exist");
			return false;
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
	//	List<Transition> for1=new ArrayList<Transition>();
		
		Transition temp=new Transition("","",0.0,"",0);
		String name;
		int index;
		State traverse=new State();
	//	for1=current.mixcost(Character.toString(word.charAt(0)));
		temp=current.min(Character.toString(word.charAt(0)));
		if(temp==null)
		{
			current=null;
			
		}
		else
		{
	//		if(word.length()==1)
		//	{
		//		return current;
	//		}
	//		else
	//		{
			name=temp.state;
			index=allnames.indexOf(name);
			current=all.get(index);
	//		}
				
		}
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
	  
		State q1=new State();
		int index;
		index=allnames.indexOf("q1");
		q1=all.get(index);
		int indexOfU=word.indexOf("[");
		Transition temp=new Transition("","",0.0,"",0);
		String output="";
			for(int i=1;i< word.length();i++)
			{
				if(i==indexOfU)
				{
					i++;
					temp=q1.min(Character.toString(word.charAt(i)));
					if(!temp.value.equals("") && temp.up!=0)
					{
						word=word+temp.change;
						q1=all.get(allnames.indexOf(temp.state));
					}
				}
				else if(Character.toString(word.charAt(i)).equals("]"))
				{
					;
				}
				else
				{
					temp=q1.min(Character.toString(word.charAt(i)));
					if(!temp.value.equals(""))
					{
						word=word+temp.value;
						q1=all.get(allnames.indexOf(temp.state));
					}
					
				}
			}
	}
	void down(String word)
	{
		
	}
	void final_states(List<String> states)
	{
		int index;
		State current=new State();
		for(int i=0;i<states.size();i++)
		{
			index=allnames.indexOf(states.get(i));
			current=all.get(index);
			current.isfinal=true;
		}
	}
	
	//up funtion self predicting the words is there is a change in output
	void up1(String word)
	{
		List<String> Up_Words=new ArrayList<String>();
		int index_up;
		
		State q1=new State();
		int index;
		String new_word="";
		index=allnames.indexOf("q1");
		q1=all.get(index);
		Transition t=new Transition("","",0.0,"",0);
		t=q1.min(Character.toString(word.charAt(0)));
		if(t==null)
		{
			System.out.println("No such word exists");
			return;
		}
		//nahi tu exist ka function pehla run kar la magar processing mar jae gi
		if(t.up==0)
		{
		  new_word=new_word+t.change;
		  Up_Words.add(new_word);
		}
		else
		{
			if(t.change.contains(" "))
			{
				String temp=t.change;
				boolean check=true;
				while(check==true)
				{
					String[] parts = temp.split(" ");
					new_word=parts[0];
					Up_Words.add(new_word);
					temp=parts[1];
					if(!temp.contains(" "))
					{
						check=false;
						Up_Words.add(temp);
					}
				}
			}
		}
		State current=new State();
		String name;
		index=allnames.indexOf(t.state);
		current=all.get(index);
		int size;
		for(int i=1;i<word.length();i++)
		{
			t=current.min(Character.toString(word.charAt(i)));
			if(t!=null)
			{
				if(t.up == 0)
				{
					for(int j=0;j<Up_Words.size();j++)
					{
						new_word=Up_Words.get(j);
						new_word=new_word+t.change;
						Up_Words.set(j, new_word);
					}
				}
				else
				{
					
					boolean check=true;
					String temp=t.change;
					List<String> changes=new ArrayList<String>();
					size=Up_Words.size();
					if(temp.contains(" "))
					{
					while(check==true)
					{
						String[] parts = temp.split(" ");
						changes.add(parts[0]);
						temp=parts[1];
						if(!temp.contains(" "))
						{
							check=false;
							changes.add(temp);
						}
					}
					
					for(int j=0;j<size;j++)
					{
						new_word=Up_Words.get(j);
						for(int k=0;k<changes.size();k++)
						{
							Up_Words.add(new_word+changes.get(k));
						}
						//
						
					}
					for(int k=0;k<size;k++)
					{
						Up_Words.remove(k);
					}
					
					}	//
					else
					{
						for( i=0;i<Up_Words.size();i++)
						{
							Up_Words.set(i,Up_Words.get(i)+t.change);
						}
					}
//						new_word=new_word+t.change;
	//					Up_Words.set(j, new_word);
					}
				}
			else
			{
				//agar transition nahi hai
				return;
			}
			}
			
		}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans t=new Trans();
		t.Add("q1","a", "q2", 0.4,"");
		t.Add("q2", "b", "q3", 0.2,"");
		t.Add("q3", "c", "q4", 0.0, "");
	//	t.Print();
		for(int i=0;i<t.all.size();i++)
		{
			System.out.println(t.all.get(i).name);
		}
		t.exists("c");
		t.exists("abc");
		List<String> finalstates= new ArrayList<String>();
		finalstates.add("q4");
		finalstates.add("q2");
		t.final_states(finalstates);
		t.exists("abc");
		t.up1("b");
		//q1 at a go to q2 with cost 0.4
	}

}
