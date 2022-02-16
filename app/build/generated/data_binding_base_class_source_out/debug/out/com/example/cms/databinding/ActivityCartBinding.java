// Generated by view binder compiler. Do not edit!
package com.example.cms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.cms.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCartBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText address;

  @NonNull
  public final TextView headerFoodName;

  @NonNull
  public final TextView headerFoodPrice;

  @NonNull
  public final TextView headerFoodQty;

  @NonNull
  public final TextView headerResult;

  @NonNull
  public final Button placeOrder;

  @NonNull
  public final RecyclerView recyclerviewViewOrder;

  @NonNull
  public final TextView textForAddress;

  @NonNull
  public final TextView totalCost;

  private ActivityCartBinding(@NonNull LinearLayout rootView, @NonNull EditText address,
      @NonNull TextView headerFoodName, @NonNull TextView headerFoodPrice,
      @NonNull TextView headerFoodQty, @NonNull TextView headerResult, @NonNull Button placeOrder,
      @NonNull RecyclerView recyclerviewViewOrder, @NonNull TextView textForAddress,
      @NonNull TextView totalCost) {
    this.rootView = rootView;
    this.address = address;
    this.headerFoodName = headerFoodName;
    this.headerFoodPrice = headerFoodPrice;
    this.headerFoodQty = headerFoodQty;
    this.headerResult = headerResult;
    this.placeOrder = placeOrder;
    this.recyclerviewViewOrder = recyclerviewViewOrder;
    this.textForAddress = textForAddress;
    this.totalCost = totalCost;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_cart, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address;
      EditText address = ViewBindings.findChildViewById(rootView, id);
      if (address == null) {
        break missingId;
      }

      id = R.id.header_food_name;
      TextView headerFoodName = ViewBindings.findChildViewById(rootView, id);
      if (headerFoodName == null) {
        break missingId;
      }

      id = R.id.header_food_price;
      TextView headerFoodPrice = ViewBindings.findChildViewById(rootView, id);
      if (headerFoodPrice == null) {
        break missingId;
      }

      id = R.id.header_food_qty;
      TextView headerFoodQty = ViewBindings.findChildViewById(rootView, id);
      if (headerFoodQty == null) {
        break missingId;
      }

      id = R.id.header_result;
      TextView headerResult = ViewBindings.findChildViewById(rootView, id);
      if (headerResult == null) {
        break missingId;
      }

      id = R.id.place_order;
      Button placeOrder = ViewBindings.findChildViewById(rootView, id);
      if (placeOrder == null) {
        break missingId;
      }

      id = R.id.recyclerview_view_order;
      RecyclerView recyclerviewViewOrder = ViewBindings.findChildViewById(rootView, id);
      if (recyclerviewViewOrder == null) {
        break missingId;
      }

      id = R.id.text_for_address;
      TextView textForAddress = ViewBindings.findChildViewById(rootView, id);
      if (textForAddress == null) {
        break missingId;
      }

      id = R.id.total_cost;
      TextView totalCost = ViewBindings.findChildViewById(rootView, id);
      if (totalCost == null) {
        break missingId;
      }

      return new ActivityCartBinding((LinearLayout) rootView, address, headerFoodName,
          headerFoodPrice, headerFoodQty, headerResult, placeOrder, recyclerviewViewOrder,
          textForAddress, totalCost);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
