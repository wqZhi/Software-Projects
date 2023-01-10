/*jshint esversion: 6 */
// @ts-check

import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../libs/CS559-Framework/GrObject.js";
import {
    Grhouse1,
    Grhouse2,
    GrWoodRoot,
    GrWoodTop
  } from "./08-06-buildings.js";

// your buildings are defined in another file... you should import them
// here

let world = new GrWorld();

function shift(grobj, x,y,z) {
    grobj.objects[0].translateX(x);
    grobj.objects[0].translateY(y);
    grobj.objects[0].translateZ(z);
    return grobj;
  }

let house1 = shift(new Grhouse1(),-3,0,0);
world.add(house1);

let house2 = shift(new Grhouse2(),0,0,0);
world.add(house2);

let woodRoot = shift(new GrWoodRoot(),3.15,0,0.13);
world.add(woodRoot);
let woodTop = shift(new GrWoodTop(),3,-0.15,0);
world.add(woodTop);

let woodRoot2 = shift(new GrWoodRoot(),2,0,2);
world.add(woodRoot2);
let woodTop2 = shift(new GrWoodTop(), 1.82,-0.15,1.82);
world.add(woodTop2);


world.go();


