package statCreator;

public class CloseApplication {
	
	public static void closeApplication() {
		Boolean answer = ConfirmBox.display("Exit so soon?", "Are you sure you want to exit?");
    	if (answer)
    		System.exit(0);

	}

}
