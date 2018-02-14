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
		for1=possible_Transitions(q1, word);
			temp=q1.min(Character.toString(word.charAt(0)));
			if(for1==null)
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
	
	//--------------------------------------------------------------------------------------------
	List<String>  upfinal(String word)
	{
//		if (exists(word)==false)
//		{
//			
//			return null;
//		}
		State q= new State();
		int indexf=0;
		int word_size=word.length();
		List<String> output=new ArrayList<String>();		//keep record of actual word
		List<String> c_output=new ArrayList<String>();      //keep record of change output
		List<String> final_output=new ArrayList<String>();
		List<String> ch_output=new ArrayList<String>();
		int index=allnames.indexOf("q1");
		q=all.get(index);
		Transition t=new Transition ("","",0.0,"",0);
		List<Transition> temp=new ArrayList<Transition>();
		temp=possible_Transitions(q,word);
		//if first state itself does not contain any tag
		if(temp==null)
		{
			return null;
		}
		set(q,output,temp);        //keeptrack of original where i am
		set0(q,c_output,temp);     //this contains the output value 
		if(output.isEmpty())
		{
			return null;
		}
		else
		{
			final_output=update_final(final_output,output,indexf);    //without change 
			ch_output=update_chh(ch_output,c_output,indexf);		  //with change
		}
		output.clear();
		final_output=Repe1(final_output,output,temp,word,ch_output);   //we returned ch so now contains output 
		List<String> finallydone=new ArrayList<String>();
		List<String> temp1=new ArrayList<String>();
		for(int i=0;i<final_output.size();i++)
		{
			temp1=final_done(final_output.get(i));
			for(int j=0;j<temp1.size();j++)
			{
				String w=temp1.get(j);
				finallydone.add(w);
			}
			temp1.clear();
		}
		return finallydone;
		
		
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
						}
						if(index2!=remain.length())
							remain=remain.substring(index2+1,remain.length());
						else
							remain="";
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
			}
		}
		int k=s.size();
		if(k!=0)
		{
		for(int i=k-1;i>=0;i--)
		{
			output=s.pop();
			c_output=s1.pop();
			final_output=update_final(final_output,output,i);
			ch_output=update_chh(ch_output,c_output,i);
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
			for(int i=0;i<temp1.size();i++)
			{
				transition.add(temp1.get(i));
			}
			temp1.clear();
		}
		
		}
		
		return ch_output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	List<Transition> possible_Transitions(State q,String word)
	{
		List<Transition> send=new ArrayList<Transition>();
		Transition t=new Transition ("","",0.0,"",0);
		for(int i=1;i<=word.length();i++)
		{
			t=q.min(word.substring(0,i));
			if(t!=null)
			{
				if(t.value.equals(t.change))
					{send.add(t);}
				else
				{
					send.add(t);					
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
	
	
	void set(State q,List<String> output,List<Transition> tran)
	{
		for(int i=0;i<tran.size();i++)
		{
				output.add(tran.get(i).value);			
		}	
	}
	void set0(State q,List<String> c_output,List<Transition> tran)
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
			}
		}
		return c_output;
	}
	//----------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans t=new Trans();
		t.Add("q1","lark", "q2", 0.0,"+noun+");
		t.Add("q2","a", "q21", 0.0,"+male+");
		t.Add("q2","i", "q22", 0.,"+female+");
		t.Add("q2", "e", "q23", 0.0, "+male prular+");
		t.Add("q2", "yan", "q24", 0.,"+female prular+");
		t.Add("q1","ghar", "q3", 0.4,"+noun+");
		t.Add("q1","gai", "q4", 0.4,"+female verb+");
		t.Add("q1","thi", "q5", 0.4,"+female past+");
		Trans tran=new Trans();
		
		List<String> finalstates= new ArrayList<String>();
		finalstates.add("q21");
		finalstates.add("q22");
		finalstates.add("q23");
		finalstates.add("q3");
		finalstates.add("q4");
		finalstates.add("q24");
		finalstates.add("q5");
		t.final_states(finalstates);
		List<String> outp=new ArrayList<String>();
		String sentence=("larki ghar gai thi");
		String[] words = sentence.split("\\s+");
		for(int j=0;j<words.length;j++)
		{outp=t.upfinal(words[j]);
		if(outp == null)
		{
			System.out.println("does not exist");
			break;
		}
		System.out.println("------------------------------------------------------");
		for(int i=0; i<outp.size();i++)
		{
			System.out.println(outp.get(i));
		}
		System.out.println("------------------------------------------------------");
		}
		}

}
