#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <stack>
#include "DynCharStack.h"

using namespace std;

int main()
{
    DynCharStack charStack;
    stack <char> StlVersion;

    char catchChar = 'q';
    ifstream inputFile;
    string fileName;

    int numLines = 0;
    int errorCount = 0;
    int userChoice = 0;

    char leftBrace = '{';
    char rightBrace = '}';
    char leftBracket = '[';
    char rightBracket = ']';
    char leftParen = '(';
    char rightParen = ')';

    string line;

    cout << "What is the name of the file you wish to check?" << endl;                             //get user input for file name
    cin >> fileName;
    cout << "\nPress '1' to parse file with my dynamic character stack." << endl;
    cout << "Press '2' to parse file with the Standard Template Library." << endl;
    cin >> userChoice;

    if (userChoice == 1)                                                                    //START OF MY DYNCHARSTACK
    {
        inputFile.open(fileName.c_str());                                                   //open file
        if (inputFile.is_open())
        {
            while(getline(inputFile, line))                                                 //get one line at a time
            {
                numLines++;                                                                 //line counter
                for (int i = 0; i < line.length(); i++)                                     //loop through line
                {
                    if(line[i] == leftBrace || line[i] == leftBracket || line[i] == leftParen)      //push these three chars
                    {
                        charStack.push(line[i]);
                    }
                    else if (line[i] == rightBrace)                                         //right brace
                    {
                        if (charStack.pop() == leftBrace)
                        {
                            //popped leftBrace
                        }
                        else
                        {
                            cout << "\nERROR: Mismatched '" << line[i] << "' at line ";       //error message
                        cout << numLines << "," << " character " << i << "." << endl;
                            exit (0);
                        }
                    }
                    else if (line[i] == rightBracket)                                       //right bracket
                    {
                        if (charStack.pop() == leftBracket)
                        {
                            //popped leftBracket
                        }
                        else
                        {
                            cout << "\nERROR: Mismatched '" << line[i] << "' at line ";       //error message
                        cout << numLines << "," << " character " << i << "." << endl;
                            exit (0);
                        }
                    }
                    else if (line[i] == rightParen)                                         //right parentheses
                    {
                        if (charStack.pop() == leftParen)
                        {
                            //popped leftParen
                        }
                        else
                        {
                            cout << "\nERROR: Mismatched '" << line[i] << "' at line ";       //error message
                        cout << numLines << "," << " character " << i << "." << endl;
                            exit (0);
                        }
                    }
                }
            }
        }
        else                                                                                //if the file name is incorrect, the file will not be open, this is my error checking
        {
            cout << "\nThat file does not exist.  Please run again.\n";
            exit(0);
        }

        if(!charStack.isEmpty())
        {
            while (!charStack.isEmpty())                                                    //if nothing left on stack, error and output stack
            {
                errorCount++;
                cout << "\nERROR " << errorCount << ": ";
                catchChar = charStack.pop();
                cout << "'" << catchChar << "' is still left on the stack.\n" << endl;
            }
        }
        else if (charStack.isEmpty())                                                            //if nothing left on stack
        {
            cout << "\nFinished parsing file.  There are no delimiter errors.  Good job.\n";
        }
    }


    else if (userChoice == 2)                                               //START OF STL VERSION
    {
        inputFile.open(fileName.c_str());
        while(getline(inputFile, line))                                                     //get one line at a time
        {
            numLines++;                                                                 //line counter
            for (int i = 0; i < line.length(); i++)                                     //loop through line
            {
                if(line[i] == leftBrace || line[i] == leftBracket || line[i] == leftParen)      //push these three chars
                {
                    StlVersion.push(line[i]);
                }
                else if (line[i] == rightBrace)                                         //right brace
                {
                    if (StlVersion.top() == leftBrace)
                    {
                        StlVersion.pop();
                    }
                    else
                    {
                        cout << "\nERROR: Mismatched '" << line[i] << "' at line ";       //error message
                        cout << numLines << "," << " character " << i << "." << endl;
                        exit (0);
                    }
                }
                else if (line[i] == rightBracket)                                       //right bracket
                {
                    if (StlVersion.top() == leftBracket)
                    {
                        StlVersion.pop();
                    }
                    else
                    {
                        cout << "\nERROR: Mismatched '" << line[i] << "' at line ";       //error message
                        cout << numLines << "," << " character " << i << "." << endl;
                        exit (0);
                    }
                }
                else if (line[i] == rightParen)                                         //right parentheses
                {
                    if (StlVersion.top() == leftParen)
                    {
                        StlVersion.pop();
                    }
                    else
                    {
                        cout << "\nERROR: Mismatched '" << line[i] << "' at line ";       //error message
                        cout << numLines << "," << " character " << i << "." << endl;
                        exit (0);
                    }
                }
            }
        }

        if(!StlVersion.empty())
        {
            while (!StlVersion.empty())                                                    //if nothing left on stack, error and output stack
            {
                errorCount++;
                cout << "\nERROR " << errorCount << ": ";

                cout << "'" << StlVersion.top() << "' is still left on the stack.\n" << endl;
                StlVersion.pop();
            }
        }
        else if (StlVersion.empty())                                                            //if nothing left on stack
        {
            cout << "\nFinished parsing file.  There are no delimiter errors.  Good job.\n";
        }
    }                                                                   //END OF STL VERSION
    else                                                                                    //error checking for userChoice
    {
        cout << "\nPlease run again and make sure to enter a 1 or a 2.\n";
        exit(0);
    }


    return 0;
}
