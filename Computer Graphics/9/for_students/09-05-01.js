// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../libs/CS559-Framework/GrObject.js";
import * as InputHelpers from "../libs/CS559/inputHelpers.js";

let parentOfCanvas = document.getElementById("div1");
let world = new GrWorld({ where: parentOfCanvas });

let urls = [
    "./images/Right.jpeg",//right
    "./images/Left.jpeg", //left
    "./images/Top.jpeg", //Top
    "./images/Bottom.jpeg", //Bottom
    "./images/Front.jpeg", //Front
    "./images/Back.jpeg", //back
];

let skyMap = new T.CubeTextureLoader().load(urls);
world.scene.background = skyMap;

let sphereObj = 1;
class GrSphere extends GrObject {
    constructor() {
        let geometry = new T.SphereBufferGeometry(1, 20, 20);

        let t = new T.MeshStandardMaterial({ 
            roughness: 0,
            envMap: skyMap,
            metalness: 1
        });
        
        let sphere = new T.Mesh(geometry, t);

      super(`GrSphere ${sphereObj++}`, sphere);
    }
  }


function shift(grobj, x, y) {
    grobj.objects[0].translateX(x);
    grobj.objects[0].translateY(y);
    return grobj;
}
  
let sphere= shift(new GrSphere(), 0, 1);
world.add(sphere);


world.go();

