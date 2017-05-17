package org.songdan.tax;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * 生成具有父子关系的树，并发版本(注意此版本需要将TreeNode中的children类型改为HashSet,TreeSet在并发请求下会抛出空指针)
 * Created by SongDan on 2017/5/17.
 */
public class TaxTreeBuilderConcurrently {

    private static final String TOP_CODE = "0";

    private ConcurrentHashMap<String, TreeNode> map = new ConcurrentHashMap<>();

    private TreeMap<String,TaxCategory> taxMap;

    public TreeNode build(ThreadPoolExecutor poolExecutor) throws IOException, InterruptedException {
        taxMap = new TaxJsonParser("tax.json").parse();
        long start = System.currentTimeMillis();
        int sum = 0;
        for (Map.Entry<String, TaxCategory> taxCategoryEntry : taxMap.entrySet()) {
            TaxCategory taxCategory = taxCategoryEntry.getValue();
            if ("N".equals(taxCategory.getMergeFirstLevel())) {
                sum++;
            }
        }
        CountDownLatch countDownLatch = new CountDownLatch(sum);
        /*
            筛选出叶子节点，每一个叶子节点都向上查找上级节点，上级节点再向上查找
         */
        for (Map.Entry<String, TaxCategory> taxCategoryEntry : taxMap.entrySet()) {
            TaxCategory taxCategory = taxCategoryEntry.getValue();
            if ("N".equals(taxCategory.getMergeFirstLevel())) {
                poolExecutor.execute(() -> {
                    try {
                        TreeNode treeNode = new TreeNode(taxCategory);
                        handleNode(treeNode);
                        /*try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                    finally {
                        countDownLatch.countDown();
                    }
                });
            }
        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis()-start);
        return map.get(TOP_CODE);
    }


    private void handleNode(TreeNode treeNode) {

        String mergeCode = treeNode.getMergeCode();
        String parentKey = calculateParentKey(mergeCode);
        if (TOP_CODE.equals(parentKey)) {
            TreeNode parentNode = new TreeNode();
            parentNode.setName("所有分类");
            parentNode.setMergeCode(TOP_CODE);
            parentNode.setPrarent(true);
            TreeNode returnNode = map.putIfAbsent(TOP_CODE,parentNode);
            if (returnNode !=null) {
                parentNode = returnNode;
            }
            parentNode.getChildren().add(treeNode);
        }else{
            TreeNode parentNode = add2Parent(treeNode, parentKey);
            handleNode(parentNode);
        }
    }

    private TreeNode add2Parent(TreeNode treeNode, String parentKey) {
        TaxCategory taxCategory = taxMap.get(parentKey);
        TreeNode parentNode = new TreeNode(taxCategory);
        TreeNode returnNode = map.putIfAbsent(parentKey, parentNode);
        if (returnNode !=null) {
            parentNode = returnNode;
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

    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        TreeNode topNode = new TaxTreeBuilderConcurrently().build(poolExecutor);
        poolExecutor.shutdown();
        System.out.println(new Gson().toJson(topNode));
    }

}
