/// \file generateTransaction.c
/// \author Sabrina Sikder
/// \date 1 avril 2021
/// \brief Génération de transaction aléatoire

#include "GenerateTransactions.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

/*!
 * \brief Permet de générer autant de transaction aléatoire que le spécifie le paramètre txNumber
 * \param user Un tableau de pointeur d'user
 * \param usersNumber Un entier représentant le nombre d'utilisateur
 * \param txNumber Un entier représentant le nombre de transaction
 * \param queue Une queue permettant de stocker les transactions créées
 */
void generateRandomTransaction(User **user, int usersNumber, int txNumber, Queue *queue){
    int n1,n2;
    long int amount;

    srand((queueSize(queue)+1) * time(NULL));

    for (int i=0; i < txNumber; i++){
        n1 = rand() % usersNumber; //Indice aléatoire pour obtenir un utilisateur dans le tableau
        n2 = rand() % usersNumber; //Indice aléatoire pour obtenir un utilisateur dans le tableau
        if(user[n1]->bankAccount > 1) {
            amount = rand() % (user[n1]->bankAccount) + 1;
            while (n1 == n2) { //Permet de ne pas avoir deux fois le même indice
                n2 = rand() % usersNumber;
            }
            user[n1]->bankAccount -= amount;
            user[n2]->bankAccount += amount;
            queuePush(queue, generateChar(user[n1]->name, user[n2]->name, amount));
        }
        else{
            i--;
        }
    }
}

/*!
 * \brief Construit une chaîne de caractère qui sera une transaction
 * \param user1 L'utilisateur qui donne de l'argent
 * \param user2 L'utilisateur qui reçoit de l'argent
 * \param amount Le montant échangé
 * \return Une chaîne de caractère étant la transaction effectuée
 */
char* generateChar(char* user1,char* user2, long int amount){
    char* str= malloc(100*sizeof (char));
    char buffer [33];
    sprintf(buffer, "%ld", amount);
    strcpy(str, user1);
    strcat(str, " donne ");
    strcat(str, buffer);
    strcat(str," satoBnb ");
    strcat(str, "a ");
    strcat(str, user2);
    return str;
}