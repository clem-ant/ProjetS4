//
// Created by TONY DE FREITAS on 26/04/2021.
//

#ifndef PROJET_S4_USER_H
#define PROJET_S4_USER_H
#include "../block.h"
#include "bcb.h"
#include "../utils/queue.h"

typedef struct User User;
struct User{
    char* name;
    int userId;
    int bankAccount;
};

User *createUser(char* name, int userId);
Block *createBlock(User *user, Bcb *bcb, Queue *queue);
Block *createGenesisByCreator(Bcb *bcb, Queue *queue, User *creator);
#endif //PROJET_S4_USER_H
