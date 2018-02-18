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
		FST.final_states(finalstates);
	}
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
	//prepositions
	void build_preposition()
	{
		FST.Add("q1", "ساتھ", "qpp", 0.0, "+pp+");
		FST.Add("q1", "قریب", "qpp", 0.0, "+pp+");
		FST.Add("q1", "تک", "qpp", 0.0, "+pp+");
		FST.Add("q2", "مگر", "qpp", 0.0, "+pp+");
		FST.Add("q2", "سے", "qpp", 0.0, "+pp+");
		FST.Add("q2", "کو", "qpp", 0.0, "+pp+");
		List<String> finalstates = new ArrayList<String>();
		finalstates.add("qpp");
	    FST.final_states(finalstates);
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
		List<String> outp = new ArrayList<String>();
		outp=u.FST.upfinal("کھاتی");
		String sentence="کھاتی کتیاں";
		String word="کھاتی";
		String[] arr = sentence.split(" ");
		List<List<String>> sen=new ArrayList<List<String>>();
		for(int i=arr.length-1;i>=0;i--)
		{
			//System.out.println(arr[i]);
			outp=u.FST.upfinal(arr[i]);
			sen.add(outp);
		}
		int i=0;
		for(int j=0;j<sen.size();j++)
		{
			System.out.println(arr[j]);
			List<String> temp=sen.get(j);
	//	for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(0));
	//	}
		}

	}

}
