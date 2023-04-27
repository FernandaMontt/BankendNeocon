package com.forum.project.response;

import java.util.List;
import com.forum.project.model.Rubro;

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
