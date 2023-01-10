/*jshint esversion: 6 */
// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../libs/CS559-Framework/GrObject.js";


// define your vehicles here - remember, they need to be imported
// into the "main" program
let schoolBusDoorSideTexture = new T.TextureLoader().load("../textures/schoolBus_Door.jpg");
let schoolBusNoDoorSideTexture = new T.TextureLoader().load("../textures/schoolBus_No_Door.jpg");
let schoolBusBackTexture = new T.TextureLoader().load("../textures/SB_Back.jpg");
let schoolBusFrontTexture = new T.TextureLoader().load("../textures/SBFront.jpg");

let carcount = 1;
export class GrCar extends GrObject {
    constructor() {
        let group = new T.Group();
        let geometry_body = new T.BoxGeometry(1, 1.5, 4);
        let mat_body = new T.MeshStandardMaterial({ 
            color: "yellow" 
        });
        let texture_body_Door = new T.MeshStandardMaterial({ 
            map : schoolBusDoorSideTexture
        });
        let texture_body_Non_Door = new T.MeshStandardMaterial({ 
            map : schoolBusNoDoorSideTexture
        });
        let texture_body_Back = new T.MeshStandardMaterial({ 
            map : schoolBusBackTexture
        });
        let texture_body_Front = new T.MeshStandardMaterial({ 
            map : schoolBusFrontTexture
        });
        const materials_body = [ texture_body_Non_Door, texture_body_Door, mat_body ,
            mat_body, texture_body_Front,texture_body_Back];
        let body = new T.Mesh(geometry_body, materials_body);
        body.position.set(0,1,0);
        group.add(body);

        let geometry_head = new T.BoxGeometry(0.8, 0.7, 0.8);
        let mat_head = new T.MeshStandardMaterial({ 
            color: "#DAA520",
            metalness: 0.5
        });
        let head = new T.Mesh(geometry_head, mat_head);
        head.position.set(0,0.6,2.4);
        group.add(head);

        let geometry_wheel = new T.CylinderBufferGeometry(.3,.3,.3,1000);
        geometry_wheel.rotateZ(Math.PI/2);
        let mat_wheel = new T.MeshStandardMaterial({
            color: "gray",
            metalness: 0.9,
            roughness: 0.7
        });
        let wheel1 = new T.Mesh( geometry_wheel, mat_wheel);
        let wheel2 = new T.Mesh( geometry_wheel, mat_wheel);
        let wheel3 = new T.Mesh( geometry_wheel, mat_wheel);
        let wheel4 = new T.Mesh( geometry_wheel, mat_wheel);
        wheel1.position.set(0.4,0.3,2.4);
        wheel2.position.set(-0.4,0.3,2.4);
        wheel3.position.set(0.4,0.3,-0.5);
        wheel4.position.set(-0.4,0.3,-0.5);
        group.add(wheel1);
        group.add(wheel2);
        group.add(wheel3);
        group.add(wheel4);

    //   // give it UVs
    //   // @@Snippet:texcoords
    //   const uvs = new Float32Array( [
    //     //
    //   ]);
    //   geometry.setAttribute('uv',new T.BufferAttribute(uvs,2));
    //   //@@Snippet:end

    //   // @@Snippet:texuse
    //   let tl = new T.TextureLoader().load("../textures/brownDoor.jpg");
    //   let material = new T.MeshStandardMaterial({
    //     color: "white",
    //     roughness: 0.75,
    //     map: tl
    //   });

    //   let mesh = new T.Mesh(geometry, material);




      super(`GrCar ${carcount++}`, group);
    }
  }