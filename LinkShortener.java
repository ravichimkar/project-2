import java.util.*;
 public class LinkShortener {
    // Map to store URL mappings
    private final Map<String, String> shortToLongMap = new HashMap<>();
    private final Map<String, String> longToShortMap = new HashMap<>();
    private final String BASE_URL = "http://short.ly/";
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int BASE = ALPHABET.length();
    private long counter = 1; // Counter to ensure unique short URLs
    // Method to generate a short URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return BASE_URL + longToShortMap.get(longURL);
        }
        String shortKey = encode(counter++);
        shortToLongMap.put(shortKey, longURL);
        longToShortMap.put(longURL, shortKey);
        return BASE_URL + shortKey;
    }
    // Method to expand a short URL
    public String expandURL(String shortURL) {
        String shortKey = shortURL.replace(BASE_URL, "");
        if (shortToLongMap.containsKey(shortKey)) {
            return shortToLongMap.get(shortKey);
        } else {
            return "Error: Short URL not found";
        }
    }
    // Method to encode a number to a base-62 string
    private String encode(long number) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(ALPHABET.charAt((int) (number % BASE)));
            number /= BASE;
        }
        return sb.reverse().toString();
    }
    // Main method to interact with the program
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Shorten a URL");
            System.out.println("2. Expand a URL");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL: ");
                    String longURL = scanner.nextLine();
                    String shortURL = linkShortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                    break;
                case 2:
                    System.out.print("Enter the short URL: ");
                    String inputShortURL = scanner.nextLine();
                    String expandedURL = linkShortener.expandURL(inputShortURL);
                    System.out.println("Original URL: " + expandedURL);
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}