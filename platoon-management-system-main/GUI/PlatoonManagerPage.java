package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import Entity.*;
import File.*;

public class PlatoonManagerPage extends JFrame implements ActionListener{
	Font font15 = new Font("Consolas",Font.BOLD,15);
	Font titleFont = new Font("Consolas",Font.BOLD,28);

 	JButton createPlatoonBtn, updatePlatoonBtn, removePlatoonBtn, clearPlatoonBtn;
	JButton createOfficerBtn, updateOfficerBtn, removeOfficerBtn, clearOfficerBtn;
	JButton showBtn, transferBtn, saveBtn;
	JTextField platoonNoF, platoonNameF;
	JTextField oPlatoonF, nameF, milF, rankF, salaryF, searchF;
	JTextArea screen;

	Platoon[] platoons = new Platoon[5];

	public PlatoonManagerPage(){
		super("Platoon Manager");
		setSize(900,600);
		setLocation(350,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		FileIO.loadFromFile(platoons);

		JLabel title = label(10,5,250,30,"Platoon Manager");
		title.setFont(titleFont);
         
		label(10,40,180,30,"Create New Platoon");
		clearPlatoonBtn = btn(230,40,80,30,"Clear");
		 color(clearPlatoonBtn, new Color(0,128,128));
		 
		label(10,80,100,30,"Platoon No.");  
		 platoonNoF = field(120,80,150,30);
		label(10,120,100,30,"Platoon Name");
		 platoonNameF = field(120,120,150,30);
		createPlatoonBtn = btn(10,160,300,30,"Create Platoon");
		
		updatePlatoonBtn = btn(10,200,145,30,"Update"); 
		 color(updatePlatoonBtn, new Color(51,153,255));
		removePlatoonBtn = btn(165,200,145,30,"Remove"); 
		 color(removePlatoonBtn,new Color(128,0,0));

		label(10,240,180,30,"Officer Details");
		clearOfficerBtn = btn(230,240,80,30,"Clear");
		 color(clearOfficerBtn, new Color(0,128,128));
		
		label(10,280,100,30,"Platoon No.");   
		 oPlatoonF = field(120,280,150,30);
		label(10,320,100,30,"Officer Name");  
		 nameF = field(120,320,150,30);
		label(10,360,100,30,"Military No.");  
		 milF = field(120,360,150,30);
		label(10,400,100,30,"Rank");          
		 rankF = field(120,400,150,30);
		label(10,440,100,30,"Salary");         
		 salaryF = field(120,440,150,30);
		
		createOfficerBtn = btn(10,480,300,30," Assign ");
		 color(createPlatoonBtn, new Color(0,0,204));
		
		updateOfficerBtn = btn(10,520,145,30,"Update"); 
		 color(updateOfficerBtn, new Color(51,153,255));
		removeOfficerBtn = btn(165,520,145,30,"Remove"); 
		 color(removeOfficerBtn, new Color(128,0,0));

		searchF = field(340,5,60,30);
		showBtn = btn(400,5,150,30,"Show Platoon");
		color(showBtn, new Color(0,0,204));
		transferBtn = btn(580,5,135,30,"Transfer"); 
		 color(transferBtn, new Color(153,51,255));
		saveBtn = btn(720,5,120,30,"Save"); 
		 saveBtn.setBackground(new Color(102,255,102));

		screen = new JTextArea();
		screen.setFont(font15);
		screen.setEditable(false);
		JScrollPane sp = new JScrollPane(screen);
		sp.setBounds(340,40,490,510);
		add(sp);
		updateScreen();

		setVisible(true);
	}

	
	JLabel label(int x,int y,int w,int h,String t)
	{ 
	JLabel c=new JLabel(t); c.setBounds(x,y,w,h); c.setFont(font15); add(c); return c;
	}
	JTextField field(int x,int y,int w,int h)
	{ JTextField c=new JTextField(); c.setBounds(x,y,w,h); c.setFont(font15); c.addActionListener(this); add(c); return c; }
	JButton btn(int x,int y,int w,int h,String t)
	{
	JButton c=new JButton(t); c.setBounds(x,y,w,h); c.setFont(font15); c.setBackground(new Color(66,245,179)); c.addActionListener(this); add(c); return c; 
	}
	void color(JButton b, Color c)
	{ 
	b.setBackground(c); b.setForeground(Color.WHITE); 
	}
	void warn(String m)
	{ 
	JOptionPane.showMessageDialog(this,m,"Warning",JOptionPane.WARNING_MESSAGE); 
	}
	boolean confirm(String m)
	{ 
	return JOptionPane.showConfirmDialog(this,m)==JOptionPane.YES_OPTION; 
	}
	boolean empty(String... s)
	{ 
	for(String x:s) if(x.isEmpty()) 
		return true; return false; 
	}
	
	boolean bad(int n){ return n<1 || n>=platoons.length || platoons[n]==null; }

	
	int findByMilitaryNo(Platoon p, String mil){
		Officer[] os = p.getAllOfficer();
		for(int i=0;i<os.length;i++) if(os[i]!=null && os[i].getMilitaryNo().equals(mil)) return i;
		return -1;
	}
	
	int findFreeSlot(Platoon p){
		Officer[] os = p.getAllOfficer();
		for(int i=0;i<os.length;i++) if(os[i]==null) return i;
		return -1;
	}

	public void updateScreen(){
		String allPlatoonData = "";
		for(int i=0;i<platoons.length;i++){
			if(platoons[i]!=null){
				allPlatoonData += i+". "+platoons[i].getPlatoon()+"\n";
			}
		}
		screen.setText(allPlatoonData);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object s = e.getSource();
		try{
			if(s==createPlatoonBtn) platoonOp(true);
			else if(s==updatePlatoonBtn) platoonOp(false);
			else if(s==removePlatoonBtn) removePlatoon();
			else if(s==clearPlatoonBtn)
		{ 
		platoonNoF.setText(""); platoonNameF.setText(""); 
		}
			else if(s==createOfficerBtn) officerOp(true);
			else if(s==updateOfficerBtn) officerOp(false);
			else if(s==removeOfficerBtn) removeOfficer();
			else if(s==clearOfficerBtn) for(JTextField f : new JTextField[]{oPlatoonF,nameF,milF,rankF,salaryF}) f.setText("");
			else if(s==showBtn) showPlatoon();
			else if(s==transferBtn) transfer();
			else if(s==saveBtn && confirm("Save Changes?")) save();
		}
		catch(NumberFormatException ex){ warn("Numbers only in No./Salary fields."); }
	}

	void platoonOp(boolean create){
		if(empty(platoonNoF.getText(), platoonNameF.getText())) 
		    { warn("Enter Platoon No. and Name!"); return; }
		int n = Integer.parseInt(platoonNoF.getText());
		if(n<0 || n>=platoons.length) { warn("Platoon No. must be 0-"+(platoons.length-1)+".");
		return; }
		if(create && platoons[n]!=null)
			{ warn("Platoon No. Taken!"); return; }
		if(!create && platoons[n]==null) 
		    { warn("Platoon does not exist."); return; }
		if(create) platoons[n] = new Platoon(platoonNameF.getText());
		else platoons[n].setPlatoonName(platoonNameF.getText());
		updateScreen();
	}
	void removePlatoon(){
		if(empty(platoonNoF.getText())) 
		{ warn("Enter Platoon No.!"); return; }
		int n = Integer.parseInt(platoonNoF.getText());
		if(bad(n)) 
		{ warn("Platoon does not exist."); return; }
		if(confirm("Remove this platoon?")) 
		{ platoons[n]=null; updateScreen(); }
	}

	void officerOp(boolean create){
		if(empty(oPlatoonF.getText(),nameF.getText(),milF.getText(),rankF.getText(),salaryF.getText()))
			{ warn("Enter All Officer Fields!"); return; }
		int t = Integer.parseInt(oPlatoonF.getText());
		if(bad(t)) 
		{ warn("Platoon No. is invalid."); return; }
		String mil = milF.getText();
		int idx = findByMilitaryNo(platoons[t], mil);
		double sal = Double.parseDouble(salaryF.getText());
		if(create)
		{
			if(idx != -1) 
			{ warn("Military No. Already Exists in this Platoon!"); 
		      return; }
			int slot = findFreeSlot(platoons[t]);
			if(slot == -1) 
			{ warn("Platoon is Full!"); return; }
			platoons[t].insertOfficer(slot, new Officer(nameF.getText(), mil, rankF.getText(), sal));
		}
		else
		{
			if(idx == -1) { warn("Officer Not Found in this Platoon."); return; }
			Officer o = platoons[t].getOfficer(idx);
			o.setOfficerName(nameF.getText());
			o.setRank(rankF.getText());
			o.setSalary(sal);
		}
		updateScreen();
	}
	void removeOfficer()
	{
		if(empty(oPlatoonF.getText(), milF.getText())) 
		{
			warn("Enter Platoon No. and Military No.!");
	        return; 
	    }
		int t = Integer.parseInt(oPlatoonF.getText());
		if(bad(t)) 
		{ warn("Platoon does not exist."); return; }
		int idx = findByMilitaryNo(platoons[t], milF.getText());
		if(idx == -1) 
		{ warn("Officer does not exist.");
 		 return;
		 }
		if(confirm("Remove this officer?")) 
		{ 
	    platoons[t].removeOfficer(idx); updateScreen(); 
	    }
	}

	void showPlatoon(){
		String q = searchF.getText();
		if(q.isEmpty()) 
		{
		updateScreen(); return;
		}
		int n = Integer.parseInt(q);
		if(bad(n)) 
		{ 
	    warn("Platoon does not exist."); return; 
	    }
		screen.setText(platoons[n].getPlatoon());
		screen.setCaretPosition(0);
	}

	void transfer()
	{
		JTextField fp=new JTextField(), fm=new JTextField(), tp=new JTextField();
		JPanel panel = new JPanel(new GridLayout(3,2,5,5));
		String[] lbl = {"From Platoon No.","Military No.","To Platoon No."};
		JTextField[] fld = 
		{fp,fm,tp};
		for(int i=0;i<3;i++) 
	{ 
	panel.add(new JLabel(lbl[i])); panel.add(fld[i]); 
	}
		if(JOptionPane.showConfirmDialog(this,panel,"Transfer Officer",JOptionPane.OK_CANCEL_OPTION)!=JOptionPane.OK_OPTION)
			return;

		int fromP=Integer.parseInt(fp.getText()), toP=Integer.parseInt(tp.getText());
		if(bad(fromP) || bad(toP)) 
		{ 
	      warn("Source or Destination Platoon does not exist.");
		  return; 
		}
		int fromI = findByMilitaryNo(platoons[fromP], fm.getText());
		if(fromI == -1)
		{
		warn("Officer Not Found in Source Platoon."); 
		 return; 
		}
		int toI = findFreeSlot(platoons[toP]);
		if(toI == -1) 
		{ 
	    warn("Destination Platoon is Full.");
		return; 
		}
		platoons[fromP].transferOfficer(fromI, toI, platoons[toP]);
		updateScreen();
	}

	void save()
	{
		new File("./File").mkdirs();
		try(PrintWriter pw = new PrintWriter(new FileWriter("./File/platoons.txt"));
			PrintWriter ow = new PrintWriter(new FileWriter("./File/officers.txt")))
		{
			for(int i=0;i<platoons.length;i++)
			{
				if(platoons[i]==null) continue;
				pw.println(i+";"+platoons[i].getPlatoonName());
				Officer[] os = platoons[i].getAllOfficer();
				for(int j=0;j<os.length;j++)
				{
					if(os[j]==null) continue;
					Officer o = os[j];
					ow.println(i+";"+j+";"+o.getOfficerName()+";"+o.getMilitaryNo()+";"+o.getRank()+";"+o.getSalary());
				}
			}
			JOptionPane.showMessageDialog(this,"Changes Saved.");
		}
		catch(IOException ex){ JOptionPane.showMessageDialog(this,"Failed to Save: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); 
		}
	}
}