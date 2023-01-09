import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";

export class GrSnow extends GrObject {
    
  constructor() {
    const snow = new T.MeshStandardMaterial({color: "White", roughness: 1.0});
    const sphere = new T.SphereBufferGeometry(1, 20, 20);
    const snowflake = [];
    const snows = [];
    let snows_group = new T.Group();

    for (let i = 0; i < 1001; i++) {
      // Compute random locations and sizes
      snows[i] = [Math.random() * 42 - 21, Math.random() * 40, Math.random() * 42 - 21, 0.05 + 0.05 * Math.random()];
      // Create the snowflake
      snowflake[i] = new T.Mesh(sphere, snow);
      snowflake[i].position.set(snows[i][0], snows[i][1], snows[i][2]);
      snowflake[i].scale.set(snows[i][3], snows[i][3], snows[i][3]);
      snows_group.add(snowflake[i]);
    }

    super(`snow`, snows_group);
    this.snows = snows;
    this.snowflake = snowflake;
    this.time = 0;
  }


    stepWorld(delta, timeOfDay) {
        let snows = this.snows;
        // Move the snowflakes down by 1 and to the left or right randomly

        // if (this.snows.length == 1000) {
            this.time += delta;
            if (this.time > 20) {
                this.time -= 20;

                this.snowflake.forEach(function(flake, i) {
                    // Update the position of the snow
                    snows[i][1] -= 0.05;// droping speed
                    snows[i][0] += 0.1 * (Math.random() - 0.5);// shift left
                    snows[i][2] += 0.1 * (Math.random() - 0.5);// shift right
                    // Move the snow back up to the top when they hit the ground
                    if (snows[i][1] < 0) {
                        snows[i][1] = 40;
                        snows[i][3] = 0.05 + 0.05 * Math.random();
                    }
                    // Reset the position of the meshes
                    flake.position.set(snows[i][0], snows[i][1], snows[i][2]);
                });

            }

        // }
        
    }
}