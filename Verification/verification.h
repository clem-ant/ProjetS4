/// \file verification.h
/// \author Tony De Freitas
/// \date 1 avril 2021
/// \brief Prototypes

#ifndef PROJET_S4_VERIFICATION_H
#define PROJET_S4_VERIFICATION_H

#include <stdbool.h>
#include "../blockchain.h"

bool blockchainIntegrity(const Blockchain *blockchain);

#endif //PROJET_S4_VERIFICATION_H
