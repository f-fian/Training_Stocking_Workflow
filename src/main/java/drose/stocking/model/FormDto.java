package drose.stocking.model;

public class FormDto {
	private String nama_toko;
	private String alamat;
	private String nama_produk;
	private String kategori;
	private String harga;
	private String stok;
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
	public String getStok() {
		return stok;
	}
	public void setStok(String stok) {
		this.stok = stok;
	}
	
	@Override
	public String toString() {
		return "FormDto [nama_toko=" + nama_toko + ", alamat=" + alamat + ", nama_produk=" + nama_produk + ", kategori="
				+ kategori + ", harga=" + harga + ", stok=" + stok + "]";
	}
	
	
}
