package com.forum.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="BALANCE")
public class Balance implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)

    @Column(name = "BALANCE_ID")
    private Integer balanceId;
    @Basic(optional = false)
    
    @Column(name = "EMPRESA_ID")
    private int empresaId;
    @Basic(optional = false)
    
    private String nombreempresa;
    
    @Column(name = "CUENTA_EMPRESA_ID")
    private int cuentaempresaId;
    @Basic(optional = false)
    
    private int cuentaCodigo;
    
    private String cuentaDescripcion;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    
    @Column(name = "SALDO")
    private int saldo;
    @Basic(optional = false)
    
    @Column(name = "PERIODO")
    private Date periodo;
    @Basic(optional = false)
    
    @Column(name = "RUBRO_ID")
    private int rubroId;
    
    private String rubroCodigo;

	private String rubroDescripcion;
    
    
    public Balance() {
    }

    public Balance(Integer balanceId) {
        this.balanceId = balanceId;
    }

	public Balance(Integer balanceId, int empresaId, int cuentaempresaId, String descripcion, int saldo, Date periodo, int rubroId) {
		super();
		this.balanceId = balanceId;
		this.empresaId = empresaId;
		this.cuentaempresaId = cuentaempresaId;
		this.descripcion = descripcion;
		this.saldo = saldo;
		this.periodo = periodo;
		this.rubroId = rubroId;
	}

	public Integer getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}

	public int getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}
	
	public String getNombreempresa() {
		return nombreempresa;
	}

	public void setNombreempresa(String nombreempresa) {
		this.nombreempresa = nombreempresa;
	}

	public int getCuentaempresaId() {
		return cuentaempresaId;
	}

	public void setCuentaempresaId(int cuentaempresaId) {
		this.cuentaempresaId = cuentaempresaId;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}
	
	public int getRubroId() {
        return rubroId;
    }

    public void setRubroId(int rubroId) {
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
    
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (balanceId != null ? balanceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Balance)) {
            return false;
        }
        Balance other = (Balance) object;
        if ((this.balanceId == null && other.balanceId != null) || (this.balanceId != null && !this.balanceId.equals(other.balanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Balance[ balanceId=" + balanceId + " ]";
    }

    


    
    

}
