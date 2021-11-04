/**
 Discount - Respective discounts for each membership tier
 @author Yi Jie
 @version 1.0
 @since 2021-10-23
*/

package main;
public class Discount {
    public enum discountType {
        
        /**
        * MEMBER - 10 percent discount
        */
        MEMBER(10.0),
        
        /**
        * SILVER - 30 percent discount
        */
        SILVER(30.0),

        /**
        * GOLD - 50 percent discount
        */
        GOLD(50.0);
        
        // Attribute for constructor
        private double discount;
        
        // Constructor   
        private discountType(double discount) {
            this.discount = discount;
        }
        
        // Accessor to get discount
        public double getDiscount() {
            return discount;
        }
    }
}