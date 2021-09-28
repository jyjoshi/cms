// Generated by view binder compiler. Do not edit!
package com.example.cms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.cms.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListItemLayoutAdminBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final ImageView imgIcon;

  @NonNull
  public final TextView textDescription;

  @NonNull
  public final TextView textPrice;

  @NonNull
  public final TextView textTitle;

  private ListItemLayoutAdminBinding(@NonNull CardView rootView, @NonNull ImageView imgIcon,
      @NonNull TextView textDescription, @NonNull TextView textPrice, @NonNull TextView textTitle) {
    this.rootView = rootView;
    this.imgIcon = imgIcon;
    this.textDescription = textDescription;
    this.textPrice = textPrice;
    this.textTitle = textTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ListItemLayoutAdminBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListItemLayoutAdminBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_item_layout_admin, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListItemLayoutAdminBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_icon;
      ImageView imgIcon = rootView.findViewById(id);
      if (imgIcon == null) {
        break missingId;
      }

      id = R.id.text_description;
      TextView textDescription = rootView.findViewById(id);
      if (textDescription == null) {
        break missingId;
      }

      id = R.id.text_price;
      TextView textPrice = rootView.findViewById(id);
      if (textPrice == null) {
        break missingId;
      }

      id = R.id.text_title;
      TextView textTitle = rootView.findViewById(id);
      if (textTitle == null) {
        break missingId;
      }

      return new ListItemLayoutAdminBinding((CardView) rootView, imgIcon, textDescription,
          textPrice, textTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
