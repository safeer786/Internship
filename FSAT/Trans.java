import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack; 

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
				i.transi.add(value);
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
				String change="";
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
					i.transi.add(value);
				}
				else
				{
					change=t1.change + " " + cha;
					t1.up=1;
					i.set(value, change);
				}
				i.link.add(o);
			}
			else if (allnames.contains(istate) && !allnames.contains(ostate))
			{
				int index;
				String change="";
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
					i.transi.add(value);
				}
				else
				{
					change=t1.change + " " + cha;
					i.set(value, change);
					//t1.up=1;
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
				i.transi.add(value);
				i.link.add(o);
				allnames.add(istate);
				all.add(i);
				size++;
			}
		
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
			name=temp.state;
			index=allnames.indexOf(name);
			current=all.get(index);
		}
		return current;
	}
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
	
	//In this function we predict what is the best value of the transition
	void up3(String word)
	{
		List<String> Up_Words=new ArrayList<String>();
		int index_up;
		List<Integer> indexes=new ArrayList<Integer>();
		List<Transition> possible=new ArrayList<Transition>();
		List<Transition> possible_now=new ArrayList<Transition>();
		State q1=new State();
		int index;
		String new_word="";
		index=allnames.indexOf("q1");
		q1=all.get(index);
		Transition t=new Transition("","",0.0,"",0);
		//t=q1.min(Character.toString(word.charAt(0)));
		for(int i=0;i<word.length();i++)
		{
			t=q1.min(word.substring(i, word.length()));
			if(t != null)
			{
				possible.add(t);
			}						
		}	
	
	     if(possible.size()==0)
	     {
	    	 System.out.println("Does Nor === exists");
	    	 return;
	     }
	     indexes.add(possible.size());
	     for(int i=0;i<possible.size();i++)
	     {
	    	 t=possible.get(i);
	    	 String prev_word=t.value;
	    	 new_word=new_word+t.value;
	    	 possible_now=helperfinal(t,word,new_word);
	    	 indexes.add(possible_now.size());
	    	 for(int j=0;j<possible_now.size();j++)
	    	 {
	    		 //if the transition exists add in a list
	    		 //case of third node for example
	    		 
	    	 }
	     }
	}
	
	List<Transition> helperfinal(Transition t,String word,String current_word)
	{
		List<Transition> possible_now=new ArrayList<Transition>();
		String remain=word.substring(current_word.length(),word.length());
		State q=new State();
		Transition t1=new Transition("","",0.0,"",0);
		int index=allnames.indexOf(t.state);
		q=all.get(index);
		for(int i=0;i<remain.length();i++)
		{
			t1=q.min(word.substring(i,remain.length()));
			if(t1 !=null)
			{
				possible_now.add(t1);
			}
		}
		return possible_now;
	} 
	
	void transition(String word)
	{
	//	for
	}
	//--------------------------------------------------------------------------------------------
	List<String>  upfinal(String word)
	{
		State q= new State();
		int indexf=0;
		int word_size=word.length();
		List<String> output=new ArrayList<String>();
		List<String> c_output=new ArrayList<String>();
		List<String> final_output=new ArrayList<String>();
		List<String> ch_output=new ArrayList<String>();
		int index=allnames.indexOf("q1");
		List<State> current_states=new ArrayList<State>();
		List<State> previous_states=new ArrayList<State>();
		q=all.get(index);
		Transition t=new Transition ("","",0.0,"",0);
	
		List<Transition> temp=new ArrayList<Transition>();
		temp=possible_Transitions(q,word);
		if(temp==null)
		{
			return null;
		}
		set(q,output,temp,current_states,previous_states);
		set0(q,c_output,temp,current_states,previous_states);
		if(output.isEmpty())
		{
			return null;
		}
		else
		{
			final_output=update_final(final_output,output,indexf);
			ch_output=update_chh(ch_output,c_output,indexf);
		}
		boolean test=true;
		int check=0;
		System.out.println("FUCK");
		output.clear();
		final_output=Repe1(final_output,output,temp,word,ch_output);
		System.out.println("YES");
		for(int i=0;i<final_output.size();i++)
		{
			System.out.println(final_output.get(i));
		}
		System.out.println("YES");
		
		//------------------------------------------------------------------
	//	final_output=final_done(final_output);
		System.out.println("NO");
		for(int i=0;i<final_output.size();i++)
		{
			System.out.println(final_output.get(i));
		}
		System.out.println("NO");
		
		//------------------------------------------------------------------
		List<String> finallydone=new ArrayList<String>();
		List<String> temp1=new ArrayList<String>();
		for(int i=0;i<5;i++)
		{
			temp1=final_done(final_output.get(i));
			for(int j=0;j<temp1.size();j++)
			{
				String w=temp1.get(j);
				finallydone.add(w);
			}
			temp1.clear();
		}
		System.out.println("NO");
		for(int i=0;i<finallydone.size();i++)
		{
			System.out.println(finallydone.get(i));
		}
		System.out.println("NO");
		return final_output;
		
		
	}
	
	//-----------------------------------------------------------------------------------------
	List <String> final_done(String words)
	{

			String remain="";
			String sub_words="";			
			int index1=0;
			int index2=0;
			List<String> ch_output=new ArrayList<String>();
			if(words.contains("£"))
			{
				index1=words.indexOf("£");
				if(index1==0)
				{
				words=words.replaceFirst("£", "");
				index2=words.indexOf("£");
				sub_words=words.substring(index1,index2);
				if(index2!=words.length())
				{
					remain=words.substring(index2+1,words.length());
				}
				else
				{
					remain="";
				}
				String arr[]=sub_words.split(" ");
				for(int k=0;k<arr.length;k++)
				{
					if(!remain.contains("£"))
					{
						ch_output.add(arr[k]+remain);
					}
					else
					{
						ch_output.add(arr[k]);
					}
				}
				}
				else
				{
					String base=words.substring(0,index1);
					words=words.substring(index1,words.length());
					words=words.replaceFirst("£", "");
					index2=words.indexOf("£");
			//		System.out.println("index1"+index2);
			//		System.out.println("index1"+words);
			//		System.out.println("index1"+index1);
			//		System.out.println("index1"+index2);
					sub_words=words.substring(0,index2);
					if(index2!=words.length())
					{
						remain=words.substring(index2+1,words.length());
					}
					else
					{
						remain="";
					}
					String arr[]=sub_words.split(" ");
					//System.out.println(arr[0]);
					for(int k=0;k<arr.length;k++)
					{
							ch_output.add(base+arr[k]);
					}
					
				}
				remain=words.substring(index2+1, words.length());
				while(remain.contains("£"))
				{
					String base="";
					index1=remain.indexOf("£");
					if(index1!=0)
					{
						base=remain.substring(0,index1);
						System.out.println("base" + base);
						for(int k=0;k<ch_output.size();k++)
						{
							String w=ch_output.get(k);
							ch_output.set(k,w+base);
						}
						remain=remain.substring(index1,remain.length());
					}
					else
					{
						remain=remain.replaceFirst("£", "");
						index2=remain.indexOf("£");
						sub_words=remain.substring(0,index2);
						String arr1[]=sub_words.split(" ");
						int siz=ch_output.size();
						String w;
						for(int k=0;k<arr1.length-1;k++)
						{
							
							for(int m=0;m<siz;m++)
							{
								w=ch_output.get(m);
								ch_output.add(w);								
							}
						}
						int counter=0;
						for(int k=0;k<arr1.length;k++)
						{
							for(int m=0;m<siz;m++)
							{
								w=ch_output.get(counter);
								ch_output.set(counter, w+arr1[k]);
								counter++;
							}
							//counter=counter+siz;
						}
						System.out.println("before remain" +remain);
						if(index2!=remain.length())
							remain=remain.substring(index2+1,remain.length());
						else
							remain="";
						System.out.println("remain" +remain);
						if(!remain.contains("£") && remain.length()>=0)
						{
							for(int i=0;i<ch_output.size();i++)
							{
								String a=ch_output.get(i);
								ch_output.set(i, a+remain);
							}
						}
						
					}
				}
			}
			else
			{
				ch_output.add(words);
			}
						
		
		
		return ch_output;
	}
	//------------------------------------------------------------------------------------------------------
	
	List<String> Repe1(List<String> final_output,List<String> output,List<Transition> transition,String word,List<String> ch_output)
	{
		Transition t=new Transition("","",0.0,"",0);
		List<Transition> temp=new ArrayList<Transition>();
		List<Transition> temp1=new ArrayList<Transition>();
		Stack<List<String>> s=new Stack<List<String>>();
		Stack<List<String>> s1=new Stack<List<String>>();
		List<String> c_output=new ArrayList<String>();
		State q=new State();
		int index;
		int count=0;
		int word_size=word.length();
		while(transition !=null)
		{
		for(int i=0;i<transition.size();i++)
		{
			t=transition.get(i);
			q=all.get(allnames.indexOf(t.state));
			if(output.isEmpty())
			{
				index=0;
			}
			else
			{index=output.size()-1  ;}
			output.clear();
			c_output.clear();
			System.out.println("YES12");
			System.out.println(final_output.get(i));
			System.out.println(word.substring(final_output.get(i).length(),word_size));
			System.out.println("YES12");
			temp=possible_Transitions(q,word.substring(final_output.get(i).length(),word_size));
			if(temp !=null)
			{
			for(int j=0;j<temp.size();j++)
			{
				temp1.add(temp.get(j));
			}
			set3(q,output,temp);
			
			set33(q,c_output,temp);
			ArrayList<String> ss=new ArrayList<String>(output);
			ArrayList<String> ss1=new ArrayList<String>(c_output);
			s.push(ss);
			s1.push(ss1);
	//		final_output=update_f(final_output,output,i);
			System.out.println("YES1");
	//		count++;
			}
		}
		int k=s.size();
		System.out.println(k);
		if(k!=0)
		{
		for(int i=k-1;i>=0;i--)
		{
			//output.clear();
			output=s.pop();
			c_output=s1.pop();
			final_output=update_f(final_output,output,i);
			ch_output=update_ch(ch_output,c_output,i);
		}
		}
		count++;
		if(temp1.isEmpty())
		{
			transition =null;
		}
		else
		{
			transition.clear();
			System.out.println("YES2");
			for(int j=0;j<temp1.size();j++)
			{
				System.out.println(temp1.get(j).value);
			}
			System.out.println("YES2");
			for(int i=0;i<temp1.size();i++)
			{
				transition.add(temp1.get(i));
			}
			temp1.clear();
		}
		
		}
		
		return ch_output;
	}
	
	List<String> Repe(List<String> final_output,List<String> output,List<Transition> transition,String word)
	{
		Transition t=new Transition("","",0.0,"",0);
		List<Transition> temp=new ArrayList<Transition>();
		List<Transition> temp1=new ArrayList<Transition>();
		State q=new State();
		int index;
		int count=0;
		int word_size=word.length();
		while(transition !=null)
		{
		for(int i=0;i<transition.size();i++)
		{
			t=transition.get(i);
			q=all.get(allnames.indexOf(t.state));
			if(output.isEmpty())
			{
				index=0;
			}
			else
			{index=output.size()-1  ;}
			output.clear();
			System.out.println("YES12");
			System.out.println(final_output.get(i));
			System.out.println(word.substring(final_output.get(i+(index*count)).length(),word_size));
			System.out.println("YES12");
			temp=possible_Transitions(q,word.substring(final_output.get(i).length(),word_size));
			if(temp !=null)
			{
			for(int j=0;j<temp.size();j++)
			{
				temp1.add(temp.get(j));
			}
			set3(q,output,temp);
			final_output=update_f(final_output,output,i);
			System.out.println("YES1");
			count++;
			}
		}
		if(temp1.isEmpty())
		{
			transition =null;
		}
		else
		{
			transition.clear();
			System.out.println("YES2");
			for(int j=0;j<temp1.size();j++)
			{
				System.out.println(temp1.get(j).value);
			}
			System.out.println("YES2");
			for(int i=0;i<temp1.size();i++)
			{
				transition.add(temp1.get(i));
			}
			temp1.clear();
		}
		
		}
		
		return final_output;
	}
	
	
	List<String> update_f(List<String> final_output,List<String> output,int index)
	{
		List<String> temp=new ArrayList<String>();
		int count=0;
		for(int i=0;i<final_output.size();i++)
		{
			
			if(i==index)
			{
				for(int j=0;j<output.size();j++)
				{
					temp.add(count,final_output.get(i)+output.get(j));
					count++;
				}
			}
			else
			{
				temp.add(count, final_output.get(i));
				count++;
			}
		}
		return temp;
	}
	List<String> update_ch(List<String> ch_output,List<String> output,int index)
	{
		List<String> temp=new ArrayList<String>();
		int count=0;
		for(int i=0;i<ch_output.size();i++)
		{
			
			if(i==index)
			{
				for(int j=0;j<output.size();j++)
				{
					temp.add(count,ch_output.get(i)+output.get(j));
					count++;
				}
			}
			else
			{
				temp.add(count, ch_output.get(i));
				count++;
			}
		}
		return temp;
	}
	
	
	
	
	
	
	
	
	
	List <String> Rep(State q,List<String> done,List<String> final_output,List<String> output,List <Transition> transections,List<State> current_states,List<State> previous_states,String word)
	{
		if(transections==null)
		{
			done.add(final_output.get(final_output.size()-1));
		}
		for(int i=0;i<transections.size();i++)
		{
			Transition t=new Transition("","",0.0,"",0);
			List<Transition> temp=new ArrayList<Transition>();
		}
		return final_output;
	}
	List <String> Repeat(State q,List<String> final_output,List<String> output,List <Transition> transections,List<State> current_states,List<State> previous_states,String word)
	{
		Transition t=new Transition("","",0.0,"",0);
		List<Transition> temp=new ArrayList<Transition>();
		List<Transition> temp1=new ArrayList<Transition>();
		int index;
		int word_size=word.length();
		for(int j=0;j<final_output.size();j++)
		{
			System.out.println(final_output.get(j));
		}
		if(transections!=null)
		{		
		for(int i=0;i<transections.size();i++)
		{
			t=transections.get(i);

			System.out.println("YEH");
			for(int j=0;j<final_output.size();j++)
			{
				System.out.println(final_output.get(j));
			}
			System.out.println("t.value");
			System.out.println(t.value);
			System.out.println(word);
			
			
			q=all.get(allnames.indexOf(t.state));
			temp=possible_Transitions(q,word.substring(t.value.length(),word_size));
			index=output.size();
			output.clear();
			set2(q,output,temp,current_states,previous_states);
			if(temp!= null)
			{
				if(i==0)
					final_output=update_final(final_output,output,0);
				else
					final_output=update_final(final_output,output,index*i);
				temp1=possible_Transitions(q,word.substring(t.value.length(),word_size));
			//	if(temp1!=null)
					Repeat(q,final_output,output,temp,current_states,previous_states,word.substring(t.value.length(),word_size));
			}
		}	
		}
		return final_output;
	}
	List<Transition> possible_Transitions(State q,String word)
	{
		List<Transition> send=new ArrayList<Transition>();
		Transition t=new Transition ("","",0.0,"",0);
		for(int i=1;i<=word.length();i++)
		{
			t=q.min(word.substring(0,i));
			//System.out.println(t.value);
			if(t!=null)
			{
				if(t.value.equals(t.change))
					{send.add(t);}
				else
				{
					send.add(t);
				//	String arr[]=t.change.split(" ");
				//	for(int j=0;j<arr.length;j++)
				//	{
				//		
				//		Transition c=new Transition (arr[j],t.state,0.0,arr[j],0);
				//		q.transitions.add(c);
					//	send.add(c);
			//		}
			//		//Transition c=new Transition (t.change,t.state,0.0,t.change,0);
					
				}
			}
			else
			{
				t=new Transition ("","",0.0,"",0);
			}
			
		}
		if(send.size()==0)
		{
			return null;
		}
		
		return send;
	}
	List<String> update_final(List<String> final_output,List<String> output,int index)
	{
		List<String> temp=new ArrayList<String>();
		
		if(final_output.isEmpty())
		{
			for(int i=0;i<output.size();i++)
			{
				final_output.add(output.get(i));
			}
			return final_output;
		}
		else
		{
			for(int i=0;i<final_output.size();i++)
			{
				if(i==index)
				{
					for(int j=0;j<output.size();j++)
					{
						temp.add(final_output.get(index)+output.get(j));
					}
				}
				else
				{
					temp.add(final_output.get(i));
				}
			}
		}
		index=index+output.size();
		return temp;
	}
	List<String> update_chh(List<String> ch_output,List<String> output,int index)
	{
		List<String> temp=new ArrayList<String>();
		
		if(ch_output.isEmpty())
		{
			for(int i=0;i<output.size();i++)
			{
				ch_output.add(output.get(i));
			}
			return ch_output;
		}
		else
		{
			for(int i=0;i<ch_output.size();i++)
			{
				if(i==index)
				{
					for(int j=0;j<output.size();j++)
					{
						temp.add(ch_output.get(index)+output.get(j));
					}
				}
				else
				{
					temp.add(ch_output.get(i));
				}
			}
		}
		index=index+output.size();
		return temp;
	}
	
	
	void set(State q,List<String> output,List<Transition> tran,List<State> current_states,List<State> previous_states)
	{
		for(int i=0;i<tran.size();i++)
		{
			
			if(tran.get(i).change.equals(tran.get(i).value))
			{
				output.add(tran.get(i).value);
				current_states.add(all.get(allnames.indexOf(tran.get(i).state)));
				previous_states.add(q);
			}
			else
			{
				output.add(tran.get(i).value);
				current_states.add(all.get(allnames.indexOf(tran.get(i).state)));
				previous_states.add(q);
			}
		}	
	}
	void set0(State q,List<String> c_output,List<Transition> tran,List<State> current_states,List<State> previous_states)
	{
		for(int i=0;i<tran.size();i++)
		{
			if(tran.get(i).change.equals(tran.get(i).value))
			{
				c_output.add(tran.get(i).change);
			}
			else
			{
				c_output.add("£"+tran.get(i).change+" £");
			}
				current_states.add(all.get(allnames.indexOf(tran.get(i).state)));
				previous_states.add(q);
			
		}	
	}
	void set2(State q,List<String> output,List<Transition> tran,List<State> current_states,List<State> previous_states)
	{
		if(tran==null)
		{
			return;
		}
		for(int i=0;i<tran.size();i++)
		{
			
			if(tran.get(i).change.equals(tran.get(i).value))
			{
				output.add(tran.get(i).value);
				current_states.add(all.get(allnames.indexOf(tran.get(i).state)));
				previous_states.add(q);
			}
			else
			{
				output.add(tran.get(i).value);
				current_states.add(all.get(allnames.indexOf(tran.get(i).state)));
				previous_states.add(q);
//				output.add(tran.get(i).change);
				current_states.add(all.get(allnames.indexOf(tran.get(i).state)));
				previous_states.add(q);
			}
		}	
	}
	List<String> set3(State q,List<String> output,List<Transition> tran)
	{
		if(tran==null)
		{
			return null;
		}
		for(int i=0;i<tran.size();i++)
		{
			
			if(tran.get(i).change.equals(tran.get(i).value))
			{
				output.add(tran.get(i).value);

			}
			else
			{
				output.add(tran.get(i).value);
			//	String arr[]=tran.get(i).change.split(" ");
			//	for (int j=0;j<arr.length;j++)
			//		{
			//			output.add(arr[j]);
			//		}
			}
		}
		return output;
	}
	
	
	
	List<String> set33(State q,List<String> c_output,List<Transition> tran)
	{
		if(tran==null)
		{
			return null;
		}
		for(int i=0;i<tran.size();i++)
		{
			
			if(tran.get(i).change.equals(tran.get(i).value))
			{
				c_output.add(tran.get(i).change);

			}
			else
			{
				c_output.add("£"+tran.get(i).change +" £");
			//	String arr[]=tran.get(i).change.split(" ");
			//	for (int j=0;j<arr.length;j++)
			//		{
			//			output.add(arr[j]);
			//		}
			}
		}
		return c_output;
	}
	//----------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans t=new Trans();
		t.Add("q1","a", "q2", 0.4,"a");
		t.Add("q1","a", "q2", 0.4,"abc");
		t.Add("q1","a", "q2", 0.4,"d");
		t.Add("q2", "d", "q5", 0.0, "c");
		t.Add("q2", "b", "q3", 0.2,"b");
		t.Add("q2", "bc", "q4", 0.2,"bc");
		t.Add("q3", "c", "q4", 0.0, "c");
		t.Add("q3", "cd", "q4", 0.0, "cd");
		t.Add("q4", "d", "q5", 0.0, "d");
		t.Add("q4", "d", "q5", 0.0, "f");
		t.Add("q1","ab", "q3", 0.4,"ab");
		Trans tran=new Trans();
		
		List<String> finalstates= new ArrayList<String>();
		finalstates.add("q4");
		finalstates.add("q2");
		finalstates.add("q5");
		t.final_states(finalstates);
		List<String> outp=new ArrayList<String>();
		outp=t.upfinal("abcd");
		State q=new State();
		Transition temp=new Transition("","",0.0,"",0);
		q=t.all.get(t.allnames.indexOf("q1"));
		for(int i=0;i<q.transitions.size();i++)
		{
			temp=q.transitions.get(i);
			System.out.println(temp.value);
			System.out.println(temp.change);
		}
	/*	Scanner reader = new Scanner(System.in);  // Reading from System.in
		Boolean check=true;
		System.out.println("Enter the transition table in the following format");
		System.out.println("istate ostate input output");
		System.out.println("write end to finish");
		while(check==true)
		{
			String input = reader.nextLine();
			if(input.equals("end") || input.equals("End") || input.equals("END"))
			{
				check=false;
				break;
			}
			String arr[]= input.split(" ");
			tran.Add(arr[0], arr[2], arr[1], 0.0, arr[3]);
		}
		check=true;
		List<String> fstates= new ArrayList<String>();
		while(check==true)
		{
			System.out.println("Enter final states");
			System.out.println("write end to finish");
			String input = reader.next();
			if(input.equals("end") || input.equals("End") || input.equals("END"))
			{
				check=false;
				break;
			}
			fstates.add(input);
		}
		System.out.println("Enter word for up");
		String input=reader.next();
		tran.upfinal(input);
		*/
	}

}
