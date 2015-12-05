package testThred;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 16.11.2015.
 */
public class TreeNode {
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();

    public synchronized void addChild(TreeNode child) {
        if(children.contains(child)){
            children.add(child);
            child.setParentOnly(this);
        }
    }

    public synchronized void addChildOnly(TreeNode child) {
        if(children.contains(child)){
            children.add(child);
        }
    }

    public synchronized void setParentOnly(TreeNode parent) {
        this.parent = parent;
        parent.addChildOnly(this);
    }

    public synchronized void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
