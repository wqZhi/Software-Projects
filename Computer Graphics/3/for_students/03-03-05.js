// @ts-check
export {};

/**
 *
 * @param {HTMLCanvasElement} canvas
 */
function picture(canvas) {
    const context = canvas.getContext("2d");
    // student should use translate and scale to
    // change the coordinate system to [-100,100] [-100,100]
    // you only need to change line 1 and line 2

    // context.scale(0.8, -0.8); // line 2
    context.translate(canvas.width/2, canvas.height/2); // line 1
    context.scale(canvas.width/2/100, -canvas.height/2/100); // line 2

    // now I'll draw something...
    context.fillStyle = "lightgray";
    context.beginPath();
    context.moveTo(0, -100);
    context.lineTo(100, 0);
    context.lineTo(0, 100);
    context.lineTo(-100, 0);
    context.fill();
    // draw the plus in the center
    context.strokeStyle = "darkred";
    context.lineWidth = 2;
    context.beginPath();
    const dx = 20;
    context.moveTo(0, -dx);
    context.lineTo(0, dx);
    context.moveTo(-dx, 0);
    context.lineTo(dx, 0);
    context.stroke();
    // draw the T
    context.strokeStyle = 'darkblue';
    context.beginPath();
    context.moveTo(-90, 90);
    context.lineTo(-70, 90);
    context.moveTo(-80, 90);
    context.lineTo(-80, 70);
    context.stroke();
}

["canvas1", "canvas2", "canvas3"].forEach(function (name) {
    picture( /** @type {HTMLCanvasElement} */ (document.getElementById(name)));
});

