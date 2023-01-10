import * as T from "../../libs/CS559-Three/build/three.module.js";
import { GrObject } from "../../libs/CS559-Framework/GrObject.js";

let snowmanHatObj = 0;
export class GrSnowmanHat extends GrObject {
  constructor(params = {}) {
    let scene = new T.Group();

    //snowman-2
    let geometry7 = new T.SphereGeometry( 3, 1000, 1000);
    let sphere4 = new T.Mesh( geometry7, new T.MeshPhongMaterial({ color: "white" }));
    sphere4.position.set(7, 0, 0);
    scene.add( sphere4 );

    let geometry8 = new T.SphereGeometry( 2, 1000, 1000);
    let sphere5 = new T.Mesh( geometry8, new T.MeshStandardMaterial({ color: "white" }));
    sphere5.position.set(7, 3.8, 0);
    scene.add( sphere5 );

    let geometry9 = new T.SphereGeometry( 1.5, 1000, 1000);
    let sphere6 = new T.Mesh( geometry9, new T.MeshStandardMaterial({ color: "white" }));
    sphere6.position.set(7, 6.5, 0);
    scene.add( sphere6 );

    //nose
    const geometry10 = new T.ConeGeometry( 0.2, 3, 1000);
    let cone2 = new T.Mesh( geometry10, new T.MeshBasicMaterial({ color: "brown" }));
    cone2.rotateX(-80);
    cone2.position.set(7, 6.5, 2);
    scene.add( cone2 );

    //eye
    let geometry11 = new T.SphereGeometry( 0.2, 1000, 1000);
    let eye3 = new T.Mesh( geometry11, new T.MeshPhongMaterial({ color: "red" }));
    eye3.position.set(7.5, 7, 1.3);
    scene.add(eye3);

    let eye4 = new T.Mesh( geometry11, new T.MeshPhongMaterial({ color: "red" }));
    eye4.position.set(6.5, 7, 1.3);
    scene.add(eye4);

    //mouth
    let geometry12 = new T.SphereGeometry( 0.1, 1000, 1000);
    let mouth4 = new T.Mesh( geometry12, new T.MeshPhongMaterial({ color: "red" }));
    mouth4.position.set(7, 6, 1.4);
    scene.add(mouth4);

    let mouth5 = new T.Mesh( geometry12, new T.MeshPhongMaterial({ color: "red" }));
    mouth5.position.set(7.2, 6.1, 1.4);
    scene.add(mouth5);

    let mouth6 = new T.Mesh( geometry12, new T.MeshPhongMaterial({ color: "red" }));
    mouth6.position.set(7.3, 6.2, 1.4);
    scene.add(mouth6);

    let mouth7 = new T.Mesh( geometry12, new T.MeshPhongMaterial({ color: "red" }));
    mouth7.position.set(7.4, 6.3, 1.4);
    scene.add(mouth7);

    let mouth8 = new T.Mesh( geometry12, new T.MeshPhongMaterial({ color: "red" }));
    mouth8.position.set(6.9, 6.1, 1.4);
    scene.add(mouth8);

    let mouth9 = new T.Mesh( geometry12, new T.MeshPhongMaterial({ color: "red" }));
    mouth9.position.set(6.8, 6.2, 1.4);
    scene.add(mouth9);

    let mouth10 = new T.Mesh( geometry12, new T.MeshPhongMaterial({ color: "red" }));
    mouth10.position.set(6.7, 6.3, 1.4);
    scene.add(mouth10);

    //hat
    let hatBox = new T.BoxGeometry(0.5, 4, 4);
    let hat1 = new T.Mesh(hatBox, new T.MeshStandardMaterial({ color: "gray" }));
    hat1.position.set(7.5, 8, 0);
    hat1.rotateZ(-90);
    scene.add(hat1);

    let hatBox2 = new T.BoxGeometry(1, 2.8, 2.8);
    let hat2 = new T.Mesh(hatBox2, new T.MeshStandardMaterial({ color: "red" }));
    hat2.position.set(7.7, 8.5, 0);
    hat2.rotateZ(-90);
    scene.add(hat2);

    let hatBox3 = new T.BoxGeometry(1.8, 2, 2);
    let hat3 = new T.Mesh(hatBox3, new T.MeshStandardMaterial({ color: "gray" }));
    hat3.position.set(8.3, 9.4, 0);
    hat3.rotateZ(-90);
    scene.add(hat3);

    //hand
        //right
    let handBox = new T.BoxGeometry(4, 0.5, 0.25);
    let righthand2 = new T.Mesh(handBox, new T.MeshStandardMaterial({ color: "pink" }));
    righthand2.position.set(8.5, 3.8, 0);
    scene.add(righthand2);
        //left
    let lefthand2 = new T.Mesh(handBox, new T.MeshStandardMaterial({ color: "pink" }));
    lefthand2.position.set(5.5, 3.8, 0);
    scene.add(lefthand2);

    

    // note that we have to make the Object3D before we can call
    // super and we have to call super before we can use this
    super(`snowmanHat-${snowmanHatObj++}`, scene);
    this.whole_ob = scene;
    this.rHand = righthand2;
    this.lHand = lefthand2;
    this.angle = 0;
    this.direction = 1;


    // put the object in its place
    this.whole_ob.position.x = params.x ? Number(params.x) : 0;
    this.whole_ob.position.y = params.y ? Number(params.y) : 0;
    this.whole_ob.position.z = params.z ? Number(params.z) : 0;
    this.whole_ob.rotateY(params.faceDir ? params.faceDir : 0);
    let scale = params.size ? Number(params.size) : 1;
    this.whole_ob.scale.set(scale, scale, scale);
  }

