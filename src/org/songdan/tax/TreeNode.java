package org.songdan.tax;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by SongDan on 2017/5/17.
 */
public class TreeNode implements Comparable<TreeNode> {

    private String mergeCode;

    private String name;

    private boolean isPrarent;

    private TreeSet<TreeNode> children = new TreeSet<>();

    public TreeNode() {
    }

    public TreeNode(TaxCategory taxCategory) {
        this.mergeCode = taxCategory.getMergeCoding();
        this.name = taxCategory.getGoodsName();
    }

    public String getMergeCode() {
        return mergeCode;
    }

    public void setMergeCode(String mergeCode) {
        this.mergeCode = mergeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeSet<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(TreeSet<TreeNode> children) {
        this.children = children;
    }

    public boolean isPrarent() {
        return isPrarent;
    }

    public void setPrarent(boolean isPrarent) {
        this.isPrarent = isPrarent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TreeNode treeNode = (TreeNode) o;

        if (mergeCode != null ? !mergeCode.equals(treeNode.mergeCode) : treeNode.mergeCode != null)
            return false;
        if (name != null ? !name.equals(treeNode.name) : treeNode.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mergeCode != null ? mergeCode.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(TreeNode o) {
        return this.mergeCode.compareTo(o.mergeCode);
    }
}
