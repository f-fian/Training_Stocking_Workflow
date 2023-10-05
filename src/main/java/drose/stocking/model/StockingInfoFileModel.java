package drose.stocking.model;

public class StockingInfoFileModel {
    private String id;
    private String system_matter_id;
    private String user_data_id;
    private String file_name;
    private String file_real_name;
    private String file_path;
    private String created_at;
    
    public StockingInfoFileModel(String id, String system_matter_id, String user_data_id, String file_name,
            String file_real_name, String file_path, String created_at) {
        super();
        this.id = id;
        this.system_matter_id = system_matter_id;
        this.user_data_id = user_data_id;
        this.file_name = file_name;
        this.file_real_name = file_real_name;
        this.file_path = file_path;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_real_name() {
        return file_real_name;
    }

    public void setFile_real_name(String file_real_name) {
        this.file_real_name = file_real_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
