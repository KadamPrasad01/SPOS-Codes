import java.util.*;

public class LRU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter number of frames: ");
        int frameCount = sc.nextInt();

        List<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("\nPage Replacement Process (LRU):");

        // Step 2: Process each page
        for (int page : pages) {
            // Page hit: move it to the end (recently used)
            if (frames.contains(page)) {
                frames.remove(Integer.valueOf(page));
                frames.add(page);
                System.out.println("Page " + page + " -> HIT | Frames: " + frames);
            } 
            // Page fault
            else {
                pageFaults++;
                if (frames.size() < frameCount) {
                    frames.add(page); // Add if space available
                } else {
                    frames.remove(0); // Remove least recently used (first element)
                    frames.add(page);
                }
                System.out.println("Page " + page + " -> FAULT | Frames: " + frames);
            }
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}
