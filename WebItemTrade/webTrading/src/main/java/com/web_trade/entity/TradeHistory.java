package com.web_trade.entity;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "trade_history")
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(nullable = false)
    private Date tradeDate;

    @Column(nullable = false)
    private double tradePrice;

    @Column(nullable = false)
    private int quantity;

    // Other relevant columns can be added as needed
}