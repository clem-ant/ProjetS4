/// \file user.c
/// \author Tony De Freitas
/// \date 22 avril 2021
/// \brief Fonctions de création d'un user et de création de block par l'user

#include <stdlib.h>
#include "user.h"
#include "../Minage/mining.h"
#include "../merkleTree.h"
#include "../GenerateTransactions.h"

/*!
 * \brief Permet de créer un utilisateur
 * \param name Une chaîne de caractère représentant le nom de l'utilisateur
 * \param userId L'ID de l'utilisateur
 * \return L'user qui vient d'être créé
 */
User *createUser(char* name, int userId){
    User *user = malloc(sizeof(User));
    user->name = name;
    user->userId = userId;
    user->bankAccount = 0;
    return user;
}

/*!
 * \brief Permet à un utilisateur de créer un block et d'être récompensé par la coinbase
 * \param user L'utilisateur qui va miner le block
 * \param bcb La banque central où tout va se dérouler
 * \param queue La queue où sont stockés les transactions
 * \return Le block créé
 */
Block *createBlock(User *user, Bcb *bcb, Queue *queue){
    Block *block = addBlock(bcb->blockchain);
    queuePush(queue, generateChar("Coinbase", user->name, bcb->reward));
    srand((queueSize(queue)+1) * time(NULL)); /* La taille de la queue permet d'obtenir un nombre plus aléatoire que
                                                time(NULL) qui est plus pseudo-aléatoire */

    for(int i = 0; i < (rand() % 10 + 1) && !queueEmpty(queue); i++){
        char *tx = (char *) queueTop(queue);
        addTx(block, tx);
        queuePop(queue);
    }

    merkleTree(block);
    mining(block, bcb->blockchain->difficulty);
    user->bankAccount += bcb->reward;
    bcb->moneySupply += bcb->reward;
    return block;
}

/*!
 * \brief Permet au creator de créer le block génésis
 * \param bcb La banque où tout va se dérouler
 * \param queue La queue où sont stockés les transactions
 * \param creator Un user représentant le creator
 * \return Le block génésis
 */
Block *createGenesisByCreator(Bcb *bcb, Queue *queue, User *creator){
    Block *genesis = createGenesis(bcb->blockchain);
    merkleTree(genesis);
    queuePush(queue, generateChar("Coinbase", creator->name, bcb->reward));
    creator->bankAccount += bcb->reward;
    bcb->moneySupply += bcb->reward;
    return genesis;
}