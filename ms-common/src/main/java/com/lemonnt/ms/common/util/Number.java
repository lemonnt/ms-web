/**
 * @author : Gavin Li
 * @date   : Nov 9, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.util.Number
 */
package com.lemonnt.ms.common.util;


import com.lemonnt.ms.common.file.csv.CSVField;
import com.lemonnt.ms.common.file.csv.CSVHeader;

/**
 * @author : Gavin Li
 * @date   : Nov 9, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.util.Number
 */
public class Number {
    
    private String name;
    @CSVField(sort=2)
    @CSVHeader("CellphoneNumber")
    private String number;
    private String gender;
    
    
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }
    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
    
    

}
