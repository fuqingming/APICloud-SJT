package com.apicloud.moduleDemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.imagetrans.CustomTransform;
import com.apicloud.moduleDemo.util.imagetrans.MyImageLoad;
import com.apicloud.moduleDemo.util.imagetrans.MyImageTransAdapter;
import com.apicloud.moduleDemo.util.imagetrans.MyProgressBarGet;
import com.apicloud.moduleDemo.util.recycler.PhotoViewHolder;
import com.apicloud.sdk.moduledemo.R;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import it.liuting.imagetrans.ImageTrans;
import it.liuting.imagetrans.listener.SourceImageViewGet;

public class PhotoAlbumAdapter extends RecyclerView.Adapter<PhotoViewHolder>
{

	private Context context;
	private List<String> images = new ArrayList<>();
	private RecyclerView recyclerView;
	private int itemSize;

	public PhotoAlbumAdapter(Context context,RecyclerView recyclerView,int itemSize)
	{
		this.context = context;
		this.recyclerView = recyclerView;
		this.itemSize = itemSize;
	}

	public void setData(List<String> images)
	{
		this.images = images;
	}

	@Override
	public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		ImageView imageView = new ImageView(parent.getContext());
		imageView.setLayoutParams(new ViewGroup.LayoutParams(itemSize, itemSize));
		return new PhotoViewHolder(imageView);
	}

	@Override
	public void onBindViewHolder(final PhotoViewHolder holder, final int position)
	{

		((ImageView)holder.itemView).setScaleType(ImageView.ScaleType.CENTER_CROP);
		ImageLoader.getInstace().loadRoundedCornersImg(context, (ImageView) holder.itemView, images.get(position),5,R.drawable.place_holder);
		holder.itemView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ImageTrans.with(context)
						.setImageList(images)
						.setSourceImageView(new SourceImageViewGet()
						{
							@Override
							public ImageView getImageView(int pos)
							{
								int layoutPos = recyclerView.indexOfChild(holder.itemView);
								View view = recyclerView.getChildAt(layoutPos + pos - position);
								if (view != null) return (ImageView) view;
								return (ImageView) holder.itemView;
							}
						})
						.setImageLoad(new MyImageLoad())
						.setNowIndex(position)
						.setProgressBar(new MyProgressBarGet())
						.setAdapter(new MyImageTransAdapter())
						.show();
			}
		});
	}

	@Override
	public int getItemCount()
	{
		return images.size();
	}
}