/*
* A toy example of Pi estimation in CUDA
* A better version is here, https://docs.nvidia.com/cuda/curand/device-api-overview.html#thrust-and-curand-example
*/

#include <iostream>
#include <iomanip>
#include <math.h>
#include <sstream>
#include <sys/time.h>
#include <curand_kernel.h>

// Accessible by ALL CPU and GPU functions !!!
__managed__ int points_in_circle;
__managed__ double rnd_seed;

// Kernel function
__global__
void countPoints(int total_points, float radius)
{
    // index = block index * number of threads per block + thread index
    int index = blockIdx.x * blockDim.x + threadIdx.x;
    // stride  = number threads per block * number of block per grid
    int stride = blockDim.x * gridDim.x;

    curandState s;

    float r1, r2, x, y;
    float circle_x=radius, circle_y=radius;
    for (unsigned int i = index; i < total_points; i += stride) {
        // seed a random number generator
        curand_init(i+rnd_seed, 0, 0, &s);
        r1 = curand_uniform(&s);
        x = r1 * 2 * radius;
        r2 = curand_uniform(&s);
        y = r2 * 2 * radius;

        // Compare radius of circle with distance of its center from given point
        if ((x - circle_x) * (x - circle_x) +
                (y - circle_y) * (y - circle_y) <= radius * radius)
            atomicAdd(&points_in_circle, 1);
    }
}



// host code
int main(int argc, char* argv[]) {
  // Check the number of parameters
  if (argc < 3) {
      // Tell the user how to run the program
      std::cerr << "Usage: " << argv[0] << " TotalPoints Radius" << std::endl;
      return 1;
  }

  int total_points = std::stoi(argv[1]);
  std::string::size_type sz;     // alias of size_t
  float radius = std::stof(argv[2],&sz);

  // use the current time to create a seed for random number generator
  struct timeval t;
  gettimeofday(&t, 0);
  rnd_seed = 1000000.0*t.tv_sec/1000.0;

  points_in_circle=0;

  // Run kernel on some number of points on the GPU
  int blockSize = 256;
  int numBlocks = (total_points + blockSize - 1) / blockSize;
  countPoints<<<numBlocks, blockSize>>>(total_points, radius);

  // Wait for GPU to finish before accessing on host
  cudaDeviceSynchronize();

  float pi = 4.0f * points_in_circle / total_points;

  std::cout << std::setprecision(9);
  std::cout << "PI(" << points_in_circle << "/" << total_points << ")= "<< pi << std::endl;

  return 0;
}