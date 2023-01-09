/*jshint esversion: 6 */
// @ts-check

import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";

//from wb 08-06-01


let yellowHouse = 0;
export class Grhouse1 extends GrObject {
    constructor(params = {}) {
      let geometry = new T.BufferGeometry();
      //
      // while the two triangles have 4 certices, we need to split the vertices
      // so that they can have different normals
      const vertices = new Float32Array( [    
        0,0,0, // c-1
        0,1,0, // c-8
        2,0,0, // c-2
        0,1,0, // c-8
        2,1,0, // c-7
        2,0,0, // c-2

        2,0,0, // b-2
        2,1,0, // b-7
        2,1,1, // b-6
        2,0,0, // b-2
        2,1,1, // b-6
        2,0,1, // b-3
   
        2,0,1, // a-3
        2,1,1, // a-6
        0,1,1, // a-5
        2,0,1, // a-3
        0,1,1, // a-5
        0,0,1, // a-4

        0,0,1, // d-4
        0,1,1, // d-5
        0,1,0, // d-8
        0,0,1, // d-4
        0,1,0, // d-8
        0,0,0, // d-1

        2,1,1, // e-6
        2,1,0, // e-7
        0,1,0, // e-8
        2,1,1, // e-6
        0,1,0, // e-8
        0,1,1, // e-5

        0,0,0, // f-1
        2,0,0, // f-2
        1,0,1, // f-3
        0,0,0, // f-1
        1,0,1, // f-3
        0,0,1, // f-4

        2,1,0, // g-7
        2,1.4,0.5, // g-10
        2,1,1, // g-6

        0,1,1, // h-5
        0,1.4,0.5, // h-9
        0,1,0, // h-8

        2,1,1, // i-6
        0,1.4,0.5, // i-9
        0,1,1, // i-5
        2,1,1, // i-6
        2,1.4,0.5, // i-10
        0,1.4,0.5, // i-9

        2,1,0, // j-7
        0,1.4,0.5, // j-9
        2,1.4,0.5, // j-10
        2,1,0, // j-7
        0,1,0, // j-8
        0,1.4,0.5, // j-9

      ]);
      geometry.setAttribute('position',new T.BufferAttribute(vertices,3));
      geometry.computeVertexNormals();
      // give it UVs
      // @@Snippet:texcoords
      const uvs = new Float32Array( [
        1/3,0, // c-1
        0,1/3, // c-8
        0,0, // c-2
        1/3,0, // c-8
        1/3,1/3, // c-7
        0,1/3, // c-2

        1/3,0, // c-1
        0,1/3, // c-8
        0,0, // c-2
        1/3,0, // c-8
        1/3,1/3, // c-7
        0,1/3, // c-2
   
        1,0, // a-3
        1,1, // a-6
        0,1, // a-5
        1,0, // a-3
        0,1, // a-5
        0,0, // a-4

        1/3,0, // c-1
        0,1/3, // c-8
        0,0, // c-2
        1/3,0, // c-8
        1/3,1/3, // c-7
        0,1/3, // c-2

        1/3,0, // c-1
        0,1/3, // c-8
        0,0, // c-2
        1/3,0, // c-8
        1/3,1/3, // c-7
        0,1/3, // c-2

        1/3,0, // c-1
        0,1/3, // c-8
        0,0, // c-2
        1/3,0, // c-8
        1/3,1/3, // c-7
        0,1/3, // c-2

        1/3,0, // g-7
        0,1/3, // g-10
        0,0, // g-6

        1/3,0, // h-5
        0,1/3, // h-9
        0,0, // h-8

        1/3,0, // c-1
        0,1/3, // c-8
        0,0, // c-2
        1/3,0, // c-8
        1/3,1/3, // c-7
        0,1/3, // c-2

        1/3,0, // c-1
        0,1/3, // c-8
        0,0, // c-2
        1/3,0, // c-8
        1/3,1/3, // c-7
        0,1/3, // c-2
      ]);
      geometry.setAttribute('uv',new T.BufferAttribute(uvs,2));
      //@@Snippet:end

      // @@Snippet:texuse
      let tl = new T.TextureLoader().load("./textures/house.png");
      let material = new T.MeshStandardMaterial({
        color: "#BC9E82",
        roughness: 0.75,
        map: tl
      });


      let mesh = new T.Mesh(geometry, material);
      mesh.translateX(params.x || 0);
      mesh.translateY(params.y || 0);
      mesh.translateZ(params.z || 0);
      let scale = params.size ? Number(params.size) : 1;
      mesh.scale.set(scale, scale, scale);

      super(`yellowHouse-${yellowHouse++}`, mesh);
    }
  }

  let whiteHouse = 0;
  export class Grhouse2 extends GrObject {
    constructor(params = {}) {
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

        //top
        1,1,1, // e-6
        1,1,0, // e-7
        0,1,0, // e-8
        1,1,1, // e-6
        0,1,0, // e-8
        0,1,1, // e-5

        //bottom
        0,0,0, // f-1
        1,0,0, // f-2
        1,0,1, // f-3
        0,0,0, // f-1
        1,0,1, // f-3
        0,0,1, // f-4

        //ai-roof
        1,1,1, // i-6
        0.5,1.5,0.5, // i-9
        0,1,1, // i-5

        //bf-roof
        1,1,0, // f-7
        0.5,1.5,0.5, // f-9
        1,1,1, // f-6

        //cg-roof
        0,1,0, // g-8
        0.5,1.5,0.5, // g-9
        1,1,0, // g-7

        //dh-roof
        0,1,1, // h-5
        0.5,1.5,0.5, // h-9
        0,1,0, // h-8
      ]);
      geometry.setAttribute('position',new T.BufferAttribute(vertices,3));
      geometry.computeVertexNormals();
      // give it UVs
      // @@Snippet:texcoords
      const uvs = new Float32Array( [
        //c
        0.11,0, // c-1
        0,0.11, // c-8
        0,0, // c-2
        0.11,0, // c-8
        0.11,0.11, // c-7
        0,0.11, // c-2

        //b
        2/3,0, // c-1
        2/3,1, // c-8
        1/3,1, // c-2
        2/3,0, // c-8
        1/3,1, // c-7
        1/3,0, // c-2
        
        //a
        1/3,0, // a-3
        1/3,1, // a-6
        0,1, // a-5
        1/3,0, // a-3
        0,1, // a-5
        0,0, // a-4

        //d
        2/3,0, // c-1
        2/3,1, // c-8
        1/3,1, // c-2
        2/3,0, // c-8
        1/3,1, // c-7
        1/3,0, // c-2

        0.15,0, // c-1
        0,0.15, // c-8
        0,0, // c-2
        0.15,0, // c-8
        0.15,0.15, // c-7
        0,0.15, // c-2

        0.15,0, // c-1
        0,0.15, // c-8
        0,0, // c-2
        0.15,0, // c-8
        0.15,0.15, // c-7
        0,0.15, // c-2

        1,0, // c-1
        1,1, // c-8
        2/3,1, // c-2

        1,0, // c-1
        1,1, // c-8
        2/3,1, // c-2

        1,0, // c-1
        1,1, // c-8
        2/3,1, // c-2

        1,0, // c-1
        1,1, // c-8
        2/3,1, // c-2
      ]);
      geometry.setAttribute('uv',new T.BufferAttribute(uvs,2));
      //@@Snippet:end

      // @@Snippet:texuse
      let tl = new T.TextureLoader().load("./textures/brownDoor.jpg");
      let material = new T.MeshStandardMaterial({
        color: "white",
        roughness: 0.75,
        map: tl
      });

      let mesh = new T.Mesh(geometry, material);
      mesh.translateX(params.x || 0);
      mesh.translateY(params.y || 0);
      mesh.translateZ(params.z || 0);
      let scale = params.size ? Number(params.size) : 1;
      mesh.scale.set(scale, scale, scale);
      super(`whiteHouse-${whiteHouse++}`, mesh);
    }
  }

