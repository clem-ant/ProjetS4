//
// Created by TONY DE FREITAS on 19/04/2021.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "block.h"
#include "./Sha256/sha256_utils.h"

char** initTransactionHashing (const Block *block) {
    int bufferSize = SHA256_BLOCK_SIZE;
    char hashRes[bufferSize*2 + 1];

    char** transactionHashList = malloc((block->txList->txNumber+1) * sizeof(char*));
    for(int i = 0; i < block->txList->txNumber + 1; i++){
        transactionHashList[i] = malloc((bufferSize*2+1) * sizeof(char));
    }

    for(int i = 0; i < block->txList->txNumber; i++){
        sha256ofString((BYTE*) block->txList->tx[i], hashRes);
        strcpy(transactionHashList[i], hashRes);
    }

    return transactionHashList;
}

void deleteTransactionHashList(char **transactionHashList, int size){
    for(int i = 0; i < size ; i++){
        free(transactionHashList[i]);
    }
    free(transactionHashList);
    //transactionHashList = NULL;
}

char* concatenateHash(char* firstHash, char* secondHash, char* hashConcatenated){
    strcpy(hashConcatenated,firstHash);
    strcat(hashConcatenated,secondHash);
    return hashConcatenated;
}

char* getMerkleRoot(const Block *block){
    int bufferSize = SHA256_BLOCK_SIZE;
    char hashRes[bufferSize*2 + 1];
    char *hashMerkleRoot = malloc((bufferSize*2+1) * sizeof(char));
    char** transactionHashList = initTransactionHashing(block);
    sha256ofString((BYTE*) transactionHashList[0], hashRes); //S'il n'y a qu'une seule transaction

    for(int i = block->txList->txNumber; i > 1; i /= 2){
        if (i % 2 != 0) { // dédoublement du dernier hash dans la liste
            strcpy(transactionHashList[i], transactionHashList[i - 1]);
            i++;
        }
        for (int j = 0, k = 0; j < i; j += 2, k++) {
            char *hashConcatenated = malloc((bufferSize*4+1)*sizeof(char));
            concatenateHash(transactionHashList[j], transactionHashList[j + 1], hashConcatenated);
            sha256ofString((BYTE*) hashConcatenated, hashRes);
            strcpy(transactionHashList[k], hashRes);
            free(hashConcatenated);
        }
    }

    strcpy(hashMerkleRoot, hashRes);
    deleteTransactionHashList(transactionHashList, block->txList->txNumber+1);
    return hashMerkleRoot;
}

void merkleTree(Block *block){
    char* merkleRoot = getMerkleRoot(block);
    strcpy(block->hashMerkleTreeRoot, merkleRoot);
    free(merkleRoot);
}