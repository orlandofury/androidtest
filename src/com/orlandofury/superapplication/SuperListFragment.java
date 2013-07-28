package com.orlandofury.superapplication;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SuperListFragment extends Fragment implements OnClickListener {
	private EditText itxtDescription;
    private final int REQUEST_GALLERY = 1001;
    private final int REQUEST_CAMERA = 1002;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_list, null);
		view.findViewById(R.id.btnGallery).setOnClickListener(this);
		view.findViewById(R.id.btnCamera).setOnClickListener(this);

		itxtDescription = (EditText) view.findViewById(R.id.editText1);

		return view;

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnGallery:
			Toast.makeText(
					getActivity(),
					String.format("Description[Gallery] %s",
							itxtDescription.getText()), Toast.LENGTH_LONG)
					.show();
			callGallery(REQUEST_GALLERY);
			break;
		case R.id.btnCamera:
			Toast.makeText(
					getActivity(),
					String.format("Description[Camera] %s",
							itxtDescription.getText()), Toast.LENGTH_LONG)
					.show();
			takePicture(REQUEST_CAMERA);
			break;
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK)
		{
			switch (requestCode) {
			case REQUEST_GALLERY:
				Toast.makeText(getActivity(), "Gallery invoked fine", Toast.LENGTH_LONG).show();
				break;
			case REQUEST_CAMERA:
				Toast.makeText(getActivity(), "Camera invoked fine", Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}
		}
		else
		{
			Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_LONG).show();
		}
	}
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	private void takePicture(int actionCode) {
		final Intent takePictureIntent = new Intent(
				MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePictureIntent, actionCode);
	}

	private void callGallery(int actionCode) {
		final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
		galleryIntent.setType("image/*");
		startActivityForResult(galleryIntent, actionCode);
	}
	private void handleSmallCameraPhoto(Intent intent) {
	    Bundle extras = intent.getExtras();
	    Bitmap mImageBitmap = (Bitmap) extras.get("data");
	    //ImageView mImageView.setImageBitmap(mImageBitmap);
	}
}
