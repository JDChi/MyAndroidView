package com.ugiant.myandroidview.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chijiaduo on 2017/1/11.
 */

public class LeafFactory {


    /**
     * 设定最大的叶子数量
     */
    private static final int MAX_LEAFS = 8;
    /**
     * 叶子旋转一个周期的默认时间
     */
    public static final long LEAF_DEFAULT_CYCLE_TIME = 3000;

    Random random = new Random();

    public Leaf generateSingleLeaf(){
        Leaf leaf = new Leaf();
        int randomType = random.nextInt(3);
        StartType startType = StartType.MIDDLE;
        switch (randomType){
            case 0:
                startType = StartType.LITTLE;
                break;
            case 1:
                startType = StartType.MIDDLE;
                break;
            case 2:
                startType = StartType.BIG;
                break;
        }
        leaf.setStartType(startType);
        leaf.setRotateAngle(random.nextInt(360));
        leaf.setRotateDirection(random.nextInt(2));
        leaf.setStartTime(System.currentTimeMillis() + random.nextInt((int) (LEAF_DEFAULT_CYCLE_TIME * 2)));
        return leaf;
    }

    /**
     * 生成指定的叶子数
     * @param leafCount
     * @return
     */
    public List<Leaf> generateLeafs(int leafCount){
        List<Leaf> leafList = new ArrayList<>();
        for(int i = 0 ; i < leafCount ; i ++){
            leafList.add(generateSingleLeaf());
        }
        return leafList;
    }

    /**
     * 生成默认的最大叶子数
     * @return
     */
    public List<Leaf> generateDefaultLeafs(){
        return generateLeafs(MAX_LEAFS);
    }
}
