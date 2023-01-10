/* Procedural shading example */
/* the student should make this more interesting */

/* pass interpolated variables to from the vertex */
varying vec2 v_uv;

// get the texture from the program
uniform sampler2D tex;

 /*
* fragment shader for specular lighting exercise
*/
varying vec3 v_normal;
varying vec3 v_position;
uniform float shininess;

// note that this is in WORLD COORDINATES
const vec3 lightDirWorld = vec3(0,0,1);
const vec3 specularBaseColor = vec3(1,1,1);

void main()
{
    // get the view direction in view-space coordinates
    // remember in view space, the camera is the origin
    vec3 viewDir = normalize(- v_position);
    // convert the lighting direction in view-space coordinates
    vec3 lightDir = normalize((viewMatrix * vec4(lightDirWorld,0.)).xyz);
    // re-normalize the interpolated normal vector
    vec3 normal = normalize(v_normal);
    // get angle of reflection to compute alignment
    // without using `reflect`, alignment can be computed by taking the halfway vetor H and evaluating dot(N,H)
    vec3 reflDir = reflect(-lightDir,normal);
    float alignment = max(dot(viewDir,reflDir),0.);

    vec4 obj = texture2D(tex, v_uv);
    vec3 specularBaseColor = vec3(obj.x,obj.y,obj.z);
    vec3 specularColor = specularBaseColor * pow(alignment, pow(2.,shininess));

    gl_FragColor = vec4(specularColor, 1);
}

