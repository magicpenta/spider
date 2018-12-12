package com.panda.entity.taobao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "${append_table}")
public class TaobaoAppendDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 卖家ID
     */
    @Column(name = "seller_id")
    private Long sellerId;

    /**
     * 评论ID
     */
    @Column(name = "comment_id")
    private Long commentId;

    /**
     * 追评ID
     */
    @Column(name = "append_id")
    private Long appendId;

    /**
     * 追评与确认收货时间差
     */
    @Column(name = "day_after_confirm")
    private Integer dayAfterConfirm;

    /**
     * 追评时间
     */
    @Column(name = "append_time")
    private Date appendTime;

    /**
     * 追评图片数
     */
    @Column(name = "photo_num")
    private Integer photoNum;

    /**
     * 是否有商家回复
     */
    @Column(name = "has_reply")
    private Integer hasReply;

    /**
     * 入库时间
     */
    @Column(name = "save_time")
    private Date saveTime;

    /**
     * 追评内容
     */
    private String content;

    /**
     * 追评图片链接
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
     * 获取追评ID
     *
     * @return append_id - 追评ID
     */
    public Long getAppendId() {
        return appendId;
    }

    /**
     * 设置追评ID
     *
     * @param appendId 追评ID
     */
    public void setAppendId(Long appendId) {
        this.appendId = appendId;
    }

    /**
     * 获取追评与确认收货时间差
     *
     * @return day_after_confirm - 追评与确认收货时间差
     */
    public Integer getDayAfterConfirm() {
        return dayAfterConfirm;
    }

    /**
     * 设置追评与确认收货时间差
     *
     * @param dayAfterConfirm 追评与确认收货时间差
     */
    public void setDayAfterConfirm(Integer dayAfterConfirm) {
        this.dayAfterConfirm = dayAfterConfirm;
    }

    /**
     * 获取追评时间
     *
     * @return append_time - 追评时间
     */
    public Date getAppendTime() {
        return appendTime;
    }

    /**
     * 设置追评时间
     *
     * @param appendTime 追评时间
     */
    public void setAppendTime(Date appendTime) {
        this.appendTime = appendTime;
    }

    /**
     * 获取追评图片数
     *
     * @return photo_num - 追评图片数
     */
    public Integer getPhotoNum() {
        return photoNum;
    }

    /**
     * 设置追评图片数
     *
     * @param photoNum 追评图片数
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
     * 获取追评内容
     *
     * @return content - 追评内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置追评内容
     *
     * @param content 追评内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取追评图片链接
     *
     * @return photo_path - 追评图片链接
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * 设置追评图片链接
     *
     * @param photoPath 追评图片链接
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