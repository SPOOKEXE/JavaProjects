package engine;

public class Main {

	public static Scene mainScene = null;
	
	// Main //
	public static void main(String[] args) {
		mainScene = new Scene();
		mainScene.loadJSON("sample.json");
	}

}
