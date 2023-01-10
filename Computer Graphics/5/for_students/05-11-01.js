// @ts-check
export {};  // null statement to tell VSCode we're doing a module

// draw the spiral - account for the checkbox and slider

let canvas = (document.getElementById("canvas1"));
if (!(canvas instanceof HTMLCanvasElement))
    throw new Error("Canvas is not HTML Element");

let context = canvas.getContext("2d");
const slider = /** @type {HTMLInputElement} */ (document.getElementById("slider1"));
const box = (/** @type{HTMLInputElement} */ document.getElementById("check"));

function parabola(u) {
    return [200+u*180*Math.cos(2*Math.PI*4*u), 200+u*180*Math.sin(2*Math.PI*4*u)];
}

//detect click box
slider.onchange = function() {
    // @ts-ignore
    draw(slider.value);    
}

//detect click box
box.onclick = function(){
    // @ts-ignore
    draw(slider.value);      
}


function draw(points){ 
    // @ts-ignore
    context.clearRect(0,0,canvas.width,canvas.height);  

    // @ts-ignore
    if(box.checked){//draw lines
        context.beginPath();
        for(let i=0; i<=1; i+=1/points) {
            const p = parabola(i);
            context.lineTo(p[0],p[1]);
            context.stroke();
        }

    } else{ //draw dots
        for(let i=0; i<=1; i+=1/points) {
            const p = parabola(i);
            context.beginPath();
            context.arc(p[0],p[1],1, 0, 2 * Math.PI, false);
            context.fillStyle = "blue";
            context.strokeStyle = "blue";
            context.fill();
            context.stroke();
        }
    }
}

draw(slider.value);



