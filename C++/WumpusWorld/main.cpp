#include <iostream>
#include <string>
#include "World.h"

using namespace std;

int main()
{
    string name;

    cout << "You are about to enter the world of the calamitous WUMPUS,";
    cout << " beware."  << endl << "\nIf you would like to enter WUMPUS world, ";
    cout << "please enter your first name." << endl;
    cin >> name;


    cout << "\n**************Directions****************\n" << endl;
    cout << "Press L to face left. (<)" << endl;                                        //game dirrections
    cout << "Press R to face right. (>)" << endl;
    cout << "Press U to face up. (^)" << endl;
    cout << "Press D to face down. (.)" << endl;
    cout << "Press F to move forward." << endl;
    cout << "You may pick up (P) the gold when one spot away and facing the gold." << endl;
    cout << "You may shoot (S) the WUMPUS when one spot away and facing the WUMPUS.\n" << endl;


    World *world = new World(name);                             //create instance of World
    world->Display();                                       //call display method


    char mInput = ' ';
    bool quit = 0;
    int status;

    while (!quit)                                                       //loop for the action of the game
    {
        //LEFT, RIGHT, FRWRD, SHOOT, PICKUP
        cout << "\n\nType L, R, U, D, F, S, P, or Q" << endl;
        cin >> mInput;
        mInput = toupper(mInput);                                       //convert all to uppercase


        if(mInput == 'Q')break;
        status = world->Action(mInput);
        world->Display();

    }


    cout << "Game Over" << endl;


    return 0;
}
