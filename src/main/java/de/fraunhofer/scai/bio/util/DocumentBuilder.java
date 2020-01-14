/*
 * Copyright 2018 Fraunhofer Institute SCAI, St. Augustin, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.fraunhofer.scai.bio.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fraunhofer.scai.bio.Document;
import de.fraunhofer.scai.bio.types.text.doc.DocumentElement;
import de.fraunhofer.scai.bio.types.text.doc.container.BackMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.BodyMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.FrontMatter;
import de.fraunhofer.scai.bio.types.text.doc.container.Paragraph;
import de.fraunhofer.scai.bio.types.text.doc.container.Section;
import de.fraunhofer.scai.bio.types.text.doc.container.StructureElement;
import de.fraunhofer.scai.bio.types.text.doc.meta.Abstract;
import de.fraunhofer.scai.bio.types.text.doc.meta.Affiliation;
import de.fraunhofer.scai.bio.types.text.doc.meta.Author;
import de.fraunhofer.scai.bio.types.text.doc.meta.Bibliographic;
import de.fraunhofer.scai.bio.types.text.doc.meta.Bibliography;
import de.fraunhofer.scai.bio.types.text.doc.meta.Concept;
import de.fraunhofer.scai.bio.types.text.doc.meta.Date;
import de.fraunhofer.scai.bio.types.text.doc.meta.Keywords;
import de.fraunhofer.scai.bio.types.text.doc.meta.License;
import de.fraunhofer.scai.bio.types.text.doc.meta.MetaElement;
import de.fraunhofer.scai.bio.types.text.doc.meta.Person;
import de.fraunhofer.scai.bio.types.text.doc.meta.Reference;
import de.fraunhofer.scai.bio.types.text.doc.meta.Title;
import de.fraunhofer.scai.bio.types.text.doc.structure.Figure;
import de.fraunhofer.scai.bio.types.text.doc.structure.ImageContent;
import de.fraunhofer.scai.bio.types.text.doc.structure.Sentence;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * a general class to compose a document from scratch, allows to create sections, paragraphs,
 * sentences, tables, ...
 * <p>
 * - first: call constructor - second: create and append document elements - finally: call
 * finalizeCAS to set all indices and document text
 *
 * @author marc
 */
/**
 * @author marc
 *
 */
public class DocumentBuilder {

    protected static Logger logger = LoggerFactory.getLogger(DocumentBuilder.class);

    private SentenceDetector sentenceDetector;

    public DocumentBuilder() {

        sentenceDetector = new SentenceDetector(0);
    }

    public Author createAuthor(String forename, String surname) {
    	  TextElement forenameTE = createTextElement(forename);
    	  TextElement surnameTE = createTextElement(surname);
    	  Person person = new Person();
    	  person.setForename(forenameTE);
    	  person.setSurname(surnameTE);
    	  Author author = new Author();
    	  author.setAuthor(person);
    	  return author;
    }

    /**
     * @param titleTextElement
     * @return
     */
    public Title createDocumentTitle(TextElement titleTextElement, TextElement subTitleText) {

        Title dTitle = null;

        if (titleTextElement != null && titleTextElement.getText() != null && titleTextElement.getText().length() > 0) {

            dTitle = new Title();
            dTitle.setTitleText(titleTextElement);
        }

        if (subTitleText != null && subTitleText.getText() != null && subTitleText.getText().length() > 0) {

            if (dTitle == null) {
                dTitle = new Title();
            }
            dTitle.setSubtitleText(subTitleText);
        }

        return dTitle;
    }

    /**
     * @param text
     * @return
     */
    public Section createSection(String text) {
        return createSection(text, text);
    }

    /**
     * no spacer after paragraphs, no sentences
     *
     * @param text
     * @param rhetorical
     * @param titleText
     * @return
     */
    public Section createSimpleSection(String text, String rhetorical, String title) {

        Section dSection = new Section();
        TextElement rhetoricalElement = new TextElement();
        rhetoricalElement.setText(rhetorical);
        dSection.setRhetorical(rhetoricalElement);
        TextElement titleElement = new TextElement();
        titleElement.setText(title);
        dSection.setTitle(titleElement);

        TextElement textElement = new TextElement();
        textElement.setText(text);
        Paragraph paragraph = new Paragraph();
        StructureElement structureElement = new StructureElement();
        structureElement.setTextElement(textElement);

        paragraph.addStructureElement(structureElement);
        dSection.addParagraph(paragraph);
        return dSection;
    }

