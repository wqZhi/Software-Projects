#include "functions.h"
#include <cmath>

const float PI = 3.1416;

float AreaOfCircle(const float& radius){
    return PI * pow(radius, 2);
}

float AreaOfTriangle(const float& width, const float& height){
    return (1.0/2.0) * width * height;
}

float AreaOfRectangle(const float& width, const float& height){
    return width * height;
}

float VolumeOfSphere(const float& radius){
    return (4.0/3.0) * PI * pow(radius, 3);
}

float VolumeOfCone(const float& radius, const float& height){
    return (1.0/3.0) * PI * pow(radius, 2) * height;
}

float VolumeOfBox(const float& width, const float& height, const float& length){
    return width * height * length;
}
