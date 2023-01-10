// @ts-check
export {};  // null statement to tell VSCode we're doing a module

// draw a picture using curves!

let canvas = document.getElementById("canvas1");
if (!(canvas instanceof HTMLCanvasElement))
    throw new Error("Canvas is not HTML Element");
    let context = canvas.getContext("2d");
    context.beginPath();
    context.lineWidth = 3;
    context.beginPath();
    context.moveTo(150, 115);
    context.bezierCurveTo(150, 112, 145, 100, 125, 100);
    context.moveTo(125,100);
    context.bezierCurveTo(95, 100, 95, 132, 95, 137.5);
    context.moveTo(175,100);
    context.bezierCurveTo(160, 100, 150, 112, 150, 115);
    context.moveTo(205, 138);
    context.bezierCurveTo(205, 137, 215, 100, 175, 100);
    context.stroke();

    context.beginPath();
    context.moveTo(150, 195);
    context.bezierCurveTo(185, 177, 205, 155, 205, 137.5);
    context.moveTo(95,138);
    context.bezierCurveTo(95, 155, 115, 177, 150, 195);
    context.strokeStyle="red";
    context.stroke();


