//
// Created by TONY DE FREITAS on 24/04/2021.
//

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include "../Minage/mining_utils.h"
#include "../blockchain.h"
#include "verification.h"
#include "../Sha256/sha256_utils.h"
#include "../merkleTree.h"
#define RED   "\x1B[31m"
#define GRN   "\x1B[32m"
#define RESET "\x1B[0m"

bool isGenesis(const Block *block){
    if(strcmp(block->hashCode, "0") == 0 && block->nonce == 0
       && block->txList->txIndex == 1 && strcmp(block->txList->tx[0], "Genesis") == 0){
        return true;
    }
    return false;
}

bool isMerkleRoot(const Block *block){
    char *currentMerkleRoot = getMerkleRoot(block);
    if(strcmp(currentMerkleRoot, block->hashMerkleTreeRoot) == 0){
        free(currentMerkleRoot);
        return true;
    }
    free(currentMerkleRoot);
    return false;
}

bool isHashCode(const Block *block){
    int i = numberCharBlock(block);
    int bufferSize = SHA256_BLOCK_SIZE;
    char *blockContent = malloc((i+1) * sizeof(char));
    char hashRes[bufferSize*2 + 1];
    blockItemsToString(block, blockContent);
    sha256ofString((BYTE*) blockContent, hashRes);
    free(blockContent);

    if(strcmp(block->hashCode, hashRes) == 0){
        return true;
    }

    return false;
}

bool isWellChained(const Block *previousBlock, const Block *block){
    if(strcmp(previousBlock->hashCode, block->hashCodePredecessor) == 0){
        return true;
    }
    return false;
}

bool blockchainIntegrity(const Blockchain *blockchain){
    char* testGenesis = GRN "[OK]"; //Premier test, vérification si le premier block est bien le block génésis
    char* testWellChained = GRN "[OK]"; //Deuxième test, vérification si le chaînage des hash est valide
    char* testHashCode = GRN "[OK]"; //Troisième test, vérification de la validé du hash des blocks
    char* testMerkleRoot = GRN "[OK]"; //Quatrième test, vérification de la validé du merkle root de chaque block

    if(!isGenesis(blockchain->blocks[0])){
        testGenesis = RED "[KO]";
    }

    for(int i = 1; i < blockchain->blockCursor; i++){
        if(!isWellChained(blockchain->blocks[i-1], blockchain->blocks[i])){
            testWellChained = RED "[KO]";
        }

        if(!isHashCode(blockchain->blocks[i])){
            testHashCode = RED "[KO]";
        }
        if(!isMerkleRoot(blockchain->blocks[i])){
            testMerkleRoot = RED "[KO]";
        }
    }

    printf(RESET "Test 1 - Genesis : %s\n", testGenesis);
    printf(RESET "Test 2 - Chainage des hash : %s\n", testWellChained);
    printf(RESET "Test 3 - Validite des hash : %s\n", testHashCode);
    printf(RESET "Test 4 - Validite des hash merkle root : %s\n" RESET, testMerkleRoot);
    return false;
}
