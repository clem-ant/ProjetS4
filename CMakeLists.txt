cmake_minimum_required(VERSION 3.16)
project(projet_s4 C)

set(CMAKE_C_STANDARD 11)

add_executable(bnb main.c blockchain.h block.h blockchain.c block.c Minage/mining_utils.c Minage/mining_utils.h ./Sha256/sha256.c ./Sha256/sha256.h ./Sha256/sha256_utils.c ./Sha256/sha256_utils.h merkleTree.c merkleTree.h Minage/mining.c Minage/mining.h Verification/verification.c Verification/verification.h Cheaters/cheaterTransaction.c Cheaters/cheaterTransaction.h BCB/bcb.c BCB/bcb.h Cheaters/cheaterBlocks.c Cheaters/cheaterBlocks.h BCB/user.c BCB/user.h utils/queue.c utils/queue.h GenerateTransactions.c GenerateTransactions.h)