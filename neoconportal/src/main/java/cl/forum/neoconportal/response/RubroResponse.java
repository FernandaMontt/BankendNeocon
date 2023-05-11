package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.Rubro;
import lombok.Data;

@Data
public class RubroResponse {

	private List<Rubro> rubro;

	public List<Rubro> getRubro() {
		return rubro;
	}

	public void setRubro(List<Rubro> rubro) {
		this.rubro = rubro;
	}
	
	
}
