package org.songdan.tax;

import java.util.*;

/**
 * Node工具类
 * 
 * @author Songdan
 * @date 2016/5/25
 */
public final class NodeUtils {

    /**
     * 通过传递的头节点和它的儿孙节点，生成头节点的树状结构
     * 
     * @param head 头节点
     * @param collection 儿孙节点集合
     * @param nodeHierarchy 用于判断节点间的父子关系的接口
     * @param <T> 类型参数
     * @return 具备树形结构的头节点
     */
    public static <T extends Comparable<T>> GenericTreeNode<T> buildNode(GenericTreeNode<T> head, Collection<T> collection,
            INodeHierarchy<T> nodeHierarchy) {
        /*
        1，选出子节点
        2，对子节点递归
         */
        for (T t : collection) {
            if (nodeHierarchy.isHierarchy(head.getValue(), t)) {
                // 使用LinkedList作为备份，不要改变原来的集合，因为此处没有随机读取，删除操作较多，更适合使用链表
                LinkedList<T> tempList = new LinkedList<>(collection);
                // 选出这个节点的所有后代节点
                filter(t, tempList, nodeHierarchy);
                // 递归调用
                head.getNodes().add(buildNode(new GenericTreeNode<>(t), tempList, nodeHierarchy));
            }
        }
        return head;
    }

    private static <T> boolean filter(T obj, final Iterable<T> collection, final INodeHierarchy<? super T> predicate) {
        boolean result = false;
        if (collection != null && predicate != null) {
            for (final Iterator<T> it = collection.iterator(); it.hasNext();) {
                if (!predicate.isHierarchyRecursion(obj, it.next())) {
                    it.remove();
                    result = true;
                }
            }
        }
        return result;
    }


}
