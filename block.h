/// \file block.h
/// \author Tony De Freitas
/// \date 11 février 2021
/// \brief Définition de structures et prototypes

#ifndef PROJET_S4_BLOCK_H
#define PROJET_S4_BLOCK_H

#include <time.h>
#include <string.h>
#include "./Sha256/sha256.h"

#define MAXIMAL_TX 50

/*!
 * \brief Objet TxList.
 *
 * C'est une liste de string, soit une liste de transaction avec un int indiquant
 * l'index de la transaction courante.
 */
typedef struct TxList TxList;
struct TxList{
    int txIndex;
    char** tx;
};

/*!
 * \brief Objet Block.
 *
 * Contient un entier représentant la nonce, la date sous forme de string, un hash code, le hash code
 * du block précédent, le hash code du merkle tree et l'objet txList
 */
typedef struct Block Block;
struct Block{
    int nonce;
    char* timestamp;
    char hashCode[SHA256_BLOCK_SIZE*2+1];
    char hashCodePredecessor[SHA256_BLOCK_SIZE*2+1];
    char hashMerkleTreeRoot[SHA256_BLOCK_SIZE*2+1];
    TxList *txList;
};

Block *initBlock(const char hashCodePredecessor[SHA256_BLOCK_SIZE*2+1]);
Block *addTx(Block *block, char* tx);
void deleteBlock(Block *block);
void printBlock(Block *block, int blockIndex);
#endif //PROJET_S4_BLOCK_H