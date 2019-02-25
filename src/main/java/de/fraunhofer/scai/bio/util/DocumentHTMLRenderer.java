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

    public static String renderDocumentElement(Document document, List<Annotation> annotations) {

	StringBuilder sb = new StringBuilder();

	if (document != null) {
	    DocumentElement documentElement = document.getDocumentElement();

	    if (documentElement != null) {
//		sb.append("<head>");
//		sb.append(renderMetaElement(documentElement.getMetaElement(), annotations));
//		sb.append("</head>");

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
    private static String renderBibliography(Bibliography bibliography, List<Annotation> annotations) {
	StringBuilder sb = new StringBuilder();

	if (bibliography != null) {

	    sb.append(String.format("<div data-id=\"%s\" class=\"%s\">", UUID.randomUUID().toString(), "sec"));

	    if (bibliography.getTitle() != null) {
		sb.append(String.format("<h%d>%s</h%d>", 2, escapeHTML(bibliography.getTitle(), annotations), 2));
	    }

	    if (bibliography.getReferences() != null) {
		for (String rid : bibliography.getReferences().keySet()) {
		    sb.append(rid + " ");
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
	    if (author.getAuthor().getDegree() != null)
		sb.append(author.getAuthor().getSurname() + " ");
	    if (author.getAuthor().getForename() != null)
		sb.append(author.getAuthor().getForename() + " ");
	    if (author.getAuthor().getSurname() != null)
		sb.append(author.getAuthor().getSurname());
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
	sb.append(String.format("<h2>Abstract</h2>"));

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

		if (paragraph.getSentences() != null) {
		    sb.append(renderSentences(paragraph.getSentences(), annotations));
		}
		if (paragraph.getStructureElements() != null) {
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
		    sb.append(renderReference(structureElement.getQuotation().getReference(), annotations));

		} else if (structureElement.getTable() != null) {
		    sb.append(escapeHTML(structureElement.getTable().getTitle(), annotations));
		    sb.append("<br>");
		    sb.append(escapeHTML(structureElement.getTable().getText(), annotations));
		    sb.append("<br>");
		    sb.append(escapeHTML(structureElement.getTable().getCaption(), annotations));
		    sb.append("<br>");

		} else if (structureElement.getTextElement() != null) {
		    sb.append(escapeHTML(structureElement.getTextElement(), annotations));
		    sb.append(" ");

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
		sb.append(escapeHTML(sentence.getText(), annotations));
		sb.append(String.format("</span>"));
		sb.append(String.format(" "));
	    }
	}
	return sb.toString();
    }

    /**
     * @param metaElement
     * @param annotations
     * @return
     */
    public static String renderMetaElement(MetaElement metaElement, List<Annotation> annotations) {
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
		    while (insertions.containsKey(f))
			f += 0.001f; // collision of indices
		    insertions.put(f, String.format("<span data-id=\"%s\" class=\"%s\">", anno.getAnnotationText(),
			    anno.getAnnotationType()));

		    f = new Float(anno.getEndOffset());
		    while (insertions.containsKey(f))
			f += 0.001f;
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

	sb.append(text.substring(lastChar));

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
