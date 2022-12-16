package com.griddynamics.dkarlov.msd.server.exam.container.impl;

import com.griddynamics.dkarlov.msd.server.exam.container.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.griddynamics.dkarlov.msd.server.exam.util.CommonConstants.LINE_BREAK;
import static com.griddynamics.dkarlov.msd.server.exam.util.CommonConstants.POINTER;
import static com.griddynamics.dkarlov.msd.server.exam.util.CommonConstants.TABULATION;

public class TreeNodeJava<K, V> implements TreeNode<K, V> {
    private final K key;
    private final V data;
    private final List<TreeNodeJava<K, V>> children;
    private TreeNodeJava<K, V> parent;

    public TreeNodeJava(K key, V data) {
        this.key = key;
        this.data = data;
        this.children = new LinkedList<>();
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getData() {
        return data;
    }

    @Override
    public TreeNode<K, V> addNode(K key, V data) {
        if (data == null) {
            throw new IllegalArgumentException("Data should not be null!");
        }

        if (searchNode(key) != null) {
            throw new IllegalArgumentException(String.format("Element with key %s was already added.", key));
        }

        TreeNodeJava<K, V> childNode = new TreeNodeJava<>(key, data);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    @Override
    public TreeNode<K, V> searchNode(K key) {
        TreeNodeJava<K, V> root = getRoot();
        return searchFromNode(root, key);
    }

    @Override
    public String getDataAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        TreeNodeJava<K, V> root = getRoot();
        stringBuilder.append(root.getData()).append(LINE_BREAK);
        appendChildrenData(stringBuilder, this, TABULATION);
        return stringBuilder.toString();
    }

    private TreeNodeJava<K, V> getRoot() {
        TreeNodeJava<K, V> root = parent;
        if (parent == null) {
            return this;
        }
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    private TreeNodeJava<K, V> searchFromNode(TreeNodeJava<K, V> parent, K key) {
        if (Objects.equals(parent.getKey(), key)) {
            return parent;
        } else {
            for (TreeNodeJava<K, V> child : parent.children) {
                TreeNodeJava<K, V> result = searchFromNode(child, key);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private void appendChildrenData(StringBuilder builder, TreeNodeJava<K, V> node, String tabulation) {
        if (!node.children.isEmpty()) {
            for (TreeNodeJava<K, V> child : node.children) {
                builder.append(tabulation)
                        .append(POINTER)
                        .append(child.getData())
                        .append(LINE_BREAK);
                appendChildrenData(builder, child, tabulation + TABULATION);
            }
        }
    }
}
