package arvoresGenericas;

import java.util.ArrayList;

public class GeneralTreeOfString {

    // Classe interna Node
    private class Node {

        public Node father;
        public String element;
        public ArrayList<Node> subtrees;

        public Node(String element) {
            father = null;
            this.element = element;
            subtrees = new ArrayList<>();
        }

        public Node(String element, Node father) {
            this.father = father;
            this.element = element;
            subtrees = new ArrayList<>();
        }

        public void addSubtree(Node n) {
            n.father = this;
            subtrees.add(n);
        }

        public boolean removeSubtree(Node n) {
            n.father = null;
            return subtrees.remove(n);
        }

        public Node getSubtree(int i) {
            if ((i < 0) || (i >= subtrees.size())) {
                throw new IndexOutOfBoundsException();
            }
            return subtrees.get(i);
        }

        public int getSubtreesSize() {
            return subtrees.size();
        }
    }

    // Atributos
    private Node root;
    private int count;

    // Metodos
    public GeneralTreeOfString() {
        root = null;
        count = 0;
    }

    public String getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void setRoot(String element) {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        root.element = element;
    }

    public boolean isRoot(Integer element) {
        if (root != null) {
            if (root.element.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public void clear() {
        root = null;
        count = 0;
    }

    public String getFaher(String element) {
        Node n = searchNodeRef(element, root);
        if (n == null || n.father == null) {
            return null;
        } else {
            return n.father.element;
        }
    }

    public boolean contains(String element) {
        Node nAux = searchNodeRef(element, root);
        return (nAux != null);
    }

    private Node searchNodeRef(String element, Node target) {
        Node res = null;
        if (target != null) {
            if (element.equals(target.element)) {
                res = target;
            } else {
                Node aux = null;
                int i = 0;
                while ((aux == null) && (i < target.getSubtreesSize())) {
                    aux = searchNodeRef(element, target.getSubtree(i));
                    i++;
                }
                res = aux;
            }
        }
        return res;
    }

    public boolean add(String element, String father) {
        Node n = new Node(element);
        Node nAux = null;
        boolean res = false;
        if (father == null) {   // Insere na raiz
            if (root != null) { //Atualiza o pai da raiz
                n.addSubtree(root);
                root.father = n;
            }
            root = n;   //Atualiza a raiz
            res = true;
        } else {        //Insere no meio da �rvore
            nAux = searchNodeRef(father, root);
            if (nAux != null) {
                nAux.addSubtree(n);
                res = true;
            }
        }
        count++;
        return res;
    }

    public ArrayList<String> positionsPre() {
        ArrayList<String> lista = new ArrayList<>();
        positionsPreAux(root, lista);
        return lista;
    }

    private void positionsPreAux(Node n, ArrayList<String> lista) {
        if (n != null) {
            lista.add(n.element);
            for (int i = 0; i < n.getSubtreesSize(); i++) {
                positionsPreAux(n.getSubtree(i), lista);
            }
        }
    }

    public ArrayList<String> positionsWidth() {
        ArrayList<String> lista = new ArrayList<>();

        Queue<Node> fila = new Queue<>();
        Node atual;

        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                lista.add(atual.element);
                for (int i = 0; i < atual.getSubtreesSize(); i++) {
                    fila.enqueue(atual.getSubtree(i));
                }
            }
        }
        return lista;
    }

}
