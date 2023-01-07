cmake_minimum_required(VERSION 3.20)
project(CS354_p5 C)

set(CMAKE_C_STANDARD 99)

add_executable(CS354_p5 mem_init.c mem.h mem_functions.c driver_alloc_8.c driver_alloc_1.c driver_alloc_1_24.c driver_alloc_8_free.c driver_free.c test.c)
