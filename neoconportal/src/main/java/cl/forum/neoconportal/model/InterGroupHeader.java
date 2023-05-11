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
@Table(name="INETERGRUPOHEADER")
public class InterGroupHeader  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)

    @Column(name = "INTERGRUPO_ID")
    private Integer interGrupoId;
    @Basic(optional = false)
    
    @Column(name = "NUMERO_IG")
    private int numeroIg;
    @Basic(optional = false)
    
    @Column(name = "DESCRIPCION_IG")
    private String descripcionIg;
    @Basic(optional = false)
    
    @Column(name = "EMPRESA1")
    private String empresa1;
    @Basic(optional = false)
    
    @Column(name = "RUBRO1")
    private int rubro1;
    @Basic(optional = false)
    
    @Column(name = "DESCRIPCIONRUBRO1")
    private String descripcionRubro1;
    @Basic(optional = false)
    
    @Column(name = "EMPRESA2")
    private String empresa2;
    @Basic(optional = false)
    
    @Column(name = "RUBRO2")
    private int rubro2;
    @Basic(optional = false)
    
    @Column(name = "DESCRIPCIONRUBRO2")
    private String descripcionRubro2;
    
    public InterGroupHeader() {
    }

    public InterGroupHeader(Integer interGrupoId) {
        this.interGrupoId = interGrupoId;
    }

	public InterGroupHeader(Integer interGrupoId, int numeroIg, String descripcionIg, String empresa1, int rubro1,
			String descripcionRubro1, String empresa2, int rubro2, String descripcionRubro2) {
		super();
		this.interGrupoId = interGrupoId;
		this.numeroIg = numeroIg;
		this.descripcionIg = descripcionIg;
		this.empresa1 = empresa1;
		this.rubro1 = rubro1;
		this.descripcionRubro1 = descripcionRubro1;
		this.empresa2 = empresa2;
		this.rubro2 = rubro2;
		this.descripcionRubro2 = descripcionRubro2;
	}

	public Integer getInterGrupoId() {
		return interGrupoId;
	}

	public void setInterGrupoId(Integer interGrupoId) {
		this.interGrupoId = interGrupoId;
	}

	public int getNumeroIg() {
		return numeroIg;
	}

	public void setNumeroIg(int numeroIg) {
		this.numeroIg = numeroIg;
	}

	public String getDescripcionIg() {
		return descripcionIg;
	}

	public void setDescripcionIg(String descripcionIg) {
		this.descripcionIg = descripcionIg;
	}

	public String getEmpresa1() {
		return empresa1;
	}

	public void setEmpresa1(String empresa1) {
		this.empresa1 = empresa1;
	}

	public int getRubro1() {
		return rubro1;
	}

	public void setRubro1(int rubro1) {
		this.rubro1 = rubro1;
	}

	public String getDescripcionRubro1() {
		return descripcionRubro1;
	}

	public void setDescripcionRubro1(String descripcionRubro1) {
		this.descripcionRubro1 = descripcionRubro1;
	}

	public String getEmpresa2() {
		return empresa2;
	}

	public void setEmpresa2(String empresa2) {
		this.empresa2 = empresa2;
	}

	public int getRubro2() {
		return rubro2;
	}

	public void setRubro2(int rubro2) {
		this.rubro2 = rubro2;
	}

	public String getDescripcionRubro2() {
		return descripcionRubro2;
	}

	public void setDescripcionRubro2(String descripcionRubro2) {
		this.descripcionRubro2 = descripcionRubro2;
	}
    
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (interGrupoId != null ? interGrupoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Balance)) {
            return false;
        }
        InterGroupHeader other = (InterGroupHeader) object;
        if ((this.interGrupoId == null && other.interGrupoId != null) || (this.interGrupoId != null && !this.interGrupoId.equals(other.interGrupoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.InterGroupHeader[ interGrupoId=" + interGrupoId + " ]";
    }
    
}