    /**
     * @param text
     * @param rhetorical
     * @param titleText
     * @return
     */
    public Section createSection(String rhetorical, String title) {

        Section dSection = new Section();
        TextElement rhetoricalElement = new TextElement();
        rhetoricalElement.setText(rhetorical);
        dSection.setRhetorical(rhetoricalElement);
        TextElement titleElement = new TextElement();
        titleElement.setText(title);
        dSection.setTitle(titleElement);

        return dSection;
    }

    /**
     * @param text
     * @param createSentences
     * @return
     */
    public Paragraph createParagraph(String text, boolean createSentences) {
    	return createParagraph(text, createSentences, -1);
    }
    
    public Paragraph createParagraph(String text) {
      return createParagraph(text, false);
    }

    /**
     * create a paragraph
     *
     * @param text
     * @param createSentences
     * @param sentence_limit; -1 iff no limit; maximum number of sentences to create
     * @return
     */
    public Paragraph createParagraph(String text, boolean createSentences, int sentence_limit) {

        Paragraph dParagraph = null;

        if (text != null && !text.isEmpty()) {
            if (createSentences) {
                List<Integer> eos = sentenceDetector.findSentencesEndPositions(text);

                List<String> sentences = sentenceDetector.getSentences(eos, text, 5);
                String note = null;
                
                // all of them
                if( sentence_limit<0 || (sentence_limit+10<sentences.size()) ) {
                	sentence_limit = sentences.size();
                } else {
                	note = String.format(" >> Note: skipped %d sentences due to size limit of %d.", sentence_limit-sentences.size(), sentence_limit);                	
                }
                
                if (!sentences.isEmpty()) {
                    dParagraph = new Paragraph();
                    for (int i = 0; i < sentence_limit; i++) {
                    	if(i<sentences.size()) {
                        StructureElement sentence = new StructureElement();
                        sentence.setSentence(createSentence(sentences.get(i)));

                        dParagraph.addStructureElement(sentence);
                    	}
                    }
                    
                    // add comment if skipped sentences
                    if(note != null) {
                      StructureElement sentence = new StructureElement();
                      sentence.setSentence(createSentence(note));

                      dParagraph.addStructureElement(sentence);
                      
                      logger.info(note);
                    }
                }

                logger.debug("Created " + sentences.size() + " sentence(s).");

            } else {
                dParagraph = new Paragraph();
                TextElement paragraphText = new TextElement();
                paragraphText.setText(text);
                StructureElement pText = new StructureElement();
                pText.setTextElement(paragraphText);
                dParagraph.addStructureElement(pText);
            }
        }

        return dParagraph;
    }

    /**
     * @param text
     * @return
     */
    public Sentence createSentence(String text) {
        if (text != null && text.length() > 0) {
            Sentence dsentence = new Sentence();

            TextElement textElement = createTextElement(text);
            dsentence.setText(textElement);
            return dsentence;
        } else {
            return null;
        }
    }

    /**
     * @param textElement
     * @return
     */
    public List<Sentence> createSentences(TextElement textElement) {

        List<Sentence> dSentences = null;

        if (textElement != null && textElement.getText() != null) {
            dSentences = createSentences(textElement.getText());
        }

        return dSentences;
    }

    public List<Sentence> createSentences(String text) {

        List<Sentence> dSentences = new ArrayList<Sentence>();

        if (text != null && !text.isEmpty()) {

            List<Integer> eos = sentenceDetector.findSentencesEndPositions(text);

            List<String> sentences = sentenceDetector.getSentences(eos, text, 5);
            if (!sentences.isEmpty()) {
                for (int i = 0; i < sentences.size(); i++) {
                    dSentences.add(createSentence(sentences.get(i)));
                }
            }
        }

        return dSentences;
    }


    /**
     * @param text
     * @return
     */
    public TextElement createTextElement(String text) {
        TextElement textElement = new TextElement();

        if (text != null && !text.isEmpty()) {
//            textElement.setText(text.trim());
            textElement.setText(text);
        }

        return textElement;
    }
    
