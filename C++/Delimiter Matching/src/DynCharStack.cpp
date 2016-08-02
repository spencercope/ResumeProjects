#include <iostream>
#include "DynCharStack.h"
using namespace std;

//**************************************************
// Destructor                                      *
// This function deletes every node in the list.   *
//**************************************************

DynCharStack::~DynCharStack()
{
   StackNode *nodePtr, *nextNode;

   // Position nodePtr at the top of the stack.
   nodePtr = top;

   // Traverse the list deleting each node.
   while (nodePtr != NULL)
   {
      nextNode = nodePtr->next;
      delete nodePtr;
      nodePtr = nextNode;
   }
}

//************************************************
// Member function push pushes the argument onto *
// the stack.                                    *
//************************************************

void DynCharStack::push(char delim)
{
   StackNode *newNode; // Pointer to a new node

   // Allocate a new node and store num there.
   newNode = new StackNode;
   newNode->value = delim;

   // If there are no nodes in the list
   // make newNode the first node.
   if (top == NULL)
   {
      newNode->next = NULL;
      top = newNode;
   }
   else  // Otherwise, insert NewNode before top.
   {
      newNode->next = top;
      top = newNode;
   }
}

//****************************************************
// Member function pop pops the value at the top     *
// of the stack off, and copies it into the variable *
// passed as an argument.                            *
//****************************************************

char DynCharStack::pop()
{
   char delim;
   StackNode *temp; // Temporary pointer

   // First make sure the stack isn't empty.
   if (isEmpty())
   {
      cout << "The stack is empty.\n";
      delim = 'q';
   }
   else  // pop value off top of stack
   {
      delim = top->value;
      temp = top->next;
      delete top;
      top = temp;
   }
   return delim;
}

//****************************************************
// Member function isEmpty returns true if the stack *
// is empty, or false otherwise.                     *
//****************************************************

bool DynCharStack::isEmpty()
{
   bool status;

   if (!top)
      status = true;
   else
      status = false;

   return status;
}
