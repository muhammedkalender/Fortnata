package com.widerspiel.fortnatawallpapers;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class main extends AppCompatActivity {
    ImageView ivBackground;
    TextView tvName;
    RecyclerView recyclerView;
    Button btnSet;

    int width;
    int height;

    int ratioX;
    int ratioY;

    ArrayList<preview> list = new ArrayList<>();

    String stringURI;

    View.OnClickListener listenerItem;

    RelativeLayout rlMenu;

    final int DEFAULT_INDEX = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lib.context = getApplicationContext();

        rlMenu = ((RelativeLayout) findViewById(R.id.setMenu));
        tvName = (TextView) findViewById(R.id.tvName);
        btnSet = (Button) findViewById(R.id.btnWP);
        ivBackground = (ImageView) findViewById(R.id.ivBackground);

        ((ImageView) findViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int index = (int) v.getTag();

                    CropImage.activity(Uri.parse(stringURI + list.get(index).uri())).setAspectRatio(ratioX, ratioY).start(main.this);
                } catch (OutOfMemoryError ex) {
                    lib.message(R.string.set_wp_fail);
                } catch (SecurityException ex) {
                    lib.message(R.string.set_wp_fail);
                } catch (IllegalArgumentException ex) {
                    lib.message(R.string.set_wp_fail);
                } catch (Exception ex) {
                    lib.message(R.string.set_wp_fail);
                }
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), getResources().getInteger(R.integer.column_count));
        gridLayoutManager.scrollToPosition(0);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, new RecyclerAdapter.ItemListener() {
            @Override
            public void onItemClick(View v, int position) {
                load(position);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rvScroll);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        ((ImageView) findViewById(R.id.ivInfo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lib.message(R.string.license);
            }
        });

        ((ImageView) findViewById(R.id.ivShare)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            getString(R.string.share));
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                } catch (Exception ex) {

                }
            }
        });

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();

        int ratio = (height * 10000) / width;

        switch (ratio) {
            case 562:
                ratioX = 9;
                ratioY = 16;
                break;
            case 600:
                ratioX = 9;
                ratioY = 15;
                break;
            case 625:
                ratioX = 10;
                ratioY = 16;
                break;
            case 666:
                ratioX = 10;
                ratioY = 15;
                break;
            case 800:
                ratioX = 12;
                ratioY = 15;
                break;
            case 750:
                ratioX = 12;
                ratioY = 16;
                break;
            case 486:
                ratioX = 18;
                ratioY = 37;
                break;
            default:
                ratioX = 9;
                ratioY = 16;
                break;
        }

        //https://stackoverflow.com/a/9904752
        stringURI = "android.resource://" + getPackageName() + "/";

        list.add(new preview(R.mipmap.w1, "#1", false));
        list.add(new preview(R.mipmap.w2, "#2", false));
        list.add(new preview(R.mipmap.w3, "#3", false));
        list.add(new preview(R.mipmap.w4, "#4", false));
        list.add(new preview(R.mipmap.w5, "#5", false));
        list.add(new preview(R.mipmap.w6, "#6", false));
        list.add(new preview(R.mipmap.w7, "#7", false));
        list.add(new preview(R.mipmap.w8, "#8", false));
        list.add(new preview(R.mipmap.w9, "#9", false));
        list.add(new preview(R.mipmap.w10, "#10", false));
        list.add(new preview(R.mipmap.w11, "#11", false));
        list.add(new preview(R.mipmap.w12, "#12", false));
        list.add(new preview(R.mipmap.w13, "#13", false));
        list.add(new preview(R.mipmap.w14, "#14", false));
        list.add(new preview(R.mipmap.w15, "#15", false));
        list.add(new preview(R.mipmap.w16, "#16", false));
        list.add(new preview(R.mipmap.w17, "#17", false));
        list.add(new preview(R.mipmap.w18, "#18", false));
        list.add(new preview(R.mipmap.w19, "#19", false));
        list.add(new preview(R.mipmap.w20, "#20", false));
        list.add(new preview(R.mipmap.w21, "#21", false));
        list.add(new preview(R.mipmap.w22, "#22", false));
        list.add(new preview(R.mipmap.w23, "#23", false));
        list.add(new preview(R.mipmap.w24, "#24", false));
        list.add(new preview(R.mipmap.w25, "#25", false));
        list.add(new preview(R.mipmap.w26, "#26", false));
        list.add(new preview(R.mipmap.w27, "#27", false));
        list.add(new preview(R.mipmap.w28, "#28", false));
        list.add(new preview(R.mipmap.w29, "#29", false));
        list.add(new preview(R.mipmap.w30, "#30", false));
        list.add(new preview(R.mipmap.w31, "#31", false));
        list.add(new preview(R.mipmap.w32, "#32", false));
        list.add(new preview(R.mipmap.w33, "#33", false));
        list.add(new preview(R.mipmap.w34, "#34", false));
        list.add(new preview(R.mipmap.w35, "#35", false));
        list.add(new preview(R.mipmap.w36, "#36", false));
        list.add(new preview(R.mipmap.w37, "#37", false));
        list.add(new preview(R.mipmap.w38, "#38", false));
        list.add(new preview(R.mipmap.w39, "#39", false));
        list.add(new preview(R.mipmap.w40, "#40", false));
        list.add(new preview(R.mipmap.w41, "#41", false));
        list.add(new preview(R.mipmap.w42, "#42", false));
        list.add(new preview(R.mipmap.w43, "#43", false));
        list.add(new preview(R.mipmap.w44, "#44", false));
        list.add(new preview(R.mipmap.w45, "#45", false));
        list.add(new preview(R.mipmap.w46, "#46", false));
        list.add(new preview(R.mipmap.w47, "#47", false));
        list.add(new preview(R.mipmap.w48, "#48", false));
        list.add(new preview(R.mipmap.w49, "#49", false));
        list.add(new preview(R.mipmap.w50, "#50", false));
        list.add(new preview(R.mipmap.w51, "#51", false));
        list.add(new preview(R.mipmap.w52, "#52", false));
        list.add(new preview(R.mipmap.w53, "#53", false));
        list.add(new preview(R.mipmap.w54, "#54", false));
        list.add(new preview(R.mipmap.w55, "#55", false));
        list.add(new preview(R.mipmap.w56, "#56", false));
        list.add(new preview(R.mipmap.w57, "#57", false));
        list.add(new preview(R.mipmap.w58, "#58", false));
        list.add(new preview(R.mipmap.w59, "#59", false));
        list.add(new preview(R.mipmap.w60, "#60", false));
        list.add(new preview(R.mipmap.w61, "#61", false));
        list.add(new preview(R.mipmap.w62, "#62", false));
        list.add(new preview(R.mipmap.w63, "#63", false));
        list.add(new preview(R.mipmap.w64, "#64", false));
        list.add(new preview(R.mipmap.w65, "#65", false));
        list.add(new preview(R.mipmap.w66, "#66", false));
        list.add(new preview(R.mipmap.w67, "#67", false));
        list.add(new preview(R.mipmap.w68, "#68", false));
        list.add(new preview(R.mipmap.w69, "#69", false));
        list.add(new preview(R.mipmap.w70, "#70", false));
        list.add(new preview(R.mipmap.w71, "#71", false));
        list.add(new preview(R.mipmap.w72, "#72", false));
        list.add(new preview(R.mipmap.w73, "#73", false));
        list.add(new preview(R.mipmap.w74, "#74", false));
        list.add(new preview(R.mipmap.w75, "#75", false));
        list.add(new preview(R.mipmap.w76, "#76", false));
        list.add(new preview(R.mipmap.w77, "#77", false));
        list.add(new preview(R.mipmap.w78, "#78", false));
        list.add(new preview(R.mipmap.w79, "#79", false));
        list.add(new preview(R.mipmap.w80, "#80", false));
        list.add(new preview(R.mipmap.w81, "#81", false));
        list.add(new preview(R.mipmap.w82, "#82", false));

        load(true);

        loadAd(true);
    }

    @Override
    public void onBackPressed() {
        try {
            if (rlMenu.getVisibility() == View.VISIBLE) {
                recyclerView.setScrollX(0);
                recyclerView.setVisibility(View.VISIBLE);

                rlMenu.setVisibility(View.INVISIBLE);
            } else {
                this.finish();
            }
        } catch (Exception ex) {
            lib.err(4, ex);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                btnSet.setClickable(false);

                final Uri resultUri = result.getUri();

                Glide.with(this)
                        .load(resultUri)
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                                try {
                                    InputStream input = getContentResolver().openInputStream(resultUri);
                                    Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(input), width, height, false);
                                    wallpaperManager.setBitmap(bitmap);

                                    Glide.with(getApplicationContext()).load(bitmap).into(ivBackground);

                                    loadAd(true);

                                    lib.deleteCache(getApplicationContext());

                                    lib.message(R.string.set_wp_success);
                                    btnSet.setClickable(true);
                                } catch (IllegalArgumentException ex) {
                                    lib.err(576, ex);
                                    lib.message(R.string.set_wp_fail);
                                    btnSet.setClickable(true);
                                } catch (OutOfMemoryError ex) {
                                    lib.err(575, ex);
                                    lib.message(R.string.set_wp_fail);
                                    btnSet.setClickable(true);
                                } catch (FileNotFoundException ex) {
                                    lib.err(666, ex);
                                    lib.message(R.string.set_wp_fail);
                                    btnSet.setClickable(true);
                                } catch (IOException ex) {
                                    lib.err(667, ex);
                                    lib.message(R.string.set_wp_fail);
                                    btnSet.setClickable(true);
                                }
                            }
                        });
                btnSet.setClickable(true);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                lib.err(868, error);
                btnSet.setClickable(true);
            }
        }
    }


    void load(int INDEX) {
        try {
            Glide.with(getApplicationContext()).load(list.get(INDEX).uri()).into(ivBackground);

            tvName.setText(list.get(INDEX).name());
            btnSet.setTag(INDEX);

            rlMenu.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);

            loadAd(false);
        } catch (OutOfMemoryError ex) {
            lib.err(3, ex, INDEX + "");
        } catch (Exception ex) {
            lib.err(2, ex, INDEX + "");
        }
    }

    void load(boolean isDefault) {
        try {
            Glide.with(getApplicationContext()).load(list.get(DEFAULT_INDEX).uri()).into(ivBackground);

        } catch (OutOfMemoryError ex) {
            lib.err(13, ex, DEFAULT_INDEX + "");
        } catch (Exception ex) {
            lib.err(12, ex, DEFAULT_INDEX + "");
        }
    }

    int count = 0;
    boolean showPopup = false;


    InterstitialAd adsPopup;

    void loadAd(boolean isForce) {
        try {
            count++;

            if (count == 3 || isForce) {
                try {
                    if (adsPopup == null) {
                        MobileAds.initialize(this, getString(R.string.app_id));
                        adsPopup = new InterstitialAd(this);

                        adsPopup.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
                        adsPopup.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                count = 0;
                                showPopup = false;
                                adsPopup.loadAd(new AdRequest.Builder().build());
                            }

                            @Override
                            public void onAdLoaded() {
                                if (showPopup) {
                                    adsPopup.show();
                                }
                            }
                        });
                        adsPopup.loadAd(new AdRequest.Builder().build());
                    }

                    if (adsPopup.isLoaded()) {
                        adsPopup.show();
                    } else {
                        showPopup = true;
                    }
                } catch (Exception ex) {
                    lib.err(736, ex);
                }
            }
        } catch (Exception ex) {
            lib.err(666, ex);
        }
    }
}
