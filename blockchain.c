//
// Created by TONY DE FREITAS on 24/02/2021.
//

#include <stdlib.h>
#include "blockchain.h"
#include "block.h"
#include <stdio.h>

#define YEL   "\x1B[33m"

char* getHashCodePredecessor(Blockchain *blockchain, char *hashCodePredecessor){
    if(blockchain->blockCursor == 0){
        strcpy(hashCodePredecessor, "0");
        return "0";
    }
    strcpy(hashCodePredecessor, blockchain->blocks[blockchain->blockCursor-1]->hashCode);
    return blockchain->blocks[blockchain->blockCursor-1]->hashCode;
}

Blockchain *initBlockchain(int difficulty, int length){
    Blockchain *blockchain = malloc(sizeof(Blockchain));
    blockchain->difficulty = difficulty;
    blockchain->blockCursor = 0;
    blockchain->length = length;
    blockchain->blocks = malloc(length * sizeof(Block*));
    return blockchain;
}

Block *addBlock(Blockchain *blockchain){
    char* hashCodePredecessor = malloc(sizeof(char) * (SHA256_BLOCK_SIZE*2+1));
    getHashCodePredecessor(blockchain, hashCodePredecessor);
    blockchain->blocks[blockchain->blockCursor] = initBlock(hashCodePredecessor);
    blockchain->blockCursor++;
    free(hashCodePredecessor);
    return blockchain->blocks[blockchain->blockCursor - 1];
}

Block *createGenesis(Blockchain *blockchain){
    Block *block = addBlock(blockchain);
    addTx(block, "Genesis");
    strcpy(block->hashCode, "0");
    return block;
}

void deleteBlockchain(Blockchain *blockchain){
    for(int i = 0; i < blockchain->blockCursor; i++){
        deleteBlock(blockchain->blocks[i]);
    }
    free(blockchain->blocks);
    free(blockchain);
}

void printBlockchain(Blockchain * blockchain){
    printf(YEL "[Contenu de la blockchain]\n");

    for(int i = 0; i < blockchain->blockCursor; i++){
        printBlock(blockchain->blocks[i], i);
    }
}
