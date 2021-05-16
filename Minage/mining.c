//
// Created by TONY DE FREITAS on 20/04/2021.
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "../block.h"
#include "../Sha256/sha256_utils.h"
#include "mining_utils.h"
#include "mining.h"

bool difficultyHashCheck(const char hashRes[SHA256_BLOCK_SIZE*2 + 1], int difficulty){
    for(int i = 0; i < difficulty; i++){
        if(hashRes[i] != '0'){
            return false;
        }
    }
    return true;
}

void mining(Block *block, int difficulty){
    int i = numberCharBlock(block);
    int bufferSize = SHA256_BLOCK_SIZE;
    char *blockContent = malloc((i+1) * sizeof(char));
    char hashRes[bufferSize*2 + 1]; // contiendra le hash en hexadécimal
    while(true){
        blockItemsToString(block, blockContent);
        sha256ofString((BYTE*) blockContent, hashRes);
        if(difficultyHashCheck(hashRes, difficulty)){
            strcpy(block->hashCode, hashRes);
            free(blockContent);
            return;
        }
        block->nonce += 1;
    }
}