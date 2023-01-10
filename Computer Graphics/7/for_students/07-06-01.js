/*jshint esversion: 6 */
// @ts-check

/**
 * Minimal Starter Code for the QuadCopter assignment
 */

import * as T from "../libs/CS559-Three/build/three.module.js";
import { OrbitControls } from "../libs/CS559-Three/examples/jsm/controls/OrbitControls.js";


let renderer = new T.WebGLRenderer();
renderer.setSize(600, 400);
document.body.appendChild(renderer.domElement);

let scene = new T.Scene();
let camera = new T.PerspectiveCamera(
        40,
        renderer.domElement.width / renderer.domElement.height,
        1,
        1000
    );

camera.position.z = 10;
camera.position.y = 5;
camera.position.x = 5;
camera.lookAt(0, 0, 0);

// since we're animating, add OrbitControls
let controls = new OrbitControls(camera, renderer.domElement);

scene.add(new T.AmbientLight("white", 0.2));

// two lights - both a little off white to give some contrast
let dirLight1 = new T.DirectionalLight(0xf0e0d0, 1);
dirLight1.position.set(1, 1, 0);
scene.add(dirLight1);

let dirLight2 = new T.DirectionalLight(0xd0e0f0, 1);
dirLight2.position.set(-1, 1, -0.2);
scene.add(dirLight2);

// make a ground plane
let groundBox = new T.BoxGeometry(10, 0.1, 10);
let groundMesh = new T.Mesh(
        groundBox,
        new T.MeshStandardMaterial({ color: 0x88b888, roughness: 0.9 })
    );
// put the top of the box at the ground level (0)
groundMesh.position.y = -0.05;
scene.add(groundMesh);

// this is the part the student should change
//** GET RID OF THIS SILLY DONUT! Replace it with an aircraft*/
//1st flight
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
scene.add(drone);

//2nd flight
let helicopter = new T.Group();
helicopter.position.y = 1;
    //body
    let body2 = new T.SphereGeometry(0.5, 1000, 1000);
    let sphere = new T.Mesh( body2, new T.MeshStandardMaterial({ color: "red" , metalness:0.5 , roughness:0.5}));
    helicopter.add(sphere);
    scene.add(helicopter);
    //back-arm
    let backArm = new T.BoxGeometry(0.2, 0.2, 1);
    let arm5 = new T.Mesh(backArm, new T.MeshStandardMaterial({ color: "gray", metalness:0.1 }));
    arm5.position.set(0,-0.9,0);
    arm5.rotateX(Math.PI/2)
    helicopter.add(arm5);
    //propellers
    let heliPropeller = new T.BoxGeometry(0.1, 0.02, 1.5);
    let propeller5 = new T.Mesh(heliPropeller, new T.MeshStandardMaterial({ color: "orange", metalness:0.1 }));
    propeller5.position.set(0,0.5,0);
    helicopter.add(propeller5);   
    let propeller7 = new T.Mesh(heliPropeller, new T.MeshStandardMaterial({ color: "orange", metalness:0.1 }));
    propeller7.position.set(0,0.5,0);
    propeller7.rotateY(Math.PI/2);
    helicopter.add(propeller7); 

    let heliPropeller2 = new T.BoxGeometry(0.1, 0.1, 0.9);
    let propeller6 = new T.Mesh(heliPropeller2, new T.MeshStandardMaterial({ color: "green", metalness:0.1 }));
    let propeller8 = new T.Mesh(heliPropeller2, new T.MeshStandardMaterial({ color: "green", metalness:0.1 }));
    propeller6.position.set(0.1,-1.4,0);
    propeller8.position.set(-0.1,-1.4,0);
    helicopter.add(propeller6);
    helicopter.add(propeller8);
 scene.add(helicopter);

//radar
let radar = new T.Group();
radar.position.set(0,0,4.2);
    //body
    let body3 = new T.SphereGeometry(0.4, 1000, 1000);
    let sphere2 = new T.Mesh( body3, new T.MeshStandardMaterial({ color: "red" , metalness:0.5 , roughness:0.5}));
    radar.add(sphere2);
    //stick
    let radarArm = new T.BoxGeometry(0.09, 0.09, 1);
    let arm6 = new T.Mesh(radarArm, new T.MeshStandardMaterial({ color: "red", metalness:0.1 }));
    arm6.position.set(0, 0.1, 0.5);
    radar.add(arm6);

scene.add(radar);

// animation loop
function animateLoop(timestamp) {
    //** EXAMPLE CODE - STUDENT SHOULD REPLACE */
    // move in a circle
    let theta = timestamp / 1000;
    let x = 3 * Math.cos(theta);
    let z = 3 * Math.sin(theta);

    // Drone spin the propellers
    propeller1.rotateY(0.2);
    propeller2.rotateY(0.2);
    propeller3.rotateY(0.2);
    propeller4.rotateY(0.2);

    // //Helicopter spin the propellers
    propeller5.rotateY(0.2);
    propeller7.rotateY(0.2);
    propeller6.rotateX(0.3);
    propeller8.rotateX(0.1);

    // drone move
    drone.lookAt(Math.sin(theta) + Math.cos(theta), -1, Math.cos(theta) + (-Math.sin(theta)));
    drone.position.x = -x;
    drone.position.z = -z;

    // helicopter move
    helicopter.lookAt(x, 0, z);
    helicopter.position.x = x;
    helicopter.position.z = z;

    radar.lookAt(x, 1.5, z);

    renderer.render(scene, camera);
    window.requestAnimationFrame(animateLoop);
  }
window.requestAnimationFrame(animateLoop);