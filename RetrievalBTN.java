import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Comparator;




import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SortOrder;

import org.apache.commons.jexl2.internal.AbstractExecutor.Set;
import org.apache.commons.lang.StringUtils;
import org.terrier.evaluation.TrecEvalEvaluation;
import org.terrier.evaluation.TRECQrelsInMemory;
import org.terrier.evaluation.TerrierEvaluation;
import org.terrier.evaluation.NamedPageEvaluation;
import org.terrier.evaluation.AdhocEvaluation;
import org.terrier.evaluation.AdhocFullQueryEvaluation;
import org.terrier.evaluation.Evaluation;

import org.terrier.matching.ResultSet;
import org.terrier.querying.Manager;
import org.terrier.querying.SearchRequest;
import org.terrier.structures.Index;
import org.terrier.structures.MetaIndex;
import org.terrier.utility.ApplicationSetup;

import gnu.trove.THashSet;

import org.terrier.matching.models.TF_IDF;

public class RetrievalBTN {
	
	
	public static ArrayList<String> findtopics() throws FileNotFoundException
	{
		
	
		
		
		ArrayList<String> topic = new ArrayList<String>();
		
		     // File myObj = new File("F:\\root2\\topics.301-350");
		//File myObj = new File("F:\\root2\\T\\topics.301-350");
		      File myObj = new File("F:\\root2\\topics.301-350");
		
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		       
		        String title =StringUtils.substringAfter(data,"<title>");
		        if(title!="")
		        {
		        topic.add(title);
		        }
		        
		        
		      
		        
		        }
		     /* for(String a:topic)
		       {
		    	   System.out.print(a);
		    	   
		       }*/
		     
		      topic.removeAll(Arrays.asList("", null));
		      //System.out.print(topic);
		      
