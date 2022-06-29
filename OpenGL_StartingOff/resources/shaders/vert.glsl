#version 140

in vec2 position;

uniform vec3 worldPosition;

void main(void) {

    gl_Position = vec4(position, 0.0, 1.0) + vec4(worldPosition, 0.0);

}