/**
 * 
 */
package org.ktlab.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ktlab.json.JSONWriter;

/**
 * @author hugo
 *
 */
public class RawParser {
	public List<String> entities;		//List contains entities (or core data)
	private char[] markSent = {'.', ';',',','?','!',':','"'
								,'(',')','[',']','&','{','}'};
	
	private char[] upperUnicode = {'A','Ả','Á','Ạ','À','Ã'
									,'Ă','Ẳ','Ắ','Ạ','Ằ','Ẵ'
									,'Â','Ẩ','Ấ','Ậ','Ầ','Ẫ'
									,'B','C','D','Đ'
									,'E','Ẽ', 'Ẻ','Ẹ','È'
									,'Ê','Ế','Ệ','Ề','Ễ','Ể'
									,'F', 'G','H','I','Ĩ','Ị'
									,'Í','Ì','Ỉ','J', 'K', 'L','M','N'
									,'O','Ó','Ọ','Ỏ','Õ','Ò'
									,'Ô','Ố','Ồ','Ổ','Ộ','Ỗ'
									,'Ơ','Ớ','Ờ','Ợ','Ở','Ỡ'
									,'P','Q','R','S','T'
									,'U','Ú','Ủ','Ũ','Ù','Ụ'
									,'Ư','Ừ','Ử','Ự','Ứ','Ữ'
									,'X','Y','Ý','Ỷ','Ỳ','Ỹ'
									,'Ỵ','V','W'
								  };

	//Constructor with _content is a string to analysis
	public RawParser() {
		this.entities = new ArrayList<String>();
	}
	//End constructor

	
	/** Parse an regular expression in a string 
	 * 
	 * @param regex
	 * @param content
	 * @return entities This a list of entities that we need
	 * @throws Exception
	 */
	public List<String> filter(String content, int minLength, int preNumWords, int postNumWords) 
																throws Exception{
		StringTokenizer tokens = new StringTokenizer(content);
		/*
		 * Load tokens into an List
		 */
		List<String> listToken = new ArrayList<String>();
		while(tokens.hasMoreTokens()) {
			listToken.add(tokens.nextToken());
		}
		return parser(listToken, minLength, preNumWords, postNumWords);
	}
	
	
	/**
	 * Parse for String
	 * @param listElement
	 * @param minLength min amount words of entity
	 * @param preNumWords Amount prefix words
	 * @param postNumWords Amount postfix words
	 * @return a list contains core-elements
	 */
	public List<String> parser(List<String> listElement, int minLength, 
											int preNumWords, int postNumWords){
		/*
		 * Load element in list into buffer
		 */
		if(listElement.size() > minLength+preNumWords+postNumWords-1) {
			List<String> buffer = new ArrayList<String>();
			
			String firstElement = listElement.remove(0); 
			buffer.add(firstElement);
			if(listElement.size()-preNumWords >= 0) {
				int i = 0;
				for(i = 0; i < preNumWords-1; i++) {
					buffer.add(listElement.get(i));
				}
				getUpperWord(listElement, preNumWords-1);
				int upperListSize = upperList.size(); 
				if(upperListSize >= minLength) {
					if(listElement.size()-buffer.size()-upperListSize
						> (postNumWords-2)) {
						this.entities.add(list2str(upperList));
						
						for(int j=0; j < upperListSize; j++) {
							listElement.remove(0);
						}
						buffer.removeAll(buffer);
						upperList.removeAll(upperList);
						parser(listElement, minLength, preNumWords, postNumWords);						
					}
				}else {
					buffer.removeAll(buffer);
					upperList.removeAll(upperList);
					parser(listElement, minLength, preNumWords, postNumWords);
				}
			}
		}
		return this.entities;
	}
	
	/** Statistics entity on list of entities
	 * 
	 * @return a Map with list of entities and frequently number
	 */
	public Map<String, Integer> frequent() {
		Map<String, Integer> counter = new TreeMap<String, Integer>();
		for (String entity:this.entities) {
			Integer count = counter.get(entity);
			counter.put(entity, (count==null)?1:count+1);
		}
		return counter;
	}

