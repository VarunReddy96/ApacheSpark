## C/C++
You need to compile and run these examples within the ```cpp``` folder.
```
cd src/main/cpp
make
```

The binary of all examples will be in the ```bin``` folder.

If you are on a machine with CUDA-capable GPU device, you should get the following outputs.
```
~/myCUDA/examples$ ./bin/VectorAdditionGPUver1 
final result: 1.000000
~/myCUDA/examples$ ./bin/VectorAdditionGPUver2
Max error: 0
~/myCUDA/examples$ ./bin/VectorAdditionGPUver3
Max error: 0
```




## Java
You need to compile and run these examples within the Java folder.
```
cd src/main/java
```

All JCUDA *.jar files are in ```/usr/local/pub/ph/jcuda```.
```
jcublas-10.1.0-javadoc.jar                   jcuda-natives-10.1.0-windows-x86_64.jar      jcufft-natives-10.1.0.jar                    jcusparse-10.1.0-javadoc.jar
jcublas-10.1.0-sources.jar                   jcuda-natives-10.1.0.jar                     jcurand-10.1.0-javadoc.jar                   jcusparse-10.1.0-sources.jar
jcublas-10.1.0.jar                           jcudnn-10.1.0-javadoc.jar                    jcurand-10.1.0-sources.jar                   jcusparse-10.1.0.jar
jcublas-natives-10.1.0-apple-x86_64.jar      jcudnn-10.1.0-sources.jar                    jcurand-10.1.0.jar                           jcusparse-natives-10.1.0-apple-x86_64.jar
jcublas-natives-10.1.0-javadoc.jar           jcudnn-10.1.0.jar                            jcurand-natives-10.1.0-apple-x86_64.jar      jcusparse-natives-10.1.0-javadoc.jar
jcublas-natives-10.1.0-linux-x86_64.jar      jcudnn-natives-10.1.0-apple-x86_64.jar       jcurand-natives-10.1.0-javadoc.jar           jcusparse-natives-10.1.0-linux-x86_64.jar
jcublas-natives-10.1.0-sources.jar           jcudnn-natives-10.1.0-javadoc.jar            jcurand-natives-10.1.0-linux-x86_64.jar      jcusparse-natives-10.1.0-sources.jar
jcublas-natives-10.1.0-windows-x86_64.jar    jcudnn-natives-10.1.0-linux-x86_64.jar       jcurand-natives-10.1.0-sources.jar           jcusparse-natives-10.1.0-windows-x86_64.jar
jcublas-natives-10.1.0.jar                   jcudnn-natives-10.1.0-sources.jar            jcurand-natives-10.1.0-windows-x86_64.jar    jcusparse-natives-10.1.0.jar
jcuda-10.1.0-javadoc.jar                     jcudnn-natives-10.1.0-windows-x86_64.jar     jcurand-natives-10.1.0.jar                   jnvgraph-10.1.0-javadoc.jar
jcuda-10.1.0-sources.jar                     jcudnn-natives-10.1.0.jar                    jcusolver-10.1.0-javadoc.jar                 jnvgraph-10.1.0-sources.jar
jcuda-10.1.0.jar                             jcufft-10.1.0-javadoc.jar                    jcusolver-10.1.0-sources.jar                 jnvgraph-10.1.0.jar
jcuda-common-10.1.0-javadoc.jar              jcufft-10.1.0-sources.jar                    jcusolver-10.1.0.jar                         jnvgraph-natives-10.1.0-apple-x86_64.jar
jcuda-common-10.1.0-sources.jar              jcufft-10.1.0.jar                            jcusolver-natives-10.1.0-apple-x86_64.jar    jnvgraph-natives-10.1.0-javadoc.jar
jcuda-common-10.1.0.jar                      jcufft-natives-10.1.0-apple-x86_64.jar       jcusolver-natives-10.1.0-javadoc.jar         jnvgraph-natives-10.1.0-linux-x86_64.jar
jcuda-natives-10.1.0-apple-x86_64.jar        jcufft-natives-10.1.0-javadoc.jar            jcusolver-natives-10.1.0-linux-x86_64.jar    jnvgraph-natives-10.1.0-sources.jar
jcuda-natives-10.1.0-javadoc.jar             jcufft-natives-10.1.0-linux-x86_64.jar       jcusolver-natives-10.1.0-sources.jar         jnvgraph-natives-10.1.0-windows-x86_64.jar
jcuda-natives-10.1.0-linux-x86_64.jar        jcufft-natives-10.1.0-sources.jar            jcusolver-natives-10.1.0-windows-x86_64.jar  jnvgraph-natives-10.1.0.jar
jcuda-natives-10.1.0-sources.jar             jcufft-natives-10.1.0-windows-x86_64.jar     jcusolver-natives-10.1.0.jar         
```

To compile 
```
javac -cp .:/usr/local/pub/ph/jcuda/jcuda-10.1.0.jar:/usr/local/pub/ph/jcuda/jcuda-natives-10.1.0.jar JCudaRuntimeTest.java
```

To run a simple test
```
java -cp .:/usr/local/pub/ph/jcuda/jcuda-10.1.0.jar:/usr/local/pub/ph/jcuda/jcuda-natives-10.1.0-linux-x86_64.jar JCudaRuntimeTest 
```

You should get the following output
```
Pointer: Pointer[nativePointer=0x0,byteOffset=0]
```
If you get an error regarding "UnsatisfiedLinkError", then you may be using the wrong JCUDA native library. You should not need to reference to *.so.


### Writing the kernel

This kernel code is written exactly in the same way as it is done for CUDA. Usually, the kernel code will be located in an individual file. (In the CUDA Runtime API, the kernel function often is part of a larger C file. While it is still possible to have additional C code in the same file as the kernel, this C code will be ignored and not relevant for JCuda). 

There is only one important aspect to consider: When the kernel should be executed with the Driver API (regardless of whether it is used in CUDA or JCuda), the kernel function has to be identified and accessed by specifying its name. But when the code is compiled with a C/C++ compiler, the name of the function will be mangled - that means that the function name will internally be modified depending on its signature, and a simple kernel function name, like "add", may be converted to a name like "_Z3addiPfS_S_". While it is still possible to access the function using this name, it is in general much easier more intuitive to declare the kernel function as an
```extern "C"```
function. This way, the original name will be preserved.

### Compiling the kernel
The kernel source code will have to be compiled by the NVCC compiler. This will create a file that can be loaded and executed using the Driver API. There are basically two options how the kernel can be compiled:
- As a PTX file, which is a human-readable (but hardly human-understandable) file containing a specific form of "assembler" source code.
- As a CUBIN file, which is a "CUDA binary" and contains the compiled code that can directly be loaded and executed by a specific GPU.

Note, CUBIN files are generally used, they have an important drawback: They are specific for the Compute Capability of the GPU. The Compute Capability is a sort of a version number for the hardware, and CUBIN files that have been created for one Compute Capability can not be loaded on a GPU with a different Compute Capability. Thus, upcoming samples will in general prefer the usage of PTX files, since they are compiled at runtime for the GPU of the target machine.

A PTX file can be created from a simple, single CUDA source code file with the following command:
```
nvcc -ptx JCudaVectorAddKernel.cu -o JCudaVectorAddKernel.ptx
```
In order to create a valid CUBIN file, it may be necessary to specify the architecture and Compute Capability of the target machine. The full command line for creating a CUBIN file for a GPU with Compute Capability 2.1 on a 64 bit machine would be 
```
nvcc -cubin -m64 -arch sm_21 JCudaVectorAddKernel.cu -o JCudaVectorAddKernel.cubin
```




    