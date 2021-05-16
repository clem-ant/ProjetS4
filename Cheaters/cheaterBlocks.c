//
// Created by Sabrina on 24/04/2021.
//

#include "cheaterBlocks.h"
#include "../Minage/mining.h"
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#define RED   "\x1B[31m"
#define GRN   "\x1B[32m"
#define RESET "\x1B[0m"

void cheaterBlock(Blockchain *blockchain, int blockIndex){
    clock_t t1=clock();
    double time_spent=0.0;

    printf(RED "[Cheater de block]" RESET " - Suppression du block %d.\n", blockIndex);
    printf("Operation en cours...\n");

    deleteBlock(blockchain->blocks[blockIndex]);
    for (int i = blockIndex; i > 0 && i < blockchain->blockCursor - 1; ++i) {
        blockchain->blocks[i] = blockchain->blocks[i+1];
        blockchain->blocks[i]->nonce = 0;
        strcpy(blockchain->blocks[i]->hashCodePredecessor, blockchain->blocks[i-1]->hashCode);
        mining(blockchain->blocks[i], blockchain->difficulty);
    }

    blockchain->blocks[blockchain->blockCursor-1] = NULL;
    blockchain->blockCursor--;

    clock_t t2=clock();
    time_spent += (double)(t2- t1) / CLOCKS_PER_SEC;
    printf(GRN "[Cheater de block] - Operation terminee\n");
    printf(RESET "Temps d'execution : %f seconde(s)\n\n", time_spent);
}