package com.forum.project.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CUENTA_EMPRESA")
public class CuentaEmpresa implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)

    @Column(name = "CUENTA_EMPRESA_ID")
    private Integer cuentaEmpresaId;
    @Basic(optional = false)

    @Column(name = "EMPRESA_ID")
    private int empresaId;
    @Basic(optional = false)

    @Column(name = "RUBRO_ID")
    private int rubroId;
    @Basic(optional = false)
    
    @Column(name = "CUENTA_CODIGO")
    private int cuentaCodigo;
    @Basic(optional = false)

    @Column(name = "CUENTA_DESCRIPCION")
    private String cuentaDescripcion;
    @Basic(optional = false)

    @Column(name = "CUENTA_TIPO")
    private String cuentaTipo;
    @Basic(optional = false)

    @Column(name = "ESTADO")
    private String estado;

    public CuentaEmpresa() {
    }

    public CuentaEmpresa(Integer cuentaEmpresaId) {
        this.cuentaEmpresaId = cuentaEmpresaId;
    }

    public CuentaEmpresa(Integer cuentaEmpresaId, int empresaId, int rubroId, int cuentaCodigo, String cuentaDescripcion, String cuentaTipo, String estado) {
        this.cuentaEmpresaId = cuentaEmpresaId;
        this.empresaId = empresaId;
        this.rubroId = rubroId;
        this.cuentaCodigo = cuentaCodigo;
        this.cuentaDescripcion = cuentaDescripcion;
        this.cuentaTipo = cuentaTipo;
        this.estado = estado;
    }

    public Integer getCuentaEmpresaId() {
        return cuentaEmpresaId;
    }

    public void setCuentaEmpresaId(Integer cuentaEmpresaId) {
        this.cuentaEmpresaId = cuentaEmpresaId;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public int getRubroId() {
        return rubroId;
    }

    public void setRubroId(int rubroId) {
        this.rubroId = rubroId;
    }

    public int getCuentaCodigo() {
		return cuentaCodigo;
	}

	public void setCuentaCodigo(int cuentaCodigo) {
		this.cuentaCodigo = cuentaCodigo;
	}

	public String getCuentaDescripcion() {
		return cuentaDescripcion;
	}

	public void setCuentaDescripcion(String cuentaDescripcion) {
		this.cuentaDescripcion = cuentaDescripcion;
	}

	public String getCuentaTipo() {
		return cuentaTipo;
	}

	public void setCuentaTipo(String cuentaTipo) {
		this.cuentaTipo = cuentaTipo;
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
        hash += (cuentaEmpresaId != null ? cuentaEmpresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaEmpresa)) {
            return false;
        }
        CuentaEmpresa other = (CuentaEmpresa) object;
        if ((this.cuentaEmpresaId == null && other.cuentaEmpresaId != null) || (this.cuentaEmpresaId != null && !this.cuentaEmpresaId.equals(other.cuentaEmpresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CuentaEmpresa[ cuentaEmpresaId=" + cuentaEmpresaId + " ]";
    }

}
