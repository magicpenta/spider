package com.panda.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 淘宝买家积分
 *
 * @author panda
 * @date 2018/11/11
 */
public class TaobaoRankUtil {

    private static final Logger logger = LoggerFactory.getLogger(TaobaoRankUtil.class);

    public static int getRank(String rankStr) {
        int rank = 0;
        int count = Integer.valueOf(CommonUtil.match(rankStr, "(\\d+)")[1]);
        if (rankStr.startsWith("b_red_")) {
            if (count == 1) {
                rank = 4;
            } else if (count == 2) {
                rank = 11;
            } else if (count == 3) {
                rank = 41;
            } else if (count == 4) {
                rank = 91;
            } else if (count == 5) {
                rank = 151;
            }
        } else if (rankStr.startsWith("b_blue_")) {
            if (count == 1) {
                rank = 251;
            } else if (count == 2) {
                rank = 501;
            } else if (count == 3) {
                rank = 1001;
            } else if (count == 4) {
                rank = 2001;
            } else if (count == 5) {
                rank = 5001;
            }
        } else if (rankStr.startsWith("b_cap_")) {
            if (count == 1) {
                rank = 10001;
            } else if (count == 2) {
                rank = 20001;
            } else if (count == 3) {
                rank = 50001;
            } else if (count == 4) {
                rank = 100001;
            } else if (count == 5) {
                rank = 200001;
            }
        } else {
            rank = 99999999;
            logger.info("rank: " + rank);
        }
//         else if (rankStr.startsWith("")) {
//            if (count == 1) {
//                rank = 500001;
//            } else if (count == 2) {
//                rank = 1000001;
//            } else if (count == 3) {
//                rank = 2000001;
//            } else if (count == 4) {
//                rank = 5000001;
//            } else if (count == 5) {
//                rank = 10000001;
//            }
//        }
        return rank;
    }
}
