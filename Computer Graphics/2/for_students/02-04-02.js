// JavaScript file to be filled in by the student for Box 4-2
// we'll give you something to get started...

// you should start by getting the canvas
// then draw whatever you want!
let canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
let context = canvas.getContext('2d');

//canvas
context.beginPath();
context.lineWidth = 5;
context.moveTo(0, 0);
context.lineTo(0, 500);
context.lineTo(500, 500);
context.lineTo(500, 0);
context.lineTo(0, 0);
context.closePath();
context.strokeStyle = 'black';
context.stroke();

//ocean
context.beginPath();
context.rect(0, 330, 500, 170);
context.lineWidth = 2;
context.closePath();
context.fillStyle = `rgba(0,0,255,0.5)`;
context.fill();
context.strokeStyle = 'black';
context.stroke();

//Sea Level
context.beginPath();
context.lineWidth = 1;
context.moveTo(0, 330);
context.lineTo(500, 330);
context.closePath();
context.strokeStyle = 'rgba(0, 0, 255)';
context.stroke();


//sun
context.beginPath();
context.lineWidth = 10;
context.arc(420, 80, 45, 0, Math.PI*2);
context.closePath();
context.fillStyle = `#FFA500`;
context.fill();
context.strokeStyle = 'rgba(255,165,0,0.7)';
context.stroke();


//boat body
context.beginPath();
context.lineWidth = 2;
context.moveTo(150, 370);
context.lineTo(350, 370);
context.lineTo(400, 300);
context.lineTo(100, 300);
context.lineTo(150, 370);
context.closePath();
context.fillStyle = "rgba(255, 0, 0, 0.15)";
context.fill();
context.strokeStyle = "gray";
context.stroke();


//boat components
context.beginPath();
context.lineWidth = 2;
context.moveTo(245, 300);
context.lineTo(245, 180);
context.moveTo(255, 300);
context.lineTo(255, 180);
context.strokeStyle = "gray";
context.stroke();

context.beginPath();
context.lineWidth = 3;
context.arc(250, 170, 10, 0, Math.PI*2);
context.closePath();
context.fillStyle = "rgba(255, 0, 0, 0.15)";
context.fill();
context.strokeStyle = "gray";
context.stroke();

//red flag
context.beginPath();
context.lineWidth = 2;
context.moveTo(256, 190);
context.lineTo(350, 270);
context.lineTo(256, 270);
context.closePath();
context.fillStyle = "rgba(255, 0, 0)";
context.fill();
context.strokeStyle = "black";
context.stroke();

//wisconsin logo
context.beginPath();
context.lineWidth = 7;
context.moveTo(265, 230);
context.lineTo(270, 250);
context.lineTo(280, 239);
context.lineTo(290, 250);
context.lineTo(295, 230);
context.strokeStyle = "rgba(0, 0, 0,0.3)";
context.stroke();

//generate
context.beginPath();
context.lineWidth = 2;
context.moveTo(358, 360);
context.lineTo(380, 360);
context.moveTo(360, 355);
context.lineTo(380, 355);
context.strokeStyle = "black";
context.stroke();

//generate water flow
context.beginPath();
context.lineWidth = 1;
context.moveTo(382, 355);
context.lineTo(397, 345);
context.moveTo(382, 359);
context.lineTo(397, 360);
context.moveTo(382, 363);
context.lineTo(397, 375);
context.strokeStyle = "black";
context.stroke();

//bubble
context.beginPath();
context.arc(420, 345, 5, 0, Math.PI*2);
context.closePath();
context.arc(410, 355, 5, 0, Math.PI*2);
context.closePath();
context.arc(415, 370, 5, 0, Math.PI*2);
context.closePath();
context.arc(430, 360, 5, 0, Math.PI*2);
context.closePath();
context.arc(445, 356, 5, 0, Math.PI*2);
context.closePath();
context.arc(465, 368, 5, 0, Math.PI*2);
context.closePath();
context.arc(476, 349, 5, 0, Math.PI*2);
context.closePath();
context.closePath();
context.fillStyle = "rgba(0,0,255,0.3)";
context.fill();
