package com.zhy.drift.bean;

public class User implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2331550680245441640L;

    private Long id;

    private String name;
    
    private String wxUserName;

    private String sex;
    
    private Integer age;
    
    private String city;
    
    private Boolean isValid;
    
    private Boolean isRobot;
    
    public User(){
        
    }
    
    public User(String wxUserName){
        this.wxUserName = wxUserName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWxUserName() {
        return wxUserName;
    }

    public void setWxUserName(String wxUserName) {
        this.wxUserName = wxUserName;
    }

    public Boolean getIsRobot() {
        return isRobot;
    }

    public void setIsRobot(Boolean isRobot) {
        this.isRobot = isRobot;
    }
}
