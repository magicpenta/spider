package com.panda.entity.taobao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "${comment_table}")
public class TaobaoCommentDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 评论ID
     */
    @Column(name = "comment_id")
    private Long commentId;

    /**
     * 评论等级（1-好评;0-中评;-1-差评）
     */
    @Column(name = "comment_level")
    private Integer commentLevel;

    /**
     * 评论时间
     */
    @Column(name = "comment_time")
    private Date commentTime;

    /**
     * 是否默认评价
     */
    @Column(name = "if_level_default")
    private Integer ifLevelDefault;

    /**
     * 是否未填写评论内容
     */
    @Column(name = "if_content_empty")
    private Integer ifContentEmpty;

    /**
     * 评论图片数
     */
    @Column(name = "photo_num")
    private Integer photoNum;

    /**
     * 是否有商家回复
     */
    @Column(name = "has_reply")
    private Integer hasReply;

    /**
     * 追评数量
     */
    @Column(name = "append_num")
    private Integer appendNum;

    /**
     * 是否不计分
     */
    @Column(name = "valid_score")
    private Integer validScore;

    /**
     * 买家用户名
     */
    private String username;

    /**
     * 买家信用分
     */
    private String rank;

    /**
     * 买家VIP等级
     */
    @Column(name = "vip_level")
    private Integer vipLevel;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品价格
     */
    @Column(name = "goods_price")
    private String goodsPrice;

    /**
     * 商品颜色分类
     */
    @Column(name = "goods_color")
    private String goodsColor;

    /**
     * 商品套餐类型
     */
    @Column(name = "goods_combo")
    private String goodsCombo;

    /**
     * 商品其他明细
     */
    @Column(name = "goods_other_sku")
    private String goodsOtherSku;

    /**
     * 入库时间
     */
    @Column(name = "save_time")
    private Date saveTime;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论图片链接
     */
    @Column(name = "photo_path")
    private String photoPath;

    /**
     * 商家回复内容
     */
    private String reply;

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
     * 获取评论ID
     *
     * @return comment_id - 评论ID
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * 设置评论ID
     *
     * @param commentId 评论ID
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取评论等级（1-好评;0-中评;-1-差评）
     *
     * @return comment_level - 评论等级（1-好评;0-中评;-1-差评）
     */
    public Integer getCommentLevel() {
        return commentLevel;
    }

    /**
     * 设置评论等级（1-好评;0-中评;-1-差评）
     *
     * @param commentLevel 评论等级（1-好评;0-中评;-1-差评）
     */
    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    /**
     * 获取评论时间
     *
     * @return comment_time - 评论时间
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * 设置评论时间
     *
     * @param commentTime 评论时间
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * 获取是否默认评价
     *
     * @return if_level_default - 是否默认评价
     */
    public Integer getIfLevelDefault() {
        return ifLevelDefault;
    }

    /**
     * 设置是否默认评价
     *
     * @param ifLevelDefault 是否默认评价
     */
    public void setIfLevelDefault(Integer ifLevelDefault) {
        this.ifLevelDefault = ifLevelDefault;
    }

    /**
     * 获取是否未填写评论内容
     *
     * @return if_content_empty - 是否未填写评论内容
     */
    public Integer getIfContentEmpty() {
        return ifContentEmpty;
    }

    /**
     * 设置是否未填写评论内容
     *
     * @param ifContentEmpty 是否未填写评论内容
     */
    public void setIfContentEmpty(Integer ifContentEmpty) {
        this.ifContentEmpty = ifContentEmpty;
    }

    /**
     * 获取评论图片数
     *
     * @return photo_num - 评论图片数
     */
    public Integer getPhotoNum() {
        return photoNum;
    }

    /**
     * 设置评论图片数
     *
     * @param photoNum 评论图片数
     */
    public void setPhotoNum(Integer photoNum) {
        this.photoNum = photoNum;
    }

    /**
     * 获取是否有商家回复
     *
     * @return has_reply - 是否有商家回复
     */
    public Integer getHasReply() {
        return hasReply;
    }

    /**
     * 设置是否有商家回复
     *
     * @param hasReply 是否有商家回复
     */
    public void setHasReply(Integer hasReply) {
        this.hasReply = hasReply;
    }

    /**
     * 获取追评数量
     *
     * @return append_num - 追评数量
     */
    public Integer getAppendNum() {
        return appendNum;
    }

    /**
     * 设置追评数量
     *
     * @param appendNum 追评数量
     */
    public void setAppendNum(Integer appendNum) {
        this.appendNum = appendNum;
    }

    /**
     * 获取是否不计分
     *
     * @return valid_score - 是否不计分
     */
    public Integer getValidScore() {
        return validScore;
    }

    /**
     * 设置是否不计分
     *
     * @param validScore 是否不计分
     */
    public void setValidScore(Integer validScore) {
        this.validScore = validScore;
    }

    /**
     * 获取买家用户名
     *
     * @return username - 买家用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置买家用户名
     *
     * @param username 买家用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取买家信用分
     *
     * @return rank - 买家信用分
     */
    public String getRank() {
        return rank;
    }

    /**
     * 设置买家信用分
     *
     * @param rank 买家信用分
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 获取买家VIP等级
     *
     * @return vip_level - 买家VIP等级
     */
    public Integer getVipLevel() {
        return vipLevel;
    }

    /**
     * 设置买家VIP等级
     *
     * @param vipLevel 买家VIP等级
     */
    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品价格
     *
     * @return goods_price - 商品价格
     */
    public String getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品价格
     *
     * @param goodsPrice 商品价格
     */
    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取商品颜色分类
     *
     * @return goods_color - 商品颜色分类
     */
    public String getGoodsColor() {
        return goodsColor;
    }

    /**
     * 设置商品颜色分类
     *
     * @param goodsColor 商品颜色分类
     */
    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    /**
     * 获取商品套餐类型
     *
     * @return goods_combo - 商品套餐类型
     */
    public String getGoodsCombo() {
        return goodsCombo;
    }

    /**
     * 设置商品套餐类型
     *
     * @param goodsCombo 商品套餐类型
     */
    public void setGoodsCombo(String goodsCombo) {
        this.goodsCombo = goodsCombo;
    }

    /**
     * 获取商品其他明细
     *
     * @return goods_other_sku - 商品其他明细
     */
    public String getGoodsOtherSku() {
        return goodsOtherSku;
    }

    /**
     * 设置商品其他明细
     *
     * @param goodsOtherSku 商品其他明细
     */
    public void setGoodsOtherSku(String goodsOtherSku) {
        this.goodsOtherSku = goodsOtherSku;
    }

    /**
     * 获取入库时间
     *
     * @return save_time - 入库时间
     */
    public Date getSaveTime() {
        return saveTime;
    }

    /**
     * 设置入库时间
     *
     * @param saveTime 入库时间
     */
    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论图片链接
     *
     * @return photo_path - 评论图片链接
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * 设置评论图片链接
     *
     * @param photoPath 评论图片链接
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    /**
     * 获取商家回复内容
     *
     * @return reply - 商家回复内容
     */
    public String getReply() {
        return reply;
    }

    /**
     * 设置商家回复内容
     *
     * @param reply 商家回复内容
     */
    public void setReply(String reply) {
        this.reply = reply;
    }
}