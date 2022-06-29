import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

// 8 + 9th September 2021

public class Main {

	static ArrayList<Scene> scenes = new ArrayList<Scene>();
	
	public static void main(String[] args) {
		
		// create scene
		Scene baseScene = new Scene(720, 480);
		baseScene.Init();
		baseScene.CreateDefault();
		scenes.add(baseScene);
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			
			private boolean isSelectingFile = false;
			
			public boolean dispatchKeyEvent(KeyEvent ke) {
				
				if (ke.getKeyChar() == 'q' && !isSelectingFile) {
					isSelectingFile = true;
					File openedObjFile = FileOpener.getObjFile();
					if (openedObjFile != null) {
						ArrayList<Triangle> objTriangles = ObjFileProcessor.decodeObjFile(openedObjFile, 3);
						baseScene.clearTriangles();
						baseScene.addTriangles(objTriangles);
						baseScene.renderPanel.repaint();
					}
					isSelectingFile = false;
				}
				
				//System.out.println(ke.getKeyChar());
	            return false;
	        }
	    });
		
	}

}
