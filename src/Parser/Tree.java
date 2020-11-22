package Parser;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nikolay Yarlychenko
 */


public class Tree {
    String node;
    static int id = 1;
    List<Tree> children;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
    }

    public Tree(String node) {
        this.node = node;
    }


    public String toGraph() {
        id = 1;
        return dfs(this, 1).toString();
    }


    @Override
    public String toString() {
        return "\nParser.Tree{" +
                "node='" + node + '\'' +
                ", id=" + id +
                ", children=" + children +
                '}';
    }

    private StringBuilder dfs(Tree v, int n) {
        if (v == null) {
            return new StringBuilder();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("n").append(n).append(" ;\n");
        sb.append("n").append(n).append(" [label=\"").append(v.node).append("\"] ;\n");


        if (v.children == null || v.children.isEmpty()) {
            return sb;
        }
        StringBuilder child = new StringBuilder();
        for (Tree c : v.children) {
            sb.append("n").append(n).append(" -- n").append(++id).append(" ;\n");
            child.append(dfs(c, id));
        }
        sb.append(child);

        return sb;
    }
}


