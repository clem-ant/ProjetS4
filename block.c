/// \file block.c
/// \author Tony De Freitas
/// \date 11 février 2021
/// \brief Fonctions permettant de construire des blocks

#include <time.h>
#include <stdlib.h>
#include "block.h"
#include <stdio.h>

#define YEL   "\x1B[33m"
#define RESET "\x1B[0m"

/*!
 * \brief Permet d'obtenir la date sous forme de chaine de caractère
 * \return Une chaine de caractère représentant la date
 */
char* getTimeStamp(){
    char* localTime = malloc(sizeof(char) * 26);
    time_t ltime = time(NULL);
    sprintf(localTime, "%s", ctime(&ltime));
    return localTime;
}

/*!
 * \brief Initialisation de la list de transaction
 * \return Une liste de transaction
 */
TxList *initTxList(){
    TxList *txList = malloc(sizeof(TxList));
    txList->txIndex = 0;
    txList->tx = malloc(MAXIMAL_TX * sizeof(char*));
    return txList;
}

/*!
 * \brief Initialisation d'un block
 * \param hashCodePredecessor Le hash code du block précédent
 * \return Un block
 */
Block *initBlock(const char hashCodePredecessor[SHA256_BLOCK_SIZE*2+1]){
    Block *block = malloc(sizeof(Block));
    block->nonce = 0;
    block->timestamp = getTimeStamp();
    strcpy(block->hashCodePredecessor, hashCodePredecessor);
    block->txList = initTxList();
    return block;
}

/*!
 * \brief Ajoute une transaction dans la liste de transaction d'un block
 * \param block Le block où ajouter la transaction
 * \param tx Une chaîne de caractère représentant la transaction à ajouter
 * \return Le block avec la transaction ajouté
 */
Block *addTx(Block *block, char* tx){
    block->txList->tx[block->txList->txIndex++] = tx;
    return block;
}

/*!
 * \brief Permet de supprimer un block
 * \param block Le block à supprimer
 */
void deleteBlock(Block *block){
    for(int i = 0; i < block->txList->txIndex; i++){
        free(block->txList->tx[i]);
    }
    free(block->txList->tx);
    free(block->txList);
    free(block->timestamp);
    free(block);
    block = NULL;
}

/*!
 * \brief Affiche sur la console un block
 * \param block Le block à afficher
 * \param blockIndex L'index du block
 */
void printBlock(Block *block, int blockIndex){
    printf(YEL "[Block n°%d]\n", blockIndex);
    printf(RESET "| Date de creation : %s", block->timestamp);
    printf("| Hashcode predecessor : %s\n", block->hashCodePredecessor);
    printf("| Nonce : %d\n", block->nonce);
    printf("| Liste de transaction :\n");
    for (int i = 0; i < block->txList->txIndex; i++){
        printf("   | %d - %s\n", i, block->txList->tx[i]);
    }
    printf("| Hash merkle root : %s\n", block->hashMerkleTreeRoot);
    printf("| Hashcode du block : %s\n\n", block->hashCode);
}