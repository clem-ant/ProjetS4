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

void bcb(Blockchain *blockchain){
    User **users = malloc(10 * sizeof(User*));
    Queue *queue = createQueue();

    char* u[10]={"Creator", "Sabrina", "Simran", "Tony", "Clement", "Alice", "Bob", "Chloe", "Emma", "Laura"};
    for(int i = 0; i < 10; i++){
        users[i] = createUser(u[i], i);
    }
    for(int i = 0; i < 10; i++){
        generateRandomTransaction(users, 10, queue);
    }

    Block *block = createGenesis(blockchain);
    merkleTree(block);

    while(!queueEmpty(queue)){
        block = addBlock(blockchain);
        for(int i = 0; i < 10 && !queueEmpty(queue); i++){
            char *tx = (char *) queueTop(queue);
            addTx(block, tx);
            queuePop(queue);
        }
        merkleTree(block);
        mining(block);
    }
    deleteQueue(&queue);
}