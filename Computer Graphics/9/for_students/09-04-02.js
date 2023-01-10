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


let bookT = new T.TextureLoader().load("./bookTop.jpg");
let bookM = new T.TextureLoader().load("./bookMid.jpg");
let bookB = new T.TextureLoader().load("./bookBottom.jpg");
let carcount = 1;
class GrBook extends GrObject {
    constructor() {
        let group = new T.Group();
        let geometry_body = new T.BoxGeometry(2.5, 3.5, 0.5);
        let mat_body = new T.MeshStandardMaterial({ 
            color: "#F0E68C" 
        });

        let texture_bookT = new T.MeshStandardMaterial({ 
            map : bookT
        });

        let texture_bookM = new T.MeshStandardMaterial({ 
            map : bookM
        });

        let texture_bookB = new T.MeshStandardMaterial({ 
            map : bookB
        });

        const materials_body = [ 
            mat_body, texture_bookM, // right & left
            mat_body, mat_body, // top & bottom
            texture_bookT, texture_bookB // front & back
        ]; 

        let body = new T.Mesh(geometry_body, materials_body);
        group.add(body);

      super(`GrBook ${carcount++}`, group);
    }
  }

  function shift(grobj, x, y) {
    grobj.objects[0].translateX(x);
    grobj.objects[0].translateY(y);
    return grobj;
  }
  
  let book = shift(new GrBook(), 0, 2);
  world.add(book);

world.go();

