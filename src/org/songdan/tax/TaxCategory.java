package org.songdan.tax;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

public class TaxCategory implements Comparable<TaxCategory> {

    /**
     * 主键
     */
    @JsonIgnore
    private String categoryId;

    /**
     * 编码
     */
    @JsonIgnore
    private String code;

    /**
     * 合并编码
     */
    private String mergeCoding;

    /**
     * 货物和劳务名称
     */
    private String goodsName;

    /**
     * 说明
     */
    @JsonIgnore
    private String explained;

    /**
     * 增值税特殊管理
     */
    @JsonIgnore
    private String addedTaxSpecial;

    /**
     * 增值税政策依据
     */
    @JsonIgnore
    private String addedTaxBasis;

    /**
     * 增值税特殊内容代码
     */
    @JsonIgnore
    private String addedTaxContent;

    /**
     * 消费税管理
     */
    @JsonIgnore
    private String consumptionTax;

    /**
     * 消费税政策依据
     */
    @JsonIgnore
    private String consumptionTaxBasis;

    /**
     * 消费税特殊内容代码
     */
    @JsonIgnore
    private String consumptionTaxContent;

    /**
     * 关键字
     */
    @JsonIgnore
    private String keyword;

    /**
     * 是否可归并上一级
     */
    private String mergeFirstLevel;

    /**
     * 增值税税率
     */
    @JsonIgnore
    private String taxRateId;

    /**
     * 编码主键
     */
    @JsonIgnore
    private String productTaxCodeId;

    /**
     * 对应统计局编码或国民行业代码
     */
    @JsonIgnore
    private String nationalIndustryId;

    /**  */
    @JsonIgnore
    private String taxRate;

    /**  */
    @JsonIgnore
    private String taxRateDesc;

    /**
     * 税率值
     */
    @JsonIgnore
    private String taxRateValue;

    // =====================================||
    // 以下为2017税收分类编码表更新新增字段||
    // =====================================||

    /**
     * 版本号
     */
    @JsonIgnore
    private String bbh;

    /**
     * 可用状态
     */
    @JsonIgnore
    private String kyzt;

    /**
     * 海关进出口商品品目
     */
    @JsonIgnore
    private String customsGoodsItem;

    @JsonIgnore
    private Date qysj;

    @JsonIgnore
    private Date gdqjzsj;

    @JsonIgnore
    private Date gxsj;

    public String getTaxRateValue() {
        return taxRateValue;
    }

    public void setTaxRateValue(String taxRateValue) {
        this.taxRateValue = taxRateValue;
    }

    public String getTaxRateDesc() {
        return taxRateDesc;
    }

    public void setTaxRateDesc(String taxRateDesc) {
        this.taxRateDesc = taxRateDesc;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getMergeCoding() {
        return mergeCoding;
    }

    public void setMergeCoding(String mergeCoding) {
        this.mergeCoding = mergeCoding == null ? null : mergeCoding.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getExplained() {
        return explained;
    }

    public void setExplained(String explained) {
        this.explained = explained == null ? null : explained.trim();
    }

    public String getAddedTaxSpecial() {
        return addedTaxSpecial;
    }

    public void setAddedTaxSpecial(String addedTaxSpecial) {
        this.addedTaxSpecial = addedTaxSpecial == null ? null : addedTaxSpecial.trim();
    }

    public String getAddedTaxBasis() {
        return addedTaxBasis;
    }

    public void setAddedTaxBasis(String addedTaxBasis) {
        this.addedTaxBasis = addedTaxBasis == null ? null : addedTaxBasis.trim();
    }

    public String getAddedTaxContent() {
        return addedTaxContent;
    }

    public void setAddedTaxContent(String addedTaxContent) {
        this.addedTaxContent = addedTaxContent == null ? null : addedTaxContent.trim();
    }

    public String getConsumptionTax() {
        return consumptionTax;
    }

    public void setConsumptionTax(String consumptionTax) {
        this.consumptionTax = consumptionTax == null ? null : consumptionTax.trim();
    }

    public String getConsumptionTaxBasis() {
        return consumptionTaxBasis;
    }

    public void setConsumptionTaxBasis(String consumptionTaxBasis) {
        this.consumptionTaxBasis = consumptionTaxBasis == null ? null : consumptionTaxBasis.trim();
    }

    public String getConsumptionTaxContent() {
        return consumptionTaxContent;
    }

    public void setConsumptionTaxContent(String consumptionTaxContent) {
        this.consumptionTaxContent = consumptionTaxContent == null ? null : consumptionTaxContent.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getMergeFirstLevel() {
        return mergeFirstLevel;
    }

    public void setMergeFirstLevel(String mergeFirstLevel) {
        this.mergeFirstLevel = mergeFirstLevel == null ? null : mergeFirstLevel.trim();
    }

    public String getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(String taxRateId) {
        this.taxRateId = taxRateId == null ? null : taxRateId.trim();
    }

    public String getProductTaxCodeId() {
        return productTaxCodeId;
    }

    public void setProductTaxCodeId(String productTaxCodeId) {
        this.productTaxCodeId = productTaxCodeId == null ? null : productTaxCodeId.trim();
    }

    public String getNationalIndustryId() {
        return nationalIndustryId;
    }

    public void setNationalIndustryId(String nationalIndustryId) {
        this.nationalIndustryId = nationalIndustryId == null ? null : nationalIndustryId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getBbh() {
        return bbh;
    }

    public void setBbh(String bbh) {
        this.bbh = bbh;
    }

    public String getCustomsGoodsItem() {
        return customsGoodsItem;
    }

    public void setCustomsGoodsItem(String customsGoodsItem) {
        this.customsGoodsItem = customsGoodsItem;
    }

    public Date getGdqjzsj() {
        return gdqjzsj;
    }

    public void setGdqjzsj(Date gdqjzsj) {
        this.gdqjzsj = gdqjzsj;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public Date getQysj() {
        return qysj;
    }

    public void setQysj(Date qysj) {
        this.qysj = qysj;
    }

    public String getKyzt() {
        return kyzt;
    }

    public void setKyzt(String kyzt) {
        this.kyzt = kyzt;
    }

    @Override
    public String toString() {
        return "TaxCategory{goodsName:" + goodsName + ",mergeCoding:" + mergeCoding + "}  ";
    }

    @Override
    public int compareTo(TaxCategory o) {
        return mergeCoding.compareTo(o.mergeCoding);
    }
}