  stepWorld(delta, timeOfDay) {

    this.angle += this.direction * 0.01;
    if (this.angle <= -0.1 || this.angle >= 0.2) {
        this.direction *= -1;
    }
    this.rHand.rotateZ(delta/1000*this.direction * Math.PI);
    this.lHand.rotateZ(delta/1000*this.direction * Math.PI);
  }

}



let snowmanObj = 0;
export class GrSnowman extends GrObject {
  constructor(params = {}) {
    let scene = new T.Group();

    //snowman-1
    let geometry = new T.SphereGeometry( 2, 1000, 1000);
    let sphere = new T.Mesh( geometry, new T.MeshPhongMaterial({ color: "white" }));
    scene.add( sphere );

    let geometry2 = new T.SphereGeometry( 1.5, 1000, 1000);
    let sphere2 = new T.Mesh( geometry2, new T.MeshStandardMaterial({ color: "white" }));
    sphere2.position.set(0, 2, 0);
    scene.add( sphere2 );

    let geometry3 = new T.SphereGeometry( 1, 1000, 1000);
    let sphere3 = new T.Mesh( geometry3, new T.MeshStandardMaterial({ color: "white" }));
    sphere3.position.set(0, 3.5, 0);
    scene.add( sphere3 );

    //nose
    const geometry4 = new T.ConeGeometry( 0.2, 2, 1000);
    let cone = new T.Mesh( geometry4, new T.MeshBasicMaterial({ color: "red" }));
    cone.rotateX(-80);
    cone.position.set(0, 3.9, 1);
    scene.add( cone );

    //eye
    let geometry5 = new T.SphereGeometry( 0.2, 1000, 1000);
    let eye = new T.Mesh( geometry5, new T.MeshPhongMaterial({ color: "black" }));
    eye.position.set(0.4, 4.1, 0.6);
    scene.add(eye);

    let eye2 = new T.Mesh( geometry5, new T.MeshPhongMaterial({ color: "black" }));
    eye2.position.set(-0.4, 4.1, 0.6);
    scene.add(eye2);

    //mouth
    let geometry6 = new T.SphereGeometry( 0.05, 1000, 1000);
    let mouth = new T.Mesh( geometry6, new T.MeshPhongMaterial({ color: "black" }));
    mouth.position.set(0, 3.6, 1);
    scene.add(mouth);

    let mouth2 = new T.Mesh( geometry6, new T.MeshPhongMaterial({ color: "black" }));
    mouth2.position.set(0.3, 3.65, 1);
    scene.add(mouth2);

    let mouth3 = new T.Mesh( geometry6, new T.MeshPhongMaterial({ color: "black" }));
    mouth3.position.set(-0.3, 3.65, 1);
    scene.add(mouth3);

    //hand
        //right
    let box = new T.BoxGeometry(1.1, 0.1, 0.1);
    let righthand = new T.Mesh(box, new T.MeshStandardMaterial({ color: "yellow" }));
    righthand.position.set(2, 2, 0);
    scene.add(righthand);
        //left
    let lefthand = new T.Mesh(box, new T.MeshStandardMaterial({ color: "yellow" }));
    lefthand.position.set(-2, 2, 0);
    scene.add(lefthand);

    
    // note that we have to make the Object3D before we can call
    // super and we have to call super before we can use this
    super(`snowman-${snowmanObj++}`, scene);
    this.whole_ob = scene;

    // put the object in its place
    this.whole_ob.position.x = params.x ? Number(params.x) : 0;
    this.whole_ob.position.y = params.y ? Number(params.y) : 0;
    this.whole_ob.position.z = params.z ? Number(params.z) : 0;
    this.whole_ob.rotateY(params.faceDir ? params.faceDir : 0);
    let scale = params.size ? Number(params.size) : 1;
    this.whole_ob.scale.set(scale, scale, scale);
  }



}