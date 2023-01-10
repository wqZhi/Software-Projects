/*
* simple diffuse lighting shader
* constant direction
* constant base material color
* light color is just white
* two-sided lighting
*/
varying vec3 l_normal;

// note that this is in WORLD COORDINATES
const vec3 lightDirWorld = vec3(1,1,1);
const vec3 baseColor = vec3(1,.8,.4);

void main()
{
    // we need to renormalize the normal since it was interpolated
    vec3 nhat = normalize(l_normal);

    // get the lighting vector in the view coordinates
    // warning: this is REALLY wasteful!
    vec3 lightDir = normalize(vec4(lightDirWorld, 0)).xyz;

    // deal with two sided lighting
    float light = (dot(nhat, lightDir));

    // brighten the base color
    gl_FragColor = vec4(light * baseColor,1);
}

