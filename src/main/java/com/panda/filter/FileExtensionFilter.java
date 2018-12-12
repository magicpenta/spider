package com.panda.filter;

/**
 * 文件扩展名过滤器
 *
 * @author panda
 * @date 2017/12/04
 */
public class FileExtensionFilter implements LinkFilter {

    private String[] fileExtentinons;

    public FileExtensionFilter(String fileExtentinon) {
        fileExtentinons = new String[]{fileExtentinon.toLowerCase()};
    }

    public FileExtensionFilter(String[] fileExtentinons) {
        this.fileExtentinons = new String[fileExtentinons.length];
        for (int i = 0; i < fileExtentinons.length; i++) {
            this.fileExtentinons[i] = fileExtentinons[i].toLowerCase();
        }
    }

    public boolean accept(String link) {
        if (link == null || link.equals("")) {
            return false;
        }

        if (link.toLowerCase().endsWith(".html") || link.toLowerCase().endsWith(".htm")
                || link.toLowerCase().endsWith(".shtml")) {
            return true;
        }

        for (int i = 0; i < fileExtentinons.length; i++) {
            if (link.toLowerCase().endsWith(fileExtentinons[i])) {
                return false;
            }
        }

        return true;
    }

}
