
package model;

public class SplayTree {
    
    private SplayNodo root;
     private int count = 0;
 
     /** Constructor **/
     public SplayTree()
     {
         root = null;
     }
  
      /** Función para validar si el arbol está vacío **/
     public boolean isEmpty()
     {
         return root == null;
     }
 
     /** Limpiar árbol **/
     public void clear()
     {
         root = null;
         count = 0;
     }
     
     /** Función para insertar un elemento */
     public void insert(Nodo pElement)
     {
         SplayNodo z = root;
         SplayNodo p = null;
         while (z != null)
         {
             p = z;
             if (pElement.getSensor().getNombre()
                     .compareTo(p.getElement().getSensor().getNombre())>0)
                 z = z.getRight();
             else
                 z = z.getLeft();
         }
         z = new SplayNodo();
         z.setElement(pElement);
         z.setParent(p);
         if (p == null)
             root = z;
         else if (pElement.getSensor().getNombre()
                 .compareTo(p.getElement().getSensor().getNombre())>0)
                 p.setRight(z);
             else
                 p.setLeft(z);
         Splay(z);
         count++;
     }
     
      /** Rotar **/
     public void makeLeftChildParent(SplayNodo pChildren, SplayNodo pParent)
     {
         if ((pChildren == null) || (pParent == null) 
                 || (pParent.getLeft() != pChildren) 
                 || (pChildren.getParent() != pParent))
             throw new RuntimeException("WRONG");
 
         if (pParent.getParent() != null)
         {
             if (pParent == pParent.getParent().getLeft())
                 pParent.getParent().setLeft(pChildren);
             else 
                 pParent.getParent().setRight(pChildren);
         }
         if (pChildren.getRight() != null)
             pChildren.getRight().setParent(pParent);
         
         pChildren.setParent(pParent.getParent());
         pParent.setParent(pChildren);
         pParent.setLeft(pChildren.getRight());
         pChildren.setRight(pParent);
     }
 
     /** Rotar **/
     public void makeRightChildParent(SplayNodo pChildren, SplayNodo pParent)
     {
         if ((pChildren == null) || (pParent == null) 
                 || (pParent.getRight() != pChildren) 
                 || (pChildren.getParent() != pParent))
             throw new RuntimeException("WRONG");
         if (pParent.getParent() != null)
         {
             if (pParent == pParent.getParent().getLeft())
                 pParent.getParent().setLeft(pChildren);
             else
                 pParent.getParent().setRight(pChildren);
         }
         if (pChildren.getLeft() != null)
             pChildren.getLeft().setParent(pParent);
         pChildren.setParent(pParent.getParent());
         pParent.setParent(pChildren);
         pParent.setRight(pChildren.getLeft());
         pChildren.setLeft(pParent);
     }
     
      /** Funcion splay **/
     private void Splay(SplayNodo pNodo)
     {
         while (pNodo.getParent() != null)
         {
             SplayNodo Parent = pNodo.getParent();
             SplayNodo GrandParent = Parent.getParent();
             if (GrandParent == null)
             {
                 if (pNodo == Parent.getLeft())
                     makeLeftChildParent(pNodo, Parent);
                 else
                     makeRightChildParent(pNodo, Parent);                 
             } 
             else
             {
                 if (pNodo == Parent.getLeft())
                 {
                     if (Parent == GrandParent.getLeft())
                     {
                         makeLeftChildParent(Parent, GrandParent);
                         makeLeftChildParent(pNodo, Parent);
                     }
                     else 
                     {
                         makeLeftChildParent(pNodo, pNodo.getParent());
                         makeRightChildParent(pNodo, pNodo.getParent());
                     }
                 }
                 else 
                 {
                     if (Parent == GrandParent.getLeft())
                     {
                         makeRightChildParent(pNodo, pNodo.getParent());
                         makeLeftChildParent(pNodo, pNodo.getParent());
                     } 
                     else 
                     {
                         makeRightChildParent(Parent, GrandParent);
                         makeRightChildParent(pNodo, Parent);
                     }
                 }
             }
         }
         root = pNodo;
     }
     
     /** Función para remover un elemento **/
     public void remove(Nodo pElement)
     {
         SplayNodo nodo = findNode(pElement);
         remove(nodo);
     }
     
     /** function to remove node **/
     private void remove(SplayNodo pNodo)
     {
         if (pNodo == null)
             return;
 
         Splay(pNodo);
         if( (pNodo.getLeft() != null) && (pNodo.getRight() !=null))
         { 
             SplayNodo min = pNodo.getLeft();
             while(min.getRight()!=null)
                 min = min.getRight();
             
             min.setRight(pNodo.getRight());
             pNodo.getRight().setParent(min);
             pNodo.getLeft().setParent(null);
             root = pNodo.getLeft();
         }
         else if (pNodo.getRight() != null)
         {
             pNodo.getRight().setParent(null);
             root = pNodo.getRight();
         } 
         else if( pNodo.getLeft() !=null)
         {
             pNodo.getLeft().setParent(null);
             root = pNodo.getLeft();
         }
         else
         {
             root = null;
         }
         pNodo.setParent(null);
         pNodo.setLeft(null);
         pNodo.setRight(null);
         pNodo = null;
         count--;
     }
     
     /** Retorna el número de nodos **/
     public int countNodes()
     {
         return this.count;
     }
 
     /** Funciones para buscar por elemento **/
     public boolean search(Nodo pNodo)
     {
         return findNode(pNodo) != null;
     }
     
     private SplayNodo findNode(Nodo pNodo)
     {
    	 SplayNodo PrevNode = null;
         SplayNodo z = root;
         while (z != null)
         {
        	 PrevNode = z;
             if (pNodo.getSensor().getNombre()
                     .compareTo(z.getElement().getSensor().getNombre()) > 0)
                 z = z.getRight();
             else if (pNodo.getSensor().getNombre()
                     .compareTo(z.getElement().getSensor().getNombre()) < 0)
                 z = z.getLeft();
             else if (pNodo.getSensor().getNombre()
                     .compareTo(z.getElement().getSensor().getNombre()) == 0) {
                 Splay(z);
                 return z;
             }
 
         }
         if(PrevNode != null)
         {
             Splay(PrevNode);
             return null;
         }
         return null;
     }
     
      /** Funcion que recorre el arbol en orden **/ 
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(SplayNodo pNodo)
     {
         if (pNodo != null)
         {
             inorder(pNodo.getLeft());
             System.out.print(pNodo.getElement() +" ");
             inorder(pNodo.getRight());
         }
     }
 

    public SplayNodo getRoot() {
        return root;
    }

    public void setRoot(SplayNodo root) {
        this.root = root;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
     
     
}
