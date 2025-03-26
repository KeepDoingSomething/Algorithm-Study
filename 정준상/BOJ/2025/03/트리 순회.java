import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-25
 * Runtime          :   364 ms
 * Memory           :   47.644 MB
 * Used algorithm   :   Tree
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static Tree[] trees;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        trees = IntStream.range(0, N)
                .mapToObj(Tree::new)
                .toArray(Tree[]::new);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int root = Integer.parseInt(st.nextToken()) - 1;
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;

            trees[root].setLR(
                    left == -2 ? null : trees[left],
                    right == -2 ? null : trees[right]
            );
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        System.out.println(inspectSize(0));
    }

    private static int inspectSize(int root) {
        Tree curr = trees[root];
        int leftReturn = 2 * curr.getLSize();

        return leftReturn + (
                curr.right != null ?
                1 + inspectSize(curr.right.root) : 0
        );
    }
}

class Tree {

    final int root;
    Tree left, right;

    public Tree(int root) {
        this.root = root;
    }

    public void setLR(Tree l, Tree r) {
        left = l;
        right = r;
    }

    public int getSize() {
        return getLSize() + getRSize() + 1;
    }

    public int getLSize() {
        return left == null ? 0 : left.getSize();
    }

    public int getRSize() {
        return right == null ? 0 : right.getSize();
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
