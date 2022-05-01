package com.itheima.ssm.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.text.ParseException;
import java.util.Date;

public class DateStringEditor extends PropertiesEditor {

    @Override             //这个text参数其实就是表单中提交的数据，虽然是个日期，但是是一个字符串类型的，重写这个方法，实现Date类型的转换
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Date date = DateUtils.string2Date(text, "yyyy-MM-dd HH:mm");
            super.setValue(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
