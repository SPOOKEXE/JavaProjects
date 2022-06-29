package renderer;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import renderer.entity.EntityManager;
import renderer.input.UserInput;

// EPISODE 10 : https://www.youtube.com/watch?v=hdxwirZPw6Y
// https://www.youtube.com/playlist?list=PLgRPwj3No0VLXFoqYnL2aYhczXB2qwKvp

public class Display extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int FPS = 60;
	public static final int WIDTH = 800; // Constant
	public static final int HEIGHT = 600; // Constant

	private static String title = "3D Renderer";
	private static boolean running = false;

	private Thread thread;
	private JFrame frame;

	private EntityManager entityManager;
	private UserInput userInput;

	public Display() {
		this.frame = new JFrame();

		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);

		this.userInput = new UserInput();
		this.entityManager = new EntityManager();

		this.addMouseListener(this.userInput.mouse);
		this.addMouseMotionListener(this.userInput.mouse);
		this.addMouseWheelListener(this.userInput.mouse);
		this.addKeyListener(this.userInput.keyboard);
	}

	public synchronized void start() {
		running = true;
		this.thread = new Thread(this, "Display");
		this.thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		final double ns = 10e8 / FPS; // 60 FPS
		final double second = 1e3;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int frames = 0;

		this.entityManager.init(this.userInput);

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
				render();
				frames++;
			}
			if (System.currentTimeMillis() - timer > second) {
				timer += second;
				this.frame.setTitle(title + " | " + frames + " fps");
				frames = 0;
			}
		}

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); // triple buffering
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);
		this.entityManager.render(g);
		g.dispose();
		bs.show();
	}

	private void update() {
		this.entityManager.update();
	}

	// STANDALONE
	public static void main(String[] args) {
		Display display = new Display();
		display.frame.setTitle(title);
		display.frame.add(display);
		display.frame.pack();
		display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop the program when the window is closed
		display.frame.setLocationRelativeTo(null);
		display.frame.setResizable(false);
		display.frame.setVisible(true);
		display.start();
	}

}
