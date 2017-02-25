/**
 * @author : Gavin Li
 * @date   : Oct 31, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.bean.MemberBasicInformation
 */
package com.lemonnt.ms.member.bean;

import java.util.Date;
import com.lemonnt.ms.common.search.Search;
import com.lemonnt.ms.common.search.SearchUtil;

/**
 * @author : Gavin Li
 * @date   : Oct 31, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.login.bean.MemberBasicInformation
 */
public class MemberBasicInformation {
    private String id;
    @Search("name")
    private String userName;
    @Search
    private String alias;
    @Search
    private String gender;
    @Search("cellphone")
    private String cellphoneNumber;
    @Search
    private String email;
    @Search("express")
    private String expression;
    @Search
    private String prefession;
    @Search("EDUCATION")
    private String educationBackground;
    private String income;
    private Integer height;
    private Integer weight;
    @Search
    private Integer age;
    @Search
    private String address;
    @Search
    private Date createdDate;
    @Search
    private String description;
    @Search
    private String province;
    @Search
    private String city;
    @Search
    private String area;
    private Date birthday;
    //积分
    private Integer score;
    //1--- > 一星 2---> 二星  一直累加
    private String level;
    
    private Integer number;
    @Search
    private String platform;
    
    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }
    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }
    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }
    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }
    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }
    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }
    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }
    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }
    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    /**
     * @return the score
     */
    public Integer getScore() {
        return score;
    }
    /**
     * @param score the score to set
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }
    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * @return the cellphoneNumber
     */
    public String getCellphoneNumber() {
        return cellphoneNumber;
    }
    /**
     * @param cellphoneNumber the cellphoneNumber to set
     */
    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the expression
     */
    public String getExpression() {
        return expression;
    }
    /**
     * @param expression the expression to set
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }
    /**
     * @return the prefession
     */
    public String getPrefession() {
        return prefession;
    }
    /**
     * @param prefession the prefession to set
     */
    public void setPrefession(String prefession) {
        this.prefession = prefession;
    }
    /**
     * @return the educationBackground
     */
    public String getEducationBackground() {
        return educationBackground;
    }
    /**
     * @param educationBackground the educationBackground to set
     */
    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }
    
    
    /**
     * @return the income
     */
    public String getIncome() {
        return income;
    }
    /**
     * @param income the income to set
     */
    public void setIncome(String income) {
        this.income = income;
    }
    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }
    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }
    /**
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }
    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }
    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    public static void main(String[] args) {
      /*  Map<String, String> result = SearchUtil.searchField(MemberBasicInformation.class);
        for(Map.Entry<String, String> r : result.entrySet()){
            String key = r.getKey();
            Object value = r.getValue();
            System.out.println(key);
            System.out.println(value.toString());
        }*/
        System.out.println(SearchUtil.search("name:湖南省", MemberBasicInformation.class));
    }


}
