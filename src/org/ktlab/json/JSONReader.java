package org.ktlab.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.SerializationConfig;
/**
 * $Author: Tuan Nguyen$ 
 **/
public class JSONReader {
	private InputStream is ;
	private JsonParser parser ;
	
	public JSONReader(String file) throws Exception {
		this(file, file.endsWith(".gzip")) ;
	}
	
	public JSONReader(String file, boolean compress) throws Exception {
		if(compress) {
			init(new GZIPInputStream(new FileInputStream(file))) ;
		} else {
		  init(new FileInputStream(file)) ;
		}
	}
	
	public JSONReader(InputStream is) throws Exception {
		init(is) ;
	}

	private void init(InputStream is) throws Exception {
		this.is  = is ;
		MappingJsonFactory factory = new MappingJsonFactory();
		factory.getCodec().configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, false) ;
		factory.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
		parser = factory.createJsonParser(new BufferedReader(new InputStreamReader(is, "UTF-8")));
	}
	
	public <T> T read(Class<T> type) throws Exception {
		if(parser.nextToken() == null) return null;
		return parser.readValueAs(type) ;
	}
	
	public <T> List<T> readAll(Class<T> type) throws Exception {
    List<T> holder = new ArrayList<T>() ;
    T object = null ;
    while((object = read(type)) != null) holder.add(object) ;
    return holder ;
  }
	
	public JsonNode read() throws Exception {
		if(parser.nextToken() == null) return null;
		return parser.readValueAsTree() ;
	}
	
	public void close() throws Exception {
		parser.close() ;
		is.close() ;
	}
}