/// \file cheaterTransaction.c
/// \author Sabrina Sikder
/// \date 1 avril 2021
/// \brief Suppression d'une transaction d'un block avec re-calcul du merkle root et des hashs des blocks

#include "cheaterTransaction.h"
#include "../merkleTree.h"
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include "../Minage/mining.h"

#define RED   "\x1B[31m"
#define GRN   "\x1B[32m"
#define RESET "\x1B[0m"

/*!
 * \brief Supprime une transaction d'un block, re-calcul le hash de merkle root, les hashs des blocks, change les
 * hash code predecessor et indique le durée d'exécution de cette fonction
 * \param blockchain La blockchain où doit être supprimé une transaction d'un block
 * \param blockIndex L'index du block où doit être supprimé la transaction
 * \param txIndex L'index de la transaction à supprimer
 */
void cheaterTransaction(Blockchain *blockchain, int blockIndex, int txIndex) {
    clock_t t1=clock();
    double time_spent=0.0;

    printf(RED "[Cheater de transaction]" RESET " - Suppression de la transaction %d du block %d.\n", txIndex, blockIndex);
    printf("Operation en cours ...\n");

    free(blockchain->blocks[blockIndex]->txList->tx[txIndex]);
    int txNumber = blockchain->blocks[blockIndex]->txList->txIndex;
    for (int i = txIndex; i < txNumber - 1; i++){
        blockchain->blocks[blockIndex]->txList->tx[i] = blockchain->blocks[blockIndex]->txList->tx[i+1];
    }
    blockchain->blocks[blockIndex]->txList->tx[txNumber - 1] = NULL;
    blockchain->blocks[blockIndex]->txList->txIndex--;

    merkleTree(blockchain->blocks[blockIndex]);

    for (int i = blockIndex; i > 0 && i < blockchain->blockCursor; ++i) {
        blockchain->blocks[i]->nonce=0;
        strcpy(blockchain->blocks[i]->hashCodePredecessor,blockchain->blocks[i-1]->hashCode);
        mining(blockchain->blocks[i], blockchain->difficulty);
    }

    clock_t t2=clock();
    time_spent += (double)(t2- t1) / CLOCKS_PER_SEC;
    printf(GRN "[Cheater de transaction] - Operation terminee\n");
    printf(RESET "Temps d'execution : %f seconde(s)\n\n", time_spent);
}