    public Title createTitle(String titleContent) {
        Title title = new Title();
        title.setTitleText(createTextElement(titleContent));
        return title;
    }

    /**
     * creating a figure annotation from begin to end
     *
     * @param rhetorical
     * @param caption
     * @param titleText
     * @param content
     * @return <code>structure.Figure</code>
     */
    public Figure createFigure(String rhetorical, String caption, String title, ImageContent content) {
        Figure figure = new Figure();

        if (rhetorical != null) {
            TextElement textElement = createTextElement(rhetorical);
            figure.setRhetorical(textElement);
        }

        if (title != null) {
            TextElement textElement = createTextElement(title);
            figure.setTitle(textElement);
        }

        if (content != null) {
            figure.setImage(content);
        }

        if (caption != null) {
            TextElement textElement = createTextElement(caption);
            figure.setCaption(textElement);
        }

        return figure;
    }

    /**
     * creating a table annotation from begin to end
     *
     * @param begin      <code>int</code>
     * @param end        <code>int</code>
     * @param rhetorical <code>String</code>
     * @return <code>structure.List</code>
     */
    public de.fraunhofer.scai.bio.types.text.doc.structure.Table createTable(String rhetorical, String caption,
                                                                             String title) {
        de.fraunhofer.scai.bio.types.text.doc.structure.Table dTable =
            new de.fraunhofer.scai.bio.types.text.doc.structure.Table();

        if (rhetorical != null) {
            TextElement textElement = createTextElement(rhetorical);
            dTable.setRhetorical(textElement);
        }

        if (title != null) {
            TextElement textElement = createTextElement(title);
            dTable.setTitle(textElement);
        }

        if (caption != null) {
            TextElement textElement = createTextElement(caption);
            dTable.setCaption(textElement);
            createParagraph(caption, true);
        }

        return dTable;

    }

    public Paragraph appendParagraph(String text) {
        return appendParagraph(text, false);
    }

    /**
     * @param outline
     * @param text           <code>String</code>
     * @param prefix         <code>String</code> eg a bullet
     * @param creatSentences <code>boolean</code>
     * @return
     */
    public Paragraph appendParagraph(String text, boolean creatSentences) {

        Paragraph dParagraph = createParagraph(text, creatSentences);
        return dParagraph;
    }

    // /**
    // * @param reference
    // */
    // private void appendBib(ElementInterface node, Reference reference) {
    //
    // List<Reference> tail = bib.getReferences();
    //
    // List<Reference> elem = new ArrayList<Reference>();
    // elem.add(reference);
    //
    // // still empty, just add
    // if (tail == null || tail.isEmpty()) {
    // bib.setReferences(elem);
    //
    // // iterate and append at the end
    // } else {
    // List<Reference> head = bib.getReferences();
    // head.addAll(elem);
    // }
    //
    // elem.addAll(tail);
    // }

    /**
     * get the keywords as StringList
     *
     * @param keywords   <code>util.List</code>
     * @param rhetorical <code>String</code>
     * @param createNNE  <code>boolean</code>
     * @return <code>structure.List</code>
     */
    public Keywords createKeywords(List<String> keywords, String rhetorical, boolean createNNE) {

        if (keywords != null && !keywords.isEmpty()) {

            Keywords keywordList = new Keywords();

            for (int i = 0; i < keywords.size(); i++) {
                TextElement kwElement = new TextElement();
                kwElement.setText(keywords.get(i).split("@")[0].trim());
                keywordList.addKeyword(kwElement);

                // TODO add annotations from keywords
                // String identifier = null;
                // try {
                // identifier = keywords.get(i).split("\0")[1];
                // } catch (Exception e) {
                // }
                // String source = null;
                // try {
                // source = keywords.get(i).split("\0")[2];
                // } catch (Exception e) {
                // }
            }
            TextElement rhetoricalElement = new TextElement();
            if (rhetorical != null && !rhetorical.isEmpty()) {
                rhetoricalElement.setText(rhetorical);
            } else {
                rhetoricalElement.setText("keywords");
            }
            keywordList.setRhetorical(rhetoricalElement);
            return keywordList;
        }

        return null;
    }

