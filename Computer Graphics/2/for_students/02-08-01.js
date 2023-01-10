/**
 * Starter file for 02-08-01.js - the only exercise of page 8 of Workbook 2
 */
// @ts-check

// Find the canvas and start!
let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("box2canvas"));
let context = canvas.getContext('2d');
let fireworks = [];
let explodes = [];
let mouseX = -10;
let mouseY = -10;


canvas.onclick = function(event) {
    mouseX = event.clientX;
    mouseY = event.clientY;
    let box = /** @type {HTMLCanvasElement} */ (event.target).getBoundingClientRect();
    mouseX -= box.left;
    mouseY -= box.top;

    fireworks.push({"x": mouseX, "y": box.bottom, "r": 10,
					"startX":mouseX, "startY": box.bottom, 
					"endX":mouseX, "endY":mouseY,
					"color":ranColor()});
};


function ranColor() {
    return '#'+Math.floor(Math.random()*16777215).toString(16);
}

function drawFireWork() {
	fireworks.forEach(function(firework){
        context.beginPath();
        context.arc(firework.x, firework.y, firework.r, 0, 2 * Math.PI);
        context.closePath();
        context.fillStyle = firework.color;
        context.fill();
    });
}


function animate() {
    // clear the canvas
    context.clearRect(0,0,canvas.width,canvas.height);

    //shot firework
    fireworks.forEach(function(firework) {
        if ( firework.y > firework.endY) {
            firework.y = firework.y - (firework.startY - firework.endY) / 60; 
        }
    	
		//let firework disapper
		if (Math.abs(firework.y - firework.endY) < 0.001 ) {
            firework.x = -10;
            firework.y = -10;  
			
			//generate explodes after firework disapper
			for (var i = 0; i < 100; i++) { 
				explodes.push({"x":mouseX, "y":mouseY,
						   	   "vx":(Math.random()-0.5)*5, "vy":(Math.random()-0.5)*5,
						       "color": ranColor()});
			}   			   			   			   
        }
    });


    // move all the dots
    explodes.forEach(function(dot){
        dot.y -= dot.vy*1.5;
        dot.x -= dot.vx*1.5;
    });


	// remove dots that have gone off the screen
    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/filter
    explodes = explodes.filter(
        // this defines a function using "arrow notation"
        // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/Arrow_functions
        dot => ((dot.y>0)&&(dot.x>0)&&(dot.x<canvas.width)&&(dot.y<canvas.height))
        );

	// draw firework
    drawFireWork();

    // draw explodes
    explodes.forEach(function(dot){
        context.fillStyle = ranColor();
        context.fillRect(dot.x,dot.y,3,3);
    });


    window.requestAnimationFrame(animate);
}
animate();

