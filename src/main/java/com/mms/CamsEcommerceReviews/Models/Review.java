package com.mms.CamsEcommerceReviews.Models;

import lombok.Data;

@Data
public class Review {
    private String title;
    private String text;
    private User author;
}
