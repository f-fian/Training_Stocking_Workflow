package drose.stocking.app;

import java.util.List;

import drose.stocking.model.StockingInfoDetailModel;
import drose.stocking.model.StockingInfoFileModel;

public class StockingForm extends ContractWorkflowForm {
    /* General Information */
    
      private String nama_produk;
        private String kategori;
        private String harga;
        private String stock;
        
        // header info to temp header 
        private String nama_toko;
        private String status ;
        private String alamat;
        
        private List<StockingInfoFileModel> fileData;
        private List<StockingInfoDetailModel> detailData;

        // file info to temp file
        
        
        public List<StockingInfoFileModel> getFileData() {
			return fileData;
		}

		public void setFileData(List<StockingInfoFileModel> fileData) {
			this.fileData = fileData;
		}

		public List<StockingInfoDetailModel> getDetailData() {
			return detailData;
		}

		public void setDetailData(List<StockingInfoDetailModel> detailData) {
			this.detailData = detailData;
		}

		public StockingForm() {
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