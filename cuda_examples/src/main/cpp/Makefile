GCC=g++
NVCC?=nvcc
OUT_DIR=bin

EXECS= VectorAdditionCPU \
       VectorAdditionGPUver1 \
       VectorAdditionGPUver2 \
       VectorAdditionGPUver3

all: directories ${EXECS}

directories: ${OUT_DIR}

${OUT_DIR}:
	mkdir -p ${OUT_DIR}


VectorAdditionCPU: VectorAdditionCPU.cpp
	${GCC} -o ${OUT_DIR}/VectorAdditionCPU VectorAdditionCPU.cpp


VectorAdditionGPUver1: VectorAdditionGPUver1.cu
	${NVCC} -o ${OUT_DIR}/VectorAdditionGPUver1 VectorAdditionGPUver1.cu

VectorAdditionGPUver2: VectorAdditionGPUver2.cu
	${NVCC} -o ${OUT_DIR}/VectorAdditionGPUver2 VectorAdditionGPUver2.cu

VectorAdditionGPUver3: VectorAdditionGPUver3.cu
	${NVCC} -o ${OUT_DIR}/VectorAdditionGPUver3 VectorAdditionGPUver3.cu