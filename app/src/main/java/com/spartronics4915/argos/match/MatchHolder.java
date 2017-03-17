package com.spartronics4915.argos.match;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * For populating the RecyclerView.
 */

public class MatchHolder extends RecyclerView.ViewHolder {
    private final TextView mNameField;
    private final TextView mDateField;

    public MatchHolder(View itemView) {
        super(itemView);
        mNameField = (TextView) itemView.findViewById(android.R.id.text1);
        mDateField = (TextView) itemView.findViewById(android.R.id.text2);
    }

    public void setName(String name) {
        mNameField.setText(name);
    }

    public void setText(String text) {
        mDateField.setText(text);
    }
}
