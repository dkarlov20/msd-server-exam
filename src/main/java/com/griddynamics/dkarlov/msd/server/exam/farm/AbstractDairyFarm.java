package com.griddynamics.dkarlov.msd.server.exam.farm;

import com.griddynamics.dkarlov.msd.server.exam.container.TreeNode;
import com.griddynamics.dkarlov.msd.server.exam.model.Cow;
import com.griddynamics.dkarlov.msd.server.exam.model.enums.DairyFarmType;

import java.util.Optional;

public abstract class AbstractDairyFarm {
    public static final int MOTHER_COW_ID = 0;
    private static final String MOTHER_COW_NICKNAME = "Mother Cow";
    protected static final Cow MOTHER_COW = new Cow(MOTHER_COW_ID, MOTHER_COW_NICKNAME, true);
    private final TreeNode<Integer, Cow> cows;

    protected AbstractDairyFarm(TreeNode<Integer, Cow> cows) {
        this.cows = cows;
    }

    public abstract DairyFarmType getDairyFarmType();

    public Cow giveBirth(int parentCowId, int childCowId, String childNickName) {
        return Optional.ofNullable(cows.searchNode(parentCowId))
                .filter(treeNode -> treeNode.getData().isAlive())
                .map(parentCow -> parentCow.addNode(childCowId, new Cow(childCowId, childNickName, true)))
                .map(TreeNode::getData)
                .orElse(null);
    }

    public Cow endLifeSpan(int cowId) {
        return Optional.ofNullable(cows.searchNode(cowId))
                .map(TreeNode::getData)
                .map(this::inactivateCow)
                .orElse(null);
    }

    public void printFarmData() {
        System.out.println(cows.getDataAsString());
    }

    private Cow inactivateCow(Cow cow) {
        cow.setAlive(false);
        return cow;
    }
}
