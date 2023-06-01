package cl.forum.neoconportal.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="NEOCONIETEM")
public class NeoConBalanceDetalle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)

    @Column(name = "NEOCONITEMID")
    private Integer neoconItemId;
    @Basic(optional = false)
    
    @Column(name = "NEOCONID")
    private Integer neoconId;
    @Basic(optional = false)
    
    private int periodo;
    
    private String acronimo;
    
    private String nombreEmpresa;
    
    private Double cuenta;
    
    private String cuentaDescripcion;
    
    
    @Column(name = "NUMERO_IG")
    private int numero_ig;
    @Basic(optional = false)
    
    @Column(name = "RUBRO")
    private int rubro;
    @Basic(optional = false)
    
    private String descripcionRubro;
    
    @Column(name = "EMPRESA_NIF")
    private int empresa_nif;
    @Basic(optional = false)
    
    @Column(name = "SALDO")
    private Double saldo;
    @Basic(optional = false)
    
    private Double saldo2;
    
    @Column(name = "SIGNODELSALDO")
    private String signodelSaldo;
    @Basic(optional = false)
    
    private String naturalezaRubro;
    
    private String signoRubro;

	@Column(name = "EMPRESA2_NIF")
    private String empresa2_nif;
    @Basic(optional = false)
    
    @Column(name = "FILLER1")
    private String filler1;
    @Basic(optional = false)
    
    @Column(name = "FILLER2")
    private String filler2;
    @Basic(optional = false)
    
    @Column(name = "FILLER3")
    private String filler3;
    @Basic(optional = false)
    
    @Column(name = "HORA")
    private Time hora;
    
    private String mensaje;
    
    @Column(name = "TESTCUENTA")
    private int testCuenta;
    
    @Column(name = "TESTRUBRO")
    private int testRubro;
    
    public NeoConBalanceDetalle() {
    }

    public NeoConBalanceDetalle(Integer neoconItemId) {
        this.neoconItemId = neoconItemId;
    }

	public NeoConBalanceDetalle(Integer neoconItemId, Integer neoconId, int numero_ig, int rubro, int empresa_nif,
			Double saldo, String signodelSaldo, String empresa2_nif, String filler1, String filler2, String filler3,
			Time hora) {
		super();
		this.neoconItemId = neoconItemId;
		this.neoconId = neoconId;
		this.numero_ig = numero_ig;
		this.rubro = rubro;
		this.empresa_nif = empresa_nif;
		this.saldo = saldo;
		this.signodelSaldo = signodelSaldo;
		this.empresa2_nif = empresa2_nif;
		this.filler1 = filler1;
		this.filler2 = filler2;
		this.filler3 = filler3;
		this.hora = hora;
	}

	public Integer getNeoconItemId() {
		return neoconItemId;
	}

	public void setNeoconItemId(Integer neoconItemId) {
		this.neoconItemId = neoconItemId;
	}

	public Integer getNeoconId() {
		return neoconId;
	}

	public void setNeoconId(Integer neoconId) {
		this.neoconId = neoconId;
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

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public Double getCuenta() {
		return cuenta;
	}

	public void setCuenta(Double cuenta) {
		this.cuenta = cuenta;
	}

	public String getCuentaDescripcion() {
		return cuentaDescripcion;
	}

	public void setCuentaDescripcion(String cuentaDescripcion) {
		this.cuentaDescripcion = cuentaDescripcion;
	}

	public int getNumero_ig() {
		return numero_ig;
	}

	public void setNumero_ig(int numero_ig) {
		this.numero_ig = numero_ig;
	}

	public int getRubro() {
		return rubro;
	}

	public void setRubro(int rubro) {
		this.rubro = rubro;
	}

	public String getDescripcionRubro() {
		return descripcionRubro;
	}

	public void setDescripcionRubro(String descripcionRubro) {
		this.descripcionRubro = descripcionRubro;
	}

	public int getEmpresa_nif() {
		return empresa_nif;
	}

	public void setEmpresa_nif(int empresa_nif) {
		this.empresa_nif = empresa_nif;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getSignodelSaldo() {
		return signodelSaldo;
	}

	public void setSignodelSaldo(String signodelSaldo) {
		this.signodelSaldo = signodelSaldo;
	}

	public String getEmpresa2_nif() {
		return empresa2_nif;
	}

	public void setEmpresa2_nif(String empresa2_nif) {
		this.empresa2_nif = empresa2_nif;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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

	public Double getSaldo2() {
		return saldo2;
	}

	public void setSaldo2(Double saldo2) {
		this.saldo2 = saldo2;
	}
	
	public String getNaturalezaRubro() {
		return naturalezaRubro;
	}

	public void setNaturalezaRubro(String naturalezaRubro) {
		this.naturalezaRubro = naturalezaRubro;
	}

	public String getSignoRubro() {
		return signoRubro;
	}

	public void setSignoRubro(String signoRubro) {
		this.signoRubro = signoRubro;
	}

	

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (neoconItemId != null ? neoconItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Balance)) {
            return false;
        }
        NeoConBalanceDetalle other = (NeoConBalanceDetalle) object;
        if ((this.neoconItemId == null && other.neoconItemId != null) || (this.neoconItemId != null && !this.neoconItemId.equals(other.neoconItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NeoConBalanceDetalle[ neoconItemId=" + neoconItemId + " ]";
    }
    
    

}
