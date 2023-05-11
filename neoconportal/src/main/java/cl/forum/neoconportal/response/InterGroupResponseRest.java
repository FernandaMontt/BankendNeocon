package cl.forum.neoconportal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterGroupResponseRest {
	
	private InterGroupHResponse interGroupHResponse = new InterGroupHResponse();

	public InterGroupHResponse getInterGroupHResponse() {
		return interGroupHResponse;
	}

	public void setInterGroupHResponse(InterGroupHResponse interGroupHResponse) {
		this.interGroupHResponse = interGroupHResponse;
	}

}
