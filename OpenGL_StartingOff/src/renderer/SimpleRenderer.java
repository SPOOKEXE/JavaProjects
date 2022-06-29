package renderer;

import models.VAO;
import org.lwjgl.opengl.GL11;
import utilities.Vector3f;

public class SimpleRenderer {

    private SimpleShader shader;

    public SimpleRenderer() {
        shader = new SimpleShader();
    }

    private void prepare() {
        GL11.glClear( GL11.GL_COLOR_BUFFER_BIT );
        GL11.glClearColor( 0.5f, 0.75f, 1.0f, 1 );
    }

    public void renderVAO(VAO vao, Vector3f position ) {
        prepare();
        shader.useProgram();
        shader.getWorldPosition().loadVec3( position.x, position.y, position.z );
        vao.render();
        shader.stopProgram();
    }

}
