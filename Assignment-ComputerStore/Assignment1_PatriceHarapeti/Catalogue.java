import java.text.*;
import java.util.*;

public class Catalogue
{
    private List<Part> parts;

    //The Catalogue constructor will add some initial Parts to the catalogue
    public Catalogue(){
        parts = new ArrayList<Part>();

        parts.add(new Part("evo 860","STORAGE",155.00));
        parts.add(new Part("daskeyboard","KEYBOARD",239.00));
        parts.add(new Part("i5","CPU", 365.00));
        parts.add(new Part("Corsair 16G","MEMORY",299.00));
        parts.add(new Part("ASUS ROG","MOTHERBOARD",159.00));
        parts.add(new Part("sheetmetal box","CASE",39.00));
        parts.add(new Part("Ryzen 7","CPU",299.00));
    }

    public void use(){
        char c;
        System.out.println("Welcome to the parts catalogue.");
        System.out.print("Enter choice (a/r/s/f/x): ");
        c = In.nextChar();

        while(c != 'x'){
            switch(c){
                case 'a': add(); break;
                case 'r': remove(); break;
                case 's': show(); break;
                case 'f': find(); break;
                default: help(); break;
            }

            System.out.print("Enter choice (a/r/s/f/x): ");
            c = In.nextChar();
        }
    }

    public boolean partExists(int i){
        if((i >= 0) && (i <= parts.size())){
            return true;
        } else {
            return false;
        }
    }
    
    public Part getPart(int i){
        return parts.get(i);
    }
    

    private void help(){
        System.out.println("a = add a new part to the catalogue");
        System.out.println("r = remove a part from the catalogue");
        System.out.println("s = show the catalogue");
        System.out.println("f = show a filtered view of the catalogue");
        System.out.println("? = this help message");
    }

    private void add(){
        String name;
        String type;
        double price;

        System.out.print("Enter part name: ");
        name = In.nextLine();

        System.out.print("Enter part type: ");
        type = In.nextLine();

        System.out.print("Enter part price: ");
        price = In.nextDouble();

        //Use name, type and price to add a new item to catalog
        parts.add(new Part(name, type, price));
    }

    public void addPart(Part part){
        parts.add(part);
    }

    private void remove(){
        int removeI;
        System.out.print("Enter catalogue number of part to remove: ");
        removeI = In.nextInt();

        //Check if this part even exists
        if(removeI >= 1 && removeI <= parts.size()){
            parts.remove(removeI-1);
        } else {
            System.out.println("The build has no part with that number.");
        }
    }

    //Show the parts in the parts list
    private void show(){
        for(int i = 0; i < parts.size(); i++) {
            int order = i + 1;
            System.out.print(order + ". ");
            System.out.println(parts.get(i).toString());
        }
    }

    private void find(){
        boolean numbersValid = false;

        System.out.print("Enter type of part to view ('all' for no filtering): ");
        String s = In.nextLine().toUpperCase();

        System.out.print("Enter minimum price ('-1' for no filtering): ");
        int minPrice = In.nextInt();

        if(s.equals("ALL")){
            if(minPrice == -1){
                for(int i = 0; i < parts.size(); i++) {
                    System.out.println((i+1) + ". " + parts.get(i).toString());
                }
            } else {
                System.out.print("Enter maximum price: ");
                int maxPrice = In.nextInt();

                if(minPrice > maxPrice){
                    System.out.println("Minimum price shouldn't be greater than maximum price.");
                    numbersValid = false;
                } else {
                    numbersValid = true;
                }

                if(numbersValid){
                    for(int i = 0; i < parts.size(); i++) {
                        if(parts.get(i).isPricedBetween(minPrice, maxPrice)){
                            int index = parts.indexOf(parts.get(i));
                            index++;
                            System.out.println(((index) + ". " + parts.get(i).toString()));
                        }
                    }
                }
            }
        } else {
            if(minPrice == -1){
                for(int i = 0; i < parts.size(); i++) {
                    if(parts.get(i).search(s, minPrice)){
                        System.out.println((i+1) + ". " + parts.get(i).toString());
                    }
                }
            } else {

                System.out.print("Enter maximum price: ");
                int maxPrice = In.nextInt();

                if(minPrice > maxPrice){
                    System.out.println("Minimum price shouldn't be greater than maximum price.");
                    numbersValid = false;
                } else {
                    numbersValid = true;
                }

                if(numbersValid){
                    for(int i = 0; i < parts.size(); i++) {
                        if(parts.get(i).search(s, minPrice, maxPrice)){
                            System.out.println((i+1) + ". " + parts.get(i).toString());
                        }
                    }
                }
            }
        }
    }

    private String formatted(double cash){
        return new DecimalFormat("###, ##0.00").format(cash);
    }

    @Override
    public String toString(){
        //stub
        return "hi";
    }
}
