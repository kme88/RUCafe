package RUCafePackage;

/**
 * This class provides the implementation related to the Coffee menu item.
 * @author Isha Vora, Kathleen Eife
 */
public class Coffee extends MenuItem implements Customizable {

    private String size;
    private boolean creamAddIn;
    private boolean milkAddIn;
    private boolean whippedCreamAddIn;
    private boolean syrupAddIn;
    private boolean caramelAddIn;
    private int numberOfAddIns;

    private static final float COFFEE_BASE_PRICE = 1.99f;
    private static final float ADD_IN_PRICE = 0.20f;
    private static final float TALL_SIZE_PRICE = 0.50f;
    private static final float GRANDE_SIZE_PRICE = 1.00f;
    private static final float VENTI_SIZE_PRICE = 1.50f;

    /**
     * This constructor takes in coffee attributes and creates a coffee object.
     * @param size size of the coffee
     * @param quantity number of coffees selected by user
     * @param creamAddIn boolean that indicates whether the cream add-in is selected
     * @param milkAddIn boolean that indicates whether the milk add-in is selected
     * @param whippedCreamAddIn boolean that indicates whether the whipped cream add-in is selected
     * @param syrupAddIn boolean that indicates whether the syrup add-in is selected
     * @param caramelAddIn boolean that indicates whether the caramel add-in is selected
     */
    public Coffee(String size, int quantity, boolean creamAddIn, boolean milkAddIn, boolean whippedCreamAddIn,
                  boolean syrupAddIn, boolean caramelAddIn) {
        super(quantity);
        this.size = size;
        this.creamAddIn = creamAddIn;
        this.milkAddIn = milkAddIn;
        this.whippedCreamAddIn = whippedCreamAddIn;
        this.syrupAddIn = syrupAddIn;
        this.caramelAddIn = caramelAddIn;
    }

    /**
     * This method calculates a menu item's price.
     */
    @Override
    public void itemPrice() {
        processAddIns();
        float coffeePrice = COFFEE_BASE_PRICE;

        switch (size) {
            case "Short" :
                coffeePrice = coffeePrice + ( numberOfAddIns * ADD_IN_PRICE );
                break;
            case "Tall" :
                coffeePrice = coffeePrice + TALL_SIZE_PRICE;
                coffeePrice = coffeePrice + ( numberOfAddIns * ADD_IN_PRICE );
                break;
            case "Grande" :
                coffeePrice = coffeePrice + GRANDE_SIZE_PRICE;
                coffeePrice = coffeePrice + ( numberOfAddIns * ADD_IN_PRICE );
                break;
            case "Venti" :
                coffeePrice = coffeePrice + VENTI_SIZE_PRICE;
                coffeePrice = coffeePrice + ( numberOfAddIns * ADD_IN_PRICE );
                break;
            default :
                break;
        }
        price = coffeePrice * quantity;
    }

    /**
     * This helper method calculates the number of add ins selected by the user.
     */
    private void processAddIns() {
        numberOfAddIns = 0;
        if (creamAddIn) {
            numberOfAddIns++;
        }
        if (milkAddIn) {
            numberOfAddIns++;
        }
        if (whippedCreamAddIn) {
            numberOfAddIns++;
        }
        if (syrupAddIn) {
            numberOfAddIns++;
        }
        if (caramelAddIn) {
            numberOfAddIns++;
        }
    }

    /**
     * This method adds add-ins based on user selection.
     * @param obj character object that represents an add-in
     * @return true if add-ins are successfully added, false otherwise
     */
    public boolean add(Object obj) {
        Character addIn = (Character) obj;

        switch (addIn) {
            case 'C' :
                this.creamAddIn = true;
                break;
            case 'M' :
                this.milkAddIn = true;
                break;
            case 'W' :
                this.whippedCreamAddIn = true;
                break;
            case 'S' :
                this.syrupAddIn = true;
                break;
            case 'L' :
                this.caramelAddIn = true;
                break;
            default :
                return false;
        }
        return true;
    }

    /**
     * This method removes add-ins based on user selection.
     * @param obj character object that represents an add-in
     * @return true if add-ins are successfully removed, false otherwise
     */
    public boolean remove(Object obj) {
        Character addIn = (Character) obj;

        switch (addIn) {
            case 'C' :
                this.creamAddIn = false;
                break;
            case 'M' :
                this.milkAddIn = false;
                break;
            case 'W' :
                this.whippedCreamAddIn = false;
                break;
            case 'S' :
                this.syrupAddIn = false;
                break;
            case 'L' :
                this.caramelAddIn = false;
                break;
            default :
                return false;
        }
        return true;
    }

    /**
     * This setter method sets the size of the coffee based on user input.
     * @param inputSize the size of the coffee
     */
    public void setSize(String inputSize) {
        this.size = inputSize;
    }

    /**
     * This setter method sets the quantity of coffees based on user input.
     * @param inputQuantity the number of coffees selected by user
     */
    public void setQuantity(int inputQuantity) {
        this.quantity = inputQuantity;
    }

    /**
     * This method returns a string representation of a coffee object.
     * @return string representation of the coffee object
     */
    @Override
    public String toString() {
        String coffeeString = "Coffee(" + quantity + ") " + size;
        if (numberOfAddIns != 0) {
            coffeeString = coffeeString + " [";

            if (creamAddIn) {
                coffeeString = coffeeString + "Cream,";
            }
            if (milkAddIn) {
                coffeeString = coffeeString + "Milk,";
            }
            if (whippedCreamAddIn) {
                coffeeString = coffeeString + "Whipped Cream,";
            }
            if (syrupAddIn) {
                coffeeString = coffeeString + "Syrup,";
            }
            if (caramelAddIn) {
                coffeeString = coffeeString + "Caramel,";
            }
            coffeeString = coffeeString.substring(0, coffeeString.length() - 1);
            coffeeString = coffeeString + "]";
        }
        return coffeeString;
    }
}
