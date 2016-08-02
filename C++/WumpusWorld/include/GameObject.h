#ifndef GAMEOBJECT_H
#define GAMEOBJECT_H
#include <iostream>
#include <string.h>
using namespace std;

enum Facing {LEFT, RIGHT, UP, DOWN, FORWARD};
enum Obj {EMPTY, WUMPUS, ME, PIT, GOLD};
enum Attribute {BREEZE, STENCH, BOTH};

class GameObject
{
    string name;


    Obj obj;
    Facing facing;

public:
    GameObject();
    ~GameObject();
    void setObj(Obj);
    Obj  showObj();
    void setFace(Facing);
    Facing showFace();
    string setAtt(string);

};

#endif // GAMEOBJECT_H
