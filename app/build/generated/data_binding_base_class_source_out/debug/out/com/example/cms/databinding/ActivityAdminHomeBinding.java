// Generated by view binder compiler. Do not edit!
package com.example.cms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.cms.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAdminHomeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button toMenu;

  @NonNull
  public final Button toMenuEditor;

  @NonNull
  public final Button toOrderList;

  @NonNull
  public final Button toTeacherSignUp;

  private ActivityAdminHomeBinding(@NonNull LinearLayout rootView, @NonNull Button toMenu,
      @NonNull Button toMenuEditor, @NonNull Button toOrderList, @NonNull Button toTeacherSignUp) {
    this.rootView = rootView;
    this.toMenu = toMenu;
    this.toMenuEditor = toMenuEditor;
    this.toOrderList = toOrderList;
    this.toTeacherSignUp = toTeacherSignUp;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAdminHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAdminHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_admin_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAdminHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.toMenu;
      Button toMenu = rootView.findViewById(id);
      if (toMenu == null) {
        break missingId;
      }

      id = R.id.toMenuEditor;
      Button toMenuEditor = rootView.findViewById(id);
      if (toMenuEditor == null) {
        break missingId;
      }

      id = R.id.toOrderList;
      Button toOrderList = rootView.findViewById(id);
      if (toOrderList == null) {
        break missingId;
      }

      id = R.id.toTeacherSignUp;
      Button toTeacherSignUp = rootView.findViewById(id);
      if (toTeacherSignUp == null) {
        break missingId;
      }

      return new ActivityAdminHomeBinding((LinearLayout) rootView, toMenu, toMenuEditor,
          toOrderList, toTeacherSignUp);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
