package com.example.NTT.helper;

import com.example.NTT.entity.Product;

import java.util.List;

public class BinarySearch {

    public static int binarySearch(List<Product> products, String target) {

        int left = 0;
        int right = products.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = target.compareTo(products.get(mid).getName());

            if (compareResult == 0) {
                return mid;
            }

            if (compareResult > 0) {
                left = mid + 1;
            }

            else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
