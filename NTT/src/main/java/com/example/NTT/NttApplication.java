package com.example.NTT;

import com.example.NTT.entity.Product;
import com.example.NTT.helper.BinarySearch;
import com.example.NTT.helper.MergeSort;
import com.example.NTT.helper.MissInputException;
import com.example.NTT.helper.checkingCondition;
import com.example.NTT.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class NttApplication {

	static ConfigurableApplicationContext context;
	static ProductService productService;
	static List<Product> products;
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		context = SpringApplication.run(NttApplication.class, args);
		productService = context.getBean(ProductService.class);
		updateProductList();

		System.out.println("\n");
		System.out.println("Hello from Inventory Management System");

		menu();

		context.close();
	}

	public static void menu() {
		int menu = 0;
		boolean condition = true;
		while (condition) {
			try {
				System.out.println("Menu Lists");
				System.out.println("1 -> Add Product");
				System.out.println("2 -> View Products");
				System.out.println("3 -> Search Product");
				System.out.println("9 -> To Quick Program");
				System.out.print("Please enter here:: ");
				menu = scan.nextInt();
				checkingCondition.checkInput(menu);
				condition = false;
				System.out.println();
			}

			catch (MissInputException e) {
				scan.nextLine();
				System.out.println(e);
			}

//			catch (Exception e) {
//				scan.nextLine();
//				System.out.println("only need to input No.");
//			}
		}

		if (menu == 1) {
			addProduct();
		}
		else if (menu == 2) {
			if (products.isEmpty()) {
				displayProducts();
				menu();
			} else {
				viewProduct();
			}
		}
		else if (menu == 3) {
			searchProduct();
		}
		else if (menu == 9) {
			MergeSort.mergeSort(products, "id");
			CompletableFuture<Boolean> futureResult = productService.saveProduct(products);
			futureResult.thenAccept(result -> {
				System.out.println("Data Saved Successfully");
				System.exit(0);
			});
		}
		else if (menu == 8) {
			productService.saveProduct(products);
		}
	}

	public static void addProduct() {
		System.out.println("Adding Product");
		System.out.println("\nEnter Product Name");
		String pname = scan.nextLine();
		System.out.println("Enter Product Category");
		String pcat = scan.nextLine();
		System.out.println("Enter Quantity");
		int pqty = scan.nextInt();
		System.out.println("Enter Price");
		double pprice = scan.nextDouble();
		scan.nextLine();
		System.out.println("Enter Currency Type");
		String pcurrency = scan.nextLine();

		System.out.println("\n\n-----Adding Product---");

		int Id;
		if (products.isEmpty()) {
			Id = 1;
		}
		else {
			Id = products.getLast().getId() + 1;
		}
		products.add(new Product(Id, pname, pcat, pqty, pprice, pcurrency));

		System.out.println("-*******Successfully Added-********\n\n");
		displayProducts();
		menu();
	}

	public static void viewProduct() {
		displayProducts();
		System.out.println("Sort by 1:Name 2:Category 3:Price 4:Id");
		int sortType = scan.nextInt();
		scan.nextLine();

		if (sortType == 1) {
			MergeSort.mergeSort(products, "name");
			System.out.println("Sorted Name by ascending order");
			displayProducts();
		} else if (sortType == 2) {
//			MergeSort.mergeSort(products, "Category");
			System.out.println("Sorted Category by ascending order");
			displayProducts();
		} else if (sortType == 3) {
			MergeSort.mergeSort(products, "price");
			System.out.println("Sorted Price by ascending order");
			displayProducts();
		} else if (sortType == 4) {
			MergeSort.mergeSort(products, "id");
			System.out.println("Sorted Id by ascending order");
			displayProducts();
		}

		menu();
	}

	public static void searchProduct() {
		System.out.println("\n.....Searching Function.....\n");
		System.out.println("Enter Product name to Search ");
		String target = scan.nextLine();
		MergeSort.mergeSort(products, "name");
		int resultId = BinarySearch.binarySearch(products, target);
		if (resultId == -1) {
			System.out.println("...Product " + target + " doesn't exist in Database...");
		}
		else {
			System.out.println("Product is found");
			System.out.println(products.get(resultId));
		}
		menu();
	}

	public static void updateProductList() {
		products = productService.getProducts();
	}

	public static void displayProducts() {
		System.out.println("\n\n---** Product Lists **---\n");
        if (!products.isEmpty()) {
			for (Product product : products) {
				System.out.println(product.getId() + "\t" + product.getName() + "\t" + product.getCategory() + "\t" + product.getQuantity() + "\t" + product.getPrice() + "\t" + product.getCurrency() + " ");
			}
			System.out.println("\n\n");
		} else {
			System.out.println("\n******Your Inventory System is Empty*******\n");
		}
	}

}
