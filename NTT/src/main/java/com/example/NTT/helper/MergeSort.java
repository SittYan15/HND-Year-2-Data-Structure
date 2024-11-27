package com.example.NTT.helper;

import com.example.NTT.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public static void mergeSort(List<Product> products, String type) {
        if (products.size() <= 1) {
            return;
        }

        int mid = products.size() / 2;
        List<Product> leftHalf = new ArrayList<>(products.subList(0, mid));
        List<Product> rightHalf = new ArrayList<>(products.subList(mid, products.size()));

        mergeSort(leftHalf, type);
        mergeSort(rightHalf, type);
        merge(products, leftHalf, rightHalf, type);
    }

    private static void merge(List<Product> arr, List<Product> leftHalf, List<Product> rightHalf, String type) {
        int leftIndex = 0;
        int rightIndex = 0;
        int arrIndex = 0;

        switch (type) {
            case "name" -> {
                while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
                    if (leftHalf.get(leftIndex).getName().compareTo(rightHalf.get(rightIndex).getName()) <= 0) {
                        arr.set(arrIndex, leftHalf.get(leftIndex));
                        leftIndex++;
                    } else {
                        arr.set(arrIndex, rightHalf.get(rightIndex));
                        rightIndex++;
                    }
                    arrIndex++;
                }
            }
            case "category" -> {
                while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
                    if (leftHalf.get(leftIndex).getCategory().compareTo(rightHalf.get(rightIndex).getCategory()) <= 0) {
                        arr.set(arrIndex, leftHalf.get(leftIndex));
                        leftIndex++;
                    } else {
                        arr.set(arrIndex, rightHalf.get(rightIndex));
                        rightIndex++;
                    }
                    arrIndex++;
                }
            }
            case "price" -> {
                while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
                    if (leftHalf.get(leftIndex).getPrice() < rightHalf.get(rightIndex).getPrice()) {
                        arr.set(arrIndex, leftHalf.get(leftIndex));
                        leftIndex++;
                    } else {
                        arr.set(arrIndex, rightHalf.get(rightIndex));
                        rightIndex++;
                    }
                    arrIndex++;
                }
            }
            case "id" -> {
                while (leftIndex < leftHalf.size() && rightIndex < rightHalf.size()) {
                    if (leftHalf.get(leftIndex).getId() < rightHalf.get(rightIndex).getId()) {
                        arr.set(arrIndex, leftHalf.get(leftIndex));
                        leftIndex++;
                    } else {
                        arr.set(arrIndex, rightHalf.get(rightIndex));
                        rightIndex++;
                    }
                    arrIndex++;
                }
            }
        }

        while (leftIndex < leftHalf.size()) {
            arr.set(arrIndex, leftHalf.get(leftIndex));
            leftIndex++;
            arrIndex++;
        }

        while (rightIndex < rightHalf.size()) {
            arr.set(arrIndex, rightHalf.get(rightIndex));
            rightIndex++;
            arrIndex++;
        }
    }
}
