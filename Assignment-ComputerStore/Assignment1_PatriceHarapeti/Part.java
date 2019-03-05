import java.text.*;

public class Part
{
    private String name;
    private String type;
    private double price;

    //takes name, type, price
    public Part(String name, String type, double price)
    {
        this.name = name.toLowerCase();
        this.type = type;
        this.price = price;
    }

    public boolean search(String searchType, double minimumPrice){        
        if(searchType.toUpperCase().equals(type.toUpperCase())){
            if(price >= minimumPrice || (minimumPrice == -1)){
                return true;
            }
        }
        return false;
    }
    
    public boolean search(String searchType, double minimumPrice, double maximumPrice){        
        if(searchType.toUpperCase().equals(type.toUpperCase())){
            if((price >= minimumPrice && price <= maximumPrice) || (minimumPrice == -1)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isPricedBetween(int minPrice, int maxPrice){
        if(price >= minPrice && price <= maxPrice){
            return true;
        } else {
            return false;
        }
    }

    public String getType(){
        return type;
    }

    public double getPrice(){
        return price;
    }
    
    private String formatted(double cash){
        return new DecimalFormat("#####0.00").format(cash).replaceAll(",","");
    }

    //MOTHERBOARD: ASUS ROG @ $159.00
    @Override
    public String toString(){
        return type.toUpperCase() + ": " + name + " @ $" + formatted(price);
    }
}
