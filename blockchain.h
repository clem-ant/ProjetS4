//
// Created by TONY DE FREITAS on 23/02/2021.
//

#ifndef PROJET_S4_BLOCKCHAIN_H
#define PROJET_S4_BLOCKCHAIN_H
#include "block.h"
#define MAXIMAL_BLOCK 100

typedef struct Blockchain Blockchain;
struct Blockchain{
    int difficulty;
    int blockCursor;
    int length;
    Block **blocks;
};

Blockchain *initBlockchain(int difficulty, int length);
Block *addBlock(Blockchain *blockchain);
void deleteBlockchain(Blockchain *blockchain);
Block *createGenesis(Blockchain *blockchain);
void printBlockchain(Blockchain * blockchain);
#endif //PROJET_S4_BLOCKCHAIN_H
