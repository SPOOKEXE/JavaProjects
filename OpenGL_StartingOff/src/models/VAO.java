package models;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

public class VAO {

    private int id;
    private int indexCount;

    private final List<VBO> dataVBOs = new ArrayList<VBO>();
    private VBO indexVbo;

    public VAO() {
        this.id = GL30.glGenVertexArrays();;
    }

    private void bind() {
        GL30.glBindVertexArray(id);
    }

    private void unbind() {
        GL30.glBindVertexArray(0);
    }

    public void bind(int... attributes){
        bind();
        for (int i : attributes) {
            GL20.glEnableVertexAttribArray(i);
        }
    }

    public void unbind(int... attributes){
        for (int i : attributes) {
            GL20.glDisableVertexAttribArray(i);
        }
        unbind();
    }

    public void render() {
        bind(0,1,2);
        GL11.glDrawElements(GL11.GL_TRIANGLES, getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
        unbind(0,1,2);
    }

    public void createIndexBuffer(int[] indices) {
        this.indexVbo = new VBO(GL15.GL_ELEMENT_ARRAY_BUFFER);
        indexVbo.bind();
        indexVbo.storeIntData(indices);
        this.indexCount = indices.length;
    }

    public void createFloatAttribute(int attribute, float[] data, int attrSize) {
        VBO dataVbo = new VBO(GL15.GL_ARRAY_BUFFER);
        dataVbo.bind();
        dataVbo.storeFloatData(data);
        GL20.glVertexAttribPointer(attribute, attrSize, GL11.GL_FLOAT, false, attrSize * Float.BYTES, 0);
        dataVbo.unbind();
        dataVBOs.add(dataVbo);
    }

    public void delete() {
        //Delete all vao data
        GL30.glDeleteVertexArrays(id);
        for(VBO vbo : dataVBOs){
            vbo.destroy();
        }
        if(indexVbo != null)
            indexVbo.destroy();
    }

    public int getIndexCount(){
        return indexCount;
    }

    public void setIndexCount(int indexCount) {
        this.indexCount = indexCount;
    }

}
