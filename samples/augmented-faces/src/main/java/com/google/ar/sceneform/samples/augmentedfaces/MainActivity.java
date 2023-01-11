package com.google.ar.sceneform.samples.augmentedfaces;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.widget.Button;

import com.google.ar.core.AugmentedFace;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.Sceneform;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.RenderableInstance;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.ArFrontFacingFragment;
import com.google.ar.sceneform.ux.AugmentedFaceNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {

    private final Set<CompletableFuture<?>> loaders = new HashSet<>();

    private ArFrontFacingFragment arFragment;
    private ArSceneView arSceneView;

    private Texture faceTexture;

    private ModelRenderable faceModel;
    private final HashMap<AugmentedFace, AugmentedFaceNode> facesNodes = new HashMap<>();

    private String label, model;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.style_1);
        button2 = findViewById(R.id.style_2);
        button3 = findViewById(R.id.style_3);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            label = bundle.getString("LABEL");
            model = bundle.getString("MODEL");
        }

        getSupportFragmentManager().addFragmentOnAttachListener(this::onAttachFragment);
        loadModels(model);
        loadTextures();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(label.equalsIgnoreCase("RAY-BAN WAYFARER")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_1/blue.glb"));

                }else if(label.equalsIgnoreCase("BOSE FRAMES")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_2/blue.glb"));

                }else if(label.equalsIgnoreCase("VINTAGE WIRE ROUND RIM GLASSES")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_3/blue.glb"));

                }else if(label.equalsIgnoreCase("VERSACE G LONDON")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_4/blue.glb"));

                }else if(label.equalsIgnoreCase("ERMENEGILDO ZEGNA")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_5/blue.glb"));
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(label.equalsIgnoreCase("RAY-BAN WAYFARER")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_1/green.glb"));

                }else if(label.equalsIgnoreCase("BOSE FRAMES")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_2/green.glb"));

                }else if(label.equalsIgnoreCase("VINTAGE WIRE ROUND RIM GLASSES")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_3/green.glb"));

                }else if(label.equalsIgnoreCase("VERSACE G LONDON")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_4/green.glb"));

                }else if(label.equalsIgnoreCase("ERMENEGILDO ZEGNA")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_5/green.glb"));
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(label.equalsIgnoreCase("RAY-BAN WAYFARER")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_1/black.glb"));

                }else if(label.equalsIgnoreCase("BOSE FRAMES")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_2/black.glb"));

                }else if(label.equalsIgnoreCase("VINTAGE WIRE ROUND RIM GLASSES")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_3/black.glb"));

                }else if(label.equalsIgnoreCase("VERSACE G LONDON")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_4/black.glb"));

                }else if(label.equalsIgnoreCase("ERMENEGILDO ZEGNA")){
                    finish();
                    startActivity(getIntent().putExtra("MODEL", "models/glass_5/black.glb"));
                }
            }
        });

        if (savedInstanceState == null) {
            if (Sceneform.isSupported(this)) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.arFragment, ArFrontFacingFragment.class, null)
                        .commit();
            }
        }
    }


    public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        if (fragment.getId() == R.id.arFragment) {
            arFragment = (ArFrontFacingFragment) fragment;
            arFragment.setOnViewCreatedListener(this::onViewCreated);
        }
    }

    public void onViewCreated(ArSceneView arSceneView) {
        this.arSceneView = arSceneView;

        // This is important to make sure that the camera stream renders first so that
        // the face mesh occlusion works correctly.
        arSceneView.setCameraStreamRenderPriority(Renderable.RENDER_PRIORITY_FIRST);

        // Check for face detections
        arFragment.setOnAugmentedFaceUpdateListener(this::onAugmentedFaceTrackingUpdate);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        for (CompletableFuture<?> loader : loaders) {
//            if (!loader.isDone()) {
//                loader.cancel(true);
//            }
//        }
//    }

    public void onDestroy() {
        super.onDestroy();
        for (CompletableFuture<?> loader : loaders) {
            if (!loader.isDone()) {
                loader.cancel(true);
            }
        }
    }

    private void loadModels(String glassModel) {
        loaders.add(ModelRenderable.builder()
            .setSource(this, Uri.parse(glassModel))
            .setIsFilamentGltf(true)
            .build()
            .thenAccept(model -> faceModel = model)
            .exceptionally(throwable -> {
                Toast.makeText(this, "Unable to load renderable", Toast.LENGTH_LONG).show();
                return null;
            }));
    }


    private void loadTextures() {
        loaders.add(Texture.builder()
            .setSource(this, Uri.parse("textures/freckles.png"))
            .setUsage(Texture.Usage.COLOR_MAP)
            .build()
            .thenAccept(texture -> faceTexture = texture)
            .exceptionally(throwable -> {
                Toast.makeText(this, "Unable to load texture", Toast.LENGTH_LONG).show();
                return null;
            }));
    }

    public void onAugmentedFaceTrackingUpdate(AugmentedFace augmentedFace) {
        if (faceModel == null || faceTexture == null) {
            return;
        }

        AugmentedFaceNode existingFaceNode = facesNodes.get(augmentedFace);

        switch (augmentedFace.getTrackingState()) {
            case TRACKING:
                if (existingFaceNode == null) {
                    AugmentedFaceNode faceNode = new AugmentedFaceNode(augmentedFace);

                    RenderableInstance modelInstance = faceNode.setFaceRegionsRenderable(faceModel);
                    modelInstance.setShadowCaster(false);
                    modelInstance.setShadowReceiver(true);

                    faceNode.setFaceMeshTexture(faceTexture);

                    arSceneView.getScene().addChild(faceNode);

                    facesNodes.put(augmentedFace, faceNode);
                }
                break;
            case STOPPED:
                if (existingFaceNode != null) {
                    arSceneView.getScene().removeChild(existingFaceNode);
                }
                facesNodes.remove(augmentedFace);
                break;
        }
    }
}
