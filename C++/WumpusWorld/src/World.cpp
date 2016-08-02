
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <iomanip>
#include "World.h"

using namespace std;

//**********************************************************************
void World::Display()                                                               //Display  the game board
{
    for (int i = 0; i < arrSize; i++)
    {
        cout << endl << "Row" << i << ": ";                                         //formatting for game board
        for (int j = 0; j < arrSize; j++)                                           //showObj
        {
            if (this->objArr[i][j].showObj() == EMPTY)                              //EMPTY
            {
                cout << setw(3) <<  mpty;
                //if (this->objArr[i+1][j].showObj() == WUMPUS)
                //{
                    //cout << setw(3) << this->objArr[i][j].setAtt(mpty);
                //}
            }
            else if (this->objArr[i][j].showObj() == WUMPUS)                        //display for WUMPUS
            {
                cout << setw(3) << wumpus;
                //cout << this->objArr[i][j].setAtt(wumpus);
            }
            else if (this->objArr[i][j].showObj() == PIT)                           //display fof PIT
            {
                cout << setw(3) << pit;
            }
            else if (this->objArr[i][j].showObj() == GOLD)                          //display for GOLD
            {
                cout << setw(3)  << gold;
            }

            else if (this->objArr[i][j].showObj() == ME)                            //display for ME
            {                                                                       //chars to display which way I'm facing
               if (this->objArr[i][j].showFace() == UP)                             //UP
               {
                   cout << setw(3)  << "^";
               }
               if (this->objArr[i][j].showFace() == DOWN)                           //DOWN
               {
                   cout << setw(3) << ".";
               }
               if (this->objArr[i][j].showFace() == LEFT)                           //LEFT
               {
                   cout << setw(3) << "<";
               }
               if (this->objArr[i][j].showFace() == RIGHT)                          //RIGHT
               {
                   cout << setw(3) << ">";
               }
            }

        }
    }

}


//************************************************************************
World::World(string name)                                            //World constructor that passes string
{

    cout << "\nProceed with caution " << name << "..." << endl;

    for (int i = 0; i < arrSize; i++)
    {
        for (int j = 0; j < arrSize; j++)
        {
            this->objArr[i][j].setObj(EMPTY);
        }
    }


    srand(time(0));                                                 //seed time

    int a = rand() % 5;
    int b = rand() % 5;
    this->objArr[a][b].setObj(PIT);                                 //randomly place pit

    a = rand() % 5;
    b = rand() % 5;
    this->objArr[a][b].setObj(PIT);                                 //randomly place pit

    a = rand() % 5;
    b = rand() % 5;
    this->objArr[a][b].setObj(PIT);                                 //randomly place pit

    a = rand() % 5;
    b = rand() % 5;
    this->objArr[a][b].setObj(PIT);                                 //randomly place pit

    a = rand() % 5;
    b = rand() % 5;
    this->objArr[a][b].setObj(PIT);                                 //randomly place pit

    a = rand() % 5;
    b = rand() % 5;
    this->objArr[a][b].setObj(PIT);                                 //randomly place pit

    a = rand() % 5;
    b = rand() % 5;
    this->objArr[a][b].setObj(WUMPUS);                              //randomly place WUMPUS

    a = rand() % 5;
    b = rand() % 5;
    this->objArr[a][b].setObj(GOLD);                                //randomly place gold


    xx = rand() % 5;                                                //random generator for ME
    yy = rand() % 5;

    this->objArr[xx][yy].setObj(ME);                                //randomly set ME
    this->objArr[xx][yy].setFace(RIGHT);


}




//**************************************************************************
void World::showEntry(int &x, int &y)                                           //showEntry
{
    x = xx;
    y = yy;
}

//*************************************************************************
void World::setEntry(int a, int b, Facing f)                                    //setEntry
{
    this->objArr[a][b].setFace(f);
}

//*************************************************************************
void World::setLocation(int &s, int &c)                                       //setLocation
{
    xx = s;
    yy = c;

    this->objArr[xx][yy].setObj(ME);
}



