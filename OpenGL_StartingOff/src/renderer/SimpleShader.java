package renderer;

import renderer.shaders.Shader;
import renderer.shaders.uniforms.UniformVec3;

public class SimpleShader extends Shader {

    protected UniformVec3 worldPosition = new UniformVec3("worldPosition");

    public SimpleShader() {
        super("\\resources\\shaders\\vert.glsl", "\\resources\\shaders\\frag.glsl");
        super.locateUniform( worldPosition );
    }

    public UniformVec3 getWorldPosition() {
        return worldPosition;
    }

}
