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

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang.StringEscapeUtils;

import de.fraunhofer.scai.bio.Document;
import de.fraunhofer.scai.bio.types.text.doc.DocumentElement;
import de.fraunhofer.scai.bio.types.text.doc.container.BackMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.BodyMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.FrontMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.Paragraph;
import de.fraunhofer.scai.bio.types.text.doc.container.Section;
import de.fraunhofer.scai.bio.types.text.doc.container.StructureElement;
import de.fraunhofer.scai.bio.types.text.doc.meta.Abstract;
import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;
import de.fraunhofer.scai.bio.types.text.doc.meta.MetaElement;
import de.fraunhofer.scai.bio.types.text.doc.meta.Reference;
import de.fraunhofer.scai.bio.types.text.doc.structure.Sentence;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * Renders a {@link Document} to a HTML document
 * 
 * @author marc
 *
 */
public class DocumentHTMLRenderer {
	
	/**
	 * converts the document text and all annotations into HTML
	 * @param docView <code>JCas</code>
	 * @return <code>String</code>
	 */
	public static String renderHTML(Document document) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE html><html>");
		
		sb.append( renderDocumentElement(document.getDocumentElement()) );
		
		sb.append("</html>"); 

		return sb.toString();
	}

	

	public static String renderDocumentElement(DocumentElement documentElement) {

		StringBuilder sb = new StringBuilder();


		sb.append("<head>");
		sb.append( renderMetaElement(documentElement.getMetaElement()) );
		sb.append("</head>");
		
		sb.append("<body>");
		sb.append( renderFrontMatter(documentElement.getFrontMatter()) );
		sb.append( renderBodyMatter(documentElement.getBodyMatter()) );
		sb.append( renderBackMatter(documentElement.getBackMatter()) );
		sb.append("</body>");
			
				
				
		return sb.toString();
	}



	public static String renderBackMatter(BackMatter backMatter) {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}



	public static String renderBodyMatter(BodyMatter bodyMatter) {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}



	public static String renderFrontMatter(FrontMatter frontMatter) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>");
		if(frontMatter.getTitleText() != null) {
			sb.append( escapeHTML( frontMatter.getTitleText()) );
		}
		sb.append("</h1>");
		sb.append( renderAbstract(frontMatter.getDocumentAbstract()) );
		
		return sb.toString();
	}



	public static String renderAbstract(Abstract documentAbstract) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("<div data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sec"));
		sb.append(String.format("<h2>Abstract</h2>"));

		sb.append( renderSections(documentAbstract.getAbstractSections()) );

		sb.append(String.format("</div>"));

		return sb.toString();
	}



	public static String renderSections(List<Section> sections) {
		StringBuilder sb = new StringBuilder();
		
		if(sections != null) {
			for(Section section : sections) {
				sb.append( renderSection(section) );
			}
		}
		
		return sb.toString();
	}



	public static String renderSection(Section section) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("<div data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sec"));
		
		if(section.getTitle() != null) {
			sb.append(String.format("<h%d>%s</h%d>", 
					section.getDepth()+2, 
					escapeHTML(section.getTitle())) 
					);
		}

		sb.append( renderParagraphs(section.getParagraphs()) );
		
		sb.append(String.format("</div>"));
		
		return sb.toString();
	}



	public static String renderParagraphs(List<Paragraph> paragraphs) {
		StringBuilder sb = new StringBuilder();
		
		if(paragraphs != null) {
			for(Paragraph paragraph : paragraphs) {
				
				sb.append(String.format("<p data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "par"));
				
				if(paragraph.getSentences() != null) {
					sb.append(renderSentences(paragraph.getSentences()) );
				}
				if(paragraph.getStructureElements() != null) {
					sb.append(renderStructureElements(paragraph.getStructureElements()) );					
				}
				
				sb.append(String.format("</p>"));
			}
		}
		return sb.toString();
	}



	public static String renderStructureElements(List<StructureElement> structureElements) {
		StringBuilder sb = new StringBuilder();
		
		if(structureElements != null) {
			for(StructureElement structureElement : structureElements) {
  			if (structureElement.getCaptionedBox() != null) {
  				escapeHTML(structureElement.getCaptionedBox().getTitle());
  				sb.append("<br>");
  				escapeHTML(structureElement.getCaptionedBox().getCaption());
  				sb.append("<br>");
  				
  			} else if (structureElement.getCode() != null) {
  				escapeHTML(structureElement.getCode().getCode());
  				sb.append("<br>");
  				
  			} else if (structureElement.getDataTable() != null) {
  				escapeHTML(structureElement.getDataTable().getContent());
  				sb.append("<br>");
  				
  			} else if (structureElement.getFigure() != null) {
  				escapeHTML(structureElement.getFigure().getTitle());
  				sb.append("<br>");
  				escapeHTML(structureElement.getFigure().getCaption());
  				sb.append("<br>");
  				
  			} else if (structureElement.getFormula() != null) {
  				escapeHTML(structureElement.getFormula().getFormula());
  				
  			} else if (structureElement.getOutline() != null) {
  				escapeHTML(structureElement.getOutline().getTitleText());
  				sb.append("<br>");
  				
  			} else if (structureElement.getQuotation() != null) {
  				sb.append( renderReference(structureElement.getQuotation().getReference()) );
  				
  			} else if (structureElement.getTable() != null) {
  				escapeHTML(structureElement.getTable().getTitle());
  				sb.append("<br>");
  				escapeHTML(structureElement.getTable().getText());
  				sb.append("<br>");
  				escapeHTML(structureElement.getTable().getCaption());
  				sb.append("<br>");
  				
  			} else if (structureElement.getTextElement() != null)
  				escapeHTML(structureElement.getTextElement());
  		}
		}
		return sb.toString();
	}



	public static String renderReference(Reference reference) {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}



	public static String renderSentences(List<Sentence> sentences) {
		StringBuilder sb = new StringBuilder();
		
		if(sentences != null) {
			for(Sentence sentence : sentences) {
				
				sb.append(String.format("<span data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sent"));
				sb.append(escapeHTML(sentence.getText()));
				sb.append(String.format("</span>"));
				sb.append(String.format(" "));
			}
		}
		return sb.toString();
	}



	public static String renderMetaElement(MetaElement metaElement) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<title>");
		if(metaElement != null) {
			if(metaElement.getBibliographic() != null) {
				if(metaElement.getBibliographic().getTitle() != null) {
					sb.append( escapeHTML(metaElement.getBibliographic().getTitle().getTitleText()) );
				}
			}
		}
		sb.append("</title>");

		return sb.toString();
	}



	/**
	 * convert a text element into proper HTML
	 * + insert all annotations
	 *  
	 * @param textElement {@link TextElement}
	 * @return {@link String}
	 */
	public static String escapeHTML(TextElement textElement) {
		String text = textElement.getText();
		StringBuilder sb = new StringBuilder();
		Map<Float, String> insertions = new TreeMap<Float, String>();
		
		int lastChar = 0;
		if(textElement.getAnnotations() != null) {
			
			// order all spans tags to be inserted
			for(Annotation anno : textElement.getAnnotations()) {
				Float f = new Float(anno.getStartOffset());
				while(insertions.containsKey(f)) f += 0.001f;		// collision of indices
				insertions.put(f, String.format("<span data-id=\"%s\" class=\"%s\">", anno.getAnnotationText(), anno.getAnnotationType()));
				
				f = new Float(anno.getEndOffset());
				while(insertions.containsKey(f)) f += 0.001f;
				insertions.put(f, "</span>");
			}
			
			for(Float key : insertions.keySet()) {
					int offset = (int)Math.floor(key); 
					sb.append(text.substring(lastChar, offset));	// text up to insertion
					sb.append("$$$$"+key+"$$$$");									// insertion placeholder
					lastChar = offset;
			}
		}
		
		sb.append(text.substring(lastChar));
		
		String html=sb.toString();
		try {
			html = StringEscapeUtils.escapeHtml(html.trim())
					.replaceAll("\\r\\n", "\n")
					.replaceAll("\\r", "\n")
					.replaceAll("\n", "<br>")	// eol to BR
					.replaceAll("\\s+", " ");	// all white spaces to blank
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// insertions
		for(Float key : insertions.keySet()) {
			html = html.replace("$$$$"+key+"$$$$", insertions.get(key));
		}
		
		return html;
	}

}
