//
// Created by Sabrina on 22/04/2021.
//

#include "GenerateTransactions.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include "./BCB/user.h"
#include "./utils/queue.h"


// peut etre faire une fonction add/delete user pour les rajouter dans la liste

void generateRandomTransaction(User **user, int usersNumber, Queue *queue){
    int n1,n2,amount;

    srand((queueSize(queue)+1) * time(NULL));
    int txNumber = (rand() % 10) + 1;

    for (int i=0; i < txNumber; i++){
        n1 = rand() % usersNumber;
        n2 = rand() % usersNumber;
        amount= rand();
        while(n1==n2)
        {
            n2=rand() % usersNumber;
        }
        queuePush(queue, generateChar(user[n1]->name, user[n2]->name, amount));
    }
}


char* generateChar(char* user1,char* user2,int amount){
    char* str= malloc(150*sizeof (char));
    char buffer [33];
    sprintf(buffer, "%d", amount);
    strcpy(str, user1);
    strcat(str, " donne ");
    strcat(str, buffer);
    strcat(str," BNB ");
    strcat(str, "a ");
    strcat(str, user2);
    return str;
}






