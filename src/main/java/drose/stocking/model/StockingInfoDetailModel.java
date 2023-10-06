package drose.stocking.model;

public class StockingInfoDetailModel {
    private int id;
    private String system_matter_id;
    private String user_data_id;
    private String nama_produk;
    private String kategori;
    private String harga;
    private String stock;
    private String nama_toko;
    
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

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getNama_toko() {
        return nama_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }
    
    
}
