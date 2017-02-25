/**
 * @author : Gavin Li
 * @date   : Nov 15, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.bean.MemberQueryParameters
 */
package com.lemonnt.ms.member.bean;


/**
 * @author : Gavin Li
 * @date   : Nov 15, 2016
 * @version : 1.0
 * @class  :com.lemonnt.ms.member.bean.MemberQueryParameters
 */
public class MemberQueryParameters{

    private String search;
    private String startDate;
    private String endDate;
    private Integer offset;
    private Integer limit;
    private Integer from;
    private Integer to;
    private String order;
    private String sort;
    
    
    
    
    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }
    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }
    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }
    /**
     * @param sort the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }
    /**
     * @return the from
     */
    public Integer getFrom() {
        return from;
    }
    /**
     * @param from the from to set
     */
    public void setFrom(Integer from) {
        this.from = from;
    }
    /**
     * @return the to
     */
    public Integer getTo() {
        return to;
    }
    /**
     * @param to the to to set
     */
    public void setTo(Integer to) {
        this.to = to;
    }
    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }
    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }
    
   
    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    /**
     * @return the offset
     */
    public Integer getOffset() {
        return offset;
    }
    /**
     * @param offset the offset to set
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }
    /**
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public String toString(){
        return "startDate : "+startDate+",endDate : "+endDate+", search : "+search+",sort : "+sort;
    }

}
