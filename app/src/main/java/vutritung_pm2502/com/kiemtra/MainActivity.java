package vutritung_pm2502.com.kiemtra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    PopupWindow popupWindow;
    ListView lvdiem;
    Button btnthem;
    DTO selectedPoint;

    ImageView imageViewX,imageViewV;
    private ArrayList<DTO> dtoArrayList;
    private DTOAdpater adapter;
    int onclick = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();





        adapter = new DTOAdpater(this,R.layout.point_item,dtoArrayList);
        lvdiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPoint = dtoArrayList.get(i);
                showItemDetailsLayout(selectedPoint);

                ImageView imageViewXoa = view.findViewById(R.id.xoa);

                // Đặt OnClickListener cho ImageView để xử lý sự kiện xóa
                imageViewXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        XacNhanXoa(i); // Gọi phương thức XacNhanXoa để xóa phần tử tại vị trí i
                    }
                });
            }
        });


        lvdiem.setAdapter(adapter);


        // add button
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.them_dto);
                EditText edtNhapx = dialog.findViewById(R.id.edtnhapx);
                EditText edtNhapY = dialog.findViewById(R.id.edtnhapy);
                EditText edtNhapDiem = dialog.findViewById(R.id.edtnhapdiem);
                Button btnthem = dialog.findViewById(R.id.addscore);
                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String X="",Y="",tendiem="";
                        if (!edtNhapx.getText().toString().equals("")) {
                            X = edtNhapx.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Enter Contact X",Toast.LENGTH_SHORT).show();
                        }
                        if (!edtNhapDiem.getText().toString().equals("")) {
                            tendiem = edtNhapDiem.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Enter Contact Y",Toast.LENGTH_SHORT).show();
                        }
                        if (!edtNhapY.getText().toString().equals("")) {
                            Y = edtNhapY.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Enter Contact TenDiem",Toast.LENGTH_SHORT).show();
                        }
                        dtoArrayList.add(new DTO(X,Y,tendiem));
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void AnhXa() {
        lvdiem = (ListView) findViewById(R.id.listdto);
        btnthem = (Button) findViewById(R.id.buttonthem);
        imageViewX = (ImageView) findViewById(R.id.xoa);
        imageViewV = (ImageView) findViewById(R.id.imagedefault);
        dtoArrayList = new ArrayList<>();
        dtoArrayList.add(new DTO("165", "165","A" ));
        dtoArrayList.add(new DTO("326", "152","B" ));
        dtoArrayList.add(new DTO("199", "1","C" ));
    }

    // Info
    private void showItemDetailsLayout (final DTO selectedPoint) {
        // Tạo layout hiển thị thông tin chi tiết sản phẩm
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View editView = inflater.inflate(R.layout.thongtin_point, null);

        // Lấy các View trong layout chi tiết
        final EditText editTextX = editView.findViewById(R.id.x);
        final EditText editTextY = editView.findViewById(R.id.y);
        final EditText editTextName = editView.findViewById(R.id.namepoint);
        Button update = editView.findViewById(R.id.update);

        // Hiển thị thông tin của phần tử được chọn
        editTextX.setText(selectedPoint.getX());
        editTextY.setText(selectedPoint.getY());
        editTextName.setText(selectedPoint.getTendiem());

        // update
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cập nhật thông tin của phần tử được chọn
                String newX = editTextX.getText().toString();
                String newY = editTextY.getText().toString();
                String newName = editTextName.getText().toString();

                selectedPoint.setX(newX);
                selectedPoint.setY(newY);
                selectedPoint.setTendiem(newName);

                // Cập nhật ListView
                adapter.notifyDataSetChanged();

                // Đóng layout sửa phần tử và hiển thị lại menu popup trước
                popupWindow.dismiss();
            }
        });

        // Hiển thị layout chi tiết trong PopupWindow
        popupWindow = new PopupWindow(editView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(editView, Gravity.CENTER, 0, 0);
    }

    //Remove


    private void XacNhanXoa(int position){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông báo!");
        alert.setIcon(R.mipmap.ic_launcher);
        alert.setMessage("Bạn có muốn DELETE tọa độ này không , Vui lòng chọn Có/Không?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dtoArrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }


}