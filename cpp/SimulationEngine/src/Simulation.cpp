#include "../src/Simulation.h"

#include <chrono>
#include <random>
#include <iostream>
using namespace std;

extern "C" {

    __declspec(dllexport)
    Stats* exportStat(const int* stats) {
        for (int i = 0; i < 16; i++) {
            cout << stats[i] << endl;
        }
        return new Stats(stats);
    }
    __declspec(dllexport)
    void test() {
    }
}