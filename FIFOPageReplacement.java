import java.util.*;

public class FIFOPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter number of frames:-");
        int framesCount = sc.nextInt();


        System.out.print("Enter number of pages:-");
        int pagesCount = sc.nextInt();

        int[] pages = new int[pagesCount];
        System.out.println("Enter the reference string:");
        for (int i = 0; i < pagesCount; i++) {
            pages[i] = sc.nextInt();
        }

        Queue<Integer> frames = new LinkedList<>();
        int pageHits = 0;
        int pageFaults = 0;

        System.out.println("\nPage Replacement Process (FIFO):");

        for (int page : pages) {
            if (frames.contains(page)) {
                pageHits++;
                System.out.println("Page " + page + " => HIT   | Frames: " + frames);
            } else {
                pageFaults++;
                if (frames.size() == framesCount) {
                    int removed = frames.poll(); 
                    System.out.println("Page " + removed + " removed.");
                }
                frames.add(page);
                System.out.println("Page " + page + " => FAULT | Frames: " + frames);
            }
        }

        System.out.println("\nTotal Page Hits   = " + pageHits);
        System.out.println("Total Page Faults = " + pageFaults);

        sc.close();
    }
}
