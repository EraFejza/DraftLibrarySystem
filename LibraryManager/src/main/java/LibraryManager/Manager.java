package LibraryManager;
import java.util.ArrayList;

public class Manager extends Librarian {
	
	private static ArrayList<Librarian> librarians = new ArrayList<>();

    public static BillNumber billNumber = new BillNumber();

	public Manager(String username, String password) {
		super(username, password);
		
	}
	
	public Manager(String username, String password, String name, double salary, String phone, String email){
		super(username,password,name,salary,phone,email);
	}
	
     public static void InstantiateLibrarians() {

        if (!Manager.getLibrarians().isEmpty()){
            return;
        }
		
		Librarian lib = new Librarian("Alfie123","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com") ;
		librarians.add(lib);
		
		lib = new Librarian("@Leo","TyFzN8we","Leo",500,"(912) 152-7493","leo@librarian.com");
		librarians.add(lib);
		
		lib = new Librarian("Julie?!","NDt8f6xL","Julie",500,"(912) 742-7832","julie@librarian.com");
		librarians.add(lib);
		
		lib = new Librarian("MargiE","vGtM6beC","Margie",500,"(912) 253-6939","margie@librarian.com");
		librarians.add(lib);
		
		lib = new Librarian("1","1","TestLibrarian",500,"(912) 632-6353","TestEmail@librarian.com");
		librarians.add(lib);
		
	}
	
    
    public static String checkStock(String path) {
    	
    	ArrayList<Book> stockbooks = billNumber.getStockBooks(path);
    	String ans = "Warning!\n";
    	int check=0;

        for (Book stockbook : stockbooks) {
            if (stockbook.getStock() < 5) {
                check = 1;
                ans = ans.concat("Book: " + stockbook.getTitle() + ", With ISBN code: " + stockbook.getISBN() + ", Has Stock: " + stockbook.getStock() + "\n");
            }
        }
    	
    	if (check==0)
    		return "Every book has 5 or more items in stock";
    	return ans;
    	
    }
    
    public static ArrayList<Book> getLowStock(String path){
    	
    	ArrayList<Book> stockbooks = billNumber.getStockBooks(path);
    	ArrayList<Book> ans = new ArrayList<>();

        for (Book stockbook : stockbooks) {

            if (stockbook.getStock() < 5) {
                ans.add(stockbook);
            }

        }
    	
    	return ans;
    	
    }
    
    public static void AddLibrarian(Librarian lib) {
    	librarians.add(lib);
    }
    
    public static ArrayList<Librarian> getLibrarians() {
    	return librarians;
    }
    
    public static boolean partOfLibrarian(Librarian lib) {

        for (Librarian librarian : librarians)
            if (librarian.getUsername().equals(lib.getUsername()))
                return true;
    		
    	return false;
    	
    }
    
    public static Librarian reEnter(Librarian lib) {

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(lib.getUsername()))
                return librarian;
        }
    	
    	return null;
    	
    }
    
    public static boolean LibrarianChecker(Librarian lib) {
        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(lib.getUsername()) && librarian.getPassword().equals(lib.getPassword()))
                return true;
        }
    	return false;
    	
    	
    }
    
    public static Librarian getBackLibrarian(Librarian lib) {

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(lib.getUsername()))
                return librarian;
        }
    	return null;
    }
    
    public static void updateLibrarians(Librarian lib) {

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(lib.getUsername())) {
                librarian.setEmail(lib.getEmail());
                librarian.setPhone(lib.getPhone());
                librarian.setSalary(lib.getSalary());
                librarian.setPassword(lib.getPassword());
                librarian.setUsername(lib.getUsername());
                return;
            }

        }
    
    	
    }
    
    public static void deleteLibrarian(Librarian lib) {
    	for (int i=0;i<librarians.size();i++) {
    		if (librarians.get(i).getUsername().equals(lib.getUsername())) {
    			librarians.remove(i);
    			return;
    		}
    	}
    }
    
    public static ArrayList<String> getAllCategories(){
    	
    	ArrayList<String> ans = new ArrayList<>();
    	ans.add("Modernist");
    	ans.add("Fiction");
    	ans.add("Novel");
    	ans.add("Magic Realism");
    	ans.add("Tragedy");
    	ans.add("Adventure Fiction");
    	ans.add("Historical Novel");
    	ans.add("Epic");
    	ans.add("War");
    	ans.add("Autobiography and memoir");
    	ans.add("Biography");
    	ans.add("Non-fiction novel");
    	ans.add("Self-help");
    	ans.add("Short stories");
    	ans.add("Horror");
    	ans.add("Mystery");
    	ans.add("Romance");
    	ans.add("Thriller");
    	return ans;
    }
}
