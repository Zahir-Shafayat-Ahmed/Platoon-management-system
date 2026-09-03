package Entity;
public class Platoon{
	private String platoonName;
	private Officer[] officers = new Officer[40];
	
	public Platoon(){
		System.out.println("E-Platoon Created.");
	}
	public Platoon(String platoonName){
		System.out.println("P-Platoon Created.");
		this.platoonName = platoonName;
	}
	
	public void setPlatoonName(String platoonName){
		this.platoonName = platoonName;
	}
	public String getPlatoonName(){
		return platoonName;
	}
	
	public void insertOfficer(int index, Officer o){
		officers[index] = o;
	}
	public Officer getOfficer(int index){
		return officers[index];
	}
	
	public void removeOfficer(int index){
		officers[index] = null;
	}
	
	public void transferOfficer(int from, int to, Platoon p){
		p.insertOfficer(to, officers[from]);
		officers[from] = null;
		System.out.println("Transfer Complete...");
	}
	
	public void exchangeOfficer(int index1, int index2, Platoon p){
		Officer o = officers[index1];
		officers[index1] = p.getOfficer(index2);
		p.insertOfficer(index2,o);
		System.out.println("Exchange Complete...");
	}
	
	
	public void showPlatoon(){
		System.out.println("-------- "+platoonName+" --------");
		for(int i=0;i<officers.length;i++){
			if(officers[i] != null){
				System.out.println("-------- "+i+" --------");
				officers[i].showOfficer();
			}
		}
		System.out.println("-------------------------\n");
	}
	
	public String getPlatoon(){
		String data="";
		data += "******** "+platoonName+" **********\n";
		double cost = 0;
		int count = 0;
		for(int i=0;i<officers.length;i++){
			if(officers[i] != null){
				cost = cost + officers[i].getSalary();
				count++;
				
				data+= "~~~~~~~~ "+i+" ~~~~~~~~\n";
				data+= officers[i].getOfficer();
			}
		}
		data+="+++++++++++++++++++++++++\n";
		data+= "Total Salary Cost of Platoon : "+cost+"\n";
		data+= "Total Number of Officers : "+count+"\n";
		
		data+= "*******************************\n";
		
		return data;
	}
	public Officer[] getAllOfficer(){
		return officers;
	}
	
}