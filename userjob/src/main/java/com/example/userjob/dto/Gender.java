package com.example.userjob.dto;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");
    private String code;
    Gender(String code) {
        this.code = code;
    }
    public String toString() {
        return code;
    }
    public static Gender fromString(String code) {
        if(code.equals("MALE")) {
            return MALE;
        } else if(code.equals("FEMALE")){
            return FEMALE;
        }
        throw new RuntimeException(); //todo Обработка ошибок
    }
}
