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
@Table(name="EMPRESA")
public class Empresa  implements Serializable{

	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)

    @Column(name = "EMPRESA_ID")
    private Integer empresaId;
    @Basic(optional = false)


    @Column(name = "EMPRESA_NOMBRE")
    private String empresaNombre;
    @Basic(optional = false)

    @Column(name = "EMPRESA_NIF")
    private int empresaNif;
    @Basic(optional = false)


    @Column(name = "ABREVIATURA")
    private String abreviatura;
    @Basic(optional = false)


    @Column(name = "ACRONIMO")
    private String acronimo;
    @Basic(optional = false)


    @Column(name = "ESTADO")
    private String estado;
    
    public Empresa() {
    }

    public Empresa(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Empresa(Integer empresaId, String empresaNombre, int empresaNif, String abreviatura, String acronimo, String estado) {
        this.empresaId = empresaId;
        this.empresaNombre = empresaNombre;
        this.empresaNif = empresaNif;
        this.abreviatura = abreviatura;
        this.acronimo = acronimo;
        this.estado = estado;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public int getEmpresaNif() {
        return empresaNif;
    }

    public void setEmpresaNif(int empresaNif) {
        this.empresaNif = empresaNif;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresaId != null ? empresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.empresaId == null && other.empresaId != null) || (this.empresaId != null && !this.empresaId.equals(other.empresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Empresa[ empresaId=" + empresaId + " ]";
    }
}
