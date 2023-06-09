package cl.forum.neoconportal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="RUBRO")
public class Rubro implements Serializable {

	    private static final long serialVersionUID = 1L;
	    @Id
	    @Basic(optional = false)

	    @Column(name = "RUBRO_ID")
	    private Integer rubroId;
	    @Basic(optional = false)

	    @Column(name = "RUBRO_CODIGO")
	    private String rubroCodigo;
	    @Basic(optional = false)

	    @Column(name = "RUBRO_DESCRIPCION")
	    private String rubroDescripcion;
	    @Basic(optional = false)

	    @Column(name = "RUBRO_NATURALEZA")
	    private String rubroNaturaleza;
	    @Basic(optional = false)

	    @Column(name = "RUBRO_ESTADO")
	    private String rubroEstado;

	    public Rubro() {
	    }

	    public Rubro(Integer rubroId) {
	        this.rubroId = rubroId;
	    }

	    public Rubro(Integer rubroId, String rubroCodigo, String rubroDescripcion, String rubroNaturaleza, String rubroEstado) {
	        this.rubroId = rubroId;
	        this.rubroCodigo = rubroCodigo;
	        this.rubroDescripcion = rubroDescripcion;
	        this.rubroNaturaleza = rubroNaturaleza;
	        this.rubroEstado = rubroEstado;
	    }

	    public Integer getRubroId() {
	        return rubroId;
	    }

	    public void setRubroId(Integer rubroId) {
	        this.rubroId = rubroId;
	    }

	    public String getRubroCodigo() {
	        return rubroCodigo;
	    }

	    public void setRubroCodigo(String rubroCodigo) {
	        this.rubroCodigo = rubroCodigo;
	    }

	    public String getRubroDescripcion() {
	        return rubroDescripcion;
	    }

	    public void setRubroDescripcion(String rubroDescripcion) {
	        this.rubroDescripcion = rubroDescripcion;
	    }

	    public String getRubroNaturaleza() {
	        return rubroNaturaleza;
	    }

	    public void setRubroNaturaleza(String rubroNaturaleza) {
	        this.rubroNaturaleza = rubroNaturaleza;
	    }

	    public String getRubroEstado() {
	        return rubroEstado;
	    }

	    public void setRubroEstado(String rubroEstado) {
	        this.rubroEstado = rubroEstado;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (rubroId != null ? rubroId.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof Rubro)) {
	            return false;
	        }
	        Rubro other = (Rubro) object;
	        if ((this.rubroId == null && other.rubroId != null) || (this.rubroId != null && !this.rubroId.equals(other.rubroId))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "entity.Rubro[ rubroId=" + rubroId + " ]";
	    }
	    
}
