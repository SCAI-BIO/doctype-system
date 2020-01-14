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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang.StringEscapeUtils;

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
import de.fraunhofer.scai.bio.types.text.doc.meta.Author;
import de.fraunhofer.scai.bio.types.text.doc.meta.Bibliography;
import de.fraunhofer.scai.bio.types.text.doc.meta.Date;
import de.fraunhofer.scai.bio.types.text.doc.meta.Keywords;
import de.fraunhofer.scai.bio.types.text.doc.meta.MetaElement;
import de.fraunhofer.scai.bio.types.text.doc.meta.PublicationType;
import de.fraunhofer.scai.bio.types.text.doc.meta.Reference;
import de.fraunhofer.scai.bio.types.text.doc.structure.Sentence;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * Renders a {@link Document} to a HTML document
 *
 * @author marc
 */
public class DocumentHTMLRenderer {

	/**
	 * converts the document text and all annotations into HTML
	 *
	 * @param docView <code>JCas</code>
	 * @return <code>String</code>
	 */
	public static String renderHTML(Document document) {

		StringBuilder sb = new StringBuilder();
		List<Annotation> annotations = new ArrayList<Annotation>();

		//sb.append("<!DOCTYPE html><html>");

		sb.append(renderDocumentElement(document, annotations));

		//sb.append("</html>");

		return sb.toString();
	}

	/**
	 * @param document
	 * @param annotations
	 * @return
	 */
	public static String renderDocumentElement(Document document, List<Annotation> annotations) {

		StringBuilder sb = new StringBuilder();

		if (document != null) {
			DocumentElement documentElement = document.getDocumentElement();

			if (documentElement != null) {
				sb.append("<head>");
				sb.append(renderMetaElement(document, documentElement.getMetaElement(), annotations));
				sb.append("</head>");

				sb.append("<body>");
				sb.append(renderFrontMatter(documentElement.getFrontMatter(), annotations));
				sb.append(renderBodyMatter(documentElement.getBodyMatter(), annotations));
				sb.append(renderBackMatter(documentElement.getBackMatter(), annotations));

				//		sb.append(renderAnnotations(document, annotations));

				sb.append("</body>");
			}
		}

		return sb.toString();
	}

	/**
	 * @param backMatter
	 * @param annotations
	 * @return
	 */
	public static String renderBackMatter(BackMatter backMatter, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (backMatter != null) {
			if (backMatter.getSections() != null && !backMatter.getSections().isEmpty()) {
				sb.append(renderSections(backMatter.getSections(), annotations));
			}

			if (backMatter.getBibliography() != null) {
				sb.append(renderBibliography(backMatter.getBibliography(), annotations));
			}
		}

		return sb.toString();
	}

	/**
	 * @param bibliography
	 * @param annotations
	 * @return
	 */
	public static String renderBibliography(Bibliography bibliography, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (bibliography != null) {

			sb.append(String.format("<div data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sec"));

			if (bibliography.getTitle() != null) {
				sb.append(String.format("<h%d>%s</h%d>", 2, escapeHTML(bibliography.getTitle(), annotations), 2));
			} else {
				sb.append(String.format("<h%d>References</h%d>", 2, 2));
			}

			if (bibliography.getReferences() != null) {
				for (String rid : bibliography.getReferences().keySet()) {
					sb.append("[<a id=\"RID_" + rid + "\">" + rid + "</a>] ");
					sb.append(renderReference(bibliography.getReferences().get(rid)));
					sb.append("<br>");
				}
			}

			sb.append(String.format("</div>"));
		}

		return sb.toString();
	}

	/**
	 * @param reference
	 */
	public static String renderReference(Reference reference) {

		StringBuilder sb = new StringBuilder();

		if (reference.getAuthors() != null) {
			for (Author author : reference.getAuthors()) {
				sb.append(renderAuthor(author));
				sb.append(". ");
			}
		}

		if (reference.getTitle() != null && reference.getTitle().getTitleText() != null) {
			sb.append(reference.getTitle().getTitleText().getText());
			sb.append(".");
		}

		reference.getDate();

		if (reference.getPublicationIds() != null) {
			sb.append(" ");
			for (TextElement pid : reference.getPublicationIds()) {
				sb.append(pid.getText() + " ");
			}
		}
		reference.getPublicationIds();
		reference.getReferenceSource();

		return sb.toString();
	}

