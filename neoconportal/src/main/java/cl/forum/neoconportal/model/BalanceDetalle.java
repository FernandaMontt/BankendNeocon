package cl.forum.neoconportal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="BALANCE_TEMP_DETALLE")
public class BalanceDetalle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)

    @Column(name = "BALANCETEMP_ID")
    private Integer balanceTempId;
    @Basic(optional = false)
    
    @Column(name = "CUENTA_CODIGO")
    private Double cuentaCodigo;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "SALDO")
    private Double saldo;
    @Basic(optional = false)
    
    @Column(name = "RUBRO")
    private int rubro;
    @Basic(optional = false)
    
    @Column(name = "TESTCUENTA")
    private int testCuenta;
    
    @Column(name = "TESTRUBRO")
    private int testRubro;
    
    private String mensaje;
    
    public BalanceDetalle() {
    }

    public BalanceDetalle(Integer balanceTempId) {
        this.balanceTempId = balanceTempId;
    }

	public BalanceDetalle(Integer balanceTempId, Double cuentaCodigo, String descripcion, Double saldo, int rubro,
			int testCuenta, int testRubro, String mensaje) {
		super();
		this.balanceTempId = balanceTempId;
		this.cuentaCodigo = cuentaCodigo;
		this.descripcion = descripcion;
		this.saldo = saldo;
		this.rubro = rubro;
		this.testCuenta = testCuenta;
		this.testRubro = testRubro;
		this.mensaje = mensaje;
	}

	public Integer getBalanceTempId() {
		return balanceTempId;
	}

	public void setBalanceTempId(Integer balanceTempId) {
		this.balanceTempId = balanceTempId;
	}

	public Double getCuentaCodigo() {
		return cuentaCodigo;
	}

	public void setCuentaCodigo(Double cuentaCodigo) {
		this.cuentaCodigo = cuentaCodigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public int getRubro() {
		return rubro;
	}

	public void setRubro(int rubro) {
		this.rubro = rubro;
	}

	public int getTestCuenta() {
		return testCuenta;
	}

	public void setTestCuenta(int testCuenta) {
		this.testCuenta = testCuenta;
	}

	public int getTestRubro() {
		return testRubro;
	}

	public void setTestRubro(int testRubro) {
		this.testRubro = testRubro;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
        if (!(object instanceof BalanceDetalle)) {
            return false;
        }
        BalanceDetalle other = (BalanceDetalle) object;
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
