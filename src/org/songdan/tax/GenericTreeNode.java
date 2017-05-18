package org.songdan.tax;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import java.util.TreeSet;

/**
 * 树形节点类
 * 
 * @author Songdan
 * @date 2016/5/25
 */
public class GenericTreeNode<T extends Comparable<T>> implements Comparable<GenericTreeNode<T>> {

    /**
     * @fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private T value;

    private TreeSet<GenericTreeNode<T>> nodes;

    public GenericTreeNode(T t) {
        this.value = t;
        this.nodes = new TreeSet<>();
    }

    public GenericTreeNode() {

    }

    public TreeSet<GenericTreeNode<T>> getNodes() {
        return nodes;
    }

    public void setNodes(TreeSet<GenericTreeNode<T>> nodes) {
        this.nodes = nodes;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GenericTreeNode<T> tNode : nodes) {
            sb.append(tNode.toString());
            sb.append("\r\n");
        }
        return "value : " + value.toString() + "\r\n\tnodes :\r\n\t" + "\t" + sb.toString();
    }

    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public int compareTo(GenericTreeNode<T> o) {
        return getValue().compareTo(o.getValue());
    }

}
