package com.example.userjob.dto;

public enum Gender {
    MALE('m'),
    FEMALE('f');
    private char code;
    Gender(char code) {
        this.code = code;
    }
    public String toString() {
        return String.valueOf(code);
    }
    public static Gender fromString(String code) {
        if(code.equals("m")) {
            return MALE;
        } else if(code.equals("f")){
            return FEMALE;
        }
        throw new RuntimeException(); //todo Обработка ошибок
    }
}
