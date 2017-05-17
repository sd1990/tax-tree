package org.songdan.tax;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * 生成具有父子关系的树
 * Created by SongDan on 2017/5/17.
 */
public class TaxTreeBuilder {

    private static final String TOP_CODE = "0";

    private Map<String, TreeNode> map = new HashMap<>();

    private TreeMap<String,TaxCategory> taxMap;

    public TreeNode build() throws IOException {
        taxMap = new TaxJsonParser("tax.json").parse();
        long start = System.currentTimeMillis();
        /*
            筛选出叶子节点，每一个叶子节点都向上查找上级节点，上级节点再向上查找
         */
        for (Map.Entry<String, TaxCategory> taxCategoryEntry : taxMap.entrySet()) {
            TaxCategory taxCategory = taxCategoryEntry.getValue();
            if ("N".equals(taxCategory.getMergeFirstLevel())) {
                TreeNode treeNode = new TreeNode(taxCategory);
                handleNode(treeNode);
                /*try {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
        System.out.println(System.currentTimeMillis()-start);
        return map.get(TOP_CODE);
    }


    private void handleNode(TreeNode treeNode) {

        String mergeCode = treeNode.getMergeCode();
        String parentKey = calculateParentKey(mergeCode);
        if (TOP_CODE.equals(parentKey)) {
            TreeNode parentNode = map.get(TOP_CODE);
            if (parentNode ==null) {
                parentNode = new TreeNode();
                parentNode.setName("所有分类");
                parentNode.setMergeCode(TOP_CODE);
                parentNode.setPrarent(true);
                map.put(parentKey, parentNode);
            }
            parentNode.getChildren().add(treeNode);
        }else{
            TreeNode parentNode = add2Parent(treeNode, parentKey);
            handleNode(parentNode);
        }
    }

    private TreeNode add2Parent(TreeNode treeNode, String parentKey) {
        TreeNode parentNode = map.get(parentKey);
        if (parentNode ==null) {
            TaxCategory taxCategory = taxMap.get(parentKey);
            parentNode = new TreeNode(taxCategory);
            parentNode.setPrarent(true);
            map.put(parentKey, parentNode);
        }
        parentNode.getChildren().add(treeNode);
        return parentNode;
    }

    private String calculateParentKey(String sonKey) {
        //处理sonkey
        String afterSonKey = trimContinuousZero(sonKey);
        if (afterSonKey.length() == 1) {
            return TOP_CODE;
        }
        else {
            return afterSonKey.substring(0, afterSonKey.length() - 2) + getZeroString(21 - afterSonKey.length());
        }
    }

    private String getZeroString(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(TOP_CODE);
        }
        return sb.toString();
    }

    public static String trimContinuousZero(String num) {
        String reg = "(00)+$";
        return num.replaceAll(reg, "");
    }

    public static void main(String[] args) throws IOException {
        TreeNode topNode = new TaxTreeBuilder().build();
        System.out.println(new Gson().toJson(topNode));
    }

}
