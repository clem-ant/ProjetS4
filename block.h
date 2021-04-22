//
// Created by TONY DE FREITAS on 23/02/2021.
//

#ifndef PROJET_S4_BLOCK_H
#define PROJET_S4_BLOCK_H

#include <time.h>
#include <string.h>

#define MAXIMAL_TX 50

typedef struct TxList TxList;
struct TxList{
    int txNumber;
    char** tx;
};

typedef struct Block Block;
struct Block{
    int nonce;
    struct tm *timestamp;
    char hashCode[64];
    char hashCodePredecessor[64];
    char hashMerkleTreeRoot[64];
    TxList *txList;
};

Block *initBlock(const char hashCodePredecessor[64]);
Block *addTx(Block *block, char* tx);

#endif //PROJET_S4_BLOCK_H