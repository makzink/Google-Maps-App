package com.kazmik.rapido.app.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.text.Html;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.kazmik.rapido.app.R;
import com.kazmik.rapido.app.api_response.directions.Routes_Content;
import com.kazmik.rapido.app.api_response.directions.Steps_Content;

import net.steamcrafted.materialiconlib.MaterialIconView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kazmik on 06/08/17.
 */

public class DirectionsFragment extends BottomSheetDialogFragment {

    Routes_Content routes_content;

    @Bind(R.id.directions_back)
    MaterialIconView directions_back;

    @Bind(R.id.directions_tv)
    TextView directions_tv;

    public DirectionsFragment()
    {

    }

    @SuppressLint("ValidFragment")
    public DirectionsFragment(Routes_Content routes_content)
    {
        this.routes_content = routes_content;
    }

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.slide_vertical_anim;
        final View contentView = View.inflate(getContext(), R.layout.directions_fragment, null);
        dialog.setContentView(contentView);

        ButterKnife.bind(this,contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        final CoordinatorLayout.Behavior behavior = params.getBehavior();

        if( behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);

            contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int height = contentView.getMeasuredHeight();
                    ((BottomSheetBehavior) behavior).setPeekHeight(height);
                }
            });

        }

        directions_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        directions_tv.setText("");
        if(routes_content!=null)
        {
            int count = 1;
            for (int i=0;i<routes_content.getLegs().size();i++)
            {
                for (int j=0;j<routes_content.getLegs().get(i).getSteps().size();j++)
                {
                    Steps_Content steps_content = routes_content.getLegs().get(i).getSteps().get(j);
                    directions_tv.append(String.valueOf(count) +". " + Html.fromHtml(steps_content.getHtml_instructions())+"\n");
                    count++;
                }
            }
        }


    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

}
