
package model;

public class SplayNodo {
    
     private SplayNodo left, right, parent;
     private Nodo element;
 
     /** Constructor **/
     public SplayNodo()
     {
         this(null, null, null, null);
     }          
     /** Constructor
     * @param ele **/
     public SplayNodo(Nodo pElement)
     {
         this(pElement, null, null, null);
     } 
     /** Constructor**/
     
     public SplayNodo(Nodo pElement, SplayNodo pLeft, SplayNodo pRight, SplayNodo pParent)
     {
         this.left = pLeft;
         this.right = pRight;
         this.parent = pParent;
         this.element = pElement;         
     }

    public SplayNodo getLeft() {
        return left;
    }

    public void setLeft(SplayNodo left) {
        this.left = left;
    }

    public SplayNodo getRight() {
        return right;
    }

    public void setRight(SplayNodo right) {
        this.right = right;
    }

    public SplayNodo getParent() {
        return parent;
    }

    public void setParent(SplayNodo parent) {
        this.parent = parent;
    }

    public Nodo getElement() {
        return element;
    }

    public void setElement(Nodo element) {
        this.element = element;
    }
     
     

}
