/* Procedural shading example */
/* the student should make this more interesting */

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


/*
* fragment shader for specular lighting exercise
*/
varying vec3 v_normal;
varying vec3 v_position;
uniform float shininess;
// note that this is in WORLD COORDINATES
const vec3 diffuselightDirWorld = vec3(0,0,1);
const vec3 baseColor = vec3(1,.8,.4);
// const vec3 lightDirWorld = vec3(0,1,0);
// const vec3 specularBaseColor = vec3(1,1,1);

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

    vec3 normal = normalize(v_normal);

    float diffuselight = abs(dot(normal, diffuselightDirWorld));

    if (mod(xc + yc, 2.0) == 1.0) {
        gl_FragColor = vec4(mix(diffuselight * light, diffuselight * dark, dc), 1.);
    }
    else{
        gl_FragColor = vec4(mix(diffuselight * light, diffuselight * purple, dc), 1.);
    }
}

