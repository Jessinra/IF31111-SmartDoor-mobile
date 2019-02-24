package com.knockknock.dragonra.smartdoor.view.Adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class CardViewAdapter {


    public static void setInvisible(View view) {
        view.setVisibility(View.GONE);
    }

    public static void setVisible(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void setLockState(View view, boolean lock) {
        if (lock) {
            setLocked(view);
        } else {
            setUnlocked(view);
        }
    }

    public static void setLocked(View view) {
        View lockedState = searchLockState(view);
        if (lockedState != null) {
            lockedState.setVisibility(View.VISIBLE);
        }
    }

    public static void setUnlocked(View view) {
        View lockedState = searchLockState(view);
        if (lockedState != null) {
            lockedState.setVisibility(View.GONE);
        }
    }

    public static void setText(View view, String text) {
        TextView cardText = (TextView) searchCardText(view);
        if (cardText != null) {
            cardText.setText(text);
        }
    }

    public static void setImage(View view, String imageUri) {
        ImageView cardImage = (ImageView) searchCardImage(view);
        if (cardImage != null) {
            cardImage.setImageURI(Uri.parse(imageUri));
        }
    }

    public static void setImage(View view, Uri imageUri) {
        ImageView cardImage = (ImageView) searchCardImage(view);
        if (cardImage != null) {
            cardImage.setImageURI(imageUri);
        }
    }


    private static View searchLockState(View view) {

        int foundOffset = 1; // skip the main card image

        ArrayList<View> allChild = getAllChildren(view);
        for (View childView : allChild) {
            if (childView instanceof ImageView) {

                if (foundOffset == 0) {
                    return childView;
                }

                foundOffset--;
            }
        }
        return null;
    }

    private static View searchCardText(View view) {

        ArrayList<View> allChild = getAllChildren(view);
        for (View childView : allChild) {
            if (childView instanceof TextView) {
                return childView;
            }
        }
        return null;
    }

    private static View searchCardImage(View view) {

        ArrayList<View> allChild = getAllChildren(view);
        for (View childView : allChild) {
            if (childView instanceof RoundedImageView) {
                return childView;
            }
        }
        return null;
    }

    private static ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<>();

        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

}
