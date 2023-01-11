package com.google.ar.sceneform.samples.augmentedfaces.product.service;

import android.content.Context;

import com.google.ar.sceneform.samples.augmentedfaces.R;
import com.google.ar.sceneform.samples.augmentedfaces.cart.CartActivity;
import com.google.ar.sceneform.samples.augmentedfaces.form.FormOrder;
import com.google.ar.sceneform.samples.augmentedfaces.product.Product;
import com.google.ar.sceneform.samples.augmentedfaces.product_catalog.ProductCatalog;
import com.google.ar.sceneform.samples.augmentedfaces.product_details.ProductDetails;
import com.google.ar.sceneform.samples.augmentedfaces.product_view.ProductView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProductList {
    public static ArrayList<Product> list = new ArrayList<>();

    //Count items for Notification Badge
    public static int counter = 0;

    private Context context;

    private static final DecimalFormat df = new DecimalFormat(",###.00");

    public void addCartItem( String name, String price, String color, int quantity, double total){
        if (list.isEmpty()){
            //Get the Array if Empty because the loop won't work if empty.
            list.add(new Product(
                    name,
                    price,
                    color,
                    quantity,
                    total)
            );

        }else{
            int previous_quantity = 0;
            double previous_total = 0.0;
            int getIndex = 0;

            for (Product myList : list) {
                if (myList.name.equals(name) && myList.color.equals(color)) {
                    previous_quantity = myList.quantity;
                    previous_total = myList.total;
                    list.remove(myList);
                    break;
                }
                getIndex++;
            }

            list.add(getIndex, new Product(
                    name,
                    price,
                    color,
                    quantity + previous_quantity,
                    total + previous_total)
            );

        }
        countList();
        getSubTotal();
    }

    public void removeCartItem(String name){
        for (Product myList : list) {
            if (myList.name.equals(name)){
                list.remove(myList);
                break;
            }
        }
        countList();
    }

    public void updateCartItem(String color, String name, int quantity, double total){
        for (Product myList : list) {
            if (myList.name.equals(name) && myList.color.equals(color)){
                myList.quantity = quantity;
                myList.total = total;
                break;
            }
        }
    }

    public void checkOut(){
        String item_purchase = "";
        int total_items = 0;
        double total = 0.0;

        for (Product myList : list) {
            item_purchase += "   " + myList.name + " ("+ myList.color +")" + " ("+ myList.quantity +")" + "\n";
            total_items += myList.quantity;
            total += myList.total;
        }

        FormOrder.txt_items = item_purchase;
        FormOrder.txt_quantity = total_items + "";
        FormOrder.txt_total = df.format(total);
    }

    //create cart list
    public void createList() {
        for (Product myList : list) {

            if (myList.name.equals("RAY-BAN WAYFARER")){
                Map<String, Object> listView = new HashMap<>();
                if(myList.color.equalsIgnoreCase("Black")){
                    listView.put("photo", R.drawable.model_1_black);
                }else if(myList.color.equalsIgnoreCase("Blue")){
                    listView.put("photo", R.drawable.model_1_blue);
                }else if(myList.color.equalsIgnoreCase("Green")) {
                    listView.put("photo", R.drawable.model_1_green);
                }
                listView.put("name", "Name: " + myList.name);
                listView.put("price", "Price: ₱" +myList.price);
                listView.put("color", "Color: " +myList.color);
                if (myList.quantity>1){
                    listView.put("quantity", "Quantity: " +myList.quantity+"pcs");
                }else{
                    listView.put("quantity", "Quantity: " +myList.quantity+"pc");
                }
                listView.put("total", "Total: ₱" + df.format(myList.total));
                CartActivity.mySimpleList.add(listView);

            }else if (myList.name.equals("BOSE FRAMES")){
                Map<String, Object> listView = new HashMap<>();
                if(myList.color.equalsIgnoreCase("Black")){
                    listView.put("photo", R.drawable.model_2_black);
                }else if(myList.color.equalsIgnoreCase("Blue")){
                    listView.put("photo", R.drawable.model_2_blue);
                }else if(myList.color.equalsIgnoreCase("Green")) {
                    listView.put("photo", R.drawable.model_2_green);
                }
                listView.put("name", "Name: " + myList.name);
                listView.put("price", "Price: ₱" +myList.price);
                listView.put("color", "Color: " +myList.color);
                if (myList.quantity>1){
                    listView.put("quantity", "Quantity: " +myList.quantity+"pcs");
                }else{
                    listView.put("quantity", "Quantity: " +myList.quantity+"pc");
                }
                listView.put("total", "Total: ₱" + df.format(myList.total));
                CartActivity.mySimpleList.add(listView);

            }else if (myList.name.equals("VINTAGE WIRE ROUND RIM GLASSES")){
                Map<String, Object> listView = new HashMap<>();
                if(myList.color.equalsIgnoreCase("Black")){
                    listView.put("photo", R.drawable.model_3_black);
                }else if(myList.color.equalsIgnoreCase("Blue")){
                    listView.put("photo", R.drawable.model_3_blue);
                }else if(myList.color.equalsIgnoreCase("Green")) {
                    listView.put("photo", R.drawable.model_3_green);
                }
                listView.put("name", "Name: " + myList.name);
                listView.put("price", "Price: ₱" +myList.price);
                listView.put("color", "Color: " +myList.color);
                if (myList.quantity>1){
                    listView.put("quantity", "Quantity: " +myList.quantity+"pcs");
                }else{
                    listView.put("quantity", "Quantity: " +myList.quantity+"pc");
                }
                listView.put("total", "Total: ₱" + df.format(myList.total));
                CartActivity.mySimpleList.add(listView);

            }else if (myList.name.equals("VERSACE G LONDON")){
                Map<String, Object> listView = new HashMap<>();
                if(myList.color.equalsIgnoreCase("Black")){
                    listView.put("photo", R.drawable.model_4_black);
                }else if(myList.color.equalsIgnoreCase("Blue")){
                    listView.put("photo", R.drawable.model_4_blue);
                }else if(myList.color.equalsIgnoreCase("Green")) {
                    listView.put("photo", R.drawable.model_4_green);
                }
                listView.put("name", "Name: " + myList.name);
                listView.put("price", "Price: ₱" +myList.price);
                listView.put("color", "Color: " +myList.color);
                if (myList.quantity>1){
                    listView.put("quantity", "Quantity: " +myList.quantity+"pcs");
                }else{
                    listView.put("quantity", "Quantity: " +myList.quantity+"pc");
                }
                listView.put("total", "Total: ₱" + df.format(myList.total));
                CartActivity.mySimpleList.add(listView);

            }else if (myList.name.equals("ERMENEGILDO ZEGNA")){
                Map<String, Object> listView = new HashMap<>();
                if(myList.color.equalsIgnoreCase("Black")){
                    listView.put("photo", R.drawable.model_5_black);
                }else if(myList.color.equalsIgnoreCase("Blue")){
                    listView.put("photo", R.drawable.model_5_blue);
                }else if(myList.color.equalsIgnoreCase("Green")) {
                    listView.put("photo", R.drawable.model_5_green);
                }
                listView.put("name", "Name: " + myList.name);
                listView.put("price", "Price: ₱" +myList.price);
                listView.put("color", "Color: " +myList.color);
                if (myList.quantity>1){
                    listView.put("quantity", "Quantity: " +myList.quantity+"pcs");
                }else{
                    listView.put("quantity", "Quantity: " +myList.quantity+"pc");
                }
                listView.put("total", "Total: ₱" + df.format(myList.total));
                CartActivity.mySimpleList.add(listView);

            }

        }
    }

    public void countList() {
        counter = 0;
        for (Product myList : list) {
            counter++;
        }
        setBadge(counter);
    }

    public void setBadge(int count_item){
        ProductCatalog.catalog_badge.setNumber(count_item);
        ProductView.prod_view_badge.setNumber(count_item);
        ProductDetails.prod_details_badge.setNumber(count_item);
    }

    public ArrayList getItem(){
        ArrayList items = new ArrayList();
        for (Product myList : list) {
            items.add(
                    "Name: "+ myList.name +"\n"
                            + "Price: "+ myList.price +"\n"
                            +"Quantity: "+myList.quantity +"\n"
                            +"Total: "+myList.total);
        }
        return items;
    }

    public Double getSubTotal(){
        double sub_total = 0.0;
        for (Product myList : list) {
            sub_total += myList.total;
        }
        return sub_total;
    }


}
