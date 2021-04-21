//
// Created by TONY DE FREITAS on 24/02/2021.
//

#include <time.h>
#include <stdlib.h>
#include "block.h"

TxList *initTxList(){
    TxList *txList = malloc(sizeof(TxList));
    txList->txNumber = 0;
    txList->tx = malloc(MAXIMAL_TX * sizeof(char*));
    return txList;
}

Block *initBlock(const char hashCodePredecessor[64]){
    Block *block = malloc(sizeof(Block));
    time_t localTimeInSecond = time(NULL);
    block->nonce = 0;
    block->timestamp = localtime(&localTimeInSecond);
    strcpy(block->hashCodePredecessor, hashCodePredecessor);
    block->txList = initTxList();
    return block;
}

Block *addTx(Block *block, char* tx){
    block->txList->tx[block->txList->txNumber++] = tx;
    return block;
}