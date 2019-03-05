public class ComputerBuilder
{
    private Catalogue catalogue;
    private Build currentBuild;

    public static void main(String[] args){
        ComputerBuilder computerBuilder = new ComputerBuilder();
        computerBuilder.use();
    }

    //Takes no parameters
    public ComputerBuilder()
    {
        catalogue = new Catalogue();
        currentBuild = new Build();
    }

    public void use() {
        char c;
        System.out.println("Welcome to Jaime's Computer Store");
        System.out.println("Quality Parts at the Best Prices");
        System.out.println("=================================");
        System.out.println("1. Catalogue Menu");
        System.out.println("2. Build Menu");
        System.out.println("3. Exit");
        System.out.print("Select option: ");
        c = In.nextChar();

        while(c != '3'){
            switch(c){
                case '1': catalogue.use(); break;
                case '2': currentBuild.use(); break;
                case '?': help(); break;
                default: break;
            }

            System.out.println("1. Catalogue Menu");
            System.out.println("2. Build Menu");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            c = In.nextChar();
        }
    }

    private void help(){
        System.out.println("1 = interact with the catalogue");
        System.out.println("2 = build your computer!");
        System.out.println("? = this help message");
    }
}
