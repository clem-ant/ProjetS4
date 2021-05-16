/// \file bcb.h
/// \author Tony De Freitas
/// \date 25 avril 2021
/// \brief Définition de structure et prototypes

#ifndef PROJET_S4_BCB_H
#define PROJET_S4_BCB_H
#include "../blockchain.h"

/*!
 * \brief Objet bcb
 *
 * Contient un long int pour la masse monétaire et la récompense, un int pour le nombre d'utilisateur, contient aussi
 * une blockchain et un pointeur void sur un futur tableau de user
 */
typedef struct Bcb Bcb;
struct Bcb{
    long int moneySupply;
    long int reward;
    int nbUser;
    Blockchain *blockchain;
    void **users;
};

void bcbStarting(Bcb *bcb);
void deleteBcb(Bcb *bcb);
Bcb *initBcb(int nbUser, int nbTx, int nbBlock, int difficulty);

#endif //PROJET_S4_BCB_H
