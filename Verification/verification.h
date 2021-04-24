//
// Created by TONY DE FREITAS on 24/04/2021.
//

#ifndef PROJET_S4_VERIFICATION_H
#define PROJET_S4_VERIFICATION_H

bool isGenesis(Block *block);
bool isMerkleRoot(Block *block);
bool isHashCode(Block *block);
bool isWellChained(Blockchain *blockchain);

#endif //PROJET_S4_VERIFICATION_H
