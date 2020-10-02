package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;

/**
 * @author tadams
 */
public class Cell implements Serializable {

    private static final long serialVersionUID = -2815310382355431825L;

    int row;
    int col;
    int rowSpan;
    int colSpan;
    TextElement content;
    CellType type;


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public TextElement getContent() {
        return content;
    }

    public void setContent(TextElement content) {
        this.content = content;
    }
}
