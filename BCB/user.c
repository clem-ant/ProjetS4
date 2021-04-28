//
// Created by TONY DE FREITAS on 26/04/2021.
//
#include <stdlib.h>
#include "user.h"

User *createUser(char* name, int userId){
    User *user = malloc(sizeof(User));
    user->name = name;
    user->userId = userId;
    user->bankAccount = 0;
    return user;
}