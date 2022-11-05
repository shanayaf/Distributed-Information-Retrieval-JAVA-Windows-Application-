import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class Divider {
	
	
	
	public static boolean containsone(File myObj1,int lines) throws FileNotFoundException
	{
		int count1 = 0;
		boolean b=false;
		
	     Scanner scanner = new Scanner(myObj1);  
	     while (scanner.hasNextLine()) {  //counting the lines in the input file
	        scanner.nextLine();  
	        count1++; 
	     }
	     if(count1>=lines)
	     {
	    	 b=true;
	    	 
	     }
	     return b;
		
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	public static boolean[] ifallhaveone(int filenum,int lines) throws FileNotFoundException
	{
		boolean []rb = null;
		int max=findmax(filenum);
		max=max-1;
		for(int i=0;i<filenum;i++)
		{
		// File myfile=new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root\\"+filenum+"th partition\\test"+max+"\\agent"+i);
			File myfile=new File("F:\\root\\"+filenum+"th partition\\test"+max+"\\agent"+i);
     	File[] listf=myfile.listFiles();
     	  rb=new boolean[listf.length];
     	//System.out.print(rb);
     	Arrays.fill(rb, false);
     	for(int j=0;j<listf.length;j++)
     	{
     		if(containsone(listf[j],lines)==true)
     		{
     			rb[j]=true;
     			
     		}
     		
     	}
		}
     	return rb;
		
     	
	}
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void createtest(int filenum)
	{
		int max=findmax(filenum);
		for(int i=0;i<filenum;i++)
		{
		
		//new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root\\"+filenum+"th partition\\test"+max+"\\agent"+i).mkdirs();
			new File("F:\\root\\"+filenum+"th partition\\test"+max+"\\agent"+i).mkdirs();
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public static void createtestroot1(int filenum)
	{
		int max=findmaxroot1(filenum);
		for(int i=0;i<filenum;i++)
		{
	
	//new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root1\\"+filenum+"th partition\\test"+max+"\\agent"+i).mkdirs();
			new File("F:\\root1\\"+filenum+"th partition\\test"+max+"\\agent"+i).mkdirs();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public static int findmaxroot1(int filenum)
	{
	int max=0;
	//File f=new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root1\\"+filenum+"th partition");
	File f=new File("F:\\root1\\"+filenum+"th partition");
	if(!f.exists())
	{
	//new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root1\\"+filenum+"th partition").mkdir();
		new File("F:\\root1\\"+filenum+"th partition").mkdir();
	}


	//File myfile=new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root1\\"+filenum+"th partition");
	File myfile=new File("F:\\root1\\"+filenum+"th partition");
	File[] listf=myfile.listFiles();
	for(int i=0;i<listf.length;i++)
	{
	String str=listf[i].toString();
	String str1=str.substring((str.length()-1));
	max=Integer.parseInt(str1);


	//System.out.print(str2);
	}
	max=max+1;

	return max;



	}
	/////////////////////////////////////////////////////////////////////////////////////
	public static int findmax(int filenum)
	{
		int max=0;
		//File f=new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root\\"+filenum+"th partition");
		File f=new File("F:\\root\\"+filenum+"th partition");
		if(!f.exists())
		{
		//new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root\\"+filenum+"th partition").mkdir();
			new File("F:\\root\\"+filenum+"th partition").mkdir();
		}
		// File myfile=new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root\\"+filenum+"th partition");
		 File myfile=new File("F:\\root\\"+filenum+"th partition");
	     	File[] listf=myfile.listFiles();
	     	for(int i=0;i<listf.length;i++)
	     	{
	     		String str=listf[i].toString();
	     		String str1=str.substring((str.length()-1));
	     		 max=Integer.parseInt(str1);
	     		 
	     		 
	     		 //System.out.print(str2);
	     	}
	     	max=max+1;
	     	
	     	return max;
	     	
	     	
		
	}
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	public static File[] createfilesroot1(int filenum) throws IOException
	{



	File[] myObj1 =new File[filenum];
	//while(Divide.getModel().isPressed())
	createtestroot1(filenum);
	int max= findmaxroot1(filenum);
	max=max-1;
	//System.out.print(max);
	for(int i=0;i<filenum;i++) {


	//myObj1[i]= new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root1\\"+filenum+"th partition\\test"+max+"\\agent"+i+"\\agent"+i+".txt");
		myObj1[i]= new File("F:\\root1\\"+filenum+"th partition\\test"+max+"\\agent"+i+"\\agent"+i+".txt");
		
	myObj1[i].createNewFile();



	}
	return myObj1;




	}
	//////////////////////////////////////////////////////////////////////////////////////
	
	public static File[] createfiles(int filenum) throws IOException
	{
		
		
		
		 File[] myObj1 =new File[filenum];
		//while(Divide.getModel().isPressed())
		 createtest(filenum);
		int max= findmax(filenum);
		max=max-1;
		//System.out.print(max);
		for(int i=0;i<filenum;i++) {
			
  	 
			//myObj1[i]= new File("C:\\Users\\shanay\\eclipse-workspace\\DIR1\\root\\"+filenum+"th partition\\test"+max+"\\agent"+i+"\\agent"+i+".txt");
			myObj1[i]= new File("F:\\root\\"+filenum+"th partition\\test"+max+"\\agent"+i+"\\agent"+i+".txt");
			
			myObj1[i].createNewFile();
  		  
  	  
  		 
  	  }
		return myObj1;
  	 
  	 
  	  
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void myfunction(int filenum,int count,int lines) throws IOException
	{
	    String inputfile ="collection.spec";
	    
	
	    	Random rand=new Random();
	    	
	    	 String [] strLine=new String[count];
	    	 BufferedReader br = new BufferedReader(new FileReader(inputfile));
	    	 File myObj1[]=createfiles(filenum);
	    	  ArrayList <Integer> rands=new ArrayList<>();
	    	  int i=0;
	    	  int j=1;
	         while(i<count) {
	        	
	        	if(i==(j*filenum))
	        	{
	        		rands.clear();
	        		j++;
	        	}
	        	 
	        	 
	        	// int randomnumber=rand.nextInt(filenum);
	        	  boolean [] boos=new boolean[filenum];
	        	  Arrays.fill(boos,true);
	        		while(!Arrays.equals(ifallhaveone(filenum,lines), boos)){
	        			 strLine[i]=br.readLine();
	        			int randomnumber=rand.nextInt(filenum);
	        			System.out.print(randomnumber);
	        			 if(!(rands.contains(randomnumber))) {
	        			
				        	  rands.add(randomnumber);
				        	  
				        	
				        	  
	        		  if(strLine!=null) {
	        			  
	        			  BufferedWriter writer = new BufferedWriter(new FileWriter(myObj1[randomnumber],true));
	        			 
		        		  writer.write(strLine[i]);
			        	    writer.newLine();
			        	   writer.close();
	        			  
	        		  }
	        			 }
	        		 else if (rands.contains(randomnumber)) {
	        				 while(rands.contains(randomnumber))
	        				 {
	        					 
	        					 randomnumber=rand.nextInt(filenum);
	        					 System.out.print(randomnumber);
	        				 }
	        				 if(!rands.contains(randomnumber)) {
	     	        			
					        	  rands.add(randomnumber);
					        	System.out.print(rands);
					        	  
		        		  if(strLine!=null) {
		        			  
		        			  BufferedWriter writer = new BufferedWriter(new FileWriter(myObj1[randomnumber],true));
		        			 
			        		  writer.write(strLine[i]);
				        	    writer.newLine();
				        	   writer.close();
		        			  
		        		  }
	        				 
	        			 }
	        		
	        		 }
	  
	        			 
	        			 i++;
	        			 break;	
	        			
	        		}
	        		while(Arrays.equals(ifallhaveone(filenum,lines),boos) )
	        		{
	        			 
	        			
	        			strLine[i]=br.readLine();
	        				
	        			int randomnumber=rand.nextInt(filenum);
	        			if(strLine!=null) {
		        			  
		        			  BufferedWriter writer = new BufferedWriter(new FileWriter(myObj1[randomnumber],true));
		        			 
			        		  writer.write(strLine[i]);
				        	    writer.newLine();
				        	   writer.close();
	        			}
	        			i++;
		        			  
		        		break;  
	        		}
	        			
	        			
	        				
	        		
	        		
	        		System.out.print(rands); 
	          }
	          
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	public static void myfunctionwithoutrules(int filenum,int count,int lines) throws IOException
	{
        
		  
	    String inputfile ="collection.spec";
	    
	
	   
	 
	    	
	    	Random rand=new Random();
	    	
	    	
	    	
	    	 String [] strLine=new String[count];
	    	 BufferedReader br = new BufferedReader(new FileReader(inputfile));
	    	 File myObj1[]=createfilesroot1(filenum);
	    	  ArrayList <Integer> rands=new ArrayList<>();
	    	  int i=0;
	    	 
	         while(i<count) {
	        	
	        	
	        	 
	        	 
	        	// int randomnumber=rand.nextInt(filenum);
	        	 
	        			 strLine[i]=br.readLine();
	        			int randomnumber=rand.nextInt(filenum);
	        			System.out.print(randomnumber);
	        			
				        	  
				        	
				        	  
	        		  if(strLine!=null) {
	        			  
	        			  BufferedWriter writer = new BufferedWriter(new FileWriter(myObj1[randomnumber],true));
	        			 
		        		  writer.write(strLine[i]);
			        	    writer.newLine();
			        	   writer.close();
	        			  
	        		  }
	        			 
	        		
	        			
	        			
	        			
	        			 
	        			 i++;
	        			
	        			
	        			
	        		}
	        		
	        			
	        				
	        		
	        		
	        		
	          }
	          
		        	 
	
	        	  
	        	       
	
	private JFrame frame;
	private JTextField Number;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Divider window = new Divider();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Divider() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Number = new JTextField();
		Number.setBounds(125, 19, 86, 20);
		frame.getContentPane().add(Number);
		Number.setColumns(10);
		
		JButton Divide = new JButton("Divide By Rule");
		Divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	int filenum = Integer.parseInt(Number.getText());
				for(int i=0;i<9;i++) {
				int filenum=2;
				
				try {
					 int count = 0;
				     String inputfile = "collection.spec";
				     File file = new File(inputfile);  
				     Scanner scanner = new Scanner(file);  
				     while (scanner.hasNextLine()) {  //counting the lines in the input file
				        scanner.nextLine();  
				        count++;  
				      } 
				     int lines=0;  
				   
				        lines= (count/filenum);  
				   
					myfunction(filenum,count,lines);
					filenum=4;
					myfunction(filenum,count,lines);
					filenum=8;
					myfunction(filenum,count,lines);
					filenum=16;
					myfunction(filenum,count,lines);
					filenum=32;
					myfunction(filenum,count,lines);
					filenum=64;
					myfunction(filenum,count,lines);
					filenum=128;
					myfunction(filenum,count,lines);
					filenum=256;
					myfunction(filenum,count,lines);
				
					
				    // createindex(count,filenum);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		Divide.setBounds(10, 70, 148, 23);
		frame.getContentPane().add(Divide);
		while(Divide.getModel().isPressed())
		{
			
		}
		
		JLabel lblNewLabel = new JLabel("Number of partition");
		lblNewLabel.setBounds(10, 11, 122, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnDivideRandom = new JButton("Divide random");
		btnDivideRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//int filenum = Integer.parseInt(Number.getText());
				
				int filenum=2;
				//for(int i=0;i<9;i++) {
				try {
					 int count = 0;
				     String inputfile = "collection.spec";
				     File file = new File(inputfile);  
				     Scanner scanner = new Scanner(file);  
				     while (scanner.hasNextLine()) {  //counting the lines in the input file
				        scanner.nextLine();  
				        count++;  
				      } 
				     int lines=0;  
				    //if((count%filenum)==0){  
				        lines= (count/filenum);  
				    // }  
				     /* else{  
				         lines=(count/filenum)+1;  
				      } */
				       // myfunctionwithoutrules(filenum,count,lines);
				        myfunctionwithoutrules(filenum,count,lines);
						filenum=4;
						myfunctionwithoutrules(filenum,count,lines);
						filenum=8;
						myfunctionwithoutrules(filenum,count,lines);
						filenum=16;
						myfunctionwithoutrules(filenum,count,lines);
						filenum=32;
						myfunctionwithoutrules(filenum,count,lines);
						filenum=64;
						myfunctionwithoutrules(filenum,count,lines);
						filenum=128;
						myfunctionwithoutrules(filenum,count,lines);
						filenum=256;
						myfunctionwithoutrules(filenum,count,lines);
					
				    // createindex(count,filenum);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//}
			}
		});
		btnDivideRandom.setBounds(10, 185, 148, 23);
		frame.getContentPane().add(btnDivideRandom);
	}
}

