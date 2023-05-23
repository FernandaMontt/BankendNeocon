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
@Table(name="HEADERBALANCE_TEMP")
public class Balance implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)

    @Column(name = "BALANCETEMP_ID")
    private Integer balanceTempId;
    @Basic(optional = false)
    
    @Column(name = "PERIODO")
    private int periodo;
    
    @Column(name = "ACRONIMO")
    private String acronimo;
    
    @Column(name = "FECHA_INICIAL")
    private Date fecha_incial;
    @Basic(optional = false)
    
    @Column(name = "FECHA_FIN")
    private Date fecha_fin;
    @Basic(optional = false)
    
    @Column(name = "NOMBREARCHIVO")
    private String nombreArchivo;
    
    @Column(name = "CANTIDADREGISTROS")
    private int cantidadRegistros;
    
    @Column(name = "ESTADO")
    private String estado;
    
    
    public Balance() {
    }

    public Balance(Integer balanceTempId) {
        this.balanceTempId = balanceTempId;
    }
	

	public Balance(Integer balanceTempId, int periodo, String acronimo, Date fecha_incial, Date fecha_fin,
			String nombreArchivo, int cantidadRegistros, String estado) {
		super();
		this.balanceTempId = balanceTempId;
		this.periodo = periodo;
		this.acronimo = acronimo;
		this.fecha_incial = fecha_incial;
		this.fecha_fin = fecha_fin;
		this.nombreArchivo = nombreArchivo;
		this.cantidadRegistros = cantidadRegistros;
		this.estado = estado;
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

	public Date getFecha_incial() {
		return fecha_incial;
	}

	public void setFecha_incial(Date fecha_incial) {
		this.fecha_incial = fecha_incial;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public int getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(int cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
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
