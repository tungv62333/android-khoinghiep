package vutritung_pm2502.com.kiemtra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DTOAdpater extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<DTO> arrayDTO;

    public DTOAdpater(MainActivity context, int layout, List<DTO> arrayDTO) {
        this.context = context;
        this.layout = layout;
        this.arrayDTO = arrayDTO;
    }

    @Override
    public int getCount() {
        return arrayDTO.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            //ánh xạ view
            holder.txtnhapx = (TextView) view.findViewById(R.id.nhapx);
            holder.txtnhapy = (TextView) view.findViewById(R.id.nhapy);
            holder.txtnhapdiem = (TextView) view.findViewById(R.id.nhapdiem);
            holder.imgxoa = (ImageView) view.findViewById(R.id.xoa);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        // gán giá trị
        DTO dto = arrayDTO.get(i);
        holder.txtnhapx.setText(dto.getX());
        holder.txtnhapy.setText(dto.getY());
        holder.txtnhapdiem.setText(dto.getTendiem());
        return view;
    }

    private class ViewHolder{
        TextView txtnhapx, txtnhapy, txtnhapdiem;
        ImageView imgxoa;
    }

}
