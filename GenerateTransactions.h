//
// Created by Sabrina on 22/04/2021.
//

#ifndef PROJETS4_C_GENERATETRANSACTIONS_H
#define PROJETS4_C_GENERATETRANSACTIONS_H
#include "block.h"
#include "./utils/queue.h"
#include "./BCB/user.h"

char* generateChar(char* user1,char* user2,int amount);
void generateRandomTransaction(User **user, int usersNumber, Queue *queue);
int genRandomTxNumber();
#endif //PROJETS4_C_GENERATETRANSACTIONS_H
