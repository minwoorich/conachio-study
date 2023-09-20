package minwoo.백준문제.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{
    char value;
    Node left;
    Node right;

    public Node(char value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
public class BOJ_트리순회_1991 {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        Node headNode = new Node('A', null, null);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            insertNode(headNode, root, left, right);
        }

        preOrder(headNode);
        System.out.println();
        inOrder(headNode);
        System.out.println();
        postOrder(headNode);
    }

    public static void insertNode(Node currentNode, char root, char left, char right) {
        //만약
        if (currentNode.value == root) {
            currentNode.left = (left == '.' ? null : new Node(left, null, null));
            currentNode.right = (right == '.' ? null : new Node(right, null, null));
        }else{
            if (currentNode.left != null) {
                insertNode(currentNode.left, root, left, right);
            }
            if (currentNode.right != null) {
                insertNode(currentNode.right, root, left, right);
            }
        }
    }

    public static void preOrder(Node node) {
        if(node == null) return;
        System.out.print(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node) {
        if(node == null) return;
        inOrder(node.left);
        System.out.print(node.value);
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value);
    }

}
