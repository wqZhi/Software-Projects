/**
 * 04-04-02.js - a simple JavaScript file that gets loaded with
 * page 4 of Workbook 4 (CS559).
 *
 * written by Michael Gleicher, January 2019
 * modified January 2020
 *
 */

// @ts-check
/* jshint -W069, esversion:6 */

import * as utilities from "./04-04-utilities.js";

/**
 * TwoDots - a function for the student to write
 * Notice that it gets the two points and the context as arguments
 * This function should apply a transformation
 *
 * You must write this function using transform.
 * There should not be any rotate, translate or scale function calls.
 *
 * @param {CanvasRenderingContext2D} context
 * @param {number} x1
 * @param {number} y1
 * @param {number} x2
 * @param {number} y2
 */
function twoDots(context, x1, y1, x2, y2) {
    let rotate_degree = Math.atan2( (y2-y1), (x2-x1) ); 
    let currDis = Math.sqrt((y2-y1)**2 + (x2-x1)**2) / 10;

    let a = Math.cos(rotate_degree) * currDis;
    let b = Math.sin(rotate_degree) * currDis;
    let c = -Math.sin(rotate_degree) * currDis;
    let d = Math.cos(rotate_degree) * currDis;
    let e = x1;
    let f = y1;
    // please leave this line - you should CHANGE the 6 lines above
    context.transform(a, b, c, d, e, f);
}


// setup and start the program
utilities.setup("canvas1", twoDots, "black");

