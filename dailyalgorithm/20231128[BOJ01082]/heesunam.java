import java.util.Scanner;

public class Main {
    static int[] prices;
    static int itemCount, budget;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        itemCount = scanner.nextInt();
        prices = new int[itemCount];

        for (int i = 0; i < itemCount; i++) {
            prices[i] = scanner.nextInt();
        }

        budget = scanner.nextInt();
        int[] selectedItems = new int[100];
        int totalCost = 0;
        int selectedIndex = 0;

        int minPriceIndex = findMinPriceIndex(0);

        if (minPriceIndex == 0) {
            minPriceIndex = findMinPriceIndex(1);

            if (prices[minPriceIndex] <= budget) {
                selectedItems[selectedIndex++] = minPriceIndex;
                totalCost += prices[minPriceIndex];
                minPriceIndex = 0;
            } else {
                System.out.println(0);
                return;
            }
        }

        while (totalCost + prices[minPriceIndex] <= budget) {
            selectedItems[selectedIndex++] = minPriceIndex;
            totalCost += prices[minPriceIndex];
        }

        for (int i = 0; i < selectedIndex; i++) {
            for (int j = itemCount - 1; j >= 0; j--) {
                if (totalCost - prices[selectedItems[i]] + prices[j] <= budget) {
                    totalCost = totalCost - prices[selectedItems[i]] + prices[j];
                    selectedItems[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < selectedIndex; i++) {
            System.out.print(selectedItems[i]);
        }
    }

    public static int findMinPriceIndex(int start) {
        int retIndex = 0, minPrice = 100;

        for (int i = start; i < itemCount; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
                retIndex = i;
            }
        }
        return retIndex;
    }
}
