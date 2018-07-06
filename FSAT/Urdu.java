import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
public class Urdu {

	public Trans FST=new Trans();
	// boy لڑکا girl  لڑکی  boys لڑکے  girls   لڑکیاں kid(female) بچی kid(male) بچہ  kids(male) بچے kids(female) بچیاں dog کتا bitch کتی	dogs کتے bitches  کتیاں
	void build_nouns()
	{
		FST.Add("q1", "لڑک", "qn", 0.0, "+noun+");
		FST.Add("q1", "بچ", "qn", 0.0, "+noun+");
		FST.Add("q1", "کت", "qn", 0.0, "+noun+");
		FST.Add("qn", "ا", "qnms", 0.0, "+sig+male+");
		FST.Add("qn", "ی", "qnfs", 0.0, "+sig+female+");
		FST.Add("qn", "ے", "qnmp", 0.0, "+pl+male+");
		FST.Add("qn", "یاں", "qnfp", 0.0, "+pl+female+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qnms");
	    finalstates.add("qnfs");
		finalstates.add("qnfp");
		finalstates.add("qnmp");
		FST.final_states(finalstates);
	}
	//eating کھانا playing کھیلنا doing کرنا going جا
	void build_verbs()
	{
		FST.Add("q1", "کھا", "qv", 0.0, "+verb+");
		FST.Add("q1", "کھیل", "qv", 0.0, "+verb+");
		FST.Add("q1", "کر", "qv", 0.0, "+verb+");
		FST.Add("q1", "جا", "qv", 0.0, "+verb+");
		FST.Add("qv", "تا", "qvms", 0.0, "+sig+male+");
		FST.Add("qv", "تی", "qvf", 0.0, "+female+");
		FST.Add("qv", "تے", "qvmp", 0.0, "+pl+male+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qvms");
	    finalstates.add("qvf");
		finalstates.add("qvmp");
		finalstates.add("qv");
		FST.final_states(finalstates);
	}
	//universal dependicies treebank
    //beautiful خوبصورت clever ہوشیار intelligent ذہین 
	void build_adjectives_nutral()
	{
		FST.Add("q1", "خوبصورت", "qadn", 0.0, "+adjective+");
		FST.Add("q1", "ہوشیار", "qadn", 0.0, "+adjective+");
		FST.Add("q1", "ذہین", "qadn", 0.0, "+adjective+");
		
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qadn");
		FST.final_states(finalstates);
	}
	//nice اچها sweet پیارا fat موتا slim پتلا
	void build_adjectives_gender()
	{
		FST.Add("q1", "اچه", "qadg", 0.0, "+adjective+");
		FST.Add("q1", "پیار", "qadg", 0.0, "+adjective+");
		FST.Add("q1", "پیار", "qadg", 0.0, "+adjective+");
		FST.Add("q1", "پتل", "qadg", 0.0, "+adjective+");
		FST.Add("qadg", "ا", "qadms", 0.0, "+sig+male+");
		FST.Add("qadg", "ی", "qadf", 0.0, "+female+");
		FST.Add("qadg", "ے", "qadmp", 0.0, "+pl+male+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qadms");
		finalstates.add("qadf");
		finalstates.add("qadmp");
		FST.final_states(finalstates);
	}
	
