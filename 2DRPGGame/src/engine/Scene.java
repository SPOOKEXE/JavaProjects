package engine;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import classes.BaseObject;
import utility.FileSystem;

public class Scene {

	// Variables //
	public ArrayList<BaseObject> activeObjects;
	
	// Constructor //
	public Scene() {
		activeObjects = new ArrayList<BaseObject>();
	}
	
	public Scene( String loadScene ) {
		activeObjects = new ArrayList<BaseObject>();
		this.loadJSON(loadScene);
	}
	
	// Methods //
	public void loadJSON( String fileName ) {
		this.cleanup();
		
		JSONObject sceneJSONData = FileSystem.loadJSONFile( fileName );
		if (sceneJSONData == null) {
			System.out.println("Unable to load json file @ " + fileName);
			return;
		}
		
		// Create scene based on JSON data
		System.out.println(sceneJSONData.toJSONString());
	}
	
	public void cleanup() {
		// let garbage collect cleanup the old stuff
		activeObjects = new ArrayList<BaseObject>();
	}
	
}
