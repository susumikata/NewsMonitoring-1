package org.ktlab.json;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.headvances.data.Document;
import org.headvances.util.FileUtil;
import org.headvances.util.text.DateUtil;
/**
 * $Author: Tuan Nguyen$ 
 **/
public class JSONMultiFileWriter {
	private String  directory ;
	private boolean compress = false ;
	private int     maxDocumentPerFile ;
	private long    maxWait ;
	
	private JSONWriter currentWriter ;
	private int currentWriteCount ;
	
	public JSONMultiFileWriter() { }
	
	public JSONMultiFileWriter(String location) throws Exception {
		directory = location ;
		onInit() ;
	}
	
	public String getDirectory() { return this.directory ; }
	public void   setDirectory(String dir) { this.directory = dir ; } 
	
	public boolean getCompress() { return this.compress ;  }
	public void    setCompress(boolean b) { this.compress = b ; }
	
  public int  getMaxDocumentPerFile() { return maxDocumentPerFile; }
	public void setMaxDocumentPerFile(int maxDocumentPerFile) { 
		this.maxDocumentPerFile = maxDocumentPerFile;
  }
	
	public long getMaxWait() { return maxWait; }
	public void setMaxWait(long maxWait) { this.maxWait = maxWait; }

	@PostConstruct
	public void onInit() throws Exception {
		if(!FileUtil.exist(directory)) {
			FileUtil.mkdirs(directory) ;
		}
	}
	
	@PreDestroy
	public void onDestroy() throws Exception {
		if(currentWriter == null) return ; 
		currentWriter.close() ;
		currentWriter = null ;
	}
	
	public void close() throws Exception {
		if(currentWriter == null) return ; 
		currentWriter.close() ;
		currentWriter = null ;
	}
	
	public void write(Document doc) throws Exception {
		getWriter().write(doc) ;
		currentWriteCount++ ;
	}
	
	JSONWriter getWriter() throws Exception {
		if(currentWriteCount == this.maxDocumentPerFile) {
			if(currentWriter != null) currentWriter.close() ;
			currentWriter = null ;
			currentWriteCount = 0 ;
		}
		if(currentWriter == null) {
			String file = getFileName(directory) ;
			if(compress) file =  file + ".gzip" ;
			this.currentWriter = new JSONWriter(file, compress) ;
		}
		return this.currentWriter ; 
	}
	
	protected String getFileName(String directory) {
		String dateId = DateUtil.asCompactDateTimeId(new Date()) ;
		String file = directory + "/set-" + dateId + ".json" ;
		return file ;
	}
}