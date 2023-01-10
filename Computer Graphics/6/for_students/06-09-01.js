// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { OrbitControls } from "../libs/CS559-Three/examples/jsm/controls/OrbitControls.js";
import { setupBasicScene } from "./06-09-01-helpers.js";
import { OBJLoader } from "../libs/CS559-Three/examples/jsm/loaders/OBJLoader.js";

/** Setup the window */
/** @type{number} */
let wid = 670; // window.innerWidth;
/** @type{number} */
let ht = 500; // window.innerHeight;
/** @type{T.WebGLRenderer} */
let renderer = new T.WebGLRenderer();
renderer.setSize(wid, ht);
renderer.shadowMap.enabled = true;

document.getElementById("museum_area").appendChild(renderer.domElement);

/* setupBasicScene creates a scene and puts the pedestals in place */
/** @type{T.Scene} */
let scene = setupBasicScene();


/** create a "main camera" */
/** @type{T.PerspectiveCamera} */
let main_camera = new T.PerspectiveCamera(60, wid / ht, 1, 100);
main_camera.position.set(0, 4, 6);
main_camera.rotation.set(-0.5, 0, 0);

/** this will be the "current camera" - we will switch when a button is pressed */
let active_camera = main_camera;

// TODO: You need to place these cameras.
let camera_1 = new T.PerspectiveCamera(60, wid / ht, 1, 100);
camera_1.position.set(0, 2.35, 0);
camera_1.lookAt(2, 1.35, 2); 

let camera_2 = new T.PerspectiveCamera(60, wid / ht, 1, 100);
camera_2.position.set(0, 2.35, 0);
camera_2.lookAt(2, 1.35, -2); 

let camera_3 = new T.PerspectiveCamera(60, wid / ht, 1, 100);
camera_3.position.set(0, 2.35, 0);
camera_3.lookAt(-2, 1.35, 2);

let camera_4 = new T.PerspectiveCamera(60, wid / ht, 1, 100);
camera_4.position.set(0, 2.35, 0);
camera_4.lookAt(-2, 1.35, -2); 


// Here, we add a basic, simple first object to the museum.
/**@type{T.Material} */
let material = new T.MeshPhongMaterial({
  color: "#00aa00",
  shininess: 15,
  specular: "#00ff00",
});
/**@type{T.BufferGeometry} */
let geometry = new T.BoxGeometry(0.5, 0.5, 0.5);
/**@type{T.Mesh} */
let cube = new T.Mesh(geometry, material);
cube.position.set(2, 1.35, 2);
cube.rotation.set(Math.PI / 4, 0, Math.PI / 4);
cube.castShadow = true;
let cube2 = new T.Group();
cube2.position.set(-2, 2.2, 2);
let cube3 = new T.Group();
cube3.position.set(2, 1.6, -2);
let cube4 = new T.Group();
cube4.position.set(-2, 1.7, -2);

// TODO: You need to create three more objects, and place them on pedestals.
let loader = new OBJLoader();

let astro;
loader.load("./objects/07-astronaut.obj", function(object) {
       astro = object;
        object.position.set(-2, 2.2, 2);
        object.scale.set(0.2, 0.2, 0.2);
        scene.add(object);
        // note that we have to render
        renderer.render(scene, main_camera);
        object.children[0].castShadow = true;
        object.children[0].material = new T.MeshStandardMaterial({color: "white"})
    });

let teapot;
loader.load("./objects/07-teapot.obj", function(object) {
        teapot = object;
        object.position.set(2, 1.6, -2);
        object.scale.set(0.03, 0.03, 0.03);
        scene.add(object);
        // note that we have to render
        renderer.render(scene, main_camera);
        object.children[0].castShadow = true;
        object.children[0].material = new T.MeshStandardMaterial({color: "yellow"})
    });

let suzanne;
loader.load("./objects/07-suzanne.obj", function(object) {
        suzanne = object;
        object.position.set(-2, 1.7, -2);
        object.scale.set(0.06, 0.06, 0.06);
        scene.add(object);
        // note that we have to render
        renderer.render(scene, main_camera);
        object.children[0].castShadow = true;
        object.children[0].material = new T.MeshStandardMaterial({color: "pink"})
    });



/* put a spotlight on the first object */
/**@type{T.SpotLight} */
let spotlight_1 = new T.SpotLight(0xaaaaff, 0.5);
spotlight_1.angle = Math.PI / 16;
spotlight_1.position.set(2, 5, 2);
spotlight_1.target = cube;
spotlight_1.castShadow = true;
scene.add(spotlight_1);

// TODO: You need to place the lights.
let spotlight_2 = new T.SpotLight(0xaaaaff, 0.5);
spotlight_2.angle = Math.PI / 16;
spotlight_2.castShadow = true;
spotlight_2.position.set(-2, 5, 2);
spotlight_2.target = cube2;
spotlight_2.castShadow = true;
scene.add(spotlight_2);

let spotlight_3 = new T.SpotLight(0xaaaaff, 0.5);
spotlight_3.angle = Math.PI / 16;
spotlight_3.castShadow = true;
spotlight_3.position.set(2, 5, -2); 
spotlight_3.target = cube3;
spotlight_3.castShadow = true;
scene.add(spotlight_3);

let spotlight_4 = new T.SpotLight(0xaaaaff, 0.5);
spotlight_4.angle = Math.PI / 16;
spotlight_4.castShadow = true;
spotlight_4.position.set(-2, 5, -2);
spotlight_4.target = cube4;
spotlight_4.castShadow = true;
scene.add(spotlight_4);

scene.add(cube);
scene.add(cube2);
scene.add(cube3);
scene.add(cube4);


// add orbit controls - but only to the main camera
let controls = new OrbitControls(main_camera, renderer.domElement);

/** Tie the buttons to the cameras */
function setupCamButton(name, camera) {
  const button = document.getElementById(name);
  if (!(button instanceof HTMLButtonElement))
    throw new Error(`Button ${name} doesn't exist`);
  button.onclick = function () {
    active_camera = camera;
    renderer.render(scene, active_camera);
  };
}
setupCamButton("main_cam", main_camera);
setupCamButton("cam_1", camera_1);
setupCamButton("cam_2", camera_2);
setupCamButton("cam_3", camera_3);
setupCamButton("cam_4", camera_4);

// finally, draw the scene. Also, add animation.
renderer.render(scene, active_camera);

let lastTimestamp; // undefined to start
function animate(timestamp) {
    // Convert time change from milliseconds to seconds
    let timeDelta = 0.003 * (lastTimestamp ? timestamp - lastTimestamp : 0);
    lastTimestamp = timestamp;

    // Animate the cube (basic object)
    cube.rotateOnWorldAxis(new T.Vector3(0, 1, 0), timeDelta);

    if (astro){
      astro.rotateX(timeDelta/2);
      astro.rotateY(timeDelta/2);
    }

    let t_theta = 0.5 * ((0.001 * timestamp) % 2.0);
    let x = 0.3 * Math.sin(Math.PI * 2 * t_theta);
    let z = 0.3 * Math.cos(Math.PI * 2 * t_theta);
    if (suzanne) {
      suzanne.position.set(-2+x, 1.7, -2+z);
    }

    if (teapot) {
      teapot.rotateOnWorldAxis(new T.Vector3(0, 1, 0), timeDelta);
      teapot.rotateX(timeDelta*3);
    }

    controls.update(); 

    // draw and loop
    renderer.render(scene, active_camera);
    window.requestAnimationFrame(animate);
}
window.requestAnimationFrame(animate);
