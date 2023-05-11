package cl.forum.neoconportal.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RubroResponseRest{
	
	private RubroResponse rubroResponse = new RubroResponse();
	public RubroResponse getRubroResponse() {
		return rubroResponse;
	}
	public void setRubroResponse(RubroResponse rubroResponse) {
		this.rubroResponse = rubroResponse;
	}
	
	
}
