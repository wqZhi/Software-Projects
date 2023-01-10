/*jshint esversion: 6 */
// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import {Grhouse1,Grhouse2} from "./Objects/buildings.js";
import {schoolBusGr} from "./Objects/schoolBus.js";
import {GrColoredRoundabout, GrCarousel, GrSpinningTeapot, GrAdvancedSwing} from "./Objects/parkObj.js";
import { GrCrane, GrExcavator, GrForkLift, GrForkLiftCrane } from "./Objects/constructionObj.js";
import { GrTower } from "./Objects/tower.js";
import { GrWindTurbine } from "./Objects/windTurbine.js";
import { GrLightBall } from "./Objects/lightBallobj.js";
import { GrRoadStraight, GrRoadCircular} from "./Objects/road.js";
import { GrTreeAdvance, GrTreeSimple} from "./Objects/tree.js";
import { GrDrone} from "./Objects/flights.js";
import { GrSnowmanHat, GrSnowman} from "./Objects/snowman.js";
import { GrSnow} from "./Objects/snow.js";
import { Helipad} from "./Objects/helipad.js";
import { Water} from "./Objects/water.js";


export function myMain(world) {
  // enviroment map
  // https://polyhaven.com/a/shanghai_bund
  let urls = [
    "./images/Right.png",//right
    "./images/Left.png", //left
    "./images/Top.png", //Top
    "./images/Bottom.png", //Bottom
    "./images/Front.png", //Front
    "./images/Back.png", //back
  ];  
  let skyMap = new T.CubeTextureLoader().load(urls);
  world.scene.background = skyMap;

  //water
  {
    const waterGeometry = new T.PlaneGeometry( 10000, 10000 );
    let water = new Water(
    waterGeometry,
    {
      waterNormals: new T.TextureLoader().load( './textures/waterNormalMap.jpg', function ( texture ) {
        texture.wrapS = texture.wrapT = T.RepeatWrapping;
      } ),
      distortionScale: 3.7
    }
  );

    water.rotation.x = - Math.PI / 2;
    water.position.y = -15;
    world.scene.add(water);

    animate();
    function animate(){
      requestAnimationFrame(animate);
      const time = performance.now() * 0.001;
      // @ts-ignore
      water.material.uniforms[ 'time' ].value += 1.0 / 60.0;
    }
  }

  //snow
  world.add(new GrSnow());
  
  //Drone
  {
    world.add(new GrDrone({x:5, y:15, size:2}));
    world.add(new Helipad(16, 0, 16));
    world.add(new Helipad(-11, 0, 1));
    world.add(new Helipad(-19.5, 0, -18));
    world.add(new Helipad(14.5, 0, -6));
  }



  //snowman
  world.add(new GrSnowmanHat({x:-12, y:1.1, size:0.7, z:-4}));
  world.add(new GrSnowman({x:5, z: 13, y:0.4, size:0.3, faceDir: -Math.PI/7}));
  world.add(new GrSnowman({x:-10, z: 18, y:0.4, size:0.3, faceDir: Math.PI/7}));


  //schoolBus
  world.add(new schoolBusGr({x:7.3, z:-13.4, size:0.8}));


  //ForkLift on the road
  world.add(new GrForkLift({  x: 10, z: 8, faceDir: Math.PI/1.5, size:0.8}));



  //top-Left
  {
    // house
    let x = -15;
    for(let i = 0; i<4; i++) {
      world.add(new Grhouse2({ x: x, z: -19, size: 3 }));
      world.add(new Grhouse2({ x: x, z: -11, size: 3 }));
      x += 5.5;
    }

    // tree
    x = -16;//simple
    for(let i = 0; i<13; i++) {
      world.add(new GrTreeSimple({x:x, z:-20.4, size:1}));
      x += 3;
    }

    x = -16.5;//advance
    for(let i = 0; i<4; i++) {
      world.add(new GrTreeAdvance({x:x, z:-16.6, size:0.6}));
      x += 5.5;
    }

    x = -11;//advance
    for(let i = 0; i<3; i++) {
      world.add(new GrTreeAdvance({x:x, z:-10.5, size:0.6}));
      x += 5.5;
    }
  }



  //top-Right
  {
    //village area
    world.add(new Grhouse1({ x: 11, z: -14, size: 2.7 }));
    world.add(new GrAdvancedSwing({x: 17, z: -9, faceDir: Math.PI/4, size:0.6}));
    world.add(new GrAdvancedSwing({x: 12, z: -9, faceDir: -Math.PI/4, size:0.6}));

    //wind Turbines
    world.add(new GrWindTurbine({x: 19, z: -18, faceDir: Math.PI/4, size: 1.4}));
    world.add(new GrWindTurbine({x: 15, z: -18, faceDir: Math.PI/4}));
    world.add(new GrWindTurbine({x: 19, z: -13, faceDir: Math.PI/4}));
    world.add(new GrWindTurbine({x: 10, z: -18, faceDir: Math.PI/4}));
    world.add(new GrWindTurbine({x: 19, z: -8, faceDir: Math.PI/4}));
  }



  //bottom-Left
  {
    //park
    world.add(new GrColoredRoundabout({ x: 2, z: 12.5, size: 0.8}));
    world.add(new GrColoredRoundabout({ x: 2, z: 17, size: 0.8}));
    world.add(new GrCarousel({x: -6.5, z: 15, size: 1}));
    world.add(new GrSpinningTeapot({x: -14, z: 15, size: 0.7}));

    // tree
    let z = 10;//simple
    for(let i = 0; i< 5; i++) {
      world.add(new GrTreeSimple({x:-20, z:z, size:1}));
      z += 2.5;
    }

    //
    world.add(new GrRoadStraight({startX:-19, startZ:14.3, endX:6.5, endZ:13.7, angle: Math.PI/2, width:11, colors:"#BC8F8F", height: 0.011}));
  }



  //bottom-Right
  {
    //construction obj
    world.add(new GrCrane({ x: 10, z: 0, faceDir: -Math.PI/4, size: 2 }));
    world.add(new GrExcavator({  x: 18, z: 8, faceDir: -Math.PI/4}));  
    
    world.add(new GrForkLiftCrane({  x: 18, z: 0, faceDir: -Math.PI/4}));


  }


  //tower
  {
    world.add(new GrTower(world));
    //created - shader - lightbulb
    world.add(new GrLightBall({y:16.7}));
  }



  //road
  {
    //top
    world.add(new GrRoadStraight({startX:-19, startZ:-13.7, endX:8, endZ:-14, angle: Math.PI/2, width:3}));
    world.add(new GrRoadStraight({startX:-17.5, startZ:-13.7, endX:6.5, endZ:-14, angle: Math.PI/2, width:0.1, colors:"yellow", height: 0.011}));
  
    //right
    world.add(new GrRoadStraight({startX:5, startZ:-14.65, endX:8, endZ:6, angle: Math.PI, width:3}));
    world.add(new GrRoadStraight({startX:5, startZ:-13.3, endX:8, endZ:6, angle: Math.PI, width:0.1, colors:"yellow", height: 0.011}));
  
    //bottom
    world.add(new GrRoadStraight({startX:-19, startZ:6, endX:8, endZ:6, angle: Math.PI/2, width:3}));
    world.add(new GrRoadStraight({startX:-17.5, startZ:6, endX:6.6, endZ:6, angle: Math.PI/2, width:0.1, colors:"yellow", height: 0.011}));

    //left
    world.add(new GrRoadStraight({startX:-17.5, startZ:-13, endX:-17.5, endZ:6, angle: Math.PI, width:3}));
    world.add(new GrRoadStraight({startX:-17.5, startZ:-13.5, endX:-17.5, endZ:6, angle: Math.PI, width:0.1, colors:"yellow", height: 0.011}));
      // tree
      let z = -12.8;//simple
      for(let i = 0; i<4; i++) {
        world.add(new GrTreeAdvance({x:-20, z:z, size:0.7}));
        z += 6;
      }
  }



}

