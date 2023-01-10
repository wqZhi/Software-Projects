/*jshint esversion: 6 */
// @ts-check

// get things we need
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import {
  GrSimpleSwing,
  GrColoredRoundabout,
  GrSimpleRoundabout,
  GrCarousel,
  GrSpinningTeapot
} from "./07-08-parkobjects.js";
// import { SimpleBouncer } from "./07-08-simplepark.js";

let parkDiv = document.getElementById("div1");
let world = new GrWorld({ groundplanesize: 20, where: parkDiv });

// world.add(new SimpleBouncer(0, 5));

let roundabout = new GrSimpleRoundabout({ x: -2 });
world.add(roundabout);

let roundabout_2 = new GrColoredRoundabout({ x: 5 });
world.add(roundabout_2);

let swing_2 = new GrSimpleSwing({ x: 10 });
world.add(swing_2);

let swing_3 = new GrSimpleSwing({ x: 15});
world.add(swing_3);

let carousel_1= new GrCarousel({x:15,z:13});
world.add(carousel_1);

let spingingTeapot = new GrSpinningTeapot({x:5, z:13});
world.add(spingingTeapot);

world.go();
