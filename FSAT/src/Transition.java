
import java.*;
import java.util.Comparator;
public class Transition implements Comparable{
	public String value;
	public String state;
	public double cost;
		Transition(String v,String s,double c)
		{
			value=v;
			state=s;
			cost=c;
		}
		void Print()
		{
			System.out.println("Value = "+this.value+" State = "+this.state + " Cost = "+ this.cost);
		}
		String get(String v)
		{
			return state+String.valueOf(cost);
		}
		public Double compareTo(Transition comparestu) {
	        double compareage=((Transition)comparestu).cost;
	        return cost-compareage;
		}
		 public static Comparator<Transition> costing = new Comparator<Transition>() {

				public int compare(Transition t1, Transition t2) {

				   double transition1 = t1.cost;
				   double transition2 = t2.cost;

				   return Double.compare(transition1,transition2);

			   }};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Transition t=null;
		t=new Transition("a","b",0.2);
		t.Print();
	}
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
