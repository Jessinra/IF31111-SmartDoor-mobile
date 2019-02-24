package com.knockknock.dragonra.smartdoor.view.Component;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class DashboardCardView extends CardView {
    private static int cardCreated = 0;
    private static int defaultCardIdBase = 1000000;

    public DashboardCardView(Context context) {
        super(context);

        DashboardCardView.cardCreated++;

        setId();
        setDefaultLayoutParams();
        setLayoutBelow();
        setDefaultRadius();
        setDefaultAlignParent();
        setDefaultMargin();
        setCardPreventOverlap();
    }

    private void setId() {
        super.setId(defaultCardIdBase + cardCreated);
    }

    private void setDefaultLayoutParams() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(335, 146);
        super.setLayoutParams(params);
    }

    private void setLayoutBelow() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.getLayoutParams();
        params.addRule(RelativeLayout.BELOW, defaultCardIdBase + cardCreated - 1);
        this.setLayoutParams(params);
    }

    private void setDefaultRadius() {
        super.setRadius(5);
    }

    private void setDefaultAlignParent() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        this.setLayoutParams(params);
    }

    private void setDefaultMargin() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.getLayoutParams();

        if (cardCreated == 1) {
            // Differentiate first card
            params.setMargins(27, 200, 27, 0);

        } else {
            params.setMargins(27, 11, 27, 0);
        }
        this.requestLayout();
    }

    private void setCardPreventOverlap() {
        super.setPreventCornerOverlap(true);
    }

    private static int getLastCreatedId() {
        return defaultCardIdBase + cardCreated;
    }

    public static void setLastCardMargin(View view) {
        View lastCard = view.findViewById(getLastCreatedId());
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) lastCard.getLayoutParams();
        params.setMargins(27, 11, 27, 270);
        lastCard.requestLayout();
    }

}
