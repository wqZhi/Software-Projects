// @ts-check

import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrWorld } from "../../libs/CS559-Framework/GrWorld.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";

export class GrTower extends GrObject {
  /**
   *
   * @param {GrWorld} world
   */
  constructor(world) {
    let group = new T.Group();
    super("ShinyTower", group);

    let radius = 2;

    this.world = world;
    const cubeRenderTarget = new T.WebGLCubeRenderTarget( 128 );
    this.cubecam = new T.CubeCamera(radius*1.05, 1000, cubeRenderTarget);
    this.sculptureGeom = new T.SphereBufferGeometry(radius, 20, 10);
    this.sculptureMaterial = new T.MeshStandardMaterial({
      color: "white",
      roughness: 0,
      metalness: 1,
      // @ts-ignore   // envMap has the wrong type
      envMap: this.cubecam.renderTarget.texture,
    });//1st ball
    let sculpture = new T.Mesh(this.sculptureGeom, this.sculptureMaterial);
    group.add(this.cubecam);
    group.translateY(3.5);
    group.add(sculpture);

    const geometry = new T.CylinderGeometry( 0.3, 0.3, 3, 32 );
    const material = new T.MeshStandardMaterial( {
      color: "gray",
      roughness: 0.1,
      metalness: 0.4,
    } );
    let stand1 = new T.Mesh(geometry, material);
    stand1.position.set(2,-2.2,0);
    stand1.rotateZ(Math.PI/8);
    group.add(stand1);
    let stand2 = new T.Mesh(geometry, material);
    stand2.position.set(-2,-2.2,0);
    stand2.rotateZ(-Math.PI/8);
    group.add(stand2);
    let stand3 = new T.Mesh(geometry, material);
    stand3.position.set(0,-2.2,2);
    stand3.rotateX(-Math.PI/8);
    group.add(stand3);
    let stand4 = new T.Mesh(geometry, material);
    stand4.position.set(0,-2.2,-2);
    stand4.rotateX(Math.PI/8);
    group.add(stand4);

    //2nd ball
    this.sculptureGeom2 = new T.SphereBufferGeometry(1.9, 20, 10);
    let sculpture2 = new T.Mesh(this.sculptureGeom2, this.sculptureMaterial);
    sculpture2.translateY(7);
    group.add(sculpture2);

    const geometry2 = new T.CylinderGeometry( 0.3, 0.3, 6, 32 );
    const material2 = new T.MeshStandardMaterial( {
      color: "gray",
      roughness: 0.1,
      metalness: 0.4,
    } );
    let col1 = new T.Mesh( geometry2, material2 );
    col1.position.set(0.8,3,0.6);
    group.add(col1);
    let col2 = new T.Mesh( geometry2, material2 );
    col2.position.set(-0.8,3,0.6);
    group.add(col2);
    let col3 = new T.Mesh( geometry2, material2 );
    col3.position.set(0,3,-0.8);
    group.add(col3);

    const geometry3 = new T.CylinderGeometry( 0.3, 0.3, 2.2, 32 );
    const material3 = new T.MeshStandardMaterial( {
      color: "gray",
      roughness: 0.1,
      metalness: 0.4,
    } );
    let topCol1 = new T.Mesh( geometry3, material3);
    topCol1.position.set(0,10,0);
    group.add(topCol1);

    //3rd ball
    this.sculptureGeom3 = new T.SphereBufferGeometry(0.7, 20, 10);
    let sculpture3 = new T.Mesh(this.sculptureGeom3, this.sculptureMaterial);
    sculpture3.translateY(11.6);
    group.add(sculpture3);

    const geometry4 = new T.CylinderGeometry( 0.1, 0.1, 2.2, 32 );
    const material4 = new T.MeshStandardMaterial( {
      color: "gray",
      roughness: 0.1,
      metalness: 0.4,
    } );
    let topCol2 = new T.Mesh( geometry4, material4);
    topCol2.position.set(0,13,0);
    group.add(topCol2);
  }

  stepWorld(delta, timeOfDay) {
    this.cubecam.update(this.world.renderer, this.world.scene);
  }
}

