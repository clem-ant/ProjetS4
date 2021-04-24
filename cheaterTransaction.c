//
// Created by Sabrina on 23/04/2021.
//

#include "cheaterTransaction.h"
#include <stdlib.h>
#include "merkleTree.h"
#include <stdio.h>
#include <time.h>
#include "Minage/mining.h"
#include "merkleTree.h"



void deleteTransaction(Blockchain *blockchain, int blockIndex, int txIndex) {
    clock_t t1=clock();
    double time_spent=0.0;
    mining(blockchain->blocks[blockIndex]); //TODO, sert un peu à rien
    //printf("Previous hash : %s\n",blockchain->blocks[blockIndex]->hashCodePredecessor);
    printf("Hash du block %d avant suppresion de la transaction %d : %s\n",blockIndex,txIndex,blockchain->blocks[blockIndex]->hashCode);
    merkleTree(blockchain->blocks[blockIndex]); //TODO, sert un peu à rien
    printf("Merkle root avant suppression de la transaction : %s\n", blockchain->blocks[blockIndex]->hashMerkleTreeRoot);
    blockchain->blocks[blockIndex]->txList->tx[txIndex]=""; //TODO, decaler les transactions
    blockchain->blocks[blockIndex]->txList->txNumber--; //TODO, same
    //TODO, free la dernière transaction après décalage de toutes les transactions
    merkleTree(blockchain->blocks[blockIndex]);
    printf("Merkle root apres suppression de la transaction : %s\n", blockchain->blocks[blockIndex]->hashMerkleTreeRoot);

    for (int i = blockIndex; i < blockchain->blockCursor; ++i) {
        blockchain->blocks[i]->nonce=0;
        strcpy(blockchain->blocks[i]->hashCodePredecessor,blockchain->blocks[i-1]->hashCode); //TODO, si c'est le genesis ca va planter
        mining(blockchain->blocks[i]);
        printf("Hash du block %d: %s\n",i, blockchain->blocks[i]->hashCode);
    }

    clock_t t2=clock();
    time_spent += (double)(t2- t1) / CLOCKS_PER_SEC;
    //printf("Previous hash : %s\n",blockchain->blocks[blockIndex]->hashCodePredecessor);
   // printf("Previous hash : %s\n",blockchain->blocks[blockIndex+1]->hashCodePredecessor);
    printf("Temps d'execution : %f secondes\n",time_spent);
}




