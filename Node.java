package hash_table;
public class Node {
 private final int capacity;   // a constant for the capacity of the hash table and we cannot change it anyway     
    private final hashNode[] table;   // an array of hashNode objects to hold the entries 
    private int size;           //Track the number of elements currently in the hash table عدد العناصر الي موجوده حاليا في التيبل بتاعي

    public Node(int capacity) {//constructor
        this.capacity = capacity;
        this.table = new hashNode[capacity];
        this.size = 0;    //Initialize size to 0 as the table is empty initially
    }
     private static class hashNode {    // a static nested class hashNode
        String name;  // Key (Contact Name)
        String phoneNumber; // Value (Phone Number of the contact)
        boolean occupiedBefore; //Flag to indicate if this node was occupied previously هلي البوينت ديه فاضيه دلوقتي ولالا
  public hashNode(String name, String phoneNumber) {//constructor
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.occupiedBefore = true; //Mark this node as occupied
        }
    }
      public static long calcHash(String key, int capacity) {
        int l = key.length();  //Get the length of the input string لينث اسم جهه الاتصال
        long hash = 0;
        for (int i = 0; i < l; i++) {
 hash += Character.getNumericValue(key.charAt(i));  //Add the numeric value of the current character to hash
            hash += (hash << 10);    //Shift hash left by 10 bits and add it to hash
            hash ^= (hash >> 6);    //XOR hash with the right shift of hash by 6 bits
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        if (hash > 0) return hash % capacity;
        else return -hash % capacity;
    }
      public boolean isFull() {
        return size == capacity;   //Return true if the table is full (size equals capacity), otherwise false
    }

    public void insert(String name, String phoneNumber) {
        if (isFull()) {
            System.out.println("Hash table is full");
 return;//هنوقف الميثود مش هتكمل بعدها
        }
        int index = (int)calcHash(name,capacity);   //Calculate the hash index for the given name
        //هنحول من لونج لرقم صحيح هنا
        while (table[index] != null) {
            if (table[index].name.equals(name)) {
                System.out.println("Contact already exists. Use update to modify the number.");
                return;
            }
            index = (index + 1) % capacity;
//عند محاوله ادخال عنصر جديد اذا كان الفهرس المحسوب مشغول بالفعل اي يحتوي علي عنصر اخر فهي تساعد في الانتقال الي الخانه التاليه حتي تجد خانه فارغه

        }
        table[index] = new hashNode(name, phoneNumber);
        size++;
        System.out.println("Contact added: " + name);
    }

    public String search(String name) {//Search for a contact by (name) to retrieve their phone number. 
        int index = (int)calcHash(name, capacity);
        while (table[index] != null && table[index].occupiedBefore) {  //continue searching while the current slot is not null and was previously occupied
            if (table[index].name.equals(name)) 
                {  //check if the name in the current slot matches the search name and return the phone number if a match is found.
                return table[index].phoneNumber;
            }
            index = (index + 1) % capacity;
            
        }
        return null;
    }

    public void delete(String name) {//Delete a contact by name. 
        int index = (int)calcHash(name, capacity);
        while (table[index] != null && table[index].occupiedBefore) {
            if (table[index].name.equals(name)) { //Check if the name in the current slot matches the name to delete
                table[index] = null;  //Remove the contact by setting the slot to null and decrease the table size
                size--;
  System.out.println("Contact deleted: " + name);
                return;
            }
            index = (index + 1) % capacity;
        }//while
        System.out.println("Contact not found.");
    }
public void update(String name, String newPhoneNumber) {//update a contact number
    int index = (int)calcHash(name, capacity);
    while (table[index] != null && table[index].occupiedBefore) {
        if (table[index].name.equals(name)) {
            table[index].phoneNumber = newPhoneNumber;
            System.out.println("Contact updated: " + name);  //Update the phone number for the given contact and print confirmation
            return;
        }
        index = (index + 1) % capacity;
    }

    // Print a message if the contact doesn't exist
    System.out.println("Contact not found.Update operation failed.");}
public void displayAll() {
    System.out.println("Contacts:");
    boolean found = false; // Flag to check if any contacts are displayed
    for (int i = 0; i < capacity; i++) {
        if (table[i] != null && table[i].occupiedBefore) {
            System.out.println("Name: " + table[i].name + " -> Phone: " + table[i].phoneNumber);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No contacts found.");
}
}}

            

