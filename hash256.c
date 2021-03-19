//
// Created by TONY DE FREITAS on 17/03/2021.
//

#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "./Sha256/sha256.h"
#include "./Sha256/sha256_utils.h"
#include "block.h"
#include <time.h>
#define STRLONG 60

int numberDigitsInteger(int number){
    int i = 0;

    if (number == 0){
        return 1;
    }

    while(number != 0){
        number = number / 10;
        i++;
    }
    return i;
}

char* blockItemsToString(const Block block, char* item){
    char* nonce = malloc(numberDigitsInteger(block.nonce)*sizeof(char));
    strcpy(item, itoa(block.nonce, nonce, 10));
    strcat(item, asctime(block.timestamp));
    strcat(item, block.hashCodePredecessor);
    //strcat(item, block.hashMerkleTreeRoot);
    for(int i = 0; i < block.txList.txIndex; i++){
        //strcat(item, block.txList.tx[i]);
    }
    free(nonce);
    return item;
}

int numberCharBlock(const Block block){
    int numberChar = 0;
    numberChar += numberDigitsInteger(block.nonce);
    numberChar += (int) strlen(asctime(block.timestamp));
    numberChar += (int) strlen(block.hashCodePredecessor);
    //numberChar += (int) strlen(block.hashMerkleTreeRoot);
    for(int i = 0; i < block.txList.txIndex; i++){
        //numberChar += (int) strlen(block.txList.tx[i]);
    }
    return numberChar;
}

char* hash256(const char* item, char* hashRes){
    sha256ofString((BYTE *)item, hashRes); // hashRes contient maintenant le hash de l'item
    int long_hash = (int) strlen(hashRes); // sa longueur en caractères hexadécimaux
    printf("%s \n", hashRes);
    return hashRes;
}