	/**
	 * @param author
	 * @return
	 */
	public static String renderAuthor(Author author) {
		StringBuilder sb = new StringBuilder();

		if (author != null && author.getAuthor() != null) {
			if (author.getAuthor().getDegree() != null) {
				sb.append(author.getAuthor().getSurname() + " ");
			}
			if (author.getAuthor().getForename() != null) {
				sb.append(author.getAuthor().getForename() + " ");
			}
			if (author.getAuthor().getSurname() != null) {
				sb.append(author.getAuthor().getSurname());
			}
		}

		return sb.toString();
	}

	/**
	 * @param bodyMatter
	 * @param annotations
	 * @return
	 */
	public static String renderBodyMatter(BodyMatter bodyMatter, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (bodyMatter != null) {
			if (bodyMatter.getChapters() != null && bodyMatter.getChapters().isEmpty()) {
				sb.append(renderChapters(bodyMatter.getChapters(), annotations));
			}

			if (bodyMatter.getSections() != null && !bodyMatter.getSections().isEmpty()) {
				sb.append(renderSections(bodyMatter.getSections(), annotations));
			}
		}

		return sb.toString();
	}

	/**
	 * @param chapters
	 * @param annotations
	 * @return
	 */
	public static String renderChapters(List<Chapter> chapters, List<Annotation> annotations) {

		StringBuilder sb = new StringBuilder();

		if (chapters != null && !chapters.isEmpty()) {
			for (Chapter chapter : chapters) {
				sb.append(renderChapter(chapter, annotations));
			}
		}

		return sb.toString();
	}

	public static Object renderChapter(Chapter chapter, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (chapter != null) {
			sb.append(renderSections(chapter.getSections(), annotations));
		}

		return sb.toString();
	}

	/**
	 * @param frontMatter
	 * @param annotations
	 * @return
	 */
	public static String renderFrontMatter(FrontMatter frontMatter, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (frontMatter != null) {
			sb.append("<h1>");
			if (frontMatter.getTitleText() != null) {
				sb.append(escapeHTML(frontMatter.getTitleText(), annotations));
			}
			sb.append("</h1>");
			sb.append(renderAbstract(frontMatter.getDocumentAbstract(), annotations));
		}

		return sb.toString();
	}

	/**
	 * @param documentAbstract
	 * @param annotations
	 * @return
	 */
	public static String renderAbstract(Abstract documentAbstract, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("<div data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sec"));
		sb.append(renderSections(documentAbstract.getAbstractSections(), annotations));

		sb.append(String.format("</div>"));

