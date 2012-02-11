/**
 * 
 */
package org.ktlab.filter.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

/**
 * @author hugo
 *
 */
public class TestAutomaton {
	/*	public static void parserEntity(String content) {
	try {
		Pattern regex = Pattern
				.compile(
						"((\\p{Lu}\\p{Ll}+ )(\\p{Lu}\\p{Ll}+ )*(\\p{L}+ ){0,1}(\\p{Lu}\\p{Ll}+ \\p{L}+ )*(\\p{Lu}\\p{Ll}+ )*\\p{Lu}\\p{Ll}+)( \\p{L}+){0,1}",
						Pattern.CANON_EQ);
		Matcher regexMatcher = regex.matcher(content);
		"((\\p{L})+\\s)((\\p{L})+\\s)((\\p{L})+\\s)((\\p{L})+\\s)((\\p{Lu}\\p{Ll}+\\s)+{2,})((\\p{L})+\\s)((\\p{L})+\\s)((\\p{L})+)"
		if (regexMatcher.find()) {
			System.out.println(regexMatcher.group(1));
			if (regexMatcher.group(7) != null) {
				System.out.println(regexMatcher.group(0));
			}
		} else {}
	}catch(Exception e){
		/*Iterator itTmp = frequent().entrySet().iterator();
		while(itTmp.hasNext()) {
			Map.Entry pairs = (Map.Entry)itTmp.next();
			Entity eTmp = new Entity(pairs.getKey().toString(), Integer.parseInt(pairs.getValue().toString()));
			writer.write(eTmp);
		}
		 */		
/*
	}
	}
*/
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		RegExp r = new RegExp("ab(cd)( )e?( )^z");
		
		//System.out.println(r.getIdentifiers().toString());
		//System.out.println(r.toString());
		//RunAutomaton a = new RunAutomaton(r.toAutomaton());
		Automaton a = r.toAutomaton();
		String s = "abcd fjsjflksfkljskjfsjsklfsklfjslkfjslfljs";
		a.makeStringMatcher(s);
		System.out.println(a.getStrings(1));
		
//		System.out.println(a.);
*/		/*String s = "Mỹ Tâm đi ăn hỏi. Mỹ Tâm lấy chồng! Mỹ USA";
		Pattern p = Pattern.compile("Mỹ Tâm");
		Matcher m = p.matcher(s);
		boolean res = m.find();
		while (res) {
			System.out.println("OK" + m.group());
			res = m.find();*/
		
/*		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("one");
		Set<String> u = new HashSet<String>(list);
		for (String key: u) {
			System.out.println(key + ":" + Collections.frequency(list, key));
		
		}
*/		
/*		String regex = "(\\b.+\\b)( )";
		String input = "Chiều ngày 20/11, Ca sỹ Mỹ Tâm làm từ thiện.";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		boolean res = m.find();
		while(res) {
			System.out.println(m.group());
			res = m.find();
		}
*/	
		//String one = "Hello how are you?";
		//StringTokenizer tokens = new StringTokenizer(one);
/*		List<String> one = new ArrayList<String>();
		one.add("Two");
		one.add("Three");
		String rst = new String();
		for(String s:one) {
			rst += s+" ";
		}
		System.out.println(one);
		System.out.println(one.remove(0));
		System.out.println(one);
		System.out.println(one.remove(0));*/
		
		List<String> one = new ArrayList<String>();
		one.add("One");
		one.add("Twi");
		System.out.println(one);
		one.removeAll(one);
		System.out.println(one.getClass());
		one.removeAll(one);
		
		
		System.out.println(one);
		one.removeAll(one);
		System.out.println(one);
		//System.out.println(l);
	}
	
}
