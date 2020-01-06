package com.ahzl.controller;

import com.ahzl.utils.FtpUtils;

import java.io.File;

/**
 * <p>
 * <p>Title:TestSftp.java</p >
 * <p>Description: </p >
 * <p>Copyright: 公共服务与应急管理战略业务本部 Copyright(c)2019</p >
 * <p>Date:2020/1/6 15:22</p >
 *
 * @author guoyu (guoyu@mail.taiji.com.cn)
 * @version 1.0
 */
public class TestSftp {
    public static void main(String[] args) {
        try {
//            FtpUtils.sshSftp(FtpUtils.getContent("D:\\壁纸\\头像\\back.jpg"),"a");
            FtpUtils.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
