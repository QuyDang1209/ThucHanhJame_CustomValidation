package com.cg.demovalidaton_customvalidation.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
@Component
public class User implements Validator {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String phone;
    private String address;

//    public User() {
//    }
//
//    public User(String name, LocalDate dob, String phone, String address) {
//        this.name = name;
//        this.dob = dob;
//        this.phone = phone;
//        this.address = address;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String name = user.getName();
        LocalDate dob = user.getDob();
        String phone = user.getPhone();
        String address = user.getAddress();
        ValidationUtils.rejectIfEmpty(errors,"name", "name.emty", "Tên không được để trống");
        ValidationUtils.rejectIfEmpty(errors,"dob", "dob.emty", "Ngày tháng nhập theo dạng DD-MM-YY, không được để trống");
        ValidationUtils.rejectIfEmpty(errors,"phone", "phone.emty", "Số điện thoại không được để trống");
        if (phone.length()>11 || phone.length()<10){
            errors.rejectValue("phone", "phone.length", "Số điện thoại gồm 11 số");
        }
        if (!phone.startsWith("0")){
            errors.rejectValue("phone", "phone.startsWith", "Số điện thoại phải bắt đầu từ số 0");
        }
        if (!phone.matches("(^$|[0-9]*$)")){
            errors.rejectValue("phone", "phone.matches", "Số điện thoại phải bao gồm 10 số");
        }
        ValidationUtils.rejectIfEmpty(errors,"address", "address.emty", "Địa chỉ không được để trống");

    }
}
