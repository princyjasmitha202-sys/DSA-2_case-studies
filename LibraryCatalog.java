class AVLNode { 

    int isbn, height; 

    AVLNode left, right; 

    AVLNode(int isbn) { this.isbn = isbn; this.height = 1; } 

} 

 

public class LibraryCatalog { 

    AVLNode root; 

 

    int height(AVLNode n) { return n == null ? 0 : n.height; } 

    int getBalance(AVLNode n) { return n == null ? 0 : height(n.left) - height(n.right); } 

 

    AVLNode rightRotate(AVLNode y) { 

        AVLNode x = y.left, T2 = x.right; 

        x.right = y; y.left = T2; 

        y.height = Math.max(height(y.left), height(y.right)) + 1; 

        x.height = Math.max(height(x.left), height(x.right)) + 1; 

        return x; 

    } 

 

    AVLNode leftRotate(AVLNode x) { 

        AVLNode y = x.right, T2 = y.left; 

        y.left = x; x.right = T2; 

        x.height = Math.max(height(x.left), height(x.right)) + 1; 

        y.height = Math.max(height(y.left), height(y.right)) + 1; 

        return y; 

    } 

 

    AVLNode insert(AVLNode node, int isbn) { 

        if (node == null) return new AVLNode(isbn); 

        if (isbn < node.isbn) node.left = insert(node.left, isbn); 

        else if (isbn > node.isbn) node.right = insert(node.right, isbn); 

        else return node; // duplicate 

 

        node.height = 1 + Math.max(height(node.left), height(node.right)); 

        int balance = getBalance(node); 

 

        if (balance > 1 && isbn < node.left.isbn) return rightRotate(node);       // LL 

        if (balance < -1 && isbn > node.right.isbn) return leftRotate(node);      // RR 

        if (balance > 1 && isbn > node.left.isbn) {                               // LR 

            node.left = leftRotate(node.left); return rightRotate(node); 

        } 

        if (balance < -1 && isbn < node.right.isbn) {                            // RL 

            node.right = rightRotate(node.right); return leftRotate(node); 

        } 

        return node; 

    } 

 

    boolean search(AVLNode node, int isbn) { 

        if (node == null) return false; 

        if (isbn == node.isbn) return true; 

        return isbn < node.isbn ? search(node.left, isbn) : search(node.right, isbn); 

    } 

 

    public static void main(String[] args) { 

        LibraryCatalog catalog = new LibraryCatalog(); 

        int[] isbns = {1001, 1002, 1003, 1004, 1005}; // sorted - would unbalance BST 

        for (int isbn : isbns) catalog.root = catalog.insert(catalog.root, isbn); 

        System.out.println("Found 1003: " + catalog.search(catalog.root, 1003)); 

        System.out.println("Found 9999: " + catalog.search(catalog.root, 9999)); 

    } 

} 