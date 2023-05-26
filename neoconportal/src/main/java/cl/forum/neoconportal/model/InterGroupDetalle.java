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
@Table(name="INETERGRUPODETALLE")
public class InterGroupDetalle implements Serializable{

	private static final long serialVersionUID = 1L;
    

	@Id
    @Basic(optional = false)

    @Column(name = "INTERGRUPO_ID")
    private Integer interGrupoId;
	@Basic(optional = false)
	
    @Column(name = "NUMERO_IG")
    private int numeroIg;
    @Basic(optional = false)
    
    @Column(name = "EMPRESA")
    private String empresa;
    @Basic(optional = false)
    
    @Column(name = "CUENTA_CODIGO")
    private int cuentaCodigo;
    @Basic(optional = false)
   
    @Column(name = "CUENTA_DESCRIPCION")
    private String cuentaDescripcion;
    @Basic(optional = false)
    
    private String descripcionIg;
    
    private String empresa1;
    
    private String empresa2;
    
    private int rubro1;
    
    private int rubro2;
    
    private int empresaNif1;
    
    private int empresaNif2;
    
    private int rubro;
    
    private String descripcionRubro;
    
    public InterGroupDetalle() {
    }

    public InterGroupDetalle(Integer numeroIg) {
        this.numeroIg = numeroIg;
    }

	public InterGroupDetalle(Integer interGrupoId, int numeroIg, String empresa, String descripcionIg, String empresa1, String empresa2,
			int rubro1, int rubro2, int cuentaCodigo, String cuentaDescripcion) {
		super();
		this.interGrupoId = interGrupoId;
		this.numeroIg = numeroIg;
		this.empresa = empresa;
		this.descripcionIg = descripcionIg;
		this.empresa1 = empresa1;
		this.empresa2 = empresa2;
		this.rubro1 = rubro1;
		this.rubro2 = rubro2;
		this.cuentaCodigo = cuentaCodigo;
		this.cuentaDescripcion = cuentaDescripcion;
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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

	public String getEmpresa2() {
		return empresa2;
	}

	public void setEmpresa2(String empresa2) {
		this.empresa2 = empresa2;
	}

	public int getRubro1() {
		return rubro1;
	}

	public void setRubro1(int rubro1) {
		this.rubro1 = rubro1;
	}

	public int getRubro2() {
		return rubro2;
	}

	public void setRubro2(int rubro2) {
		this.rubro2 = rubro2;
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

    public int getEmpresaNif1() {
		return empresaNif1;
	}

	public void setEmpresaNif1(int empresaNif1) {
		this.empresaNif1 = empresaNif1;
	}

	public int getEmpresaNif2() {
		return empresaNif2;
	}

	public void setEmpresaNif2(int empresaNif2) {
		this.empresaNif2 = empresaNif2;
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

	@Override
    public String toString() {
        return "entity.InterGroupDetalle[ interGrupoId=" + interGrupoId + " ]";
    }
    
    
    
    
}
