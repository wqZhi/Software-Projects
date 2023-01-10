/** @type{HTMLInputElement} */ let slider61 = (/** @type{HTMLInputElement} */ document.getElementById("box6-slider1"));
/** @type{HTMLInputElement} */ let slider62 = (/** @type{HTMLInputElement} */ document.getElementById("box6-slider2"));
/** @type{HTMLInputElement} */ let slider63 = (/** @type{HTMLInputElement} */ document.getElementById("box6-slider3"));

slider61.onchange = function() {
    slider63.value = Math.abs(slider62.value - slider61.value);
};
slider62.onchange = function() {
    slider63.value = Math.abs(slider62.value - slider61.value);
};
slider63.onchange = function() {
    if (slider62.value < slider63.value ) {
        slider62.value = 0;
        slider61.value = slider63.value - slider62.value;
    }
    else {
        slider62.value = 0;
        slider61.value = slider63.value;
    }

};
