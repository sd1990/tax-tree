package org.songdan.tax;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeMap;

/**
 *
 * @author Songdan
 * @date 2017/5/18 9:34
 */
public class TaxTreeBuilderSlow {

    private TreeMap<String,TaxCategory> taxMap;

    public GenericTreeNode<TaxCategory> build() throws IOException {
        taxMap = new TaxJsonParser("tax.json").parse();
        long start = System.currentTimeMillis();
        Collection<TaxCategory> values = taxMap.values();
        TaxCategory top = new TaxCategory();
        top.setMergeCoding("0");
        top.setGoodsName("所有分类");
        GenericTreeNode<TaxCategory> head = new GenericTreeNode<>(top);
        GenericTreeNode<TaxCategory> headNode =
                NodeUtils.buildNode(head, values, new TaxCodeNodeHierachyImpl());
        System.out.println(System.currentTimeMillis()-start);
        return headNode;
    }

    public static void main(String[] args) throws IOException {
        GenericTreeNode<TaxCategory> build = new TaxTreeBuilderSlow().build();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(build));
    }

}
