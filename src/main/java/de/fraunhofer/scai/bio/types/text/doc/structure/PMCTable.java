package de.fraunhofer.scai.bio.types.text.doc.structure;

import java.io.Serializable;
import java.util.List;

/**
 * @author tadams
 */
public class PMCTable implements Serializable {

    private static final long serialVersionUID = -7075934358639959834L;

    List<Cell> cells;

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

}
