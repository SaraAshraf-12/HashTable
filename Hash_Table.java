package hash_table;
public class Hash_Table {
   public static void main(String[] args) {
       Node hashTable = new Node(10);

        // Insert data into the hash table
        hashTable.insert("Sam Doe", "+1-555-1234");
        hashTable.insert("John Smith", "+1-555-8976");
        hashTable.insert("Lisa Smith", "+1-555-5030");

        // Display all contacts
        System.out.println("Displaying all contacts:");
        hashTable.displayAll();

        // Search for a contact
        System.out.println("Searching for 'John Smith':");
        String phone = hashTable.search("John Smith");
        if (phone != null) {
            System.out.println("Found: John Smith -> " + phone);
        } else {
            System.out.println("Contact not found.");
        }

        // Update a contact
        System.out.println("Updating 'Lisa Smith':");
        hashTable.update("Lisa Smith", "+1-555-6789");
        hashTable.displayAll();

        // Delete a contact
        System.out.println("Deleting 'Sam Doe':");
        hashTable.delete("Sam Doe");
        hashTable.displayAll();

        // Try to delete a non-existing contact
        System.out.println("Trying to delete 'Unknown Name':");
        hashTable.delete("Unknown Name");
    
        
        
    }
    
}