    public String objectToString(Object o) {

        // Convert object to JSON string
        try {
            ObjectMapper myObjectMapper = new ObjectMapper();

            myObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return myObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);

        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            logger.debug(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * @param textElement
     * @return
     */
    public StructureElement createStructureElement(TextElement textElement) {
        if (textElement == null) {
            return null;
        }

        StructureElement structureElement = new StructureElement();
        structureElement.setTextElement(textElement);

        return structureElement;
    }

    /**
     * @param text
     * @return
     */
    public StructureElement createStructureElement(String text) {
        if (text != null) {
            StructureElement se = new StructureElement();
            se.setTextElement(createTextElement(text));
            return se;
        }
        return null;
    }

    public void setTitle(Document document, String title, String subtitle) {

        if (title == null || title.length() <= 0) {
            title = "no title provided";
        }

        TextElement titleTextElement = createTextElement(title);
        TextElement subtitleTextElement = createTextElement(subtitle);

        getFrontMatter(document).setTitleText(titleTextElement);

        getBibliographic(document).setTitle(
            createDocumentTitle(titleTextElement, subtitleTextElement)
        );

    }

    public void setDocType(Document document, String docType) {
      if (docType == null || docType.length() <= 0) {
          docType = "unspecified";
      }
      document.setDocType(docType);
  }

    
    public void setLanguage(Document document, String language) {
        if (language == null || language.length() <= 0) {
            language = "unspecified";
        }

        getBibliographic(document).setLanguage(createTextElement(language));
    }

    public void setAbstract(Document document, String dAbstract) {
        Abstract documentAbstract = new Abstract();

        if (dAbstract == null || dAbstract.length() <= 0) {
            dAbstract = "no abstract provided";
        }

        documentAbstract.addAbstractSection(createSimpleSection(dAbstract, "Abstract", "Abstract"));
        getFrontMatter(document).setDocumentAbstract(documentAbstract);
        getBibliographic(document).setDocumentAbstract(documentAbstract);
    }

    public DocumentElement getDocumentElement(Document document) {
        DocumentElement docElem = document.getDocumentElement();

        if (docElem == null) {
            docElem = new DocumentElement();
            document.setDocumentElement(docElem);
        }

        return docElem;
    }

    public FrontMatter getFrontMatter(Document document) {
        FrontMatter frontMatter = getDocumentElement(document).getFrontMatter();

        if (frontMatter == null) {
            frontMatter = new FrontMatter();
            getDocumentElement(document).setFrontMatter(frontMatter);
        }

        return frontMatter;
    }

    public Bibliography getBibliography(Document document) {
        Bibliography bib = getBackMatter(document).getBibliography();

        if (bib == null) {
            bib = new Bibliography();
            getBackMatter(document).setBibliography(bib);
        }

        return bib;
    }

    @Deprecated
    public void addReference(Document document, String id, String referenceSource, String publicationId, String publicationType, String title, List<Author> authors, java.util.Date docDate) {

        Bibliography bib = getBibliography(document);

        Reference reference = new Reference();

        if (authors != null) {
            for (Author author : authors) {
                reference.addAuthor(author);
            }
        }
        if (publicationId != null) {
            reference.addPublicationId(createTextElement(publicationId));
        }
        //reference.setLanguage(language);
        if (publicationType != null) {
            reference.setPublicationType(createTextElement(publicationType));
        }
        if (referenceSource != null) {
            reference.setReferenceSource(createTextElement(referenceSource));
        }
        if (title != null) {
            reference.setTitle(createDocumentTitle(createTextElement(title), null));
        }
        if (docDate != null) {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(docDate);
            date.setDate(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
            reference.setDate(date);
        }

        bib.addReference(id, reference);

    }
    
    public void addReference(Document document, String id, String referenceSource, String publicationId, String publicationType, String title, List<Author> authors, java.time.LocalDate docDate) {

        Bibliography bib = getBibliography(document);

        Reference reference = new Reference();

        if (authors != null) {
            for (Author author : authors) {
                reference.addAuthor(author);
            }
        }
        if (publicationId != null) {
            reference.addPublicationId(createTextElement(publicationId));
        }
        //reference.setLanguage(language);
        if (publicationType != null) {
            reference.setPublicationType(createTextElement(publicationType));
        }
        if (referenceSource != null) {
            reference.setReferenceSource(createTextElement(referenceSource));
        }
        if (title != null) {
            reference.setTitle(createDocumentTitle(createTextElement(title), null));
        }
        if (docDate != null) {
            Date date = new Date();
            date.setDate(docDate.getDayOfMonth(), docDate.getMonthValue(), docDate.getYear());
            reference.setDate(date);
        }

        bib.addReference(id, reference);

    }

    public Bibliographic getBibliographic(Document document) {
        Bibliographic bib = getMetaElement(document).getBibliographic();

        if (bib == null) {
            bib = new Bibliographic();
            getMetaElement(document).setBibliographic(bib);
        }

        return bib;
    }

    public BodyMatter getBodyMatter(Document document) {
        BodyMatter bodyMatter = getDocumentElement(document).getBodyMatter();

        if (bodyMatter == null) {
            bodyMatter = new BodyMatter();
            getDocumentElement(document).setBodyMatter(bodyMatter);
        }

        return bodyMatter;
    }

    public BackMatter getBackMatter(Document document) {
        BackMatter backMatter = getDocumentElement(document).getBackMatter();

        if (backMatter == null) {
            backMatter = new BackMatter();
            getDocumentElement(document).setBackMatter(backMatter);
        }

        return backMatter;
    }

    public MetaElement getMetaElement(Document document) {

        MetaElement meta = getDocumentElement(document).getMetaElement();

        if (meta == null) {
            meta = new MetaElement();
            getDocumentElement(document).setMetaElement(meta);
        }

        return meta;
    }

    public List<License> createLicense(String value) {
        List<License> license = new ArrayList<>();
        License lic = new License();
        lic.setLicenseName(createTextElement(value));
        license.add(lic);
        return license;
    }
    
    public Section createMainSection(Document document, String text) {

        Section section = createSection("Main Section", "Main");

        Paragraph mainParagraph = createParagraph(text, true);

        section.addParagraph(mainParagraph);

        return section;
    }

    public void setSource(Document document, String source) {
      if (source == null || source.length() <= 0) source = "unspecified";
      getBibliographic(document).setSource(createTextElement(source));      
    }
    
    public Concept setDocumentId(Document document, String source, String id, String altlabel) {
        Concept concept = getConcept(document);

        if (source == null || source.length() <= 0) source = "unspecified";
        if (id == null || id.length() <= 0) id = "unspecified";
        if (altlabel == null || altlabel.length() <= 0) altlabel = "unspecified";

        concept.setIdentifierSource(createTextElement(source));
        concept.setIdentifier(createTextElement(id));
        concept.setPrefLabel(createTextElement(source + ":" + id));
        concept.setAltLabel(createTextElement(altlabel));

        return concept;
    }

    public Concept setDocumentIdasHash(Document document, String content, String altlabel) {

        String id = DigestUtils.sha512Hex(content);
        String source = "sha512Hex";

        return setDocumentId(document, source, id, altlabel);
    }

    public Concept getConcept(Document document) {
        Concept concept = getMetaElement(document).getConcept();

        if (concept == null) {
            concept = new Concept();
            getMetaElement(document).setConcept(concept);
        }

        return concept;
    }

    public void addPerson(Document document, String forename, String surname) {

        Person person = new Person();
        person.setForename(createTextElement(forename));
        person.setSurname(createTextElement(surname));

        Author author = new Author();
        author.setAuthor(person);

        getBibliographic(document).addAuthor(author);

    }

    public void addOrganization(Document document, String name) {
        Affiliation organization = new Affiliation();
        organization.setOrganization(createTextElement(name));

        Author author = new Author();
        author.setOrganization(organization);

        getBibliographic(document).addAuthor(author);

    }

    public void addOtherDocumentId(Document document, String id) {
        Concept concept = getConcept(document);

        concept.addAltLabel(createTextElement(id));

    }

    public void addKeywords(Document document, String rhetorical, List<String> keywords) {

        Keywords kws = createKeywords(keywords, rhetorical, false);
        document.getDocumentElement().getMetaElement().addKeywords(kws);

    }
    
    public void setPublicationDate(Document document, int day, int month, int year) {
    	Date pubDate = new Date();
    	pubDate.setDate(day, month, year);
    	getBibliographic(document).setPubDate(pubDate);
    }

}