	void build_adverbs_nutral()
	{
		FST.Add("q1", "خوبصورتی", "qav", 0.0, "+Adverb+");
		
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qav");
		FST.final_states(finalstates);
	}
	//prepositions
	void build_preposition()
	{
		FST.Add("q1", "ساتھ", "qpp", 0.0, "+pp+");
		FST.Add("q1", "قریب", "qpp", 0.0, "+pp+");
		FST.Add("q1", "تک", "qpp", 0.0, "+pp+");
		FST.Add("q1", "مگر", "qpp", 0.0, "+pp+");
		FST.Add("q1", "سے", "qpp", 0.0, "+pp+");
		FST.Add("q1", "کو", "qpp", 0.0, "+pp+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qpp");
	    FST.final_states(finalstates);
	}
	//present tense
	void build_present()
	{
		FST.Add("q1", "ہے", "qpt1", 0.0, "+male/female sg present+");
		FST.Add("q1", "ہیں", "qpt2", 0.0, "+male/female pl present+");
		FST.Add("q1", "ہوں", "qpt3", 0.0, "+male/female sg present+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qpt1");
		finalstates.add("qpt2");
		finalstates.add("qpt3");
	    FST.final_states(finalstates);
	}
	//future tense
	void build_future()
	{
		FST.Add("q1", "ہو", "qf", 0.0, "+future+");
		FST.Add("qf", "گا", "qf1", 0.0, "+male sg+");
		FST.Add("qf", "گی", "qf2", 0.0, "+female sg/pl +");
		FST.Add("qf", "نگے", "qf3", 0.0, "+male pl+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qf1");
	    finalstates.add("qf2");
		finalstates.add("qf3");
		FST.final_states(finalstates);
	}
	//perfect
	void build_perfect()
	{
		FST.Add("q1", "چکا", "qperf1", 0.0, "+perfect male sg+");
		FST.Add("q1", "چکی", "qperf2", 0.0, "+perfect female sg+");
		FST.Add("q2", "چکے", "qperf3", 0.0, "+perfect male pl+");
		FST.Add("q3", "چکیں", "qperf4", 0.0, "+perfect female pl+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qperf1");
	    finalstates.add("qperf2");
		finalstates.add("qperf3");
		finalstates.add("qperf4");
		FST.final_states(finalstates);
	}
	//continuous
	void build_continuous()
	{
		FST.Add("q1", "راھا", "qcon1", 0.0, "+continuous male sg+");
		FST.Add("q1", "راھی", "qcon2", 0.0, "+continuous female sg+");
		FST.Add("q2", "راھے", "qcon3", 0.0, "+continuous male pl+");
		FST.Add("q3", "راھیں", "qcon4", 0.0, "+continuous female pl+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qcon1");
	    finalstates.add("qcon2");
		finalstates.add("qcon3");
		finalstates.add("qcon4");
		FST.final_states(finalstates);
	}	
	//future tense
		void build_past()
		{
			FST.Add("q1", "تھ", "qpst", 0.0, "+past+");
			FST.Add("qpst", "ا", "qpstf1", 0.0, "+male sg+");
			FST.Add("qpst", "ی", "qpstf2", 0.0, "+female sg +");
			FST.Add("qpst", "ے", "qpstf3", 0.0, "+male pl+");
			FST.Add("qpst", "یں", "qpstf4", 0.0, "+female pl+");
			List<String> finalstates = new ArrayList<String>();
			finalstates.add("qpstf1");
		    finalstates.add("qpstf2");
			finalstates.add("qpstf3");
			finalstates.add("qpstf4");
			FST.final_states(finalstates);
		}
		void sbj_obj(List<List<String>> sen,String[] arr)
		{
			int index=arr.length;
			int check=0;
			for(int i=0;i<sen.size();i++)
			{
				List <String> temp=sen.get(i);
				index--;
				for(int j=0;j<temp.size();j++)
				{
					String a=temp.get(j);
					if(a.contains("noun") && check==0)
					{
						System.out.println("Subject : " + arr[index] + a  );
						check=1;
					}
					else if(a.equals("noun") && check==1)
					{
						System.out.println("Object : " + arr[index]  );
					}
					else
					{
						;
					}
				}
				
			}
			
		}
		
		void check_sentence(List<List<String>> sen,String subject)
		{
			int index=2;
			int check=0;
			for(int i=0;i<sen.size();i++)
			{
				List <String> temp=sen.get(i);
				index--;
				for(int j=0;j<temp.size();j++)
				{
					String a=temp.get(j);
					if(a.contains("noun") && check==0)
					{
				//		System.out.println("Subject : " + arr[index]  );
						check=1;
					}
					else if(a.equals("noun") && check==1)
					{
			//			System.out.println("Object : " + arr[index]  );
					}
					else
					{
						;
					}
				}
				
			}
		}
	//
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Urdu u=new Urdu();
		u.build_nouns();
		u.build_adjectives_gender();
		u.build_adjectives_nutral();
		u.build_preposition();
		u.build_verbs();
		u.build_adverbs_nutral();
		u.build_future();
		u.build_past();
		u.build_present();
		u.build_perfect();
		u.build_continuous();
		List<String> outp = new ArrayList<String>();
		outp=u.FST.upfinal("خوبصورتی");
		System.out.println(outp);
		System.out.println("with present past and future simple tense");
		System.out.println("noun");
		System.out.println("boy لڑکا girl لڑکی boys لڑکے girls لڑکیاں kid(female) بچی kid(male) بچہ  kids(male) بچے kids(female) بچیاں dog کتا bitch کتی	dogs کتے bitches  کتیاں");
		System.out.println("verb");
		System.out.println("eating کھانا playing کھیلنا doing کرنا going جا");
		System.out.println("adjective");		
		System.out.println("beautiful خوبصورت clever ہوشیار intelligent ذہین nice اچها sweet پیارا fat موتا slim پتلا");
		System.out.println("لڑکی خوبصورت ہے" + "girl is beautiful");
		System.out.println("لڑکیاں کھاتی ہیں" + "girls eat");
		System.out.println("لڑکیاں کھاتی تھیں" + "girls used to eat");
		System.out.println("لڑکا جا راھا ہے" + "boy is going");
		List<String> sentences=new ArrayList<String>();
		sentences.add("لڑکی خوبصورت ہے");
		sentences.add("لڑکیاں کھاتی ہیں");
		sentences.add("لڑکیاں کھاتی تھیں");
		sentences.add("لڑکا جا راھا ہے");
		for(int sent=0;sent<sentences.size();sent++)
		{String sentence=sentences.get(sent);
		
		String[] arr = sentence.split(" ");
		List<List<String>> sen=new ArrayList<List<String>>();
		for(int i=arr.length-1;i>=0;i--)
		{
			//System.out.println(arr[i]);
			outp=u.FST.upfinal(arr[i]);
			sen.add(outp);
		}
		int i=arr.length-1;
		for(int j=0;j<sen.size();j++)
		{
			System.out.println(arr[i]);
			List<String> temp=sen.get(j);
	//	for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(0));
			i--;
	//	}
		}
		
		u.sbj_obj(sen, arr);
		}
	}

}
