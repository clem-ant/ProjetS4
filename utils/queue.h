#ifndef PROJET_S4_QUEUE_H
#define PROJET_S4_QUEUE_H

#include <stdio.h>
#include <stdbool.h>


typedef struct s_queue Queue;
typedef Queue *ptrQueue;

Queue *createQueue();
Queue *queuePush(Queue *q, void *v);
void deleteQueue(ptrQueue *q);
Queue *queuePop(Queue *q);
void *queueTop(Queue *q);
bool queueEmpty(Queue *q);
unsigned int queueSize(Queue *q);

#endif //PROJET_S4_QUEUE_H