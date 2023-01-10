/** @type{HTMLInputElement} */ let slr4 = (/** @type{HTMLInputElement} */ document.getElementById("slider1"));

let button1 = document.getElementById("button1");
let button2 = document.getElementById("button2");

var startButton = false;
var stopButton = false;

function advanceSLR4() {
    // add speed, roll over to zero if we hit the max
    // note that the value of the slider is a string,
    // so we have to convert it to a number
    // The more obvious 1+"1" = "11" - thanks to JavaScripts
    // aggressive coercion rules

    button1.onclick = function() {
        startButton = true;
        stopButton = false;
    };

    button2.onclick = function() {
        stopButton = true;
        startButton = false;
    };

    let speed = startButton ? 2:0 ;
    let newValue = (Number(slr4.value)+speed) % 100;
    if (newValue<0) newValue=100;
    slr4.value = newValue.toString();
    // ask for this to be called again 16ms in the future
    window.requestAnimationFrame(advanceSLR4);
}
// note that just defined the function, now we need to call it
// to start the loop
advanceSLR4();


