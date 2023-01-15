package com.google.ar.sceneform.samples.augmentedfaces.product_list;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.ar.sceneform.samples.augmentedfaces.R;
import com.google.ar.sceneform.samples.augmentedfaces.cart.CartActivity;
import com.google.ar.sceneform.samples.augmentedfaces.product.service.ProductList;
import com.nex3z.notificationbadge.NotificationBadge;

public class ProductCatalog3 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    public static NotificationBadge catalog_badge;

    private ProductList prod_list = new ProductList();
    private CardView item1, item2, item3, item4, item5;
    private ImageButton cartIcon, menuOption;
    private Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_catalog3);

        //hide on-screen bottom navigation
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        cartIcon = (ImageButton) findViewById(R.id.catalog_cart_icon);
        menuOption = (ImageButton) findViewById(R.id.catalog_option);

        catalog_badge = (NotificationBadge) findViewById(R.id.catalog_cart_badge);
        catalog_badge.setNumber(prod_list.counter);

        item1 = (CardView) findViewById(R.id.card_1);
        item2 = (CardView) findViewById(R.id.card_2);
        item3 = (CardView) findViewById(R.id.card_3);

        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ProductCatalog3.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.card_1) {

        } else if (v.getId() == R.id.card_2) {

        } else if (v.getId() == R.id.card_3) {

        }
    }

    public void showMenu(View view){
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.option_menu);
        popup.show();
    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
//            case R.id.item_1:
//                intent = new Intent(this, AboutUsActivity.class);
//                startActivity(intent);
//                return true;

//            case R.id.item_2:
//                intent = new Intent(this, ContactUsActivity.class);
//                startActivity(intent);
//                return true;

            case R.id.item_3:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Confirmation");
                alertDialogBuilder
                        .setMessage("Do you want to close the app?")
                        .setCancelable(false)
                        .setPositiveButton("No",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })

                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;

            default:
                return false;
        }
    }
}