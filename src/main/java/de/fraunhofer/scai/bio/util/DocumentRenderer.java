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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.fraunhofer.scai.bio.Document;
import de.fraunhofer.scai.bio.types.text.doc.DocumentElement;
import de.fraunhofer.scai.bio.types.text.doc.container.BackMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.BodyMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.Chapter;
import de.fraunhofer.scai.bio.types.text.doc.container.FrontMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.Paragraph;
import de.fraunhofer.scai.bio.types.text.doc.container.Section;
import de.fraunhofer.scai.bio.types.text.doc.container.StructureElement;
import de.fraunhofer.scai.bio.types.text.doc.meta.Abstract;
import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;
import de.fraunhofer.scai.bio.types.text.doc.meta.Bibliographic;
import de.fraunhofer.scai.bio.types.text.doc.meta.Bibliography;
import de.fraunhofer.scai.bio.types.text.doc.meta.Reference;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

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
     * plain text list separated by blanks. Used for indexing in Solr.
     * 
     * @param doc <code>Document</code>
     * @return String of content words separated by blanks
     */
    public static String renderTextContents(Document doc) {

    	StringBuilder sb = new StringBuilder();
    	HashSet<String> contents = new HashSet<String>();
    	Map<UUID, TextElement> index = doc.getTextElementIndex();

    	for (TextElement se : index.values()) {
    		Matcher unwantedMatcher = PUNCTUATION.matcher(se.getText());
    		contents.add(unwantedMatcher.replaceAll(""));

    		// TODO if we want to index annotations
    		if (se.getAnnotations() != null) {
    			for(Annotation anot : se.getAnnotations()) {
    				contents.add(anot.getAnnotationText());			
    			}
    		}
    	}
    	
    	for (String word : contents) {
    		sb.append(word + " ");
    	}
    	return sb.toString();
    }

    /**
     * Get the <code>Document</code> {@link Abstract} in plain text format 
     * 
     * @param doc input <code>Document</code>
     * @return Abstract String
     */
    public static String getDocumentAbstract(Document doc) {	
	StringBuilder sb = new StringBuilder();
	Bibliographic bib = doc.getDocumentElement().getMetaElement().getBibliographic();
	List<Section> abstractSections = bib.getDocumentAbstract().getAbstractSections();
	for(Section section : abstractSections) {
	    List<Paragraph> paragraphs = section.getParagraphs();
	    	for (Paragraph para : paragraphs) {
	    	    List<StructureElement> structureElements = para.getStructureElements();
	    	    for (StructureElement se : structureElements) {
			sb.append(getText(se));
		    }
		}
	    }
	return sb.toString();
    }

    /**
     * go through all fields and extract from the non-empty one the text
     * 
     * @return <code>String</code>
     */
    public static String getText(StructureElement se) {
	if (se.getCaptionedBox() != null)
	    return se.getCaptionedBox().toString();
	if (se.getCode() != null)
	    return se.getCode().toString();
	if (se.getDataTable() != null)
	    return se.getDataTable().toString();
	if (se.getFigure() != null)
	    return se.getFigure().toString();
	if (se.getFormula() != null)
	    return se.getFormula().toString();
	if (se.getImageContent() != null)
	    return se.getImageContent().toString();
	if (se.getOutline() != null)
	    return se.getOutline().toString();
	if (se.getQuotation() != null)
	    return se.getQuotation().toString();
	if (se.getTable() != null)
	    return se.getTable().toString();
	if (se.getTextElement() != null)
	    return se.getTextElement().toString();

	return null;
    }
    

    public static Map<UUID, TextElement> getDocumentTextElements(Document document) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();

    	elements.putAll(getMetaElementTextElements(document));

    	elements.putAll(getFrontMatterTextElements(document));
    	elements.putAll(getBodyMatterTextElements(document));
    	elements.putAll(getBackMatterTextElements(document));
    	
    	return elements;
    }

    public static Map<UUID, TextElement> getMetaElementTextElements(Document document) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();

    	// TODO
    	return elements;
		}

		/**
     * 
     * @param document<code>Document</code>
     * @return
     */
    public static Map<UUID, TextElement> getFrontMatterTextElements(Document document) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();

    	DocumentElement de = document.getDocumentElement();

    	if(de != null) {
    		
    		FrontMatter fm = de.getFrontMatter();
    		if(fm != null) {
    			elements.put(fm.getTitleText().getUuid(), fm.getTitleText());

    			Abstract abstr = fm.getDocumentAbstract();
    			if(abstr != null) {
    				elements.putAll(getSectionsTextElements(abstr.getAbstractSections()) );
    			}
    		}
    	}
    	
    	return elements;
    }

    /**
     * 
     * @param document<code>Document</code>
     * @return
     */
    public static Map<UUID, TextElement> getBodyMatterTextElements(Document document) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();

    	DocumentElement de = document.getDocumentElement();

    	if(de != null) {
    		
    		BodyMatter bom = de.getBodyMatter();
    		if(bom != null) {
    			elements.putAll( getChapterTextElements(bom.getChapter()) );
    			elements.putAll( getSectionsTextElements(bom.getSections()) );
    		}
    	}

    	return elements;
    }

    public static Map<UUID, TextElement> getChapterTextElements(List<Chapter> chapters) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();
    	
    	if(chapters != null) {
    		for(Chapter chapter : chapters) {
    			elements.putAll(getSectionsTextElements(chapter.getSections()) );
    		}
    	}
    	
			return elements;
		}

    public static Map<UUID, TextElement> getSectionsTextElements(List<Section> sections) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();
    	
    	if(sections != null) {
    		for(Section sec : sections) {
    			if(sec.getTitle() != null) {
    				elements.put(sec.getTitle().getUuid(), sec.getTitle());
    			}
    			elements.putAll( getParagraphsTextElements(sec.getParagraphs()) );
    		}
    	}
    	
			return elements;
		}

    public static Map<UUID, TextElement> getParagraphsTextElements(List<Paragraph> paragraphs) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();
    	
    	if(paragraphs != null) {
    		for(Paragraph par : paragraphs) {
    			elements.putAll(getStructureElementsTextElements(par.getStructureElements()) );
    		}
    	}
    	
			return elements;
		}

    public static Map<UUID, TextElement> getStructureElementsTextElements(List<StructureElement> structureElements) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();
    
    	if(structureElements != null) {
    		for(StructureElement sel : structureElements) {
    			if (sel.getCaptionedBox() != null) {
    				elements.put(sel.getCaptionedBox().getTitle().getUuid(), sel.getCaptionedBox().getTitle());
    				elements.put(sel.getCaptionedBox().getCaption().getUuid(), sel.getCaptionedBox().getCaption());
    				
    			} else if (sel.getCode() != null) {
    				elements.put(sel.getCode().getCode().getUuid(), sel.getCode().getCode());
    				
    			} else if (sel.getDataTable() != null) {
    				elements.put(sel.getDataTable().getContent().getUuid(), sel.getDataTable().getContent());
    				
    			} else if (sel.getFigure() != null) {
    				elements.put(sel.getFigure().getTitle().getUuid(), sel.getFigure().getTitle());
    				elements.put(sel.getFigure().getCaption().getUuid(), sel.getFigure().getCaption());
    				
    			} else if (sel.getFormula() != null) {
    				elements.put(sel.getFormula().getFormula().getUuid(), sel.getFormula().getFormula());
    				
    			} else if (sel.getOutline() != null) {
    				elements.put(sel.getOutline().getTitleText().getUuid(), sel.getOutline().getTitleText());
    				
    			} else if (sel.getQuotation() != null) {
    				elements.putAll( getReferenceTextElements(sel.getQuotation().getReference()) );
    				
    			} else if (sel.getTable() != null) {
    				elements.put(sel.getTable().getTitle().getUuid(), sel.getTable().getTitle());
    				elements.put(sel.getTable().getText().getUuid(), sel.getTable().getText());
    				elements.put(sel.getTable().getCaption().getUuid(), sel.getTable().getCaption());
    				
    			} else if (sel.getTextElement() != null)
    				elements.put(sel.getTextElement().getUuid(), sel.getTextElement());
    		}
    	}
    	
			return elements;
		}

    
		/**
     * 
     * @param document {@link Document}
     * @return
     */
    public static Map<UUID, TextElement> getBackMatterTextElements(Document document) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();

    	DocumentElement de = document.getDocumentElement();
    	
    	if(de != null) {
    		BackMatter bam = de.getBackMatter();

    		if(bam != null) {
    			Bibliography bib = bam.getBibliography();

    			if(bib != null) {
    				for(Reference ref : bib.getReferences()) {
    					elements.putAll(getReferenceTextElements(ref));
    				}
    			}
    		}
    	}

    	return elements;
    }

    public static Map<UUID, TextElement> getReferenceTextElements(Reference reference) {
    	Map<UUID, TextElement> elements = new TreeMap<UUID, TextElement>();

    	if(reference != null) {
    		if(reference.getTitle() != null) {
    			if(reference.getTitle().getTitleText() != null) {
    				elements.put(reference.getTitle().getTitleText().getUuid(), reference.getTitle().getTitleText());
    			}
    			if(reference.getTitle().getTitleText() != null) {
    				elements.put(reference.getTitle().getSubtitleText().getUuid(), reference.getTitle().getSubtitleText());
    			}
    		}

    		// TODO
    		reference.getAuthors();
    		reference.getDoi();
    		reference.getIsbn();
    	}

    	return elements;
    }
    
}

