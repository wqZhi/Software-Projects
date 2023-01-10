// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../libs/CS559-Framework/GrObject.js";
import * as InputHelpers from "../libs/CS559/inputHelpers.js";

let parentOfCanvas = document.getElementById("div1");
let world = new GrWorld({ where: parentOfCanvas });

let normal_map = new T.TextureLoader().load("./normalMap.jpeg"); // https://polyhaven.com/a/aerial_rocks_02
let bump_map = new T.TextureLoader().load("./bumpMap.jpeg"); //https://polyhaven.com/a/aerial_rocks_02
let color_map = new T.TextureLoader().load("./colorMap.jpeg"); //https://polyhaven.com/a/aerial_rocks_02

let sphereNormalObj = 1;
class GrSphere_normal extends GrObject {
    constructor() {
        let geometry = new T.SphereBufferGeometry(1, 20, 20);
        let t = new T.MeshStandardMaterial({ 
            normalMap: normal_map,
        });
        let sphere = new T.Mesh(geometry, t);

      super(`GrSphere_normal ${sphereNormalObj++}`, sphere);
    }
}

let sphereBumpObj = 1;
class GrSphere_bump extends GrObject {
    constructor() {
        let geometry = new T.SphereBufferGeometry(1, 20, 20);
        let t = new T.MeshStandardMaterial({ 
            bumpMap: bump_map
        });
        let sphere = new T.Mesh(geometry, t);

      super(`GrSphere_bump ${sphereBumpObj++}`, sphere);
    }
}

let spherecombineObj = 1;
class GrSphere_combine extends GrObject {
    constructor() {
        let geometry = new T.SphereBufferGeometry(1, 20, 20);
        let t = new T.MeshStandardMaterial({ 
            map: color_map,
            bumpMap: bump_map
        });
        let sphere = new T.Mesh(geometry, t);

      super(`GrSphere_combine ${spherecombineObj++}`, sphere);
    }
}

function shift(grobj, x, y, z) {
    grobj.objects[0].translateX(x);
    grobj.objects[0].translateY(y);
    grobj.objects[0].translateZ(z);
    return grobj;
}
  
let sphere_N= shift(new GrSphere_normal(), 0, 1, 0);
world.add(sphere_N);

let sphere_B= shift(new GrSphere_bump(), -3, 1, 0);
world.add(sphere_B);

let sphere_C= shift(new GrSphere_combine(), 3, 1, 0);
world.add(sphere_C);


world.go();

