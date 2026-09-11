//
// Created by william on 9/10/2026.
//

#include "Util.h"

#include <random>

std::mt19937 gen(std::random_device{}());

int Util::randomInt(const int min, const int max) {
    return std::uniform_int_distribution(min, max)(gen);
}
double Util::randomDouble(const double min, const double max) {
    return std::uniform_real_distribution(min, max)(gen);
}
double Util::randomDouble() {
    return std::uniform_real_distribution(0.0, 1.0)(gen);
}