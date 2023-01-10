/*jshint esversion: 6 */
// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../libs/CS559-Framework/GrObject.js";

// define a class of Dice here - it should be a subclass of GrObject
class DiceGr extends GrObject {
    constructor() {
      let geometry = new T.BufferGeometry();
      //
      // while the two triangles have 4 certices, we need to split the vertices
      // so that they can have different normals
      const vertices = new Float32Array( [
         0,0,0, // c-1
         0,1,0, // c-8
         1,0,0, // c-2
         0,1,0, // c-8
         1,1,0, // c-7
         1,0,0, // c-2

         1,0,0, // b-2
         1,1,0, // b-7
         1,1,1, // b-6
         1,0,0, // b-2
         1,1,1, // b-6
         1,0,1, // b-3
    
         1,0,1, // a-3
         1,1,1, // a-6
         0,1,1, // a-5
         1,0,1, // a-3
         0,1,1, // a-5
         0,0,1, // a-4

         0,0,1, // d-4
         0,1,1, // d-5
         0,1,0, // d-8
         0,0,1, // d-4
         0,1,0, // d-8
         0,0,0, // d-1

         1,1,1, // e-6
         1,1,0, // e-7
         0,1,0, // e-8
         1,1,1, // e-6
         0,1,0, // e-8
         0,1,1, // e-5

         0,0,0, // f-1
         1,0,0, // f-2
         1,0,1, // f-3
         0,0,0, // f-1
         1,0,1, // f-3
         0,0,1, // f-4
      ]);
      geometry.setAttribute('position',new T.BufferAttribute(vertices,3));
      geometry.computeVertexNormals();
      // give it UVs
      // @@Snippet:texcoords
      const uvs = new Float32Array( [
        1/3,2/3, // c-1
        1/3,1, // c-8
        2/3,2/3, // c-2
        1/3,1, // c-8
        2/3,1, // c-7
        2/3,2/3, // c-2

        1,1/3, // b-2
        1,2/3, // b-7
        2/3,2/3, // b-6
        1,1/3, // b-2
        2/3,2/3, // b-6
        2/3,1/3, // b-3
   
        2/3,0, // a-3
        2/3,1/3, // a-6
        1/3,1/3, // a-5
        2/3,0, // a-3
        1/3,1/3, // a-5
        1/3,0, // a-4

        1/3,1/3, // d-4
        1/3,2/3, // d-5
        0,2/3, // d-8
        1/3,1/3, // d-4
        0,2/3, // d-8
        0,1/3, // d-1

        2/3,1/3, // e-6
        2/3,2/3, // e-7
        1/3,2/3, // e-8
        2/3,1/3, // e-6
        1/3,2/3, // e-8
        1/3,1/3, // e-5

        2/3,1/3, // f-1
        1,1/3, // f-2
        1,0, // f-3
        2/3,1/3, // f-1
        1,0, // f-3
        2/3,0, // f-4
      ]);
      geometry.setAttribute('uv',new T.BufferAttribute(uvs,2));
      //@@Snippet:end

      // @@Snippet:texuse
      let tl = new T.TextureLoader().load("../images/dice_texture.png");
      let material = new T.MeshStandardMaterial({
        color: "white",
        roughness: 0.75,
        map: tl
      });

      let mesh = new T.Mesh(geometry, material);
      super("DiceGr", mesh);
    }
  }


function shift(grobj, x,y) {
  grobj.objects[0].translateX(x);
  grobj.objects[0].translateY(y);
  return grobj;
}

function rotate(grobj, arc) {
    grobj.objects[0].rotateOnAxis(new T.Vector3(1,0,0), arc)
    return grobj;
  }


let world = new GrWorld();

let dice1 = shift(new DiceGr(), 0, 2);
world.add(dice1);

let dice2 = shift(new DiceGr(), 2, 3);
dice2 = rotate(dice2, Math.PI/2);
world.add(dice2);

// put the two dice into the world Here
// don't forget to orient them so they have different numbers facing up

world.go();

