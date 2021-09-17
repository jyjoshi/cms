// Generated by view binder compiler. Do not edit!
package com.example.cms.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.cms.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnSaveChanges;

  @NonNull
  public final EditText editTextProfName;

  @NonNull
  public final EditText editTextProfPhone;

  @NonNull
  public final EditText editTextTextPostalAddress;

  @NonNull
  public final ImageView profImgId;

  @NonNull
  public final TextView textProfileName;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  private FragmentProfileBinding(@NonNull LinearLayout rootView, @NonNull Button btnSaveChanges,
      @NonNull EditText editTextProfName, @NonNull EditText editTextProfPhone,
      @NonNull EditText editTextTextPostalAddress, @NonNull ImageView profImgId,
      @NonNull TextView textProfileName, @NonNull TextView textView2, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.btnSaveChanges = btnSaveChanges;
    this.editTextProfName = editTextProfName;
    this.editTextProfPhone = editTextProfPhone;
    this.editTextTextPostalAddress = editTextTextPostalAddress;
    this.profImgId = profImgId;
    this.textProfileName = textProfileName;
    this.textView2 = textView2;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSaveChanges;
      Button btnSaveChanges = rootView.findViewById(id);
      if (btnSaveChanges == null) {
        break missingId;
      }

      id = R.id.editTextProfName;
      EditText editTextProfName = rootView.findViewById(id);
      if (editTextProfName == null) {
        break missingId;
      }

      id = R.id.editTextProfPhone;
      EditText editTextProfPhone = rootView.findViewById(id);
      if (editTextProfPhone == null) {
        break missingId;
      }

      id = R.id.editTextTextPostalAddress;
      EditText editTextTextPostalAddress = rootView.findViewById(id);
      if (editTextTextPostalAddress == null) {
        break missingId;
      }

      id = R.id.prof_img_id;
      ImageView profImgId = rootView.findViewById(id);
      if (profImgId == null) {
        break missingId;
      }

      id = R.id.text_profile_name;
      TextView textProfileName = rootView.findViewById(id);
      if (textProfileName == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = rootView.findViewById(id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = rootView.findViewById(id);
      if (textView3 == null) {
        break missingId;
      }

      return new FragmentProfileBinding((LinearLayout) rootView, btnSaveChanges, editTextProfName,
          editTextProfPhone, editTextTextPostalAddress, profImgId, textProfileName, textView2,
          textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}