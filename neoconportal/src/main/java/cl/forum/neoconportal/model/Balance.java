package cl.forum.neoconportal.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="BALANCE_TEMP_DETALLE")
public class Balance implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)

    @Column(name = "BALANCETEMP_ID")
    private Integer balanceTempId;
    @Basic(optional = false)
    
    private int periodo;
    
    private String acronimo;
    
    @Column(name = "CUENTA_CODIGO")
    private int cuentaCodigo;
    @Basic(optional = false)
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    
    @Column(name = "SALDO")
    private double saldo;
    
    @Column(name = "RUBRO")
    private int rubro;
    
    
    public Balance() {
    }

    public Balance(Integer balanceTempId) {
        this.balanceTempId = balanceTempId;
    }
	
	public Balance(Integer balanceTempId, int cuentaCodigo, String descripcion, double saldo, int rubro) {
		super();
		this.balanceTempId = balanceTempId;
		this.cuentaCodigo = cuentaCodigo;
		this.descripcion = descripcion;
		this.saldo = saldo;
		this.rubro = rubro;
	}

	public Integer getBalanceTempId() {
		return balanceTempId;
	}

	public void setBalanceTempId(Integer balanceTempId) {
		this.balanceTempId = balanceTempId;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	public int getCuentaCodigo() {
		return cuentaCodigo;
	}

	public void setCuentaCodigo(int cuentaCodigo) {
		this.cuentaCodigo = cuentaCodigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getRubro() {
		return rubro;
	}

	public void setRubro(int rubro) {
		this.rubro = rubro;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (balanceTempId != null ? balanceTempId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Balance)) {
            return false;
        }
        Balance other = (Balance) object;
        if ((this.balanceTempId == null && other.balanceTempId != null) || (this.balanceTempId != null && !this.balanceTempId.equals(other.balanceTempId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Balance[ balanceTempId=" + balanceTempId + " ]";
    }

    


    
    

}
