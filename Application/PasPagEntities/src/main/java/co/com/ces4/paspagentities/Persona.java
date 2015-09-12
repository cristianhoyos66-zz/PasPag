package co.com.ces4.paspagentities;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cristian
 */
@Table
public class Persona extends DatosBasicos {
    @Id
    private String documento;  
    private TipoDocumento tipo_doc;
    private TipoPersona tipo_per;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoDocumento getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(TipoDocumento tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public TipoPersona getTipo_per() {
        return tipo_per;
    }

    public void setTipo_per(TipoPersona tipo_per) {
        this.tipo_per = tipo_per;
    }
 
}
