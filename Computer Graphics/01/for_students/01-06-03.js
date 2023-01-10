/** @type{HTMLInputElement} */ let slr = (/** @type{HTMLInputElement} */ document.getElementById("slider"));

let lasttime;
let newValue;
var increase = true;

function advanceSLR1(timestamp) {
    // add speed, roll over to zero if we hit the max
    // note that the value of the slider is a string,
    // so we have to convert it to a number
    // The more obvious 1+"1" = "11" - thanks to JavaScripts
    // aggressive coercion rules

    if (lasttime === undefined) {
        newValue = 0;
    } else {
        const delta = (timestamp - lasttime) / 10;
        if (increase) newValue = (Number(slr.value) + delta);
        if (!increase) newValue = (Number(slr.value) - delta);
    
        if (newValue >= 100) increase = false;
        if (newValue <= 0) increase = true;
    }

    slr.value = newValue.toString();
    // ask for this to be called again 16ms in the future
    window.requestAnimationFrame(advanceSLR1);
    lasttime = timestamp;
}
// note that just defined the function, now we need to call it
// to start the loop
advanceSLR1();
