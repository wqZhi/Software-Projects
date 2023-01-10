// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../libs/CS559-Framework/GrObject.js";
import * as InputHelpers from "../libs/CS559/inputHelpers.js";
import {GrSphere, GrCube, GrCylinder} from "../libs/CS559-Framework/SimpleObjects.js";

function spinY(obj, speed = 1) {
  obj.stepWorld = function(delta, timeOfDay) {
    obj.objects.forEach(obj => obj.rotateY(((speed * delta) / 1000) * Math.PI));
  };
  return obj;
}

function test() {
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

  const cubeRenderTarget = new T.WebGLCubeRenderTarget( 128, { generateMipmaps: true, minFilter: T.LinearMipmapLinearFilter } );
  let cubecam = new T.CubeCamera(1,1000, cubeRenderTarget);
  cubecam.position.y = 2;

  
  let sphereObj = 1;
  class GrSphereGlass extends GrObject {
      constructor() {
          let geometry = new T.SphereBufferGeometry(1, 20, 20);
          let t = new T.MeshStandardMaterial({ 
              roughness: 0,
              envMap: cubeRenderTarget.texture,
              metalness: 1
          });
          let sphere = new T.Mesh(geometry, t);
        super(`GrSphere ${sphereObj++}`, sphere);
      }
    }

  function shift(grobj, x, y) {
      grobj.objects[0].translateX(x);
      grobj.objects[0].translateY(y);
      return grobj;
  }
  
  //glass ball
  let sphere= shift(new GrSphereGlass(), 0, 3);
  world.add(sphere);

  //move obj
  let gr = new T.Group();
  let mat = new T.MeshStandardMaterial({ color: "red" });
  let geom = new T.TorusBufferGeometry();
  let tmesh = new T.Mesh(geom, mat);
  tmesh.rotateX(Math.PI / 2);
  tmesh.scale.set(0.5, 0.5, 0.25);
  tmesh.translateX(-2);
  gr.add(tmesh);
  gr.translateY(1);
  let highobj = new GrObject("high obj", gr);
  spinY(highobj);
  world.add(highobj);

  //other obj
  world.add(new GrCube({x:-4, z:-4, y:2.5, color: "red"}));
  world.add(new GrSphere({x:-4, z: 4, y:2.5, color: "purple"}));
  world.add(new GrCylinder({x: 4, z: 4, y:2.5, radius: .4, height:5, color: "yellow"}));
  world.add(new GrCylinder({x:-4, z: 0, y:2.5, color: "orange"}));
  world.add(new GrCube({x: 4, z: 0, y:2.5, color: "blue"}));
  world.add(new GrCylinder({x: 4, z:-4, y:2.5, radius: .4, height:5}));

  world.go({
    predraw: function() {
      cubecam.update(world.renderer, world.scene );
      world.renderer.render( world.scene, world.camera );
    }
  });

}

test();

