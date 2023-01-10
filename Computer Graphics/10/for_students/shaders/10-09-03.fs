/* Procedural shading example */
/* the student should make this more interesting */

/* pass interpolated variables to from the vertex */
varying vec2 v_uv;

/* colors for the checkerboard */
uniform vec3 light;
uniform vec3 dark;
/* number of checks over the UV range */
uniform float checks;

uniform vec3 color;
// We also passed in the time as a uniform (for bonus exercise)
uniform float time;

void main()
{
    float x = v_uv.x * checks;
    float y = v_uv.y * checks;

    float xc = floor(x);
    float yc = floor(y);

    float dx = x-xc-.5;
    float dy = y-yc-.5;

    float d = sqrt(dx * dy);
    float dc = step(d,0.5);

    gl_FragColor = vec4(mix(light,dark,dc) * color, time);
}

