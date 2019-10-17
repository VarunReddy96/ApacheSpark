GCC=g++
NVCC?=nvcc
OUT_DIR=bin

EXECS= pi \
       pi_nvidia

all: directories ${EXECS}

directories: ${OUT_DIR}

${OUT_DIR}:
	mkdir -p ${OUT_DIR}

clean:
	rm -rf bin

pi: pi.cu
	${NVCC} -o ${OUT_DIR}/pi pi.cu

pi_nvidia: pi_nvidia.cu
	${NVCC} -o ${OUT_DIR}/pi_nvidia pi_nvidia.cu

