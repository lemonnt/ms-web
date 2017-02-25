/**
 * @author : Gavin Li
 * @date   : Nov 19, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.util.Test
 */
package com.lemonnt.ms.common.bean;

import java.util.*;

/**
 * @author : Gavin Li
 * @date   : Nov 19, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.common.util.Permutation
 */
public class Permutation {
    private char[] charaters;
    private int total = 0;
    private int length;
    private String inStr= "";
    private String original;
    private List<Integer> tempParameter = new ArrayList<Integer>();
    private List<String> result = new ArrayList<String>();

    public Permutation(String original,int length) {
        this.original = original;
        this.length = length;
    }
    
    public Permutation(){};
    
    public List<String> permutation(){
        if(null == original || "".equals(original)) return result;
        charaters = this.original.toCharArray();
        permutation(this.inStr,tempParameter,length);
        return result;
    }

    private void permutation(String s, List<Integer> tempParameter, int m) {
        if(m == 0) {
            result.add(s);
            total++;
            return;
        }
        List<Integer> tempParameter2;
        for(int i = 0; i < charaters.length; i++) {
            tempParameter2 = new ArrayList<Integer>();
            tempParameter2.addAll(tempParameter);
            if(!tempParameter.contains(i)) {
                tempParameter2.add(i);
                permutation(s + charaters[i], tempParameter2, m-1);
            }
        }
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original the original to set
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * @return the result
     */
    public List<String> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<String> result) {
        this.result = result;
    }
    
}
