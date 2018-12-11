/**
 * 
 */
package de.fraunhofer.scai.bio.types.text.doc.container;

import java.io.Serializable;

import de.fraunhofer.scai.bio.types.text.doc.structure.CaptionedBox;
import de.fraunhofer.scai.bio.types.text.doc.structure.Code;
import de.fraunhofer.scai.bio.types.text.doc.structure.DataTable;
import de.fraunhofer.scai.bio.types.text.doc.structure.Figure;
import de.fraunhofer.scai.bio.types.text.doc.structure.Formula;
import de.fraunhofer.scai.bio.types.text.doc.structure.ImageContent;
import de.fraunhofer.scai.bio.types.text.doc.structure.List;
import de.fraunhofer.scai.bio.types.text.doc.structure.Outline;
import de.fraunhofer.scai.bio.types.text.doc.structure.Quotation;
import de.fraunhofer.scai.bio.types.text.doc.structure.Sentence;
import de.fraunhofer.scai.bio.types.text.doc.structure.Table;
import de.fraunhofer.scai.bio.types.text.doc.structure.TextElement;

/**
 * @author tadams Only set 1 field!
 */
public class StructureElement implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7490022742872925687L;
    private CaptionedBox captionedBox;
    private Code code;
    private DataTable dataTable;
    private Figure figure;
    private Formula formula;
    private ImageContent imageContent;
    private Outline outline;
    private Quotation quotation;
    private Table table;
    private TextElement textElement;
    private Sentence sentence;
    private List list;
    
    /**
     * @return the captionedBox
     */
    public CaptionedBox getCaptionedBox() {
	return captionedBox;
    }

    /**
     * @param captionedBox the {@link CaptionedBox} to set
     */
    public void setCaptionedBox(CaptionedBox captionedBox) {
	this.captionedBox = captionedBox;
    }

    /**
     * @return the {@link Code}
     */
    public Code getCode() {
	return code;
    }

    /**
     * @param code the {@link Code} to set
     */
    public void setCode(Code code) {
	this.code = code;
    }

    /**
     * @return the {@link DataTable}
     */
    public DataTable getDataTable() {
	return dataTable;
    }

    /**
     * @param dataTable the {@link DataTable} to set
     */
    public void setDataTable(DataTable dataTable) {
	this.dataTable = dataTable;
    }

    /**
     * @return the {@link Figure}
     */
    public Figure getFigure() {
	return figure;
    }

    /**
     * @param figure the {@link Figure} to set
     */
    public void setFigure(Figure figure) {
	this.figure = figure;
    }

    /**
     * @return the {@link Formula}
     */
    public Formula getFormula() {
	return formula;
    }

    /**
     * @param formula the {@link Formula} to set
     */
    public void setFormula(Formula formula) {
	this.formula = formula;
    }

    /**
     * @return the {@link ImageContent}
     */
    public ImageContent getImageContent() {
	return imageContent;
    }

    /**
     * @param imageContent the {@link ImageContent} to set
     */
    public void setImageContent(ImageContent imageContent) {
	this.imageContent = imageContent;
    }

    /**
     * @return the {@link Outline}
     */
    public Outline getOutline() {
	return outline;
    }

    /**
     * @param outline the {@link Outline} to set
     */
    public void setOutline(Outline outline) {
	this.outline = outline;
    }

    /**
     * @return the {@link Quotation}
     */ 
    public Quotation getQuotation() {
	return quotation;
    }

    /**
     * @param quotation the {@link Quotation} to set
     */
    public void setQuotation(Quotation quotation) {
	this.quotation = quotation;
    }

    /**
     * @return the {@link Table}
     */
    public Table getTable() {
	return table;
    }

    /**
     * @param table the {@link Table} to set
     */
    public void setTable(Table table) {
	this.table = table;
    }

    /**
     * @return the {@link StructureElement}
     */
    public TextElement getTextElement() {
	return textElement;
    }

    /**
     * @param textElement the {@link TextElement} to set
     */
    public void setTextElement(TextElement textElement) {
	this.textElement = textElement;
    }

    /**
     * @return the {@link Sentence}
     */
    public Sentence getSentence() {
	return sentence;
    }
    
    /**
     * @param sentence the {@link Sentence} to set
     */
    public void setSentence(Sentence sentence) {
	this.sentence = sentence;
    }

		/**
		 * @return
		 */
		public List getList() {
			return list;
		}

		/**
		 * @param list
		 */
		public void setList(List list) {
			this.list = list;
		}

}
