
public class Pet implements SortedADT {

    private PetNode root;
    private PetNode current;
    private PetNode parent;

    public String toString(){
        String treeDetails = new String();
        if (this.root != null) {
            treeDetails+=this.getTree(this.root,0);
        }
        else{
            treeDetails+="No pet in the system right now";
        }
        return treeDetails;
    }

    private String getTree(PetNode current, Integer level) {
        String PetDetails = new String();
        level++;
        if (current != null) {
            PetDetails += this.getTree(current.right, level);
            for (Integer i = 0; i < level; i++) {
                PetDetails += "    ";
            }
            PetDetails += current.name + "\n";
            PetDetails += this.getTree(current.left, level);

        } else {
            for (Integer i = 0; i < level; i++) {
                PetDetails += "    ";
            }
            PetDetails += "null\n";
        }
        return PetDetails;
    }

    public String getPet() {
        String petDetails = new String();
        if (this.root != null ) {
            if (this.getInOrderPet(this.root).isEmpty()){
                petDetails += "No pet in the system that has products right now";
            }
            else {
                petDetails += "Pet type that the company provides products for:  \n";
                petDetails += this.getInOrderPet(this.root) + "\n";
            }

        } else {
            petDetails += "No pet in the system right now";
        }
        return petDetails;
    }


    private String getInOrderPet(PetNode current) {
        String inOrderDetails = new String();
        if (current != null) {
            inOrderDetails += this.getInOrderPet(current.left);
            if (current.productList!= null)
                inOrderDetails += current.name +"    ";
            inOrderDetails += this.getInOrderPet(current.right);
        }
        return inOrderDetails;
    }



    public void insert(String object) throws NotUniqueException {
        PetNode newNode = new PetNode();
        newNode.name = object;
        if (this.root == null) {
            this.root = newNode;
        } else {
            this.insert(newNode,this.root);
        }
    }

    private void insert(PetNode newNode,PetNode current)
            throws NotUniqueException{
        if (newNode.name.compareToIgnoreCase(current.name) == 0)
            throw new NotUniqueException();
        if (newNode.name.compareToIgnoreCase(current.name) < 0) {
            if (current.left == null) {
                current.left = newNode;
            } else {
                this.insert(newNode,current.left);
            }
        } else if (current.right == null) {
            current.right = newNode;
        } else {
            this.insert(newNode,current.right);
        }
    }

    public String find(String object) throws NotFoundException {
        return this.find(object,this.root);
    }

    private String find(String object, PetNode current)
            throws NotFoundException{

        String foundObject;
        if (current != null) {
            if (object.compareToIgnoreCase(current.name) == 0) {
                this.current=current;
                foundObject = current.name;
            } else{
                this.parent=current;
                if (object.compareToIgnoreCase(current.name) < 0) {
                    foundObject = this.find(object,current.left);
                } else {
                    foundObject = this.find(object,current.right);
                }
            }
        } else{
            throw new NotFoundException();
        }
        return foundObject;
    }

    public PetNode rNode(String object) throws NotFoundException {
        return this.rNode(object,this.root);
    }
    private PetNode rNode(String object, PetNode current)
            throws NotFoundException{

        PetNode foundObject;
        if (current != null) {
            if (object.compareToIgnoreCase(current.name) == 0) {
                this.current=current;
                foundObject = current;
            } else{
                this.parent=current;
                if (object.compareToIgnoreCase(current.name) < 0) {
                    foundObject = this.rNode(object,current.left);
                } else {
                    foundObject = this.rNode(object,current.right);
                }
            }
        } else{
            throw new NotFoundException();
        }
        return foundObject;
    }

    public String remove(String object) throws NotFoundException {
        // sets up parent and current
        String removedObject=this.find(object);
        if (this.current.left == null && this.current.right == null) {
            this.replaceNode(null);
        } else if (this.current.left != null && this.current.right == null) {
            this.replaceNode(this.current.left);
            this.current.left = null;
        } else if (this.current.left == null && this.current.right != null) {
            this.replaceNode(this.current.right);
            this.current.right = null;
        } else {
            this.successorNode(this.current, this.current, this.current.right);
        }
        return removedObject;
    }


    private void replaceNode(PetNode replacement) {

        if (this.current == this.root) // removing root
        {
            this.root = replacement;
        } else if (this.current == this.parent.left) {
            this.parent.left = replacement;
        } else {
            this.parent.right = replacement;
        }
        this.current.name = null;
    }

    private void successorNode(PetNode nodeForDeletion, PetNode parent, PetNode current) {
        if (current.left == null) {
            nodeForDeletion.name = current.name;
            if (parent == nodeForDeletion) {
                parent.right = current.right;
            } else {
                parent.left = current.right;
            }
            current.name = null;
            current.right = null;
        } else {
            this.successorNode(nodeForDeletion, current, current.left);
        }
    }

    public void getTraversals() {
        if (this.root != null ) {
            System.out.println("Pet type name in alphabetical order and their product information:");
            this.getInOrder(this.root);
        } else {
            System.out.println("No pet in the system right now");
        }
    }


    private void getInOrder(PetNode current) {
        if (current != null ) {
            this.getInOrder(current.left);
            System.out.println("Pet Type: " + current.name);
            System.out.println("Products for pet-type: ");
            current.readList();
            this.getInOrder(current.right);
        }
    }




}
