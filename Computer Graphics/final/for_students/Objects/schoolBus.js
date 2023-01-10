/*jshint esversion: 6 */
// @ts-check
import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";

// define your vehicles here - remember, they need to be imported
// into the "main" program
let schoolBusDoorSideTexture = new T.TextureLoader().load("./textures/schoolBus_Door.jpg");
let schoolBusNoDoorSideTexture = new T.TextureLoader().load("./textures/schoolBus_No_Door.jpg");
let schoolBusBackTexture = new T.TextureLoader().load("./textures/SB_Back.jpg");
let schoolBusFrontTexture = new T.TextureLoader().load("./textures/SBFront.jpg");

let schoolBusObj = 0;
export class schoolBusGr extends GrObject {
    constructor(params = {}) {
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
            metalness: 0.2
        });
        let head = new T.Mesh(geometry_head, mat_head);
        head.position.set(0,0.6,2.4);
        group.add(head);

        //beam
        let beam_geom = new T.ConeBufferGeometry();
        let beam_mat = new T.MeshPhongMaterial({ 
            color: "rgb(100%, 100%, 0%)", 
            transparent: true, 
            opacity: 0.2 
        });
        let beam1 = new T.Mesh(beam_geom, beam_mat);
        head.add(beam1);
        beam1.rotateX(-Math.PI/2);
        beam1.translateY(-1.7);
        beam1.translateX(-0.3);
        beam1.scale.set(0.6, 3, 0.6);
        let beam2 = new T.Mesh(beam_geom, beam_mat);
        head.add(beam2);
        beam2.rotateX(-Math.PI/2);
        beam2.translateY(-1.7);
        beam2.translateX(0.3);
        beam2.scale.set(0.6, 3, 0.6);
        

        //wheel
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



        super(`GrSchoolBus-${schoolBusObj++}`, group);
        this.whole_ob = group;
        this.time = 0;
        this.curve;

        this.rideable = this.objects[0];

        this.ridePoint = new T.Object3D();
        this.ridePoint.translateY(0.5);
        this.objects[0].add(this.ridePoint);
        this.rideable = this.ridePoint;

  
      // put the object in its place
      group.position.x = params.x ? Number(params.x) : 0;
      group.position.y = params.y ? Number(params.y) : 0;
      group.position.z = params.z ? Number(params.z) : 0;
      group.rotateY(params.faceDir ? params.faceDir : 0);

      let scale = params.size ? Number(params.size) : 1;
      group.scale.set(scale, scale, scale);
    }

    shift(grobj, x,y,z) {
        grobj.objects[0].translateX(x);
        grobj.objects[0].translateY(y);
        grobj.objects[0].translateZ(z);
        return grobj;
    }


    stepWorld(delta, timeOfDay) {
        this.curve = new T.CatmullRomCurve3( [
        new T.Vector3( 6.6, 0, -13.8),
        new T.Vector3( 6.6, 0, 6.3),
        new T.Vector3( -17.65, 0,  6.3),
        new T.Vector3( -17.65, 0,  -13.8),

       ], true, "catmullrom", 0.15);

    
        this.time += delta / 10000;
        let t = this.time - Math.floor(this.time);
        this.whole_ob.position.copy(this.curve.getPointAt(t));
        this.whole_ob.lookAt(this.curve.getPointAt(t).add(this.curve.getTangentAt(t)));
    }  


}