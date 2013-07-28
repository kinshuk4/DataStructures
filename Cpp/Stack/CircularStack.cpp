#include <iostream>
#include <stdlib.h>
using namespace std;
struct node
{
       int info;
       struct node *next;
};


    
int c=0;
struct node *top;
    
    
int empty()
{
    return((top == NULL)? 1:0);
}

void print()
{
     int i =0;
     struct node *temp;
     cout<<"\n\t\t";

     if(c==0)
                   cout<<"\n\t\tNo elements\n";
     else
     {
         temp = top->next;
         while(i!=c)
         {
		      int info = temp->info;	
                      cout<<info<<"  ";
                      temp=temp->next;
                      i++;
         }
         cout<<"END PRINT";
      }
}

void push(int n)
{
     struct node *p;
     p=new node;
     if(p!=NULL)
     {
              
                c++;
                p->info=n;
                if(empty())
                           top=p;
                else
                    p->next=top->next;
                top->next=p;                  
     }
     else
         cout<<"Not inserted,No memory available";
}

int pop()
{
  
    c--;
    struct node *temp;
    int x;
    temp=top->next;
    x=temp->info;
    if(temp==top)
                top=NULL;
    else
        top->next=temp->next;
    free(temp);
    return(x);
}
    
int main()
{
    int s,n;
    cout<<"\n\t\tCIRCULAR STACK IMPLEMENTATION\n";
    while(1)
    {
            cout<<"\n\t\t1>Push\n";
            cout<<"\t\t2>Pop\n";
            cout<<"\t\t3>Print\n";
            cout<<"\t\t4>Exit\n";
            cout<<"\t\tEnter your choice:";
            cin>>s;
            switch(s)
            {
                  case 1:
                          cout<<"\n\t\tEnter the number:";
                          cin>>n;
                          push(n);
                          break;
                
                  case 2:
                
                          if(empty())
                          {
                             cout<<"\n\t\tStack underflow\n";
                
                          }
                          else
                          {
                             n=pop();
                             cout<<"\n\t\tThe pop value is:"<<n;
                          }
                          
                          break;
                      
                  case 3:
                          print();
                          break;
                  case 4:
                          exit(1);
		      break;
            }
    }
}
