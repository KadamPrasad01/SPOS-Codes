import java.util.*;

public class OPR{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take inputs
        System.out.print("Enter number of frames: ");
        int frameCount = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        List<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        // Step 2: Process each page one by one
        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // If page already in frame, just show it
            if (frames.contains(page)) {
                System.out.println("Step " + (i + 1) + " -> " + frames + " (HIT)");
                continue;
            }

            // Page not in frame → page fault
            pageFaults++;

            // If space is available, add directly
            if (frames.size() < frameCount) {
                frames.add(page);
            } else {
                // Otherwise, find page to replace
                int replaceIndex = findPageToReplace(frames, pages, i + 1);
                frames.set(replaceIndex, page);
            }

            System.out.println("Step " + (i + 1) + " -> " + frames + " (FAULT)");
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }

    // Helper method: find which page should be replaced
    private static int findPageToReplace(List<Integer> frames, int[] pages, int start) {
        int farthest = -1;
        int replaceIndex = 0;

        for (int i = 0; i < frames.size(); i++) {
            int page = frames.get(i);
            int nextUse = Integer.MAX_VALUE;

            // Find when this page will be used next
            for (int j = start; j < pages.length; j++) {
                if (pages[j] == page) {
                    nextUse = j;
                    break;
                }
            }

            // If page never used again, return immediately
            if (nextUse == Integer.MAX_VALUE)
                return i;

            // Else choose the one used farthest in the future
            if (nextUse > farthest) {
                farthest = nextUse;
                replaceIndex = i;
            }
        }

        return replaceIndex;
    }
}
