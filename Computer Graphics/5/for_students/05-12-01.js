// @ts-check
/* jshint -W069, esversion:6 */

/**
 * drawing function for box 1
 *
 * draw something.
 **/

// note that checking that canvas is the right type of element tells typescript
// that this is the right type - it's a form of a safe cast 
let canvas = document.getElementById("canvas1");
if (!(canvas instanceof HTMLCanvasElement))
    throw new Error("Canvas is not HTML Element");

let context = canvas.getContext("2d");

context.beginPath();

// change these so that rather than connecting with straight lines,
// they use cardinal interpolation
// your points should cycle - to make a loop

context.moveTo(150,150);     // you don't need to change this line
context.lineTo(150,450);    // this line gets replaced by a bezierCurveTo
context.lineTo(450,450);     // this line gets replaced by a bezierCurveTo
context.lineTo(450,150);    // this line gets replaced by a bezierCurveTo
context.closePath();
context.lineWidth = 3;
context.stroke();

context.beginPath();
context.moveTo(150, 150);
context.bezierCurveTo(150+(150-150)/6, 150+(450-150)/6, 150+(150-450)/6, 450+(150-450)/6, 150, 450);
context.bezierCurveTo(150+(450-150)/6, 450+(450-150)/6, 450+(150-450)/6, 450+(150-450)/6, 450, 450);
context.bezierCurveTo(450+(450-150)/6, 450+(150-450)/6, 450+(450-150)/6, 150+(450-150)/6, 450, 150);
context.strokeStyle = "red";
context.closePath();
context.lineWidth = 3;
context.stroke();

// context.beginPath();
// context.moveTo(150,150);
// context.bezierCurveTo(150+(350-50)/6, 150+(150-50)/6, 350+(50-350)/6, 150+(150-50)/6, 350, 150);
// context.bezierCurveTo(350+(350-50)/6, 150+(50-150)/6, 350+(350-200)/6, 50+(150-100)/6, 350, 50);
// context.bezierCurveTo(350+(200-350)/6, 50+(100-150)/6, 200+(350-50)/6, 100+(50-50)/6, 200, 100);
// context.bezierCurveTo(200+(50-350)/6, 100+(50-50)/6, 50+(200-50)/6, 50+(100-150)/6, 50, 50);
// context.bezierCurveTo(50+(50-200)/6, 50+(150-100)/6, 50+(50-350)/6, 150+(50-150)/6, 50, 150);
// context.strokeStyle = "red";
// context.closePath();
// context.lineWidth = 3;
// context.stroke();

// context.beginPath();

// // change these so that rather than connecting with straight lines,
// // they use cardinal interpolation
// // your points should cycle - to make a loop

// context.moveTo(50,150);     // you don't need to change this line

// context.lineTo(350,150);    // this line gets replaced by a bezierCurveTo
// context.lineTo(350,50);     // this line gets replaced by a bezierCurveTo
// context.lineTo(200,100);    // this line gets replaced by a bezierCurveTo
// context.lineTo(50,50);      // this line gets replaced by a bezierCurveTo
// context.lineTo(50,150);     // this line gets replaced by a bezierCurveTo
// context.closePath();
// context.lineWidth = 3;
// context.stroke();

// context.beginPath();
// context.moveTo(50,150);
// context.bezierCurveTo(50+(350-50)/6, 150+(150-50)/6, 350+(50-350)/6, 150+(150-50)/6, 350, 150);
// context.bezierCurveTo(350+(350-50)/6, 150+(50-150)/6, 350+(350-200)/6, 50+(150-100)/6, 350, 50);
// context.bezierCurveTo(350+(200-350)/6, 50+(100-150)/6, 200+(350-50)/6, 100+(50-50)/6, 200, 100);
// context.bezierCurveTo(200+(50-350)/6, 100+(50-50)/6, 50+(200-50)/6, 50+(100-150)/6, 50, 50);
// context.bezierCurveTo(50+(50-200)/6, 50+(150-100)/6, 50+(50-350)/6, 150+(50-150)/6, 50, 150);
// context.strokeStyle = "red";
// context.closePath();
// context.lineWidth = 3;
// context.stroke();