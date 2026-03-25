package Ex_4;
import java.util.List;

class BenhNhanDTO {
    private int id;
    private String hoTen;
    private List<DichVuDTO> dsDichVu;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public List<DichVuDTO> getDsDichVu() { return dsDichVu; }
    public void setDsDichVu(List<DichVuDTO> dsDichVu) { this.dsDichVu = dsDichVu; }
}

class DichVuDTO {
    private int id;
    private String tenDichVu;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTenDichVu() { return tenDichVu; }
    public void setTenDichVu(String tenDichVu) { this.tenDichVu = tenDichVu; }
}