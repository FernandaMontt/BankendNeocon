package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.NeoConHeader;
import lombok.Data;

@Data
public class NeoConHeaderResponse {
	
	private List<NeoConHeader> neoConHeader;

	public List<NeoConHeader> getNeoConHeader() {
		return neoConHeader;
	}

	public void setNeoConHeader(List<NeoConHeader> neoConHeader) {
		this.neoConHeader = neoConHeader;
	}

}
