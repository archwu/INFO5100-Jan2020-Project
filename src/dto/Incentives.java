package dto;

import persist.IncentivesManager;

import java.util.Date;

public class Incentives extends BigDataType{
    private int incentiveId;
    private String title;
    private String description;
    private String disclaimer;
    private Date startDate;
    private Date endDate;
    private int discountValue;
    private String discountType;

    public int getIncentiveId() {
        return incentiveId;
    }

    public void setIncentiveId(int incentiveId) {
        this.incentiveId = incentiveId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
}