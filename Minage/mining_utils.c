/// \file mining_utils.c
/// \author Tony De Freitas
/// \date 15 février 2021
/// \brief Fonctions utilitaires pour le minage de block

#include <stdio.h>
#include <string.h>
#include "../block.h"

/*!
 * \brief Converti le contenu d'un block en chaîne de caractères
 * \param block Le block où le contenu doit être converti
 * \param blockItems Une chaîne de caractère où va être stocké le résultat
 * \return Une chaîne de caractères où est stocké le résultat
 */
char* blockItemsToString(const Block *block, char* blockItems){
    char nonce[10];
    sprintf(nonce, "%d", block->nonce);
    strcpy(blockItems, nonce);
    strcat(blockItems, block->timestamp);
    strcat(blockItems, block->hashCodePredecessor);
    strcat(blockItems, block->hashMerkleTreeRoot);
    for(int i = 0; i < block->txList->txIndex; i++){
        strcat(blockItems, block->txList->tx[i]);
    }
    return blockItems;
}

/*!
 * \brief Permet de compter le nombre de caractères que contient les informations d'un block
 * \param block Le block où l'on doit compter
 * \return Un int représentant le nombre de caractères
 */
int numberCharBlock(const Block *block){
    int numberChar = 0;
    numberChar += 10; // Correspondant à la taille maximum de la nonce
    numberChar += (int) strlen(block->timestamp);
    numberChar += (int) strlen(block->hashCodePredecessor);
    numberChar += (int) strlen(block->hashMerkleTreeRoot);
    for(int i = 0; i < block->txList->txIndex; i++){
        numberChar += (int) strlen(block->txList->tx[i]);
    }
    return numberChar;
}