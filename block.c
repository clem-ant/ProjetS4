//
// Created by TONY DE FREITAS on 24/02/2021.
//

#include <time.h>
#include <stdlib.h>
#include "block.h"
#include <stdio.h>

#define YEL   "\x1B[33m"
#define RESET "\x1B[0m"

char* getTimeStamp(){
    char* localTime = malloc(sizeof(char) * 26);
    time_t ltime = time(NULL);
    sprintf(localTime, "%s", ctime(&ltime));
    return localTime;
}

TxList *initTxList(){
    TxList *txList = malloc(sizeof(TxList));
    txList->txNumber = 0;
    txList->tx = malloc(MAXIMAL_TX * sizeof(char*));
    return txList;
}

Block *initBlock(const char hashCodePredecessor[SHA256_BLOCK_SIZE*2+1]){
    Block *block = malloc(sizeof(Block));
    block->nonce = 0;
    block->timestamp = getTimeStamp();
    strcpy(block->hashCodePredecessor, hashCodePredecessor);
    block->txList = initTxList();
    return block;
}

Block *addTx(Block *block, char* tx){
    block->txList->tx[block->txList->txNumber++] = tx;
    return block;
}

void deleteBlock(Block *block){
    free(block->txList->tx);
    free(block->txList);
    free(block->timestamp);
    free(block);
    block = NULL;
}

void printBlock(Block *block, int blockIndex){
    printf(YEL "[Block n°%d]\n", blockIndex);
    printf(RESET "| Date de creation : %s", block->timestamp);
    printf("| Hashcode predecessor : %s\n", block->hashCodePredecessor);
    printf("| Nonce : %d\n", block->nonce);
    printf("| Liste de transaction :\n");
    for (int i = 0; i < block->txList->txNumber; i++){
        printf("   | %d - %s\n", i, block->txList->tx[i]);
    }
    printf("| Hash merkle root : %s\n", block->hashMerkleTreeRoot);
    printf("| Hashcode du block : %s\n\n", block->hashCode);
}