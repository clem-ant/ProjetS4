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

bool difficultyHashCheck(const char hashRes[SHA256_BLOCK_SIZE*2 + 1]){
    int difficulty = DIFFICULTY;
    for(int i = 0; i < difficulty; i++){
        if(hashRes[i] != '0'){
            return false;
        }
    }
    return true;
}

void mining(Block *block){
    int i = numberCharBlock(*block);
    int bufferSize = SHA256_BLOCK_SIZE;
    BYTE *blockContent = malloc((i+1) * sizeof(BYTE));
    char hashRes[bufferSize*2 + 1]; // contiendra le hash en hexadécimal
    while(true){
        blockItemsToString(*block, (char*) blockContent);
        sha256ofString(blockContent, hashRes);
        if(difficultyHashCheck(hashRes)){
            strcpy(block->hashCode, hashRes);
            printf("%d - %s\n", block->nonce, hashRes);
            free(blockContent);
            return;
        }
        block->nonce++;
    }
}