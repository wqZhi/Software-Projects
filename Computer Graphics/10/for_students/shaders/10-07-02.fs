/* a simple procedural texture */
/* the student should change this to implement a checkerboard */

/* passed interpolated variables to from the vertex */
varying vec2 v_uv;

/* colors for the checkerboard */
uniform vec3 light;
uniform vec3 dark;

/* number of checks over the UV range */
uniform float checks;

void main()
{
    float x = v_uv.x * checks;
    float y = v_uv.y * checks;

    float xc = floor(x);
    float yc = floor(y);

    float dx = abs(0.5 - (x-xc));
    float dy = abs(0.5 - (y-yc));

    float d = max(dx, dy);

    float blur = 0.05;
    float mixs = smoothstep(0.5 - blur, 0.5, d) - 0.5;

    if (mod(xc+yc, 2.0) == 1.0) {
        gl_FragColor = vec4(mix(dark,light, mixs), 1.);
    }
    else{
        gl_FragColor = vec4(mix(light,dark,mixs), 1.);
    }
}