		      myReader.close();
		      return topic;
		     
	}
	
	public ArrayList<Double> retievalDFIZ(int s,int p) throws IOException 
	{

		
		int topicnumber=300;
		int queryn=0;
		
		
		ArrayList<String> Topics=new ArrayList<String>();
		
		
		ResultSet results = null;
		
		ArrayList<Double> scores=new ArrayList<>(p);
		
		
		String ar=null;
		
		
		
		Topics=findtopics();
		 for(String a1:Topics)
		 {
			 List<ArrayList<String>> filesinfominmax=new ArrayList<ArrayList<String>>();
			//List<ArrayList<String>> filesinfo2=new ArrayList<ArrayList<String>>();
			 List<ArrayList<String>> filesinfosum=new ArrayList<ArrayList<String>>();
			 List<ArrayList<String>> mintowrite=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> sumtowrite=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> filesinfom=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> filesinfos=new ArrayList<ArrayList<String>>();
				 ArrayList<String> ars=new ArrayList<String>();
				 ArrayList<Double> forth=new ArrayList<Double>();
			
			
			 
			
			
			 results=null;
			
			
			 ar=null;
			
			 scores.clear();
			 topicnumber++;
			 queryn++;
			 
			
	 for(int j=0;j<p;j++)
     {
		 forth.clear();
		 
		 List sc=null;
		 List sumlist=null;
		
		 results=null;
		filesinfom.clear();
		filesinfos.clear();
		 
		 ar=null;
		
		 scores.clear();
		 
			
		 
		//	System.setProperty("terrier.home", "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index");
		 System.setProperty("terrier.home","C:\\terrier-core-4.2");
		 
		String pathToIndex =  "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index";
		ApplicationSetup.setProperty("querying.postfilters.controls", "decorate:org.terrier.querying.SimpleDecorate");
		ApplicationSetup.setProperty("querying.postfilters.order", "org.terrier.querying.SimpleDecorate");
		
		/* ApplicationSetup.setProperty("querying.processes", "terrierql:TerrierQLParser,"
			        + "parsecontrols:TerrierQLToControls,"
			        + "parseql:TerrierQLToMatchingQueryTerms,"
			        + "matchopql:MatchingOpQLParser,"
			        + "applypipeline:ApplyTermPipeline,"
			        + "localmatching:LocalManager$ApplyLocalMatching,"
			        + "filters:LocalManager$PostFilterProcess");*/
		// ApplicationSetup.setProperty("querying.postfilters", "decorate:org.terrier.querying.SimpleDecorate");
		 Index diskIndex =Index.createIndex(pathToIndex, "data");
		 
		 
		
		
			 
		
			 Manager queryingManager = new Manager(diskIndex);
		 
		 SearchRequest srq = queryingManager.newSearchRequestFromQuery(a1);
		 srq.addMatchingModel("Matching","DFIZ");
		 srq.setControl("decorate", "on");
		 queryingManager.runSearchRequest(srq);
		  results = srq.getResultSet();
		  String[] docnos = null;
		  final MetaIndex metaIndex = diskIndex.getMetaIndex();
		  
		  String r=null;
		 for (int i =0; i< results.getResultSize(); i++) {
			int sz=results.getResultSize();
	         int docid = results.getDocids()[i];
	         double score = results.getScores()[i];
	         docnos = metaIndex.getItems("DOCNO", results.getDocids());
	         scores.add(score);
	        r =results.getMetaItem("DOCNO", docid);
	       // final String queryIdExpanded =  srq.getQueryID() + " Q";
	       //  String[] doci=results.getMetaItems("filename");
	         
	       
	         //data[j][i] = score;
	         
	        
	        
	        	 
	        	// System.out.println(" Rank "+i+": "+docid+" "+"The score is: "+score);
	        	 new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ").mkdir();
	         File resultfile = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\results"+j+"-"+topicnumber+".res");
	        
	         
	         try {
				resultfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         BufferedWriter writer1 = null;
			try {
				writer1 = new BufferedWriter(new FileWriter(resultfile,true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 		 ar=topicnumber+" "+"Q"+queryn+" "+results.getMetaItem("docno", i)+" "+i+" "+scores.get(i)+" "+"DFIZ";
	 		 
	 		ArrayList <String> myListm = new ArrayList<String>(Arrays.asList(ar.split(" ")));
	 		ArrayList <String> myLists = new ArrayList<String>(Arrays.asList(ar.split(" ")));
	 		
	 		filesinfom.add(myListm);
	 		filesinfos.add(myLists);
	 		
	 		
	 		
	 		
	 		//System.out.print(filesinfo);
	 		
	 		 ars.add(ar);
	 		 
	 		 
	 		 
	 		
			
	 		
	 		try {
				writer1.write(ar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
		 }
		 int flag=0;
		 
		 for(int h=0;h<filesinfom.size();h++)
		 {
		  double ss=(Double.parseDouble(filesinfom.get(h).get(4)));
		  forth.add(ss);
		  
		 }
		 
		 sc=MinMax(s,p,forth,results.getResultSize(),flag,ars);
		 sumlist=Sum(s,p,forth,results.getResultSize(),flag,ars);
		 for(int i=0;i<filesinfom.size();i++)
		 { 
			 filesinfom.get(i).remove(4);
			filesinfom.get(i).add(4,sc.get(i).toString());
			
			 
		  
		 }
		 filesinfominmax.addAll(filesinfom);
		 
		 
		 /////////////////////////////////////
		
		 for(int i=0;i<filesinfos.size();i++)
		 { 
			 filesinfos.get(i).remove(4);
			filesinfos.get(i).add(4,sumlist.get(i).toString());
			
			 
		  
		 }
		 filesinfosum.addAll(filesinfos);
		  
		  
		
		
     }
	  //  System.out.print(filesinfo);     
		 
	 
	 
	 
	
	 
	 
	 
	 Collections.sort(filesinfominmax, new Comparator<ArrayList<String>>() {    
	        @Override
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	        	
	        	Double i1=Double.parseDouble(o1.get(4));
	        	Double i2=Double.parseDouble(o2.get(4));
	            return i2.compareTo(i1);
	        }               
	});
	 
	 ///////////////////////////////////sort filesinfosum 
	 
	 Collections.sort(filesinfosum, new Comparator<ArrayList<String>>() {    
	        @Override
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	        	
	        	Double i1=Double.parseDouble(o1.get(4));
	        	Double i2=Double.parseDouble(o2.get(4));
	            return i2.compareTo(i1);
	        }               
	});
	
	if(filesinfominmax.size()>=1000)
	{	
		mintowrite=filesinfominmax.subList(0, 1000);
	}
	else if(filesinfominmax.size()<1000)
	{
		mintowrite=filesinfominmax;
	}
	
	////////////////////////////////get first 1000 for sum too
	
	if(filesinfosum.size()>=1000)
	{	
		sumtowrite=filesinfosum.subList(0, 1000);
	}
	else if(filesinfosum.size()<1000)
	{
		sumtowrite=filesinfosum;
	}
		
	 

//sumlist=Sum(s,p,forth,results.getResultSize(),flag,ars);


//System.out.print(sc);






/////////////////////////for sumlist


//System.out.print(filesinfo1);

//////append for minmax



File normal=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized");
if(!normal.exists())
{
	normal.mkdir();
}


	
	

	File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\MinMax-"+topicnumber+"-.res");
	File myObj2 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\Sum-"+topicnumber+"-.res");
	
	myObj1.createNewFile();
	myObj2.createNewFile();

for(int m=0;m<mintowrite.size();m++)	
{
	BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\MinMax-"+topicnumber+"-.res",true));
for(String bf: mintowrite.get(m))//for each row
{
	


writer.write(bf);//save the string representation of the board
writer.write(" ");


}
writer.newLine();
writer.close();
}

//////////////////for sum write to file


for(int m=0;m<sumtowrite.size();m++)	
{
	BufferedWriter writer1 = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\sum-"+topicnumber+"-.res",true));
for(String bf: sumtowrite.get(m))//for each row
{
	


writer1.write(bf);//save the string representation of the board
writer1.write(" ");


}
writer1.newLine();
writer1.close();
}


/*for(String bf: replacedone2)//for each row
{
	BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum-"+topicnumber+"-.res",true));


writer.write(bf);//save the string representation of the board
writer.newLine();
writer.close();
}*/

////////////////////////////////////////////////////////////Write the results to Sum.res


		
	
	
	scores.clear();
		 
	 filesinfom.clear();
	
		
		 }
		 
		 ///////////////////////////////////Combine all to one
		 
		 for(int topicnumber1=301;topicnumber1<=350;topicnumber1++)
			{	
			File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\MinMax-"+topicnumber1+"-.res");
			File myObj2=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\MinMax-301-350"+s+".res");
			myObj2.createNewFile();
		      Scanner myReader = new Scanner(myObj1);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(myObj2,true));
				 
	  		  writer.write(data);
	      	    writer.newLine();
	      	   writer.close();
		        
			
			}
			
		}
		 
		 
		 
		 for(int topicnumber1=301;topicnumber1<=350;topicnumber1++)
			{	
			File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\Sum-"+topicnumber1+"-.res");
			File myObj2=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\Sum-301-350"+s+".res");
			myObj2.createNewFile();
		      Scanner myReader = new Scanner(myObj1);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(myObj2,true));
				 
	  		  writer.write(data);
	      	    writer.newLine();
	      	   writer.close();
		        
			
			}
			
		}
		
		return scores;
		
	
		
	}
	
	
	public ArrayList<Double> retievalIFB2(int s,int p) throws IOException 
	{
		
		int topicnumber=300;
		int queryn=0;
		
		
		ArrayList<String> Topics=new ArrayList<String>();
		
		
		ResultSet results = null;
		
		ArrayList<Double> scores=new ArrayList<>(p);
		
		
		String ar=null;
		
		
		
		Topics=findtopics();
		 for(String a1:Topics)
		 {
			 List<ArrayList<String>> filesinfominmax=new ArrayList<ArrayList<String>>();
			//List<ArrayList<String>> filesinfo2=new ArrayList<ArrayList<String>>();
			 List<ArrayList<String>> filesinfosum=new ArrayList<ArrayList<String>>();
			 List<ArrayList<String>> mintowrite=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> sumtowrite=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> filesinfom=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> filesinfos=new ArrayList<ArrayList<String>>();
				 ArrayList<String> ars=new ArrayList<String>();
				 ArrayList<Double> forth=new ArrayList<Double>();
			
			
			 
			
			
			 results=null;
			
			
			 ar=null;
			
			 scores.clear();
			 topicnumber++;
			 queryn++;
			 
			
	 for(int j=0;j<p;j++)
     {
		 forth.clear();
		 
		 List sc=null;
		 List sumlist=null;
		
		 results=null;
		filesinfom.clear();
		filesinfos.clear();
		 
		 ar=null;
		
		 scores.clear();
		 
			
		 
		//	System.setProperty("terrier.home", "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index");
		 System.setProperty("terrier.home","C:\\terrier-core-4.2");
		 
		String pathToIndex =  "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index";
		ApplicationSetup.setProperty("querying.postfilters.controls", "decorate:org.terrier.querying.SimpleDecorate");
		ApplicationSetup.setProperty("querying.postfilters.order", "org.terrier.querying.SimpleDecorate");
		
		/* ApplicationSetup.setProperty("querying.processes", "terrierql:TerrierQLParser,"
			        + "parsecontrols:TerrierQLToControls,"
			        + "parseql:TerrierQLToMatchingQueryTerms,"
			        + "matchopql:MatchingOpQLParser,"
			        + "applypipeline:ApplyTermPipeline,"
			        + "localmatching:LocalManager$ApplyLocalMatching,"
			        + "filters:LocalManager$PostFilterProcess");*/
		// ApplicationSetup.setProperty("querying.postfilters", "decorate:org.terrier.querying.SimpleDecorate");
		 Index diskIndex =Index.createIndex(pathToIndex, "data");
		 
		 
		
		
			 
		
			 Manager queryingManager = new Manager(diskIndex);
		 
		 SearchRequest srq = queryingManager.newSearchRequestFromQuery(a1);
		 srq.addMatchingModel("Matching","IFB2");
		 srq.setControl("decorate", "on");
		 queryingManager.runSearchRequest(srq);
		  results = srq.getResultSet();
		  String[] docnos = null;
		  final MetaIndex metaIndex = diskIndex.getMetaIndex();
		  
		  String r=null;
		 for (int i =0; i< results.getResultSize(); i++) {
			int sz=results.getResultSize();
	         int docid = results.getDocids()[i];
	         double score = results.getScores()[i];
	         docnos = metaIndex.getItems("DOCNO", results.getDocids());
	         scores.add(score);
	        r =results.getMetaItem("DOCNO", docid);
	       // final String queryIdExpanded =  srq.getQueryID() + " Q";
	       //  String[] doci=results.getMetaItems("filename");
	         
	       
	         //data[j][i] = score;
	         
	        
	        
	        	 
	        	// System.out.println(" Rank "+i+": "+docid+" "+"The score is: "+score);
	        	 new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2").mkdir();
	         File resultfile = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\results"+j+"-"+topicnumber+".res");
	        
	         
	         try {
				resultfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         BufferedWriter writer1 = null;
			try {
				writer1 = new BufferedWriter(new FileWriter(resultfile,true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 		 ar=topicnumber+" "+"Q"+queryn+" "+results.getMetaItem("docno", i)+" "+i+" "+scores.get(i)+" "+"IFB2";
	 		 
	 		ArrayList <String> myListm = new ArrayList<String>(Arrays.asList(ar.split(" ")));
	 		ArrayList <String> myLists = new ArrayList<String>(Arrays.asList(ar.split(" ")));
	 		
	 		filesinfom.add(myListm);
	 		filesinfos.add(myLists);
	 		
	 		
	 		
	 		
	 		//System.out.print(filesinfo);
	 		
	 		 ars.add(ar);
	 		 
	 		 
	 		 
	 		
			
	 		
	 		try {
				writer1.write(ar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
		 }
		 int flag=0;
		 
		 for(int h=0;h<filesinfom.size();h++)
		 {
		  double ss=(Double.parseDouble(filesinfom.get(h).get(4)));
		  forth.add(ss);
		  
		 }
		 
		 sc=MinMax(s,p,forth,results.getResultSize(),flag,ars);
		 sumlist=Sum(s,p,forth,results.getResultSize(),flag,ars);
		 for(int i=0;i<filesinfom.size();i++)
		 { 
			 filesinfom.get(i).remove(4);
			filesinfom.get(i).add(4,sc.get(i).toString());
			
			 
		  
		 }
		 filesinfominmax.addAll(filesinfom);
		 
		 
		 /////////////////////////////////////
		
		 for(int i=0;i<filesinfos.size();i++)
		 { 
			 filesinfos.get(i).remove(4);
			filesinfos.get(i).add(4,sumlist.get(i).toString());
			
			 
		  
		 }
		 filesinfosum.addAll(filesinfos);
		  
		  
		
		
     }
	  //  System.out.print(filesinfo);     
		 
	 
	 
	 
	
	 
	 
	 
	 Collections.sort(filesinfominmax, new Comparator<ArrayList<String>>() {    
	        @Override
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	        	
	        	Double i1=Double.parseDouble(o1.get(4));
	        	Double i2=Double.parseDouble(o2.get(4));
	            return i2.compareTo(i1);
	        }               
	});
	 
	 ///////////////////////////////////sort filesinfosum 
	 
	 Collections.sort(filesinfosum, new Comparator<ArrayList<String>>() {    
	        @Override
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	        	
	        	Double i1=Double.parseDouble(o1.get(4));
	        	Double i2=Double.parseDouble(o2.get(4));
	            return i2.compareTo(i1);
	        }               
	});
	
	if(filesinfominmax.size()>=1000)
	{	
		mintowrite=filesinfominmax.subList(0, 1000);
	}
	else if(filesinfominmax.size()<1000)
	{
		mintowrite=filesinfominmax;
	}
	
	////////////////////////////////get first 1000 for sum too
	
	if(filesinfosum.size()>=1000)
	{	
		sumtowrite=filesinfosum.subList(0, 1000);
	}
	else if(filesinfosum.size()<1000)
	{
		sumtowrite=filesinfosum;
	}
		
	 

//sumlist=Sum(s,p,forth,results.getResultSize(),flag,ars);


//System.out.print(sc);






/////////////////////////for sumlist


//System.out.print(filesinfo1);

//////append for minmax



File normal=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized");
if(!normal.exists())
{
	normal.mkdir();
}


	
	

	File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\MinMax-"+topicnumber+"-.res");
	File myObj2 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\Sum-"+topicnumber+"-.res");
	
	myObj1.createNewFile();
	myObj2.createNewFile();

for(int m=0;m<mintowrite.size();m++)	
{
	BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\MinMax-"+topicnumber+"-.res",true));
for(String bf: mintowrite.get(m))//for each row
{
	


writer.write(bf);//save the string representation of the board
writer.write(" ");


}
writer.newLine();
writer.close();
}

//////////////////for sum write to file


for(int m=0;m<sumtowrite.size();m++)	
{
	BufferedWriter writer1 = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\sum-"+topicnumber+"-.res",true));
for(String bf: sumtowrite.get(m))//for each row
{
	


writer1.write(bf);//save the string representation of the board
writer1.write(" ");


}
writer1.newLine();
writer1.close();
}


/*for(String bf: replacedone2)//for each row
{
	BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum-"+topicnumber+"-.res",true));


writer.write(bf);//save the string representation of the board
writer.newLine();
writer.close();
}*/

////////////////////////////////////////////////////////////Write the results to Sum.res


		
	
	
	scores.clear();
		 
	 filesinfom.clear();
	
		
		 }
		 
		 ///////////////////////////////////Combine all to one
		 
		 for(int topicnumber1=301;topicnumber1<=350;topicnumber1++)
			{	
			File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\MinMax-"+topicnumber1+"-.res");
			File myObj2=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\MinMax-301-350"+s+".res");
			myObj2.createNewFile();
		      Scanner myReader = new Scanner(myObj1);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(myObj2,true));
				 
	  		  writer.write(data);
	      	    writer.newLine();
	      	   writer.close();
		        
			
			}
			
		}
		 
		 
		 
		 for(int topicnumber1=301;topicnumber1<=350;topicnumber1++)
			{	
			File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\Sum-"+topicnumber1+"-.res");
			File myObj2=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\Sum-301-350"+s+".res");
			myObj2.createNewFile();
		      Scanner myReader = new Scanner(myObj1);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(myObj2,true));
				 
	  		  writer.write(data);
	      	    writer.newLine();
	      	   writer.close();
		        
			
			}
			
		}
		
		return scores;
		
	}
	
	
	
	public ArrayList<Double> retievalTDIDF(int s,int p) throws IOException 
	{
		
		int topicnumber=300;
		int queryn=0;
		
		
		ArrayList<String> Topics=new ArrayList<String>();
		
		
		ResultSet results = null;
		
		ArrayList<Double> scores=new ArrayList<>(p);
		
		
		String ar=null;
		
		
		
		Topics=findtopics();
		 for(String a1:Topics)
		 {
			 List<ArrayList<String>> filesinfominmax=new ArrayList<ArrayList<String>>();
			//List<ArrayList<String>> filesinfo2=new ArrayList<ArrayList<String>>();
			 List<ArrayList<String>> filesinfosum=new ArrayList<ArrayList<String>>();
			 List<ArrayList<String>> mintowrite=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> sumtowrite=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> filesinfom=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> filesinfos=new ArrayList<ArrayList<String>>();
				 ArrayList<String> ars=new ArrayList<String>();
				 ArrayList<Double> forth=new ArrayList<Double>();
			
			
			 
			
			
			 results=null;
			
			
			 ar=null;
			
			 scores.clear();
			 topicnumber++;
			 queryn++;
			 
			
	 for(int j=0;j<p;j++)
     {
		 forth.clear();
		 
		 List sc=null;
		 List sumlist=null;
		
		 results=null;
		filesinfom.clear();
		filesinfos.clear();
		 
		 ar=null;
		
		 scores.clear();
		 
			
		 
		//	System.setProperty("terrier.home", "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index");
		 System.setProperty("terrier.home","C:\\terrier-core-4.2");
		 
		String pathToIndex =  "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index";
		ApplicationSetup.setProperty("querying.postfilters.controls", "decorate:org.terrier.querying.SimpleDecorate");
		ApplicationSetup.setProperty("querying.postfilters.order", "org.terrier.querying.SimpleDecorate");
		
		/* ApplicationSetup.setProperty("querying.processes", "terrierql:TerrierQLParser,"
			        + "parsecontrols:TerrierQLToControls,"
			        + "parseql:TerrierQLToMatchingQueryTerms,"
			        + "matchopql:MatchingOpQLParser,"
			        + "applypipeline:ApplyTermPipeline,"
			        + "localmatching:LocalManager$ApplyLocalMatching,"
			        + "filters:LocalManager$PostFilterProcess");*/
		// ApplicationSetup.setProperty("querying.postfilters", "decorate:org.terrier.querying.SimpleDecorate");
		 Index diskIndex =Index.createIndex(pathToIndex, "data");
		 
		 
		
		
			 
		
			 Manager queryingManager = new Manager(diskIndex);
		 
		 SearchRequest srq = queryingManager.newSearchRequestFromQuery(a1);
		 srq.addMatchingModel("Matching","TF_IDF");
		 srq.setControl("decorate", "on");
		 queryingManager.runSearchRequest(srq);
		  results = srq.getResultSet();
		  String[] docnos = null;
		  final MetaIndex metaIndex = diskIndex.getMetaIndex();
		  
		  String r=null;
		 for (int i =0; i< results.getResultSize(); i++) {
			int sz=results.getResultSize();
	         int docid = results.getDocids()[i];
	         double score = results.getScores()[i];
	         docnos = metaIndex.getItems("DOCNO", results.getDocids());
	         scores.add(score);
	        r =results.getMetaItem("DOCNO", docid);
	       // final String queryIdExpanded =  srq.getQueryID() + " Q";
	       //  String[] doci=results.getMetaItems("filename");
	         
	       
	         //data[j][i] = score;
	         
	        
	        
	        	 
	        	// System.out.println(" Rank "+i+": "+docid+" "+"The score is: "+score);
	        	 new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF").mkdir();
	         File resultfile = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\results"+j+"-"+topicnumber+".res");
	        
	         
	         try {
				resultfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         BufferedWriter writer1 = null;
			try {
				writer1 = new BufferedWriter(new FileWriter(resultfile,true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 		 ar=topicnumber+" "+"Q"+queryn+" "+results.getMetaItem("docno", i)+" "+i+" "+scores.get(i)+" "+"TFIDF";
	 		 
	 		ArrayList <String> myListm = new ArrayList<String>(Arrays.asList(ar.split(" ")));
	 		ArrayList <String> myLists = new ArrayList<String>(Arrays.asList(ar.split(" ")));
	 		
	 		filesinfom.add(myListm);
	 		filesinfos.add(myLists);
	 		
	 		
	 		
	 		
	 		//System.out.print(filesinfo);
	 		
	 		 ars.add(ar);
	 		 
	 		 
	 		 
	 		
			
	 		
	 		try {
				writer1.write(ar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
		 }
		 int flag=0;
		 
		 for(int h=0;h<filesinfom.size();h++)
		 {
		  double ss=(Double.parseDouble(filesinfom.get(h).get(4)));
		  forth.add(ss);
		  
		 }
		 
		 sc=MinMax(s,p,forth,results.getResultSize(),flag,ars);
		 sumlist=Sum(s,p,forth,results.getResultSize(),flag,ars);
		 for(int i=0;i<filesinfom.size();i++)
		 { 
			 filesinfom.get(i).remove(4);
			filesinfom.get(i).add(4,sc.get(i).toString());
			
			 
		  
		 }
		 filesinfominmax.addAll(filesinfom);
		 
		 
		 /////////////////////////////////////
		
		 for(int i=0;i<filesinfos.size();i++)
		 { 
			 filesinfos.get(i).remove(4);
			filesinfos.get(i).add(4,sumlist.get(i).toString());
			
			 
		  
		 }
		 filesinfosum.addAll(filesinfos);
		  
		  
		
		
     }
	  //  System.out.print(filesinfo);     
		 
	 
	 
	 
	
	 
	 
	 
	 Collections.sort(filesinfominmax, new Comparator<ArrayList<String>>() {    
	        @Override
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	        	
	        	Double i1=Double.parseDouble(o1.get(4));
	        	Double i2=Double.parseDouble(o2.get(4));
	            return i2.compareTo(i1);
	        }               
	});
	 
	 ///////////////////////////////////sort filesinfosum 
	 
	 Collections.sort(filesinfosum, new Comparator<ArrayList<String>>() {    
	        @Override
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	        	
	        	Double i1=Double.parseDouble(o1.get(4));
	        	Double i2=Double.parseDouble(o2.get(4));
	            return i2.compareTo(i1);
	        }               
	});
	
	if(filesinfominmax.size()>=1000)
	{	
		mintowrite=filesinfominmax.subList(0, 1000);
	}
	else if(filesinfominmax.size()<1000)
	{
		mintowrite=filesinfominmax;
	}
	
	////////////////////////////////get first 1000 for sum too
	
	if(filesinfosum.size()>=1000)
	{	
		sumtowrite=filesinfosum.subList(0, 1000);
	}
	else if(filesinfosum.size()<1000)
	{
		sumtowrite=filesinfosum;
	}
		
	 

//sumlist=Sum(s,p,forth,results.getResultSize(),flag,ars);


//System.out.print(sc);






/////////////////////////for sumlist


//System.out.print(filesinfo1);

//////append for minmax



File normal=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized");
if(!normal.exists())
{
	normal.mkdir();
}


	
	

	File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\MinMax-"+topicnumber+"-.res");
	File myObj2 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum-"+topicnumber+"-.res");
	
	myObj1.createNewFile();
	myObj2.createNewFile();

for(int m=0;m<mintowrite.size();m++)	
{
	BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\MinMax-"+topicnumber+"-.res",true));
for(String bf: mintowrite.get(m))//for each row
{
	


writer.write(bf);//save the string representation of the board
writer.write(" ");


}
writer.newLine();
writer.close();
}

//////////////////for sum write to file


for(int m=0;m<sumtowrite.size();m++)	
{
	BufferedWriter writer1 = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\sum-"+topicnumber+"-.res",true));
for(String bf: sumtowrite.get(m))//for each row
{
	


writer1.write(bf);//save the string representation of the board
writer1.write(" ");


}
writer1.newLine();
writer1.close();
}


/*for(String bf: replacedone2)//for each row
{
	BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum-"+topicnumber+"-.res",true));


writer.write(bf);//save the string representation of the board
writer.newLine();
writer.close();
}*/

////////////////////////////////////////////////////////////Write the results to Sum.res


		
	
	
	scores.clear();
		 
	 filesinfom.clear();
	
		
		 }
		 
		 ///////////////////////////////////Combine all to one
		 
		 for(int topicnumber1=301;topicnumber1<=350;topicnumber1++)
			{	
			File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\MinMax-"+topicnumber1+"-.res");
			File myObj2=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\MinMax-301-350"+s+".res");
			myObj2.createNewFile();
		      Scanner myReader = new Scanner(myObj1);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(myObj2,true));
				 
	  		  writer.write(data);
	      	    writer.newLine();
	      	   writer.close();
		        
			
			}
			
		}
		 
		 
		 
		 for(int topicnumber1=301;topicnumber1<=350;topicnumber1++)
			{	
			File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum-"+topicnumber1+"-.res");
			File myObj2=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum-301-350"+s+".res");
			myObj2.createNewFile();
		      Scanner myReader = new Scanner(myObj1);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(myObj2,true));
				 
	  		  writer.write(data);
	      	    writer.newLine();
	      	   writer.close();
		        
			
			}
			
		}
		
		return scores;
	}
	
	
	//////////////////////////////////////////////////////////////////Evaluate function
	
	public void BM25evaluation(int p,int s) throws IOException
	{
		int f=0;
		//create(p,s,f);
		
		new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\EvaluationResults").mkdir();
	
			File EvalResultall = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\EvaluationResults\\MinMax.eval");
			EvalResultall.createNewFile();
			
			
			
			
			String qrels ="F:\\root2\\qrels.trec6.adhoc.part15";
			String ResultFile ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\MinMax-301-350"+s+".res";
			
			String EvalResult ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\EvaluationResults\\MinMax.eval";
			PrintWriter writer=new PrintWriter(System.out);
			
			
			

			TrecEvalEvaluation trecEval = new TrecEvalEvaluation(qrels);
			trecEval.evaluate(ResultFile);
			trecEval.writeEvaluationResult();
			//System.out.print(EvalResult);
			trecEval.writeEvaluationResult(EvalResult);
			//trecEval.writeEvaluationResult(writer);
			
			
			///////////////////////////////////////evaluate sum results
			File EvalResultallsum = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\EvaluationResults\\Sum.eval");
			EvalResultallsum.createNewFile();
			
			
			
			
			String qrelssum ="F:\\root2\\qrels.trec6.adhoc.part15";
			String ResultFilesum ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\Sum-301-350"+s+".res";
			
			String EvalResultsum ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\EvaluationResults\\Sum.eval";
		//	PrintWriter writersum=new PrintWriter(System.out);
			
			
			

			TrecEvalEvaluation trecEvalsum = new TrecEvalEvaluation(qrels);
			trecEval.evaluate(ResultFilesum);
			trecEval.writeEvaluationResult();
			//System.out.print(EvalResult);
			trecEval.writeEvaluationResult(EvalResultsum);
			
			
		
	

				
		
				
		
	}
	public void DFIZevaluation(int p,int s) throws IOException
	{

		new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\EvaluationResults").mkdir();
		File EvalResultall = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\EvaluationResults\\MinMax.eval");
		EvalResultall.createNewFile();
	//	int f=1;
		//create(p,s,f);
		//for(int i=301;i<=350;i++)
			
		//{
		
		String qrels ="F:\\root2\\qrels.trec6.adhoc.part15";
		String ResultFile ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\MinMax-301-350"+s+".res";

		String EvalResult ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\EvaluationResults\\MinMax.eval";
		




		TrecEvalEvaluation trecEval = new TrecEvalEvaluation(qrels);
		trecEval.evaluate(ResultFile);
		trecEval.writeEvaluationResult();
		//System.out.print(EvalResult);
		trecEval.writeEvaluationResult(EvalResult);
	//	}
		

		///////////////////////////////////////evaluate sum results
		
			File EvalResultallsum = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\EvaluationResults\\Sum.eval");
			EvalResultallsum.createNewFile();
		
		String qrels2 ="F:\\root2\\qrels.trec6.adhoc.part15";
		String ResultFile2 ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\normalized\\Sum-301-350"+s+".res";

		String EvalResult2 ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolDFIZ\\EvaluationResults\\Sum.eval";




		TrecEvalEvaluation trecEval2 = new TrecEvalEvaluation(qrels2);
		trecEval2.evaluate(ResultFile2);
		trecEval2.writeEvaluationResult();
		trecEval2.writeEvaluationResult(EvalResult2);
		
	
		
	}
	public void IFB2evaluation(int p,int s) throws IOException
	{
		new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\EvaluationResults").mkdir();
		File EvalResultall = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\EvaluationResults\\MinMax.eval");
		EvalResultall.createNewFile();
	//	int f=1;
		//create(p,s,f);
		//for(int i=301;i<=350;i++)
			
		//{
		
		String qrels ="F:\\root2\\qrels.trec6.adhoc.part15";
		String ResultFile ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\MinMax-301-350"+s+".res";

		String EvalResult ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\EvaluationResults\\MinMax.eval";
		




		TrecEvalEvaluation trecEval = new TrecEvalEvaluation(qrels);
		trecEval.evaluate(ResultFile);
		trecEval.writeEvaluationResult();
		//System.out.print(EvalResult);
		trecEval.writeEvaluationResult(EvalResult);
	//	}
		

		///////////////////////////////////////evaluate sum results
		
			File EvalResultallsum = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\EvaluationResults\\Sum.eval");
			EvalResultallsum.createNewFile();
		
		String qrels2 ="F:\\root2\\qrels.trec6.adhoc.part15";
		String ResultFile2 ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\normalized\\Sum-301-350"+s+".res";

		String EvalResult2 ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolIFB2\\EvaluationResults\\Sum.eval";




		TrecEvalEvaluation trecEval2 = new TrecEvalEvaluation(qrels2);
		trecEval2.evaluate(ResultFile2);
		trecEval2.writeEvaluationResult();
		trecEval2.writeEvaluationResult(EvalResult2);
		
	}
	
	////////////////////////////////////tfidf evaluation
	public void TFIDFevaluation(int p,int s) throws IOException
	{
		new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults").mkdir();
		File EvalResultall = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults\\MinMax.eval");
		EvalResultall.createNewFile();
	//	int f=1;
		//create(p,s,f);
		//for(int i=301;i<=350;i++)
			
		//{
		
		String qrels ="F:\\root2\\qrels.trec6.adhoc.part15";
		String ResultFile ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\MinMax-301-350"+s+".res";

		String EvalResult ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults\\MinMax.eval";
		




		TrecEvalEvaluation trecEval = new TrecEvalEvaluation(qrels);
		trecEval.evaluate(ResultFile);
		trecEval.writeEvaluationResult();
		//System.out.print(EvalResult);
		trecEval.writeEvaluationResult(EvalResult);
	//	}
		

		///////////////////////////////////////evaluate sum results
		
			File EvalResultallsum = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults\\Sum.eval");
			EvalResultallsum.createNewFile();
		
		String qrels2 ="F:\\root2\\qrels.trec6.adhoc.part15";
		String ResultFile2 ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum-301-350"+s+".res";

		String EvalResult2 ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults\\Sum.eval";




		TrecEvalEvaluation trecEval2 = new TrecEvalEvaluation(qrels2);
		trecEval2.evaluate(ResultFile2);
		trecEval2.writeEvaluationResult();
		trecEval2.writeEvaluationResult(EvalResult2);
		
		//System.out.print(EvalResult);
		//trecEval2.writeEvaluationResult(EvalResult2);
		
		/*AdhocFullQueryEvaluation pr=new AdhocFullQueryEvaluation();
		
		pr.evaluate(qrels2, ResultFile2);
		pr.writeEvaluationResult();*/
		
		
		
		
		
	
	}
	
	///////////////////////////////////////////////////////////////TFIDF7 Evaluation
	public ArrayList<Double> retievalTDIDF7(int s,int p) throws IOException 
	{
		String[][] ar1=null;
		String[][] ar12=null;
		int topicnumber=400;
		int queryn=0;
		ArrayList<String> Topics=new ArrayList<String>();
		ArrayList<String> replacedone=new ArrayList<String>();
		ArrayList<String> replacedone2=new ArrayList<String>();
		String line=null;
		ResultSet results = null;
		ArrayList<Double> scores=new ArrayList<>(p);
		ArrayList<String> replace=new ArrayList<>();
		ArrayList<String> replace2=new ArrayList<>();
		String ar=null;
		 ArrayList<String> ars=new ArrayList<String>();
		 Topics=findtopics();
		 for(String a1:Topics)
		 {
			 ar1=null;
			 ar12=null;
			 replacedone.clear();
			 replacedone2.clear();
			 results=null;
			 line=null;
			 replace.clear();
			 replace2.clear();
			 ar=null;
			 ars.clear();
			 scores.clear();
			 topicnumber++;
			 queryn++;
	 for(int j=0;j<p;j++)
     {
		 
			
		 
			System.setProperty("terrier.home", "F:\\root3\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index");
		String pathToIndex =  "F:\\root3\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index";
		ApplicationSetup.setProperty("querying.postfilters.controls", "decorate:org.terrier.querying.SimpleDecorate");
		ApplicationSetup.setProperty("querying.postfilters.order", "org.terrier.querying.SimpleDecorate");
		
		/* ApplicationSetup.setProperty("querying.processes", "terrierql:TerrierQLParser,"
			        + "parsecontrols:TerrierQLToControls,"
			        + "parseql:TerrierQLToMatchingQueryTerms,"
			        + "matchopql:MatchingOpQLParser,"
			        + "applypipeline:ApplyTermPipeline,"
			        + "localmatching:LocalManager$ApplyLocalMatching,"
			        + "filters:LocalManager$PostFilterProcess");*/
		// ApplicationSetup.setProperty("querying.postfilters", "decorate:org.terrier.querying.SimpleDecorate");
		 Index diskIndex =Index.createIndex(pathToIndex, "data");
		 
		 
		 Manager queryingManager = new Manager(diskIndex);
		 SearchRequest srq = queryingManager.newSearchRequestFromQuery(a1);
		 srq.addMatchingModel("Matching","TF_IDF");
		 srq.setControl("decorate", "on");
		 queryingManager.runSearchRequest(srq);
		  results = srq.getResultSet();
		  String[] docnos = null;
		  final MetaIndex metaIndex = diskIndex.getMetaIndex();
		  
		  String r=null;
		 for (int i =0; i< results.getResultSize(); i++) {
			int sz=results.getResultSize();
	         int docid = results.getDocids()[i];
	         double score = results.getScores()[i];
	         docnos = metaIndex.getItems("DOCNO", results.getDocids());
	         scores.add(score);
	        r =results.getMetaItem("DOCNO", docid);
	       // final String queryIdExpanded =  srq.getQueryID() + " Q";
	       //  String[] doci=results.getMetaItems("filename");
	         
	       
	         //data[j][i] = score;
	         
	        
	        
	        	 
	        	// System.out.println(" Rank "+i+": "+docid+" "+"The score is: "+score);
	        	 new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF8").mkdir();
	         File resultfile = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\results"+j+"-"+topicnumber+".res");
	        
	         
	         try {
				resultfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         BufferedWriter writer1 = null;
			try {
				writer1 = new BufferedWriter(new FileWriter(resultfile,true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 ar=topicnumber+" "+"Q"+queryn+" "+results.getMetaItem("docno", i)+" "+i+" "+scores.get(i)+" "+"TFIDF";
	 		 ars.add(ar);
	 		
			
	 		
	 		try {
				writer1.write(ar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
	 		
	         }
	 int flag=0;
	 
	 new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\normalized").mkdir();
	// new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\normalized").mkdir();
	 
	  
	 List sc=MinMax(s,p,scores,results.getResultSize(),flag,ars);
		List sumlist=Sum(s,p,scores,results.getResultSize(),flag,ars);
		
		
		//System.out.print(sc);
		for(int h=0;h<ars.size();h++)
		{
			line=ars.get(h);
			String[] l=line.split(" ");
			for(int m=0;m<6;m++)
			{
				if(m==4)
				{
					String newone=sc.get(h).toString();
					replace.add(4,newone);
				}
				else {
					replace.add(m,l[m]);
				}
				
			
			}
			StringBuffer sj = new StringBuffer();
			
			
			//StringJoiner sj = new StringJoiner(",");
			for(String s1:replace) 
				{
				sj.append(s1);
				sj.append(" ");
				}
			String sbr=sj.toString();
			
			//replacedone.add(h, sj.toString());
			replacedone.add(sbr);
			
			replace.clear();
			//System.out.print(replacedone);
			//String oldone=l[4];
			
			//replacedone.set(4, newone);
			
			
			
			
			
			
			
		}
		
		//////////////////////////////////////////////////replace the new scores for sum algorithm
		for(int h=0;h<ars.size();h++)
		{
			String line2=ars.get(h);
			String[] l=line2.split(" ");
			for(int m=0;m<6;m++)
			{
				if(m==4)
				{
					String newone2=sumlist.get(h).toString();
					replace2.add(4,newone2);
				}
				else {
					replace2.add(m,l[m]);
				}
				
			
			}
			StringBuffer sj = new StringBuffer();
			
			
			//StringJoiner sj = new StringJoiner(",");
			for(String s1:replace2) 
				{
				sj.append(s1);
				sj.append(" ");
				}
			String sbr=sj.toString();
			
			//replacedone.add(h, sj.toString());
			replacedone2.add(sbr);
			
			replace2.clear();
			//System.out.print(replacedone);
			//String oldone=l[4];
			
			//replacedone.set(4, newone);
			
			
			
			
			
			
			
		}
		ars.clear();
		scores.clear();
		//results.getResultSet(null);
		//System.out.print(replacedone);
		//System.out.print(ars);
	// System.out.print(scores);
	 
	
		
//	System.out.print(scores);

	


     }
 ar1=new String[replacedone.size()][6];
 ar12=new String[replacedone2.size()][6];

// int forth=0;

for(int i=0;i<replacedone.size();i++)
{
	String line1=replacedone.get(i);
	String [] l1=line1.split(" ");
	for(int j=0;j<6;j++)
	{
		
			 
		ar1[i][j]=l1[j];
		
		
	}
}
////////////////////////////////////////Do same for sum
for(int i=0;i<replacedone2.size();i++)
{
	String line1=replacedone2.get(i);
	String [] l1=line1.split(" ");
	for(int j=0;j<6;j++)
	{
		
			 
		ar12[i][j]=l1[j];
		
		
	}
}
/////////////////////////////////////////////////


Arrays.sort(ar1, new Comparator<String[]>() {
	 
	  public int compare(String[] o1, String[] o2) {
		  System.out.print(Double.parseDouble(o1[4]));
	    Double i1 =Double.parseDouble(o1[4]) ;
	    Double i2 = Double.parseDouble(o2[4]) ;
	    //return i1.compareTo(i2);
	    if(i1<i2)
	    {
	    	return 1;
	    }
	    else if(i1>i2)
	    {
	    	return -1;
	    }
	    else
	    {
	    	return 0;
	    }
	    
	  }
	});

/////////////////////////////////////////////////Sort sum values
Arrays.sort(ar12, new Comparator<String[]>() {
	 
	  public int compare(String[] a1, String[] a2) {
		 // System.out.print(Double.parseDouble(o1[4]));
	    Double l1 =Double.parseDouble(a1[4]) ;
	    Double l2 = Double.parseDouble(a2[4]) ;
	    //return i1.compareTo(i2);
	    if(l1<l2)
	    {
	    	return 1;
	    }
	    else if(l1>l2)
	    {
	    	return -1;
	    }
	    else
	    {
	    	return 0;
	    }
	    
	  }
	});






System.out.print(ar1);

new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\normalized").mkdir();


	
	

	File myObj1 = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\normalized\\MinMax"+topicnumber+".res");
	File myObj2 = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\normalized\\Sum"+topicnumber+".res");
	
	myObj1.createNewFile();
	myObj2.createNewFile();

StringBuilder builder = new StringBuilder();
StringBuilder builder2=new StringBuilder();
for(int i = 0; i < ar1.length; i++)//for each row
{
for(int j = 0; j < ar1[i].length; j++)//for each column
{
   builder.append(ar1[i][j]+"");//append to the output string
   if(j < ar1.length - 1)//if this is not the last row element
      builder.append(" ");//then add comma (if you don't like commas you can use spaces)
}
builder.append("\n");//append new line at the end of the row
}
BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\normalized\\MinMax"+topicnumber+".res"));
writer.write(builder.toString());//save the string representation of the board
writer.close();
////////////////////////////////////////////////////////////Write the results to Sum.res
for(int i = 0; i < ar12.length; i++)//for each row
{
for(int j = 0; j < ar12[i].length; j++)//for each column
{
   builder2.append(ar12[i][j]+"");//append to the output string
   if(j < ar12.length - 1)//if this is not the last row element
      builder2.append(" ");//then add comma (if you don't like commas you can use spaces)
}
builder2.append("\n");//append new line at the end of the row
}
BufferedWriter writer2 = new BufferedWriter(new FileWriter("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\normalized\\Sum"+topicnumber+".res"));
writer2.write(builder2.toString());//save the string representation of the board
writer2.close();


///////////////////////////////to evaluate
/*int f=1;
create(p,s,f);

String qrels ="F:\\root2\\qrels.trec6.adhoc.part15";
String ResultFile ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\MinMax.res";

String EvalResult ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults\\MinMax.eval";




TrecEvalEvaluation trecEval = new TrecEvalEvaluation(qrels);
trecEval.evaluate(ResultFile);
trecEval.writeEvaluationResult();
//System.out.print(EvalResult);
trecEval.writeEvaluationResult(EvalResult);

///////////////////////////////////////evaluate sum results
String qrels2 ="F:\\root2\\qrels.trec6.adhoc.part15";
String ResultFile2 ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum.res";

String EvalResult2 ="F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults\\Sum.eval";




TrecEvalEvaluation trecEval2 = new TrecEvalEvaluation(qrels);
trecEval.evaluate(ResultFile2);
trecEval.writeEvaluationResult();
//System.out.print(EvalResult);
trecEval.writeEvaluationResult(EvalResult2);*/

		
		
		
		
		
		
	
	ars.clear();
	scores.clear();
	ar1=null;
	ar12=null;
		 }
	//results.getResultSet(null);
	//System.out.print(replacedone);
	//System.out.print(ars);
//System.out.print(scores);


	
//System.out.print(scores);








	
	
	
	







	
//System.out.print(scores);






//Sum(s,p,scores,results.getResultSize(),flag);
	
	//MinMax(s,p,scores,results.getResultSize(),flag);
	


//System.out.print(replacedone);


		  
	  
	  
	 
	return scores;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<Double> retievalBM25(int s,int p) throws IOException 
	{
		int topicnumber=300;
		int queryn=0;
		
		
		ArrayList<String> Topics=new ArrayList<String>();
		
		
		ResultSet results = null;
		
		ArrayList<Double> scores=new ArrayList<>(p);
		
		
		String ar=null;
		
		
		
		Topics=findtopics();
		 for(String a1:Topics)
		 {
			 List<ArrayList<String>> filesinfominmax=new ArrayList<ArrayList<String>>();
			//List<ArrayList<String>> filesinfo2=new ArrayList<ArrayList<String>>();
			 List<ArrayList<String>> filesinfosum=new ArrayList<ArrayList<String>>();
			 List<ArrayList<String>> mintowrite=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> sumtowrite=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> filesinfom=new ArrayList<ArrayList<String>>();
				List<ArrayList<String>> filesinfos=new ArrayList<ArrayList<String>>();
				 ArrayList<String> ars=new ArrayList<String>();
				 ArrayList<Double> forth=new ArrayList<Double>();
			
			
			 
			
			
			 results=null;
			
			
			 ar=null;
			
			 scores.clear();
			 topicnumber++;
			 queryn++;
			 
			
	 for(int j=0;j<p;j++)
     {
		 forth.clear();
		 
		 List sc=null;
		 List sumlist=null;
		
		 results=null;
		filesinfom.clear();
		filesinfos.clear();
		 
		 ar=null;
		
		 scores.clear();
		 
			
		 
		//	System.setProperty("terrier.home", "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index");
		 System.setProperty("terrier.home","C:\\terrier-core-4.2");
		 
		String pathToIndex =  "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+j+"\\index";
		ApplicationSetup.setProperty("querying.postfilters.controls", "decorate:org.terrier.querying.SimpleDecorate");
		ApplicationSetup.setProperty("querying.postfilters.order", "org.terrier.querying.SimpleDecorate");
		
		/* ApplicationSetup.setProperty("querying.processes", "terrierql:TerrierQLParser,"
			        + "parsecontrols:TerrierQLToControls,"
			        + "parseql:TerrierQLToMatchingQueryTerms,"
			        + "matchopql:MatchingOpQLParser,"
			        + "applypipeline:ApplyTermPipeline,"
			        + "localmatching:LocalManager$ApplyLocalMatching,"
			        + "filters:LocalManager$PostFilterProcess");*/
		// ApplicationSetup.setProperty("querying.postfilters", "decorate:org.terrier.querying.SimpleDecorate");
		 Index diskIndex =Index.createIndex(pathToIndex, "data");
		 
		 
		
		
			 
		
			 Manager queryingManager = new Manager(diskIndex);
		 
		 SearchRequest srq = queryingManager.newSearchRequestFromQuery(a1);
		 srq.addMatchingModel("Matching","BM25");
		 srq.setControl("decorate", "on");
		 queryingManager.runSearchRequest(srq);
		  results = srq.getResultSet();
		  String[] docnos = null;
		  final MetaIndex metaIndex = diskIndex.getMetaIndex();
		  
		  String r=null;
		 for (int i =0; i< results.getResultSize(); i++) {
			int sz=results.getResultSize();
	         int docid = results.getDocids()[i];
	         double score = results.getScores()[i];
	         docnos = metaIndex.getItems("DOCNO", results.getDocids());
	         scores.add(score);
	        r =results.getMetaItem("DOCNO", docid);
	       // final String queryIdExpanded =  srq.getQueryID() + " Q";
	       //  String[] doci=results.getMetaItems("filename");
	         
	       
	         //data[j][i] = score;
	         
	        
	        
	        	 
	        	// System.out.println(" Rank "+i+": "+docid+" "+"The score is: "+score);
	        	 new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25").mkdir();
	         File resultfile = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\results"+j+"-"+topicnumber+".res");
	        
	         
	         try {
				resultfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         BufferedWriter writer1 = null;
			try {
				writer1 = new BufferedWriter(new FileWriter(resultfile,true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 		 ar=topicnumber+" "+"Q"+queryn+" "+results.getMetaItem("docno", i)+" "+i+" "+scores.get(i)+" "+"BM25";
	 		 
	 		ArrayList <String> myListm = new ArrayList<String>(Arrays.asList(ar.split(" ")));
	 		ArrayList <String> myLists = new ArrayList<String>(Arrays.asList(ar.split(" ")));
	 		
	 		filesinfom.add(myListm);
	 		filesinfos.add(myLists);
	 		
	 		
	 		
	 		
	 		//System.out.print(filesinfo);
	 		
	 		 ars.add(ar);
	 		 
	 		 
	 		 
	 		
			
	 		
	 		try {
				writer1.write(ar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		try {
				writer1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
		 }
		 int flag=0;
		 
		 for(int h=0;h<filesinfom.size();h++)
		 {
		  double ss=(Double.parseDouble(filesinfom.get(h).get(4)));
		  forth.add(ss);
		  
		 }
		 
		 sc=MinMax(s,p,forth,results.getResultSize(),flag,ars);
		 sumlist=Sum(s,p,forth,results.getResultSize(),flag,ars);
		 for(int i=0;i<filesinfom.size();i++)
		 { 
			 filesinfom.get(i).remove(4);
			filesinfom.get(i).add(4,sc.get(i).toString());
			
			 
		  
		 }
		 filesinfominmax.addAll(filesinfom);
		 
		 
		 /////////////////////////////////////
		
		 for(int i=0;i<filesinfos.size();i++)
		 { 
			 filesinfos.get(i).remove(4);
			filesinfos.get(i).add(4,sumlist.get(i).toString());
			
			 
		  
		 }
		 filesinfosum.addAll(filesinfos);
		  
		  
		
		
     }
	  //  System.out.print(filesinfo);     
		 
	 
	 
	 
	
	 
	 
	 
	 Collections.sort(filesinfominmax, new Comparator<ArrayList<String>>() {    
	        @Override
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	        	
	        	Double i1=Double.parseDouble(o1.get(4));
	        	Double i2=Double.parseDouble(o2.get(4));
	            return i2.compareTo(i1);
	        }               
	});
	 
	 ///////////////////////////////////sort filesinfosum 
	 
	 Collections.sort(filesinfosum, new Comparator<ArrayList<String>>() {    
	        @Override
	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	        	
	        	Double i1=Double.parseDouble(o1.get(4));
	        	Double i2=Double.parseDouble(o2.get(4));
	            return i2.compareTo(i1);
	        }               
	});
	
	if(filesinfominmax.size()>=1000)
	{	
		mintowrite=filesinfominmax.subList(0, 1000);
	}
	else if(filesinfominmax.size()<1000)
	{
		mintowrite=filesinfominmax;
	}
	
	////////////////////////////////get first 1000 for sum too
	
	if(filesinfosum.size()>=1000)
	{	
		sumtowrite=filesinfosum.subList(0, 1000);
	}
	else if(filesinfosum.size()<1000)
	{
		sumtowrite=filesinfosum;
	}
		
	 

//sumlist=Sum(s,p,forth,results.getResultSize(),flag,ars);


//System.out.print(sc);






/////////////////////////for sumlist


//System.out.print(filesinfo1);

//////append for minmax



File normal=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized");
if(!normal.exists())
{
	normal.mkdir();
}


	
	

	File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\MinMax-"+topicnumber+"-.res");
	File myObj2 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\Sum-"+topicnumber+"-.res");
	
	myObj1.createNewFile();
	myObj2.createNewFile();

for(int m=0;m<mintowrite.size();m++)	
{
	BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\MinMax-"+topicnumber+"-.res",true));
for(String bf: mintowrite.get(m))//for each row
{
	


writer.write(bf);//save the string representation of the board
writer.write(" ");


}
writer.newLine();
writer.close();
}

//////////////////for sum write to file


for(int m=0;m<sumtowrite.size();m++)	
{
	BufferedWriter writer1 = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\sum-"+topicnumber+"-.res",true));
for(String bf: sumtowrite.get(m))//for each row
{
	


writer1.write(bf);//save the string representation of the board
writer1.write(" ");


}
writer1.newLine();
writer1.close();
}


/*for(String bf: replacedone2)//for each row
{
	BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\Sum-"+topicnumber+"-.res",true));


writer.write(bf);//save the string representation of the board
writer.newLine();
writer.close();
}*/

////////////////////////////////////////////////////////////Write the results to Sum.res


		
	
	
	scores.clear();
		 
	 filesinfom.clear();
	
		
		 }
		 
		 ///////////////////////////////////Combine all to one
		 
		 for(int topicnumber1=301;topicnumber1<=350;topicnumber1++)
			{	
			File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\MinMax-"+topicnumber1+"-.res");
			File myObj2=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\MinMax-301-350"+s+".res");
			myObj2.createNewFile();
		      Scanner myReader = new Scanner(myObj1);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(myObj2,true));
				 
	  		  writer.write(data);
	      	    writer.newLine();
	      	   writer.close();
		        
			
			}
			
		}
		 
		 
		 
		 for(int topicnumber1=301;topicnumber1<=350;topicnumber1++)
			{	
			File myObj1 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\Sum-"+topicnumber1+"-.res");
			File myObj2=new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\Sum-301-350"+s+".res");
			myObj2.createNewFile();
		      Scanner myReader = new Scanner(myObj1);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        BufferedWriter writer = new BufferedWriter(new FileWriter(myObj2,true));
				 
	  		  writer.write(data);
	      	    writer.newLine();
	      	   writer.close();
		        
			
			}
			
		}
		
		return scores;
	}
	
	/////////////////////////////////////////////////////////////////////
	public static void create(int p,int s,int f) throws IOException
	{
		
		
		if(f==0)
		{
		new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\EvaluationResults").mkdir();
		}
		else if(f==1)
		{
		new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults").mkdir();
		}
		for(int i=301;i<=350;i++)
		{	
		if(f==0)
		{
		
		File EvalResult = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\EvaluationResults\\MinMax"+i+".eval");
		File EvalResult2 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\EvaluationResults\\Sum"+i+".eval");
		EvalResult.createNewFile();
		EvalResult2.createNewFile();
		}
		
		else if(f==1)
		{
		
		File EvalResult11 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults\\MinMax"+i+".eval");
		File EvalResult12 = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\EvaluationResults\\Sum"+i+".eval");
		EvalResult11.createNewFile();
		EvalResult12.createNewFile();
		}
		}
		
		
		
		
		
	}
	///////////////////////////////////////////////////////////////////////create7
	
	public static void create7(int p,int s,int f) throws IOException
	{
		
		
		new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolBM25-7\\EvaluationResults").mkdir();
		new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\EvaluationResults").mkdir();
		for(int i=351;i<=400;i++)
		{	
		if(f==0)
		{
		
		File EvalResult = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolBM25-7\\EvaluationResults\\MinMax"+i+".eval");
		File EvalResult2 = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolBM25-7\\EvaluationResults\\Sum"+i+".eval");
		EvalResult.createNewFile();
		EvalResult2.createNewFile();
		}
		
		else if(f==1)
		{
		
		File EvalResult11 = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\EvaluationResults\\MinMax"+i+".eval");
		File EvalResult12 = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF7\\EvaluationResults\\Sum"+i+".eval");
		EvalResult11.createNewFile();
		EvalResult12.createNewFile();
		}
		}
		
		
		
		
		
	}
	
	////////////////////////////////////////////////////////////////////create8
	public static void create8(int p,int s,int f) throws IOException
	{
		
		
		new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolBM25-8\\EvaluationResults").mkdir();
		new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF8\\EvaluationResults").mkdir();
		for(int i=401;i<=450;i++)
		{	
		if(f==0)
		{
		
		File EvalResult = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolBM25-8\\EvaluationResults\\MinMax"+i+".eval");
		File EvalResult2 = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolBM25-8\\EvaluationResults\\Sum"+i+".eval");
		EvalResult.createNewFile();
		EvalResult2.createNewFile();
		}
		
		else if(f==1)
		{
		
		File EvalResult11 = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF8\\EvaluationResults\\MinMax"+i+".eval");
		File EvalResult12 = new File("F:\\root3\\"+p+"th partition\\test"+s+"\\poolTFIDF8\\EvaluationResults\\Sum"+i+".eval");
		EvalResult11.createNewFile();
		EvalResult12.createNewFile();
		}
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////////
	public List MinMax(int s,int p,ArrayList<Double> Scores, int limit,int flag,ArrayList<String> ars) throws IOException {
		double minScore = 0, maxScore = 0;
		double result1=0;
		ArrayList<Double> finalres=new ArrayList<Double>();
		
		int limit1 = Scores.size();
		

		//limit =result.size();
		if (limit >0 ){
			maxScore = Scores.get(0);
			minScore = Scores.get(limit-1);
		}

		for (int i=0; i<limit; i++)
		{
			Double element=Scores.get(i);
			//System.out.print(element);
			result1= (element - minScore)/(maxScore- minScore);
			finalres.add(i, result1);
			
		}
		System.out.print(finalres);
		
		
		//Collections.sort(finalres,Collections.reverseOrder());
		//List<Double>filelist=finalres.subList(0, 1000);
	//	List<Double>filelist=finalres;
		//System.out.print(filelist);
		

		//return finalres;
		 new File("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized").mkdir();
		
		//FileWriter writer = new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolTFIDF\\normalized\\MinMax.res");
		//FileWriter writer1 = new FileWriter("F:\\root2\\"+p+"th partition\\test"+s+"\\poolBM25\\normalized\\MinMax.res");
		
	/*	if(flag==0)
		{
		
		for(Double str: filelist) {
			 
		  writer.write("Scores: "+str + System.lineSeparator());
		  writer.close();
		}
		}*/
		
		//System.out.print(filelist.size());
		
	/*	if(flag==1)
		{
			for(Double str:filelist) {
				 
				writer1.write("Scores: "+str + System.lineSeparator());
				  
				
			
		}
			writer1.close();
			*/
			
		
		
		
		
		
		
		
		
		
		
		
		
		
	//}
		return finalres;
	}// 
	
	////////////////////////////////////////////////////////////////////////////////////////
	public ArrayList<Double> Sum(int s,int p,ArrayList<Double> result, int limit,int flag,ArrayList<String> ars) throws IOException {
		double minScore = 0, shiftScores =0, res=0;
		ArrayList<Double> resultSum = new ArrayList<>(); // obtain the limit from the resultSet length
		ArrayList<Double> fresult=new ArrayList<>();
		limit = result.size();
		double ressum=0;

		if (limit >0 )
			minScore = result.get(limit-1);

		for (int i=0; i<limit; i++) {
			res = result.get(i) - minScore;
			fresult.add(i,res);
			shiftScores += fresult.get(i);
		}

		for (int i=0; i<limit; i++)
		{
			ressum = fresult.get(i) / shiftScores;
			resultSum.add(i,ressum);
		}
		
		
		
		
		
		
		
		
		
		
		
		

		return resultSum;
		
	} 
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public boolean beenindexed(int s,int p)
	
	{
		
		boolean in=false;
		for(int h=0;h<p;h++)
		{
		File path =new File("F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+h);
		File[] listf=path.listFiles();
		for (File file : listf) {
		 
		       
		if(file.getName().contains("index"))
		{
			
			in=true;
		}
		
		}
		
		
		
		
	}
		return in;

}
	public boolean retrieved(int s,int p)
	{
		
		boolean in=false;
		for(int h=0;h<p;h++)
		{
		File path =new File("F:\\root2\\"+p+"th partition\\test"+s);
		File[] listf=path.listFiles();
		for (File file : listf) {
		 
		       
		if(file.getName().contains("poolTFIDF7"))
		{
			
			in=true;
		}
		
		}
		
		
		
		
	}
		return in;
		
	}
}
