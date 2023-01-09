//wb 07-06-01.js

import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";

let droneObj = 0;
export class GrDrone extends GrObject {
    constructor(params = {}) {
        let drone = new T.Group();
        drone.position.y = 3;
            //flight body
            let geometry = new T.BoxGeometry(2, 1, 1);
            let body = new T.Mesh(geometry, new T.MeshStandardMaterial({ color: "green", metalness:0.1, roughness:0 }));
            // scene.add(cube);
            body.scale.set(0.5, 0.5, 0.5);
            drone.add(body);
        
            //4-arms
            let arm = new T.BoxGeometry(0.1, 0.1, 0.8);
            let arm1 = new T.Mesh(arm, new T.MeshStandardMaterial({ color: "yellow", metalness:0.1 }));
            arm1.position.set(0.6,0,0.3);
            arm1.rotateY(45);
            drone.add(arm1);
            let arm2 = new T.Mesh(arm, new T.MeshStandardMaterial({ color: "yellow", metalness:0.1 }));
            arm2.position.set(-0.6,0,-0.3);
            arm2.rotateY(45);
            drone.add(arm2);
            let arm3 = new T.Mesh(arm, new T.MeshStandardMaterial({ color: "yellow", metalness:0.1 }));
            arm3.position.set(0.6,0,-0.3);
            arm3.rotateY(90);
            drone.add(arm3);
            let arm4 = new T.Mesh(arm, new T.MeshStandardMaterial({ color: "yellow", metalness:0.1 }));
            arm4.position.set(-0.6,0,0.3);
            arm4.rotateY(90);
            drone.add(arm4);
        
            //propellers
            let propeller = new T.BoxGeometry(0.05, 0.05, 0.6);
            let propeller1 = new T.Mesh(propeller, new T.MeshStandardMaterial({ color: "blue", metalness:0.1 }));
            propeller1.position.set(0.93,0,0.5);
            drone.add(propeller1);
            let propeller2 = new T.Mesh(propeller, new T.MeshStandardMaterial({ color: "blue", metalness:0.1 }));
            propeller2.position.set(-0.93,0,-0.5);
            drone.add(propeller2);
            let propeller3 = new T.Mesh(propeller, new T.MeshStandardMaterial({ color: "blue", metalness:0.1 }));
            propeller3.position.set(0.93,0,-0.5);
            drone.add(propeller3);
            let propeller4 = new T.Mesh(propeller, new T.MeshStandardMaterial({ color: "blue", metalness:0.1 }));
            propeller4.position.set(-0.93,0,0.5);
            drone.add(propeller4);

            super(`GrDrone-${droneObj++}`, drone);
            this.whole_ob = drone;
            this.propeller1 = propeller1;
            this.propeller2 = propeller2;
            this.propeller3 = propeller3;
            this.propeller4 = propeller4;
            this.theta = 0;
            this.curve;
            this.time=0;

            //follow cammer
            this.rideable = this.objects[0];
            this.ridePoint = new T.Object3D();
            this.ridePoint.rotateY(Math.PI/2);
            this.ridePoint.translateY(0.5);
            this.objects[0].add(this.ridePoint);
            this.rideable = this.ridePoint;

            // put the object in its place
            this.whole_ob.position.x = params.x ? Number(params.x) : 0;
            this.whole_ob.position.y = params.y ? Number(params.y) : 0;
            this.whole_ob.position.z = params.z ? Number(params.z) : 0;
            this.whole_ob.rotateY(params.faceDir ? params.faceDir : 0);
      
            let scale = params.size ? Number(params.size) : 1;
            this.whole_ob.scale.set(scale, scale, scale);
    }

    stepWorld(delta, timeofday) {
        this.theta += 0.0009 * delta;
        let x = 20 * Math.cos(this.theta);
        let z = 20 * Math.sin(this.theta);

        this.propeller1.rotateY(0.1 * delta* Math.random());
        this.propeller2.rotateY(0.15 * delta* Math.random());
        this.propeller3.rotateY(0.2 * delta* Math.random());
        this.propeller4.rotateY(0.3 * delta* Math.random());

        this.curve = new T.CatmullRomCurve3( [
            new T.Vector3( 16, 0.5, 16),
            new T.Vector3( 16, 17, 16),
            new T.Vector3( -17.65, 14,  6.3),
            new T.Vector3( -11, 0.5,  1),
            new T.Vector3( -11, 17,  1),
            new T.Vector3( -20, 14, -3),

            //3rd
            new T.Vector3( -19.5, 17, -18),
            new T.Vector3( -19.5, 0.5, -18),
            new T.Vector3( -19.5, 17, -18),

            //3rd to 1st
            new T.Vector3( 16, 19, 16),
            new T.Vector3( 16, 0.5, 16),
            new T.Vector3( 16, 19, 16),

            new T.Vector3( 14.5, 16, -6),
            new T.Vector3( 14.5, 0.5, -6),
            new T.Vector3( 14.5, 19, -6),

            new T.Vector3( -11, 17,  1),
            new T.Vector3( -11, 0.5,  1),
            new T.Vector3( -11, 17,  1),

            new T.Vector3( -19.5, 17, -18),
            new T.Vector3( -19.5, 0.5, -18),
            new T.Vector3( -19.5, 17, -18),
            new T.Vector3( 16, 19, 16),

            
    
        ], true, "catmullrom", 1);
    
        
        this.time += delta/29999;
        let t = this.time - Math.floor(this.time);
        this.whole_ob.position.copy(this.curve.getPointAt(t));
    }

}