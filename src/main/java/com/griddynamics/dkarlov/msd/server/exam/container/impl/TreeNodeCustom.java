package com.griddynamics.dkarlov.msd.server.exam.container.impl;

import com.griddynamics.dkarlov.msd.server.exam.container.TreeNode;

import java.util.Objects;

import static com.griddynamics.dkarlov.msd.server.exam.util.CommonConstants.LINE_BREAK;
import static com.griddynamics.dkarlov.msd.server.exam.util.CommonConstants.POINTER;
import static com.griddynamics.dkarlov.msd.server.exam.util.CommonConstants.TABULATION;

public class TreeNodeCustom<K, V> implements TreeNode<K, V> {
    private final K key;
    private final V data;
    private TreeNodeCustom<K, V> parent;
    private TreeNodeCustom<K, V> firstLeaf;
    private TreeNodeCustom<K, V> nextNode;

    public TreeNodeCustom(K key, V data) {
        this.key = key;
        this.data = data;
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

        TreeNodeCustom<K, V> childNode = new TreeNodeCustom<>(key, data);
        childNode.parent = this;
        if (firstLeaf == null) {
            childNode.parent.firstLeaf = childNode;
        } else {
            TreeNodeCustom<K, V> nextChildNode = firstLeaf.nextNode;
            if (nextChildNode != null) {
                while (nextChildNode.nextNode != null) {
                    nextChildNode = nextChildNode.nextNode;
                }
                nextChildNode.nextNode = childNode;
            } else {
                firstLeaf.nextNode = childNode;
            }
        }
        return childNode;
    }

    @Override
    public TreeNode<K, V> searchNode(K key) {
        TreeNodeCustom<K, V> root = getRoot();
        return searchFromNode(root, key);
    }

    @Override
    public String getDataAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        TreeNodeCustom<K, V> root = getRoot();
        stringBuilder.append(root.data).append(LINE_BREAK);
        appendChildrenData(stringBuilder, this, TABULATION);
        return stringBuilder.toString();
    }

    private TreeNodeCustom<K, V> getRoot() {
        TreeNodeCustom<K, V> root = parent;
        if (parent == null) {
            return this;
        }
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    private TreeNodeCustom<K, V> searchFromNode(TreeNodeCustom<K, V> node, K key) {
        if (Objects.equals(node.getKey(), key)) {
            return node;
        }

        if (node.nextNode != null) {
            TreeNodeCustom<K, V> foundNode = searchFromNode(node.nextNode, key);
            if (foundNode != null) {
                return foundNode;
            }
            if (node.firstLeaf != null) {
                return searchFromNode(node.firstLeaf, key);
            }
        }

        if (node.firstLeaf != null) {
            TreeNodeCustom<K, V> foundNode = searchFromNode(node.firstLeaf, key);
            if (foundNode != null) {
                return foundNode;
            }
            if (node.nextNode != null) {
                return searchFromNode(node.nextNode, key);
            }
        }

        return null;
    }

    private void appendChildrenData(StringBuilder builder, TreeNodeCustom<K, V> node, String tabulation) {
        if (node.firstLeaf != null) {
            builder.append(tabulation)
                    .append(POINTER)
                    .append(node.firstLeaf.data)
                    .append(LINE_BREAK);
            appendChildrenData(builder, node.firstLeaf, tabulation + TABULATION);

            TreeNodeCustom<K, V> nextChildNode = node.firstLeaf.nextNode;
            while (nextChildNode != null) {
                builder.append(tabulation)
                        .append(POINTER)
                        .append(nextChildNode.data)
                        .append(LINE_BREAK);
                appendChildrenData(builder, nextChildNode, tabulation + TABULATION);
                nextChildNode = nextChildNode.nextNode;
            }
        }
    }
}
