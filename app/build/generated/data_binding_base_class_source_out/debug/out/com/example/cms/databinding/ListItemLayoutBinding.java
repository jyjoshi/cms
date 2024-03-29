// Generated by view binder compiler. Do not edit!
package com.example.cms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.cms.R;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListItemLayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageButton add;

  @NonNull
  public final ShapeableImageView imgIcon;

  @NonNull
  public final ImageButton sub;

  @NonNull
  public final TextView textDescription;

  @NonNull
  public final TextView textPrice;

  @NonNull
  public final TextView textQuantity;

  @NonNull
  public final TextView textTitle;

  private ListItemLayoutBinding(@NonNull CardView rootView, @NonNull ImageButton add,
      @NonNull ShapeableImageView imgIcon, @NonNull ImageButton sub,
      @NonNull TextView textDescription, @NonNull TextView textPrice,
      @NonNull TextView textQuantity, @NonNull TextView textTitle) {
    this.rootView = rootView;
    this.add = add;
    this.imgIcon = imgIcon;
    this.sub = sub;
    this.textDescription = textDescription;
    this.textPrice = textPrice;
    this.textQuantity = textQuantity;
    this.textTitle = textTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListItemLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListItemLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_item_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListItemLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add;
      ImageButton add = ViewBindings.findChildViewById(rootView, id);
      if (add == null) {
        break missingId;
      }

      id = R.id.img_icon;
      ShapeableImageView imgIcon = ViewBindings.findChildViewById(rootView, id);
      if (imgIcon == null) {
        break missingId;
      }

      id = R.id.sub;
      ImageButton sub = ViewBindings.findChildViewById(rootView, id);
      if (sub == null) {
        break missingId;
      }

      id = R.id.text_description;
      TextView textDescription = ViewBindings.findChildViewById(rootView, id);
      if (textDescription == null) {
        break missingId;
      }

      id = R.id.text_price;
      TextView textPrice = ViewBindings.findChildViewById(rootView, id);
      if (textPrice == null) {
        break missingId;
      }

      id = R.id.text_quantity;
      TextView textQuantity = ViewBindings.findChildViewById(rootView, id);
      if (textQuantity == null) {
        break missingId;
      }

      id = R.id.text_title;
      TextView textTitle = ViewBindings.findChildViewById(rootView, id);
      if (textTitle == null) {
        break missingId;
      }

      return new ListItemLayoutBinding((CardView) rootView, add, imgIcon, sub, textDescription,
          textPrice, textQuantity, textTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
