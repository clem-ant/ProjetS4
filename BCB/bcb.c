//
// Created by TONY DE FREITAS on 25/04/2021.
//

#include <stdlib.h>
#include "bcb.h"
#include "user.h"
#include "../blockchain.h"
#include "../GenerateTransactions.h"
#include "../merkleTree.h"
#include "../Minage/mining.h"

Bcb *initBcb(int nbUser, int nbTx, int nbBlock, int difficulty){
    Bcb *bcb = malloc(sizeof(Bcb));
    bcb->blockchain = initBlockchain(difficulty, nbBlock, nbTx);
    bcb->moneySupply = 0;
    bcb->reward = 50;
    bcb->nbUser = nbUser;
    return bcb;
}

User **initUserTab(int userNumber){
    User **users = malloc(userNumber * sizeof(User*));
    users[0] = createUser("Creator", 0);
    char userIndex[5];

    for(int i = 1; i < userNumber; i++){
        char *userName = malloc(10 * sizeof(char));
        strcpy(userName, "User ");
        sprintf(userIndex, "%d", i);
        strcat(userName, userIndex);
        users[i] = createUser(userName, i);
    }

    return users;
}

void deleteUserTab(User **users, int size){
    if(size > 1){
        free(users[--size]->name);
        free(users[size]);
        return deleteUserTab(users, size);
    }
    free(users[0]);
    free(users);
}

void deleteTxInQueue(Queue* queueToDelete){
    unsigned int queueToDeleteSize = queueSize(queueToDelete);
    for (unsigned int i = 0; i < queueToDeleteSize; i++){
        char *tx = queueTop(queueToDelete);
        queuePop(queueToDelete);
        free(tx);
    }
}

void helicopterMoney(Bcb *bcb, User **users, Queue *queue){
    for(int i = 1; i < bcb->nbUser; i++){ // On démarre i à 1 car Creator a déjà était servi
        queuePush(queue, generateChar("Coinbase", users[i]->name, bcb->reward));
        users[i]->bankAccount += bcb->reward;
        bcb->moneySupply += bcb->reward;
    }
}

void deleteBcb(Bcb *bcb){
    deleteBlockchain(bcb->blockchain);
    free(bcb);
}

void bcbStarting(Bcb *bcb){
    User **users = initUserTab(bcb->nbUser);
    Queue *queue = createQueue();

    createGenesisByCreator(bcb, queue, users[0]);
    helicopterMoney(bcb, users, queue);
    generateRandomTransaction(users, bcb->nbUser, bcb->blockchain->txNumber, queue);

    int i = 0;
    while(!queueEmpty(queue) && bcb->blockchain->blockCursor < bcb->blockchain->blockNumber){
        createBlock(users[i%(bcb->nbUser)], bcb, queue);
        if(bcb->blockchain->blockCursor % 100 == 0){
            bcb->reward = bcb->reward / 2;
        }
        i++;
    }

    deleteTxInQueue(queue);
    deleteQueue(&queue);
    deleteUserTab(users, bcb->nbUser);
    printf("Masse monetaire = %d / Reward = %d\n", bcb->moneySupply, bcb->reward);
}