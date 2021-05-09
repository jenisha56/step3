public class PetTest {
    public static void main(String[] args) {
        SortedADT pet = new Pet();
        String name;
        Integer option=0;

        do {
            System.out.println("\n0: Quit");
            System.out.println("1: Add Pet  Type");
            System.out.println("2: Display Pet Type");
            System.out.println("3: Find Pet Type");
            System.out.println("4: Remove Pet Type");
            System.out.println("5: Add Product Type");
            System.out.println("6: Display Product");
            System.out.println("7: Delete Product");
            System.out.println("8: Find Product");
            System.out.println("9: Display Pet Type And Their Product");
            option = Input.getInteger("Input option: ");
            switch (option) {
                case 0:
                    System.out.println("\nQuitting Program");
                    break;
                case 1:
                    name = Input.getString("Pet Type: ");
                    try{
                        pet.insert(name);
                        System.out.println("Inserted Pet Successfully");
                    }
                    catch(SortedADT.NotUniqueException e){
                        System.out.println("Insert Invalid - Pet Type Already Exists");
                    }
                    break;
                case 2:
                    System.out.println(((Pet)pet).getPet());
                    break;
                case 3:
                    name = Input.getString("Enter Pet Type: ");
                    try{
                        name=(String)pet.find(name);
                        System.out.println(name+" Pet Type Found");
                    }
                    catch(SortedADT.NotFoundException e){
                        System.out.println("Pet Type Not Found");
                    }
                    break;
                case 4:
                    name = Input.getString("Enter Pet Type: ");
                    try{
                        name=(String)pet.remove(name);
                        System.out.println(name+" Pet Type Removed");
                    }
                    catch(SortedADT.NotFoundException e){
                        System.out.println("Remove Invalid - Pet Type Not Found");
                    }
                    break;

                case 5:
                    name = Input.getString("Select Pet Type: ");
                    try{
                        name=pet.find(name);
                        PetNode select = pet.rNode(name);
                        System.out.println("Enter Product For "+name+"-> ");
                        if (select!= null){
                            String p_name = Input.getString("Product Name: ");
                            String code = Input.getString("Product Code: ");
                            Integer quantity = Input.getInteger("Quantity In Stock: ");
                            select.addProduct(p_name, code, quantity);
                        }
                    }
                    catch(SortedADT.NotFoundException e){
                        System.out.println("Pet Type Not Found");
                    }
                    break;

                case 6:
                    name = Input.getString("Select Pet Type: ");
                    try{
                        PetNode select = pet.rNode(name);
                        System.out.println("Product List For "+ name+"-> ");
                        select.readList();
                    }
                    catch(SortedADT.NotFoundException e){
                        System.out.println("Pet Type Not Found");
                    }
                    break;

                case 7:
                    name = Input.getString("Select Pet Type: ");
                    try {
                        PetNode select = pet.rNode(name);
                        String p_name =Input.getString("Product Name: ");
                        try {
                            select.deleteProduct(p_name);
                        }
                        catch(SortedADT.NotFoundException e){
                            System.out.println("Product Not Found");
                        }
                    }
                    catch(SortedADT.NotFoundException e){
                        System.out.println("Pet Type Not Found");
                    }
                    break;

                case 8:
                    name = Input.getString("Select Pet Type: ");
                    try {
                        PetNode select = pet.rNode(name);
                        String p_name =Input.getString("Product Name: ");
                        try {
                            select.findProduct(p_name);
                        }
                        catch(SortedADT.NotFoundException e){
                            System.out.println("Product Unavailable");
                        }
                    }
                    catch(SortedADT.NotFoundException e){
                        System.out.println("Pet Type Not Found");
                    }
                    break;

                case 9:
                    ((Pet)pet).getTraversals();
                    break;

                default:
                    System.out.println("\nInvalid option");
                    break;
            }
        } while (option != 0);
    }

}


