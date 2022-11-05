import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.terrier.indexing.Collection;
import org.terrier.indexing.TRECCollection;
import org.terrier.structures.Index;
import org.terrier.structures.indexing.Indexer;
import org.terrier.structures.indexing.classical.BasicIndexer;
import org.terrier.utility.ApplicationSetup;

public class IndexBTN {
	
	public void index(int s,int p)
	{
		
			
					
						for(int h=0;h<p;h++) {
							
							new File("F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+h+"\\index").mkdirs();
							
						String aDirectoryToIndex = "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+h;
							String trecDirectoryToIndex = "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+h+"\\agent"+h+".txt";
							String pathToIndex =  "F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+h+"\\index";
							
							/**********************Properties should be set for TREC documents Retrieval*******************/
							System.setProperty("terrier.home","C:\\terrier-core-4.2");
							ApplicationSetup.setProperty("TrecDocTags.doctag", "DOC");
							ApplicationSetup.setProperty("TrecDocTags.idtag", "DOCNO");
							ApplicationSetup.setProperty("TrecDocTags.skip", "DOCHDR");
							ApplicationSetup.setProperty("TrecDocTags.casesensitive", "false");
							
							///////////////////////////////////////////////indexing code
							ApplicationSetup.setProperty("trec.querying.outputformat.docno.meta.key", "docno");
							ApplicationSetup.setProperty("indexer.meta.forward.keys", "docno");
							ApplicationSetup.setProperty("indexer.meta.forward.keylens", "200");
							ApplicationSetup.setProperty("termpipelines", "Stopwords,PorterStemmer");
							
							

							Indexer indexer = new BasicIndexer(pathToIndex, "data");
							//Collection coll = new SimpleFileCollection(Arrays.asList(aDirectoryToIndex), true);
							Collection coll = new TRECCollection(trecDirectoryToIndex);
							//indexer.index(new Collection[]{coll});
							indexer.createDirectIndex(new Collection[]{coll});
							indexer.createInvertedIndex();
							//((Closeable) indexer).close();

							//index = Index.createIndex("D:/eclipse projects/WorkspaceNew/terrier-agent/directory/index", "data");

							ApplicationSetup.setProperty("querying.postfilters.order", "org.terrier.querying.SimpleDecorate");
							ApplicationSetup.setProperty("querying.postfilters.controls", "decorate:org.terrier.querying.SimpleDecorate");
							///////////////////////////////////////////////////////////////////////////////////////////////////////////////
							 Index diskIndex =Index.createIndex(pathToIndex, "data");
							System.out.print(diskIndex.getCollectionStatistics().getNumberOfDocuments()); 
						       System.out.print(diskIndex.getCollectionStatistics().getNumberOfTokens());
						      // writetofile();
						       File myObj = new File("F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+h+"\\index\\statistics.res");
								BufferedWriter writer=null;
								try {
									writer = new BufferedWriter(new FileWriter(myObj,true));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String a="Number of Docs: ";
								String b="Number of Tokens: ";
								try {
									writer.write(a);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String a1=Integer.toString(diskIndex.getCollectionStatistics().getNumberOfDocuments());
								String b1=Integer.toString((int) diskIndex.getCollectionStatistics().getNumberOfTokens());
								try {
									writer.write(a1);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								try {
									writer.newLine();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//writer.newLine();
						      // PrintvaluesTofile(diskIndex.getCollectionStatistics().getNumberOfDocuments());
						       try {
								writer.write(b);
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						       try {
								writer.write(b1);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						       
						    //  PrintvaluesTofile((int) diskIndex.getCollectionStatistics().getNumberOfTokens());
						       try {
								writer.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						   
						      
						}
						
		
	}
	
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

public boolean isdivided(int s,int p)
{
	boolean in=false;
	for(int h=0;h<p;h++)
	{
	File path =new File("F:\\root2\\"+p+"th partition\\test"+s+"\\agent"+h);
	File[] listf=path.listFiles();
	for (File file : listf) {
	 
	       
	if(file.getName().contains("agent"+h))
	{
		
		in=true;
	}
	
	}
	
	
	
	
}
	return in;
	
	
	
}

////////////////////////////////////////////////////////////////////////////////////////////
public boolean hasdivided(int p)
{
	boolean in=false;
	for(int h=0;h<p;h++)
	{
	File path =new File("F:\\root2");
	File[] listf=path.listFiles();
	for (File file : listf) {
	 
	       
	if(file.getName().contains(p+"th partition"))
	{
		
		in=true;
	}
	
	}
	
	
	
	
}
	return in;
	
	
	
}
	

	

}
