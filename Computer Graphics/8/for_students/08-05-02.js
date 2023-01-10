/*jshint esversion: 6 */
// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../libs/CS559-Framework/GrObject.js";

// define a class of Domino here - it should be a subclass of GrObject
class DominoGr extends GrObject {
    constructor() {
      let geometry = new T.BufferGeometry();
      //
      // while the two triangles have 4 certices, we need to split the vertices
      // so that they can have different normals
      const vertices = new Float32Array( [    
         // c_1  
         0,0,0, // c-1
         0,1,0, // c-8
         1,0,0, // c-2
         0,1,0, // c-8
         1,1,0, // c-7
         1,0,0, // c-2

         // c_2  
         0,1,0, // c-8
         0,2,0, // c-9
         1,1,0, // c-7
         0,2,0, // c-9
         1,2,0, // c-10
         1,1,0, // c-7

         //b_1
         1,0,0, // b-2
         1,1,0, // b-7
         1,1,0.3, // b-6
         1,0,0, // b-2
         1,1,0.3, // b-6
         1,0,0.3, // b-3

         //b_2
         1,1,0, // b-7
         1,2,0, // b-10
         1,2,0.3, // b-11
         1,1,0, // b-7
         1,2,0.3, // b-11
         1,1,0.3, // b-6
    
         //a_1
         1,0,0.3, // a-3
         1,1,0.3, // a-6
         0,1,0.3, // a-5
         1,0,0.3, // a-3
         0,1,0.3, // a-5
         0,0,0.3, // a-4

         //a_2
         1,1,0.3, // a-6
         1,2,0.3, // a-11
         0,2,0.3, // a-12
         1,1,0.3, // a-6
         0,2,0.3, // a-12
         0,1,0.3, // a-5

         //d_1
         0,0,0.3, // d-4
         0,1,0.3, // d-5
         0,1,0, // d-8
         0,0,0.3, // d-4
         0,1,0, // d-8
         0,0,0, // d-1

         //d_2
         0,1,0.3, // d-5
         0,2,0.3, // d-12
         0,2,0, // d-9
         0,1,0.3, // d-5
         0,2,0, // d-9
         0,1,0, // d-8

         //root
         1,2,0.3, // e-11
         1,2,0, // e-10
         0,2,0, // e-9
         1,2,0.3, // e-11
         0,2,0, // e-9
         0,2,0.3, // e-12

         //bottom
         0,0,0, // f-1
         1,0,0, // f-2
         1,0,0.5, // f-3
         0,0,0, // f-1
         1,0,0.5, // f-3
         0,0,0.5, // f-4
      ]);
      geometry.setAttribute('position',new T.BufferAttribute(vertices,3));
      geometry.computeVertexNormals();
      // give it UVs
      // @@Snippet:texcoords
      const uvs = new Float32Array( [
         // c_1  
         0,2/3, // c-1
         0,1, // c-8
         1/3,2/3, // c-2
         0,1, // c-8
         1/3,1, // c-7
         1/3,2/3, // c-2

         // c_2  
         0,2/3, // c-1
         0,1, // c-8
         1/3,2/3, // c-2
         0,1, // c-8
         1/3,1, // c-7
         1/3,2/3, // c-2

         //b_1
         0,2/3, // c-1
         0,1, // c-8
         1/3,2/3, // c-2
         0,1, // c-8
         1/3,1, // c-7
         1/3,2/3, // c-2

         //b_2
         0,2/3, // c-1
         0,1, // c-8
         1/3,2/3, // c-2
         0,1, // c-8
         1/3,1, // c-7
         1/3,2/3, // c-2

         //a_1
         1, 0, // a-3
         1, 1/3, // a-6
         2/3, 1/3, // a-5
         1, 0, // a-3
         2/3, 1/3, // a-5
         2/3, 0, // a-4

         //a_2
         1, 0, // a-6
         1, 1/3, // a-11
         2/3, 1/3, // a-12
         1, 0, // a-6
         2/3, 1/3, // a-12
         2/3, 0, // a-5

         //d_1
         0,2/3, // c-1
         0,1, // c-8
         1/3,2/3, // c-2
         0,1, // c-8
         1/3,1, // c-7
         1/3,2/3, // c-2

         //d_2
         0,2/3, // c-1
         0,1, // c-8
         1/3,2/3, // c-2
         0,1, // c-8
         1/3,1, // c-7
         1/3,2/3, // c-2

         //root
         0,2/3, // c-1
         0,1, // c-8
         1/3,2/3, // c-2
         0,1, // c-8
         1/3,1, // c-7
         1/3,2/3, // c-2

         //bottom
         0,2/3, // c-1
         0,1, // c-8
         1/3,2/3, // c-2
         0,1, // c-8
         1/3,1, // c-7
         1/3,2/3, // c-2
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
    // grobj.objects[0].translateY(y);
    return grobj;
  }

  let world = new GrWorld();
  
  let dice1 = shift(new DominoGr(), 0);
  world.add(dice1);

// put the domino into the world Here
// you can, of course, add more than 1

world.go();
