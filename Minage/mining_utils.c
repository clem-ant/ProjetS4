//
// Created by TONY DE FREITAS on 17/03/2021.
//

#include <stdio.h>
#include <string.h>
#include "../block.h"
#include <time.h>
#define STRLONG 60


char* blockItemsToString(const Block *block, char* blockItems){
    char nonce[10];
    sprintf(nonce, "%d", block->nonce);
    strcpy(blockItems, nonce);
    strcat(blockItems, block->timestamp);
    strcat(blockItems, block->hashCodePredecessor);
    strcat(blockItems, block->hashMerkleTreeRoot);
    for(int i = 0; i < block->txList->txIndex; i++){
        strcat(blockItems, block->txList->tx[i]);
    }
    return blockItems;
}

int numberCharBlock(const Block *block){
    int numberChar = 0;
    numberChar += 10; // Correspondant à la taille maximum de la nonce
    numberChar += (int) strlen(block->timestamp);
    numberChar += (int) strlen(block->hashCodePredecessor);
    numberChar += (int) strlen(block->hashMerkleTreeRoot);
    for(int i = 0; i < block->txList->txIndex; i++){
        numberChar += (int) strlen(block->txList->tx[i]);
    }
    return numberChar;
}