//*************************************************************************
int World::Action(char input)                                           //each action that the player takes
{


    int k = 0;
    int l = 0;
    showEntry(k,l);

    if (input == 'L')                                                   //change facing to left
    {
       setEntry(k, l, LEFT);
    }
    if (input == 'R')                                                   //change facing to right
    {
       setEntry(k, l, RIGHT);
    }
    if (input == 'U')                                                   //change facing to up
    {
       setEntry(k, l, UP);
    }
    if (input == 'D')                                                   //change facing to down
    {
       setEntry(k, l, DOWN);
    }



    if (input == 'F')                                                               // when the player moves forward
    {
        if ( l == 0 && this->objArr[k][l].showFace() == LEFT)                                       //cannot move left
        {
            cout << "\nPlease stay on the board.  You cannot move further left.\n" << endl;
            return 0;
        }
        else if ( l == 4 && this->objArr[k][l].showFace() == RIGHT)                                 //cannot move right
        {
            cout << "\nPlease stay on the board.  You cannot move further right.\n" << endl;
            return 0;
        }
        else if ( k == 0 && this->objArr[k][l].showFace() == UP )                                   //cannot move up
        {
            cout << "\nPlease stay on the board.  You cannot move further up.\n" << endl;
            return 0;
        }
        else if ( k == 4 && this->objArr[k][l].showFace() == DOWN)                                  //cannot move down
        {
            cout << "\nPlease stay on the board.  You cannot move further down.\n" << endl;
            return 0;
        }


        if (this->objArr[k][l].showFace() == RIGHT && this->objArr[k][l+1].showObj() == PIT)           //When trying to move RIGHT into PIT
        {
            cout << "\nYou have fallen into a pit, and died.  Game Over.\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == LEFT && this->objArr[k][l-1].showObj() == PIT)      //When trying to move LEFT into PIT
        {
            cout << "\nYou have fallen into a pit, and died.  Game Over.\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == DOWN && this->objArr[k+1][l].showObj() == PIT)      //When trying to move DOWN into PIT
        {
            cout << "\nYou have fallen into a pit, and died.  Game Over.\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == UP && this->objArr[k-1][l].showObj() == PIT)        //When trying to move UP into PIT
        {
            cout << "\nYou have fallen into a pit, and died.  Game Over.\n" << endl;
            exit(0);
        }



        if (this->objArr[k][l].showFace() == RIGHT && this->objArr[k][l+1].showObj() == WUMPUS)         //When trying to move RIGHT into WUMPUS
        {
            cout << "\nYou ran into the WUMPUS, and died.  Game Over.\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == LEFT && this->objArr[k][l-1].showObj() == WUMPUS)      //When trying to move LEFT into WUMPUS
        {
            cout << "\nYou ran into the WUMPUS, and died.  Game Over.\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == DOWN && this->objArr[k+1][l].showObj() == WUMPUS)      //When trying to move DOWN into WUMPUS
        {
            cout << "\nYou ran into the WUMPUS, and died.  Game Over.\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == UP && this->objArr[k-1][l].showObj() == WUMPUS)        //When trying to move UP into WUMPUS
        {
            cout << "\nYou ran into the WUMPUS, and died.  Game Over.\n" << endl;
            exit(0);
        }



        if (this->objArr[k][l].showFace() == RIGHT && this->objArr[k][l+1].showObj() == GOLD)                   //Please don't step on the gold
        {
            cout << "\nPlease don't step on the gold, press (P) to pick it up (and win!).\n" << endl;
            return 0;
        }
        else if (this->objArr[k][l].showFace() == LEFT && this->objArr[k][l-1].showObj() == GOLD)               //Please don't step on the gold
        {
            cout << "\nPlease don't step on the gold, press (P) to pick it up (and win!).\n" << endl;
            return 0;
        }
        else if (this->objArr[k][l].showFace() == DOWN && this->objArr[k+1][l].showObj() == GOLD)               //Please don't step on the gold
        {
            cout << "\nPlease don't step on the gold, press (P) to pick it up (and win!)." << endl;
            return 0;
        }
        else if (this->objArr[k][l].showFace() == UP && this->objArr[k-1][l].showObj() == GOLD)                 //Please don't step on the gold
        {
            cout << "\nPlease don't step on the gold, press (P) to pick it up (and win!).\n" << endl;
            return 0;
        }




        if (this->objArr[k][l].showFace() == LEFT)                                      //Move left
        {
            if (this->objArr[k][l-1].showFace() == EMPTY)
            {
                this->objArr[xx][yy].setObj(EMPTY);                                     //empty old space
                setLocation(k, --l);
                this->objArr[xx][yy].setFace(LEFT);                                     //keep the facing direction
            }
        }
        else if (this->objArr[k][l].showFace() == RIGHT)                                //move right
        {
            if (this->objArr[k][l+1].showFace() == EMPTY)
            {
                this->objArr[xx][yy].setObj(EMPTY);                                     //make old space empty
                setLocation(k, ++l);
                this->objArr[xx][yy].setFace(RIGHT);                                    //keep the facing direction
            }
        }

        else if (this->objArr[k][l].showFace() == UP)                                   //move up
        {
            if (this->objArr[k-1][l].showFace() == EMPTY)
            {
                this->objArr[xx][yy].setObj(EMPTY);
                setLocation(--k, l);
                this->objArr[xx][yy].setFace(UP);
            }
        }
        else if (this->objArr[k][l].showFace() == DOWN)                                 //move down
        {
            if (this->objArr[k+1][l].showFace() == EMPTY)
            {
                this->objArr[xx][yy].setObj(EMPTY);
                setLocation(++k, l);
                this->objArr[xx][yy].setFace(DOWN);
            }
        }


    }



    if (input == 'P')
    {
        if (this->objArr[k][l].showFace() == RIGHT && this->objArr[k][l+1].showObj() == GOLD)          //When GOLD is to the RIGHT
        {
            cout << "\n******You picked up the gold.  You win!*******\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == LEFT && this->objArr[k][l-1].showObj() == GOLD)      //When GOLD is to the LEFT
        {
            cout << "\n******You picked up the gold.  You win!*******\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == DOWN && this->objArr[k+1][l].showObj() == GOLD)      //When GOLD is DOWN
        {
            cout << "\n******You picked up the gold.  You win!*******\n" << endl;
            exit(0);
        }
        else if (this->objArr[k][l].showFace() == UP && this->objArr[k-1][l].showObj() == GOLD)        //When GOLD is UP
        {
            cout << "\n******You picked up the gold.  You win!*******\n" << endl;
            exit(0);

        }
    }
    if (input == 'S')                                                                                   //Shoot the WUMPUS
    {
        if (this->objArr[k][l].showFace() == RIGHT && this->objArr[k][l+1].showObj() == WUMPUS)         //When WUMPUS is to the RIGHT
        {
            cout << "\nYou shot the Wumpus!" << endl;
            this->objArr[k][++l].setObj(EMPTY);
            return 0;
        }
        else if (this->objArr[k][l].showFace() == LEFT && this->objArr[k][l-1].showObj() == WUMPUS)      //When WUMPUS is to the LEFT
        {
            cout << "\nYou shot the Wumpus!" << endl;
            this->objArr[k][--l].setObj(EMPTY);
            return 0;
        }
        else if (this->objArr[k][l].showFace() == DOWN && this->objArr[k+1][l].showObj() == WUMPUS)      //When WUMPUS is DOWN
        {
            cout << "\nYou shot the Wumpus!" << endl;
            this->objArr[++k][l].setObj(EMPTY);
            return 0;
        }
        else if (this->objArr[k][l].showFace() == UP && this->objArr[k-1][l].showObj() == WUMPUS)        //When WUMPUS is UP
        {
            cout << "\nYou shot the Wumpus!" << endl;
            this->objArr[--k][l].setObj(EMPTY);
            return 0;
        }


    }


}






//
//World::~World()
//{
//    //dtor
//}
