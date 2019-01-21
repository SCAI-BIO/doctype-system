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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
import de.fraunhofer.scai.bio.types.text.doc.meta.Author;
import de.fraunhofer.scai.bio.types.text.doc.meta.Bibliographic;
import de.fraunhofer.scai.bio.types.text.doc.meta.Bibliography;
import de.fraunhofer.scai.bio.types.text.doc.meta.Date;
import de.fraunhofer.scai.bio.types.text.doc.meta.Person;
import de.fraunhofer.scai.bio.types.text.doc.meta.Reference;
import de.fraunhofer.scai.bio.types.text.doc.structure.Sentence;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * @author tadams
 * @author marc
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
	 * @param document {@link Document}
	 * @return String of content words separated by blanks
	 */
	public static String renderTextContents(Document document) {

		StringBuilder sb = new StringBuilder();
		HashSet<String> contents = new HashSet<String>();

		// TODO to be discussed what should be indexed
		Map<String, TextElement> index = getFrontMatterTextElements(document);
		index.putAll(getBodyMatterTextElements(document));

		for (TextElement se : index.values()) {
			if (se != null && se.getText() != null) {
				Matcher unwantedMatcher = PUNCTUATION.matcher(se.getText());
				contents.add(unwantedMatcher.replaceAll(""));
			}

			// TODO if we want to index annotations
			if (se != null && se.getAnnotations() != null) {
				for (Annotation anot : se.getAnnotations()) {
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
		for (Section section : abstractSections) {
			if(section != null) {
				List<Paragraph> paragraphs = section.getParagraphs();
				for (Paragraph para : paragraphs) {
					if(para != null) {
						// either a sentence or a structure element is set
						if (para.getSentences() != null) {
							List<Sentence> sentences = para.getSentences();
							for (Sentence sentence : sentences) {
								sb.append(sentence.getText().getText());
								sb.append(" ");
							}
						} else {
							List<StructureElement> structureElements = para.getStructureElements();
							for (StructureElement se : structureElements) {
								sb.append(renderStructureElement(se));
							}
						}
					}
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
	public static String renderStructureElement(StructureElement se) {
		if(se != null) {
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
		}

		return null;
	}

	/**
	 * concatenate all contents of a list of {@link StructureElement}
	 * @param seList
	 * @return {@link String}
	 */
	public static String renderStructureElements(List<StructureElement> seList) {

		StringBuilder sb = new StringBuilder();

		if(seList != null && !seList.isEmpty()) {
			for(StructureElement se : seList) sb.append(renderStructureElement(se));
		}

		return sb.toString();
	}


	/**
	 * @param textelements
	 * @return
	 */
	public static String renderTextElements(List<TextElement> textelements) {
		StringBuilder sb = new StringBuilder();

		for(TextElement te : textelements) sb.append(te.getText());

		return sb.toString();

	}

	/**
	 * collect all {@link Annotation}s from all {@link TextElement}s into a
	 * {@link List}
	 * 
	 * @param document {@link Document}
	 * @return
	 */
	public static List<Annotation> getAllAnnotations(Document document) {

		List<Annotation> annotations = new ArrayList<Annotation>();

		Map<String, TextElement> elements = getDocumentTextElements(document);

		if (elements != null) {
			for (TextElement element : elements.values()) {
				if(element != null && element.getAnnotations() != null) {
					annotations.addAll(element.getAnnotations());
				}
			}
		}

		return annotations;
	}

	/**
	 * collect all {@link TextElement}s into a Map
	 * 
	 * @param document {@link Document}
	 * @return
	 */
	public static Map<String, TextElement> getDocumentTextElements(Document document) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		elements.putAll(getMetaElementTextElements(document));

		elements.putAll(getFrontMatterTextElements(document));
		elements.putAll(getBodyMatterTextElements(document));
		elements.putAll(getBackMatterTextElements(document));

		return elements;
	}

	public static Map<String, TextElement> getMetaElementTextElements(Document document) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		// TODO
		return elements;
	}

	/**
	 * 
	 * @param document {@link Document}
	 * @return
	 */
	public static Map<String, TextElement> getFrontMatterTextElements(Document document) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		DocumentElement de = document.getDocumentElement();

		if (de != null) {

			FrontMatter fm = de.getFrontMatter();
			if (fm != null) {
				addTextElement(elements, fm.getTitleText(), "FrontMatterTitle");

				Abstract abstr = fm.getDocumentAbstract();
				if (abstr != null) {
					elements.putAll(getSectionsTextElements(abstr.getAbstractSections()));
				}
			}
		}

		return elements;
	}

	/**
	 * 
	 * @param document {@link Document}
	 * @return
	 */
	public static Map<String, TextElement> getBodyMatterTextElements(Document document) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		DocumentElement de = document.getDocumentElement();

		if (de != null) {

			BodyMatter bom = de.getBodyMatter();
			if (bom != null) {
				elements.putAll(getChapterTextElements(bom.getChapters()));
				elements.putAll(getSectionsTextElements(bom.getSections()));
			}
		}

		return elements;
	}

	public static Map<String, TextElement> getChapterTextElements(List<Chapter> chapters) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		if (chapters != null) {
			for (Chapter chapter : chapters) {
				if(chapter != null) {
					elements.putAll(getSectionsTextElements(chapter.getSections()));
				}
			}
		}

		return elements;
	}

	public static Map<String, TextElement> getSectionsTextElements(List<Section> sections) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		if (sections != null) {
			for (Section sec : sections) {
				if(sec != null) {
					if (sec.getTitle() != null) {
						addTextElement(elements, sec.getTitle(), "SectionTitle" + sec.getDepth());
					}
					if (sec.getParagraphs() != null)
						elements.putAll(getParagraphsTextElements(sec.getParagraphs()));
				}
			}
		}

		return elements;
	}

	public static Map<String, TextElement> getParagraphsTextElements(List<Paragraph> paragraphs) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		if (paragraphs != null) {
			for (Paragraph par : paragraphs) {
				if(par != null) {
					if (par.getSentences() != null) {
						elements.putAll(getSentencesTextElements(par.getSentences()));
					}
					if (par.getStructureElements() != null) {
						elements.putAll(getStructureElementsTextElements(par.getStructureElements()));
					}
				}
			}
		}

		return elements;
	}

	public static Map<String, TextElement> getSentencesTextElements(List<Sentence> sentences) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		if (sentences != null) {
			for (Sentence sentence : sentences) {
				if(sentence != null) {
					addTextElement(elements, sentence.getText(), "Sentence");
				}
			}
		}

		return elements;
	}

	public static Map<String, TextElement> getStructureElementsTextElements(List<StructureElement> structureElements) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		if (structureElements != null) {
			for (StructureElement sel : structureElements) {
				if(sel != null) {
					if (sel.getCaptionedBox() != null) {
						addTextElement(elements, sel.getCaptionedBox().getTitle(), "CaptionedBoxTitle");
						addTextElement(elements, sel.getCaptionedBox().getCaption(), "CaptionedBoxCaption");

					} else if (sel.getCode() != null) {
						addTextElement(elements, sel.getCode().getCode(), "Code");

					} else if (sel.getDataTable() != null) {
						addTextElement(elements, sel.getDataTable().getContent(), "DataTableContent");

					} else if (sel.getFigure() != null) {
						addTextElement(elements, sel.getFigure().getTitle(), "FigureTitle");
						addTextElement(elements, sel.getFigure().getCaption(), "FigureCaption");

					} else if (sel.getFormula() != null) {
						addTextElement(elements, sel.getFormula().getFormula(), "Formula");

					} else if (sel.getOutline() != null) {
						addTextElement(elements, sel.getOutline().getTitleText(), "OutlineTitleText");

					} else if (sel.getQuotation() != null) {
						elements.putAll(getReferenceTextElements(sel.getQuotation().getReference()));

					} else if (sel.getTable() != null) {
						addTextElement(elements, sel.getTable().getTitle(), "TableTitle");
						addTextElement(elements, sel.getTable().getText(), "TableText");
						addTextElement(elements, sel.getTable().getCaption(), "TableCaption");

					} else if (sel.getTextElement() != null) {
						addTextElement(elements, sel.getTextElement(), "TextElement");

					} else if (sel.getList() != null) {
						addTextElement(elements, sel.getList().getTitle(), "ListTitle");
						for(TextElement element : sel.getList().getItems()) {
							addTextElement(elements, element, "ListElement");							
						}
					}

				}
			}
		}

		return elements;
	}

	/**
	 * 
	 * @param document {@link Document}
	 * @return
	 */
	public static Map<String, TextElement> getBackMatterTextElements(Document document) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		DocumentElement de = document.getDocumentElement();

		if (de != null) {
			BackMatter bam = de.getBackMatter();

			if (bam != null) {
				Bibliography bib = bam.getBibliography();

				if (bib != null) {
				    Map<String, Reference> refs = bib.getReferences();
				    
				    	if (refs != null) {
						for (Reference ref : refs.values()) {
							elements.putAll(getReferenceTextElements(ref));
						}
				    	}

				}
			}
		}

		return elements;
	}

	/**
	 * @param reference
	 * @return
	 */
	public static Map<String, TextElement> getReferenceTextElements(Reference reference) {
		Map<String, TextElement> elements = new TreeMap<String, TextElement>();

		if (reference != null) {
			if (reference.getTitle() != null) {
				if (reference.getTitle().getTitleText() != null) {
					addTextElement(elements, reference.getTitle().getTitleText(), "TitleText");
				}
				if (reference.getTitle().getTitleText() != null) {
					addTextElement(elements, reference.getTitle().getSubtitleText(), "SubtitleText");
				}
			}
			if (reference.getLanguage() != null) {
				addTextElement(elements, reference.getLanguage(), "Language");
			}
			if(reference.getPublicationIds() != null) {
				for(TextElement element : reference.getPublicationIds()) {
					addTextElement(elements, element, "PublicationId");					
				}
			}
			if(reference.getPublicationType() != null) {
				addTextElement(elements, reference.getPublicationType(), "PublicationType");
			}
			if(reference.getReferenceSource() != null) {
				addTextElement(elements, reference.getReferenceSource(), "ReferenceSource");
			}

			// TODO
			reference.getAuthors();
		}

		return elements;
	}

	/**
	 * create a nice key for each {@link TextElement} and place in {@link Map}
	 * 
	 * @param elements    Map<String, TextElement>
	 * @param textElement {@link TextElement}
	 * @param title       of text element {@link String}
	 */
	private static void addTextElement(Map<String, TextElement> elements, TextElement textElement, String title) {
		if (textElement != null && textElement.getUuid() != null) {
			elements.put(String.format("%s\t*%s*", textElement.getUuid(), title.toUpperCase()), textElement);
		}
	}

	/**
	 * @param reference
	 * @return
	 */
	public static String renderReference(Reference reference) {
		StringBuilder sb = new StringBuilder();

		if(reference.getAuthors() != null) {
			for(Author author : reference.getAuthors()) {
				if(author.getAuthor() != null) { 
					sb.append(renderPerson(author.getAuthor())); 
					sb.append(", ");
				}
				if(author.getOrganization() != null) { 
					sb.append(author.getOrganization().getOrganization().getText()); 
					sb.append(", ");
				}			
			}

			sb.replace(sb.length()-2, sb.length(), ". ");
		}

		if(reference.getTitle() != null) { 
			sb.append( reference.getTitle().getTitleText().getText() );
		}

		if(reference.getReferenceSource() != null) {
			if(reference.getTitle() != null) {
				if(!sb.substring(sb.length()-1).equals("?")) sb.append(". ");
				else sb.append(" ");
			}

			sb.append( reference.getReferenceSource().getText() );
		}


		if(reference.getDate() != null) { sb.append( ", "); sb.append( renderDate(reference.getDate())); }

		if(reference.getPublicationIds() != null) {
			for(TextElement te : reference.getPublicationIds()) {
				sb.append(" ");
				sb.append(te.getText());
			}
		}

		reference.getPublicationType();		

		sb.append( ".");

		return sb.toString();
	}

	/**
	 * @param date
	 * @return
	 */
	public static String renderDate(Date date) {
		StringBuilder sb = new StringBuilder();

		if(date.getYear()>0) sb.append(date.getYear());
		if(date.getMonth()>0) { sb.append("-"); sb.append(date.getMonth()); }
		if(date.getDay()>0) { sb.append("-"); sb.append(date.getDay()); }

		return sb.toString();
	}

	/**
	 * @param author
	 * @return
	 */
	public static String renderPerson(Person author) {
		StringBuilder sb = new StringBuilder();

		if(author.getDegree() != null)  { sb.append(author.getDegree()); sb.append(" "); }
		if(author.getSurname() != null) { sb.append(author.getSurname()); }
		if(author.getForename() != null){ sb.append(" "); sb.append(author.getForename()); }

		return sb.toString();
	}
}
