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

import lombok.Data;

/**
 * @author tadams Only set 1 field!
 */
@Data public class StructureElement implements Serializable {

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

}
