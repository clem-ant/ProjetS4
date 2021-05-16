//
// Created by TONY DE FREITAS on 25/04/2021.
//

#ifndef PROJET_S4_BCB_H
#define PROJET_S4_BCB_H
#include "../blockchain.h"

typedef struct Bcb Bcb;
struct Bcb{
    int moneySupply;
    int reward;
    int nbUser;
    Blockchain *blockchain;
};

void bcbStarting(Bcb *bcb);
void deleteBcb(Bcb *bcb);
Bcb *initBcb(int nbUser, int nbTx, int nbBlock, int difficulty);
#endif //PROJET_S4_BCB_H