	/**
	 * Write filtered data
	 * @param jsonFileName
	 * @throws Exception
	 */
	public void writeToJSON(String jsonFileName) throws Exception {
		JSONWriter writer = new JSONWriter(jsonFileName);
		Map<String, Integer> itTmp = frequent();
		for(Map.Entry<String, Integer> e : itTmp.entrySet()) {
			Entity eTmp = new Entity(e.getKey(), e.getValue());
			writer.write(eTmp);
		}
	}
	
	
	/**
	 * Get a uppercase word in a string list
	 * @param lWord
	 * @param index
	 * @return
	 */
	List<String> upperList = new ArrayList<String>();
	public List<String> getUpperWord(List<String> lWord, int index) {
		String word = lWord.get(index);
		//System.out.println("Print :" + word);
		boolean checkUpper = isUpperUnicode(word);
		boolean checkMark = isMarked(word);
		if(checkUpper & !checkMark){
			if(index < lWord.size()-1) {
				upperList.add(word);
				getUpperWord(lWord, index+1);
			}else if (checkUpper & checkMark) {
				upperList.add(word.substring(0, word.length()-2));
			}
			
			else{
				upperList.add(word);
			}
		}else {}
		return upperList;
	}
	
	/**
	 * Check a string is uppercase in unicode form
	 * @param s
	 * @return true if yes, false if otherwise
	 */
	public boolean isUpperUnicode(String s) {
		char firstChar = s.charAt(0);
		for(char c:upperUnicode) {
			if(firstChar==c) return true;
		}
		return false;
	}
	
	/**
	 * Check a word with mark sentence
	 * @param s
	 * @return
	 */
	public boolean isMarked(String s) {
		for(char c:markSent) {
			if(s.endsWith(String.valueOf(c))) return true;
		}
		return false;
	}
	/**
	 * Convert list to string
	 * @param l
	 * @return a string converted
	 */
	public String list2str(List<String> l) {
		String rst = new String();
		for(String s:l) {
			rst += s+" ";
		}
		return rst.trim();
	}
//==============================================================================
	//NEW
	public void newsParser(String content, int minLength, int preNumWords, 
															int postNumWords) {
		List<String> words = new ArrayList<String>();
		List<String> window = new ArrayList<String>();
		
		StringTokenizer tokens = new StringTokenizer(content);
		while(tokens.hasMoreTokens()) {
			words.add(tokens.nextToken());
		}
		
		while(words.isEmpty()!=true) {
			//String entity = new String();
			List<String> entity = new ArrayList<String>();
			System.out.println("Size:" + window.size());
			if(window.size() < preNumWords) {
				window.add(words.remove(0));
			}
			if(window.size()==preNumWords) {
				if(isUpperUnicode(words.get(0))==true) {
					while(isUpperUnicode(words.get(0))==true){
						if(isMarked(words.get(0))==false) {
							entity.add(words.get(0));
							window.remove(0);
							window.add(words.remove(0));
						}else {
							entity.add(words.get(0).substring(0, words.get(0).length()-1));
							window.remove(0);
							window.add(words.remove(0));
							break;
						}
					}
					if((entity.size() > minLength-1)
							&(words.size() > postNumWords-1)){
						this.entities.add(list2str(entity));
					}
				}else {
					window.remove(0);
					window.add(words.remove(0));
				}
			}
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		RawParser parser = new RawParser();
		//String content = new GetData().parse("\\media\\STUDYing\\A_SResearch\\Student2012\\2012\\data\\Baomoi");
		//String content = new GetData().parse("/media/STUDYing/A_SResearch/Student2012/2012/dataTest");
		//String content = new GetData().parse("dataTest");
		//String content = "Đã rất rất lau Trần Xuân! Tứ Teee không đi học";
		long start = System.currentTimeMillis();
		//parser.newsParser(content, 2, 4, 3);
		System.out.println(parser.entities);
		parser.writeToJSON("1stParse/name.json");
		System.out.println("DONE");
		//System.out.println(content);
		//System.out.println(parser.filter(content, 2, 4, 3));
	}
}


/*List<String> listToken = new ArrayList<String>();
StringTokenizer tokens = new StringTokenizer(content);

while(tokens.hasMoreTokens()) {
	String tk = tokens.nextToken();
	listToken.add(tk);

	
}

*/

//System.err.println(listToken.size());

//System.out.println(content);
//String regex = "((\\p{L})+\\s)((\\p{L})+\\s)((\\p{L})+\\s)((\\p{L})+\\s)((\\p{Lu}\\p{Ll}+\\s)+{2,})((\\p{L})+\\s)((\\p{L})+\\s)((\\p{L})+)";
//List<String> res = parser.filter(content,4,3);
//System.out.println(parser.parserEntity(content));

//System.out.println(parser.isUpperUnicode("Ẳ"));
//String content = "Đã rất rất Trần Xuân Tứ không đi học";
//String content = "Da rat lau roi ljskfljklslfsj sjflsjfkls klj jklk Tran Xuan Tu Nguyen đi Hào Nam di hoc he.";
//String content = "Da rat lau roi \n\n\n";

