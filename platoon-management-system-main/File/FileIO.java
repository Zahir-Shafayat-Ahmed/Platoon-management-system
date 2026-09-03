package File;
import Entity.*;
import java.io.*;
import java.util.Scanner;
public class FileIO{
	public static void loadFromFile(Platoon[] platoons){
		try{
			Scanner sc = new Scanner(new File("./File/platoons.txt"));
			
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String[] data = line.split(";");
				int platoonNo = Integer.parseInt(data[0]);
				String platoonName = data[1];
				platoons[platoonNo] = new Platoon(platoonName);
			}
			sc.close();
			
			sc = new Scanner(new File("./File/officers.txt"));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String[] data = line.split(";");
				int platoonNo = Integer.parseInt(data[0]);
				int oNo = Integer.parseInt(data[1]);
				String name = data[2];
				String militaryNo = data[3];
				String rank = data[4];
				double salary = Double.parseDouble(data[5]);
				
				Officer o = new Officer(name,militaryNo,rank,salary);
				platoons[platoonNo].insertOfficer(oNo,o);
			}
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
}