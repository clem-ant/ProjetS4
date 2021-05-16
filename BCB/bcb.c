/// \file bcb.c
/// \author Tony De Freitas
/// \date 25 avril 2021
/// \brief Banque Centrale, permet de gérer les utilisateurs, la phase helicopter money, l'inflation ...

#include <stdlib.h>
#include "bcb.h"
#include "user.h"
#include "../GenerateTransactions.h"

#define CYCLE 20
/*!
 * \brief Créer un tableau de pointeur vers des users
 * \param userNumber Le nombre d'user
 * \return Un tableau de pointeur vers des users
 */
User **initUserTab(int userNumber){
    User **users = malloc(userNumber * sizeof(User*));
    users[0] = createUser("Creator", 0);
    char userIndex[5]; // Permet de stocker un entier sous forme de char[]

    //Génération des utilisateurs
    for(int i = 1; i < userNumber; i++){
        char *userName = malloc(10 * sizeof(char));
        strcpy(userName, "User ");
        sprintf(userIndex, "%d", i);
        strcat(userName, userIndex);
        users[i] = createUser(userName, i);
    }

    return users;
}

/*!
 * \brief Initialise la banque centrale
 * \param nbUser Le nombre d'utilisateur
 * \param nbTx Le nombre de transaction qui seront générées
 * \param nbBlock Le nombre de block maximum
 * \param difficulty Le critère de difficulté à respecter
 * \return La bcb qui vient d'être créée
 */
Bcb *initBcb(int nbUser, int nbTx, int nbBlock, int difficulty){
    Bcb *bcb = malloc(sizeof(Bcb));
    bcb->blockchain = initBlockchain(difficulty, nbBlock, nbTx);
    bcb->moneySupply = 0;
    bcb->reward = 5000000000;
    bcb->nbUser = nbUser;
    bcb->users = (void **) initUserTab(nbUser);
    return bcb;
}

/*!
 * \brief Permet de supprimer un tableau d'utilisateur
 * \param users Le tableau de pointeur vers des users
 * \param size La taille du tableau
 */
void deleteUserTab(User **users, int size){
    //Fonction récursive, supprime tout les utilisateurs puis le creator et enfin le tableau
    if(size > 1){
        free(users[--size]->name);
        free(users[size]);
        return deleteUserTab(users, size);
    }
    free(users[0]);
    free(users);
}

/*!
 * \brief Permet de supprimer les transactions dans la queue
 * \param queueToDelete La queue où doivent être supprimée les transactions
 */
void deleteTxInQueue(Queue* queueToDelete){
    unsigned int queueToDeleteSize = queueSize(queueToDelete);
    for (unsigned int i = 0; i < queueToDeleteSize; i++){
        char *tx = queueTop(queueToDelete);
        queuePop(queueToDelete); //On enlève la valeur de la queue
        free(tx); //On libère la mémoire alloué à cette tx
    }
}

/*!
 * \brief Permet de faire la phase helicopter money en distribuant à tout les utilisateurs sauf le creator la somme de
 * 50 BNB
 * \param bcb La bcb où doit être effectué cette phase
 * \param users Le tableau d'utilisateur de cette bcb
 * \param queue La queue où sont stockés les transactions
 */
void helicopterMoney(Bcb *bcb, User **users, Queue *queue){
    for(int i = 1; i < bcb->nbUser; i++){ // On démarre i à 1 car Creator a déjà était servi
        queuePush(queue, generateChar("Coinbase", users[i]->name, bcb->reward));
        users[i]->bankAccount += bcb->reward;
        bcb->moneySupply += bcb->reward;
    }
}

/*!
 * \brief Permet de supprimer la banque centrale
 * \param bcb La banque centrale à supprimer
 */
void deleteBcb(Bcb *bcb){
    deleteBlockchain(bcb->blockchain);
    deleteUserTab((User **) bcb->users, bcb->nbUser);
    free(bcb);
}

/*!
 * \brief Fonction principale de simulation de création minétaire par la bcb
 * \param bcb La banque centrale où toutes les opérations vont se dérouler
 */

void bcbStarting(Bcb *bcb){
    Queue *queue = createQueue();

    createGenesisByCreator(bcb, queue, bcb->users[0]);
    helicopterMoney(bcb, (User **) bcb->users, queue);
    generateRandomTransaction((User **) bcb->users, bcb->nbUser, bcb->blockchain->txNumber, queue);

    int i = 0;
    while(!queueEmpty(queue) && bcb->blockchain->blockCursor < bcb->blockchain->blockNumber){
        createBlock(bcb->users[i%(bcb->nbUser)], bcb, queue);
        if(bcb->blockchain->blockCursor % CYCLE == 0){
            bcb->reward = bcb->reward / 2; //On divise par deux la récompense tout les
        }
        i++;
    }

    deleteTxInQueue(queue);
    deleteQueue(&queue);
    printf("Masse monetaire = %ld / Reward = %ld\n", bcb->moneySupply, bcb->reward);
}