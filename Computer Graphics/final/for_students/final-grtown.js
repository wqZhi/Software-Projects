/*jshint esversion: 6 */
// @ts-check

/**
 * Graphics Town Framework - "Main" File
 *
 * This is the main file - it creates the world, populates it with
 * objects and behaviors, and starts things running
 *
 * The initial distributed version has a pretty empty world.
 * There are a few simple objects thrown in as examples.
 *
 * It is the students job to extend this by defining new object types
 * (in other files), then loading those files as modules, and using this
 * file to instantiate those objects in the world.
 */

import { GrWorld } from "../libs/CS559-Framework/GrWorld.js";
import { WorldUI } from "../libs/CS559-Framework/WorldUI.js";
// import {main} from "../examples/main.js";
import {myMain} from "./myMain.js";

/**m
 * The Graphics Town Main -
 * This builds up the world and makes it go...
 */

// make the world
let world = new GrWorld({
    width: 800,
    height: 600,
    groundplanesize: 21, // make the ground plane big enough for a world of stuff
    groundplanecolor: "#808080"
    
});

// put stuff into the world
// this calls the example code (that puts a lot of objects into the world)
// you can look at it for reference, but do not use it in your assignment
// main(world);
myMain(world);

// while making your objects, be sure to identify some of them as "highlighted"

///////////////////////////////////////////////////////////////
// because I did not store the objects I want to highlight in variables, I need to look them up by name
// This code is included since it might be useful if you want to highlight your objects here
function highlight(obName) {
    const toHighlight = world.objects.find(ob => ob.name === obName);
    if (toHighlight) {
        toHighlight.highlighted = true;
    } else {
        throw `no object named ${obName} for highlighting!`;
    }
}
// of course, the student should highlight their own objects, not these
highlight("Carousel-0");
highlight("Crane-0");
highlight("Excavator-0");
highlight("ForkLift-0");
highlight("ForkLiftCrane-0");
highlight("GrDrone-0");
highlight("GrSchoolBus-0");
highlight("ShinyTower");
highlight("SpinningTeapot-0");
for (let i=0; i< 9; i++){
    highlight(`road-${i}`);
}
highlight("shinyBall-0");
highlight("snow");
highlight("snowman-0");
highlight("snowman-1");
highlight("snowmanHat-0");
for (let i=0; i< 10; i++){
    highlight(`treeAdvance-${i}`);
}
for (let i=0; i< 18; i++){
    highlight(`treeSimple-${i}`);
}
for (let i=0; i< 8; i++){
    highlight(`whiteHouse-${i}`);
}
highlight(`yellowHouse-0`);

for (let i=0; i< 5; i++){
    highlight(`windTurbine-${i}`);
}

///////////////////////////////////////////////////////////////
// build and run the UI
// only after all the objects exist can we build the UI
// @ts-ignore       // we're sticking a new thing into the world
world.ui = new WorldUI(world);
// now make it go!
world.go();
