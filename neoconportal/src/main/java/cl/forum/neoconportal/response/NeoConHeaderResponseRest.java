package cl.forum.neoconportal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NeoConHeaderResponseRest {
	
	private NeoConHeaderResponse neoConHeaderResponse = new NeoConHeaderResponse();

	public NeoConHeaderResponse getNeoConHeaderResponse() {
		return neoConHeaderResponse;
	}

	public void setNeoConHeaderResponse(NeoConHeaderResponse neoConHeaderResponse) {
		this.neoConHeaderResponse = neoConHeaderResponse;
	}
	
}
