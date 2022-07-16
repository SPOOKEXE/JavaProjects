package classes;

import base.Vector2c;

public class Player extends BaseObject {

	// Constructor //
	public Player() {
		
	}
	
	// Methods //
	public void Move( Vector2c direction ) {
		this.position.add(direction);
	}
	
}
