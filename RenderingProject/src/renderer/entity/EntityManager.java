package renderer.entity;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

import renderer.entity.builder.BasicEntityBuilder;
import renderer.entity.builder.ComplexEntityBuilder;
import renderer.input.ClickType;
import renderer.input.Keyboard;
import renderer.input.Mouse;
import renderer.input.UserInput;
import renderer.point.MyVector;
import renderer.point.PointConverter;
import renderer.world.Camera;

import java.awt.Color;

public class EntityManager {

	private List<IEntity> entities;
	
	private int initialX, initialY;
	private double mouseSensitivity = 0.8;
	private MyVector lightVector = MyVector.normalize( new MyVector(1, 1, 1) );
	
	private Mouse mouse;
	private Keyboard keyboard;
	
	private Camera camera; // move to WorldManager
	private static final double CAMERA_MOVE_SPEED = 10;
	
	public EntityManager() {
		this.entities = new ArrayList<IEntity>();
		this.camera = new Camera(0, 0, 0);
	}
	
	public void init( UserInput userInput ) {
		this.mouse = userInput.mouse;
		this.keyboard = userInput.keyboard;
		this.entities.add( BasicEntityBuilder.createCube(100, 400, 0, 0) );
		this.entities.add( BasicEntityBuilder.createDiamond(Color.ORANGE , 100, 800, 0, 0 ) );
		this.entities.add( ComplexEntityBuilder.createRubiksCube(100, 0, 0, 0) );
		this.entities.add( BasicEntityBuilder.createSphere(Color.RED, 100, 50, -400, 0, 0) );
		this.setLighting();
	}
	
	
	public void update(  ) {
		int x = this.mouse.getX();
		int y = this.mouse.getY();

		int deltaX = (int) ( (x - initialX) * mouseSensitivity );
		int deltaY = (int) ( (y - initialY) * mouseSensitivity );
		
		if (this.mouse.getButton() == ClickType.LeftClick) {
			this.rotate( true, 0, -deltaY, deltaX );
		} else if (this.mouse.getButton() == ClickType.RightClick) {
			this.rotate( true, deltaX, -deltaY, 0 );
		}
		
		if (this.mouse.isScrollingUp() ) {
			PointConverter.zoomIn();
		} else if (this.mouse.isScrollingDown()) {
			PointConverter.zoomOut();
		}
		
		if (this.keyboard.getLeft()) {
			this.camera.translate(0, -CAMERA_MOVE_SPEED, 0);
			for (IEntity entity : this.entities) {
				entity.translate(0, CAMERA_MOVE_SPEED, 0);
			}
		}
		
		if (this.keyboard.getRight()) {
			this.camera.translate(0, CAMERA_MOVE_SPEED, 0);
			for (IEntity entity : this.entities) {
				entity.translate(0, -CAMERA_MOVE_SPEED, 0);
			}
		}
		
		if (this.keyboard.getUp()) {
			this.camera.translate(0, 0, CAMERA_MOVE_SPEED);
			for (IEntity entity : this.entities) {
				entity.translate(0, 0, -CAMERA_MOVE_SPEED);
			}
		}
		
		if (this.keyboard.getDown()) {
			this.camera.translate(0, 0, -CAMERA_MOVE_SPEED);
			for (IEntity entity : this.entities) {
				entity.translate(0, 0, CAMERA_MOVE_SPEED);
			}
		}
		
		if (this.keyboard.getForward()) {
			this.camera.translate(-CAMERA_MOVE_SPEED, 0, 0);
			for (IEntity entity : this.entities) {
				entity.translate(CAMERA_MOVE_SPEED, 0, 0);
			}
		}
		
		if (this.keyboard.getBackward()) {
			this.camera.translate(CAMERA_MOVE_SPEED, 0, 0);
			for (IEntity entity : this.entities) {
				entity.translate(-CAMERA_MOVE_SPEED, 0, 0);
			}
		}
		
		/*
		for (IEntity entity : this.entities) {
			entity.shift( );
		}*/
		
		this.mouse.resetScroll();
		this.keyboard.update();
		
		initialX = x;
		initialY = y;
	}
	
	public void render( Graphics g ) {
		for (IEntity entity : this.entities) {
			entity.render( g );
		}
	}
	
	private void rotate( boolean clockWise, double xDegrees, double yDegrees, double zDegrees ) {
		for (IEntity entity : this.entities) {
			entity.rotate(clockWise, xDegrees, yDegrees, zDegrees, this.lightVector);
		}
	}
	
	private void setLighting() {
		for (IEntity entity : this.entities) {
			entity.setLighting( this.lightVector );
		}
	}
	
}
