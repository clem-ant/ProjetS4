//
// Created by TONY DE FREITAS on 23/02/2021.
//

#ifndef PROJET_S4_BLOCK_H
#define PROJET_S4_BLOCK_H

#include <time.h>
#include <string.h>
#include "./Sha256/sha256.h"

#define MAXIMAL_TX 50

typedef struct TxList TxList;
struct TxList{
    int txNumber;
    char** tx;
};

typedef struct Block Block;
struct Block{
    int nonce;
    char* timestamp;
    char hashCode[SHA256_BLOCK_SIZE*2+1];
    char hashCodePredecessor[SHA256_BLOCK_SIZE*2+1];
    char hashMerkleTreeRoot[SHA256_BLOCK_SIZE*2+1];
    TxList *txList;
};

Block *initBlock(const char hashCodePredecessor[SHA256_BLOCK_SIZE*2+1]);
Block *addTx(Block *block, char* tx);
void deleteBlock(Block *block);
#endif //PROJET_S4_BLOCK_H