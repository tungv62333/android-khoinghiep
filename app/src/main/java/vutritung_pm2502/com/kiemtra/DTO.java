package vutritung_pm2502.com.kiemtra;

public class DTO {
    private String x;
    private String y;
    private String tendiem;

    public DTO(String x, String y, String tendiem) {
        this.x = x;
        this.y = y;
        this.tendiem = tendiem;
    }

    public String getTendiem() {
        return tendiem;
    }

    public void setTendiem(String tendiem) {
        this.tendiem = tendiem;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
    // dto là khai báo trường dữ liệu
    // dto adapter để truyền dữ liệu vào list
    //mainacivity dùng để hiển thị chung các công việc ( Xóa, hiển thị, thêm)

}
