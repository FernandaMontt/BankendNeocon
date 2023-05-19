package cl.forum.neoconportal.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="NEOCONHEADER")
public class NeoConHeader implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Basic(optional = false)

    @Column(name = "NEOCONID")
    private Integer neoconId;
    @Basic(optional = false)
    
    @Column(name = "FECHAPROCESO")
    private Date fechaProceso;
    @Basic(optional = false)
    
    @Column(name = "VERSION")
    private int version;
    @Basic(optional = false)
    
    @Column(name = "PERIODO")
    private int periodo;
    @Basic(optional = false)
    
    @Column(name = "ACRONIMO")
    private String acronimo;
    @Basic(optional = false)
    
    @Column(name = "HORA")
    private Time hora;
    
    
    public NeoConHeader() {
    }

    public NeoConHeader(Integer neoconId) {
        this.neoconId = neoconId;
    }

	public NeoConHeader(Integer neoconId, Date fechaProceso, int version, int periodo, String acronimo, Time hora) {
		super();
		this.neoconId = neoconId;
		this.fechaProceso = fechaProceso;
		this.version = version;
		this.periodo = periodo;
		this.acronimo = acronimo;
		this.hora = hora;
	}

	public Integer getNeoconId() {
		return neoconId;
	}

	public void setNeoconId(Integer neoconId) {
		this.neoconId = neoconId;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
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
        NeoConHeader other = (NeoConHeader) object;
        if ((this.neoconId == null && other.neoconId != null) || (this.neoconId != null && !this.neoconId.equals(other.neoconId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NeoConHeader[ neoconId=" + neoconId + " ]";
    }

}
