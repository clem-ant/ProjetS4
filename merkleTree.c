/// \file merkleTree.c
/// \author Sabrina Sikder
/// \date 15 février 2021
/// \brief Calcul du hash du merkle tree d'un block

#include <stdlib.h>
#include <string.h>
#include "block.h"
#include "./Sha256/sha256_utils.h"

/*!
 * \brief Initialise un tableau de chaîne de caractère contenant les hashs des transactions du block
 * \param block Le block où sont stockés les transactions
 * \return Un tableau de chaîne de caractère contenant les hashs des transactions du block
 */
char** initTransactionHashing (const Block *block) {
    int bufferSize = SHA256_BLOCK_SIZE;
    char hashRes[bufferSize*2 + 1];

    char** transactionHashList = malloc((block->txList->txIndex + 1) * sizeof(char*));
    for(int i = 0; i < block->txList->txIndex + 1; i++){
        transactionHashList[i] = malloc((bufferSize*2+1) * sizeof(char));
    }

    for(int i = 0; i < block->txList->txIndex; i++){
        sha256ofString((BYTE*) block->txList->tx[i], hashRes);
        strcpy(transactionHashList[i], hashRes);
    }

    return transactionHashList;
}

/*!
 * \brief Supprime le tableau de string passé en paramètre
 * \param transactionHashList Le tableau de string à supprimer
 * \param size Un entier représentant la taille du tableau
 */
void deleteTransactionHashList(char **transactionHashList, int size){
    for(int i = 0; i < size ; i++){
        free(transactionHashList[i]);
    }
    free(transactionHashList);
}

/*!
 * \brief Fonction de concaténation
 * \param firstHash Un string étant le premier hash
 * \param secondHash Un string étant le deuxième hash
 * \param hashConcatenated Un string où va être stocké la concaténation
 * \return Un string contenant la concaténation des deux hash passés en paramètre
 */
char* concatenateHash(char* firstHash, char* secondHash, char* hashConcatenated){
    strcpy(hashConcatenated,firstHash);
    strcat(hashConcatenated,secondHash);
    return hashConcatenated;
}

/*!
 * \brief Calcul du hash du merkle root d'un block
 * \param block Le block où doit être effectué le calcul
 * \return Une chaîne de caractère représentant le hash du merkle root
 */
char* getMerkleRoot(const Block *block){
    int bufferSize = SHA256_BLOCK_SIZE;
    char hashRes[bufferSize*2 + 1];
    char *hashMerkleRoot = malloc((bufferSize*2+1) * sizeof(char));
    char** transactionHashList = initTransactionHashing(block);
    sha256ofString((BYTE*) transactionHashList[0], hashRes); //S'il n'y a qu'une seule transaction

    for(int i = block->txList->txIndex; i > 1; i /= 2){
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
    deleteTransactionHashList(transactionHashList, block->txList->txIndex + 1);
    return hashMerkleRoot;
}

/*!
 * \brief Permet de stocker le hash du merkle root dans le block passé en paramètre
 * \param block Le block où doit être calculé le hash du merkle root
 */
void merkleTree(Block *block){
    char* merkleRoot = getMerkleRoot(block);
    strcpy(block->hashMerkleTreeRoot, merkleRoot);
    free(merkleRoot);
}