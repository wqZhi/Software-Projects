// JavaScript file to be filled in by the student for Box 4-1
// we'll give you something to get started...
// you should start by getting the canvas
// then draw the 4 shapes
let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
let context = canvas.getContext('2d');

context.lineWidth = 5;

context.beginPath();
context.arc(50, 50, 25, 0, Math.PI*2);
context.closePath();
context.fillStyle = "#F8E";
context.fill();
context.strokeStyle = "#846";
context.stroke();


context.beginPath();
context.moveTo(25, 150);
context.lineTo(75, 150);
context.lineTo(50, 110);
context.closePath();
context.fillStyle = "sandybrown";
context.fill();
context.strokeStyle = 'darkgoldenrod';
context.stroke();


context.beginPath();
context.arc(125, 50, 25,90*Math.PI/180, 270*Math.PI/180);
context.moveTo(125, 75);
context.lineTo(175, 75);
context.arc(175, 50, 25, 90*Math.PI/180, 270*Math.PI/180, true);
context.lineTo(175, 25);
context.lineTo(125, 25);
context.fillStyle = "lightpink";
context.fill();
context.strokeStyle = "darkred";
context.stroke();


context.beginPath();
context.moveTo(100, 150);
context.lineTo(200, 150);
context.lineTo(200, 125);
context.lineTo(175, 100);
context.lineTo(150, 125);
context.lineTo(125, 100);
context.lineTo(100, 125);
context.closePath();
context.fillStyle = "gray";
context.fill();
context.strokeStyle = 'black';
context.stroke();
