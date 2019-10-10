# Open MPI Examples 

Note, if you are using these examples within the Tardis cluster, you may want to use resources from ```/usr/local/pub/ph```. 


## C/C++ 
To compile your MPI C/C++ source code
```
cd src/main/c
make
```
Binary executables will be placed in the ```bin``` folder.

To run your MPI program
```
cd src/main/c/bin
mpiexec mpi_hello
```

Sample output
```
From C/C++ Program: Number of tasks= 2 My rank= 1 Running on mac-2.local
From C/C++ Program: Number of tasks= 2 My rank= 0 Running on mac-2.local
```

To run on cluster nodes
```
mpirun --hostfile myHostfile mpi_hello
```
You need to create a new file ```myHostfile``` and add ```localhost``` and other cluster node in each line.


Note, add ```--prefix /usr/local``` if you have the folloing error:
```
orted: error while loading shared libraries: libopen-rte.so.40: cannot open shared object file: No such file or directory
```


## Java


To compile your MPI java source code
```
cd src/main/java
mpijavac -cp <PATH-TO-OpenMPI-Source>/openmpi-4.0.1/ompi/mpi/java/java/mpi.jar edu/rit/cs/MPI_Hello.java
```
```mpi.jar``` can be found in your OpenMPI installation folder e.g.,```openmpi-4.0.1/ompi/mpi/java/java/mpi.jar```. 
On the ```tardis``` cluster, you can find ```mpi.jar``` in  ```/usr/local/pub/ph/mpi.jar```.

Note, here is how your can ```mpi.jar``` to your IntelliJ: Go to ```File/Project Structure.../Modules```, navigate to ```<PATH-TO-OpenMPI-Source>/openmpi-4.0.1/ompi/mpi/java/java/mpi.jar```.


To run your MPI program locally
```
mpiexec java -cp <PATH-TO-OpenMPI-Source>/openmpi-4.0.1/ompi/mpi/java/java/mpi.jar  edu.rit.cs.MPI_Hello
```

Sample output
```
From Java Program: Number of tasks= 2, My rank=1, Running on mac-2.local
From Java Program: Number of tasks= 2, My rank=0, Running on mac-2.local
```

Or running it across the tardis cluster nodes; create a new file ```myHostfile``` and add ```localhost``` and other nodes in each line, then run as
```
mpirun --hostfile myHostfile  java -cp /usr/local/pub/ph/mpi.jar edu.rit.cs.MPI_Hello
```

Note, add ```--prefix /usr/local``` before the ```java``` keyword if you have the folloing error:
```
orted: error while loading shared libraries: libopen-rte.so.40: cannot open shared object file: No such file or directory
```
