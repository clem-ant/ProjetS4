/// \file user.h
/// \author Tony De Freitas
/// \date 22 avril 2021
/// \brief Définition de structure et prototypes

#ifndef PROJET_S4_USER_H
#define PROJET_S4_USER_H
#include "../block.h"
#include "bcb.h"
#include "../utils/queue.h"

/*!
 * \brief Objet User
 *
 * Contient une chaîne de caractère étant le nom, un entier pour l'ID de l'utilisateur et un autre pour
 * le bank account
 */
typedef struct User User;
struct User{
    char* name;
    int userId;
    long int bankAccount;
};

User *createUser(char* name, int userId);
Block *createBlock(User *user, Bcb *bcb, Queue *queue);
Block *createGenesisByCreator(Bcb *bcb, Queue *queue, User *creator);
#endif //PROJET_S4_USER_H