package com.example.hibernate.pojo;

import com.example.Reasons;
import com.example.Venue;

public class Item {
    private Reasons reasons;
    private Venue venue;
    private String referralId;

    public Reasons getReasons() {
        return reasons;
    }

    public void setReasons(Reasons reasons) {
        this.reasons = reasons;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }
}
