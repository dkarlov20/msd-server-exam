package com.griddynamics.dkarlov.msd.server.exam.container;

public interface TreeNode<K, V> {

    K getKey();

    V getData();

    TreeNode<K, V> addNode(K key, V data);

    TreeNode<K, V> searchNode(K key);

    String getDataAsString();

}
