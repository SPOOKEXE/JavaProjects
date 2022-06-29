package renderer;

import renderer.scene.IScene;
import renderer.scene.Scene;
import renderer.scene.SceneHandler;

public class Main {

	static SceneHandler mainScene;
	
	public static void main( String[] args ) {
		
		System.out.println("Start Scene Machine");

		mainScene = new SceneHandler();
		mainScene.init();
		
		System.out.println("Generate Scene From File");
		IScene myScene = new Scene();
		myScene.loadScene("scene1");
		myScene.init();
		
	}

}
