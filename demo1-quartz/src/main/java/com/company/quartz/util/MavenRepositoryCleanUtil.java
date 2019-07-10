package com.company.quartz.util;

import java.io.File;

public class MavenRepositoryCleanUtil {

    private static final String[] SUFFIX = {".lastUpdated", "jar-in-progress"};

    public static void main(String[] args) {
        clean("E:\\tomcat\\apache-maven-3.6.0\\lib\\");
    }

    public static void clean(String path){

        File mavenRep=new File(path);

        if(!mavenRep.exists()){
            System.out.println("Maven repos is not exist!");
            return;
        }

        File[] files = mavenRep.listFiles();

        execute(files);
    }

    /**
     * 存在问题1：需要多次执行才能删干净，说明递归没有遍历到最深处
     * 存在问题2: 上层是文件夹，下层是空文件夹，只是删除了下层的空文件夹
     * @param files
     */
    private static void execute(File[] files){
        for (File file : files) {
            if(file.isDirectory()){
                if(file.listFiles()==null || file.listFiles().length==0){
                    //删除空文件夹
                    System.out.println("empty folder "+file.getAbsolutePath());
                    file.delete();
//                    file.getParentFile()
                }else{
                    //递归执行
                    execute(file.listFiles());
                }
            }else {
                for (int i = 0; i < SUFFIX.length; i++) {
                    if (file.getName().contains(SUFFIX[i])) {
                        System.out.println("delete file "+file.getName());
                        //删除文件
                        file.delete();
                    }
                }

            }
        }
    }
}