		return sb.toString();
	}

	/**
	 * @param sections
	 * @param annotations
	 * @return
	 */
	public static String renderSections(List<Section> sections, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (sections != null) {
			for (Section section : sections) {
				sb.append(renderSection(section, annotations));
			}
		}

		return sb.toString();
	}

	/**
	 * @param section
	 * @param annotations
	 * @return
	 */
	public static String renderSection(Section section, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("<div data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sec"));

		if (section.getTitle() != null) {
			sb.append(String.format("<h%d>%s</h%d>", section.getDepth() + 2,
					escapeHTML(section.getTitle(), annotations), section.getDepth() + 2));
		}

		sb.append(renderParagraphs(section.getParagraphs(), annotations));

		sb.append(String.format("</div>"));

		return sb.toString();
	}

	/**
	 * @param paragraphs
	 * @param annotations
	 * @return
	 */
	public static String renderParagraphs(List<Paragraph> paragraphs, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (paragraphs != null && !paragraphs.isEmpty()) {
			for (Paragraph paragraph : paragraphs) {

				sb.append(String.format("<p data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "par"));

				if (paragraph != null && paragraph.getStructureElements() != null) {
					sb.append(renderStructureElements(paragraph.getStructureElements(), annotations));
				}

				sb.append(String.format("</p>"));
			}
		}
		return sb.toString();
	}

	/**
	 * @param structureElements
	 * @param annotations
	 * @return
	 */
	public static String renderStructureElements(List<StructureElement> structureElements,
			List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (structureElements != null) {
			for (StructureElement structureElement : structureElements) {
				if (structureElement.getCaptionedBox() != null) {
					sb.append(escapeHTML(structureElement.getCaptionedBox().getTitle(), annotations));
					sb.append("<br>");
					sb.append(escapeHTML(structureElement.getCaptionedBox().getCaption(), annotations));
					sb.append("<br>");

				} else if (structureElement.getCode() != null) {
					sb.append(escapeHTML(structureElement.getCode().getCode(), annotations));
					sb.append("<br>");

				} else if (structureElement.getDataTable() != null) {
					sb.append(escapeHTML(structureElement.getDataTable().getContent(), annotations));
					sb.append("<br>");

				} else if (structureElement.getFigure() != null) {
					sb.append(escapeHTML(structureElement.getFigure().getTitle(), annotations));
					sb.append("<br>");
					sb.append(escapeHTML(structureElement.getFigure().getCaption(), annotations));
					sb.append("<br>");

				} else if (structureElement.getFormula() != null) {
					sb.append(escapeHTML(structureElement.getFormula().getFormula(), annotations));

				} else if (structureElement.getOutline() != null) {
					sb.append(escapeHTML(structureElement.getOutline().getTitleText(), annotations));
					sb.append("<br>");

				} else if (structureElement.getQuotation() != null) {

					sb.append("(<a href=\"");
					sb.append("#RID_" + structureElement.getQuotation().getReferenceId());
					sb.append("\">");
					sb.append(escapeHTML(structureElement.getQuotation().getLabel(), annotations));
					sb.append("</a>)");
					sb.append(renderReference(structureElement.getQuotation().getReference(), annotations));

				} else if (structureElement.getTable() != null) {
					if(structureElement.getTable().getTitle() != null) {
						sb.append(escapeHTML(structureElement.getTable().getTitle(), annotations));
						sb.append("<br>");
					}

					if(structureElement.getTable().getText() != null) {
						sb.append(escapeHTML(structureElement.getTable().getText(), annotations));
						sb.append("<br>");
					}
					
					
					sb.append("<style type=\"text/css\">");
					sb.append(".tg  {border-collapse:collapse;border-spacing:0;border-color:#aabcfe;}");
					sb.append(".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;}");
					sb.append(".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;}");
					sb.append(".tg .tg-hmp3{background-color:#D2E4FC;text-align:left;vertical-align:top}");
					sb.append(".tg .tg-baqh{text-align:center;vertical-align:top}");
					sb.append(".tg .tg-mb3i{background-color:#D2E4FC;text-align:right;vertical-align:top}");
					sb.append(".tg .tg-lqy6{text-align:right;vertical-align:top}");
					sb.append(".tg .tg-0lax{text-align:left;vertical-align:top}");
				  sb.append("</style>");
				  
				  Map<Integer,String> cols = new HashMap<Integer, String>();
				  
					sb.append("<table class=\"tg\">");
				  if(structureElement.getTable().getHeaders() != null) {
					  int col =0;
				  	sb.append("<tr>");
						for(TextElement header : structureElement.getTable().getHeaders()) {
							sb.append("<th class=\"tg-baqh\">");
							sb.append(escapeHTML(header, annotations));
							sb.append("</th>");
							cols.put(col, header.getText());
							col++;
						}
				  	sb.append("</tr>");
					}
				  
				  boolean switchColor = true;
				  if(structureElement.getTable().getRows() != null) {
				  	for(Map<String, String> row : structureElement.getTable().getRows()) {
					  	sb.append("<tr>");
					  	for(int col=0; col<cols.size(); col++) {
					  		if(switchColor) sb.append("<td class=\"tg-hmp3\">"); 
					  		else 						sb.append("<td class=\"tg-0lax\">"); 
					  		sb.append(row.get(cols.get(col)));
						  	sb.append("</td>");
					  	}
					  	switchColor = !switchColor;
					  	sb.append("</tr>");				  		
				  	}
				  }
				  
					sb.append("</table>");
					

					if(structureElement.getTable().getCaption() != null) { 
						sb.append(escapeHTML(structureElement.getTable().getCaption(), annotations));
						sb.append("<br>");
					}

				} else if (structureElement.getTextElement() != null) {
					sb.append(escapeHTML(structureElement.getTextElement(), annotations));
					sb.append(" ");

				} else if (structureElement.getSentence() != null) {
					sb.append(String.format("<span data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sent"));
					sb.append("<u>");
					sb.append(escapeHTML(structureElement.getSentence().getText(), annotations));
					sb.append("</u>");
					sb.append(String.format("</span>"));
					sb.append(String.format(" "));

				} else if (structureElement.getList() != null) {
					if (structureElement.getList().getTitle() != null) {
						sb.append(escapeHTML(structureElement.getList().getTitle(), annotations));
						sb.append("<br>");
					}

					if (structureElement.getList().isBullets()) {
						sb.append("<ul>");
					} else {
						sb.append("<ol>");
					}

					for (TextElement element : structureElement.getList().getItems()) {
						sb.append("<li>");
						sb.append(element.getText());
						sb.append("</li>");
					}

					if (structureElement.getList().isBullets()) {
						sb.append("</ul>");
					} else {
						sb.append("</ol>");
					}
					sb.append("<br>");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * @param reference
	 * @param annotations
	 * @return
	 */
	public static String renderReference(Reference reference, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	/**
	 * @param sentences
	 * @param annotations
	 * @return
	 */
	public static String renderSentences(List<Sentence> sentences, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		if (sentences != null) {
			for (Sentence sentence : sentences) {

				sb.append(String.format("<span data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sent"));
				sb.append("<u>");
				sb.append(escapeHTML(sentence.getText(), annotations));
				sb.append("</u>");
				sb.append(String.format("</span>"));
				sb.append(String.format(" "));
			}
		}
		return sb.toString();
	}

	/**
	 * @param metaElement
	 * @param annotations
	 * @param document
	 * @return
	 */
	public static String renderMetaElement(Document document, MetaElement metaElement, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		sb.append("<title>");
		if (metaElement != null) {
			if (metaElement.getBibliographic() != null) {
				if (metaElement.getBibliographic().getTitle() != null) {
					sb.append(escapeHTML(metaElement.getBibliographic().getTitle().getTitleText(), annotations));
				}
			}
		}
		sb.append("</title>");


		if (metaElement.getBibliographic().getPublicationTypes() != null) {
			for (PublicationType pt : metaElement.getBibliographic().getPublicationTypes()) {
				sb.append(String.format("%s (%s) <br>", pt.getPublicationType().getText(), pt.getIdentifier().getText()));
			}
		}

		String date = "unspecified";
		if(metaElement != null && metaElement.getBibliographic() != null && metaElement.getBibliographic().getPubDate() != null) {
		    Calendar cal = Calendar.getInstance();
		    Date d = metaElement.getBibliographic().getPubDate();
		    cal.set(d.getYear(), d.getMonth(), d.getDay());
			date = new SimpleDateFormat("MMMM dd, yyyy").format(cal.getTime());
		}

		// identifiers
		try {
		sb.append(String.format("%s, %s (%s) [lang:%s], %s<br>",
				metaElement.getBibliographic().getSource().getText(), metaElement.getConcept().getAltLabel(), date,
				metaElement.getBibliographic().getLanguage().getText(), document.getDocType()));
		} catch (Exception e) {}
		
		if (metaElement.getConcept() != null && metaElement.getConcept().getAltLabels() != null) {
			for (TextElement alt : metaElement.getConcept().getAltLabels()) {
				sb.append(String.format("%s, ", alt.getText()));
			}
			sb.delete(sb.lastIndexOf(", "), sb.length());
			sb.append("<br>");
		}

		// authors
		if (metaElement.getBibliographic().getAuthors() != null) {
			for (Author author : metaElement.getBibliographic().getAuthors()) {
				if (author.getAuthor() != null && author.getAuthor().getSurname() != null && author.getAuthor().getForename() != null) {
					sb.append(String.format("%s, %s<br>", author.getAuthor().getSurname().getText(), author.getAuthor().getForename().getText()));
				}
				if (author.getOrganization() != null) {
					sb.append(String.format("%s<br>", author.getOrganization().getOrganization().getText()));
				}

			}
			sb.append("<br>");
		}

		// keywords
		if (document.getDocumentElement() != null
				&& document.getDocumentElement().getMetaElement() != null
				&& document.getDocumentElement().getMetaElement().getKeywords() != null) {
			for (Keywords kws : document.getDocumentElement().getMetaElement().getKeywords()) {
				sb.append(String.format("%s: ", kws.getRhetorical()));
				for (TextElement kw : kws.getKeywordList()) {
					sb.append(String.format("%s, ", kw.getText()));
				}
				sb.delete(sb.lastIndexOf(", "), sb.length());
				sb.append("<br>");
			}
		}

		return sb.toString();
	}

	/**
	 * convert a text element into proper HTML + insert all annotations
	 *
	 * @param textElement {@link TextElement}
	 * @param annotations
	 * @return {@link String}
	 */
	public static String escapeHTML(TextElement textElement, List<Annotation> annotations) {
		String text = textElement.getText();
		StringBuilder sb = new StringBuilder();
		Map<Float, String> insertions = new TreeMap<Float, String>();

		int lastChar = 0;
		if (textElement.getAnnotations() != null) {

			// order all spans tags to be inserted
			for (Annotation anno : textElement.getAnnotations()) {

				if (anno != null && anno.getEndOffset() > anno.getStartOffset()) {
					annotations.add(anno);
					int idx = annotations.size();

					Float f = new Float(anno.getStartOffset());
					while (insertions.containsKey(f)) {
						f += 0.001f; // collision of indices
					}
					insertions.put(f, String.format("<span data-id=\"%s\" class=\"%s\">", anno.getAnnotationText(),
							anno.getAnnotationType()));

					f = new Float(anno.getEndOffset());
					while (insertions.containsKey(f)) {
						f += 0.001f;
					}
					insertions.put(f, String.format("</span> <sup><a href=\"#anno%d\" title=\"%s:%s\">[%d]</a></sup>",
							idx, anno.getAnnotationType(), anno.getAnnotationText(), idx));
				}
			}

			for (Float key : insertions.keySet()) {
				int offset = (int) Math.floor(key);
				sb.append(text.substring(lastChar, offset)); // text up to insertion
				sb.append("$$$$" + key + "$$$$"); // insertion placeholder
				lastChar = offset;
			}
		}

		if(text != null && text.length()>lastChar) sb.append(text.substring(lastChar));

		String html = sb.toString();
		try {
			html = StringEscapeUtils.escapeHtml(html.trim()).replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n")
					.replaceAll("\n", "<br>") // eol to BR
					.replaceAll("\\s+", " "); // all white spaces to blank

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// insertions
		for (Float key : insertions.keySet()) {
			html = html.replace("$$$$" + key + "$$$$", insertions.get(key));
		}

		return html;
	}

	/**
	 * @param document
	 * @param annotations
	 * @return
	 */
	public static String renderAnnotations(Document document, List<Annotation> annotations) {
		StringBuilder sb = new StringBuilder();

		sb.append("<hr>");
		sb.append("<h2>Annotations</h2>");
		sb.append("<ul style=\"list-style-type:none\">");
		for (int i = 0; i < annotations.size(); i++) {
			Annotation anno = annotations.get(i);
			sb.append(String.format("<li id=\"anno%d\">[%d] <b>%s:%s</b> <i>%s@[%d,%d]</i></li>", i + 1, i + 1,
					anno.getAnnotationType(), anno.getAnnotationText(), anno.getProvenance().getSource(),
					anno.getStartOffset(), anno.getEndOffset()));
		}
		sb.append("</ol>");

		return sb.toString();
	}

}
