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
 * Contient un entier pour la masse monétaire, la récompense et le nombre d'utilisateur, contient aussi une blockchain
 * un pointeur void sur un futur tableau de user
 */
typedef struct Bcb Bcb;
struct Bcb{
    int moneySupply;
    int reward;
    int nbUser;
    Blockchain *blockchain;
    void **users;
};

void bcbStarting(Bcb *bcb);
void deleteBcb(Bcb *bcb);
Bcb *initBcb(int nbUser, int nbTx, int nbBlock, int difficulty);

#endif //PROJET_S4_BCB_H
