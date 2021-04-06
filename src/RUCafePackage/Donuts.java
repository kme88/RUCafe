package RUCafePackage;

/**
 * This class provides the implementation related to the Donut menu item.
 * @author Isha Vora, Kathleen Eife
 */
public class Donuts extends MenuItem {

    private String donutType;
    private String donutFlavor;

    private static final float YEAST_DONUT_PRICE = 1.39f;
    private static final float CAKE_DONUT_PRICE = 1.59f;
    private static final float DONUT_HOLES_PRICE = 0.33f;

    /**
     * This constructor takes in donut attributes and creates a donut object.
     * @param quantity number of donuts
     * @param donutType type of donut
     * @param donutFlavor donut flavor
     */
    public Donuts(int quantity, String donutType, String donutFlavor) {
        super(quantity);
        this.donutType = donutType;
        this.donutFlavor = donutFlavor;
    }

    /**
     * This method calculates a menu item's price.
     */
    @Override
    public void itemPrice() {
        float donutPrice = 0;

        switch (donutType) {
            case "Yeast Donuts" :
                donutPrice = donutPrice + YEAST_DONUT_PRICE;
                break;
            case "Cake Donuts" :
                donutPrice = donutPrice + CAKE_DONUT_PRICE;
                break;
            case "Donut Holes" :
                donutPrice = donutPrice + DONUT_HOLES_PRICE;
                break;
            default :
                break;
        }
        price = price + ( donutPrice * quantity );
    }

    /**
     * This method returns a string representation of a donut object.
     */
    @Override
    public String toString() {
        return donutType + ", " + donutFlavor + "(" + quantity + ")";
    }
}
