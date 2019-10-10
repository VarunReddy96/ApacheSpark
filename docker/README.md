# Docker Image for Parallel Computing

Please run ```docker images``` and check whether ```csci654``` image exist. 

If not, run the following to create a docker image. 
```
docker build -t csci654:latest .
```

The following commands for you to run within docker containers.

## OpenMP

### C/C++
Add ```-fopenmp``` to your compile command, for example,
```
g++ -fopenmp -o myProgram myProgram.cpp
``` 

For more details, see [OpenMP site](https://www.openmp.org/resources/openmp-compilers-tools/).
More examples about OpenMP [here](https://bisqwit.iki.fi/story/howto/openmp/)

### Java
Use [OMP4J](http://www.omp4j.org) library. OMP4J is a compile with a wrapper on top of javac. 
```
java -cp /csci654Tools/omp4j-1.2.jar myProgram.java
java -cp . myProgram
```



## OpenMPI
We use Open MPI implementation. 

### Start multiple docker containers
```
cd {PROJECT_ROOT}/docker
bash createContainers.bash 3
```
This will create 3 docker containers with names: container-{yourusername}-index. For example,
```
container-ph-1
container-ph-2
container-ph-3
```

### Stop and remove all docker containers
```
cd {PROJECT_ROOT}/docker
bash removeMyContainers.bash
```

### Before compiling source code
In each container (run ```docker attach container-{yourusername}-index``` to attach to a running container), run the following
```
git pull; cd /csci654/docker/; bash initContainer.bash
```


Depends on C/C++ or Java, please following the corresponding instructions below.

#### C/C++

To compile
```
cd /csci654/openmpi_examples/src/main/c
make
```

In the first container, add additional docker containers after ```localhost``` in ```/csci654/openmpi_examples/src/main/c/hostfile/myHostfile``` if necessary.

```
cd /csci654/openmpi_examples/src/main/c/bin
mpirun --hostfile /csci654/openmpi_examples/src/main/c/hostfiles/myHostfile --allow-run-as-root mpi_hello
```

Sample output
```
root@174ad6630e6b:/csci654/openmpi_examples/src/main/c/bin# mpirun --hostfile /csci654/openmpi_examples/src/main/c/hostfiles/myHostfile --allow-run-as-root mpi_hello
From C/C++ Program: Number of tasks= 4 My rank= 2 Running on 174ad6630e6b
From C/C++ Program: Number of tasks= 4 My rank= 3 Running on 174ad6630e6b
From C/C++ Program: Number of tasks= 4 My rank= 1 Running on 174ad6630e6b
From C/C++ Program: Number of tasks= 4 My rank= 0 Running on 174ad6630e6b
```

Note, add ```--prefix /usr/local``` if you have the folloing error:
```
orted: error while loading shared libraries: libopen-rte.so.40: cannot open shared object file: No such file or directory
```

Similar procedure for testing example within ```/csci654/openmpi_examples/src/main/mpitutorial/```.

[OpenMPI Reference](https://www.open-mpi.org/doc/current/)


#### Java
To compile your MPI java source code
```
cd /csci654/openmpi_examples/src/main/java
mpijavac -cp /csci654Tools/mpi.jar edu/rit/cs/MPI_Hello.java
```

Note, in other systems, ```mpi.jar``` can be found in your OpenMPI installation folder e.g.,```openmpi-4.0.1/ompi/mpi/java/java/mpi.jar```. 


To run your MPI program
```
cd /csci654/openmpi_examples/src/main/java
mpiexec --allow-run-as-root java -cp /csci654Tools/mpi.jar  edu.rit.cs.MPI_Hello
```

Sample output
```
root@174ad6630e6b:/csci654/openmpi_examples/src/main/java# mpiexec --allow-run-as-root java -cp /csci654Tools/mpi.jar  edu.rit.cs.MPI_Hello
From Java Program: Number of tasks= 4, My rank=0, Running on 174ad6630e6b
From Java Program: Number of tasks= 4, My rank=3, Running on 174ad6630e6b
From Java Program: Number of tasks= 4, My rank=1, Running on 174ad6630e6b
From Java Program: Number of tasks= 4, My rank=2, Running on 174ad6630e6b
```
```
