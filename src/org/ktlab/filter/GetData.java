package org.ktlab.filter;

import java.util.ArrayList;
import java.util.List;
import org.ktlab.json.JSONMultiFileReader;


public class GetData {
	private List<News> data = new ArrayList<News>();
	
	//Constructors
	public GetData(){};					//Must exist 
	
	public GetData(List<News> _data){
		this.data = _data; 
	}
	
	//End constructors
	
	/**
	 * @return the data
	 */
	public List<News> getData() {
		return this.data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<News> _data) {
		this.data = _data;
	}
	
	@Override
	public String toString() {
		return "" + data + "";
	}

	/**
	 * JSON Parser
	 * @throws Exception 
	 */
	
	public List<String> parse(String location) throws Exception {
		String result = null;
		GetData gdt = null;
		JSONMultiFileReader mjReader = new JSONMultiFileReader(location);
		RawParser rp = new RawParser();
		while((gdt = mjReader.next(GetData.class)) != null) {
			//result = gdt.toString();
			rp.newsParser(gdt.toString(),2,4,3);
		}
		return rp.entities;
	}
	
	
	public String nextDoc() {
		return null;
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		GetData one = new GetData();
		System.out.println(one.parse("testD"));
	}
}
