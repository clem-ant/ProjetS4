/// \file main.c
/// \author Tony De Freitas
/// \date 11 février 2021
/// \brief Programme principal permettant d'exécuter en fonction des paramètres plusieurs fonctions

/*!
 * \mainpage Bonoland Central Bank - Blockchain avec simulation de transaction
 */

#include <stdio.h>
#include <stdlib.h>
#include "block.h"
#include "blockchain.h"
#include "./Verification/verification.h"
#include "./Cheaters/cheaterTransaction.h"
#include "./Cheaters/cheaterBlocks.h"
#include "./BCB/bcb.h"

#define RED   "\x1B[31m"
#define GRN   "\x1B[32m"
#define YEL   "\x1B[33m"
#define RESET "\x1B[0m"

void helpMenu(){
    printf("NAME\n");
    printf("\tBonoboland Central Bank\n");
    printf("SYNOPSIS\n");
    printf("\tbcb [DIFFICULTY] [NB_BLOCK] [NB_TX] [NB_USER]... [OPTION]...\n");
    printf("DESCRIPTION\n");
    printf("-c [INT] [INT]\n\tActivation du cheater, premier entier obligatoire pour l'index du block, deuxieme "
           "entier optionnel pour le numero de transaction\n");
}

int main(int argc, char *argv[]) {
    int difficulty, nbBlock, nbTx, nbUser, blockIndex = 0, txIndex = 0;
    char *endPtr;

    if (argc > 4) {
        difficulty = (int) strtol(argv[1], &endPtr, 10);
        if(endPtr == argv[1]){
            fprintf(stderr, RED "[ERREUR] - La difficulte n'est pas un entier !\n" RESET);
            return EXIT_FAILURE;
        }

        nbBlock = (int) strtol(argv[2], &endPtr, 10);
        if(endPtr == argv[2]){
            fprintf(stderr, RED "[ERREUR] - Le nombre de block n'est pas un entier !\n" RESET);
            return EXIT_FAILURE;
        }

        nbTx = (int) strtol(argv[3], &endPtr, 10);
        if(endPtr == argv[3]){
            fprintf(stderr, RED "[ERREUR] - La nombre de transaction n'est pas un entier !\n" RESET);
            return EXIT_FAILURE;
        }

        nbUser = (int) strtol(argv[4], &endPtr, 10);
        if(endPtr == argv[4]){
            fprintf(stderr, RED "[ERREUR] - La nombre d'utilisateur n'est pas un entier !\n" RESET);
            return EXIT_FAILURE;
        }
    }
    else{
        fprintf(stderr, RED "[ERREUR] - La syntaxe de la commande n'est pas respecte !\n" RESET);
        helpMenu();
        return EXIT_FAILURE;
    }

    Bcb *bcb = initBcb(nbUser, nbTx, nbBlock, difficulty);
    bcbStarting(bcb);

    if (argc > 6){
        if(strcmp(argv[5], "-c") == 0){
            blockIndex = (int) strtol(argv[6], &endPtr, 10);
            if(endPtr == argv[6]){
                fprintf(stderr, RED "[ERREUR] - L'index de block pour le cheater n'est pas un entier !\n" RESET);
                deleteBcb(bcb);
                return EXIT_FAILURE;
            }
            else if(blockIndex >= bcb->blockchain->blockCursor){
                fprintf(stderr, RED "[ERREUR] - L'index de block pour le cheater est supérieur ou égale au "
                                    "nombre total de block !\n" RESET "Suppression par defaut du dernier block...\n");
                blockIndex = bcb->blockchain->blockCursor - 1;
            }
        }
        else{
            fprintf(stderr, RED "[ERREUR] - La syntaxe de la commande n'est pas respecte !\n" RESET);
            helpMenu();
            deleteBcb(bcb);
            return EXIT_FAILURE;
        }

        if(argc > 7){
            txIndex = (int) strtol(argv[7], &endPtr, 10);
            if(endPtr == argv[7]){
                fprintf(stderr, RED "[ERREUR] - L'index de la transaction pour le cheater n'est pas un entier !\n" RESET);
                deleteBcb(bcb);
                return EXIT_FAILURE;
            }
            else if(txIndex < 0 || txIndex >= bcb->blockchain->blocks[blockIndex]->txList->txIndex){
                fprintf(stderr, RED "[ERREUR] - L'index de la transaction pour le cheater est inferieur a 0 ou "
                                    "est superieur ou egale au nombre de transaction du block !\n" RESET
                                    "Suppression par defaut de la derniere transaction...\n");
                txIndex = bcb->blockchain->blocks[blockIndex]->txList->txIndex - 1;
            }
            cheaterTransaction(bcb->blockchain, blockIndex, txIndex);
            printBlockchain(bcb->blockchain);
        }
        else{
            cheaterBlock(bcb->blockchain, blockIndex);
            printBlockchain(bcb->blockchain);
        }
    }

    blockchainIntegrity(bcb->blockchain);
    deleteBcb(bcb);
    return EXIT_SUCCESS;
}