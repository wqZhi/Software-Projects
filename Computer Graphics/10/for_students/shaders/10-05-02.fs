/* a simple procedural texture: dots */

/* pass interpolated variables to from the vertex */
varying vec2 v_uv;

/* colors for the dots */
uniform vec3 light;
uniform vec3 dark;
uniform vec3 purple;

/* number of dots over the UV range */
uniform float dots;

/* how big are the circles */
uniform float radius;

void main()
{
    float x = v_uv.x * dots;
    float y = v_uv.y * dots;

    float xc = floor(x);
    float yc = floor(y);

    float dx = x-xc-.5;
    float dy = y-yc-.5;

    float d = sqrt(dx*dx + dy*dy);
    float dc = step(d,radius);

    if (mod(xc + yc, 2.0) == 1.0) {
        gl_FragColor = vec4(mix(light,dark,dc), 1.);
    }
    else{
        gl_FragColor = vec4(mix(light,purple,dc), 1.);
    }
    
}

