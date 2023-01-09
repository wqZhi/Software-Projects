import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";


let road = 0;
export class GrRoadStraight extends GrObject {
    constructor(params = {}) {
      let width = params.width || 2;
      let sX = params.startX;
      let sZ = params.startZ;
      let eX = params.endX;
      let eZ = params.endZ;
      let y = params.height || 0.01;
      let color = params.colors || "#696969";
      
    //   let color = params.colors || "#909090";
      let length = Math.sqrt((sX-eX)**2 + (sZ-eZ)**2 );

      let geometry = new T.BoxGeometry(width, 0.01, length);
      let mat = new T.MeshStandardMaterial({
        color: color,
        roughness: 1.0,
      });

      let mesh = new T.Mesh(geometry, mat);
      super(`road-${road++}`, mesh);

      //   mesh.translateY(0.1);
      mesh.translateY(y);
      mesh.translateX(sX + Math.abs(sX-eX)/2);
      mesh.translateZ(sZ + Math.abs(sZ-eZ)/2);
      mesh.rotateY(params.angle || 0);

    }
  }

  export class GrRoadCircular extends GrObject {
    constructor(params = {}) {
      let radius = params.radius || 6;
      let width = params.width || 1;
      let ring = new T.RingGeometry(radius - width, radius + width, 20, 3);
      let material = new T.MeshStandardMaterial({
        side: T.DoubleSide,
        color: "#909090",
        roughness: 1.0,
      });
      let mesh = new T.Mesh(ring, material);
      mesh.rotateX(Math.PI / 2);
      let group = new T.Group();
      group.add(mesh);
      group.translateX(params.x || 0);
      group.translateY(params.bias || 0.1); // raise track above ground to avoid z-fight
      group.translateZ(params.z || 0);
      super(`CircularTrack`, group);
  
      this.x = params.x || 0;
      this.z = params.z || 0;
      this.y = params.bias || 0.1;
      this.r = radius;
    }
    // eval(u) {
    //   let p = u * 2 * Math.PI;
    //   return [
    //     this.x + this.r * Math.cos(p),
    //     this.y,
    //     this.z + this.r * Math.sin(p),
    //   ];
    // }
    // tangent(u) {
    //   let p = u * 2 * Math.PI;
    //   // unit tangent vector - not the real derivative
    //   return [Math.sin(p), 0, -Math.cos(p)];
    // }
  }