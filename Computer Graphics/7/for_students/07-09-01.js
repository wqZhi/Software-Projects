/*jshint esversion: 6 */
// @ts-check

// get things we need
import * as T from "../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { AutoUI } from "../libs/CS559-Framework/AutoUI.js";
import { GrCrane, GrExcavator, GrForkLift, GrForkLiftCrane } from "./07-09-constructionobjects.js";

let cDiv = document.getElementById("construction");
let world = new GrWorld({ groundplanesize: 10, where: cDiv });

let crane = new GrCrane({ x: 2, z: -2 });
world.add(crane);
let c_ui = new AutoUI(crane, 300, cDiv, 1, true);

let excavator = new GrExcavator({ x: -2, z: 2 });
world.add(excavator);
let e_ui = new AutoUI(excavator, 300, cDiv, 1, true);
e_ui.set("x", 6);
e_ui.set("z", 3);
e_ui.set("theta", 36);

let forkLift = new GrForkLift();
world.add(forkLift);
let F_l = new AutoUI(forkLift, 300, cDiv, 1, true);
F_l.set("x", 1);
F_l.set("z", 7);
F_l.set("theta", 36);

let forkLiftCrane = new GrForkLiftCrane();
world.add(forkLiftCrane);
let F_l_C = new AutoUI(forkLiftCrane, 300, cDiv, 1, true);
F_l_C.set("x", -3);
F_l_C.set("z", 6);
F_l_C.set("theta", 36);

world.go();
