package main;

// https://www.youtube.com/watch?v=zfge57Ul9tM

import models.VAO;
import renderer.SimpleRenderer;
import renderer.Window;
import utilities.Vector3f;

import java.util.concurrent.TimeUnit;

public class MainApp {

    public static void main( String[] args ) throws InterruptedException {

        Window window = new Window();
        window.createWindow( 1280, 720, "Epic Window" );

        SimpleRenderer renderer = new SimpleRenderer();

        float[] vertices = {
            0, 0.75f, 0,
            -0.75f, -0.5f, 0,
            0.75f, -0.5f, 0,
        };

        int[] indices = { 0, 1, 2 };

        VAO vao = new VAO();
        vao.bind();
        vao.createFloatAttribute( 0, vertices, 3 );
        vao.createIndexBuffer( indices );
        vao.unbind();

        Vector3f position = new Vector3f(-0.5f, 0.5f, 0.0f);

        while (!window.shouldClose()) {
            renderer.renderVAO(vao, position);
            window.updateWindow();
            TimeUnit.SECONDS.sleep(0);
        }

        System.out.println("Kill");
        window.closeWindow();

    }

}
