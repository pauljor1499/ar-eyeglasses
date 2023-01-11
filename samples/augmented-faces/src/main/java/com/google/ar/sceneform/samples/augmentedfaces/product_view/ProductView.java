package com.google.ar.sceneform.samples.augmentedfaces.product_view;
import androidx.appcompat.app.AppCompatActivity;
import com.google.ar.sceneform.samples.augmentedfaces.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.ar.sceneform.samples.augmentedfaces.product_catalog.ProductCatalog;
import com.google.ar.sceneform.samples.augmentedfaces.product_details.ProductDetails;
import com.google.ar.sceneform.samples.augmentedfaces.cart.CartActivity;
import com.google.ar.sceneform.samples.augmentedfaces.product.service.ProductList;
import com.google.ar.sceneform.samples.augmentedfaces.MainActivity;
import com.nex3z.notificationbadge.NotificationBadge;


public class ProductView extends AppCompatActivity {

    public static NotificationBadge prod_view_badge;
    private ProductList prod_list = new ProductList();
    private ImageButton buttonBack, cartIcon;
    private ImageView title_bar;
    //Bundle
    private String product_label, product_price, product_weight, product_details;
    private int product_image;
    //
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        //hide on-screen bottom navigation
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        ImageView imageView = (ImageView) findViewById(R.id.viewProductImage);
        TextView item_name = (TextView) findViewById(R.id.viewProductName);
        TextView item_price = (TextView) findViewById(R.id.viewProductPrice);
        TextView item_weight = (TextView) findViewById(R.id.viewProductWeight);
        TextView item_details = (TextView) findViewById(R.id.viewProductDetails);

        //Get Value From Catalog
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            product_label = bundle.getString("LABEL");
            product_price = bundle.getString("PRICE");
            product_image = bundle.getInt("IMAGE");
            product_weight = bundle.getString("WEIGHT");
            product_details = bundle.getString("DETAILS");

            item_name.setText(product_label);
            item_price.setText(product_price);
            imageView.setImageResource(product_image);
            item_weight.setText(product_weight);
            item_details.setText(product_details);
        }

        title_bar = (ImageView) findViewById(R.id.app_bar_title);
        title_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ProductView.this, ProductCatalog.class);
                startActivity(intent);
            }
        });

        cartIcon = (ImageButton) findViewById(R.id.catalog_cart_icon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ProductView.this, CartActivity.class);
                startActivity(intent);
            }
        });

        prod_view_badge = (NotificationBadge) findViewById(R.id.catalog_cart_badge);
        prod_view_badge.setNumber(prod_list.counter);

        buttonBack = (ImageButton) findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Buy(View v) {

        if (product_label.equalsIgnoreCase("RAY-BAN WAYFARER")){
            intent = new Intent(this, ProductDetails.class);
            intent.putExtra("LABEL", "RAY-BAN WAYFARER");
            intent.putExtra("COLOR", "Black");
            intent.putExtra("PRICE", "599.99");
            intent.putExtra("PICTURE", R.drawable.model_1_black);
            startActivity(intent);

        }else if (product_label.equalsIgnoreCase("BOSE FRAMES")){
            intent = new Intent(this, ProductDetails.class);
            intent.putExtra("LABEL", "BOSE FRAMES");
            intent.putExtra("COLOR", "Black");
            intent.putExtra("PRICE", "499.99");
            intent.putExtra("PICTURE", R.drawable.model_2_black);
            startActivity(intent);

        }else if (product_label.equalsIgnoreCase("VINTAGE WIRE ROUND RIM GLASSES")){
            intent = new Intent(this, ProductDetails.class);
            intent.putExtra("LABEL", "VINTAGE WIRE ROUND RIM GLASSES");
            intent.putExtra("COLOR", "Black");
            intent.putExtra("PRICE", "799.99");
            intent.putExtra("PICTURE", R.drawable.model_3_black);
            startActivity(intent);

        }else if (product_label.equalsIgnoreCase("VERSACE G LONDON")){
            intent = new Intent(this, ProductDetails.class);
            intent.putExtra("LABEL", "VERSACE G LONDON");
            intent.putExtra("COLOR", "Black");
            intent.putExtra("PRICE", "886.80");
            intent.putExtra("PICTURE", R.drawable.model_4_black);
            startActivity(intent);

        }else if (product_label.equalsIgnoreCase("ERMENEGILDO ZEGNA")){
            intent = new Intent(this, ProductDetails.class);
            intent.putExtra("LABEL", "ERMENEGILDO ZEGNA");
            intent.putExtra("COLOR", "Black");
            intent.putExtra("PRICE", "756.80");
            intent.putExtra("PICTURE", R.drawable.model_5_black);
            startActivity(intent);
        }

    }

    public void Try(View v){

        if (product_label.equalsIgnoreCase("RAY-BAN WAYFARER")){
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LABEL", "RAY-BAN WAYFARER");
            intent.putExtra("MODEL", "models/glass_1/black.glb");
            startActivity(intent);

        }else if (product_label.equalsIgnoreCase("BOSE FRAMES")){
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LABEL", "BOSE FRAMES");
            intent.putExtra("MODEL", "models/glass_2/black.glb");
            startActivity(intent);

        }else if (product_label.equalsIgnoreCase("VINTAGE WIRE ROUND RIM GLASSES")){
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LABEL", "VINTAGE WIRE ROUND RIM GLASSES");
            intent.putExtra("MODEL", "models/glass_3/black.glb");
            startActivity(intent);

        }else if (product_label.equalsIgnoreCase("VERSACE G LONDON")){
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LABEL", "VERSACE G LONDON");
            intent.putExtra("MODEL", "models/glass_4/black.glb");
            startActivity(intent);

        }else if (product_label.equalsIgnoreCase("ERMENEGILDO ZEGNA")){
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LABEL", "ERMENEGILDO ZEGNA");
            intent.putExtra("MODEL", "models/glass_5/black.glb");
            startActivity(intent);

        }
    }


}