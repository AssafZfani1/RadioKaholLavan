package zfani.assaf.radiokahollavan.utils.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import zfani.assaf.radiokahollavan.R;
import zfani.assaf.radiokahollavan.model.Broadcast;

public class BroadcastViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvName, tvBroadcastName, tvDescription, tvTimes;
    private final ImageView ivIcon;

    public BroadcastViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvBroadcastName = itemView.findViewById(R.id.tvBroadcastName);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        tvTimes = itemView.findViewById(R.id.tvTimes);
        ivIcon = itemView.findViewById(R.id.ivIcon);
    }

    public void bindData(Broadcast broadcast) {
        if (broadcast == null) {
            itemView.setVisibility(View.GONE);
        } else {
            itemView.setVisibility(View.VISIBLE);
            Glide.with(ivIcon.getContext()).load(broadcast.getBroadcasterImageUrl()).circleCrop().into(ivIcon);
            tvName.setText(broadcast.getName());
            tvBroadcastName.setText(broadcast.getBroadcasterName());
            tvDescription.setText(broadcast.getDescription());
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            tvTimes.setText(new StringBuilder(dateFormat.format(broadcast.getStartDate()) + "\n" + "עד" + "\n" + dateFormat.format(broadcast.getEndDate())));
            Date now = new Date(System.currentTimeMillis());
            itemView.setBackgroundResource(now.after(broadcast.getStartDate()) && now.before(broadcast.getEndDate()) ? R.drawable.big_white_radius_bg : R.drawable.big_gray_radius_bg);
        }
    }
}
