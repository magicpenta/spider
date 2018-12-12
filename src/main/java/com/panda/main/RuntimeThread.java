package com.panda.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 进程消息回收线程
 * 防止 Runtime 挂起
 *
 * @author panda
 * @date 2018/1/14
 */
public class RuntimeThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(RuntimeThread.class);

    private BufferedReader bf;

    public RuntimeThread(InputStream input) {
        bf = new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public void run() {
        String line;
        try {
            line = bf.readLine();
            while (line != null) {
//                logger.info(line);
                line = bf.readLine();
            }
        } catch (IOException e) {
            logger.error("回收多进程消息异常:", e);
        }
    }
}