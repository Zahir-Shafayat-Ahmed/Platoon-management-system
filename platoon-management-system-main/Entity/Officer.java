package Entity;
public class Officer{
	private String officerName;
	private String militaryNo;
	private String rank;
	private double salary;
	
	public Officer(){
		System.out.println("E-Officer Created.");
	}
	public Officer(String officerName, String militaryNo, String rank, double salary){
		System.out.println("P-Officer Created.");
		setOfficerName(officerName);
		setMilitaryNo(militaryNo);
		setRank(rank);
		setSalary(salary);
	}
	
	public void setOfficerName(String officerName){
		this.officerName = officerName;
	}
	public String getOfficerName(){
		return officerName;
	}
	
	public void setMilitaryNo(String militaryNo){
		this.militaryNo = militaryNo;
	}
	public String getMilitaryNo(){ return militaryNo; }
	
	public void setRank(String rank){
		this.rank = rank;
	}
	public String getRank(){ return rank; }
	
	public void setSalary(double salary){
		this.salary = salary;
	}
	public double getSalary(){
		return salary;
	}
	
	public void showOfficer(){
		System.out.println("Officer Name: "+officerName);
		System.out.println("Military No: "+militaryNo);
		System.out.println("Rank: "+rank);
		System.out.println("Salary: "+salary+" Taka");
	}
	public String getOfficer(){
		return  "Officer Name: "+officerName+"\n"+
				"Military No: "+militaryNo+"\n"+
				"Rank: "+rank+"\n"+
				"Salary: "+salary+" Taka "+"\n";
	}
	
}