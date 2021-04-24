//
// Created by Sabrina on 22/04/2021.
//

#include "GenerateTransactions.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

char* users[10]={"Sabrina", "Simran","Tony","Clement","Alice","Bob","Chloe","Emma","Laura","Gerard"};
// peut etre faire une fonction add/delete user pour les rajouter dans la liste

void generateTransactions(Block *block){
    int TxNumber =genRandomTxNumber();
    char* user2;
    char* user1;
    srand(time(0));

    for (int i=0;i<TxNumber;i++){
        int n1=rand()%10;
        int n2=rand()%10;
        int amount=rand();
        while(n1==n2)
        {
            n2=rand()%10;
        }
       user1  =users[n1];
        user2  =users[n2];
        addTx(block, generateChar(user1,user2,amount));
        printf("%s\n",block->txList->tx[i]);
        }
    }



// génère un nombre entre 1 et 10
int genRandomTxNumber(){
    srand(time(NULL));
    int TxNumber= (rand()%10)+1;
    return TxNumber;
}

char* generateChar(char* user1,char* user2,int amount){
    char* str= malloc(150*sizeof (char));
    char buffer [33];
    itoa(amount,buffer,10);
    strcpy(str, user1);
    strcat(str, " donne ");
    strcat(str, buffer);
    strcat(str," BNB ");
    strcat(str, "a ");
    strcat(str, user2);
    return str;
}






