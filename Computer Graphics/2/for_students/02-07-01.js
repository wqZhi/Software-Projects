/**
 * Starter file for 02-07-01.js - the only exercise of page 7 of Workbook 2
 */
// @ts-check

// Find the canvas and start!
// @ts-check
export {};

// we'll keep track of a set of "circles"
let circles = [];

let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("box1canvas"));
let context = canvas.getContext('2d');

//draw default green circle when clicked
canvas.onmousedown = function(event) {
    let x = event.clientX;
    let y = event.clientY;
    let box = /** @type {HTMLCanvasElement} */(event.target).getBoundingClientRect();
    x -= box.left;
    y -= box.top;
    circles.push({"x":x, "y":y});

    context.beginPath();
    context.arc(x, y, 20, 0, Math.PI*2);
    context.closePath();
    context.fillStyle = "#00FF00";
    context.fill();
    context.strokeStyle = 'black';
    context.stroke();
};

//detect current mouse location
canvas.onmousemove = function(event) {
    let mouseX = event.clientX;
    let mouseY = event.clientY;
    let box = /** @type {HTMLCanvasElement} */ (event.target).getBoundingClientRect();
    mouseX -= box.left;
    mouseY -= box.top;

    changeDrewCirclesColor(context, circles, mouseX, mouseY);
};

function changeDrewCirclesColor(context, circlesList, mx, my) {
    circlesList.forEach(function(cir) {
        var dis = Math.sqrt((mx-cir.x)*(mx-cir.x) + (my-cir.y)*(my-cir.y));

        if (dis <= 20){
            //change color
            context.beginPath();
            context.arc(cir.x, cir.y, 20, 0, Math.PI*2);
            context.closePath();
            context.fillStyle = "gray";
            context.fill();
            context.strokeStyle = 'black';
            context.stroke();
        }
        else{
            context.beginPath();
            context.arc(cir.x, cir.y, 20, 0, Math.PI*2);
            context.closePath();
            context.fillStyle = "#00FF00";
            context.fill();
            context.strokeStyle = 'black';
            context.stroke();
        }
    });
}

