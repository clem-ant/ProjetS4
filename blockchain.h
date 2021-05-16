/// \file blockchain.h
/// \author Tony De Freitas
/// \date 11 février 2021
/// \brief Définition de structure et prototypes

#ifndef PROJET_S4_BLOCKCHAIN_H
#define PROJET_S4_BLOCKCHAIN_H
#include "block.h"
#define MAXIMAL_BLOCK 100

/*!
 * \brief Objet Blockchain.
 *
 * Contient un entier pour la difficulté, le curseur de block, le nombre de block et le nombre de transaction.
 * Contient aussi un tableau de block.
 */
typedef struct Blockchain Blockchain;
struct Blockchain{
    int difficulty;
    int blockCursor;
    int blockNumber;
    int txNumber;
    Block **blocks;
};

Blockchain *initBlockchain(int difficulty, int blockNumber, int txNumber);
Block *addBlock(Blockchain *blockchain);
void deleteBlockchain(Blockchain *blockchain);
Block *createGenesis(Blockchain *blockchain);
void printBlockchain(Blockchain * blockchain);
#endif //PROJET_S4_BLOCKCHAIN_H
