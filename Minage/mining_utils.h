/// \file mining_utils.h
/// \author Tony De Freitas
/// \date 15 février 2021
/// \brief Prototypes

#ifndef PROJET_S4_MINING_UTILS_H
#define PROJET_S4_MINING_UTILS_H

#include "../block.h"

char* blockItemsToString(const Block *block, char* blockItems);
int numberCharBlock(const Block *block);

#endif //PROJET_S4_MINING_UTILS_H
