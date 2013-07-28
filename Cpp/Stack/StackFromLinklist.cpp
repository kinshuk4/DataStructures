#include <iostream>
using namespace std;


struct node
{
       int info;
       struct node *next;
};
struct node *top;

int empty()
{
    return((top == NULL)? 1:0);
}

void push(int n)
{
     struct node *p;
     p=new node;
     if(p!=NULL)
     {
                p->info=n;
                p->next=top;
                top=p;
     }
     else
         cout<<"Not inserted,No memory available";
}

int pop()
{
    struct node *temp;
    int x;
    x=top->info;
    temp=top;
    top=top->next;
    free(temp);
    return(x);
}

void print()
{
     int i =0;
     struct node * temp;
     temp = top;
     cout<<"\n\t\t";

     if(temp==NULL)
          cout<<"\n\t\tNo elements\n";
     else
     {
         while(temp!=NULL)
         {
              int info = temp->info;
              cout<info<<"  ";
              temp=temp->next;
         }
         cout<<"End of List<<"\n";
      }
}
