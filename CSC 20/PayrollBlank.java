/*Elijah ****** Id grade myself at 90/100 due to the 10 percent markdown for the assignment being late, other than that 
I have provided comments, made sure the program runs correctly and does what its supposed to and followed the rubric as closely
as possible.  */
class PayrollBlank
{
	//Instance variables
	private String name;
	private String id;
	private double hoursWorked;
	private double hourlyRate;
	
	//constructor
	public PayrollBlank(String name, String id, double hoursWorked, double hourlyRate)
	{
		this.name = name;
		this.id = id;
		this.hoursWorked = hoursWorked;
		this.hourlyRate = hourlyRate;
	}
	
	//getters
	public String getName()
	{
		return this.name;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public double getHoursWorked()
	{
		return this.hoursWorked;
	}
	
	public double getHourlyRate()
	{
		return this.hourlyRate;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	//setters
	public void setHoursWorked(double hoursWorked)
	{
		this.hoursWorked = hoursWorked;
	}
	
	public void setHourlyRate(double hourlyRate)
	{
		this.hourlyRate = hourlyRate;
	}
	
	public double getPay()
	{
		double pay = (this.hourlyRate*this.hoursWorked);
		return pay;
	}
	
	//changes the values associated with a person into a string 
	public String toString()
	{
		String string = ("Name: "+this.name+"\nId: "+this.id+"\nHours Worked: "+this.hoursWorked+"\nHourly Rate: "+this.hourlyRate);
		return string;
	}
}

//the driver
class payrollBlankDriver
{
	public static void main(String[] args)
	{
		//Code from shell 
		System.out.println("Creating payroll objects");
		PayrollWhite p1 = new PayrollWhite("Alex Martinez" ,"123456", 25, 20);
		PayrollWhite p2 = new PayrollWhite("Ali Santos" ,"986747", 125, 45);
		PayrollWhite p3 = new PayrollWhite("Jose Busta" ,"45678", 55, 30);
		System.out.println("testing the toString method");  
		System.out.println("List of the employees");
		System.out.println(p1);
		System.out.println("Salary is : " + p1.getPay());
		System.out.println("\n*******************\n");		
		System.out.println(p2);
		System.out.println("Salary is : "+ p2.getPay());
		System.out.println("\n*******************\n");
		System.out.println(p3);
		System.out.println("Salary is : "+ p3.getPay());
		System.out.println("\n*******************\n");
		System.out.println("Testing the setter methods");
		System.out.println("The hourly pay of " + p1.getName()  + " is being chnaged");
		p1.setHoursWorked(80);
		System.out.println(p1);
		
		//Creates my objects
		PayrollWhite p4 = new PayrollWhite("Elijah", "156789", 29, 44);
		PayrollWhite p5 = new PayrollWhite("Thomas", "245631", 33, 86);
		
		//Displays my objects at default values
		System.out.println("\n*******************\n");
		System.out.println(p4);
		System.out.println("Salary is: "+p4.getPay());
		System.out.println("\n"+p5);
		System.out.println("Salary is: "+p5.getPay());
		System.out.println("\n*******************\n");
		
		//Switches hourly rates of my objects, then displays
		p4.setHourlyRate(24);
		System.out.println(p4);
		System.out.println("Salary is: "+p4.getPay());
		p5.setHourlyRate(33);
		System.out.println("\n"+p5);
		System.out.println("Salary is: "+p5.getPay());
		System.out.println("\n*******************\n");
		
		//Changes the hours worked of my objects, then displays
		p4.setHoursWorked(27);
		System.out.println(p4);
		System.out.println("Salary is: "+p4.getPay());
		p5.setHoursWorked(36);
		System.out.println("\n"+p5);
		System.out.println("Salary is: "+p5.getPay());
  }
}