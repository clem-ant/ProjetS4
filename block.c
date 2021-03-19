//
// Created by TONY DE FREITAS on 24/02/2021.
//

#include <time.h>
#include "block.h"

void initTxList(TxList txList){
    txList.txIndex = 0;
}

void initBlock(Block* block, const char hashCodePredecessor[64]){
    time_t localTimeInSecond = time(NULL);
    block->nonce = 0;
    block->timestamp = localtime(&localTimeInSecond);
    for (int i=0; i < 64; i++){
        block->hashCodePredecessor[i] = hashCodePredecessor[i];
    }
    initTxList(block->txList);
}