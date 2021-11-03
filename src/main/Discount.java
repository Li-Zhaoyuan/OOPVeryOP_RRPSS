/**
 Discount - Respective discounts for each membership tier
 @author Chua Yi Jie
 @version 1.0
 @since 2021-10-23
*/

package main;
public class Discount {
    public enum discountType {
        
        /**
        * MEMBER - 10 percent discount
        */
        MEMBER(10),
        
        /**
        * SILVER - 30 percent discount
        */
        SILVER(30),

        /**
        * GOLD - 50 percent discount
        */
        GOLD(50);
        
        // Attribute for constructor
        private int discount;
        
        // Constructor   
        private discountType(int discount) {
            this.discount = discount;
        }
        
        // Accessor to get discount
        public int getDiscount() {
            return discount;
        }
    }
}