// @ts-check
export {};  // null statement to tell VSCode we're doing a module

// recreate the picture from SVG - but don't use quadratics

let canvas = document.getElementById("canvas1");
if (!(canvas instanceof HTMLCanvasElement)) 
    throw new Error("Canvas is not HTML Element");
    let context = canvas.getContext("2d");
    context.beginPath();
    context.strokeStyle="black";
    context.fillStyle ="#CCC";
    context.lineWidth = 5;
    
    context.moveTo(150, 100);
    context.bezierCurveTo(150,           100+2/3*(50),  100+2/3*(50),  150,           100,  150);
    context.bezierCurveTo(100+2/3*(-50), 150,           50,            100+2/3*(50),  50,   100);
    context.bezierCurveTo(50,            100+2/3*(-50), 100+2/3*(-50), 50,            100,  50);
    context.bezierCurveTo(100,           50+2/3*(50),   150+2/3*(-50), 100,           150,  100);

    context.closePath();
    context.fill();
    context.stroke();