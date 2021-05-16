/// \file mining.c
/// \author Tony De Freitas
/// \date 15 février 2021
/// \brief Minage de block

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "../block.h"
#include "../Sha256/sha256_utils.h"
#include "mining_utils.h"
#include "mining.h"

/*!
 * \brief Vérifie si le hash code respecte bien le critère de difficulté
 * \param hashRes Le hash code à vérifier
 * \param difficulty Un entier représentant la difficulté (le nombre de 0 au début du hash)
 * \return Un boolean, true si la propriété est vérifiée, false sinon
 */
bool difficultyHashCheck(const char hashRes[SHA256_BLOCK_SIZE*2 + 1], int difficulty){
    for(int i = 0; i < difficulty; i++){
        if(hashRes[i] != '0'){
            return false;
        }
    }
    return true;
}

/*!
 * \brief Minage de block
 * \param block Le block qui doit être miné
 * \param difficulty La difficulté à respecter
 */
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