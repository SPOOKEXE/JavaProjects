package main;

public class Main {

	public static void main(String[] args) {
		TestProject TestProject = new TestProject();
		TestProject.init();
		
		Thread thread = new Thread(TestProject);
		thread.start();
	}

}
