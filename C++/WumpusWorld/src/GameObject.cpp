#include "GameObject.h"
#include <iostream>
#include "World.h"

using namespace std;


GameObject::GameObject()
{
    //cout << "from GameObject constructor" << endl;
}

GameObject::~GameObject()
{
    //cout << "from GameObject destructor" << endl;
}


//string GameObject::setAtt(string att)
//{
//    string breeze = "b";
//    string stench = "s";
//    string both = "bs";
//    att = "0";
//    //att = att + stench;
//    return att;
//}



//******************************************************************
void GameObject::setFace(Facing f)
{
    this->facing = f;
}

//******************************************************************
Facing GameObject::showFace()
{
    return this->facing;
}

//******************************************************************
void GameObject::setObj(Obj x)
{
    this->obj = x;
}

//*****************************************************************
Obj GameObject::showObj()
{
    return this->obj;
}
