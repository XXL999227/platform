package cn.xxl.platform;

public class test {
    public static void main(String[] args) {
        String path = "http://www.baidu.com/http";
        String path2 = "https://www.baidu.com/https";
        String path3 = "www.baidu.com/https";
        System.out.println(getPath(path));
        System.out.println(getPath(path2));
        System.out.println(getPath(path3));
    }

    /**
     * 获取路径
     *
     * @param path 路径
     * @return {@link String}
     */
    public static String getPath(String path) {
        return "";
    }
}
