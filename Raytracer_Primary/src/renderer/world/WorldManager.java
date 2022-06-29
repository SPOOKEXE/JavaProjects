package renderer.world;

import renderer.display.IDisplay;
import renderer.input.UserInput;

import java.util.ArrayList;
import java.util.List;

public class WorldManager {
	
	private List<IWorld> worlds;
	private List<IDisplay> displays;
	private UserInput userInput;

	private boolean allowUserInput;
	
	public WorldManager() {
		this.worlds = new ArrayList<IWorld>();
		this.displays = new ArrayList<IDisplay>();
		this.userInput = new UserInput();
		this.allowUserInput = true;
	}
	
	public List<IWorld> getWorlds() {
		return this.worlds;
	}
	
	public List<IDisplay> getDisplays() {
		return this.displays;
	}
	
	public int countWorlds() {
		return this.worlds.size();
	}
	
	public void addWorlds( ArrayList<IWorld> worlds ) {
		for (IWorld world : worlds) {
			this.worlds.add(world);
			this.displays.addAll( world.getDisplays() );
		}
		System.out.println("Added " + worlds.size() + " Worlds to World Manager.");
	}
	
	public void clearWorlds() {
		System.out.println("Clear Worlds");
	}

	public void update() {

		if (this.allowUserInput) {

			if (this.userInput.keyboard.getLeft()) {

			}

			if (this.userInput.keyboard.getRight()) {

			}

			if (this.userInput.keyboard.getUp()) {

			}

			if (this.userInput.keyboard.getDown()) {

			}

		}

	}

	public void init() {
		
	}
	
}
