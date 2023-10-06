package drose.stocking.model;

public class StockingHeaderInfoModel {
    private int id;
    private String system_matter_id;
    private String user_data_id ;
    private String status ;
    private String nama_toko;
    private String alamat;
    
   
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSystem_matter_id() {
        return system_matter_id;
    }
    public void setSystem_matter_id(String system_matter_id) {
        this.system_matter_id = system_matter_id;
    }
    public String getUser_data_id() {
        return user_data_id;
    }
    public void setUser_data_id(String user_data_id) {
        this.user_data_id = user_data_id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getNama_toko() {
        return nama_toko;
    }
    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
}
