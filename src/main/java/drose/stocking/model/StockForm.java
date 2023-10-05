package drose.stocking.model;

import drose.stocking.app.StockingWorkflowForm;

public class StockForm extends StockingWorkflowForm{
    // detail info to temp detail info
    private String nama_produk;
    private String kategori;
    private String harga;
    private String stock;
    
    // header info to temp header 
    private String nama_toko;
    private String status ;
    private String alamat;

    // file info to temp file
    
    
    public StockForm() {
    }

    public StockForm(String nama_produk, String kategori, String harga, String stock, String nama_toko, String status,
            String alamat) {
        super();
        this.nama_produk = nama_produk;
        this.kategori = kategori;
        this.harga = harga; 
        this.stock = stock;
        this.nama_toko = nama_toko;
        this.status = status;
        this.alamat = alamat;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
}

