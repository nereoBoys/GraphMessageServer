package graphmessage.pojos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BannedUserRegistry {
	
	private ArrayList<String> bannedUsers = new ArrayList<>();

	public ArrayList<String> getBannedUsers() {
		return bannedUsers;
	}

	public void setBannedUsers(ArrayList<String> bannedUsers) {
		this.bannedUsers = bannedUsers;
	}

}
