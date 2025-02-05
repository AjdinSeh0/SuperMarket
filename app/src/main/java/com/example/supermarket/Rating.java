package com.example.supermarket;

public class Rating {
    private int ratingId;
    private float liquorRating;
    private float produceRating;
    private float meatRating;
    private float cheeseRating;
    private float checkoutRating;


    public Rating(){
        ratingId = -1;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public float getLiquorRating() {
        return liquorRating;
    }

    public void setLiquorRating(float liquorRating) {
        this.liquorRating = liquorRating;
    }

    public float getMeatRating() {
        return meatRating;
    }

    public void setMeatRating(float meatRating) {
        this.meatRating = meatRating;
    }

    public float getCheeseRating() {
        return cheeseRating;
    }

    public void setCheeseRating(float cheeseRating) {
        this.cheeseRating = cheeseRating;
    }

    public float getProduceRating() {
        return produceRating;
    }

    public void setProduceRating(float produceRating) {
        this.produceRating = produceRating;
    }

    public float getCheckoutRating() {
        return checkoutRating;
    }

    public void setCheckoutRating(float checkoutRating) {
        this.checkoutRating = checkoutRating;
    }
}
