//
// Created by Sabrina on 22/04/2021.
//

#ifndef PROJETS4_C_GENERATETRANSACTIONS_H
#define PROJETS4_C_GENERATETRANSACTIONS_H
#include "block.h"

char* generateChar(char* user1,char* user2,int amount);
void generateTransactions(Block *block);
int genRandomTxNumber();
#endif //PROJETS4_C_GENERATETRANSACTIONS_H
