#include <stdio.h>
#include <stdlib.h>
#include "block.h"
#include "blockchain.h"
#include "merkleTree.h"
#include "./Sha256/sha256.h"
#include "./Sha256/sha256_utils.h"
#include "Minage/mining_utils.h"
#include "./Minage/mining.h"
#include "./Verification/verification.h"
#include "Cheaters/cheaterTransaction.h"
#include "Cheaters/cheaterBlocks.h"
#include "./BCB/bcb.h"

#define RED   "\x1B[31m"
#define GRN   "\x1B[32m"
#define YEL   "\x1B[33m"
#define RESET "\x1B[0m"

int main(int argc, char *argv[]) {
    Blockchain *blockchain = initBlockchain(4, 1000);

    //blockchainIntegrity(blockchain);
    //deleteTransaction(blockchain, 2, 1);
    //cheaterBlock(blockchain, 3);
    bcb(blockchain);
    printBlockchain(blockchain);
    blockchainIntegrity(blockchain);
    deleteBlockchain(blockchain);

    return 0;
}