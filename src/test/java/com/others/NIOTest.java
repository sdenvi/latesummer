package com.others;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by IntelliJ IDEA.
 * User: someone
 * Date:2017/12/12
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class NIOTest {

    public void readFile() {
        /**
         * 从文件中读取数据需要三步
         * 1.从FileInputStream获取Channel
         * 2.创建Buffer
         * 3.从Channel读取数据到Buffer
         */
        try {
            /**
             * 获取通道
             */
            FileInputStream fin = new FileInputStream("D:\\my-project\\test.xml");
            FileChannel fc = fin.getChannel();
            /**
             * 创建缓冲区
             */
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            /**
             * 将数据从通道读到缓冲区
             */
            fc.read(buffer);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用java NIO api拷贝文件
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void copyFileUseNIO(String src,String dst) throws IOException {
        // 声明原文件和目标文件
        FileInputStream fi = new FileInputStream(new File(src));
        FileOutputStream fo = new FileOutputStream(new File(dst));
        // 获取channel通道
        FileChannel inChannel = fi.getChannel();
        FileChannel outChannel = fo.getChannel();
        // 获取容器buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024000);
        while (true){
            // 判断是否读完文件
            int eof = inChannel.read(buffer);
            if (eof == -1){
                break;
            }
            // 重设一下buffer的position=0，limit=position
            buffer.flip();
            // 开始写
            outChannel.write(buffer);
            // 写完要重置buffer，重设position=0,limit=capacity
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        fi.close();
        fo.close();
    }


    public static void main(String[] args) {
        try {
            copyFileUseNIO("D:\\my-project\\test.xml","D:\\my-project\\write.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
