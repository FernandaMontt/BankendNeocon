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
@Table(name="VALIDACION_PROCESO_NATURALEZA")
public class ValidacionMensaje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)

    @Column(name = "NEOCONID")
    private Integer neoconId;
    @Basic(optional = false)
    
    @Column(name = "CUENTA")
    private int cuenta_codigo;
    
    @Column(name = "RUBRO")
    private int rubro;
    
    @Column(name = "RUBRO_NATURALEZA")
    private String rubroNaturaleza;
    
    @Column(name = "SIGNOSALDO")
    private String signoSaldo;
    
    @Column(name = "MENSAJE")
    private String mensaje;
    
    private Integer balancetemp_id;
    
    private int IG;
    
    private String empresa1;
    
    private String empresa2;
    
    private String mensajeC;
    
    private String mensajeR;
    
    private String mensajeI;
    
    private String nombreArchivo;
    
    private String acronimo;
    
    public ValidacionMensaje() {
    }

    public ValidacionMensaje(Integer neoconId) {
        this.neoconId = neoconId;
    }

	public ValidacionMensaje(Integer neoconId, int cuenta_codigo, int rubro, String rubroNaturaleza, String signoSaldo,
			String mensaje) {
		super();
		this.neoconId = neoconId;
		this.cuenta_codigo = cuenta_codigo;
		this.rubro = rubro;
		this.rubroNaturaleza = rubroNaturaleza;
		this.signoSaldo = signoSaldo;
		this.mensaje = mensaje;
	}

	public Integer getNeoconId() {
		return neoconId;
	}

	public void setNeoconId(Integer neoconId) {
		this.neoconId = neoconId;
	}

	public int getCuenta_codigo() {
		return cuenta_codigo;
	}

	public void setCuenta_codigo(int cuenta_codigo) {
		this.cuenta_codigo = cuenta_codigo;
	}

	public int getRubro() {
		return rubro;
	}

	public void setRubro(int rubro) {
		this.rubro = rubro;
	}

	public String getRubroNaturaleza() {
		return rubroNaturaleza;
	}

	public void setRubroNaturaleza(String rubroNaturaleza) {
		this.rubroNaturaleza = rubroNaturaleza;
	}

	public String getSignoSaldo() {
		return signoSaldo;
	}

	public void setSignoSaldo(String signoSaldo) {
		this.signoSaldo = signoSaldo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getBalancetemp_id() {
		return balancetemp_id;
	}

	public void setBalancetemp_id(Integer balancetemp_id) {
		this.balancetemp_id = balancetemp_id;
	}

	public int getIG() {
		return IG;
	}

	public void setIG(int iG) {
		IG = iG;
	}

	public String getEmpresa1() {
		return empresa1;
	}

	public void setEmpresa1(String empresa1) {
		this.empresa1 = empresa1;
	}

	public String getEmpresa2() {
		return empresa2;
	}

	public void setEmpresa2(String empresa2) {
		this.empresa2 = empresa2;
	}

	public String getMensajeC() {
		return mensajeC;
	}

	public void setMensajeC(String mensajeC) {
		this.mensajeC = mensajeC;
	}

	public String getMensajeR() {
		return mensajeR;
	}

	public void setMensajeR(String mensajeR) {
		this.mensajeR = mensajeR;
	}

	public String getMensajeI() {
		return mensajeI;
	}

	public void setMensajeI(String mensajeI) {
		this.mensajeI = mensajeI;
	}
    
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (neoconId != null ? neoconId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Balance)) {
            return false;
        }
        ValidacionMensaje other = (ValidacionMensaje) object;
        if ((this.neoconId == null && other.neoconId != null) || (this.neoconId != null && !this.neoconId.equals(other.neoconId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ValidacionMensaje[ neoconId=" + neoconId + " ]";
    }
    

}
