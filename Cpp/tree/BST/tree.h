#ifndef TREE1_H
#define TREE1_H

# include <stdio.h>
# include <stdlib.h>
#define isChildless(p) p->left==NULL &&p->right==NULL
enum BOOL{false,true} ;
typedef enum BOOL BOOL;

struct node {
	int data;
	struct node* left;
	struct node* right;
} ;
typedef struct node node;


/*
Given a binary tree, return true if a node
with the target data is found in the tree. Recurs
down the tree, chooses the left or right
branch by comparing the target to each node.
 */
static int lookup(node* node, int target) {
	// 1. Base case == empty tree
	// in that case, the target is not found so return false
	if (node == NULL) {
		return(false);
	}
	else {
		// 2. see if found here
		if (target == node->data) return(true);
		else {
			// 3. otherwise recur down the correct subtree
			if (target <>data) return(lookup(node->left, target));
			else return(lookup(node->right, target));
		}
	}
}


node* newNode(int data) {
	node* temp = (node*) malloc(sizeof(node)); // "new" is like "malloc"
	temp->data = data;
	temp->left = NULL;
	temp->right = NULL;

	return(temp);
}

/*
Give a binary search tree and a number, inserts a new node
with the given number in the correct place in the tree.
Returns the new root pointer which the caller should
then use (the standard trick to avoid using reference
parameters).
 */
struct node* insert(struct node* node, int data) {
	// 1. If the tree is empty, return a new, single node
	if (node == NULL) {
		return(newNode(data));
	}
	else {
		// 2. Otherwise, recur down the tree
		if (data <= node->data) node->left = insert(node->left, data);
		else node->right = insert(node->right, data);

		return(node); // return the (unchanged) node pointer
	}
}

//functions simply on traversals
int size(node *p)
{
	if (p==NULL) {
		return(0);
	} else {
		return(size(p->left) + 1 + size(p->right));
	}
}


/*
Given a binary search tree, print out
its data elements in increasing
sorted order.
 */
void printTree(struct node* node) {
	if (node == NULL) {//printf("check");
		return;}

	printTree(node->left);
	printf("%d <<", node->data);
	printTree(node->right);
}

void printPostTree(node *node)
{
	if (node == NULL) return;

	printPostTree(node->left);//note here even printTree will work
	printPostTree(node->right);
	printf("%d ", node->data);

}


/*
The node to be deleted might be in the following states


 * The node does not exist in the tree - In this case you have nothing to delete.
 * The node to be deleted has no children - The memory occupied by this node must
be freed and either the left link or the right link of the parent of this node must be set to NULL.
 * The node to be deleted has exactly one child -
We have to adjust the pointer of the parent of the node to be deleted such that
after deletion it points to the child of the node being deleted.
 * The node to be deleted has two children -
We need to find the inorder successor of the node to be deleted.
The data of the inorder successor must be copied into the node to be deleted and a pointer should
be setup to the inorder successor. This inorder successor would have one or zero children.
This node should be deleted using the same procedure as for deleting a one child or a zero child node.
Thus the whole logic of deleting a node with two children is to locate the inorder successor,
copy its data and reduce the problem to a simple deletion of a node with one or zero children.*/


int select()
{
	int selection;
	do
	{
		puts("");
		puts("Enter 1: Insert a node in the BST");
		puts("Enter 2: Delete");
		puts("Enter 3: Display(preorder)the BST");
		puts("Enter 4: Display(inorder)the BST");
		puts("Enter 5: Display(postorder) the BST");
		puts("Enter 6: Print");
		puts("Enter 7: Lookup");
		puts("Enter 9: End");
		puts("Enter your choice");
		scanf("%d",&selection);
		if((selection<1)||(selection>7))
		{
			puts("wrong choice:Try again");
			getch(); }
	}while((selection<1)||(selection>7));
	return (selection);
}

node *getInorder2Child(node *n)//returns inorder succesor only when parent has //right node
{

	if( n->right==0 ) return 0;
	n = n->right;

	while( n->left != 0 )
		n = n->left;

		return n;
}

node *child0(node *p, node **parent,int rt,int lt)
{
	node *parentP = *parent;

	if(rt==1) parentP->right = NULL;
	else if(lt==1) parentP->left = NULL;
	//retNode = p;
	//free(p);//free only when u dont want to return the node and adjust return type accordingly
	return p;

}

node *child_1(node *p, node **parent, int rt,int lt)
{
	node *parentP = *parent;
	if(p->left==NULL && p->right!=NULL)
	{
		if(rt==1) parentP->right = p->right;
		else parentP->left = p->right;
	}
	if(p->left!=NULL&&p->right==NULL)
	{
		if(rt==1) parentP->right = p->left;
		else parentP->left = p->left;
	}
}
node * deleteNode(node **root,int num)
{
	int lt=0,rt=0,inorderData;
	node *p = *root,*parentP=*root;
	node *retNode;
	node *inordersucc;
	if(!lookup(*root,num)) {printf("node not found\n");
	return (node*) 0;
	}
	//if node to be deleted has no child
	while(p->data!=num)
	{
		if(p->data > num) { parentP=p; p=p->left; lt=1;rt=0;}
		else if(p->dataright; lt=0;rt=1;}
}

if(isChildless(p))
{
	p = child0(p,&parentP,rt,lt);
	return p;
}

//only 1 child to be deleted
if((p->left==NULL && p->right!=NULL)||(p->left!=NULL&&p->right==NULL))
{
	p=child_1(p,&parentP,rt,lt);
	return p;
}

if(p->left!= NULL && p->right != NULL)
{
	inordersucc = getInorder2Child(p);
	// printf("%d\n" , inordersucc->data);
	//swap teh values
	// swaptemp = num;//though swaptemp is useless variable, because we already have num

	p->data = inordersucc->data;
	//inordersucc->data = num;
	//printf("%d\n",inordersucc->data);
	retNode = deleteNode(&p->right,inordersucc->data);
}
return (node*) NULL;
}


#endif
