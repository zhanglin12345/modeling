package com.example.modeling.biz;

public interface OrderDeliverable {
    void outStockOrder(String orderId);
    void deliverOrder(String orderId);
    void deliveredOrder(String orderId);
}
