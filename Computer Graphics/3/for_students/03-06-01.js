// @ts-check
export {};

const canvas = /** @type {HTMLCanvasElement} */ (document.getElementById("canvas1"));
let ctx = canvas.getContext("2d");


function drawDrone(angle) {
    ctx.save();
    // draw the body
        ctx.save();
            ctx.beginPath();
            ctx.fillStyle = "gray";
            ctx.moveTo(300, 300);
            ctx.lineTo(350, 300);
            ctx.lineTo(370, 320);
            ctx.lineTo(370, 420);
            ctx.lineTo(350, 440);
            ctx.lineTo(300, 440);
            ctx.lineTo(280, 420);
            ctx.lineTo(280, 320);
            ctx.lineTo(300, 300);
            ctx.fill();
            ctx.stroke();
            ctx.closePath();
            ctx.fillStyle = "black";
            ctx.fillRect(259, 354, 20, 30);
        ctx.restore();

     //draw the arms
        ctx.save();
            ctx.strokeStyle = 'lightgray';
            ctx.lineWidth = 7;
            ctx.beginPath();
            ctx.moveTo(289, 310);
            ctx.lineTo(259, 280);
            ctx.moveTo(361, 310);
            ctx.lineTo(390, 280);
            ctx.moveTo(361, 430);
            ctx.lineTo(391, 460); 
            ctx.moveTo(289, 430);
            ctx.lineTo(259, 460); 
            ctx.stroke();
            ctx.beginPath();
            ctx.lineWidth = 1;
            ctx.strokeStyle = 'black';
            ctx.arc(259, 280, 35, 0, 2 * Math.PI);
            ctx.stroke();
            ctx.beginPath();
            ctx.arc(390, 280, 35, 0, 2 * Math.PI);
            ctx.stroke();
            ctx.beginPath();
            ctx.arc(391, 460, 35, 0, 2 * Math.PI);
            ctx.stroke();
            ctx.beginPath();
            ctx.arc(259, 460, 35, 0, 2 * Math.PI);
            ctx.stroke();
        ctx.restore();

        ctx.save();
            // spin the propeller
            ctx.save();
            ctx.translate(259, 280); // we'll build the propeller at the origin, move into place
            ctx.rotate(angle/15); // spin the propeller
            // place the different blades at 90 degree angles to the first
            for (let blades = 0; blades < 4; blades++) {
                ctx.fillStyle = "black";
                ctx.fillRect(0, 0, 30, 1);
                ctx.rotate(Math.PI / 2);
            }
        ctx.restore();

        // spin the propeller
        ctx.save();
            ctx.translate(390, 280); // we'll build the propeller at the origin, move into place
            ctx.rotate(angle/4); // spin the propeller
            // place the different blades at 90 degree angles to the first
            for (let blades = 0; blades < 4; blades++) {
                ctx.fillStyle = "black";
                ctx.fillRect(0, 0, 30, 1);
                ctx.rotate(Math.PI / 2);
            }
        ctx.restore();

        // spin the propeller
        ctx.save();
            ctx.translate(391, 460); // we'll build the propeller at the origin, move into place
            ctx.rotate(angle/2); // spin the propeller
            // place the different blades at 90 degree angles to the first
            for (let blades = 0; blades < 4; blades++) {
                ctx.fillStyle = "black";
                ctx.fillRect(0, 0, 30, 1);
                ctx.rotate(Math.PI / 2);
            }
        ctx.restore();

        // spin the propeller
        ctx.save();
            ctx.translate(259, 460); // we'll build the propeller at the origin, move into place
            ctx.rotate(angle/8); // spin the propeller
            // place the different blades at 90 degree angles to the first
            for (let blades = 0; blades < 4; blades++) {
                ctx.fillStyle = "black";
                ctx.fillRect(0, 0, 30, 1);
                ctx.rotate(Math.PI / 2);
            }
        ctx.restore();
    
    ctx.restore();
}

function draw (angle, b, newX, newY) {
    ctx.save();
    ctx.translate(newX, newY);
    ctx.scale(0.3, 0.3);
    ctx.rotate(b);
    drawDrone(angle);
    ctx.restore();
}

let moveOn = 0;
let newX, newY, dx, dy, b = 0;
// and you will want to make an animation loop with something like:
/**
 * the animation loop gets a timestamp from requestAnimationFrame
 * 
 * @param {DOMHighResTimeStamp} timestamp 
 */
function loop(timestamp) {
    let a = timestamp / 20;
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    ctx.save();
        moveOn += 0.01;
        dx = Math.sin(moveOn)
        dy = -Math.cos(moveOn) 
        b = Math.atan2(dy, dx);
        newX = dx*2;
        newY = dy*2;
        draw(a, b, newX+300, newY+300);
    ctx.restore();

    ctx.save();
        moveOn += 0.01;
        dx = Math.cos(2*moveOn) * 200;
        dy = Math.cos(moveOn) * 50;
        b = Math.atan2(dy, dx);
        draw(a, b, dx+300, dy+280);
    ctx.restore();

    window.requestAnimationFrame(loop);
};
// and then you would start the loop with:
window.requestAnimationFrame(loop);