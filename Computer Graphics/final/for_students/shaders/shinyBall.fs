uniform vec3 color;

// We also passed in the time as a uniform (for bonus exercise)
uniform float time;

void main()
{
    gl_FragColor = vec4(color, time);
}