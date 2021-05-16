/// \file blockchain.c
/// \author Tony De Freitas
/// \date 11 février 2021
/// \brief Fonctions permettant de construire une blockchain

#include <stdlib.h>
#include <stdio.h>
#include "blockchain.h"

#define YEL   "\x1B[33m"

/*!
 * \brief Permet d'obtenir le hash code du block précédent
 * \param blockchain Une blockchain où aller chercher ce hash code
 * \param hashCodePredecessor Une chaîne de caractère où sera écrit le hash code du block précédent
 * \return Une chaîne de caractère où est écrit le hash code du block précédent
 */
char* getHashCodePredecessor(Blockchain *blockchain, char *hashCodePredecessor){
    if(blockchain->blockCursor == 0){
        strcpy(hashCodePredecessor, "0");
        return "0";
    }
    strcpy(hashCodePredecessor, blockchain->blocks[blockchain->blockCursor-1]->hashCode);
    return blockchain->blocks[blockchain->blockCursor-1]->hashCode;
}

/*!
 * \brief Permet d'intialiser une blockchain
 * \param difficulty Un entier représentant la difficulté
 * \param blockNumber Un entier représentant le nombre maximum de block
 * \param txNumber Un entier représentant le nombre maximum de transaction
 * \return Un objet Blockchain
 */
Blockchain *initBlockchain(int difficulty, int blockNumber, int txNumber){
    Blockchain *blockchain = malloc(sizeof(Blockchain));
    blockchain->difficulty = difficulty;
    blockchain->blockCursor = 0;
    blockchain->blockNumber = blockNumber;
    blockchain->txNumber = txNumber;
    blockchain->blocks = malloc(blockNumber * sizeof(Block*));
    return blockchain;
}

/*!
 * \brief Permet d'ajouter un block à la blockchain passée en paramètre
 * \param blockchain Une blockchain où va être ajouté le block
 * \return Le block ajouté
 */
Block *addBlock(Blockchain *blockchain){
    char* hashCodePredecessor = malloc(sizeof(char) * (SHA256_BLOCK_SIZE*2+1));
    getHashCodePredecessor(blockchain, hashCodePredecessor);
    blockchain->blocks[blockchain->blockCursor] = initBlock(hashCodePredecessor);
    blockchain->blockCursor++;
    free(hashCodePredecessor);
    return blockchain->blocks[blockchain->blockCursor - 1];
}

/*!
 * \brief Permet de créer le block génésis
 * \param blockchain La blockchain où le block génésis va être ajouté
 * \return Le block génésis
 */
Block *createGenesis(Blockchain *blockchain){
    Block *block = addBlock(blockchain);
    char *txGenesis = malloc(8 * sizeof(char)); /* Allocation dynamique pour ne pas gérer de cas particulier
                                                lors de la suppression */
    strcpy(txGenesis, "Genesis");
    addTx(block, txGenesis);
    strcpy(block->hashCode, "0");
    return block;
}

/*!
 * \brief Permet de supprimer une blockchain
 * \param blockchain La blockchain à supprimer
 */
void deleteBlockchain(Blockchain *blockchain){
    for(int i = 0; i < blockchain->blockCursor; i++){
        deleteBlock(blockchain->blocks[i]);
    }
    free(blockchain->blocks);
    free(blockchain);
}

/*!
 * \brief Permet d'afficher sur la console la blockchain
 * \param blockchain La blockchain à afficher
 */
void printBlockchain(Blockchain * blockchain){
    printf(YEL "[Contenu de la blockchain]\n");

    for(int i = 0; i < blockchain->blockCursor; i++){
        printBlock(blockchain->blocks[i], i);
    }
}
