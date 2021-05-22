package com.mms.CamsEcommerceReviews.Review;

import com.mms.CamsEcommerceReviews.Product.Product;
import com.mms.CamsEcommerceReviews.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    @ManyToOne
    private User author;
    @ManyToOne
    private Product product;
}
