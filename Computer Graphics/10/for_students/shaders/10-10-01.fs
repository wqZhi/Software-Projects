/*
 * Placeholder shader
 * The student should replace this with their own shader file.
 */

uniform vec2 u_resolution;

vec2 mirrorTile(vec2 _st, float _zoom) {
    _st *= _zoom;
    if (fract(_st.y * 0.5) > 0.5){
        _st.x = _st.x+0.5;
        _st.y = 1.0-_st.y;
    }
    return fract(_st);
}

float fillY(vec2 _st, float _pct,float _antia) {
  return smoothstep( _pct-_antia, _pct, _st.y);
}


void main() {
    vec2 st = gl_FragCoord.xy / u_resolution.xy;
    vec3 color = vec3(0.0);

    st = mirrorTile(st*vec2(1., 2.), 5.);
    float x = st.x*2.;
    float a = floor(1.+sin(x*3.14));
    float b = floor(1.+sin((x+1.)*3.14));
    float f = fract(x);

    color = vec3(fillY (st, mix(a, b, f), 0.01) );

    gl_FragColor = vec4( color, 1.0 );
}

