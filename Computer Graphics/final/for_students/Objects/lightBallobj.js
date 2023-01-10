import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";
import { shaderMaterial } from "../../libs/CS559-Framework/shaderHelper.js";
import * as SimpleObjects from "../../libs/CS559-Framework/SimpleObjects.js";

let shinyBall = 0;
export class GrLightBall extends GrObject {
    constructor(params = {}) {
      let mat = shaderMaterial("./shaders/shinyBall.vs", "./shaders/shinyBall.fs", {
        uniforms: {
          color: { value: new T.Vector3(0.4, 0.5, 0.5) },
          time: { value: 0 },
        },
      });

      let geometry = new T.SphereGeometry(0.25, 32, 32);
      let sphere = new T.Mesh( geometry, mat);
    
      

      super(`shinyBall-${shinyBall++}`, sphere);
      this.mat = mat;
      // add an "advance" function to animate this cube
      this.cubeTime = 0;

      sphere.position.x = params.x ? Number(params.x) : 0;
      sphere.position.y = params.y ? Number(params.y) : 0;
      sphere.position.z = params.z ? Number(params.z) : 0;
      
    }

    stepWorld(delta, timeofday) {
      this.cubeTime += delta;
      let newR = Math.sin(this.cubeTime / 200) / 2 + 0.5; // get a number between 0-1
      this.mat.uniforms.color.value.x = newR;
      this.mat.uniforms.time.value = this.cubeTime * 0.001; // pass in the time in seconds
    }

}