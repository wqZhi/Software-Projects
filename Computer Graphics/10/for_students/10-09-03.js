/*jshint esversion: 6 */
// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../libs/CS559-Framework/GrObject.js";
import * as InputHelpers from "../libs/CS559/inputHelpers.js";
import * as SimpleObjects from "../libs/CS559-Framework/SimpleObjects.js";
import { shaderMaterial } from "../libs/CS559-Framework/shaderHelper.js";

/**
 *
 * @param {GrObject} obj
 * @param {number} [speed=1] - rotations per second
 */
 function spinY(obj, speed = 1) {
  obj.stepWorld = function (delta, timeOfDay) {
    obj.objects.forEach((obj) =>
      obj.rotateY(((speed * delta) / 1000) * Math.PI)
    );
  };
  return obj;
}


{
  let mydiv = document.getElementById("div1");

  let world = new GrWorld({ width: mydiv ? 600 : 800, where: mydiv });

  let shaderMat = shaderMaterial("./shaders/10-09-03.vs", "./shaders/10-09-03.fs", {
    side: T.DoubleSide,
    uniforms: {
      color: { value: new T.Vector3(0.4, 0.5, 0.5) },
      time: { value: 0 },
      checks: { value: 9.0 },
      light: { value: new T.Vector3(1, 1, 1) },
      dark: { value: new T.Vector3(0.2, 0.2, 0.7) },
    },
  });

  let sphere = new SimpleObjects.GrSphere({  material: shaderMat, x: -2, y: 1 });
  let square = new SimpleObjects.GrSquareSign({ x: 2, y: 1, size: 1, material: shaderMat });

  let cubeTime = 0;
  square.stepWorld = function (delta, timeofday) {
    cubeTime += delta;
    let newR = Math.sin(cubeTime / 200) / 2 + 0.5; // get a number between 0-1
    shaderMat.uniforms.color.value.x = newR;
    shaderMat.uniforms.time.value = cubeTime * 0.001; // pass in the time in seconds
  };

  // add a time accumulator to those objects...
  // sphere.time = 0;

  // beware! non-lexical this (it will become a method when we attach it to an object)
  // function spinner(delta, tod) {
  //   this.time += delta;
  //   this.objects[0].position.x = 2 * Math.sin(this.time / 1000);
  //   this.objects[0].position.z = 2 * Math.cos(this.time / 1000);
  // }
  // sphere.stepWorld = spinner;

  world.add(
    spinY(sphere)
    );
  // world.add(sphere);
  world.add(square);

  world.go();
}
