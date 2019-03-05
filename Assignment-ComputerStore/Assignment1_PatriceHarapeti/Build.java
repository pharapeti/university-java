import java.text.*;
import java.util.*;

public class Build
{
    private List<Part> parts;

    //Takes no paramters
    public Build(){
        parts = new ArrayList<Part>();
    }

    public void use(){
        char c;
        //System.out.println();
        System.out.println("Let's build a 1337 box!");
        //System.out.println();
        System.out.print("Enter choice (n/a/r/v/c/x): ");
        c = In.nextChar();

        while(c != 'x'){
            switch(c){
                //not sure what n does
                case 'n': newBuild(); break;
                case 'a': add(); break;
                case 'r': remove(); break;
                case 'v': show(); break;
                case 'c': check(); break;
                default: help(); break;
            }

            //System.out.println();
            System.out.print("Enter choice (n/a/r/v/c/x): ");
            c = In.nextChar();
        }
    }

    private void newBuild(){
        parts.clear();
    }

    private void add(){
        //the user could enter a single part (1) or multiple (1,2,3,4,5); need to split the input into sections without [,]
        System.out.print("Enter catalogue number of the part: ");
        String inputString = In.nextLine();
        
        Catalogue newParts = new Catalogue();

        if(inputString.contains(",")){
            String[] sArray = inputString.split(",");
            int[] intArray = new int[sArray.length];

            for(int index=0; index < sArray.length; index++){
                intArray[index] = Integer.parseInt(sArray[index].trim())-1;
                //System.out.println(intArray[index]);
            }

            for(int n=0; n < intArray.length; n++){
                if(newParts.partExists(intArray[n])){
                    this.parts.add(newParts.getPart(intArray[n]));
                } else {
                    System.out.println("There is no part by that number.");
                }
            }
        } else {
            int i = Integer.parseInt(inputString);
            if(newParts.partExists(i-1)){
                this.parts.add(newParts.getPart(i-1));
            } else {
                System.out.println("There is no part by that number.");
            }
        }
    }
    
    private void remove(){
        System.out.print("Enter number of part to remove: ");
        int removeI = In.nextInt();

        //Check if this part even exists
        if(removeI >= 1 && removeI <= parts.size()){
            parts.remove(removeI-1);
        } else {
            System.out.println("The build has no part with that number.");
        }
    }

    //Same as a show, but list the items in the list in build
    private void show(){
        for(int i = 0; i < parts.size(); i++) {
            int order = i + 1;
            System.out.print(order + ". ");
            System.out.println(parts.get(i).toString());
        }

        System.out.println("Total Price: $" + formatted(priceCheck()));
    }

    private void check(){
        //If only 1 of all types are in the list in Build
        boolean cpu = false;
        boolean memory = false;
        boolean motherboard = false;
        boolean storage = false;
        boolean keyboard = false;
        boolean computerCase = false;

        //print "The build is functional."
        for(int i = 0; i < parts.size(); i++) {
            String type = parts.get(i).getType();
            switch(type){
                case "CPU": cpu=true; break;
                case "MEMORY": memory=true; break;
                case "MOTHERBOARD": motherboard=true; break;
                case "STORAGE": storage=true; break;
                case "KEYBOARD": keyboard=true; break;
                case "CASE": computerCase=true; break;
            }

            if(cpu && memory && motherboard && storage && keyboard && computerCase){
                System.out.println("The build is functional.");
            }
        }

        if(!cpu){
            System.out.println("The build is missing a cpu.");
        }

        if(!motherboard){
            System.out.println("The build is missing a motherboard.");
        }

        if(!memory){
            System.out.println("The build is missing RAM.");
        }

        if(!storage){
            System.out.println("The build is missing storage.");
        }

        // if(!keyboard){
        // System.out.println("The build is missing a keyboard.");
        // }

        if(!computerCase){
            System.out.println("The build is missing a case.");
        }

    }

    private double priceCheck(){
        double sum = 0.0;

        for(Part part: parts){
            sum += part.getPrice();
        }

        return sum;
    }

    private void help(){
        System.out.println("n = start a new build (clears old build)");
        System.out.println("a = add a part from the catalogue to the build");
        System.out.println("r = remove a part from the build");
        System.out.println("v = show the current state of the build");
        System.out.println("c = check if the build is a functional computer");
        System.out.println("? = this help message");
    }

    private String formatted(double cash){
        return new DecimalFormat("#####0.00").format(cash);
    }

    @Override
    public String toString(){
        //stub
        return "hi";
    }
}
