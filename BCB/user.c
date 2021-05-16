//
// Created by TONY DE FREITAS on 26/04/2021.
//

#include <stdlib.h>
#include "user.h"
#include "../Minage/mining.h"
#include "../block.h"
#include "../blockchain.h"
#include "../utils/queue.h"
#include "../merkleTree.h"
#include "../GenerateTransactions.h"
#include "bcb.h"

User *createUser(char* name, int userId){
    User *user = malloc(sizeof(User));
    user->name = name;
    user->userId = userId;
    user->bankAccount = 0;
    return user;
}

Block *createBlock(User *user, Bcb *bcb, Queue *queue){
    Block *block = addBlock(bcb->blockchain);
    queuePush(queue, generateChar("Coinbase", user->name, bcb->reward));
    srand((queueSize(queue)+1) * time(NULL));

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

Block *createGenesisByCreator(Bcb *bcb, Queue *queue, User *creator){
    Block *genesis = createGenesis(bcb->blockchain);
    merkleTree(genesis);
    queuePush(queue, generateChar("Coinbase", creator->name, bcb->reward));
    creator->bankAccount += bcb->reward;
    bcb->moneySupply += bcb->reward;
    return genesis;
}