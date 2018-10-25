/**
 * 
 */
package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.scai.bio.types.text.doc.meta.Annotation;

/**
 * @author tadams
 * Only set 1 field!
 */
public class StructureElement {

    private CaptionedBox captionedBox;
    private Code code;
    private DataTable dataTable;
    private Figure figure;
    private Formula formula;
    private ImageContent imageContent;
    private ItemsList itemsList;
    private Outline outline;
    private Quotation quotation;
    private Sentence sentence;
    private Table table;
    private TextElement textElement;
    
    private List<Annotation> annotations;    
    
    /**
     * 
     */
    public StructureElement() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @return the captionedBox
     */
    public CaptionedBox getCaptionedBox() {
        return captionedBox;
    }

    /**
     * @param captionedBox the captionedBox to set
     */
    public void setCaptionedBox(CaptionedBox captionedBox) {
        this.captionedBox = captionedBox;
    }

    /**
     * @return the code
     */
    public Code getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Code code) {
        this.code = code;
    }

    /**
     * @return the dataTable
     */
    public DataTable getDataTable() {
        return dataTable;
    }

    /**
     * @param dataTable the dataTable to set
     */
    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    /**
     * @return the figure
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     * @param figure the figure to set
     */
    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    /**
     * @return the formula
     */
    public Formula getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    /**
     * @return the imageContent
     */
    public ImageContent getImageContent() {
        return imageContent;
    }

    /**
     * @param imageContent the imageContent to set
     */
    public void setImageContent(ImageContent imageContent) {
        this.imageContent = imageContent;
    }

    /**
     * @return the itemsList
     */
    public ItemsList getItemsList() {
        return itemsList;
    }

    /**
     * @param itemsList the itemsList to set
     */
    public void setItemsList(ItemsList itemsList) {
        this.itemsList = itemsList;
    }

    /**
     * @return the outline
     */
    public Outline getOutline() {
        return outline;
    }

    /**
     * @param outline the outline to set
     */
    public void setOutline(Outline outline) {
        this.outline = outline;
    }

    /**
     * @return the quotation
     */
    public Quotation getQuotation() {
        return quotation;
    }

    /**
     * @param quotation the quotation to set
     */
    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    /**
     * @return the sentence
     */
    public Sentence getSentence() {
        return sentence;
    }

    /**
     * @param sentence the sentence to set
     */
    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * @return the textElement
     */
    public TextElement getTextElement() {
        return textElement;
    }

    /**
     * @param textElement the textElement to set
     */
    public void setTextElement(TextElement textElement) {
        this.textElement = textElement;
    }

    /**
     * @return the annotations
     */
    public List<Annotation> getAnnotations() {
	return annotations;
    }

    /**
     * @param annotation the annotation to add
     */
    public void addAnnotation(Annotation annotation) {
	if (this.annotations == null)
	    this.annotations = new ArrayList<Annotation>();
	this.annotations.add(annotation);
    }
    
    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(List<Annotation> annotations) {
	this.annotations = annotations;
    }


}
