//
// Created by TONY DE FREITAS on 24/02/2021.
//

#include <time.h>
#include "block.h"

void initBlock(Block* block, const char hashCodePredecessor[64]){
    block->nonce = 0;
    block->timestamp = time(NULL);
    for (int i=0; i < 64; i++){
        block->hashCodePredecessor[i] = hashCodePredecessor[i];
    }
}