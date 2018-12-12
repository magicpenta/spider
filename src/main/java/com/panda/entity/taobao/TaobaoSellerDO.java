package com.panda.entity.taobao;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "${seller_table}")
public class TaobaoSellerDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filename;

    /**
     * 卖家ID
     */
    @Column(name = "seller_id")
    private Long sellerId;

    /**
     * 卖家名称
     */
    @Column(name = "seller_name")
    private String sellerName;

    /**
     * 是否天猫（0-否；1-是）
     */
    @Column(name = "is_tianmao")
    private Integer isTianmao;

    /**
     * 是否老店
     */
    @Column(name = "is_old_seller")
    private Integer isOldSeller;

    /**
     * 是否金牌卖家
     */
    @Column(name = "is_gold_seller")
    private Integer isGoldSeller;

    /**
     * 当前主营
     */
    @Column(name = "main_business")
    private String mainBusiness;

    /**
     * 所在地区
     */
    private String location;

    /**
     * 卖家信用
     */
    @Column(name = "seller_credit")
    private Integer sellerCredit;

    /**
     * 买家信用
     */
    @Column(name = "buyer_credit")
    private Integer buyerCredit;

    /**
     * 是否有消费者保障
     */
    @Column(name = "has_consumer_guarantee")
    private Integer hasConsumerGuarantee;

    /**
     * 是否有正品保障
     */
    @Column(name = "has_real_guarantee")
    private Integer hasRealGuarantee;

    /**
     * 是否支持发票
     */
    @Column(name = "has_invoice")
    private Integer hasInvoice;

    /**
     * 是否有七天退货
     */
    @Column(name = "has_return_in_week")
    private Integer hasReturnInWeek;

    /**
     * 保证金余额
     */
    @Column(name = "deposit_balance")
    private BigDecimal depositBalance;

    /**
     * 是否有认证信息
     */
    @Column(name = "has_authentication_info")
    private Integer hasAuthenticationInfo;

    /**
     * 是否有执照信息
     */
    @Column(name = "has_license_info")
    private Integer hasLicenseInfo;

    /**
     * 描述相符得分
     */
    @Column(name = "description_match_score")
    private Double descriptionMatchScore;

    /**
     * 描述得分超出平均值百分比
     */
    @Column(name = "description_over_percent")
    private Double descriptionOverPercent;

    /**
     * 描述得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    @Column(name = "is_description_over_average")
    private Integer isDescriptionOverAverage;

    /**
     * 描述得分评分人数
     */
    @Column(name = "description_score_num")
    private Integer descriptionScoreNum;

    /**
     * 描述得分5分评价人数百分比
     */
    @Column(name = "description_5_score_percent")
    private Double description5ScorePercent;

    /**
     * 描述得分4分评价人数百分比
     */
    @Column(name = "description_4_score_percent")
    private Double description4ScorePercent;

    /**
     * 描述得分3分评价人数百分比
     */
    @Column(name = "description_3_score_percent")
    private Double description3ScorePercent;

    /**
     * 描述得分2分评价人数百分比
     */
    @Column(name = "description_2_score_percent")
    private Double description2ScorePercent;

    /**
     * 描述得分1分评价人数百分比
     */
    @Column(name = "description_1_score_percent")
    private Double description1ScorePercent;

    /**
     * 服务态度得分
     */
    @Column(name = "service_attitude_score")
    private Double serviceAttitudeScore;

    /**
     * 服务态度超出平均值百分比
     */
    @Column(name = "service_over_percent")
    private Double serviceOverPercent;

    /**
     * 服务态度是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    @Column(name = "is_service_over_average")
    private Integer isServiceOverAverage;

    /**
     * 服务态度评分人数
     */
    @Column(name = "service_score_num")
    private Integer serviceScoreNum;

    /**
     * 服务态度5分评价人数百分比
     */
    @Column(name = "service_5_score_percent")
    private Double service5ScorePercent;

    /**
     * 服务态度4分评价人数百分比
     */
    @Column(name = "service_4_score_percent")
    private Double service4ScorePercent;

    /**
     * 服务态度3分评价人数百分比
     */
    @Column(name = "service_3_score_percent")
    private Double service3ScorePercent;

    /**
     * 服务态度2分评价人数百分比
     */
    @Column(name = "service_2_score_percent")
    private Double service2ScorePercent;

    /**
     * 服务态度1分评价人数百分比
     */
    @Column(name = "service_1_score_percent")
    private Double service1ScorePercent;

    /**
     * 物流服务得分
     */
    @Column(name = "logistics_service_score")
    private Double logisticsServiceScore;

    /**
     * 物流得分超出平均值百分比
     */
    @Column(name = "logistics_over_percent")
    private Double logisticsOverPercent;

    /**
     * 物流得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    @Column(name = "is_logistics_over_average")
    private Integer isLogisticsOverAverage;

    /**
     * 物流得分评分人数
     */
    @Column(name = "logistics_score_num")
    private Integer logisticsScoreNum;

    /**
     * 物流得分5分评价人数百分比
     */
    @Column(name = "logistics_5_score_percent")
    private Double logistics5ScorePercent;

    /**
     * 物流得分4分评价人数百分比
     */
    @Column(name = "logistics_4_score_percent")
    private Double logistics4ScorePercent;

    /**
     * 物流得分3分评价人数百分比
     */
    @Column(name = "logistics_3_score_percent")
    private Double logistics3ScorePercent;

    /**
     * 物流得分2分评价人数百分比
     */
    @Column(name = "logistics_2_score_percent")
    private Double logistics2ScorePercent;

    /**
     * 物流得分1分评价人数百分比
     */
    @Column(name = "logistics_1_score_percent")
    private Double logistics1ScorePercent;

    /**
     * 售后率
     */
    @Column(name = "after_sale_rate")
    private Double afterSaleRate;

    /**
     * 售后率行业均值
     */
    @Column(name = "after_sale_average_rate")
    private Double afterSaleAverageRate;

    /**
     * 售后率是否大于行业均值
     */
    @Column(name = "is_after_sale_rate_over_average")
    private Integer isAfterSaleRateOverAverage;

    /**
     * 纠纷率
     */
    @Column(name = "dispute_rate")
    private Double disputeRate;

    /**
     * 纠纷率行业均值
     */
    @Column(name = "dispute_average_rate")
    private Double disputeAverageRate;

    /**
     * 纠纷率是否大于行业均值
     */
    @Column(name = "is_dispute_rate_over_average")
    private Integer isDisputeRateOverAverage;

    /**
     * 仅退款自主完结时长
     */
    @Column(name = "only_refund_spend_time")
    private Double onlyRefundSpendTime;

    /**
     * 仅退款自主完结平均时长
     */
    @Column(name = "only_refund_average_spend_time")
    private Double onlyRefundAverageSpendTime;

    /**
     * 仅退款自主完结时长是否大于行业均值
     */
    @Column(name = "is_only_refund_spend_time_over_average")
    private Integer isOnlyRefundSpendTimeOverAverage;

    /**
     * 退货退款自主完结时长
     */
    @Column(name = "full_return_spend_time")
    private Double fullReturnSpendTime;

    /**
     * 退货退款自主完结平均时长
     */
    @Column(name = "full_return_average_spend_time")
    private Double fullReturnAverageSpendTime;

    /**
     * 退货退款自主完结时长是否大于行业均值
     */
    @Column(name = "is_full_return_spend_time_over_average")
    private Integer isFullReturnSpendTimeOverAverage;

    /**
     * 退款自主完结率
     */
    @Column(name = "auto_refund_rate")
    private Double autoRefundRate;

    /**
     * 退款自主完结率行业均值
     */
    @Column(name = "auto_refund_average_rate")
    private Double autoRefundAverageRate;

    /**
     * 退款自主完结率是否大于行业均值
     */
    @Column(name = "is_auto_refund_rate_over_average")
    private Integer isAutoRefundRateOverAverage;

    /**
     * 最近一周好评数
     */
    @Column(name = "good_comments_last_one_week")
    private Integer goodCommentsLastOneWeek;

    /**
     * 最近一周中评数
     */
    @Column(name = "normal_comments_last_one_week")
    private Integer normalCommentsLastOneWeek;

    /**
     * 最近一周差评数
     */
    @Column(name = "bad_comments_last_one_week")
    private Integer badCommentsLastOneWeek;

    /**
     * 最近一月好评数
     */
    @Column(name = "good_comments_last_one_month")
    private Integer goodCommentsLastOneMonth;

    /**
     * 最近一月中评数
     */
    @Column(name = "normal_comments_last_one_month")
    private Integer normalCommentsLastOneMonth;

    /**
     * 最近一月差评数
     */
    @Column(name = "bad_comments_last_one_month")
    private Integer badCommentsLastOneMonth;

    /**
     * 最近半年好评数
     */
    @Column(name = "good_comments_last_half_year")
    private Integer goodCommentsLastHalfYear;

    /**
     * 最近半年中评数
     */
    @Column(name = "normal_comments_last_half_year")
    private Integer normalCommentsLastHalfYear;

    /**
     * 最近半年差评数
     */
    @Column(name = "bad_comments_last_half_year")
    private Integer badCommentsLastHalfYear;

    /**
     * 半年以前好评数
     */
    @Column(name = "good_comments_half_year_ago")
    private Integer goodCommentsHalfYearAgo;

    /**
     * 半年以前中评数
     */
    @Column(name = "normal_comments_half_year_ago")
    private Integer normalCommentsHalfYearAgo;

    /**
     * 半年以前差评数
     */
    @Column(name = "bad_comments_half_year_ago")
    private Integer badCommentsHalfYearAgo;

    @Column(name = "save_time")
    private Date saveTime;

    /**
     * 批次编号
     */
    @Column(name = "batch_no")
    private Integer batchNo;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 获取卖家ID
     *
     * @return seller_id - 卖家ID
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     * 设置卖家ID
     *
     * @param sellerId 卖家ID
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取卖家名称
     *
     * @return seller_name - 卖家名称
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * 设置卖家名称
     *
     * @param sellerName 卖家名称
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * 获取是否天猫（0-否；1-是）
     *
     * @return is_tianmao - 是否天猫（0-否；1-是）
     */
    public Integer getIsTianmao() {
        return isTianmao;
    }

    /**
     * 设置是否天猫（0-否；1-是）
     *
     * @param isTianmao 是否天猫（0-否；1-是）
     */
    public void setIsTianmao(Integer isTianmao) {
        this.isTianmao = isTianmao;
    }

    /**
     * 获取是否老店
     *
     * @return is_old_seller - 是否老店
     */
    public Integer getIsOldSeller() {
        return isOldSeller;
    }

    /**
     * 设置是否老店
     *
     * @param isOldSeller 是否老店
     */
    public void setIsOldSeller(Integer isOldSeller) {
        this.isOldSeller = isOldSeller;
    }

    /**
     * 获取是否金牌卖家
     *
     * @return is_gold_seller - 是否金牌卖家
     */
    public Integer getIsGoldSeller() {
        return isGoldSeller;
    }

    /**
     * 设置是否金牌卖家
     *
     * @param isGoldSeller 是否金牌卖家
     */
    public void setIsGoldSeller(Integer isGoldSeller) {
        this.isGoldSeller = isGoldSeller;
    }

    /**
     * 获取当前主营
     *
     * @return main_business - 当前主营
     */
    public String getMainBusiness() {
        return mainBusiness;
    }

    /**
     * 设置当前主营
     *
     * @param mainBusiness 当前主营
     */
    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    /**
     * 获取所在地区
     *
     * @return location - 所在地区
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置所在地区
     *
     * @param location 所在地区
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取卖家信用
     *
     * @return seller_credit - 卖家信用
     */
    public Integer getSellerCredit() {
        return sellerCredit;
    }

    /**
     * 设置卖家信用
     *
     * @param sellerCredit 卖家信用
     */
    public void setSellerCredit(Integer sellerCredit) {
        this.sellerCredit = sellerCredit;
    }

    /**
     * 获取买家信用
     *
     * @return buyer_credit - 买家信用
     */
    public Integer getBuyerCredit() {
        return buyerCredit;
    }

    /**
     * 设置买家信用
     *
     * @param buyerCredit 买家信用
     */
    public void setBuyerCredit(Integer buyerCredit) {
        this.buyerCredit = buyerCredit;
    }

    /**
     * 获取是否有消费者保障
     *
     * @return has_consumer_guarantee - 是否有消费者保障
     */
    public Integer getHasConsumerGuarantee() {
        return hasConsumerGuarantee;
    }

    /**
     * 设置是否有消费者保障
     *
     * @param hasConsumerGuarantee 是否有消费者保障
     */
    public void setHasConsumerGuarantee(Integer hasConsumerGuarantee) {
        this.hasConsumerGuarantee = hasConsumerGuarantee;
    }

    /**
     * 获取是否有正品保障
     *
     * @return has_real_guarantee - 是否有正品保障
     */
    public Integer getHasRealGuarantee() {
        return hasRealGuarantee;
    }

    /**
     * 设置是否有正品保障
     *
     * @param hasRealGuarantee 是否有正品保障
     */
    public void setHasRealGuarantee(Integer hasRealGuarantee) {
        this.hasRealGuarantee = hasRealGuarantee;
    }

    /**
     * 获取是否支持发票
     *
     * @return has_invoice - 是否支持发票
     */
    public Integer getHasInvoice() {
        return hasInvoice;
    }

    /**
     * 设置是否支持发票
     *
     * @param hasInvoice 是否支持发票
     */
    public void setHasInvoice(Integer hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    /**
     * 获取是否有七天退货
     *
     * @return has_return_in_week - 是否有七天退货
     */
    public Integer getHasReturnInWeek() {
        return hasReturnInWeek;
    }

    /**
     * 设置是否有七天退货
     *
     * @param hasReturnInWeek 是否有七天退货
     */
    public void setHasReturnInWeek(Integer hasReturnInWeek) {
        this.hasReturnInWeek = hasReturnInWeek;
    }

    /**
     * 获取保证金余额
     *
     * @return deposit_balance - 保证金余额
     */
    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    /**
     * 设置保证金余额
     *
     * @param depositBalance 保证金余额
     */
    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    /**
     * 获取是否有认证信息
     *
     * @return has_authentication_info - 是否有认证信息
     */
    public Integer getHasAuthenticationInfo() {
        return hasAuthenticationInfo;
    }

    /**
     * 设置是否有认证信息
     *
     * @param hasAuthenticationInfo 是否有认证信息
     */
    public void setHasAuthenticationInfo(Integer hasAuthenticationInfo) {
        this.hasAuthenticationInfo = hasAuthenticationInfo;
    }

    /**
     * 获取是否有执照信息
     *
     * @return has_license_info - 是否有执照信息
     */
    public Integer getHasLicenseInfo() {
        return hasLicenseInfo;
    }

    /**
     * 设置是否有执照信息
     *
     * @param hasLicenseInfo 是否有执照信息
     */
    public void setHasLicenseInfo(Integer hasLicenseInfo) {
        this.hasLicenseInfo = hasLicenseInfo;
    }

    /**
     * 获取描述相符得分
     *
     * @return description_match_score - 描述相符得分
     */
    public Double getDescriptionMatchScore() {
        return descriptionMatchScore;
    }

    /**
     * 设置描述相符得分
     *
     * @param descriptionMatchScore 描述相符得分
     */
    public void setDescriptionMatchScore(Double descriptionMatchScore) {
        this.descriptionMatchScore = descriptionMatchScore;
    }

    /**
     * 获取描述得分超出平均值百分比
     *
     * @return description_over_percent - 描述得分超出平均值百分比
     */
    public Double getDescriptionOverPercent() {
        return descriptionOverPercent;
    }

    /**
     * 设置描述得分超出平均值百分比
     *
     * @param descriptionOverPercent 描述得分超出平均值百分比
     */
    public void setDescriptionOverPercent(Double descriptionOverPercent) {
        this.descriptionOverPercent = descriptionOverPercent;
    }

    /**
     * 获取描述得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     *
     * @return is_description_over_average - 描述得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    public Integer getIsDescriptionOverAverage() {
        return isDescriptionOverAverage;
    }

    /**
     * 设置描述得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     *
     * @param isDescriptionOverAverage 描述得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    public void setIsDescriptionOverAverage(Integer isDescriptionOverAverage) {
        this.isDescriptionOverAverage = isDescriptionOverAverage;
    }

    /**
     * 获取描述得分评分人数
     *
     * @return description_score_num - 描述得分评分人数
     */
    public Integer getDescriptionScoreNum() {
        return descriptionScoreNum;
    }

    /**
     * 设置描述得分评分人数
     *
     * @param descriptionScoreNum 描述得分评分人数
     */
    public void setDescriptionScoreNum(Integer descriptionScoreNum) {
        this.descriptionScoreNum = descriptionScoreNum;
    }

    /**
     * 获取描述得分5分评价人数百分比
     *
     * @return description_5_score_percent - 描述得分5分评价人数百分比
     */
    public Double getDescription5ScorePercent() {
        return description5ScorePercent;
    }

    /**
     * 设置描述得分5分评价人数百分比
     *
     * @param description5ScorePercent 描述得分5分评价人数百分比
     */
    public void setDescription5ScorePercent(Double description5ScorePercent) {
        this.description5ScorePercent = description5ScorePercent;
    }

    /**
     * 获取描述得分4分评价人数百分比
     *
     * @return description_4_score_percent - 描述得分4分评价人数百分比
     */
    public Double getDescription4ScorePercent() {
        return description4ScorePercent;
    }

    /**
     * 设置描述得分4分评价人数百分比
     *
     * @param description4ScorePercent 描述得分4分评价人数百分比
     */
    public void setDescription4ScorePercent(Double description4ScorePercent) {
        this.description4ScorePercent = description4ScorePercent;
    }

    /**
     * 获取描述得分3分评价人数百分比
     *
     * @return description_3_score_percent - 描述得分3分评价人数百分比
     */
    public Double getDescription3ScorePercent() {
        return description3ScorePercent;
    }

    /**
     * 设置描述得分3分评价人数百分比
     *
     * @param description3ScorePercent 描述得分3分评价人数百分比
     */
    public void setDescription3ScorePercent(Double description3ScorePercent) {
        this.description3ScorePercent = description3ScorePercent;
    }

    /**
     * 获取描述得分2分评价人数百分比
     *
     * @return description_2_score_percent - 描述得分2分评价人数百分比
     */
    public Double getDescription2ScorePercent() {
        return description2ScorePercent;
    }

    /**
     * 设置描述得分2分评价人数百分比
     *
     * @param description2ScorePercent 描述得分2分评价人数百分比
     */
    public void setDescription2ScorePercent(Double description2ScorePercent) {
        this.description2ScorePercent = description2ScorePercent;
    }

    /**
     * 获取描述得分1分评价人数百分比
     *
     * @return description_1_score_percent - 描述得分1分评价人数百分比
     */
    public Double getDescription1ScorePercent() {
        return description1ScorePercent;
    }

    /**
     * 设置描述得分1分评价人数百分比
     *
     * @param description1ScorePercent 描述得分1分评价人数百分比
     */
    public void setDescription1ScorePercent(Double description1ScorePercent) {
        this.description1ScorePercent = description1ScorePercent;
    }

    /**
     * 获取服务态度得分
     *
     * @return service_attitude_score - 服务态度得分
     */
    public Double getServiceAttitudeScore() {
        return serviceAttitudeScore;
    }

    /**
     * 设置服务态度得分
     *
     * @param serviceAttitudeScore 服务态度得分
     */
    public void setServiceAttitudeScore(Double serviceAttitudeScore) {
        this.serviceAttitudeScore = serviceAttitudeScore;
    }

    /**
     * 获取服务态度超出平均值百分比
     *
     * @return service_over_percent - 服务态度超出平均值百分比
     */
    public Double getServiceOverPercent() {
        return serviceOverPercent;
    }

    /**
     * 设置服务态度超出平均值百分比
     *
     * @param serviceOverPercent 服务态度超出平均值百分比
     */
    public void setServiceOverPercent(Double serviceOverPercent) {
        this.serviceOverPercent = serviceOverPercent;
    }

    /**
     * 获取服务态度是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     *
     * @return is_service_over_average - 服务态度是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    public Integer getIsServiceOverAverage() {
        return isServiceOverAverage;
    }

    /**
     * 设置服务态度是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     *
     * @param isServiceOverAverage 服务态度是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    public void setIsServiceOverAverage(Integer isServiceOverAverage) {
        this.isServiceOverAverage = isServiceOverAverage;
    }

    /**
     * 获取服务态度评分人数
     *
     * @return service_score_num - 服务态度评分人数
     */
    public Integer getServiceScoreNum() {
        return serviceScoreNum;
    }

    /**
     * 设置服务态度评分人数
     *
     * @param serviceScoreNum 服务态度评分人数
     */
    public void setServiceScoreNum(Integer serviceScoreNum) {
        this.serviceScoreNum = serviceScoreNum;
    }

    /**
     * 获取服务态度5分评价人数百分比
     *
     * @return service_5_score_percent - 服务态度5分评价人数百分比
     */
    public Double getService5ScorePercent() {
        return service5ScorePercent;
    }

    /**
     * 设置服务态度5分评价人数百分比
     *
     * @param service5ScorePercent 服务态度5分评价人数百分比
     */
    public void setService5ScorePercent(Double service5ScorePercent) {
        this.service5ScorePercent = service5ScorePercent;
    }

    /**
     * 获取服务态度4分评价人数百分比
     *
     * @return service_4_score_percent - 服务态度4分评价人数百分比
     */
    public Double getService4ScorePercent() {
        return service4ScorePercent;
    }

    /**
     * 设置服务态度4分评价人数百分比
     *
     * @param service4ScorePercent 服务态度4分评价人数百分比
     */
    public void setService4ScorePercent(Double service4ScorePercent) {
        this.service4ScorePercent = service4ScorePercent;
    }

    /**
     * 获取服务态度3分评价人数百分比
     *
     * @return service_3_score_percent - 服务态度3分评价人数百分比
     */
    public Double getService3ScorePercent() {
        return service3ScorePercent;
    }

    /**
     * 设置服务态度3分评价人数百分比
     *
     * @param service3ScorePercent 服务态度3分评价人数百分比
     */
    public void setService3ScorePercent(Double service3ScorePercent) {
        this.service3ScorePercent = service3ScorePercent;
    }

    /**
     * 获取服务态度2分评价人数百分比
     *
     * @return service_2_score_percent - 服务态度2分评价人数百分比
     */
    public Double getService2ScorePercent() {
        return service2ScorePercent;
    }

    /**
     * 设置服务态度2分评价人数百分比
     *
     * @param service2ScorePercent 服务态度2分评价人数百分比
     */
    public void setService2ScorePercent(Double service2ScorePercent) {
        this.service2ScorePercent = service2ScorePercent;
    }

    /**
     * 获取服务态度1分评价人数百分比
     *
     * @return service_1_score_percent - 服务态度1分评价人数百分比
     */
    public Double getService1ScorePercent() {
        return service1ScorePercent;
    }

    /**
     * 设置服务态度1分评价人数百分比
     *
     * @param service1ScorePercent 服务态度1分评价人数百分比
     */
    public void setService1ScorePercent(Double service1ScorePercent) {
        this.service1ScorePercent = service1ScorePercent;
    }

    /**
     * 获取物流服务得分
     *
     * @return logistics_service_score - 物流服务得分
     */
    public Double getLogisticsServiceScore() {
        return logisticsServiceScore;
    }

    /**
     * 设置物流服务得分
     *
     * @param logisticsServiceScore 物流服务得分
     */
    public void setLogisticsServiceScore(Double logisticsServiceScore) {
        this.logisticsServiceScore = logisticsServiceScore;
    }

    /**
     * 获取物流得分超出平均值百分比
     *
     * @return logistics_over_percent - 物流得分超出平均值百分比
     */
    public Double getLogisticsOverPercent() {
        return logisticsOverPercent;
    }

    /**
     * 设置物流得分超出平均值百分比
     *
     * @param logisticsOverPercent 物流得分超出平均值百分比
     */
    public void setLogisticsOverPercent(Double logisticsOverPercent) {
        this.logisticsOverPercent = logisticsOverPercent;
    }

    /**
     * 获取物流得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     *
     * @return is_logistics_over_average - 物流得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    public Integer getIsLogisticsOverAverage() {
        return isLogisticsOverAverage;
    }

    /**
     * 设置物流得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     *
     * @param isLogisticsOverAverage 物流得分是否超过同行业平均水平（1-高于；0-持平；-1-低于）
     */
    public void setIsLogisticsOverAverage(Integer isLogisticsOverAverage) {
        this.isLogisticsOverAverage = isLogisticsOverAverage;
    }

    /**
     * 获取物流得分评分人数
     *
     * @return logistics_score_num - 物流得分评分人数
     */
    public Integer getLogisticsScoreNum() {
        return logisticsScoreNum;
    }

    /**
     * 设置物流得分评分人数
     *
     * @param logisticsScoreNum 物流得分评分人数
     */
    public void setLogisticsScoreNum(Integer logisticsScoreNum) {
        this.logisticsScoreNum = logisticsScoreNum;
    }

    /**
     * 获取物流得分5分评价人数百分比
     *
     * @return logistics_5_score_percent - 物流得分5分评价人数百分比
     */
    public Double getLogistics5ScorePercent() {
        return logistics5ScorePercent;
    }

    /**
     * 设置物流得分5分评价人数百分比
     *
     * @param logistics5ScorePercent 物流得分5分评价人数百分比
     */
    public void setLogistics5ScorePercent(Double logistics5ScorePercent) {
        this.logistics5ScorePercent = logistics5ScorePercent;
    }

    /**
     * 获取物流得分4分评价人数百分比
     *
     * @return logistics_4_score_percent - 物流得分4分评价人数百分比
     */
    public Double getLogistics4ScorePercent() {
        return logistics4ScorePercent;
    }

    /**
     * 设置物流得分4分评价人数百分比
     *
     * @param logistics4ScorePercent 物流得分4分评价人数百分比
     */
    public void setLogistics4ScorePercent(Double logistics4ScorePercent) {
        this.logistics4ScorePercent = logistics4ScorePercent;
    }

    /**
     * 获取物流得分3分评价人数百分比
     *
     * @return logistics_3_score_percent - 物流得分3分评价人数百分比
     */
    public Double getLogistics3ScorePercent() {
        return logistics3ScorePercent;
    }

    /**
     * 设置物流得分3分评价人数百分比
     *
     * @param logistics3ScorePercent 物流得分3分评价人数百分比
     */
    public void setLogistics3ScorePercent(Double logistics3ScorePercent) {
        this.logistics3ScorePercent = logistics3ScorePercent;
    }

    /**
     * 获取物流得分2分评价人数百分比
     *
     * @return logistics_2_score_percent - 物流得分2分评价人数百分比
     */
    public Double getLogistics2ScorePercent() {
        return logistics2ScorePercent;
    }

    /**
     * 设置物流得分2分评价人数百分比
     *
     * @param logistics2ScorePercent 物流得分2分评价人数百分比
     */
    public void setLogistics2ScorePercent(Double logistics2ScorePercent) {
        this.logistics2ScorePercent = logistics2ScorePercent;
    }

    /**
     * 获取物流得分1分评价人数百分比
     *
     * @return logistics_1_score_percent - 物流得分1分评价人数百分比
     */
    public Double getLogistics1ScorePercent() {
        return logistics1ScorePercent;
    }

    /**
     * 设置物流得分1分评价人数百分比
     *
     * @param logistics1ScorePercent 物流得分1分评价人数百分比
     */
    public void setLogistics1ScorePercent(Double logistics1ScorePercent) {
        this.logistics1ScorePercent = logistics1ScorePercent;
    }

    /**
     * 获取售后率
     *
     * @return after_sale_rate - 售后率
     */
    public Double getAfterSaleRate() {
        return afterSaleRate;
    }

    /**
     * 设置售后率
     *
     * @param afterSaleRate 售后率
     */
    public void setAfterSaleRate(Double afterSaleRate) {
        this.afterSaleRate = afterSaleRate;
    }

    /**
     * 获取售后率行业均值
     *
     * @return after_sale_average_rate - 售后率行业均值
     */
    public Double getAfterSaleAverageRate() {
        return afterSaleAverageRate;
    }

    /**
     * 设置售后率行业均值
     *
     * @param afterSaleAverageRate 售后率行业均值
     */
    public void setAfterSaleAverageRate(Double afterSaleAverageRate) {
        this.afterSaleAverageRate = afterSaleAverageRate;
    }

    /**
     * 获取售后率是否大于行业均值
     *
     * @return is_after_sale_rate_over_average - 售后率是否大于行业均值
     */
    public Integer getIsAfterSaleRateOverAverage() {
        return isAfterSaleRateOverAverage;
    }

    /**
     * 设置售后率是否大于行业均值
     *
     * @param isAfterSaleRateOverAverage 售后率是否大于行业均值
     */
    public void setIsAfterSaleRateOverAverage(Integer isAfterSaleRateOverAverage) {
        this.isAfterSaleRateOverAverage = isAfterSaleRateOverAverage;
    }

    /**
     * 获取纠纷率
     *
     * @return dispute_rate - 纠纷率
     */
    public Double getDisputeRate() {
        return disputeRate;
    }

    /**
     * 设置纠纷率
     *
     * @param disputeRate 纠纷率
     */
    public void setDisputeRate(Double disputeRate) {
        this.disputeRate = disputeRate;
    }

    /**
     * 获取纠纷率行业均值
     *
     * @return dispute_average_rate - 纠纷率行业均值
     */
    public Double getDisputeAverageRate() {
        return disputeAverageRate;
    }

    /**
     * 设置纠纷率行业均值
     *
     * @param disputeAverageRate 纠纷率行业均值
     */
    public void setDisputeAverageRate(Double disputeAverageRate) {
        this.disputeAverageRate = disputeAverageRate;
    }

    /**
     * 获取纠纷率是否大于行业均值
     *
     * @return is_dispute_rate_over_average - 纠纷率是否大于行业均值
     */
    public Integer getIsDisputeRateOverAverage() {
        return isDisputeRateOverAverage;
    }

    /**
     * 设置纠纷率是否大于行业均值
     *
     * @param isDisputeRateOverAverage 纠纷率是否大于行业均值
     */
    public void setIsDisputeRateOverAverage(Integer isDisputeRateOverAverage) {
        this.isDisputeRateOverAverage = isDisputeRateOverAverage;
    }

    /**
     * 获取仅退款自主完结时长
     *
     * @return only_refund_spend_time - 仅退款自主完结时长
     */
    public Double getOnlyRefundSpendTime() {
        return onlyRefundSpendTime;
    }

    /**
     * 设置仅退款自主完结时长
     *
     * @param onlyRefundSpendTime 仅退款自主完结时长
     */
    public void setOnlyRefundSpendTime(Double onlyRefundSpendTime) {
        this.onlyRefundSpendTime = onlyRefundSpendTime;
    }

    /**
     * 获取仅退款自主完结平均时长
     *
     * @return only_refund_average_spend_time - 仅退款自主完结平均时长
     */
    public Double getOnlyRefundAverageSpendTime() {
        return onlyRefundAverageSpendTime;
    }

    /**
     * 设置仅退款自主完结平均时长
     *
     * @param onlyRefundAverageSpendTime 仅退款自主完结平均时长
     */
    public void setOnlyRefundAverageSpendTime(Double onlyRefundAverageSpendTime) {
        this.onlyRefundAverageSpendTime = onlyRefundAverageSpendTime;
    }

    /**
     * 获取仅退款自主完结时长是否大于行业均值
     *
     * @return is_only_refund_spend_time_over_average - 仅退款自主完结时长是否大于行业均值
     */
    public Integer getIsOnlyRefundSpendTimeOverAverage() {
        return isOnlyRefundSpendTimeOverAverage;
    }

    /**
     * 设置仅退款自主完结时长是否大于行业均值
     *
     * @param isOnlyRefundSpendTimeOverAverage 仅退款自主完结时长是否大于行业均值
     */
    public void setIsOnlyRefundSpendTimeOverAverage(Integer isOnlyRefundSpendTimeOverAverage) {
        this.isOnlyRefundSpendTimeOverAverage = isOnlyRefundSpendTimeOverAverage;
    }

    /**
     * 获取退货退款自主完结时长
     *
     * @return full_return_spend_time - 退货退款自主完结时长
     */
    public Double getFullReturnSpendTime() {
        return fullReturnSpendTime;
    }

    /**
     * 设置退货退款自主完结时长
     *
     * @param fullReturnSpendTime 退货退款自主完结时长
     */
    public void setFullReturnSpendTime(Double fullReturnSpendTime) {
        this.fullReturnSpendTime = fullReturnSpendTime;
    }

    /**
     * 获取退货退款自主完结平均时长
     *
     * @return full_return_average_spend_time - 退货退款自主完结平均时长
     */
    public Double getFullReturnAverageSpendTime() {
        return fullReturnAverageSpendTime;
    }

    /**
     * 设置退货退款自主完结平均时长
     *
     * @param fullReturnAverageSpendTime 退货退款自主完结平均时长
     */
    public void setFullReturnAverageSpendTime(Double fullReturnAverageSpendTime) {
        this.fullReturnAverageSpendTime = fullReturnAverageSpendTime;
    }

    /**
     * 获取退货退款自主完结时长是否大于行业均值
     *
     * @return is_full_return_spend_time_over_average - 退货退款自主完结时长是否大于行业均值
     */
    public Integer getIsFullReturnSpendTimeOverAverage() {
        return isFullReturnSpendTimeOverAverage;
    }

    /**
     * 设置退货退款自主完结时长是否大于行业均值
     *
     * @param isFullReturnSpendTimeOverAverage 退货退款自主完结时长是否大于行业均值
     */
    public void setIsFullReturnSpendTimeOverAverage(Integer isFullReturnSpendTimeOverAverage) {
        this.isFullReturnSpendTimeOverAverage = isFullReturnSpendTimeOverAverage;
    }

    /**
     * 获取退款自主完结率
     *
     * @return auto_refund_rate - 退款自主完结率
     */
    public Double getAutoRefundRate() {
        return autoRefundRate;
    }

    /**
     * 设置退款自主完结率
     *
     * @param autoRefundRate 退款自主完结率
     */
    public void setAutoRefundRate(Double autoRefundRate) {
        this.autoRefundRate = autoRefundRate;
    }

    /**
     * 获取退款自主完结率行业均值
     *
     * @return auto_refund_average_rate - 退款自主完结率行业均值
     */
    public Double getAutoRefundAverageRate() {
        return autoRefundAverageRate;
    }

    /**
     * 设置退款自主完结率行业均值
     *
     * @param autoRefundAverageRate 退款自主完结率行业均值
     */
    public void setAutoRefundAverageRate(Double autoRefundAverageRate) {
        this.autoRefundAverageRate = autoRefundAverageRate;
    }

    /**
     * 获取退款自主完结率是否大于行业均值
     *
     * @return is_auto_refund_rate_over_average - 退款自主完结率是否大于行业均值
     */
    public Integer getIsAutoRefundRateOverAverage() {
        return isAutoRefundRateOverAverage;
    }

    /**
     * 设置退款自主完结率是否大于行业均值
     *
     * @param isAutoRefundRateOverAverage 退款自主完结率是否大于行业均值
     */
    public void setIsAutoRefundRateOverAverage(Integer isAutoRefundRateOverAverage) {
        this.isAutoRefundRateOverAverage = isAutoRefundRateOverAverage;
    }

    /**
     * 获取最近一周好评数
     *
     * @return good_comments_last_one_week - 最近一周好评数
     */
    public Integer getGoodCommentsLastOneWeek() {
        return goodCommentsLastOneWeek;
    }

    /**
     * 设置最近一周好评数
     *
     * @param goodCommentsLastOneWeek 最近一周好评数
     */
    public void setGoodCommentsLastOneWeek(Integer goodCommentsLastOneWeek) {
        this.goodCommentsLastOneWeek = goodCommentsLastOneWeek;
    }

    /**
     * 获取最近一周中评数
     *
     * @return normal_comments_last_one_week - 最近一周中评数
     */
    public Integer getNormalCommentsLastOneWeek() {
        return normalCommentsLastOneWeek;
    }

    /**
     * 设置最近一周中评数
     *
     * @param normalCommentsLastOneWeek 最近一周中评数
     */
    public void setNormalCommentsLastOneWeek(Integer normalCommentsLastOneWeek) {
        this.normalCommentsLastOneWeek = normalCommentsLastOneWeek;
    }

    /**
     * 获取最近一周差评数
     *
     * @return bad_comments_last_one_week - 最近一周差评数
     */
    public Integer getBadCommentsLastOneWeek() {
        return badCommentsLastOneWeek;
    }

    /**
     * 设置最近一周差评数
     *
     * @param badCommentsLastOneWeek 最近一周差评数
     */
    public void setBadCommentsLastOneWeek(Integer badCommentsLastOneWeek) {
        this.badCommentsLastOneWeek = badCommentsLastOneWeek;
    }

    /**
     * 获取最近一月好评数
     *
     * @return good_comments_last_one_month - 最近一月好评数
     */
    public Integer getGoodCommentsLastOneMonth() {
        return goodCommentsLastOneMonth;
    }

    /**
     * 设置最近一月好评数
     *
     * @param goodCommentsLastOneMonth 最近一月好评数
     */
    public void setGoodCommentsLastOneMonth(Integer goodCommentsLastOneMonth) {
        this.goodCommentsLastOneMonth = goodCommentsLastOneMonth;
    }

    /**
     * 获取最近一月中评数
     *
     * @return normal_comments_last_one_month - 最近一月中评数
     */
    public Integer getNormalCommentsLastOneMonth() {
        return normalCommentsLastOneMonth;
    }

    /**
     * 设置最近一月中评数
     *
     * @param normalCommentsLastOneMonth 最近一月中评数
     */
    public void setNormalCommentsLastOneMonth(Integer normalCommentsLastOneMonth) {
        this.normalCommentsLastOneMonth = normalCommentsLastOneMonth;
    }

    /**
     * 获取最近一月差评数
     *
     * @return bad_comments_last_one_month - 最近一月差评数
     */
    public Integer getBadCommentsLastOneMonth() {
        return badCommentsLastOneMonth;
    }

    /**
     * 设置最近一月差评数
     *
     * @param badCommentsLastOneMonth 最近一月差评数
     */
    public void setBadCommentsLastOneMonth(Integer badCommentsLastOneMonth) {
        this.badCommentsLastOneMonth = badCommentsLastOneMonth;
    }

    /**
     * 获取最近半年好评数
     *
     * @return good_comments_last_half_year - 最近半年好评数
     */
    public Integer getGoodCommentsLastHalfYear() {
        return goodCommentsLastHalfYear;
    }

    /**
     * 设置最近半年好评数
     *
     * @param goodCommentsLastHalfYear 最近半年好评数
     */
    public void setGoodCommentsLastHalfYear(Integer goodCommentsLastHalfYear) {
        this.goodCommentsLastHalfYear = goodCommentsLastHalfYear;
    }

    /**
     * 获取最近半年中评数
     *
     * @return normal_comments_last_half_year - 最近半年中评数
     */
    public Integer getNormalCommentsLastHalfYear() {
        return normalCommentsLastHalfYear;
    }

    /**
     * 设置最近半年中评数
     *
     * @param normalCommentsLastHalfYear 最近半年中评数
     */
    public void setNormalCommentsLastHalfYear(Integer normalCommentsLastHalfYear) {
        this.normalCommentsLastHalfYear = normalCommentsLastHalfYear;
    }

    /**
     * 获取最近半年差评数
     *
     * @return bad_comments_last_half_year - 最近半年差评数
     */
    public Integer getBadCommentsLastHalfYear() {
        return badCommentsLastHalfYear;
    }

    /**
     * 设置最近半年差评数
     *
     * @param badCommentsLastHalfYear 最近半年差评数
     */
    public void setBadCommentsLastHalfYear(Integer badCommentsLastHalfYear) {
        this.badCommentsLastHalfYear = badCommentsLastHalfYear;
    }

    /**
     * 获取半年以前好评数
     *
     * @return good_comments_half_year_ago - 半年以前好评数
     */
    public Integer getGoodCommentsHalfYearAgo() {
        return goodCommentsHalfYearAgo;
    }

    /**
     * 设置半年以前好评数
     *
     * @param goodCommentsHalfYearAgo 半年以前好评数
     */
    public void setGoodCommentsHalfYearAgo(Integer goodCommentsHalfYearAgo) {
        this.goodCommentsHalfYearAgo = goodCommentsHalfYearAgo;
    }

    /**
     * 获取半年以前中评数
     *
     * @return normal_comments_half_year_ago - 半年以前中评数
     */
    public Integer getNormalCommentsHalfYearAgo() {
        return normalCommentsHalfYearAgo;
    }

    /**
     * 设置半年以前中评数
     *
     * @param normalCommentsHalfYearAgo 半年以前中评数
     */
    public void setNormalCommentsHalfYearAgo(Integer normalCommentsHalfYearAgo) {
        this.normalCommentsHalfYearAgo = normalCommentsHalfYearAgo;
    }

    /**
     * 获取半年以前差评数
     *
     * @return bad_comments_half_year_ago - 半年以前差评数
     */
    public Integer getBadCommentsHalfYearAgo() {
        return badCommentsHalfYearAgo;
    }

    /**
     * 设置半年以前差评数
     *
     * @param badCommentsHalfYearAgo 半年以前差评数
     */
    public void setBadCommentsHalfYearAgo(Integer badCommentsHalfYearAgo) {
        this.badCommentsHalfYearAgo = badCommentsHalfYearAgo;
    }

    /**
     * @return save_time
     */
    public Date getSaveTime() {
        return saveTime;
    }

    /**
     * @param saveTime
     */
    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    /**
     * 获取批次编号
     *
     * @return batch_no - 批次编号
     */
    public Integer getBatchNo() {
        return batchNo;
    }

    /**
     * 设置批次编号
     *
     * @param batchNo 批次编号
     */
    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }
}