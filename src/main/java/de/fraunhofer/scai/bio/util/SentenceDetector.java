/*
 * Copyright 2018 Fraunhofer Institute SCAI, St. Augustin, Germany
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.fraunhofer.scai.bio.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

/**
 * uses a list of regular expressions for sentence end positions
 * 
 * @author marc
 */
public class SentenceDetector {

	private TreeMap<String, Pattern> regularExpressions;

	private int shortLine = 50;
	
	/**
	 * default constructor 
	 */
	public SentenceDetector() {
		this(50, getDefaultPatterns());
	}

	public SentenceDetector(int tooShortLine) {
		this(tooShortLine, getDefaultPatterns());
	}
	
	/**
	 * constructor - compiles regular patterns
	 * 
	 * @param tooShortLine 
	 * @param endOfSentencePatterns 
	 */
	public SentenceDetector(int tooShortLine, Map<String, String> endOfSentencePatterns) {

		// for each type keep a HashMap with the regex
		regularExpressions = new TreeMap<String, Pattern>();

		shortLine  = tooShortLine;
		
		// for each regular expression in that section
		for(Entry<String, String> expr : endOfSentencePatterns.entrySet()) {

			try {
				Pattern pattern = Pattern.compile( expr.getValue() );
				regularExpressions.put(expr.getKey(), pattern);

			} catch (Exception e) {
				throw new IllegalArgumentException("Couldn't compile pattern " + expr.getKey() + ": " + expr.getValue());
			}

		}
	}

	/**
	 * provide some general end of sentence patterns
	 * 
	 * @return <code>Map</code> of <code>String, String</code>
	 */
	static Map<String, String> getDefaultPatterns() {
		TreeMap<String, String> patterns = new TreeMap<String, String>();

		patterns.put("colon in front of Capital letter [$1]", "(:)(\\s)(\\s*[A-Z])");
		patterns.put("full stop in front of Capital letter [$1]", "([.!?])(\\s)(?!\\s*[a-z0-9])");
		patterns.put("words followed by empty lines [$2]", "(\\S+\\s*\\n)([^\\n\\r]*?)(\\s*\\n){2,}");

		return patterns;
	}

	/**
	 * @param docText <code>String</code>
	 * @return <code>List</code> of <code>Integer</code>
	 * @throws IllegalArgumentException
	 */
	public List<Integer> findSentencesEndPositions(String docText) throws IllegalArgumentException {
		
		int docLen = docText.length();

		// check for correct input to your annotator, if not throw an exception
		if( docText == null || docText.isEmpty() ) {
			throw new IllegalArgumentException("document text is empty");			
		}
		
		// list of positions where sentences can end
		List<Integer> stoplist = new ArrayList<Integer>();

		// find short lines via a scanner
		if(shortLine>0) {			
		    findShortLines(stoplist, docText);
		}
		
		// find stop positions via regex
		findStopPatterns(stoplist, docText);		
		
		// close document with sentence
		if(!stoplist.contains(docLen)) stoplist.add(docLen);
		
		// order the different findings
		Collections.sort(stoplist);

		return stoplist;
	}
	
	/**
	 * pattern matcher for stop positions
     * add stop tokens to the list
	 * 
	 * @param stoplist <code>Map of Integer, String</code>
	 * @param docText 
	 */
	private void findStopPatterns(List<Integer> stoplist, String docText) {
		
		if(regularExpressions != null) {
			for(String regexName : regularExpressions.keySet()) {

				Pattern pattern = regularExpressions.get(regexName);					
				Matcher matcher = pattern.matcher( docText );

				// which part of the matched pattern should be annotated
				int end = extractEnd(regexName);

				// find all matches and add end of sentence to list
				while(matcher.find()) {
					if(!stoplist.contains(matcher.end(end))) {
						stoplist.add(matcher.end(end));
					}
				}			
			}
		}
	}

