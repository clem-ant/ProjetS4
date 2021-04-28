//
// Created by TONY DE FREITAS on 26/04/2021.
//

#ifndef PROJET_S4_USER_H
#define PROJET_S4_USER_H

typedef struct User User;
struct User{
    char* name;
    int userId;
    int bankAccount;
};
User *createUser(char* name, int userId);
#endif //PROJET_S4_USER_H
