#ifndef WORLD_H
#define WORLD_H
#include <iostream>
#include "GameObject.h"



const int arrSize = 5;


class World
{
     GameObject objArr[arrSize][arrSize];
     int xx, yy;

public:
    World(string);
    int Action(char);
    void Display();

    void setEntry(int s, int t, Facing f);
    void showEntry(int &x, int &y);
    void setLocation(int &, int &);
    void setBreeze(int x, int y);

    string wumpus = "W";
    string pit = "P";
    string gold= "G";
    string mpty = "0";

};

#endif // WORLD_H
