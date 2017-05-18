package org.songdan.tax;

/**
 * 税收分类树形关联关系接口
 * 
 * @author Songdan
 * @date 2016/5/25
 */
public class TaxCodeNodeHierachyImpl implements INodeHierarchy<TaxCategory> {

    @Override
    public boolean isHierarchy(TaxCategory pNode, TaxCategory sonNode) {
        String pMergeCoding = StringUtils.trimContinuousZero(pNode.getMergeCoding());
        String sonMergeCoding = StringUtils.trimContinuousZero(sonNode.getMergeCoding());
        if (pMergeCoding.length() == sonMergeCoding.length() && pMergeCoding.equals("0")) {
            return true;
        }
        return sonMergeCoding.startsWith(pMergeCoding) && sonMergeCoding.length() - pMergeCoding.length() == 2;
    }

    @Override
    public boolean isHierarchyRecursion(TaxCategory pNode, TaxCategory childrenNode) {
        String pMergeCoding = StringUtils.trimContinuousZero(pNode.getMergeCoding());
        String sonMergeCoding = StringUtils.trimContinuousZero(childrenNode.getMergeCoding());
        return sonMergeCoding.startsWith(pMergeCoding) && sonMergeCoding.length() > pMergeCoding.length();
    }

}
