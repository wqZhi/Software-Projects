/** @type{HTMLInputElement} */ let slr = (/** @type{HTMLInputElement} */ document.getElementById("slider"));

function blink(){
    let toblink = document.getElementById("ex3-span");
    let value = 0;
    var increase  = true;
    let lasttime;
    function blinker(timestamp) {
            // red : 255, 0, 0
            // white : 255 , 255 , 255
            const delta = (timestamp - lasttime) / 10;
            
            if (increase) value++;
            if (!increase) value--;

            if (value >= 255) increase = false;
            if (value <=0) increase = true;
            toblink.style.backgroundColor = `rgb(255, ${value}, ${value})`;
            window.requestAnimationFrame(blinker);
            lasttime = timestamp;
    }
    window.requestAnimationFrame(blinker);
}

blink();