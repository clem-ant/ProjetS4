//
// Created by TONY DE FREITAS on 20/04/2021.
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "../block.h"
#include "../Sha256/sha256.h"
#include "../hash256.h"
#include "minage.h"

bool difficultyCheck(Block *block, const char hashRes[SHA256_BLOCK_SIZE*2 + 1]){
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
    char *item = malloc((i+1) * sizeof(char));
    char hashRes[bufferSize*2 + 1]; // contiendra le hash en hexadécimal

    while(true){
        blockItemsToString(*block, item);
        hash256(item, hashRes);
        printf("%d - %s\n", block->nonce, hashRes);
        if(difficultyCheck(block, hashRes)){
            strcpy(block->hashCode, hashRes);
            printf("%d - %s\n", block->nonce, hashRes);
            free(item);
            return;
        }
        block->nonce++;
    }
}