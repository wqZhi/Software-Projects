import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";
import * as Geom from "../../libs/CS559-Three/examples/jsm/deprecated/Geometry.js";


// keep the helipad geometry global so we can re-use it
// variables are initialized to undefined by default
// (and its a warning to explicitly set them that way)
let helipadCount = 0;
let helipadMaterial;
let helipadGeometry;

export class Helipad extends GrObject {
  /**
   * Make a place for a helicopter to land
   *
   * @param {Number} x
   * @param {Number} y
   * @param {Number} z
   */
  constructor(x, y, z) {
    if (!helipadGeometry) {
      // make the helipad geometry as a global - if it's not there
      const q = 0.25;
      const h = 0.5;
      // make the normals point upwards - no matter what orientation the triangle has
      const up = new T.Vector3(0, -1, 0);
      const padcoords = [
        -1,
        0,
        -1,
        -1,
        0,
        1,
        -h,
        0,
        1,
        -h,
        0,
        -1,
        1,
        0,
        -1,
        1,
        0,
        1,
        h,
        0,
        1,
        h,
        0,
        -1,
        -h,
        0,
        -q,
        -h,
        0,
        q,
        h,
        0,
        q,
        h,
        0,
        -q,
      ];
      const padidx = [2, 1, 0, 3, 2, 0, 4, 5, 6, 4, 6, 7, 10, 9, 8, 10, 8, 11];
      helipadGeometry = new Geom.Geometry();
      for (let i = 0; i < padcoords.length; i += 3) {
        helipadGeometry.vertices.push(
          new T.Vector3(padcoords[i], padcoords[i + 1], padcoords[i + 2])
        );
      }
      for (let i = 0; i < padidx.length; i += 3) {
        helipadGeometry.faces.push(
          new Geom.Face3(padidx[i], padidx[i + 1], padidx[i + 2], up)
        );
      }
    }
    if (!helipadMaterial) {
      helipadMaterial = new T.MeshLambertMaterial({
        color: "#FFFF00",
        side: T.DoubleSide,
      });
    }
    let helipadBufferGeom = helipadGeometry.toBufferGeometry();
    let mesh = new T.Mesh(helipadBufferGeom, helipadMaterial);

    super(`Helipad-${++helipadCount}`, mesh);
    mesh.position.x = x ? x : 0;
    mesh.position.y = (y ? y : 0) + 0.01;
    mesh.position.z = z ? z : 0;
    mesh.receiveShadow = true;
    mesh.castShadow = false;
    this.mesh = mesh;
    this.objects.push(mesh);
  }
}