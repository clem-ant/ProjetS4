/// \file generateTransaction.h
/// \author Sabrina Sikder
/// \date 1 avril 2021
/// \brief Prototypes

#ifndef PROJETS4_C_GENERATETRANSACTIONS_H
#define PROJETS4_C_GENERATETRANSACTIONS_H

#include "block.h"
#include "./utils/queue.h"
#include "./BCB/user.h"

char* generateChar(char* user1,char* user2, long int amount);
void generateRandomTransaction(User **user, int usersNumber, int txNumber, Queue *queue);

#endif //PROJETS4_C_GENERATETRANSACTIONS_H
