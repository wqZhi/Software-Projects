// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { OrbitControls } from "../libs/CS559-Three/examples/jsm/controls/OrbitControls.js";

let renderer = new T.WebGLRenderer();
renderer.setSize(400, 400);

let scene = new T.Scene();
let camera = new T.PerspectiveCamera();
camera.position.z = 10;
camera.position.y = 5;
camera.position.x = 5;
camera.lookAt(0, 3, 0);

// since we're animating, add OrbitControls
let controls = new OrbitControls(camera, renderer.domElement);

scene.add(new T.AmbientLight("white", 0.2));
let point = new T.PointLight("white", 1, 0, 0);
point.position.set(20, 10, 15);
scene.add(point);

let spot = new T.SpotLight("white", 1, 0, Math.PI / 15, 0); // ,0,Math.PI/9);
spot.position.set(0, 7, 0);
scene.add(spot);
let sh = new T.SpotLightHelper(spot);
//scene.add(sh);

// the directional light comes from the side
let dir = new T.DirectionalLight("yellow", 1);
dir.position.set(5, 0, 0);
scene.add(dir);

// make a ground plane
let groundBox = new T.BoxGeometry(6, 0.1, 6);
let groundMesh = new T.Mesh(
    groundBox,
    new T.MeshStandardMaterial({ color: 0x888888 })
);
// put the top of the box at the ground level (0)
groundMesh.position.y = -0.05;
scene.add(groundMesh);

/**
 * Make some cubes in variou2 places and orientations
 */

let box = new T.BoxGeometry(1, 1, 1);
let cube1 = new T.Mesh(box, new T.MeshStandardMaterial({ color: "purple" }));
cube1.rotateY(45);
cube1.position.set(2, 0.5, 0);
scene.add(cube1);

let cube2 = new T.Mesh(box, new T.MeshStandardMaterial({ color: "red" }));
cube2.rotateY(45);
cube2.translateX(2);
cube2.translateY(0.5);
scene.add(cube2);

let cube3 = new T.Mesh(box, new T.MeshStandardMaterial({ color: "white" }));
cube3.position.y = 0.5;
scene.add(cube3);

let cube4 = new T.Mesh(box, new T.MeshStandardMaterial({ color: "white" }));
cube4.position.set(-2, Math.sqrt(2) / 2, 2);
//cube4.lookAt(-2+10,10,2+10);
scene.add(cube4);

let cube5 = new T.Mesh(box, new T.MeshStandardMaterial({ color: "white" }));
cube5.position.set(-2, 1, -2);
scene.add(cube5);

document.getElementById("div1").appendChild(renderer.domElement);

let lastTimestamp; // undefined to start
  
function animate(timestamp) {
    // Convert time change from milliseconds to seconds
    let timeDelta = 0.001 * (lastTimestamp ? timestamp - lastTimestamp : 0);
    lastTimestamp = timestamp;

    // Slide the cube back and forth over the course of a second
    let t_x = (0.001 * timestamp) % 1.0;
    let x = 2 * Math.cos(Math.PI * 2 * t_x);

    cube3.position.z = x;
    cube5.rotateX(0.5 * timeDelta);
    cube5.rotateY(0.5 * timeDelta);
    renderer.render(scene, camera);
    
    window.requestAnimationFrame(animate);
}
window.requestAnimationFrame(animate);
