package org.ktlab.json;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.zip.GZIPOutputStream;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
/**
 * $Author: Tuan Nguyen$ 
 **/
public class JSONWriter {
	private OutputStream out ;
	private JsonGenerator generator ;
	
	public JSONWriter(String file) throws Exception {
		this(file, file.endsWith(".gzip")) ;
	}
	
	public JSONWriter(String file, boolean compress) throws Exception {
		if(compress) {
			init(new GZIPOutputStream(new FileOutputStream(file))) ;
		} else {
			init(new FileOutputStream(file)) ;
		}
	}
	
	public JSONWriter(OutputStream os) throws Exception {
		init(os) ;
	}
	
  public JSONWriter(PrintStream out) throws IOException {
  	init(out) ;
  }
  
  private void init(OutputStream out) throws IOException {
  	this.out = out ;
  	MappingJsonFactory factory = new MappingJsonFactory();
		factory.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false); // all configuration before use
		factory.getCodec().getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL) ;
		generator = factory.createJsonGenerator(out, JsonEncoding.UTF8) ;
		DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter() ;
		prettyPrinter.indentArraysWith(new DefaultPrettyPrinter.Lf2SpacesIndenter()) ;
		generator.setPrettyPrinter(prettyPrinter) ;
  }
	
	synchronized public void write(Object object) throws Exception {
		generator.writeObject(object) ;
	}
	
	synchronized public void write(JsonNode node) throws Exception {
		generator.writeTree(node) ;
	}
	
	public void close() throws IOException {
		generator.close() ;
		out.flush() ;
		out.close() ;
	}
}