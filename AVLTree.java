class AVLTree {

    class Node {
        int patientID, height;
        Node left, right;

        Node(int patientID) {
            this.patientID = patientID;
            height = 1;
        }
    }

    Node root;

    int height(Node N) {
        return (N == null) ? 0 : N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    Node insert(Node node, int patientID) {

        if (node == null)
            return new Node(patientID);

        if (patientID < node.patientID)
            node.left = insert(node.left, patientID);
        else if (patientID > node.patientID)
            node.right = insert(node.right, patientID);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Case
        if (balance > 1 && patientID < node.left.patientID)
            return rightRotate(node);

        // RR Case
        if (balance < -1 && patientID > node.right.patientID)
            return leftRotate(node);

        // LR Case
        if (balance > 1 && patientID > node.left.patientID) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Case
        if (balance < -1 && patientID < node.right.patientID) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Node search(Node root, int patientID) {
        if (root == null || root.patientID == patientID)
            return root;

        if (patientID < root.patientID)
            return search(root.left, patientID);

        return search(root.right, patientID);
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.patientID + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 70);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 60);
        tree.root = tree.insert(tree.root, 80);

        System.out.print("Patient IDs (Inorder Traversal): ");
        tree.inorder(tree.root);

        int key = 60;
        Node result = tree.search(tree.root, key);

        if (result != null)
            System.out.println("\nPatient ID " + key + " Found");
        else
            System.out.println("\nPatient ID " + key + " Not Found");
    }
}