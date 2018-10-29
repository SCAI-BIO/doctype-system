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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.fraunhofer.scai.bio.Document;
import de.fraunhofer.scai.bio.types.text.doc.container.StructureElement;
import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;

/**
 * @author tadams
 * 
 *         This class provides functionality to render document contents in
 *         different formats by traversing the {@link Document} structure
 *
 */
public class DocumentRenderer {
    
    private static final Pattern PUNCTUATION = Pattern.compile("[.!?\\;]");

    /**
     * Assemble all document contents which can be written as String, return them as
     * plain text list seperated by blanks. Used for indexiatin in Solr.
     * 
     * @param doc <code>Document</code>
     * @return String of conent words seperated by blanks 
     */
    public String renderTextContents(Document doc) {

	StringBuilder sb = new StringBuilder();
	HashSet<String> contents = new HashSet<String>();	
	Map<String, StructureElement> index = doc.getStructureElementIndex();
	
	for (StructureElement se : index.values()) {
	    Matcher unwantedMatcher = PUNCTUATION.matcher(se.getText());
	    contents.add(unwantedMatcher.replaceAll(""));
	    for (Annotation anno : se.getAnnotations()) {
		contents.add(anno.getAnnotationText());
	    }    
	}	
	for(String word : contents) {
	    sb.append(word + " ");
	}	
	return sb.toString();
    }

}
