package com.griddynamics.dkarlov.msd.server.exam.container;

import com.griddynamics.dkarlov.msd.server.exam.container.impl.TreeNodeCustom;
import com.griddynamics.dkarlov.msd.server.exam.model.Cow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TreeNodeCustomTest {
    private static final int COW_ID_TEST = 0;
    private static final String COW_NICKNAME_TEST = "test";
    private static final Cow COW_TEST = new Cow(COW_ID_TEST, COW_NICKNAME_TEST, true);
    private TreeNodeCustom<Integer, Cow> nodeTest;

    @BeforeEach
    public void setUp() {
        nodeTest = new TreeNodeCustom<>(COW_ID_TEST, COW_TEST);
    }

    @Test
    public void nodeAdditionTest() {
        int newNodeKey = 1;
        Cow newNodeData = new Cow(newNodeKey, COW_NICKNAME_TEST, true);

        TreeNode<Integer, Cow> node = nodeTest.addNode(newNodeKey, newNodeData);

        assertInstanceOf(TreeNodeCustom.class, node);
        assertEquals(newNodeKey, node.getKey());
        assertEquals(newNodeData, node.getData());
    }

    @Test
    public void nullableDataNodeAdditionTest() {
        int newNodeKey = 1;
        Cow newNodeData = null;

        assertThrows(IllegalArgumentException.class, () -> nodeTest.addNode(newNodeKey, newNodeData));
    }

    @Test
    public void existingNodeAdditionTest() {
        Cow newNodeData = new Cow(COW_ID_TEST, COW_NICKNAME_TEST, true);

        assertThrows(IllegalArgumentException.class, () -> nodeTest.addNode(COW_ID_TEST, newNodeData));
    }

    @Test
    public void searchRootNodeTest() {
        TreeNode<Integer, Cow> foundNode = nodeTest.searchNode(COW_ID_TEST);

        assertInstanceOf(TreeNodeCustom.class, foundNode);
        assertEquals(COW_ID_TEST, foundNode.getKey());
        assertEquals(COW_TEST, foundNode.getData());
    }

    @Test
    public void searchChildNodeTest() {
        int childNodeKey = 1;
        Cow childNodeData = new Cow(childNodeKey, COW_NICKNAME_TEST, true);
        nodeTest.addNode(childNodeKey, childNodeData);

        TreeNode<Integer, Cow> foundNode = nodeTest.searchNode(childNodeKey);

        assertInstanceOf(TreeNodeCustom.class, foundNode);
        assertEquals(childNodeKey, foundNode.getKey());
        assertEquals(childNodeData, foundNode.getData());
    }

    @Test
    public void searchMissingNodeTest() {
        int missingNodeKey = 1;

        TreeNode<Integer, Cow> foundNode = nodeTest.searchNode(missingNodeKey);

        assertNull(foundNode);
    }
}
