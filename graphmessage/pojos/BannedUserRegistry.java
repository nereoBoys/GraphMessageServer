package graphmessage.pojos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BannedUserRegistry {
	
	private ArrayList<Long> bannedUsers = new ArrayList<>();

	public ArrayList<Long> getBannedUsers() {
		return bannedUsers;
	}

	public void setBannedUsers(ArrayList<Long> bannedUsers) {
		this.bannedUsers = bannedUsers;
	}

}
