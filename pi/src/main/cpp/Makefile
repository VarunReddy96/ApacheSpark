GCC=g++
NVCC?=nvcc
OUT_DIR=bin

EXECS= pi

all: directories ${EXECS}

directories: ${OUT_DIR}

${OUT_DIR}:
	mkdir -p ${OUT_DIR}

clean:
	rm -rf bin

pi: pi.cu
	${NVCC} -o ${OUT_DIR}/pi pi.cu

