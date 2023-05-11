package cl.forum.neoconportal.response;

import java.util.List;

import cl.forum.neoconportal.model.InterGroupHeader;
import lombok.Data;
@Data
public class InterGroupHResponse {
	
	private List<InterGroupHeader> interGroupHeader;

	public List<InterGroupHeader> getInterGroupHeader() {
		return interGroupHeader;
	}

	public void setInterGroupHeader(List<InterGroupHeader> interGroupHeader) {
		this.interGroupHeader = interGroupHeader;
	}

}
