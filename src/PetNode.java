class ProductAlreadyExist extends Exception {

}

class PetNode  {
    String name;
    PetNode left;
    PetNode right;

    ProductNode productList;


    public void addProduct(String p_name, String code, Integer quantity)  {
        try {
            if (productList == null) {
                productList = new ProductNode(p_name, code, quantity);
                System.out.println("Product Inserted");
            } else {
                this._addProduct(productList, p_name, code, quantity);
            }
        } catch (ProductAlreadyExist ex) {
            System.out.println("Product already exists. New Product was not added.");
        }
    }

    private void _addProduct(ProductNode root, String name, String code, Integer quantity) throws ProductAlreadyExist {
        if (root != null) {
            if (root.data.p_name.compareToIgnoreCase(name) == 0) {
                throw new ProductAlreadyExist();
            }
            if (root.next == null) {
                root.next = new ProductNode(name, code, quantity);
                System.out.println("Product Inserted");
            } else {
                this._addProduct(root.next, name, code, quantity);
            }
        }
    }

    public void deleteProduct(String name) throws SortedADT.NotFoundException {
        try {
            if (productList == null) {
                System.out.println("No Products Available.");
                return;
            }
            if (productList.data.p_name.compareToIgnoreCase(name) == 0) {
                productList = productList.next;
                System.out.println("Product Deleted ");
            } else {
                if (productList.next != null) {
                    if (productList.next.data.p_name.compareToIgnoreCase(name) == 0) {
                        productList.next = productList.next.next;
                        System.out.println("Product Deleted ");
                    } else {
                        this._deleteProduct(productList.next, name);
                    }
                } else {
                    throw new SortedADT.NotFoundException();
                }
            }
        } catch (SortedADT.NotFoundException exp) {
            throw (SortedADT.NotFoundException)exp;
        }
    }
    private void _deleteProduct(ProductNode root,String  name) throws SortedADT.NotFoundException {
        if (root == null || root.next == null) throw new SortedADT.NotFoundException();
        if (root.next.data.p_name.compareToIgnoreCase(name) == 0) {
            root.next = root.next.next;
            System.out.println("Product Deleted ");
        } else {
            this._deleteProduct(root.next, name);
        }
    }



    public void readList() {
        if (productList == null) {
            System.out.println("No Products Available");
            return;
        }
        this.traverseList(productList);
    }

    private void traverseList(ProductNode  root) {
        if (root == null) return;
        System.out.println("Product Name: "+root.data.p_name);
        System.out.println("Product Code: "+root.data.p_code);
        System.out.println("Quantity In Stock: "+root.data.p_quantity+"\n");
        this.traverseList(root.next);
    }

    public void findProduct(String name) throws SortedADT.NotFoundException {
        try {
            if (productList == null) {
                System.out.println("No Products Available.");
                return;
            }
            if (productList.data.p_name.compareToIgnoreCase(name) == 0) {
                System.out.println("Product Available");
                System.out.println("Product Name: "+productList.data.p_name);
                System.out.println("Product Code: "+productList.data.p_code);
                System.out.println("Quantity In Stock: "+productList.data.p_quantity+"\n");

            } else {
                if (productList.next != null) {
                    if (productList.next.data.p_name.compareToIgnoreCase(name) == 0) {
                        System.out.println("Product Available");
                        System.out.println("Product Name: "+productList.next.data.p_name);
                        System.out.println("Product Code: "+productList.next.data.p_code);
                        System.out.println("Quantity In Stock: "+productList.next.data.p_quantity+"\n");
                    } else {
                        this._findProduct(productList.next, name);
                    }
                } else {
                    throw new SortedADT.NotFoundException();
                }
            }
        } catch (SortedADT.NotFoundException exp) {
            throw (SortedADT.NotFoundException)exp;
        }
    }
    private void _findProduct(ProductNode root,String  name) throws SortedADT.NotFoundException {
        if (root == null || root.next == null) throw new SortedADT.NotFoundException();
        if (root.next.data.p_name.compareToIgnoreCase(name) == 0) {
            root.next = root.next.next;
            System.out.println("Product Deleted ");
        } else {
            this._deleteProduct(root.next, name);
        }
    }




}