	/**
	 * search for short lines and add stop tokens to the list
	 * 
	 * @param stoplist <code>Map of Integer, String</code>
	 * @param docText 
	 */
	private void findShortLines(List<Integer> stoplist, String docText) {
		int index = 0;

		Scanner scanner = new Scanner( docText );
		while ( scanner.hasNextLine() ) {
		  String line = scanner.nextLine();
		  
		  index += line.length();
		  if(line.length() < shortLine) stoplist.add(index);
		  index++;		// new line
		  
		}
		
		scanner.close();
	}
	
	/**
	 * searches for the last group pattern in regexName like [$1,$2]
	 * 
	 * @param regexName
	 * @return <code>int</code> number of group
	 */
	private int extractEnd(String regexName) {
		String idx = "";
		
		if(regexName.indexOf("$")>=0) idx = regexName.substring(regexName.indexOf("[")+1);
		
		if(idx.indexOf(",")>0) idx = idx.substring(idx.indexOf(",")+1);
		if(idx.indexOf("$")>=0) idx = idx.substring(idx.indexOf("$")+1);
		if(idx.indexOf("]")>0) idx = idx.substring(0, idx.indexOf("]"));

		try {
			if(idx.length()>0) return Integer.parseInt(idx);
		} catch (Exception e) {
			// not a number
		}
		
		return 0;	// fallback: all
	}

	/**
	 * searches for the first group pattern in regexName like [$1,$2]
	 * 
	 * @param regexName
	 * @return <code>int</code> number of group
	 */
	public int extractBegin(String regexName) {
		String idx = "";
		
		if(regexName.indexOf("$")>=0) idx = regexName.substring(regexName.indexOf("$")+1);
		if(idx.indexOf(",")>0) idx = idx.substring(0, idx.indexOf(","));
		if(idx.indexOf("]")>0) idx = idx.substring(0, idx.indexOf("]"));
		
		try {
			if(idx.length()>0) return Integer.parseInt(idx);
		} catch (Exception e) {
			// not a number
		}
		
		return 0;	// fallback: all
	}

	/**
	 * @param stoplist
	 * @param text
	 * @param shortSentence
	 * @return
	 */
	public List<String> getSentences(List<Integer> stoplist, String text, int shortSentence) {
		
		ArrayList<String> sentences = new ArrayList<String>();
		
		List<Tuple> tuples = getSentencesOffsets(stoplist, text, shortSentence);
		
		for(Tuple tuple : tuples) {
			sentences.add(text.substring(tuple.getBegin(), tuple.getEnd()));
		}
		
		return sentences;
	}
	
	/**
	 * @param stoplist
	 * @param text
	 * @param shortSentence
	 * @return
	 */
	public List<Tuple> getSentencesOffsets(List<Integer> stoplist, String text, int shortSentence) {
		
		List<Tuple> tuples = new ArrayList<Tuple>();
		
		int docLen = text.length();
		
		if(!stoplist.contains(docLen)) stoplist.add(docLen);
		
		// create sentences for each stop position
		int begin = 0;
		
		for(int end : stoplist) {

			end = Math.min(end, docLen);
			
			boolean lastStop = (end==docLen);

			// trim begin of sentence
			while( begin < docLen && 
					StringUtils.isWhitespace(				
							CharUtils.toString(text.charAt(begin))
					)
				) { begin++; }

			// trim end of sentence
			while( end>0 && StringUtils.isWhitespace(				
					CharUtils.toString(text.charAt(end-1))
					)
				) { end--; }

			// don't create too short sentences
			if(!lastStop && (end-begin) <= shortSentence) continue;
			
			// proper length check
			if (begin+1 < end) {

				tuples.add(new Tuple(begin, end));
				
				begin = Math.min(end+1, docLen);
				
			}
		}
		
		return tuples;
	}
	
	/**
	 * @author marc
	 *
	 * used to store offsets of annotations
	 */
	public class Tuple {
		int begin;
		int end;
		
		public Tuple(int a, int b) {
			begin = a;
			end = b;
		}
		
		public int getBegin() { return begin; }
		public int getEnd() { return end; }
	}
}
