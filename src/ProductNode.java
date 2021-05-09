class ProductNode {
    ProductData data;
    ProductNode next;

    public ProductNode (String name, String code, Integer quantity) {
        this.data = new ProductData(name, code, quantity);
    }
}

class ProductData {
    String p_name,p_code;
    Integer p_quantity;

    public ProductData(String name, String code, Integer quantity) {
        this.p_name = name;
        this.p_code = code;
        this.p_quantity = quantity;
    }


}