import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";


let treeAdvanceObj = 0;
export class GrTreeAdvance extends GrObject {
  constructor(params = {}) {
    let tree = new T.Group();

    //base
    let base_geom = new T.CylinderGeometry(0.3, 0.3, 1.5, 16);
    let base_mat = new T.MeshStandardMaterial({
      color: "brown",
      metalness: 0.5,
      roughness: 0.8
    });
    let base = new T.Mesh(base_geom, base_mat);
    base.translateY(0.7);
    tree.add(base);

    //top
    let top_geom = new T.CylinderGeometry(0.001, 1.3, 1.5, 8);
    let top_mat = new T.MeshStandardMaterial({
      color: "green",
      metalness: 0.5,
      roughness: 0.8
    });
    let top = new T.Mesh(top_geom, top_mat);
    top.translateY(1.3);
    base.add(top);

    let top_geom2 = new T.CylinderGeometry(0.001, 1, 1.5, 8);
    let top2 = new T.Mesh(top_geom2, top_mat);
    top2.translateY(0.8);
    top.add(top2);

    let top_geom3 = new T.CylinderGeometry(0.001, 0.7, 1, 8);
    let top3 = new T.Mesh(top_geom3, top_mat);
    top3.translateY(1.4);
    top.add(top3);

    //ground-soil
    let soil_geom = new T.BoxGeometry(2, 0.1, 2);
    let soil_mat = new T.MeshStandardMaterial({
        color: "brown",
        roughness: 1,
        metalness: 0.5
    });
    let soil = new T.Mesh(soil_geom, soil_mat);
    soil.translateY(-0.7);
    base.add(soil);

    //protect
    let protect_geom = new T.BoxGeometry(2, 0.4, 0.1);
    let protect_mat = new T.MeshStandardMaterial({
        color: "white",
        roughness: 0,
        metalness: 0.6
    });
    let protect = new T.Mesh(protect_geom, protect_mat);
    protect.position.set(0,-0.7,1);
    base.add(protect);

    let protect2 = new T.Mesh(protect_geom, protect_mat);
    protect2.position.set(0,-0.7,-1);
    base.add(protect2);

    let protect3 = new T.Mesh(protect_geom, protect_mat);
    protect3.position.set(1,-0.7,0);
    protect3.rotateY(Math.PI/2);
    base.add(protect3);

    let protect4 = new T.Mesh(protect_geom, protect_mat);
    protect4.position.set(-1,-0.7,0);
    protect4.rotateY(Math.PI/2);
    base.add(protect4);


    super(`treeAdvance-${treeAdvanceObj++}`, tree);
    this.whole_ob = tree;


    // put the object in its place
    this.whole_ob.position.x = params.x ? Number(params.x) : 0;
    this.whole_ob.position.y = params.y ? Number(params.y) : 0;
    this.whole_ob.position.z = params.z ? Number(params.z) : 0;

    let scale = params.size ? Number(params.size) : 1;
    tree.scale.set(scale, scale, scale);
  }
}


let treeSimpleObj = 0;
export class GrTreeSimple extends GrObject {
  constructor(params = {}) {
    let tree = new T.Group();

    //base
    let base_geom = new T.CylinderGeometry(0.2, 0.2, 2, 16);
    let base_mat = new T.MeshStandardMaterial({
      color: "brown",
      metalness: 0.5,
      roughness: 0.8
    });
    let base = new T.Mesh(base_geom, base_mat);
    base.translateY(0.8);
    tree.add(base);

    //top
    let top_geom = new T.CylinderGeometry(0.1, 0.5, 2.7, 8);
    let top_mat = new T.MeshStandardMaterial({
      color: "green",
      metalness: 0.5,
      roughness: 0.8
    });
    let top = new T.Mesh(top_geom, top_mat);
    top.translateY(1.3);
    base.add(top);



    super(`treeSimple-${treeSimpleObj++}`, tree);
    this.whole_ob = tree;

    // put the object in its place
    this.whole_ob.position.x = params.x ? Number(params.x) : 0;
    this.whole_ob.position.y = params.y ? Number(params.y) : 0;
    this.whole_ob.position.z = params.z ? Number(params.z) : 0;

    let scale = params.size ? Number(params.size) : 1;
    tree.scale.set(scale, scale, scale);
  }
}