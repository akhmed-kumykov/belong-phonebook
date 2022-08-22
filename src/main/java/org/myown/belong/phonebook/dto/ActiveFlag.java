package org.myown.belong.phonebook.dto;

public enum ActiveFlag {
        ACTIVE("1"), INACTIVE("0");
    public String code;

    ActiveFlag(String code) {
        this.code = code;
    }

    public static ActiveFlag parse(String code){
        for (ActiveFlag flag : values()){
            if (flag.code.equalsIgnoreCase(code))
                return flag;
        }
        return null;
    }
}
