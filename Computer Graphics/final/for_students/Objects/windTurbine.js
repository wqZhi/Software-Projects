import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";

let windTurbineObj = 0;
export class GrWindTurbine extends GrObject {
  constructor(params = {}) {
    let windTurbine = new T.Group();

    let base_geom = new T.CylinderGeometry(0.2, 0.2, 5, 4);
    let base_mat = new T.MeshStandardMaterial({
      color: "white",
      metalness: 0.5,
      roughness: 0.8
    });
    let base = new T.Mesh(base_geom, base_mat);
    base.translateY(2.4);
    windTurbine.add(base);

    //engine
    let engine_geom = new T.CylinderGeometry(0.3, 0, 1.5, 8);
    let engine_mat = new T.MeshStandardMaterial({
      color: "white",
      metalness: 0.5,
      roughness: 0.8
    });
    let engine = new T.Mesh(engine_geom, engine_mat);
    engine.position.set(0.31,4.9,0);
    engine.rotateZ(Math.PI/2);
    windTurbine.add(engine);

    //roter
    let roter_geom = new T.CylinderGeometry(0.1, 0.1, 0.3, 8);
    let roter_mat = new T.MeshStandardMaterial({
      color: "white",
      metalness: 0.5,
      roughness: 0.8
    });
    let roter = new T.Mesh(roter_geom, roter_mat);
    roter.position.set(-0.5,4.9,0);
    roter.rotateZ(Math.PI/2);
    windTurbine.add(roter);

    //fan
    let fan_geom = new T.CylinderGeometry(0.2, 0.1, 2.5, 2);
    let fan_mat = new T.MeshStandardMaterial({
      color: "white",
      metalness: 0.3,
      roughness: 0.6
    });

    for (let i =0; i< 3; i++) {
      let fan = new T.Mesh(fan_geom, fan_mat);
      roter.add(fan);
      fan.rotateZ(Math.PI/2);
      fan.rotateX(i * Math.PI*2/3);
      fan.translateY(-1.2);
      fan.translateX(0.1);
    }
  
    // note that we have to make the Object3D before we can call
    // super and we have to call super before we can use this
    super(`windTurbine-${windTurbineObj++}`, windTurbine);
    this.whole_ob = windTurbine;
    this.roter_ob = roter;

    // put the object in its place
    this.whole_ob.position.x = params.x ? Number(params.x) : 0;
    this.whole_ob.position.y = params.y ? Number(params.y) : 0;
    this.whole_ob.position.z = params.z ? Number(params.z) : 0;
    this.whole_ob.rotateY(params.faceDir ? params.faceDir : 0);
    let scale = params.size ? Number(params.size) : 1;
    windTurbine.scale.set(scale, scale, scale);
  }

  stepWorld(delta, timeOfDay) {
    this.roter_ob.rotateY(0.005 * delta* Math.random());
  }

}