package graphmessage.logic;

public class LogicContainer {
	
	private static Logic logic = new Logic();

	public static Logic getLogic() {
		return logic;
	}

	public static void setLogic(Logic logic) {
		LogicContainer.logic = logic;
	}
	
}
