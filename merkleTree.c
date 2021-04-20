//
// Created by TONY DE FREITAS on 19/04/2021.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "block.h"
#include "blockchain.h"
#include "./Sha256/sha256.h"
#include "hash256.h"
#include "./Sha256/sha256.h"
#include "./Sha256/sha256_utils.h"

char** TransactionHashing (Block *block, char** transactionHashList);
char* concatenateHash(char* firstHash,char* secondHash);
void getMerkleRoot(Block *block);

char** initTransactionHashing (Block *block, char** transactionHashList) {
    int bufferSize = SHA256_BLOCK_SIZE;
    char hashRes[bufferSize*2 + 1];
    for(int i = 0; i < block->txNumber; i++){
        sha256ofString((BYTE*) block->txList.tx[i], hashRes);
        transactionHashList[i] = strdup(hashRes);
        printf("%s -> %s\n", block->txList.tx[i], transactionHashList[i]);
    }
    printf("\n");
    return transactionHashList;
}

void getMerkleRoot(Block *block){
    int bufferSize = SHA256_BLOCK_SIZE;
    char** transactionHashList = (char**) malloc((block->txNumber + 1) * sizeof(char[bufferSize*2+1]));
    char hashRes[bufferSize*2 + 1];
    transactionHashList = initTransactionHashing(block, transactionHashList);
    for(int i = block->txNumber; i > 1; i /= 2){
        if (i % 2 != 0) { // dédoublement du dernier hash dans la liste
            transactionHashList[i] = transactionHashList[i - 1];
            i++;
        }

        for (int j = 0, k = 0; j < i; j += 2, k++) {
            char *hashConcatenated = concatenateHash(transactionHashList[j], transactionHashList[j + 1]);
            sha256ofString((BYTE*) hashConcatenated, hashRes);
            transactionHashList[k]=hashRes;
            strcpy(block->hashMerkleTreeRoot, hashRes);
            printf("concatenate = %s\n", transactionHashList[k]);
        }
        printf("\n");
    }
    free(transactionHashList);
}

char* concatenateHash(char* firstHash,char* secondHash){
    char* destination;
    destination= malloc((strlen(firstHash)+strlen(secondHash)+1)*sizeof(char));
    strcpy(destination,firstHash);
    strcat(destination,secondHash);
    return destination;
}