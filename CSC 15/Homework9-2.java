// H O M E W O R K   # 9
// C S C   1 5
// Student's Name Here:Elijah ****** 

package Main;


public class Homework9{
	public static void main(String[] args){
		// My tests (Do NOT modify this part!)
		int[] a = {24,3,12,55,6,21,35,7,33,10};
		int[] b = {72,103,3,56,87,23,9,78,5,110};
		p(divide(10,0));
		p(divide(20,5));
		p(getUnsignedIntString(-100));
		p(getUnsignedIntString(1000));
		p(Integer.toString(getMaxValue(a)));
		p(Integer.toString(getMaxValue(b)));
		p(getArrayElement(a, 3));
		p(getArrayElement(b, 10));
		p(twoSum(a, 31));
		p(twoSum(b, 113));
	}
	
	public static String getUnsignedIntString(int value){
		String ret = "";
		if(value>=0){
			ret = String.valueOf(value);
		}
		else{
			ret = String.valueOf(0);
		}
		return ret;
	}
	
	public static String getArrayElement(int[] a, int index){
		String ret = "";
		try{
			ret = String.valueOf(a[index]);
		} catch (ArrayIndexOutOfBoundsException e){
		ret="Out of bounds";
		}
		// TODO: Follow Homework hand out for method
		return ret;
	}
	
	public static int getMaxValue(int[] a){
		int max = a[0];
		for (int i =1; i<a.length;i++){
			if(a[i]>max){
				max = a[i];
			} 
		}
		
		// TODO: Follow Homework hand out for method
		return max;
	}
	
	public static String divide(int v1, int v2){
		String ret;
		int v3 = 0;
		try{v3=v1/v2;
		ret = String.valueOf(v3);
		}catch(ArithmeticException e){
			ret = "Undefined";
		}		
		
		// TODO: Follow Homework hand out for method
		return ret;
	}
	
	public static String twoSum(int[] a, int sum){
		int val1;
		int val2;
		String ret="";
		for(val1 = 0; val1<a.length;val1++){
			for(val2 = val1; val2<a.length; val2++){
				if(a[val1] + a[val2] == sum)
					ret = String.valueOf(a[val1]) +","+ String.valueOf(a[val2]);	
			}
		}
		// TODO: Follow Homework hand out for method
		return ret;
	}
	
	/* Given for easy printing */
	public static void p(String inp){
		System.out.println(inp);
	}
}