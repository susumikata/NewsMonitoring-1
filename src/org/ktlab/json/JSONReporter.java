package org.ktlab.json;

import java.io.PrintStream;

import org.headvances.util.statistic.StatisticMap;

/**
 * $Author: Tuan Nguyen$ 
 **/
public class JSONReporter<T> {
	private String file ;
	private Class<T> type ;
	private StatisticMap map ;
	
	public JSONReporter(String file, Class<T> type) {
		this.file = file ;
		this.type = type ;
		this.map = new StatisticMap() ;
	}
	
	public void process() throws Exception {
		map.clear() ;
		JSONReader reader = new JSONReader(file) ;
		T object  = null ;
		while((object = reader.read(type)) != null) {
			log(object) ;
		}
		reader.close() ;
	}
	
	protected void log(T object) {
		map.incr("Statistic", "all", 1) ;
	}
	
	public void report(PrintStream out) {
		map.report(out);
	}
}
