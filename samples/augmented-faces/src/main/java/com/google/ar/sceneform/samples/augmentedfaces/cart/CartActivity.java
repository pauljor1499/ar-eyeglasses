package com.google.ar.sceneform.samples.augmentedfaces.cart;

import androidx.appcompat.app.AppCompatActivity;
import com.google.ar.sceneform.samples.augmentedfaces.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.ar.sceneform.samples.augmentedfaces.form.FormOrder;
import com.google.ar.sceneform.samples.augmentedfaces.product.Product;
import com.google.ar.sceneform.samples.augmentedfaces.product_catalog.ProductCatalog;
import com.google.ar.sceneform.samples.augmentedfaces.product.service.ProductList;

import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    public static NotificationBadge cart_badge;
    public static Button checkOut;
    public static List<Map<String, Object>> mySimpleList;

    private ProductList prod_list = new ProductList();
    private ListView item_list;
    private ImageButton buttonBack, cartButton;
    private NotificationBadge cartBadge;
    Button shop;
    private TextView message, subTotal;
    private RelativeLayout layout;
    private ImageView title_bar;

    private DecimalFormat df = new DecimalFormat(",###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //hide on-screen bottom navigation
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //list of items
        item_list = (ListView) findViewById(R.id.cart_list);
        //your cart is empty
        message = (TextView) findViewById(R.id.cart_message);
        //go shopping now
        shop = (Button) findViewById(R.id.cart_button_shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, ProductCatalog.class);
                startActivity(intent);
            }
        });

        //cart badge
        cart_badge = (NotificationBadge) findViewById(R.id.catalog_cart_badge);
        if (prod_list.counter == 0){
            message.setVisibility(View.VISIBLE);
            shop.setVisibility(View.VISIBLE);
            item_list.setVisibility(View.INVISIBLE);
        }else{
            message.setVisibility(View.INVISIBLE);
            shop.setVisibility(View.INVISIBLE);
            item_list.setVisibility(View.VISIBLE);
            cart_badge.setNumber(prod_list.counter);
        }

        //Adapter 1
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, prod_list.getItem());

        mySimpleList = new ArrayList<>();
        prod_list.createList();
        String [] from = {"photo","name", "price", "color", "quantity", "total" };
        int [] to = {R.id.list_itemPhoto, R.id.list_itemName, R.id.list_itemPrice, R.id.list_itemColor, R.id.list_itemQuantity, R.id.list_itemTotal };
        //Adapter2
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), mySimpleList, R.layout.listview_design, from, to);

        //set item list
        item_list.setAdapter(simpleAdapter);

        title_bar = (ImageView) findViewById(R.id.app_bar_title);
        title_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ProductCatalog.class);
                startActivity(intent);
            }
        });

        buttonBack = (ImageButton) findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = intent = new Intent(CartActivity.this, ProductCatalog.class);
                startActivity(intent);
            }
        });

        item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item_position = 0;
                for (Product myList : prod_list.list) {
                    if (position == item_position){

                        if (myList.name.equals("RAY-BAN WAYFARER")){
                            Intent intent = new Intent(CartActivity.this, CartItemActivity.class);
                            if(myList.color.equalsIgnoreCase("Black")){
                                intent.putExtra("PICTURE", R.drawable.model_1_black);
                            }else if(myList.color.equalsIgnoreCase("Blue")){
                                intent.putExtra("PICTURE", R.drawable.model_1_blue);
                            }else if(myList.color.equalsIgnoreCase("Green")) {
                                intent.putExtra("PICTURE", R.drawable.model_1_green);
                            }
                            intent.putExtra("NAME", myList.name);
                            intent.putExtra("PRICE", myList.price);
                            intent.putExtra("COLOR", myList.color);
                            intent.putExtra("QUANTITY", myList.quantity);
                            intent.putExtra("TOTAL", myList.total);
                            startActivity(intent);

                        }else if (myList.name.equals("BOSE FRAMES")){
                            Intent intent = new Intent(CartActivity.this, CartItemActivity.class);
                            if(myList.color.equalsIgnoreCase("Black")){
                                intent.putExtra("PICTURE", R.drawable.model_1_black);
                            }else if(myList.color.equalsIgnoreCase("Blue")){
                                intent.putExtra("PICTURE", R.drawable.model_1_blue);
                            }else if(myList.color.equalsIgnoreCase("Green")) {
                                intent.putExtra("PICTURE", R.drawable.model_1_green);
                            }
                            intent.putExtra("NAME", myList.name);
                            intent.putExtra("PRICE", myList.price);
                            intent.putExtra("COLOR", myList.color);
                            intent.putExtra("QUANTITY", myList.quantity);
                            intent.putExtra("TOTAL", myList.total);
                            startActivity(intent);

                        }else if (myList.name.equals("VINTAGE WIRE ROUND RIM GLASSES")){
                            Intent intent = new Intent(CartActivity.this, CartItemActivity.class);
                            if(myList.color.equalsIgnoreCase("Black")){
                                intent.putExtra("PICTURE", R.drawable.model_1_black);
                            }else if(myList.color.equalsIgnoreCase("Blue")){
                                intent.putExtra("PICTURE", R.drawable.model_1_blue);
                            }else if(myList.color.equalsIgnoreCase("Green")) {
                                intent.putExtra("PICTURE", R.drawable.model_1_green);
                            }
                            intent.putExtra("NAME", myList.name);
                            intent.putExtra("PRICE", myList.price);
                            intent.putExtra("COLOR", myList.color);
                            intent.putExtra("QUANTITY", myList.quantity);
                            intent.putExtra("TOTAL", myList.total);
                            startActivity(intent);

                        }else if (myList.name.equals("VERSACE G LONDON")){
                            Intent intent = new Intent(CartActivity.this, CartItemActivity.class);
                            if(myList.color.equalsIgnoreCase("Black")){
                                intent.putExtra("PICTURE", R.drawable.model_1_black);
                            }else if(myList.color.equalsIgnoreCase("Blue")){
                                intent.putExtra("PICTURE", R.drawable.model_1_blue);
                            }else if(myList.color.equalsIgnoreCase("Green")) {
                                intent.putExtra("PICTURE", R.drawable.model_1_green);
                            }
                            intent.putExtra("NAME", myList.name);
                            intent.putExtra("PRICE", myList.price);
                            intent.putExtra("COLOR", myList.color);
                            intent.putExtra("QUANTITY", myList.quantity);
                            intent.putExtra("TOTAL", myList.total);
                            startActivity(intent);

                        }else if (myList.name.equals("ERMENEGILDO ZEGNA")){
                            Intent intent = new Intent(CartActivity.this, CartItemActivity.class);
                            if(myList.color.equalsIgnoreCase("Black")){
                                intent.putExtra("PICTURE", R.drawable.model_1_black);
                            }else if(myList.color.equalsIgnoreCase("Blue")){
                                intent.putExtra("PICTURE", R.drawable.model_1_blue);
                            }else if(myList.color.equalsIgnoreCase("Green")) {
                                intent.putExtra("PICTURE", R.drawable.model_1_green);
                            }
                            intent.putExtra("NAME", myList.name);
                            intent.putExtra("PRICE", myList.price);
                            intent.putExtra("COLOR", myList.color);
                            intent.putExtra("QUANTITY", myList.quantity);
                            intent.putExtra("TOTAL", myList.total);
                            startActivity(intent);

                        }

//                        Toast.makeText(CartActivity.this, myList.name,Toast.LENGTH_SHORT).show();
                        item_position = 0;
                        break;
                    }
                    item_position++;
                }
            }
        });

        checkOut = (Button) findViewById(R.id.cart_CheckOut);
        checkOut.setText("Check Out ("+prod_list.counter+")");

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prod_list.checkOut();
                Intent intent = new Intent(CartActivity.this, FormOrder.class);
                startActivity(intent);
            }
        });

        subTotal = (TextView) findViewById(R.id.cart_subTotal);
        subTotal.setText("₱"+df.format(prod_list.getSubTotal()));

        layout = (RelativeLayout) findViewById(R.id.bottom_layout);
        if (prod_list.getSubTotal()==0){
            layout.setVisibility(View.INVISIBLE);
        }else{
            layout.setVisibility(View.VISIBLE);
        }

        cartButton = (ImageButton) findViewById(R.id.catalog_cart_icon);
        cartButton.setVisibility(View.INVISIBLE);

        cartBadge = (NotificationBadge) findViewById(R.id.catalog_cart_badge);
        cartBadge.setVisibility(View.INVISIBLE);

    }
}