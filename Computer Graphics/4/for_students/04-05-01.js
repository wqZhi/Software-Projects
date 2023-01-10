/**
 * 04-05-01.js - a simple JavaScript file that gets loaded with
 * page 5 of Workbook 4 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020, February 2021
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

/**
 * If you want to read up on JavaScript classes, 
 * see the tutorial on the class website:
 * 
 * https://graphics.cs.wisc.edu/Courses/559-sp2021/tutorials/oop-in-js-1/
 */
class Boid {
    /**
     * 
     * @param {number} x    - initial X position
     * @param {number} y    - initial Y position
     * @param {number} vx   - initial X velocity
     * @param {number} vy   - initial Y velocity
     */
    constructor(x, y, vx = 1, vy = 0) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.color = "black";
        this.hitSec = 0;
        this.hit = 0;
    }

    /**
     * Draw the Boid
     * @param {CanvasRenderingContext2D} context 
     */
    draw(context) {
        context.save();
        context.translate(this.x, this.y);
        context.rotate(Math.atan2(this.vy, this.vx) + Math.PI/2);
        context.fillStyle = this.color;
        context.beginPath();
        context.moveTo(5, 5);
        context.lineTo(2, 25);
        context.lineTo(8, 25);
        context.closePath();
        context.fill();
        context.restore();
    }

    /**
     * Perform the "steering" behavior -
     * This function should update the velocity based on the other
     * members of the flock.
     * It is passed the entire flock (an array of Boids) - that includes
     * "this"!
     * Note: dealing with the boundaries does not need to be handled here
     * (in fact it can't be, since there is no awareness of the canvas)
     * *
     * And remember, (vx,vy) should always be a unit vector!
     * @param {Array<Boid>} flock 
     */
    steer(flock) {
        /*
		// Note - this sample behavior is just to help you understand
		// what a steering function might  do
		// all this one does is have things go in circles, rather than
		// straight lines
		// Something this simple would not count for the advanced points:
		// a "real" steering behavior must consider other boids,
		// or at least obstacles.
		
        // a simple steering behavior: 
        // create a rotation matrix that turns by a small amount
        // 2 degrees per time step
        const angle = 2 * Math.PI / 180;
        const s = Math.sin(angle);
        const c = Math.cos(angle);

        let ovx = this.vx;
        let ovy = this.vy;

        this.vx =  ovx * c + ovy * s;
        this.vy = -ovx * s + ovy * c;
		*/

        // const angle = 2 * Math.PI / 180;
        // const s = Math.sin(angle);
        // const c = Math.cos(angle);

        // let ovx = this.vx;
        // let ovy = this.vy;

        // this.vx =  ovx * c + ovy * s;
        // this.vy = -ovx * s + ovy * c;

        var currX = this.x;
        var currY = this.y;

        flock.forEach(function (boid){
            if ((currX == boid.x) && (currY == boid.y)) {
                return;
            }
            else{
                var dis = Math.sqrt((boid.x- currX)**2 + (boid.y-currY)**2);
                if (dis <= 12) {
                    boid.hit = 1;
                    boid.color = "red";
                    boid.vx = -boid.vx;
                    boid.vy = -boid.vy;
                }
            }

        });


    }
}


/** the actual main program
 * this used to be inside of a function definition that window.onload
 * was set to - however, now we use defer for loading
 */

 /** @type Array<Boid> */
let theBoids = [];

let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("flock"));
let context = canvas.getContext("2d");

let speedSlider = /** @type {HTMLInputElement} */ (document.getElementById("speed"));

function draw() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    theBoids.forEach(boid => boid.draw(context));
}


/**
 * Create some initial boids
 * STUDENT: may want to replace this
 */
theBoids.push(new Boid(100, 100));
theBoids.push(new Boid(200, 200, -1, 0));
theBoids.push(new Boid(300, 300, 0, -1));
theBoids.push(new Boid(400, 400, 0, 1));

/**
 * Handle the buttons
 */
document.getElementById("add").onclick = function () {
    // Students Fill This In
    for (var i = 0; i < 10; i++) {
        let xPos = Math.floor(Math.random()*601);
        let yPos = Math.floor(Math.random()*601);

        let xVelo = Math.floor(Math.random() * 21 - 10) / 10;
        let yVelo = Math.floor(Math.random() * 21 - 10) / 10;

        while (xVelo == 0 && yVelo == 0) {
            xVelo = Math.floor(Math.random() * 21 - 10) / 10; 
            yVelo = Math.floor(Math.random() * 21 - 10) / 10;
        }

        theBoids.push(new Boid(xPos, yPos, xVelo, yVelo));
    }
};

document.getElementById("clear").onclick = function () {
    // Student Fill This In
    theBoids = [];
};

let lastTime; // will be undefined by default
/**
 * The Actual Execution
 */
function loop(timestamp) {
    // time step - convert to 1/60th of a second frames
    // 1000ms / 60fps
    const delta = (lastTime ? timestamp-lastTime : 0) * 1000.0/60.0;

    // change directions
    theBoids.forEach(boid => boid.steer(theBoids));

    // move forward
    let speed = Number(speedSlider.value);
    theBoids.forEach(function (boid) {
        boid.x += boid.vx * speed;
        boid.y += boid.vy * speed;
    });

    // make sure that we stay on the screen
    theBoids.forEach(function (boid) {
        /**
         * Students should replace this with collision code
         */
        //bounce when hit the wall
        if (boid.y <= 0 ){
            boid.vy = -boid.vy;
            boid.color = "red";
            boid.hit = 1;
        }
        if (boid.y >= 600){
            boid.vy = -boid.vy;
            boid.color = "red";
            boid.hit = 1;
        }
            
        if (boid.x <= 0){
            boid.vx = -boid.vx;
            boid.color = "red";
            boid.hit = 1;
        }
        if (boid.x >= 600){
            boid.vx = -boid.vx;
            boid.color = "red";
            boid.hit = 1;
        }


    });

    theBoids.forEach(function (boid) {
        if (boid.hit == 1) boid.hitSec += 0.1;

        if (boid.hitSec >3) {
            boid.color = "black";
            boid.hit = 0;
            boid.hitSec = 0;
        }
    });


    // now we can draw
    draw();
    // and loop
    window.requestAnimationFrame(loop);

}
// start the loop with the first iteration
window.requestAnimationFrame(loop);


