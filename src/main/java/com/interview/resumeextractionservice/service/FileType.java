package com.interview.resumeextractionservice.service;

public enum FileType {
    PDF,
    DOCX,
    DOC,
    TXT

};

class FileTypeUtil {
    public static boolean contains(String test) {
        for (FileType fileType : FileType.values()) {
            if (fileType.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
